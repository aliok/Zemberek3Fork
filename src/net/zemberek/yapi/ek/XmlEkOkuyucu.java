/*
 * Lisans bilgisi icin lutfen proje ana dizinindeki zemberek2-lisans.txt dosyasini okuyunuz.
 */

package net.zemberek.yapi.ek;

import net.zemberek.araclar.Kayitci;
import net.zemberek.araclar.XmlYardimcisi;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * xml ek dosyasindan ek bilgilerini okur ve ekleri olusturur.
 * User: ahmet
 * Date: Aug 15, 2005
 */
public class XmlEkOkuyucu {

    private static Logger log = Kayitci.kayitciUret(XmlEkOkuyucu.class);

    private Map<String, Set<Ek>> ekKumeleri = new HashMap<String, Set<Ek>>();
    private Map<String, Ek> ekler = new HashMap<String, Ek>();

    private final String xmlEkDosyasi;
    private final EkUretici ekUretici;

    private final EkOzelDurumUretici ekOzelDurumUretici;
    private final EkKuralKelimesiCozumleyici kuralKelimesiCozumleyici;

    public XmlEkOkuyucu(String xmlEkDosyasi,
                        EkUretici ekUretici,
                        EkOzelDurumUretici ekOzelDurumUretici,
                        EkKuralKelimesiCozumleyici kuralKelimesiCozumleyici
    ) {
        this.xmlEkDosyasi = xmlEkDosyasi;
        this.ekUretici = ekUretici;
        this.ekOzelDurumUretici = ekOzelDurumUretici;
        this.kuralKelimesiCozumleyici = kuralKelimesiCozumleyici;

    }

    public Map<String, Ek> getEkler() {
        return ekler;
    }

    public void xmlOku() throws IOException {
        Document document = XmlYardimcisi.xmlDosyaCozumle(xmlEkDosyasi);

        // kok elemente ulas.
        Element kokElement = document.getDocumentElement();

        ilkEkleriOlustur(XmlYardimcisi.ilkEleman(kokElement, "suffixes"));
        ekKumeleriniOlustur(XmlYardimcisi.ilkEleman(kokElement, "suffix-sets"));
        ekleriOlustur(XmlYardimcisi.ilkEleman(kokElement, "suffixes"));
    }

    /**
     * xml dosyadan sadece eklerin adlarini okuyup Ek nesnelerin ilk hallerinin
     * olusturulmasini saglar.
     *
     * @param eklerElement tum ekleri iceren Ekler bileseni
     */
    private void ilkEkleriOlustur(Element eklerElement) {
        List<Element> tumEkler = XmlYardimcisi.elemanlar(eklerElement, "suffix");
        // tum ekleri bos haliyle uret.
        for (Element ekElement : tumEkler) {
            String ekadi = ekElement.getAttribute("id");
            if (ekler.containsKey(ekadi))
                throw new EkKonfigurasyonHatasi("Ek tekrari! " + ekadi);
            ekler.put(ekadi, new Ek(ekadi));
        }
    }

    /**
     * xml dosyadan ek kumelerini ayiklar. sonuclar ekKumeleri Map'ina atilir.
     *
     * @param ekKumeleriElement tum ek kumelerini iceren xml bileseni.
     */
    private void ekKumeleriniOlustur(Element ekKumeleriElement) {
        List<Element> xmlKumeler = XmlYardimcisi.elemanlar(ekKumeleriElement, "suffix-set");
        for (Element ekKumeEl : xmlKumeler) {
            String kumeAdi = ekKumeEl.getAttribute("id");           
            Set<Ek> kumeEkleri = new HashSet<Ek>(getSuffixesfromCommaSeparated(ekKumeEl.getTextContent()));
            ekKumeleri.put(kumeAdi, kumeEkleri);
        }
    }

