package zemberek3.lexicon;

/**
 * These represents attributes of morphemes.
 */
public enum RootAttr implements StringEnum {
    Voicing,
    NoVoicing,
    InverseHarmony,
    LastVowelDrop,
    Doubling,
    StemChange,
    ProgressiveVowelDrop,
    Aorist_I,
    Aorist_A,
    NonTransitive,
    CompoundP3sg;

    private static StringEnumMap<RootAttr> shortFormToPosMap = StringEnumMap.get(RootAttr.class);

    public static StringEnumMap<RootAttr> converter() {
        return shortFormToPosMap;
    }

    public String getStringForm() {
        return this.name();
    }
}
