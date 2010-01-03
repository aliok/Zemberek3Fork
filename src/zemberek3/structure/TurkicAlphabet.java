package zemberek3.structure;

/**
 * An TurkicAlphabet is a collection of symbols used for written language.
 */
public interface TurkicAlphabet {

    /**
     * returns the Letter representation of a char. if there is no Letter for the char, it throws IllegalCharException
     * @param c input character
     * @return Letter representation.
     */
    TurkicLetter getLetter(char c);

    boolean englishEqual(TurkicLetter l1, TurkicLetter  l2);

    boolean isValid(char c);

    int getAphabeticIndex(char c);

    char getCharByAlphabeticIndex(int index);
}
