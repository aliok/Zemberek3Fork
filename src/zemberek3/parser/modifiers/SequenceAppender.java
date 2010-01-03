package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;


public class SequenceAppender implements SequenceModifier {

    final TurkicLetterSequence toBeAppended;

    public SequenceAppender(TurkicLetterSequence toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public SequenceModifier modify(TurkicLetterSequence sequence) {
        sequence.append(toBeAppended);
        return this;
    }
}
