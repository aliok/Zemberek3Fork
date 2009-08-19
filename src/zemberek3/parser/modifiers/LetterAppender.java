package zemberek3.parser.modifiers;

import zemberek3.structure.Letter;
import zemberek3.structure.LetterSequence;

/**
 * Appends a predefined letter to a sequence.
 */
public class LetterAppender implements SequenceModifier<LetterSequence> {

    private final Letter toBeAppended;

    public LetterAppender(Letter toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public SequenceModifier<LetterSequence> modify(LetterSequence letterSequence) {
        letterSequence.append(toBeAppended);
        return this;
    }
}
