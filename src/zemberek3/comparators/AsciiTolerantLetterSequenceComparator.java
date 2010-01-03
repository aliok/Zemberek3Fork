package zemberek3.comparators;

import zemberek3.structure.TurkicAlphabet;
import zemberek3.structure.TurkicLetterSequence;

public class AsciiTolerantLetterSequenceComparator
        implements CharSequenceComparator<TurkicLetterSequence> {

    TurkicAlphabet alphabet;

    public AsciiTolerantLetterSequenceComparator(TurkicAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    public boolean isEqual(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return false;
    }

    public boolean startsWith(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return false;
    }
}
