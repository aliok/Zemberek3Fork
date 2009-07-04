package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;


public class SequenceAppender implements SequenceModifier<LetterSequence> {

    final LetterSequence toBeAppended;

    public SequenceAppender(LetterSequence toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public SequenceModifier<LetterSequence> modify(LetterSequence sequence) {
        sequence.append(toBeAppended);
        return this;
    }
}
