package zemberek3.parser.modifiers;

import zemberek3.structure.Letter;
import zemberek3.structure.LetterSequence;

import java.util.Map;

public class LastLetterModifier implements SequenceModifier<LetterSequence> {

    Letter[] arrayLookup = new Letter[100];

    public LastLetterModifier(Map<Letter, Letter> table) {
        for (Letter letter : table.keySet()) {
            arrayLookup[letter.alphabeticIndex()] = table.get(letter);
        }
    }

    public LetterSequence modify(LetterSequence letterSequence) {
        final Letter last = letterSequence.lastLetter();
        if (last == null)
            return letterSequence;
        letterSequence.changeLetter(letterSequence.length() - 1, last);
        return letterSequence;
    }
}
