package zemberek3.parser.morphology;

import zemberek3.lexicon.DictionaryItem;

public class StemNode extends MorphNode {
    DictionaryItem dictionaryItem;
    SuffixNode suffixRootNode;

    protected StemNode(String form, DictionaryItem dictionaryItem, SuffixNode suffixRootNode, TerminationType termination) {
        super(form, termination);
        this.dictionaryItem = dictionaryItem;
        this.suffixRootNode = suffixRootNode;
    }
}
