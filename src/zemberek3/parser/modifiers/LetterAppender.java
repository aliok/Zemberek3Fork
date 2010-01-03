package zemberek3.parser.modifiers;

import zemberek3.structure.Letter;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicLetterSequence;

/**
 * Appends a predefined letter to a sequence.
 */
public class LetterAppender implements SequenceModifier {

    private final TurkicLetter toBeAppended;

    public LetterAppender(TurkicLetter toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public SequenceModifier modify(TurkicLetterSequence letterSequence) {
        letterSequence.append(toBeAppended);
        return this;
    }
}
