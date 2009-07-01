package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;

public class StartWithVowel implements SequenceRule {

    public boolean check(LetterSequence sequence) {
        return sequence.firstLetter().isVowel();
    }
}
