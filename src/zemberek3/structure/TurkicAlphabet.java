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

    public TurkicLetter getLetter(int alphabeticIndex);

    boolean isValid(char c);

    char getCharByAlphabeticIndex(int alphabeticIndex);

    int getAphabeticIndex(char c);
    
    byte[] toIndexes(String s);

    TurkicLetter getAsciiEquivalentLetter(TurkicLetter letter);

    boolean asciiEqual(char c1, char c2);

    public char getAsciiEquivalentChar(char c);

    public TurkicLetter getAsciEquivalentLetter(char c);
}
