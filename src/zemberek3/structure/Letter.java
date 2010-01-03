package zemberek3.structure;

/**
 * A <code>Letter</code> is a character of an alphabet. It does however contains some linguistic properties such as if
 * it is a vowel.
 */
public interface Letter {

    char charValue();

    int alphabeticIndex();

    boolean isVowel();

    boolean isConsonant();

}
