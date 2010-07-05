/*
 * Lisans bilgisi icin lutfen proje ana dizinindeki zemberek2-lisans.txt dosyasini okuyunuz.
 */

package net.zemberek.yapi;

import net.zemberek.araclar.Kayitci;
import net.zemberek.bilgi.KaynakYukleyici;
import net.zemberek.bilgi.ZemberekAyarlari;
import net.zemberek.bilgi.araclar.DuzYaziKokOkuyucu;
import net.zemberek.bilgi.araclar.IkiliKokOkuyucu;
import net.zemberek.bilgi.araclar.IkiliKokYazici;
import net.zemberek.bilgi.araclar.KokOkuyucu;
import net.zemberek.bilgi.kokler.AgacSozluk;
import net.zemberek.bilgi.kokler.Sozluk;
import net.zemberek.islemler.DenetlemeCebi;
import net.zemberek.islemler.cozumleme.CozumlemeYardimcisi;
import net.zemberek.tr.islemler.TurkceCozumlemeYardimcisi;
import net.zemberek.tr.yapi.TurkceHeceleyici;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import net.zemberek.tr.yapi.kok.TurkceKokOzelDurumBilgisi;
import net.zemberek.yapi.ek.EkKuralKelimesiCozumleyici;
import net.zemberek.yapi.ek.EkYonetici;
import net.zemberek.yapi.ek.TemelEkYonetici;
import net.zemberek.yapi.ek.XmlEkOkuyucu;
import net.zemberek.yapi.kok.KokOzelDurumBilgisi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Bir dil icin gerekli parametrelerin kolay uretimi icin kullanilan fabrika sinifi.
 * Dile ozel siniflara iliskin nesneler reflection ile uretilir. Hangi dilin hangi sinifa
 * sahip oldgusu gibi bilgiler ilklendirme sirasindaki giris parametresi olan DilAyarlari
 * nesnesinden edinilir.
 * <p/>
 * User: ahmet
 * Date: Sep 17, 2006
 */
public class TurkceDilBilgisi implements DilBilgisi {

    private DilAyarlari dilAyarlari;
    private String dilAdi;

    private Alfabe alfabe;
    private Sozluk sozluk;
    private DenetlemeCebi cep;
    private CozumlemeYardimcisi yardimci;
    private EkYonetici ekYonetici;
    private KokOzelDurumBilgisi ozelDurumBilgisi;
    private Heceleyici heceleyici;

    private static Logger logger = Kayitci.kayitciUret(TurkceDilBilgisi.class);

    private final String bilgiDizini;

    private final String alfabeDosyaAdi;
    private final String ekDosyaAdi;
    private final String kokDosyaAdi;


    private boolean cepKullan = true;

