package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;

public class StartWithVowel implements SequenceRule {

    public boolean check(TurkicLetterSequence sequence) {
        return sequence.firstLetter().isVowel();
    }
}
