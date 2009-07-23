package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;

/**
 * deletes the last wovel form the letter sequence. if there is no
 */
public class DeleteLastWovel implements SequenceModifier<LetterSequence> {

    /**
     * Deletes the last wovel from the sequence. if there is no wovel, no modifications are made.
     *
     * @param letterSequence input sequence.
     * @return this
     */
    public SequenceModifier<LetterSequence> modify(LetterSequence letterSequence) {
        for (int i = letterSequence.length() - 1; i >= 0; i--) {
            if (letterSequence.getLetter(i).isVowel()) {
                letterSequence.delete(i);
                break;
            }
        }
        return this;
    }
}
