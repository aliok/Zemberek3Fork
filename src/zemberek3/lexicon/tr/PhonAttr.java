package zemberek3.lexicon.tr;

import zemberek3.structure.BitEnumMap;
import zemberek3.structure.IndexedEnum;

public enum PhonAttr implements IndexedEnum {
    LastVowelFrontal("LVF"),
    LastVowelBack("LVB"),
    LastVowelRounded("LVR"),
    LastVowelUnrounded("LVuR"),
    LastLetterVoiceless("LLVless"),
    LastLetterNotVoiceless("LLNotVless"),
    LastLetterVoicelessStop("LLStop"),
    LastLetterVowel("LLV"),
    LastLetterConsonant("LLC"),
    FirstLetterVowel("FLV"),
    FirstLetterConsonant("FLC"),
    HasNoVowel("NoVow");

    int index;
    String shortForm;

    private static BitEnumMap<PhonAttr> indexToEnumMap = BitEnumMap.get(PhonAttr.class);

    PhonAttr(String shortForm) {
        this.shortForm = shortForm;
        this.index = this.ordinal();
    }

    public static BitEnumMap<PhonAttr> converter() {
        return indexToEnumMap;
    }

    public int getBitIndex() {
        return index;
    }

    public String getShortForm() {
        return shortForm;
    }
}
