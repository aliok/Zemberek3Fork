/*
 * Lisans bilgisi icin lutfen proje ana dizinindeki zemberek2-lisans.txt dosyasini okuyunuz.
 */

package net.zemberek.tr.yapi.kok;

import net.zemberek.yapi.kok.KokOzelDurumTipi;

import static net.zemberek.tr.yapi.ek.TurkceEkAdlari.*;

/**
 * User: ahmet
 * Date: Sep 9, 2006
 */
public enum TurkceKokOzelDurumTipleri implements KokOzelDurumTipi {

    SESSIZ_YUMUSAMASI("Vo"),

    SESSIZ_YUMUSAMASI_NK("Vo2"),

    ISIM_SESLI_DUSMESI("VowDrop",
            Dat,
            Acc,
            P1sg,
            P2sg,
            P2pl,
            P3sg,
            P1pl,
            Gen),

    CIFTLEME("ConsDoubling"),

    FIIL_ARA_SESLI_DUSMESI("VowDropPass", FIIL_EDILGEN_IL),

    KUCULTME("ConsDrop", Dim),

    TEKIL_KISI_BOZULMASI("PronDeformation", Dat),

    FIIL_KOK_BOZULMASI("VerbDeformation",
            Prog,
            Fut,
            FIIL_DONUSUM_ECEK,
            FIIL_DONUSUM_EN,
            FIIL_DONUSUM_IS,
            FIIL_ISTEK_E,
            FIIL_SUREKLILIK_EREK,
            FIIL_SURERLIK_EDUR,
            FIIL_SURERLIK_EGEL,
            FIIL_SURERLIK_EKAL,
            FIIL_YAKLASMA_AYAZ,
            FIIL_YETENEK_EBIL,
            FIIL_BERABERLIK_IS,
            FIIL_EMIR_SIZ_IN,
            FIIL_ZAMAN_INCE,
            FIIL_EMIR_SIZRESMI_INIZ,
            FIIL_YETERSIZLIK_E,
            FIIL_DONUSUM_ESI,
            FIIL_IMSI_IP
    ),

    TERS_SESLI_EK("Frontal"),

    SIMDIKI_ZAMAN("SDK_ZAMAN", Prog),

    ISIM_SON_SESLI_DUSMESI("DUS_SON",
            Loc,
            Abl,
            Plural),

    ZAMIR_SESLI_OZEL("ZAMIR_SESLI_OZEL",
            Dat,
            Loc,
            Abl,
            Acc,
            Gen,
            Without,
            Plural,
            By,
            Ness,
            Dim2),

    /*
     * bazi kokler aslinda saf kok degil, icinde isim tamlamasi iceriyor
     * mesela, zeytinyagi, acemborusu gibi.
     * bu koklere bazi ekler eklendiginde kok bozuluyor
     * mesela: acemborusu -> acemborulari seklinde.
     * bu kokler sistemde ozel sekilde saklaniyor.
     * acemborusu -> acemboru IS_TAM seklinde tanimlanmistir.
     * ayni sekilde zeytinyag IS_TAM gibi
     */    
    ISIM_TAMLAMASI("Compound",
            P1sg,
            P2sg,
            P3sg,
            P1pl,
            P2pl,
            P3pl,
            Gen,
            MadeFor,
            With,
            Plural,
            Without,
            By,
            Ness,
            Dim2,
            ISIM_ILGI_CI),

    YALIN("YAL"),
    GENIS_ZAMAN("Aor2"),
    KAYNASTIRMA_N("ConsInsert_N"),
    KESMESIZ("NoQuote"),
    TIRE("TIRE"),
    ZAMIR_IM("ZAMIR_IM"),
    ZAMIR_IN("ZAMIR_IN"),
    KISALTMA_SON_SESLI("SON"),
    KISALTMA_SON_SESSIZ("SON_N"),
    SU_OZEL_DURUMU("ConsInsert");

    private String kisaAd;
    private String[] ekAdlari = new String[0];

    TurkceKokOzelDurumTipleri(String kisaAd, String... ekAdlari) {
        this.kisaAd = kisaAd;
        if (this.ekAdlari != null) {
            this.ekAdlari = ekAdlari;
        }
    }

    public String ad() {
        return this.name();
    }

    public String kisaAd() {
        return kisaAd;
    }

    public int indeks() {
        return ordinal();
    }

    public String[] ekAdlari() {
        return ekAdlari;
    }
}
