package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;

public class LastLetterModifier implements SequenceModifier {

    public LastLetterModifier() {
    }

    public SequenceModifier modify(TurkicLetterSequence letterSequence) {
        return this;
    }
}