    /**
     * asil ek nesnelerinin olusturulma islemi burada olur.
     *
     * @param eklerElement tum ekleri iceren xml ekler bileseni.
     */
    private void ekleriOlustur(Element eklerElement) {
        List<Element> tumEkler = XmlYardimcisi.elemanlar(eklerElement, "suffix");
        for (Element ekElement : tumEkler) {
            String ekAdi = ekElement.getAttribute("id");
            Ek ek = this.ekler.get(ekAdi);
            // uretim kuralini oku ve ekleri uret.
            Attr uretimKurali = ekElement.getAttributeNode("gen");
            if (uretimKurali == null)
                throw new EkKonfigurasyonHatasi("ek uretim kural kelimesi yok!" + ekAdi);

            ek.setArdisilEkler(ardisilEkleriOlustur(ek, ekElement));
            ek.setEkKuralCozumleyici(ekUretici);
            List<EkUretimBileseni> bilesenler = kuralKelimesiCozumleyici.cozumle(uretimKurali.getValue());
            ek.setUretimBilesenleri(bilesenler);
            List<EkOzelDurumu> ozelDurumlar = ozelDurumlariOku(ekElement);
            ek.setOzelDurumlar(ozelDurumlar);

            ek.setSesliIleBaslayabilir(ekUretici.sesliIleBaslayabilir(bilesenler));

            ek.baslangicHarfleriEkle(ekUretici.olasiBaslangicHarfleri(bilesenler));
            for (EkOzelDurumu oz : ozelDurumlar) {
                ek.baslangicHarfleriEkle(ekUretici.olasiBaslangicHarfleri(oz.uretimBilesenleri()));
            }
        }
        log.fine("ek olusumu sonlandi.");
    }

    private List<EkOzelDurumu> ozelDurumlariOku(Element ekElement) {
        List<EkOzelDurumu> ozelDurumlar = new ArrayList<EkOzelDurumu>();
        //xml ozel durumlarini al.
        List<Element> ozelDurumlarXml = XmlYardimcisi.elemanlar(ekElement, "exception");
        if (ozelDurumlarXml == null) return Collections.emptyList();

        for (Element element : ozelDurumlarXml) {
            String ozelDurumAdi = element.getAttribute("id");
            EkOzelDurumu oz = ekOzelDurumUretici.uret(ozelDurumAdi);
            Attr uretimKurali = element.getAttributeNode("gen");

            if (uretimKurali != null) {
                oz.setEkKuralCozumleyici(ekUretici);
                oz.setUretimBilesenleri(kuralKelimesiCozumleyici.cozumle(uretimKurali.getValue()));
            }

            Set<Ek> onekler = new HashSet<Ek>(getSuffixesfromCommaSeparated(element.getTextContent()));
            oz.setOnEkler(onekler);
            ozelDurumlar.add(oz);
        }
        return ozelDurumlar;
    }

    /**
     * Bir eke iliskin ardisil ekler belirlenir. ardisil ekler
     * a) ek kumelerinden
     * b) normal tek olarak
     * c) dogrudan baska bir ekin ardisil eklerinden kopyalanarak
     * elde edilir.
     * Ayrica eger oncelikli ekler belirtilmis ise bu ekler ardisil ek listeisnin en basina koyulur.
     *
     * @param ekElement :  ek xml bileseni..
     * @param anaEk     ardisil ekler eklenecek asil ek
     * @return Ek referans Listesi.
     */
    private List<Ek> ardisilEkleriOlustur(Ek anaEk, Element ekElement) {

        Set<Ek> ardisilEkSet = new HashSet<Ek>();
        Element ardisilEklerEl = XmlYardimcisi.ilkEleman(ekElement, "allowed-suffixes");
        if (ardisilEklerEl == null) return Collections.emptyList();

        String ekListeString = ardisilEklerEl.getTextContent();
        ardisilEkSet.addAll(getSuffixesfromCommaSeparated(ekListeString));

        //varsa baska bir ekin ardisil eklerini kopyala.
        Attr attr = ardisilEklerEl.getAttributeNode("copy-from");
        if (attr != null) {
            final String kopyaEkadi = attr.getValue();
            Ek ek = this.ekler.get(kopyaEkadi);
            if (ek == null)
                throw new EkKonfigurasyonHatasi(anaEk.ad() + " icin kopyalanacak ek bulunamiyor! " + kopyaEkadi);
            ardisilEkSet.addAll(ek.ardisilEkler());
        }

        return new ArrayList<Ek>(ardisilEkSet);
    }

    private List<Ek> getSuffixesfromCommaSeparated(String ekListeString) {
        List<Ek> ekList = new ArrayList<Ek>();
        for (String token_ : ekListeString.split("[,]")) {
            String token = token_.trim();
            if (token.length() == 0)
                continue;
            if (!ekKumeleri.containsKey(token) && !this.ekler.containsKey(token))
                throw new EkKonfigurasyonHatasi(token + " icin ek ya da kume bulunamiyor.");
            if (ekKumeleri.containsKey(token))
                ekList.addAll(ekKumeleri.get(token));
            if (this.ekler.containsKey(token))
                ekList.add(this.ekler.get(token));
        }
        return ekList;
    }
}

class EkKonfigurasyonHatasi extends RuntimeException {

    public EkKonfigurasyonHatasi(String message) {
        super(message);
    }
}