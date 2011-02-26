package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicSeq;

public class LastLetterIsWovel implements SequenceRule {
    public boolean check(TurkicSeq sequence) {
        return sequence.lastLetter().isVowel();
    }
}