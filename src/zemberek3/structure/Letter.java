package zemberek3.structure;

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
