package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;

public class LastLetterIsVoiceless implements SequenceRule{
    public boolean check(TurkicLetterSequence sequence) {
        return sequence.lastLetter().voiceless;
    }
}
