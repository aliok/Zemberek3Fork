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
            ISIM_YONELME_E,
            ISIM_BELIRTME_I,
            ISIM_SAHIPLIK_BEN_IM,
            ISIM_SAHIPLIK_SEN_IN,
            ISIM_SAHIPLIK_SIZ_INIZ,
            ISIM_SAHIPLIK_O_I,
            ISIM_SAHIPLIK_BIZ_IMIZ,
            ISIM_TAMLAMA_IN,
            ISIM_TAMLAMA_I),

    CIFTLEME("ConsDoubling"),

    FIIL_ARA_SESLI_DUSMESI("VowDropPass", FIIL_EDILGEN_IL),

    KUCULTME("ConsDrop", ISIM_KUCULTME_CIK),

    TEKIL_KISI_BOZULMASI("PronDeformation", ISIM_YONELME_E),

    FIIL_KOK_BOZULMASI("VerbDeformation",
            FIIL_SIMDIKIZAMAN_IYOR,
            FIIL_GELECEKZAMAN_ECEK,
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

    SIMDIKI_ZAMAN("SDK_ZAMAN", FIIL_SIMDIKIZAMAN_IYOR),

    ISIM_SON_SESLI_DUSMESI("DUS_SON",
            ISIM_KALMA_DE,
            ISIM_CIKMA_DEN,
            ISIM_COGUL_LER),

    ZAMIR_SESLI_OZEL("ZAMIR_SESLI_OZEL",
            ISIM_YONELME_E,
            ISIM_KALMA_DE,
            ISIM_CIKMA_DEN,
            ISIM_BELIRTME_I,
            ISIM_TAMLAMA_IN,
            ISIM_YOKLUK_SIZ,
            ISIM_COGUL_LER,
            ISIM_TARAFINDAN_CE,
            ISIM_DURUM_LIK,
            ISIM_KUCULTME_CEGIZ),

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
            ISIM_SAHIPLIK_BEN_IM,
            ISIM_SAHIPLIK_SEN_IN,
            ISIM_SAHIPLIK_O_I,
            ISIM_SAHIPLIK_BIZ_IMIZ,
            ISIM_SAHIPLIK_SIZ_INIZ,
            ISIM_SAHIPLIK_ONLAR_LERI,
            ISIM_TAMLAMA_I,
            ISIM_TAMLAMA_IN,
            ISIM_BULUNMA_LIK,
            ISIM_BULUNMA_LI,
            ISIM_COGUL_LER,
            ISIM_YOKLUK_SIZ,
            ISIM_TARAFINDAN_CE,
            ISIM_DURUM_LIK,
            ISIM_KUCULTME_CEGIZ,
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
