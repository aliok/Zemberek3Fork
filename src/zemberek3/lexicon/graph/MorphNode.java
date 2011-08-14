package zemberek3.lexicon.graph;

import zemberek3.lexicon.ExclusiveSuffixData;
import zemberek3.lexicon.PhonAttr;
import zemberek3.structure.AttributeSet;

public abstract class MorphNode {
    public final String surfaceForm;
    public final TerminationType termination;
    public AttributeSet<PhoneticExpectation> expectations = new AttributeSet<PhoneticExpectation>();
    public AttributeSet<PhonAttr> attributes = new AttributeSet<PhonAttr>();
    public ExclusiveSuffixData exclusiveSuffixData = new ExclusiveSuffixData();

    protected MorphNode(String surfaceForm, TerminationType termination) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
    }

    protected MorphNode(
            String surfaceForm,
            TerminationType termination,
            AttributeSet<PhonAttr> attributes,
            AttributeSet<PhoneticExpectation> expectations,
            ExclusiveSuffixData exclusiveSuffixData
    ) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
        this.attributes = attributes;
        this.expectations = expectations;
        this.exclusiveSuffixData = exclusiveSuffixData;
    }

    protected MorphNode(
            String surfaceForm,
            TerminationType termination,
            AttributeSet<PhonAttr> attributes,
            AttributeSet<PhoneticExpectation> expectations
    ) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
        this.attributes = attributes;
        this.expectations = expectations;
    }


    public AttributeSet<PhoneticExpectation> getExpectations() {
        return expectations;
    }

    public boolean isNullMorpheme() {
        return surfaceForm.length()==0;
    }
}

