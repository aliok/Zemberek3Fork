package zemberek3.lexicon;

/**
 * These represents attributes of morphemes.
 */
public enum MorphemicAttribute implements StringEnum {
    Voicing,
    NoVoicing,
    InverseHarmony,
    LastVowelDrop,
    Doubling,
    StemChange,
    ProgressiveVowelDrop,
    Aorist_I,
    Aorist_A,
    NonTransitive;

    private static StringEnumMap<MorphemicAttribute> shortFormToPosMap =  StringEnumMap.get(MorphemicAttribute.class);

    public static StringEnumMap<MorphemicAttribute> converter() {
        return shortFormToPosMap;
    }

    public String getStringForm() {
        return this.name();
    }
}
