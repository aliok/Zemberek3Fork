package zemberek3.cache;

/**
 * an interface for cache implementations.
 */
public interface CharSequenceCache<T extends CharSequence> {

    /**
     * checks if an input charsequence exists in CharSequenceCache
     * @param cs input to check in cache
     * @return cache
     */
    boolean check(T cs);

    /**
     * empties the cache.
     */
    void flush();
}
