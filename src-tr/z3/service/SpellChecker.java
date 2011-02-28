package z3.service;

/**
 * An interface for checking if a charsequence is spelled correctly.
 */
public interface SpellChecker<T extends CharSequence> {

    /**
     * if a word is spelled correctly. The decision of if something is spelled correctly is determined by the implementor.
     * @param input input charsequence.
     * @return true if input is written correctly.
     */
    boolean check(T input);

}
