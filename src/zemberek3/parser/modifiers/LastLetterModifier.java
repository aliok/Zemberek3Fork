package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicLetterSequence;

/**
 * modifies the last letter.
 */
public class LastLetterModifier implements SequenceModifier {

    final TurkicLetter letterToReplace;

    public LastLetterModifier(TurkicLetter letter) {
        letterToReplace = letter;
    }

    public SequenceModifier modify(TurkicLetterSequence letterSequence) {
        letterSequence.changeLetter(letterSequence.length()-1, letterToReplace);
        return this;
    }
}