package zemberek3.lexicon.graph;

import zemberek3.lexicon.tr.PhonAttr;
import zemberek3.lexicon.tr.PhoneticExpectation;
import zemberek3.structure.AttributeSet;

public abstract class MorphNode {
    public final String surfaceForm;
    public TerminationType termination = TerminationType.TERMINAL;
    public AttributeSet<PhoneticExpectation> expectations = new AttributeSet<PhoneticExpectation>();
    public AttributeSet<PhonAttr> attributes = new AttributeSet<PhonAttr>();
    public SuffixData exclusiveSuffixData = new SuffixData();

    protected MorphNode(String surfaceForm, TerminationType termination) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
    }

    protected MorphNode(
            String surfaceForm,
            TerminationType termination,
            AttributeSet<PhonAttr> attributes,
            AttributeSet<PhoneticExpectation> expectations,
            SuffixData suffixData
    ) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
        this.attributes = attributes;
        this.expectations = expectations;
        this.exclusiveSuffixData = suffixData;
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

    protected MorphNode(
            String surfaceForm,
            AttributeSet<PhonAttr> attributes,
            AttributeSet<PhoneticExpectation> expectations
    ) {
        this.surfaceForm = surfaceForm;
        this.attributes = attributes;
        this.expectations = expectations;
    }


    public AttributeSet<PhoneticExpectation> getExpectations() {
        return expectations;
    }

    public boolean isNullMorpheme() {
        return surfaceForm.length() == 0;
    }

    public void setTermination(TerminationType termination) {
        this.termination = termination;
    }
}

