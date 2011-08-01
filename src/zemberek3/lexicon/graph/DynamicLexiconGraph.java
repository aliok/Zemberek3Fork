package zemberek3.lexicon.graph;

import zemberek3.lexicon.*;

import java.util.HashSet;
import java.util.Set;

public class DynamicLexiconGraph {

    Set<SuffixNode> suffixRootNodes = new HashSet<SuffixNode>();
    Set<StemNode> stemNodes = new HashSet<StemNode>();

    StemNodeGenerator stemNodeGenerator = new StemNodeGenerator();
    SuffixNodeGenerator suffixNodeGenerator = new SuffixNodeGenerator();

    public void addItem(DictionaryItem item) {

        StemNode[] stems = stemNodeGenerator.generate(item);
        for (StemNode stem : stems) {
            if (!stemNodes.contains(stem)) {
                stem.suffixRootNode = getSuffixRootNode(stem);
            } else {
                // duplicate stem!
            }
        }
    }

    public SuffixNode getSuffixRootNode(StemNode node) {
        SuffixFormSet set = getRootForm(node.dictionaryItem);
        // construct a new suffix node.
        SuffixNode suffixNode = new SuffixNode(set, "", node.attributes, node.expectations, node.termination);
        // check if it already exist.

        // TODO : NOT FINISHED.
        return suffixNode;
    }

    public SuffixFormSet getRootForm(DictionaryItem item) {
        switch (item.primaryPos) {
            case Noun:
                return TurkishSuffixes.Noun_Main;
            case Verb:
                return TurkishSuffixes.Verb_Main;
            default:
                // TODO: for now return noun for all but verbs.
                return TurkishSuffixes.Noun_Main;
        }
    }
}
