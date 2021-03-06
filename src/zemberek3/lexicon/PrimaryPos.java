package zemberek3.lexicon;

import zemberek3.structure.StringEnum;
import zemberek3.structure.StringEnumMap;

public enum PrimaryPos implements StringEnum<PrimaryPos> {
    Noun("Noun"),
    Adjective("Adj"),
    Adverb("Adv"),
    Conjunction("Conj"),
    Interjection("Interj"),
    Verb("Verb"),
    Pronoun("Pron"),
    Numeral("Num"),
    Determiner("Det"),
    Particle("Part");

    public String shortForm;

    PrimaryPos(String shortForm) {
        this.shortForm = shortForm;
    }

    private static StringEnumMap<PrimaryPos> shortFormToPosMap = StringEnumMap.get(PrimaryPos.class);

    public static StringEnumMap<PrimaryPos> converter() {
        return shortFormToPosMap;
    }

    public String getStringForm() {
        return shortForm;
    }
}
