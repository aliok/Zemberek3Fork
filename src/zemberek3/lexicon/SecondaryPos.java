package zemberek3.lexicon;

public enum SecondaryPos implements StringEnum<SecondaryPos> {
    Duplicator("Dup"),
    Determiner("Det"),
    PostPositive("Post"),
    Question("Ques"),
    Demonstrative("Demons"),
    Numeral("Num"),
    Time("Time"),
    ProperNoun("Prop"),
    None("None");

    String shortForm;

    SecondaryPos(String shortForm) {
        this.shortForm = shortForm;
    }

    private static StringEnumMap<SecondaryPos> shortFormToPosMap = StringEnumMap.get(SecondaryPos.class);

    public static StringEnumMap<SecondaryPos> converter() {
        return shortFormToPosMap;
    }

    public String getStringForm() {
        return shortForm;
    }
}
