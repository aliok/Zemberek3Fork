package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;

public class LastLetterIsWovel implements SequenceRule {
    public boolean check(TurkicLetterSequence sequence) {
        return sequence.lastLetter().isVowel();
    }
}