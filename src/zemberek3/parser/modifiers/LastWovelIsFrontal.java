package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;

public class LastWovelIsFrontal implements SequenceRule{
    public boolean check(TurkicLetterSequence sequence) {
        return sequence.lastVowel().isFrontalVowel();
    }
}
