package zemberek3.comparators;

/**
 * This is used for checking if two charsequences are similar. Similarity decision is up to the implementor.
 */
public interface SimilarityChecker {
    boolean isSimilar(CharSequence t1, CharSequence t2);
}
