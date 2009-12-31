package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;


public class SequenceAppender implements SequenceModifier {

    final LetterSequence toBeAppended;

    public SequenceAppender(LetterSequence toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public SequenceModifier modify(LetterSequence sequence) {
        sequence.append(toBeAppended);
        return this;
    }
}
