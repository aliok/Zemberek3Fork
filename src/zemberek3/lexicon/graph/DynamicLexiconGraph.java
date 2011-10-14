package zemberek3.lexicon.graph;

import com.google.common.collect.Maps;
import zemberek3.lexicon.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamicLexiconGraph {

    private Map<SuffixNode, SuffixNode> rootSuffixNodeMap = Maps.newHashMap();
    Set<StemNode> stemNodes = new HashSet<StemNode>();

    StemNodeGenerator stemNodeGenerator;
    SuffixNodeGenerator suffixNodeGenerator = new SuffixNodeGenerator();

    final SuffixProvider suffixProvider;

    private Map<SuffixFormSet, Set<SuffixNode>> suffixFormMap = Maps.newConcurrentMap();

    public DynamicLexiconGraph(SuffixProvider suffixProvider) {
        this.suffixProvider = suffixProvider;
        this.stemNodeGenerator = new StemNodeGenerator(suffixProvider);
    }

    public int totalSuffixNodeCount() {
        int total = 0;
        for (SuffixFormSet suffixFormSet : suffixFormMap.keySet()) {
            total += suffixFormMap.get(suffixFormSet).size();
        }
        return total;
    }

    public int totalStemNodeCount() {
        return stemNodes.size();
    }

    public void addDictionaryItem(DictionaryItem item) {

        StemNode[] stems = stemNodeGenerator.generate(item);

        for (StemNode stem : stems) {

            if (!stemNodes.contains(stem)) {
                SuffixNode rootSuffixNode = getRootSuffixNode(stem);
                if (!rootSuffixNodeMap.containsKey(rootSuffixNode)) {
                    connectSuffixNodes(rootSuffixNode);
                }
                // check if it already exist. If it exists, use the existing one or add the new one.
                rootSuffixNode = addOrRetrieveExisting(rootSuffixNode);
                // connect stem to suffix root node.
                stem.suffixRootNode = rootSuffixNode;
                stemNodes.add(stem);
            } else {
                // duplicate stem!
                System.out.println("Stem Node:" + stem + " already exist.");
            }
        }
    }

    public void addDictionaryItems(DictionaryItem... items) {
        for (DictionaryItem item : items) {
            addDictionaryItem(item);
        }
    }

    public void addDictionaryItems(List<DictionaryItem> items) {
        for (DictionaryItem item : items) {
            addDictionaryItem(item);
        }
    }

    public Set<StemNode> getStemNodes() {
        return stemNodes;
    }

    private SuffixNode addOrRetrieveExisting(SuffixNode nodeTocheck) {
        if (!rootSuffixNodeMap.containsKey(nodeTocheck)) {
            rootSuffixNodeMap.put(nodeTocheck, nodeTocheck);
            return nodeTocheck;
        } else return rootSuffixNodeMap.get(nodeTocheck);
    }

    public SuffixNode getRootSuffixNode(StemNode node) {
        SuffixFormSet set = suffixProvider.getRootSet(node.dictionaryItem, node.exclusiveSuffixData);
        // construct a new suffix node.
        return new SuffixNode(
                set,
                "",
                node.attributes,
                node.expectations,
                node.exclusiveSuffixData,
                node.termination);
    }


    private void connectSuffixNodes(SuffixNode node) {
        // get the successive form sets for this node.
        SuffixData successors = node.suffixSet.directSuccessors;
        // iterate over form sets.
        for (SuffixFormSet succSet : successors) {
            // get the nodes for the  suffix form.
            if (succSet.isTemplate())
                continue;
            List<SuffixNode> nodesInSuccessor = suffixNodeGenerator.getNodes(
                    node.attributes,
                    node.expectations,
                    node.exclusiveSuffixData,
                    succSet);
            for (SuffixNode nodeInSuccessor : nodesInSuccessor) {
                // if there are expectations for the node, check if it matches with the attributes of the node in successor.
                if (!node.expectations.isEmpty()) {
                    if (!expectationsMatches(node, nodeInSuccessor))
                        continue;
                }
                boolean recurse = false;
                if (!nodeExists(succSet, nodeInSuccessor)) {
                    recurse = true;
                }
                nodeInSuccessor = addOrReturnExisting(succSet, nodeInSuccessor);
                node.addSuccNode(nodeInSuccessor);
                if (recurse) {
                    connectSuffixNodes(nodeInSuccessor);
                }
            }
        }
    }

    private boolean expectationsMatches(SuffixNode node, SuffixNode nodeInSuccessor) {
        if (nodeInSuccessor.isNullMorpheme())
            return true;
        if ((node.expectations.contains(PhoneticExpectation.ConsonantStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterConsonant)) ||
                (node.expectations.contains(PhoneticExpectation.VowelStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterVowel)))
            return true;
        else return false;

    }

    private boolean nodeExists(SuffixFormSet set, SuffixNode newNode) {
        Set<SuffixNode> nodes = suffixFormMap.get(set);
        return nodes != null && nodes.contains(newNode);
    }

    public SuffixNode addOrReturnExisting(SuffixFormSet set, SuffixNode newNode) {

        if (!suffixFormMap.containsKey(set)) {
            suffixFormMap.put(set, new HashSet<SuffixNode>());
        }
        Set<SuffixNode> nodes = suffixFormMap.get(set);
        if (!nodes.contains(newNode)) {
            nodes.add(newNode);
            return newNode;
        }
        for (SuffixNode node : nodes) {
            if (node.equals(newNode))
                return node;
        }
        throw new IllegalStateException("Cannot be here. Set: " + set.id + " Node:" + newNode.dump());
    }
}
