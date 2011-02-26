package z3.comparators;

import zemberek3.structure.TurkicSeq;

public class SimilarLetterSequenceComparator 
        implements CharSequenceComparator<TurkicSeq> {

    final SimilarityChecker similarityChecker;

    public SimilarLetterSequenceComparator(SimilarityChecker similarityChecker) {
        this.similarityChecker = similarityChecker;
    }

    public boolean isEqual(TurkicSeq t1, TurkicSeq t2) {
        return similarityChecker.isSimilar(t1, t2);
    }

    public boolean startsWith(TurkicSeq t1, TurkicSeq t2) {
        return false;
    }
}
