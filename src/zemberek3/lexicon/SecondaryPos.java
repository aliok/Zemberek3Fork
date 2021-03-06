package zemberek3.lexicon;

import zemberek3.structure.StringEnum;
import zemberek3.structure.StringEnumMap;

public enum SecondaryPos implements StringEnum<SecondaryPos> {
    Duplicator("Dup"),
    PostPositive("Postp"),
    Question("Ques"),
    Demonstrative("Demons"),
    Time("Time"),
    ProperNoun("Prop"),
    None("None");

    public String shortForm;

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
