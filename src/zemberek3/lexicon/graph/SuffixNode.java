package zemberek3.lexicon.graph;

import zemberek3.lexicon.PhonAttr;
import zemberek3.lexicon.TurkishSuffix;
import zemberek3.structure.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class SuffixNode extends MorphNode {

    TurkishSuffix suffix;
    AttributeSet<PhonAttr> attributes = new AttributeSet<PhonAttr>();
    List<MorphNode> successors = new ArrayList<MorphNode>(1);

    public SuffixNode(TurkishSuffix suffix, String form, AttributeSet<PhonAttr> attributes, TerminationType termination) {
        super(form, termination);
        this.suffix = suffix;
        this.attributes = attributes;
    }

    public SuffixNode addSuccNode(MorphNode form) {
        this.successors.add(form);
        return this;
    }

    public List<MorphNode> getSuccessors() {
        return successors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixNode that = (SuffixNode) o;
        if (!surfaceForm.equals(that.surfaceForm)) return false;
        if (!attributes.equals(that.attributes)) return false;
        if (!suffix.equals(that.suffix)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = suffix.hashCode();
        result = 31 * result + attributes.hashCode();
        result = 31 * result + surfaceForm.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return suffix.id + ":" + this.surfaceForm;
    }
}
