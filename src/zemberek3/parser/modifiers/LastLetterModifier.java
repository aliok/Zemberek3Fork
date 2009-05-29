package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;
import zemberek3.structure.Letter;

import java.util.Map;

public class LastLetterModifier implements Modifier<LetterSequence> {
    final Map<Letter, Letter> table;

    public LastLetterModifier(Map<Letter, Letter> table) {
        this.table = table;
    }

    public LetterSequence modify(LetterSequence letterSequence) {
        final Letter last = letterSequence.lastLetter();
        if (last == null)
            return letterSequence;
        letterSequence.changeLetter(letterSequence.length() - 1, last);
        return letterSequence;
    }
}
