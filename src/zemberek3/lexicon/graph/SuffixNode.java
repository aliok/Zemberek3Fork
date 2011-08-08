package zemberek3.lexicon.graph;

import zemberek3.lexicon.ExclusiveSuffixData;
import zemberek3.lexicon.PhonAttr;
import zemberek3.lexicon.SuffixFormSet;
import zemberek3.structure.AttributeSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuffixNode extends MorphNode {

    SuffixFormSet suffixSet;
    Set<SuffixNode> successors = new HashSet<SuffixNode>(1);

    public SuffixNode(
            SuffixFormSet suffixSet,
            String form,
            AttributeSet<PhonAttr> attributes,
            AttributeSet<PhoneticExpectation> expectations,
            ExclusiveSuffixData exclusiveSuffixData,
            TerminationType termination) {
        super(form, termination, attributes, expectations, exclusiveSuffixData);
        this.suffixSet = suffixSet;
    }

    public SuffixNode(
            SuffixFormSet suffixSet,
            String form,
            AttributeSet<PhonAttr> attributes,
            TerminationType termination) {
        // TODO: expectations and exclusive suffix data is empty.
        super(form, termination, attributes, AttributeSet.<PhoneticExpectation>emptySet(), new ExclusiveSuffixData());
        this.suffixSet = suffixSet;
        this.attributes = attributes;
    }

    public SuffixFormSet getSuffixSet() {
        return suffixSet;
    }

    public AttributeSet<PhonAttr> getAttributes() {
        return attributes;
    }

    public SuffixNode addSuccNode(SuffixNode form) {
        this.successors.add(form);
        return this;
    }

    public Set<SuffixNode> getSuccessors() {
        return successors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixNode that = (SuffixNode) o;
        if (!surfaceForm.equals(that.surfaceForm)) return false;
        if (!attributes.equals(that.attributes)) return false;
        if (!expectations.equals(that.expectations)) return false;
        if (!suffixSet.equals(that.suffixSet)) return false;
        if (!exclusiveSuffixData.equals(that.exclusiveSuffixData)) return false;
        if (!termination.equals(that.termination)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = suffixSet.hashCode();
        result = 31 * result + attributes.hashCode();
        result = 31 * result + expectations.hashCode();
        result = 31 * result + surfaceForm.hashCode();
        result = 31 * result + exclusiveSuffixData.hashCode();
        result = 31 * result + termination.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return suffixSet.id + ":" + this.surfaceForm;
    }
}
