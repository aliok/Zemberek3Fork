package zemberek3.lexicon.graph;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.PhonAttr;
import zemberek3.structure.AttributeSet;

public class StemNode extends MorphNode {

    DictionaryItem dictionaryItem;
    SuffixNode suffixRootNode;

    protected StemNode(String form, DictionaryItem dictionaryItem, SuffixNode suffixRootNode, TerminationType termination) {
        super(form, termination);
        this.dictionaryItem = dictionaryItem;
        this.suffixRootNode = suffixRootNode;
    }

    public StemNode(String surfaceForm,
                    DictionaryItem dictionaryItem,
                    TerminationType termination,
                    AttributeSet<PhonAttr> phonAttrs,
                    AttributeSet<PhoneticExpectation> expectations) {
        super(surfaceForm, termination, phonAttrs, expectations);
        this.dictionaryItem = dictionaryItem;
    }

    public StemNode(String surfaceForm,
                    DictionaryItem dictionaryItem,
                    TerminationType termination,
                    AttributeSet<PhonAttr> phonAttrs) {
        super(surfaceForm, termination, phonAttrs, AttributeSet.<PhoneticExpectation>emptySet());
        this.dictionaryItem = dictionaryItem;
    }

    public StemNode(String surfaceForm,
                    DictionaryItem dictionaryItem,
                    AttributeSet<PhonAttr> phonAttrs,
                    AttributeSet<PhoneticExpectation> expectations) {
        super(surfaceForm, phonAttrs, expectations);
        this.dictionaryItem = dictionaryItem;
    }

    public StemNode(String surfaceForm, DictionaryItem dictionaryItem, TerminationType termination) {
        super(surfaceForm, termination, AttributeSet.<PhonAttr>emptySet(), AttributeSet.<PhoneticExpectation>emptySet());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StemNode stemNode = (StemNode) o;

        if (!dictionaryItem.equals(stemNode.dictionaryItem)) return false;
        if (suffixRootNode != null ? !suffixRootNode.equals(stemNode.suffixRootNode) : stemNode.suffixRootNode != null)
            return false;
        if (!attributes.equals(stemNode.attributes)) return false;
        if (!exclusiveSuffixData.equals(stemNode.exclusiveSuffixData)) return false;
        if (!expectations.equals(stemNode.expectations)) return false;
        if (!surfaceForm.equals(stemNode.surfaceForm)) return false;
        if (termination != stemNode.termination) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dictionaryItem.hashCode();
        result = 31 * result + (suffixRootNode != null ? suffixRootNode.hashCode() : 0);
        result = 31 * result + termination.hashCode();
        result = 31 * result + expectations.hashCode();
        result = 31 * result + attributes.hashCode();
        result = 31 * result + exclusiveSuffixData.hashCode();
        return result;
    }
}
