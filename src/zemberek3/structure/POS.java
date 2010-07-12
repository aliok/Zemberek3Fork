package zemberek3.structure;

import java.util.HashMap;
import java.util.Map;

public enum POS {
    UNKNOWN("Unknown", "Unknown"), // Unknown POS
    NOUN("Noun", "IS"), // TR: ad, isim
    VERB("Verb", "FI"), // TR: fiil, eylem
    ADJECTIVE("Adj", "SI"), // TR: sifat
    ADVERB("Adv", "ZARF"), // TR: zarf
    PRONOUN("Pron", "ZAM"), // TR: zamir
    PROPER_NOUN("PropNoun", "OZ"), // TR: Ozel isim
    TIME("Time", "ZA"), // TR: Zaman
    QUESTION("SO", "SO"), // TR: soru
    NUMERAL("Num", "NUM"), // TR: sayi
    INTERJECTION("Interj", "UN"), // TR: unlem
    PARTICLE("Particle", "EDAT"), // TR: edat
    DUPILCATOR("Dup", "YA"), // TR: Yansima, Yanki, Ikileme
    CONJUNCTION("Conj", "BAGLAC"); // TR: baglac

    private String label;
    private String zemberekLabel;

    POS(String label, String zemberekLabel) {
        this.label = label;
        this.zemberekLabel = zemberekLabel;
    }

    private static final Map<String, POS> shortToPOS = new HashMap<String, POS>();
    private static final Map<String, POS> zemberekToPOS = new HashMap<String, POS>();

    static {
        for (POS p : POS.values()) {
            shortToPOS.put(p.label, p);
            zemberekToPOS.put(p.zemberekLabel, p);
        }
    }

    public static POS getPOSByLabel(String label) {
        POS p = shortToPOS.get(label);
        if (p != null)
            return p;
        return POS.UNKNOWN;
    }

}
