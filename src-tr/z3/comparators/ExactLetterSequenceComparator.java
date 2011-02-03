package z3.comparators;

import zemberek3.structure.TurkicLetterSequence;

public class ExactLetterSequenceComparator implements CharSequenceComparator<TurkicLetterSequence> {

    public boolean isEqual(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return t1.equals(t2);
    }

    public boolean startsWith(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return t1.startsWith(t2);
    }
}
