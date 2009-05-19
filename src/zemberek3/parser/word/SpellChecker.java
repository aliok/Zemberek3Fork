package zemberek3.parser.word;

/**
 * An interface for checking if a charsequence is spelled correctly.
 */
public interface SpellChecker<T extends CharSequence> {

    /**
     * if a word is spelled correctly according to it's original writing style.
     * Such that, if a word must start with a capital letter, this will result false if given input
     * starts with a small case letter.
     * @param input input charsequence.
     * @return true if input is written correctly.
     */
    boolean check(T input);

    /**
     * TODO: leave this to the implementation.
     * if a word is spelled correctly even it does not contain formatting rules related with the language.
     * such that, if a word must be written "Ankara'ya" but input word is "ankaraya" method will return true.
     * @param input input word, may not be formatted correctly.
     * @return true if written is written correctly regardless od the format.
     */
    boolean checkUnformatted(T input);
}
