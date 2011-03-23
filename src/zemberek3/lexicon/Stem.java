package zemberek3.lexicon;

/**
 * this is an intermediate object necessary for building the lexicon part of the graph.
 */
public class Stem {
    // actual form of the root.
    String surfaceForm;
    // lexical Item
    LexiconItem lexiconItem;
    // Boundary state that this state connects to.
    SuffixForm rootForm;
    // if this state can be a terminal state.
    boolean terminal;

    public Stem(String surfaceForm, LexiconItem lexiconItem, SuffixForm rootForm, boolean terminal) {
        this.surfaceForm = surfaceForm;
        this.lexiconItem = lexiconItem;
        this.rootForm = rootForm;
        this.terminal = terminal;
    }

    public String toString() {
        return surfaceForm + " : " + (terminal ? "terminal " : "non-terminal") + " : " + lexiconItem.toString() + " : " + rootForm;
    }

}
