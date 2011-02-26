package z3.comparators;

import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

public class AsciiTolerantLetterSequenceComparator
        implements CharSequenceComparator<TurkicSeq> {

    TurkishAlphabet alphabet;

    public AsciiTolerantLetterSequenceComparator(TurkishAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    public boolean isEqual(TurkicSeq t1, TurkicSeq t2) {
        return false;
    }

    public boolean startsWith(TurkicSeq t1, TurkicSeq t2) {
        return false;
    }
}
