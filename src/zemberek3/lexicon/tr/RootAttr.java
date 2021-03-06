package zemberek3.lexicon.tr;

import zemberek3.structure.BitEnumMap;
import zemberek3.structure.IndexedEnum;
import zemberek3.structure.StringEnum;
import zemberek3.structure.StringEnumMap;

/**
 * These represents attributes of morphemes.
 */
public enum RootAttr implements StringEnum, IndexedEnum {
    Voicing,
    VoicingOpt,
    NoVoicing,
    InverseHarmony,
    LastVowelDrop,
    Doubling,
    StemChange,
    NounConsInsert,
    NounConsInsert_n,
    NoQuote,
    Plural,
    ProgressiveVowelDrop,
    Aorist_I,
    Aorist_A,
    NonTransitive,
    Passive_In,
    CompoundP3sg,
    Compound,
    Causative_t,
    Reflexive,
    Reciprocal,
    NoSuffix;

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
