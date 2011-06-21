package zemberek3.lexicon.graph;

import zemberek3.structure.AttributeSet;

public abstract class MorphNode {
    public final String surfaceForm;
    public final TerminationType termination;
    public AttributeSet<PhoneticExpectation> expectations = new AttributeSet<PhoneticExpectation>();

    protected MorphNode(String surfaceForm, TerminationType termination) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
    }

    protected MorphNode(String surfaceForm, TerminationType termination, AttributeSet<PhoneticExpectation> expectations) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
        this.expectations = expectations;
    }

    public AttributeSet<PhoneticExpectation> getExpectations() {
        return expectations;
    }
}
