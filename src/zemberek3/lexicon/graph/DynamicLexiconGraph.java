package zemberek3.lexicon.graph;

import com.google.common.collect.Maps;
import zemberek3.lexicon.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DynamicLexiconGraph {

    private Map<SuffixNode, SuffixNode> suffixRootMap = Maps.newHashMap();
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
                System.out.println("Stem Node:" + stem + " already exist.");
            }
        }
    }

    private SuffixNode addOrRetrieveExisting(SuffixNode nodeTocheck) {
        if(!suffixRootMap.containsKey(nodeTocheck)) {
            suffixRootMap.put(nodeTocheck, nodeTocheck);
            return nodeTocheck;
        }
        else return suffixRootMap.get(nodeTocheck);
    }

    public SuffixNode getSuffixRootNode(StemNode node) {
        SuffixFormSet set = getRootForm(node.dictionaryItem);
        // construct a new suffix node.
        SuffixNode suffixNode = new SuffixNode(set, "", node.attributes, node.expectations, node.termination);
        // check if it already exist. If it exists, use the existing one or add the new one.
        suffixNode = addOrRetrieveExisting(suffixNode);

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
