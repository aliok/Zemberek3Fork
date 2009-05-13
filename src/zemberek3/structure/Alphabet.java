package zemberek3.structure;

public interface Alphabet {

    public static final Letter UNDEFINED_LETTER = new Letter() {

        public char charValue() {
            return 0;
        }

        public int alphabeticIndex() {
            return -1;
        }

        public boolean isVowel() {
            return false;
        }

        public boolean isCapital() {
            return false;
        }
    };

    Letter getLetter(char c);
}
