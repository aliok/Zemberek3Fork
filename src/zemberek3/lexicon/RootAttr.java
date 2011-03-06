package zemberek3.lexicon;

import zemberek3.structure.StringEnum;
import zemberek3.structure.StringEnumMap;

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
