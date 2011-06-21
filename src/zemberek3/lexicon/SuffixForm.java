package zemberek3.lexicon;

import zemberek3.lexicon.graph.PhoneticExpectation;
import zemberek3.structure.AttributeSet;

public class SuffixForm {

    public final String surface;
    final AttributeSet<PhonAttr> attributes;
    final AttributeSet<PhoneticExpectation> expectations;

    public SuffixForm(String surface, AttributeSet<PhonAttr> attributes) {
        this.surface = surface;
        this.attributes = attributes;
        this.expectations = AttributeSet.emptySet();
    }

    public SuffixForm(String surface, AttributeSet<PhonAttr> attributes,AttributeSet<PhoneticExpectation> expectations ) {
        this.surface = surface;
        this.attributes = attributes;
        this.expectations = expectations;
    }

    public SuffixForm copy() {
        return new SuffixForm(surface, attributes.copy());
    }

    public AttributeSet<PhonAttr> getAttributes() {
        return attributes;
    }

    public String getSurface() {
        return surface;
    }

    public AttributeSet<PhoneticExpectation> getExpectations() {
        return expectations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixForm that = (SuffixForm) o;

        if (!attributes.equals(that.attributes)) return false;
        if (!expectations.equals(that.expectations)) return false;
        if (!surface.equals(that.surface)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = surface.hashCode();
        result = 31 * result + attributes.hashCode();
        result = 31 * result + expectations.hashCode();
        return result;
    }

    public String toString() {
        return surface + ":" + attributes.getAsList(PhonAttr.class);
    }
}
