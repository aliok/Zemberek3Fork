package zemberek3.lexicon;

/**
 * this is an intermediate object necessary for building the lexicon part of the graph.
 */
public class RootState {
    // actual form of the root.
    String surfaceForm;
    // lexical Item
    LexiconItem lexiconItem;
    //boundary state that this state connects to.
    BoundaryState boundaryState;
    // if this state can be a terminal state.
    boolean terminal;

    public RootState(String surfaceForm, LexiconItem lexiconItem, BoundaryState boundaryState, boolean terminal) {
        this.surfaceForm = surfaceForm;
        this.lexiconItem = lexiconItem;
        this.boundaryState = boundaryState;
        this.terminal = terminal;
    }

    public String toString() {
        return surfaceForm + " : " + (terminal ? "terminal " : "non-terminal") + " : " + lexiconItem.toString() + " : " + boundaryState;
    }

}
