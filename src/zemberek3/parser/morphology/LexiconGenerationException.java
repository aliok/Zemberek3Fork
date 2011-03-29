package zemberek3.parser.morphology;

public class LexiconGenerationException extends RuntimeException {
    public LexiconGenerationException(String message) {
        super(message);
    }

    public LexiconGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
