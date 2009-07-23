package zemberek3.comparators;

/**
 * this is used for checking if two charsequences are similar enough.
 * the desicion is up to the implementor.
 */
public interface SimilarityChecker {
    boolean similar(CharSequence t1, CharSequence t2);
}
