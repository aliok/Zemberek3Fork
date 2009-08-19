package zemberek3.comparators;

import zemberek3.structure.LetterSequence;

public class EditDistanceLetterSequenceComparator 
        implements CharSequenceComparator<LetterSequence> {

    final SimilarityChecker similarityChecker;

    public EditDistanceLetterSequenceComparator(SimilarityChecker similarityChecker) {
        this.similarityChecker = similarityChecker;
    }

    public boolean isEqual(LetterSequence t1, LetterSequence t2) {
        return similarityChecker.isSimilar(t1, t2);
    }

    public boolean startsWith(LetterSequence t1, LetterSequence t2) {
        return false;
    }
}
