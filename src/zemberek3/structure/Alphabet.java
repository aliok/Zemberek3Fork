package zemberek3.structure;

public interface Alphabet<T extends Letter> {

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
    };

    T getLetter(char c);

    boolean equalsAscii(T l1, T l2);
}
