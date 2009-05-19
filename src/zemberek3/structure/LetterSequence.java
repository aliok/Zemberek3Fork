package zemberek3.structure;

public class LetterSequence implements CharSequence, Comparable<LetterSequence> {

    private Letter[] dizi;
    private int boy = 0;
    public static final LetterSequence EMPTY_SEQUENCE = new LetterSequence(0);

    /**
     * default constructor. 7 boyutlu bir Letter referans dizisi olusturur.
     */
    public LetterSequence() {
        dizi = new Letter[7];
    }

    /**
     * 'kapasite' boyutlu 'Letter' dizisine sahip nesne olusturur.
     *
     * @param kapasite baslangic olusan Letter[] boyu
     */
    public LetterSequence(int kapasite) {
        dizi = new Letter[kapasite];
    }

    /**
     * 'kapasite' boyutlu 'Letter' dizisine sahip nesne olusturur. daha sonra
     * girisi String'i icindeki karakterleri Letter seklinde Letter dizisine aktarir.
     * Eger String boyu kapasiteden buyukse kapasite'yi boy'a esitler.
     * Eger String icindeki karakter Alfabe'de yar almiyorsa "TANIMSIZ_HARF" harfi olarak eklenir.
     *
     * @param str      ornek alincak String
     * @param kapasite baslangic olusan Letter[] boyu
     * @param alfabe   ilgili alfabe
     */
    public LetterSequence(String str, Alphabet alfabe, int kapasite) {
        if (kapasite < str.length())
            kapasite = str.length();
        dizi = new Letter[kapasite];
        boy = str.length();
        for (int i = 0; i < boy; i++)
            dizi[i] = alfabe.getLetter(str.charAt(i));
    }


    /**
     * Belirlenen alfabe ile String icerigini Harflere donusturur.
     *
     * @param str    ornek alincak String
     * @param alfabe ilgili alfabe
     */
    public LetterSequence(String str, Alphabet alfabe) {
        boy = str.length();
        dizi = new Letter[boy];
        for (int i = 0; i < boy; i++)
            dizi[i] = alfabe.getLetter(str.charAt(i));
    }

    /**
     * Copy-Constructor. gelen harf dizisi ile ayni icerige sahip olacak sekilde
     * Letter dizisi olusturur.
     *
     * @param hdizi ornek alinacak LetterSequence
     */
    public LetterSequence(LetterSequence hdizi) {
        boy = hdizi.length();
        dizi = new Letter[boy];
        System.arraycopy(hdizi.dizi, 0, dizi, 0, boy);
    }

    /**
     * gelen Letter dizisini icerige kopyalar.
     *
     * @param inpDizi kopyalancak Letter dizisi.
     */
    private LetterSequence(Letter[] inpDizi) {
        boy = inpDizi.length;
        dizi = new Letter[boy];
        System.arraycopy(inpDizi, 0, dizi, 0, boy);
    }

    /**
     * bu metod harf referansi dizisini serbest birakmaz,
     * sadece boyu sifira indirir.
     */
    public void sil() {
        boy = 0;
    }

    /**
     * Dizinin son harfini dondurur.
     *
     * @return varsa son harf, Yoksa TANIMSIZ_HARF.
     */
    public Letter sonHarf() {
        if (boy > 0)
            return dizi[boy - 1];
        else
            return Alphabet.UNDEFINED_LETTER;
    }

    /**
     * dizideki son sesliyi dondurur. eger dizi boyu 0 ise ya da sesli harf yoksa
     * TANIMSIZ_HARF doner.
     *
     * @return varsa son sesli yoksa TANIMSIZ_HARF
     */
    public Letter sonSesli() {
        for (int i = boy - 1; i >= 0; i--) {
            if (dizi[i].isVowel())
                return dizi[i];
        }
        return Alphabet.UNDEFINED_LETTER;
    }

    /**
     * ic metod. harf dizisinin boyutu yetersiz geldiginde "ek" miktarinda daha
     * fazla yere sahip yeni dizi olusturulup icerik yeni diziye kopyalanir.
     *
     * @param ek eklenecek LetterSequence miktari.
     */
    private void kapasiteAyarla(int ek) {
        Letter[] yeniDizi = new Letter[dizi.length + ek];
        System.arraycopy(dizi, 0, yeniDizi, 0, dizi.length);
        dizi = yeniDizi;
    }

    /**
     * otomatik kapasite ayarlama. dizi boyu iki katina cikarilir.
     */
    private void kapasiteAyarla() {
        Letter[] yeniDizi = new Letter[dizi.length * 2];
        System.arraycopy(dizi, 0, yeniDizi, 0, dizi.length);
        dizi = yeniDizi;
    }

    /**
     * kelimenin sonuna harf ekler.
     *
     * @param harf eklenecek harf
     * @return this
     */
    public LetterSequence ekle(Letter harf) {
        if (boy == dizi.length)
            kapasiteAyarla(3);
        dizi[boy++] = harf;
        return this;
    }

