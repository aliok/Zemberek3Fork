package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;

public class LastLetterIsWovel implements SequenceRule {
    public boolean check(LetterSequence sequence) {
        return sequence.lastLetter().isVowel();
    }

    public static void main(String[] args) {
        System.out.println("gdgg");
    }
}