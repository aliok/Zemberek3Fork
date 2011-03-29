package zemberek3.lexicon;

import zemberek3.parser.morphology.SuffixForm;

/**
 * This represents a surface form of a DictionaryItem.
 */
public class Stem {
    // actual form of the root.
    String surfaceForm;
    // lexical Item
    DictionaryItem dictionaryItem;
    // Boundary state that this state connects to.
    SuffixForm rootForm;
    // if this state can be a terminal state.
    boolean terminal;

    public Stem(String surfaceForm, DictionaryItem dictionaryItem, SuffixForm rootForm, boolean terminal) {
        this.surfaceForm = surfaceForm;
        this.dictionaryItem = dictionaryItem;
        this.rootForm = rootForm;
        this.terminal = terminal;
    }

    public String toString() {
        return surfaceForm + " : " + (terminal ? "terminal " : "non-terminal") + " : " + dictionaryItem.toString() + " : " + rootForm;
    }

}
