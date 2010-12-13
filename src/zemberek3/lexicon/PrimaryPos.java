package zemberek3.lexicon;

public enum PrimaryPos implements StringEnum<PrimaryPos> {
    Noun("Noun"),
    Adjective("Adj"),
    Adverb("Adv"),
    Conjunction("Conj"),
    Interjection("Interj"),
    Verb("Verb"),
    Pronoun("Pron"),
    Particle("Part");

    String shortForm;

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
