package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicSeq;

public class LastLetterIsVoiceless implements SequenceRule{
    public boolean check(TurkicSeq sequence) {
        return sequence.lastLetter().voiceless;
    }
}
