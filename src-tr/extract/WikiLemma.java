package extract;

import zemberek3.structure.POS;

import java.util.HashMap;
import java.util.Map;

public class WikiLemma {
    POS pos;
    String lemma;
    String wikiType;

    static Map<String, POS> posMap = new HashMap<String, POS>();

    static {
        posMap.put("Bağlaç", POS.CONJUNCTION);
        posMap.put("Fiil", POS.VERB);
        posMap.put("Eylem", POS.VERB);
        posMap.put("Sıfat", POS.ADJECTIVE);
        posMap.put("Zamir", POS.PRONOUN);
        posMap.put("Ad", POS.NOUN);
        posMap.put("İsim", POS.NOUN);
        posMap.put("İlgeç", POS.PARTICLE);
        posMap.put("Edat", POS.PARTICLE);
        posMap.put("Zarf", POS.ADVERB);
        posMap.put("Ünlem", POS.INTERJECTION);
    }

    WikiLemma(String wikiType, String lemma) {
        if (posMap.containsKey(wikiType)) {
            this.pos = posMap.get(wikiType);
        } else
            this.pos = POS.UNKNOWN;
        this.lemma = lemma;
        this.wikiType = wikiType;
    }

    public String toString() {
        return lemma + " : " + wikiType;
    }
}
