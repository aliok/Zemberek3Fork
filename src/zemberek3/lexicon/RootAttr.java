package zemberek3.lexicon;

import zemberek3.structure.IndexedEnum;
import zemberek3.structure.BitEnumMap;
import zemberek3.structure.StringEnum;
import zemberek3.structure.StringEnumMap;

/**
 * These represents attributes of morphemes.
 */
public enum RootAttr implements StringEnum, IndexedEnum {
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

    int index;

    RootAttr() {
        this.index = this.ordinal();
    }

    private static StringEnumMap<RootAttr> shortFormToPosMap = StringEnumMap.get(RootAttr.class);

    public static StringEnumMap<RootAttr> converter() {
        return shortFormToPosMap;
    }

    public String getStringForm() {
        return this.name();
    }

    private static BitEnumMap<RootAttr> indexToEnumMap = BitEnumMap.get(RootAttr.class);

    public static BitEnumMap<RootAttr> indexConverter() {
        return indexToEnumMap;
    }

    public int getBitIndex() {
        return index;
    }

}
