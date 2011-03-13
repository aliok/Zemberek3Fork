package z3.parser.modifiers;

import zemberek3.structure.TurkicSeq;

public class LastWovelIsFrontal implements SequenceRule{
    public boolean check(TurkicSeq sequence) {
        return sequence.lastVowel().isFrontal();
    }
}
