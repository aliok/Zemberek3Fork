package zemberek3.lexicon.graph;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.structure.AttributeSet;

public class StemNode extends MorphNode {
    DictionaryItem dictionaryItem;
    SuffixNode suffixRootNode;

    protected StemNode(String form, DictionaryItem dictionaryItem, SuffixNode suffixRootNode, TerminationType termination) {
        super(form, termination);
        this.dictionaryItem = dictionaryItem;
        this.suffixRootNode = suffixRootNode;
    }

    public StemNode(String surfaceForm, DictionaryItem dictionaryItem, TerminationType termination, AttributeSet<PhoneticExpectation> expectations) {
        super(surfaceForm, termination, expectations);
        this.dictionaryItem = dictionaryItem;
    }

    public StemNode(String surfaceForm, DictionaryItem dictionaryItem, TerminationType termination) {
        super(surfaceForm, termination, AttributeSet.<PhoneticExpectation>emptySet());
        this.dictionaryItem = dictionaryItem;
    }

    public SuffixNode getSuffixRootNode() {
        return suffixRootNode;
    }

    public DictionaryItem getDictionaryItem() {
        return dictionaryItem;
    }

    public boolean isTerminal() {
        return termination == TerminationType.TERMINAL;
    }

    @Override
    public String toString() {
        return surfaceForm + ":" + dictionaryItem;
    }
}
