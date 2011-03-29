package zemberek3.parser.morphology;

import zemberek3.lexicon.DictionaryItem;

public class StemNode extends MorphNode {
    DictionaryItem dictionaryItem;

    protected StemNode(String form, TerminationType termination) {
        super(form, termination);
    }
}
