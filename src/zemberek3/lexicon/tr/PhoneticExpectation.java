package zemberek3.lexicon.tr;

import zemberek3.structure.IndexedEnum;

public enum PhoneticExpectation implements IndexedEnum {
    VowelStart,
    ConsonantStart;

    int index;

    PhoneticExpectation() {
        this.index = this.ordinal();
    }

    public int getBitIndex() {
        return index;
    }

}
