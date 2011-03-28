package zemberek3.lexicon;

import zemberek3.structure.IndexedEnum;
import zemberek3.structure.BitEnumMap;

public enum PhonAttr implements IndexedEnum {
    LastVowelFrontal,
    LastVowelBack,
    LastVowelRounded,
    LastVowelUnrounded,
    LastLetterVoicelessStop,
    LastLetterNotVoicelessStop,
    LastLetterVowel,
    LastLetterConsonant,
    HasNoVowel;
    int index;

    PhonAttr() {
        this.index = this.ordinal();
    }

    private static BitEnumMap<PhonAttr> indexToEnumMap = BitEnumMap.get(PhonAttr.class);

    public static BitEnumMap<PhonAttr> converter() {
        return indexToEnumMap;
    }

    public int getBitIndex() {
        return index;
    }

}
