package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicSeq;

public class SuffixForm {

    TurkicSeq sequence;
    AttributeSet<PhonAttr> attributes = new AttributeSet<PhonAttr>();
    AttributeSet<PhonAttr> expectations = new AttributeSet<PhonAttr>();

    public SuffixForm(TurkicSeq sequence, AttributeSet<PhonAttr> attributes, AttributeSet<PhonAttr> expectations) {
        this.sequence = sequence;
        this.attributes = attributes;
        this.expectations = expectations;
    }

    public SuffixForm(TurkicSeq sequence) {
        this.sequence = sequence;
    }

    public SuffixForm copy() {
        return new SuffixForm(new TurkicSeq(sequence), attributes.copy(), expectations.copy());
    }

    public String surface() {
        return sequence.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixForm that = (SuffixForm) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;
        if (expectations != null ? !expectations.equals(that.expectations) : that.expectations != null) return false;
        if (sequence != null ? !sequence.equals(that.sequence) : that.sequence != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sequence != null ? sequence.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (expectations != null ? expectations.hashCode() : 0);
        return result;
    }
}