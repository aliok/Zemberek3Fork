package zemberek3.lexicon;

/**
 * These represents attributes of morphemes.
 */
public enum MorphAttr implements StringEnum {
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
    CompoundP3sg,

    LastVowelFrontal,
    LastVowelRounded,
    LastLetterVoicelessStop,
    LastLetterVowel,

    ExpectsVowel,
    ExpectsConsonant;


    private static StringEnumMap<MorphAttr> shortFormToPosMap = StringEnumMap.get(MorphAttr.class);

    public static StringEnumMap<MorphAttr> converter() {
        return shortFormToPosMap;
    }

    public String getStringForm() {
        return this.name();
    }
}
