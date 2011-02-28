package z3.parser.modifiers;

import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;

/**
 * Appends a predefined letter to a sequence.
 */
public class LetterAppender implements SequenceModifier {

    private final TurkicLetter toBeAppended;

    public LetterAppender(TurkicLetter toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public SequenceModifier modify(TurkicSeq seq) {
        seq.append(toBeAppended);
        return this;
    }
}
