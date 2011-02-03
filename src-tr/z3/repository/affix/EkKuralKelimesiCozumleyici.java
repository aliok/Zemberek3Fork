package z3.repository.affix;

import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkishAlphabet;

import java.util.*;

/**
 * Ek bilgi dosyasinda yer alan ek kural kelimeinin cozumlenmesinde kullanilir.
 *
 * Basit bir tokenizer. Kural kelimesini dile gore ozel kural bilgilerini kullanarak
 * EkUretimBileseni listesine donusturur.
 */
public class EkKuralKelimesiCozumleyici {

    private TurkishAlphabet alfabe;
    /**
     * Ek kural bilgisi nesnesi dile ozel ek kural kelime enum sinifindan elde edilir.
     */
    private EkKuralBilgisi ekKuralBilgisi;


    public EkKuralKelimesiCozumleyici(TurkishAlphabet alfabe, EkKuralBilgisi ekKuralBilgisi) {
        this.alfabe = alfabe;
        this.ekKuralBilgisi = ekKuralBilgisi;
    }

    public List<EkUretimBileseni> cozumle(String uretimKelimesi) {
        if (uretimKelimesi == null || uretimKelimesi.length() == 0)
            return Collections.emptyList();
        List<EkUretimBileseni> bilesenler = new ArrayList<EkUretimBileseni>();
        Iterator<EkUretimBileseni> it = new BilesenIterator(uretimKelimesi.trim().replaceAll("[ ]", ""));
        while (it.hasNext())
            bilesenler.add(it.next());
        return bilesenler;
    }

    class BilesenIterator implements Iterator<EkUretimBileseni> {

        private int pointer;
        private final String uretimKelimesi;


        public BilesenIterator(String uretimKelimesi) {
            this.uretimKelimesi = uretimKelimesi;
        }

        public boolean hasNext() {
            return uretimKelimesi != null && pointer < uretimKelimesi.length();
        }

        public EkUretimBileseni next() {
            if (!hasNext()) {
                throw new NoSuchElementException("bilesen kalmadi!");
            }
            char p = uretimKelimesi.charAt(pointer++);
            //ardisil harf ile iliskili kuralmi
            if (ekKuralBilgisi.harfKuralKarakterleri().contains(p)) {

                if (pointer == uretimKelimesi.length())
                    throw new IllegalArgumentException(p + " kuralindan sonra normal harf bekleniyordu!");

                char h = uretimKelimesi.charAt(pointer++);

                if (ekKuralBilgisi.sesliKuralKarakterleri().contains(h))
                    throw new IllegalArgumentException(p + " kuralindan sonra sesli uretim kurali gelemez:" + h);

                return new EkUretimBileseni(ekKuralBilgisi.karakterKuralTablosu().get(p), alfabe.getLetter(h));

            } else if (ekKuralBilgisi.sesliKuralKarakterleri().contains(p)) {
                return new EkUretimBileseni(ekKuralBilgisi.karakterKuralTablosu().get(p), TurkicLetter.UNDEFINED);
            } else if (alfabe.getLetter(p) != null && Character.isLowerCase(p)) {
                return new EkUretimBileseni(ekKuralBilgisi.harfEklemeKurali(), alfabe.getLetter(p));
            } else {
                throw new IllegalArgumentException(p + "  simgesi cozumlenemiyor.. kelime:" + uretimKelimesi);
            }
        }

        public void remove() {
        }
    }
}