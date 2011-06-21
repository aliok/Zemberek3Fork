package zemberek3.lexicon.graph;

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
