package zemberek3.comparators;

/**
 * this is used for checking if two charsequence implementor is similar enough. the desicion is up to the implementor.
 */
public interface SimilarityChecker<T extends CharSequence> {
    boolean similar(T t1, T t2);
}
