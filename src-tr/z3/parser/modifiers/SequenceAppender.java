package z3.parser.modifiers;

import zemberek3.structure.TurkicSeq;


public class SequenceAppender implements SequenceModifier {

    final TurkicSeq toBeAppended;

    public SequenceAppender(TurkicSeq toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public SequenceModifier modify(TurkicSeq sequence) {
        sequence.append(toBeAppended);
        return this;
    }
}
