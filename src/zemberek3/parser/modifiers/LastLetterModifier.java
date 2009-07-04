package zemberek3.parser.modifiers;

import zemberek3.structure.Letter;
import zemberek3.structure.LetterSequence;

import java.util.Map;

/**
 * modifies the last letter of a sequence according to a given Letter Map.
 */
public class LastLetterModifier implements SequenceModifier<LetterSequence> {

    Letter[] arrayLookup = new Letter[100];

    public LastLetterModifier(Map<Letter, Letter> table) {
        for (Letter letter : table.keySet()) {
            arrayLookup[letter.alphabeticIndex()] = table.get(letter);
        }
    }

    public SequenceModifier<LetterSequence> modify(LetterSequence letterSequence) {
        return this;
    }
}
