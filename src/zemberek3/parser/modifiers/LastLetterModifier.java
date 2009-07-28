package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;

public class LastLetterModifier implements SequenceModifier<LetterSequence> {

    public LastLetterModifier() {
    }

    public SequenceModifier<LetterSequence> modify(LetterSequence letterSequence) {
        return this;
    }
}