package zemberek3.lexicon.graph;

import zemberek3.lexicon.PhonAttr;
import zemberek3.lexicon.SuffixFormSet;
import zemberek3.structure.AttributeSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuffixNode extends MorphNode {

    SuffixFormSet suffixSet;
    AttributeSet<PhonAttr> attributes = new AttributeSet<PhonAttr>();
    Set<SuffixNode> successors = new HashSet<SuffixNode>(1);

    public SuffixNode(SuffixFormSet suffixSet, String form, AttributeSet<PhonAttr> attributes, TerminationType termination) {
        super(form, termination);
        this.suffixSet = suffixSet;
        this.attributes = attributes;
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
        if (!suffixSet.equals(that.suffixSet)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = suffixSet.hashCode();
        result = 31 * result + attributes.hashCode();
        result = 31 * result + surfaceForm.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return suffixSet.id + ":" + this.surfaceForm;
    }
}
