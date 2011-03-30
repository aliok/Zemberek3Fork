package zemberek3.parser.morphology;

public abstract class MorphNode {
    public final String surfaceForm;
    public final TerminationType termination;

    protected MorphNode(String surfaceForm, TerminationType termination) {
        this.surfaceForm = surfaceForm;
        this.termination = termination;
    }

}
