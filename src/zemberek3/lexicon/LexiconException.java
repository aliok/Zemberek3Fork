package zemberek3.lexicon;

public class LexiconException extends RuntimeException {
    public LexiconException(String message) {
        super(message);
    }

    public LexiconException(String message, Throwable cause) {
        super(message, cause);
    }
}
