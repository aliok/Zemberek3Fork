package zemberek3.comparators;

import zemberek3.structure.TurkicLetterSequence;

public class SimilarLetterSequenceComparator 
        implements CharSequenceComparator<TurkicLetterSequence> {

    final SimilarityChecker similarityChecker;

    public SimilarLetterSequenceComparator(SimilarityChecker similarityChecker) {
        this.similarityChecker = similarityChecker;
    }

    public boolean isEqual(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return similarityChecker.isSimilar(t1, t2);
    }

    public boolean startsWith(TurkicLetterSequence t1, TurkicLetterSequence t2) {
        return false;
    }
}
