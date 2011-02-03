package z3.comparators;

public interface CharSequenceComparator<T extends CharSequence> {

    /**
     * checks if t1 and t2 are equals. Equality criteria will be defined by the implementor.
     * @param t1 first CharSequence
     * @param t2 second Char sequence
     * @return true if sequences are equal.
     */
    boolean isEqual(T t1, T t2);

    /**
     * if t1 has t2 as prefix.
     *
     * @param t1, Letter sequence that has t2 as a prefix
     * @param t2, letter sequence, t1 will will searched as a prefix wihin
     * @return if t2 starts with t1, true, else false.
     */
    boolean startsWith(T t1, T t2);
}
