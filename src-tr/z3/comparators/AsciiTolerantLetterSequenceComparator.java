package z3.comparators;

import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.TurkishAlphabet;

public class AsciiTolerantLetterSequenceComparator
        implements CharSequenceComparator<TurkicLetterSequence> {

    TurkishAlphabet alphabet;

    public AsciiTolerantLetterSequenceComparator(TurkishAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    public boolean isEqual(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return false;
    }

    public boolean startsWith(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return false;
    }
}
