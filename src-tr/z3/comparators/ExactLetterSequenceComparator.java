package z3.comparators;

import zemberek3.structure.TurkicSeq;

public class ExactLetterSequenceComparator implements CharSequenceComparator<TurkicSeq> {

    public boolean isEqual(TurkicSeq t1, TurkicSeq t2) {
        return t1.equals(t2);
    }

    public boolean startsWith(TurkicSeq t1, TurkicSeq t2) {
        return t1.startsWith(t2);
    }
}
