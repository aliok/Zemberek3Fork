package zemberek3.structure;

/**
 * An Alphabet is a collection of symbols used for written language.
 */
public interface Alphabet<T extends Letter> {

    /**
     * returns the Letter representation of a char. if there is no Letter for the char, it throws IllegalCharException
     * @param c input character
     * @return Letter representation.
     */
    T getLetter(char c);

    boolean englishEqual(T l1, T l2);

    boolean isValid(char c);

    int getAphabeticIndex(char c);

    char getCharByAlphabeticIndex(int index);
}
