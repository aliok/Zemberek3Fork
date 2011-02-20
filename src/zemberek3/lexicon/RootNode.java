package zemberek3.lexicon;

/**
 * this is an intermediate object necessary for building the lexicon part of the graph.
 */
public class RootNode {
    // actual form of the root.
    String surfaceForm;
    // lexical Item
    LexiconItem lexiconItem;
    //boundary state that this state connects to.
    BoundaryNode boundaryNode;
    // if this state can be a terminal state.
    boolean terminal;

    public RootNode(String surfaceForm, LexiconItem lexiconItem, BoundaryNode boundaryNode, boolean terminal) {
        this.surfaceForm = surfaceForm;
        this.lexiconItem = lexiconItem;
        this.boundaryNode = boundaryNode;
        this.terminal = terminal;
    }

    public String toString() {
        return surfaceForm + " : " + (terminal ? "terminal " : "non-terminal") + " : " + lexiconItem.toString() + " : " + boundaryNode;
    }

}
