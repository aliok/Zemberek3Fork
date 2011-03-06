package zemberek3.lexicon;

public enum PhonAttr implements BitEnum {
    LastVowelFrontal,
    LastVowelRounded,
    LastLetterVoicelessStop,
    LastLetterVowel,
    HasNoVowel,
    FirstVowelFrontal,
    FirstVowelRounded,
    FirstLetterVowel,
    FirstLetterConsonant;

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
