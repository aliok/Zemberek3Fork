package zemberek3.comparators;

import zemberek3.structure.LetterSequence;

public class EditDistanceLetterSequenceComparator 
        implements CharSequenceComparator<LetterSequence> {

    final SimilarityChecker<LetterSequence> similarityChecker;

    public EditDistanceLetterSequenceComparator(SimilarityChecker<LetterSequence> similarityChecker) {
        this.similarityChecker = similarityChecker;
    }

    public boolean isEqual(LetterSequence t1, LetterSequence t2) {
        return similarityChecker.similar(t1, t2);
    }

    public boolean startsWith(LetterSequence t1, LetterSequence t2) {
        return false;
    }
}
