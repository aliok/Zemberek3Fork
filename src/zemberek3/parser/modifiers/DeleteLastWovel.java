package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicLetterSequence;

/**
 * deletes the last wovel form the letter sequence. if there is no
 */
public class DeleteLastWovel implements SequenceModifier {

    /**
     * Deletes the last wovel from the sequence. if there is no vowel, no modifications are made.
     *
     * @param letterSequence input sequence.
     * @return this
     */
    public SequenceModifier modify(TurkicLetterSequence letterSequence) {
        for (int i = letterSequence.length() - 1; i >= 0; i--) {
            if (letterSequence.getLetter(i).isVowel()) {
                letterSequence.delete(i);
                break;
            }
        }
        return this;
    }
}
