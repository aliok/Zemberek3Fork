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

    StemNodeGenerator stemNodeGenerator = new StemNodeGenerator();
    SuffixNodeGenerator suffixNodeGenerator = new SuffixNodeGenerator();

    final SuffixProvider suffixProvider;

    private Map<SuffixFormSet, Set<SuffixNode>> suffixFormMap = Maps.newConcurrentMap();

    public DynamicLexiconGraph(SuffixProvider suffixProvider) {
            this.suffixProvider = suffixProvider;
    }

    public void addDictionaryItem(DictionaryItem item) {

        StemNode[] stems = stemNodeGenerator.generate(item);
        for (StemNode stem : stems) {
            if (!stemNodes.contains(stem)) {
                SuffixNode rootSuffixNode = getRootSuffixNode(stem);
                if (!rootSuffixNodeMap.containsKey(rootSuffixNode)) {
                    // check if it already exist. If it exists, use the existing one or add the new one.
                    rootSuffixNode = addOrRetrieveExisting(rootSuffixNode);
                    connectSuffixNodes(rootSuffixNode);
                }
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
        SuffixFormSet set = suffixProvider.getRootForm(node.dictionaryItem);
        // construct a new suffix node.
        SuffixNode suffixNode = new SuffixNode(
                set,
                "",
                node.attributes,
                node.expectations,
                node.exclusiveSuffixData,
                node.termination);
        return suffixNode;
    }


    private void connectSuffixNodes(SuffixNode node) {
        // get the successive form sets for this node.
        Set<SuffixFormSet> successors = node.suffixSet.getSuccessors();
        // iterate over form sets.
        for (SuffixFormSet succSet : successors) {
            // get the nodes for the  suffix form.
            List<SuffixNode> nodesInSuccessor = suffixNodeGenerator.getNodes(node.attributes, node.expectations, node.exclusiveSuffixData, succSet);
            for (SuffixNode nodeInSuccessor : nodesInSuccessor) {
                nodeInSuccessor = addOrReturnExisting(succSet, nodeInSuccessor);
                if (node.expectations.isEmpty() ||
                        (node.expectations.contains(PhoneticExpectation.ConsonantStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterConsonant)) ||
                        (node.expectations.contains(PhoneticExpectation.VowelStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterVowel)))
                    node.addSuccNode(nodeInSuccessor);
                if (!nodeExists(succSet, nodeInSuccessor)) {
                    connectSuffixNodes(nodeInSuccessor);
                }
            }
        }
    }

    private boolean nodeExists(SuffixFormSet set, SuffixNode newNode) {
        Set<SuffixNode> nodes = suffixFormMap.get(set);
        return nodes.contains(newNode);
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
        throw new IllegalStateException("Cannot be here.");
    }

/*    public void generate(Set<SuffixFormSet> startForms) {
    Set<SuffixFormSet> toProcess = new HashSet<SuffixFormSet>();
    for (SuffixFormSet rootFormSet : startForms) {
        for (SuffixFormSet succSet : rootFormSet.getSuccessorsIterable()) {
            for (SuffixNode node : suffixFormMap.get(rootFormSet)) {
                List<SuffixNode> nodesInSuccessor = nodeGenerator.getNodes(node.attributes, node.expectations, succSet);
                for (SuffixNode nodeInSuccessor : nodesInSuccessor) {
                    if (!nodeExists(succSet, nodeInSuccessor)) {
                        toProcess.add(succSet);
                    }
                    nodeInSuccessor = addOrReturnExisting(succSet, nodeInSuccessor);
                    if (node.expectations.isEmpty() ||
                            (node.expectations.contains(PhoneticExpectation.ConsonantStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterConsonant)) ||
                            (node.expectations.contains(PhoneticExpectation.VowelStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterVowel)))
                        node.addSuccNode(nodeInSuccessor);
                }
            }
        }
    }
    if (toProcess.size() == 0)
        return;
   generate(toProcess);
}*/

}
