package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;

public class StartsWithVowel implements SequenceRule {

    public boolean check(TurkicLetterSequence sequence) {
        return sequence.firstLetter().isVowel();
    }
}
