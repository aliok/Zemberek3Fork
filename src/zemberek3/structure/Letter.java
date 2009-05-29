package zemberek3.structure;

/**
 * A <code>Letter</code> is a character of an alphabet. It does however contains some linguistic properties such as if
 * it is a vowel. 
 */
public interface Letter {

    public static final Letter UNDEFINED = new Letter() {

        public char charValue() {
            return 0;
        }

        public int alphabeticIndex() {
            return -1;
        }

        public boolean isVowel() {
            return false;
        }
    };
    
    char charValue();

    int alphabeticIndex();

    boolean isVowel();

}
