package zemberek3.comparators;

import zemberek3.structure.LetterSequence;

/**
 * A Similarity checker based on Damareu-levensthtein string distance algorithm.
 */
public class EditDistanceSimilarityChecker implements SimilarityChecker<LetterSequence> {

    final int similarityTreshold;

    public EditDistanceSimilarityChecker(int similarityTreshold) {
        if (similarityTreshold < 0)
            throw new IllegalArgumentException("Similarity threshold value cannot be negative. current:" + similarityTreshold);
        this.similarityTreshold = similarityTreshold;
    }

    public boolean similar(LetterSequence t1, LetterSequence t2) {
        
        return false;
    }
}
