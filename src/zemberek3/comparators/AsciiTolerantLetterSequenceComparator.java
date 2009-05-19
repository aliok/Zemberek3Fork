package zemberek3.comparators;

import zemberek3.structure.Alphabet;
import zemberek3.structure.LetterSequence;

public class AsciiTolerantLetterSequenceComparator
        implements CharSequenceComparator<LetterSequence> {

    Alphabet alphabet;

    public AsciiTolerantLetterSequenceComparator(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public boolean isEqual(LetterSequence t1, LetterSequence t2) {
        return false;
    }

    public boolean startsWith(LetterSequence t1, LetterSequence t2) {
        return false;
    }
}
