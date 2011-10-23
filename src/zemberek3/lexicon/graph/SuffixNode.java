package zemberek3.lexicon.graph;

import zemberek3.lexicon.*;
import zemberek3.lexicon.tr.PhonAttr;
import zemberek3.lexicon.tr.PhoneticExpectation;
import zemberek3.structure.AttributeSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuffixNode extends MorphNode {

    SuffixForm suffixSet;
    Set<SuffixNode> successors = new HashSet<SuffixNode>(1);

    public SuffixNode(
            SuffixForm suffixSet,
            String surfaceForm,
            AttributeSet<PhonAttr> attributes,
            AttributeSet<PhoneticExpectation> expectations,
            SuffixData exclusiveSuffixData,
            TerminationType termination) {
        super(surfaceForm, termination, attributes, expectations, exclusiveSuffixData);
        this.suffixSet = suffixSet;
    }

    public SuffixNode(
            SuffixForm suffixSet,
            String surfaceForm,
            AttributeSet<PhonAttr> attributes,
            TerminationType termination) {
        // TODO: expectations and exclusive suffix data is empty.
        super(surfaceForm, termination, attributes, AttributeSet.<PhoneticExpectation>emptySet(), new SuffixData());
        this.suffixSet = suffixSet;
        this.attributes = attributes;
    }

    public SuffixForm getSuffixSet() {
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
        if (!suffixSet.getId().equals(that.suffixSet.getId())) return false;
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


    public String dump() {
        String surface = surfaceForm.length() == 0 ? "NULL" : surfaceForm;
        StringBuilder sb = new StringBuilder(" [set:" + suffixSet.id + "|" + surface + "]");
        if (successors.size() > 0) {
            sb.append(" [Successors:");
            int i = 0;
            for (SuffixNode successor : successors) {
                sb.append(successor.suffixSet.getId());
                if (i++ < successors.size() - 1)
                    sb.append(", ");
            }
            sb.append("]");
        }
        printAttributes(sb);
        printExpectations(sb);
        sb.append(" [T:" + termination.name() + "] ");
        return sb.toString();
    }

    private void printAttributes(StringBuilder sb) {
        if (!attributes.isEmpty())
            sb.append(" [A:");
        else return;
        int i = 0;
        List<PhonAttr> arr = attributes.getAsList(PhonAttr.class);
        for (PhonAttr attribute : arr) {
            sb.append(attribute.getShortForm());
            if (i++ < arr.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
    }

    private void printExpectations(StringBuilder sb) {
        if (!expectations.isEmpty())
            sb.append(" [E:");
        else return;
        int i = 0;
        List<PhoneticExpectation> arr = expectations.getAsList(PhoneticExpectation.class);
        for (PhoneticExpectation attribute : arr) {
            sb.append(attribute.name());
            if (i++ < arr.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
    }
}
