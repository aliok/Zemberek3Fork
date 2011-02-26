package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicSeq;

/**
 * deletes the last wovel form the letter sequence. if there is no
 */
public class DeleteLastWovel implements SequenceModifier {

    /**
     * Deletes the last wovel from the sequence. if there is no vowel, no modifications are made.
     *
     * @param seq input sequence.
     * @return this
     */
    public SequenceModifier modify(TurkicSeq seq) {
        for (int i = seq.length() - 1; i >= 0; i--) {
            if (seq.getLetter(i).isVowel()) {
                seq.delete(i);
                break;
            }
        }
        return this;
    }
}
