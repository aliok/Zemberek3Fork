package zemberek3.parser.modifiers;

import zemberek3.structure.LetterSequence;


public class Appender implements Modifier<LetterSequence> {

    final LetterSequence toBeAppended;

    public Appender(LetterSequence toBeAppended) {
        this.toBeAppended = toBeAppended;
    }

    public LetterSequence modify(LetterSequence sequence) {
        return sequence.append(toBeAppended);
    }
}