    /**
     * istenilen dilayarlari nesnesine gore cesitli parametreleri (bilgi dizin adi, kaynak dosyalarin locale
     * uyumlu adlari gibi) olusturur. bilgi dosyalari
     * kaynaklar/<locale>/bilgi/ ana dizini altinda yer almak zorundadir.
     *
     * @param dilAyarlari
     */
    public TurkceDilBilgisi(DilAyarlari dilAyarlari) {

        this.dilAyarlari = dilAyarlari;
        this.dilAdi = dilAyarlari.ad();
        bilgiDizini = "resources/" + dilAyarlari.locale().getLanguage() + "/";
        alfabeDosyaAdi = dosyaAdiUret("harf", "txt");
        ekDosyaAdi = bilgiDizini + "suffix.xml";
        kokDosyaAdi = "kokler_tr.bin";

        try {
            alfabe = new Alfabe(alfabeDosyaAdi, dilAyarlari.locale().getLanguage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        EkKuralKelimesiCozumleyici kuralCozumleyici = new EkKuralKelimesiCozumleyici(alfabe, dilAyarlari.ekKuralBilgisi());
        XmlEkOkuyucu ekOkuyucu = new XmlEkOkuyucu(
                ekDosyaAdi,
                dilAyarlari.ekUretici(alfabe),
                dilAyarlari.ekOzelDurumUretici(alfabe),
                kuralCozumleyici);

        try {
            ekYonetici = new TemelEkYonetici(dilAyarlari.baslangiEkAdlari(), ekOkuyucu);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ozelDurumBilgisi = new TurkceKokOzelDurumBilgisi(ekYonetici, alfabe);

        heceleyici = new TurkceHeceleyici();

        yardimci = new TurkceCozumlemeYardimcisi(alfabe);

        if (!new KaynakYukleyici().kaynakMevcutmu(kokDosyaAdi)) {
            logger.warning("binary kok dosyasi bulunamadi. proje icerisinden calisildigi varsayilarak \n" +
                    "calisilan dizine goreceli olarak '" + kokDosyaAdi + "' dosyasi uretilmeye calisacak.\n" +
                    "eger duz yazki kok bilgilerine erisim saglanamazsa sistem kok bilgisine uretemeycektir. ");
            try {
                ikiliKokDosyasiUret();
            } catch (IOException e) {
                logger.severe("kok bilgilerine erisim saglanamadigindan uygulama calismaya devam edemez." + e.getMessage());
            }
        }
        kokOzelDurumlari();
        logger.fine("Ikili okuyucu uretiliyor:");
        try {
            KokOkuyucu okuyucu = new IkiliKokOkuyucu(kokDosyaAdi, ozelDurumBilgisi);
            logger.fine("Sozluk ve agac uretiliyor:" + dilAdi);
            sozluk = new AgacSozluk(okuyucu, alfabe, ozelDurumBilgisi);
        } catch (IOException e) {
            logger.severe("sozluk uretilemiyor." + e.getMessage());
        }

    }

    public TurkceDilBilgisi(DilAyarlari dilAyarlari, ZemberekAyarlari zemberekAyarlari) {
        this(dilAyarlari);
        this.cepKullan = zemberekAyarlari.cepKullan();
    }

    /**
     * kok_<locale>.uzanti dosya adini uretir.
     *
     * @param kok
     * @param uzanti
     * @return olusan kaynak dosyasi adi.
     */
    private String dosyaAdiUret(String kok, String uzanti) {
        return bilgiDizini + kok + '_' + dilAyarlari.locale().getLanguage() + '.' + uzanti;
    }

    public Alfabe alfabe() {
        return alfabe;
    }

    public EkYonetici ekler() {
        return ekYonetici;
    }

    /**
     * Sozluk, daha dogrusu Kokleri tasiyan agac ve iliskili kok secicileri tasiyan nesneyi uretir
     * Proje gelistirime asamasinda, eger ikili kok-sozluk dosyasi (kokler_xx.bin) dosyasi mevcut
     * degilse once onu uretmeye calisir, daha sonra asil sozluk uretim islemini yapar.
     * Normal kosullarda dagitim jar icerisinde bu dosya yer alacagindan bu islem (bin dosya uretimi) atlanir.
     *
     * @return Sozluk
     */
    public Sozluk kokler() {
        return sozluk;
    }

    public KokOzelDurumBilgisi kokOzelDurumlari() {
        return ozelDurumBilgisi;
    }

    public DenetlemeCebi denetlemeCebi() {
        return null;
    }

    public DilAyarlari dilAyarlari() {
        return dilAyarlari;
    }

    public Heceleyici heceBulucu() {
        return heceleyici;
    }


    public CozumlemeYardimcisi cozumlemeYardimcisi() {
        return yardimci;
    }

    /**
     * Bu metod ile ikili kok bilgisi dosyasi (kokler_xx.bin uretilir.)
     * Eger uretim sirasinda istatistik bilgisi mevcutsa bu da kullanilir.
     *
     * @throws IOException
     */
    public void ikiliKokDosyasiUret() throws IOException {
        alfabe();
        ekler();
        kokOzelDurumlari();
        logger.info("Ikili sozluk dosyasi olusturuluyor...");

        //kokleri duz yazi dosyalardan oku
        List<Kok> tumKokler = new ArrayList<Kok>();
        for (String dosyaAdi : dilAyarlari.duzYaziKokDosyalari()) {
            KokOkuyucu okuyucu = new DuzYaziKokOkuyucu(
                    dosyaAdi,
                    ozelDurumBilgisi,
                    alfabe,
                    dilAyarlari.kokTipiAdlari());
            List<Kok> list = okuyucu.hepsiniOku();
            logger.info("Okunan kok sayisi: " + list.size());
            tumKokler.addAll(list);
        }
        logger.info("Toplam kok sayisi:" + tumKokler.size());

        // kokleri ikili olarak kaydet.
        IkiliKokYazici ozelYazici = new IkiliKokYazici(kokDosyaAdi);
        ozelYazici.yaz(tumKokler);
    }

    /**
     * Ana sinif calistiginda ikiliKokDosyasiUret uret sinifini calistirir. Eger parametre olarak
     * dil ayar sinifi adi gonderilirse iliskili dil icin uretim yapar. aksi halde Turkiye Turkcesi icin
     * ikili kok-sozluk dosyasini olusturur.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TurkceDilBilgisi td = new TurkceDilBilgisi(new TurkiyeTurkcesi());
        List<Kok> kokler = td.kokler().kokBul("elma");
        for (Kok kok : kokler) {
            System.out.println("Lemma:" + kok);
        }
    }

}