    /**
     * girilen pozisyona herf ekler, bu noktadan sonraki harfler otelenir.
     * "armut" icin (2, a) "aramut" uretir.
     *
     * @param index eklenecek pozisyon
     * @param harf  eklenecek harf.
     * @throws ArrayIndexOutOfBoundsException
     */
    public void ekle(int index, Letter harf) {
        if (index < 0 || index > boy)
            throw new ArrayIndexOutOfBoundsException("index degeri:" + index + " fakat harf dizi boyu:" + boy);

        if (boy == dizi.length)
            kapasiteAyarla();

        for (int i = boy - 1; i >= index; i--)
            dizi[i + 1] = dizi[i];
        dizi[index] = harf;
        boy++;
    }

    /**
     * Diziye baska bir harf dizisinin icerigini ular.
     *
     * @param hdizi ulanacak harf dizisi.
     * @return this.
     */
    public LetterSequence ekle(LetterSequence hdizi) {
        int hboy = hdizi.length();
        if (boy + hboy > dizi.length)
            kapasiteAyarla(hboy);

        System.arraycopy(hdizi.dizi, 0, dizi, boy, hboy);
        boy += hdizi.length();
        return this;
    }

    /**
     * Diziye baska bir harf dizisinin icerigini index ile belirtilen harften itibaren ekler.
     * "armut" icin (2, hede) "arhedemut" uretir.
     *
     * @param index eklencek pozisyon
     * @param hdizi eklenecek harf dizisi
     * @return this.
     * @throws ArrayIndexOutOfBoundsException
     */
    public LetterSequence ekle(int index, LetterSequence hdizi) {
        if (index < 0 || index > boy)
            throw new ArrayIndexOutOfBoundsException("indeks degeri:" + index + " fakat harf dizi boyu:" + boy);

        //dizi kapasitesini ayarla
        int hboy = hdizi.length();
        if (boy + hboy > dizi.length)
            kapasiteAyarla(hboy);

        //sondan baslayarak this.dizinin index'ten sonraki kismini dizinin sonuna tasi
        for (int i = hboy + boy - 1; i >= hboy; i--)
            dizi[i] = dizi[i - hboy];

        //gelen diziyi kopyala ve boyutu degistir.
        System.arraycopy(hdizi.dizi, 0, dizi, index, hboy);
        boy += hdizi.length();
        return this;
    }

    public LetterSequence araDizi(int bas, int son) {
        if (son < bas) return null;
        Letter[] yeniHarfler = new Letter[son - bas];
        System.arraycopy(dizi, bas, yeniHarfler, 0, son - bas);
        return new LetterSequence(yeniHarfler);
    }

    /**
     * verilen pozisyondaki harfi dondurur. icerigi "kedi" olan LetterSequence icin
     * harf(1) e dondurur.
     *
     * @param i istenilen pozisyondaki harf.
     * @return girilen pozisyondaki harf, yoksa TANIMSIZ_HARF
     */
    public Letter harf(int i) {
        if (i < 0)
            return Alphabet.UNDEFINED_LETTER;
        if (i < boy)
            return dizi[i];
        return Alphabet.UNDEFINED_LETTER;
    }

    /**
     * ilk sesliyi dondurur. eger sesli yoksa UNDEFINED_LETTER doner. aramaya belirtilen indeksten baslar.
     *
     * @param basla baslangic indeksi.
     * @return varsa ilk sesli, yoksa UNDEFINED_LETTER
     */
    public Letter ilkSesli(int basla) {
        for (int i = basla; i < boy; i++) {
            if (dizi[i].isVowel())
                return dizi[i];
        }
        return Alphabet.UNDEFINED_LETTER;
    }

    /**
     * Tam esitlik kiyaslamasi. kiyaslama nesne tipi, ardindan da Letter dizisi icindeki
     * harflerin char iceriklerine gore yapilir.
     *
     * @param o kiyaslanacak nesne
     * @return true eger esitse.
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof LetterSequence)) return false;

        final LetterSequence harfDizisi = (LetterSequence) o;
        if (boy != harfDizisi.boy) return false;
        for (int i = 0; i < boy; i++) {
            if (dizi[i].charValue() != harfDizisi.dizi[i].charValue())
                return false;
        }
        return true;
    }

    public boolean startsWith(LetterSequence ls) {
        if (ls.boy > boy)
            return false;
        for (int i = 0; i < ls.dizi.length; i++) {
            if (!dizi[i].equals(ls.dizi[i]))
                return false;
        }
        return true;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * istenen noktadaki harfi giris parametresi olan Letter ile degistirir.
     *
     * @param index degistirilecek indeks.
     * @param harf  kullanilacak harf
     * @throws ArrayIndexOutOfBoundsException
     */
    public void harfDegistir(int index, Letter harf) {
        if (index < 0 || index >= boy)
            throw new ArrayIndexOutOfBoundsException("indeks degeri:" + index + " fakat harf dizi boyu:" + boy);
        dizi[index] = harf;
    }

