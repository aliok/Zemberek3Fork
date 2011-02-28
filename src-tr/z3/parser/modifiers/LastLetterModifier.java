package z3.parser.modifiers;

import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;

/**
 * modifies the last letter.
 */
public class LastLetterModifier implements SequenceModifier {

    final TurkicLetter letterToReplace;

    public LastLetterModifier(TurkicLetter letter) {
        letterToReplace = letter;
    }

    public SequenceModifier modify(TurkicSeq seq) {
        seq.changeLetter(seq.length()-1, letterToReplace);
        return this;
    }
}