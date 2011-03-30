package zemberek3.parser.morphology;

import zemberek3.lexicon.PhonAttr;
import zemberek3.structure.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class SuffixForm {

    public final String surface;
    final AttributeSet<PhonAttr> attributes;

    public SuffixForm(String surface, AttributeSet<PhonAttr> attributes) {
        this.surface = surface;
        this.attributes = attributes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixForm that = (SuffixForm) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;
        if (surface != null ? !surface.equals(that.surface) : that.surface != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = surface != null ? surface.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }

    public String toString() {
        return surface + ":" + attributes.getAsList(PhonAttr.class);
    }
}
