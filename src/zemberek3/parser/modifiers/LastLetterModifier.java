package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;

public class LastLetterModifier implements SequenceModifier {

    public LastLetterModifier() {
    }

    public SequenceModifier modify(LetterSequence letterSequence) {
        return this;
    }
}