    /**
     * son harfi siler. eger harf yoksa hicbir etki yapmaz.
     */
    public void sonHarfSil() {
        if (boy > 0)
            boy--;
    }

    /**
     * verilen pozisyondaki harfi siler. kelimenin kalan kismi otelenir.
     * eger verilen pozisyon yanlis ise  ArrayIndexOutOfBoundsException firlatir.
     * <p/>
     * "kedi" icin (2) "kei" olusturur.
     *
     * @param index silinecek harf pozisyonu
     * @return dizinin kendisi.
     * @throws ArrayIndexOutOfBoundsException
     */
    public LetterSequence harfSil(int index) {
        if (index < 0 || index >= boy)
            throw new ArrayIndexOutOfBoundsException("indeks degeri:" + index + " fakat harf dizi boyu:" + boy);
        if (index == boy - 1) {
            boy--;
        } else {
            System.arraycopy(dizi, index + 1, dizi, index, boy - index - 1);
            boy--;
        }
        return this;
    }

    /**
     * verilen pozisyondan belli miktar harfi siler.
     * "kediler" icin (2,2) "keler" olusturur.
     *
     * @param index      silinmeye baslanacak pozisyon
     * @param harfSayisi silinecek harf miktari
     * @return dizinin kendisi
     */
    public LetterSequence harfSil(int index, int harfSayisi) {
        if (index < 0 || index >= boy)
            throw new ArrayIndexOutOfBoundsException("indeks degeri:" + index + " fakat harf dizi boyu:" + boy);
        if (index + harfSayisi > boy)
            harfSayisi = boy - index;
        for (int i = index + harfSayisi; i < boy; i++)
            dizi[i - harfSayisi] = dizi[i];
        boy -= harfSayisi;
        return this;
    }

    /**
     * ilk harfi dondurur. eger harf yoksa UNDEFINED_LETTER doner.
     *
     * @return ilk Letter.
     */
    public Letter ilkHarf() {
        if (boy == 0) return Alphabet.UNDEFINED_LETTER;
        else
            return dizi[0];
    }

    /**
     * "index" numarali harften itibaren siler.
     * "kedi" icin (1) "k" olusturur.
     *
     * @param index kirpilmaya baslanacak pozisyon
     */
    public void kirp(int index) {
        if (index <= boy && index >= 0)
            boy = index;
    }

    /**
     * sadece belirli bir bolumunu String'e donusturur.
     *
     * @param index String'e donusum baslangic noktasi.
     * @return olusan String.
     */
    public String toString(int index) {
        if (index < 0 || index >= boy) return "";
        StringBuilder s = new StringBuilder(boy - index);
        for (int i = index; i < boy; i++)
            s.append(charAt(i));
        return s.toString();
    }

    @Override
    public String toString() {
        return new StringBuilder(this).toString();
    }

    /**
     * Compare to metodu siralama icin kiyaslama yapar. Kiyaslama oncelikle harflerin alfabetik sirasina
     * daha sonra dizilerin boyutuna gore yapilir.
     *
     * @param o kiyaslanacak dizi.
     * @return 'kedi'.compareTo('kedi') -> 0
     *         'kedi'.compareTo('ke')  -> 2 (boy farki)
     *         'kedi'.compareTo('kedm') -> -4 (i->m alfabetik sira farki)
     *         'kedi'.compareTo(null) -> 1
     */
    public int compareTo(LetterSequence o) {
        if (o == null)
            return 1;

        if (this == o)
            return 0;

        int l = o.boy;
        int n = Math.min(boy, l);

        for (int i = 0; i < n; i++) {
            if (!dizi[i].equals(o.dizi[i]))
                return dizi[i].alphabeticIndex() - o.dizi[i].alphabeticIndex();
        }
        return boy - l;
    }

    /* ------------------------- ozel metodlar ------------------------------- */

    /**
     * Genellikle kelimedeki hece sayisini bulmak icin kullanilir.
     *
     * @return inte, sesli harf sayisi.
     */
    public int sesliSayisi() {
        int sonuc = 0;
        for (int i = 0; i < boy; i++) {
            if (dizi[i].isVowel())
                sonuc++;
        }
        return sonuc;
    }

    /**
     * @return hepsi buyuk harf ise true, boy=0 dahil.
     */
    public boolean hepsiBuyukHarfmi() {
        for (int i = 0; i < boy; i++) {
            if (Character.isLowerCase(dizi[i].charValue()))
                return false;
        }
        return true;
    }

    //--------- asagidaki metodlar CharSequence arayuzu icin hazirlandi. -----

    public int length() {
        return boy;
    }

    public char charAt(int index) {
        if (index < 0 || index >= boy)
            throw new StringIndexOutOfBoundsException(index);
        return dizi[index].charValue();
    }

    public LetterSequence subSequence(int start, int end) {
        return araDizi(start, end);
    }

}
