package zemberek3.comparators;

import zemberek3.structure.LetterSequence;

public class ExactLetterSequenceComparator implements CharSequenceComparator<LetterSequence> {

    public boolean isEqual(LetterSequence t1, LetterSequence t2) {
        return false;
    }


    public boolean startsWith(LetterSequence t1, LetterSequence t2) {
        return false;
    }
}
