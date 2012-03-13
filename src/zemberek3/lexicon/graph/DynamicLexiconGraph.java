package zemberek3.lexicon.graph;

import com.google.common.collect.Maps;
import zemberek3.lexicon.*;
import zemberek3.lexicon.tr.PhonAttr;
import zemberek3.lexicon.tr.PhoneticExpectation;
import zemberek3.lexicon.tr.StemNodeGenerator;

import java.util.*;

public class DynamicLexiconGraph {

    private Map<SuffixNode, SuffixNode> rootSuffixNodeMap = Maps.newHashMap();
    Set<StemNode> stemNodes = new HashSet<StemNode>();

    StemNodeGenerator stemNodeGenerator;
    SuffixNodeGenerator suffixNodeGenerator = new SuffixNodeGenerator();

    final SuffixProvider suffixProvider;

    private Map<SuffixForm, Set<SuffixNode>> suffixFormMap = Maps.newConcurrentMap();

    public DynamicLexiconGraph(SuffixProvider suffixProvider) {
        this.suffixProvider = suffixProvider;
        this.stemNodeGenerator = new StemNodeGenerator(suffixProvider);
    }

    public int totalSuffixNodeCount() {
        int total = 0;
        for (SuffixForm suffixFormSet : suffixFormMap.keySet()) {
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
                    generateNodeConnections(rootSuffixNode);
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
        SuffixForm set = suffixProvider.getRootSet(node.dictionaryItem, node.exclusiveSuffixData);
        // construct a new suffix node.
        return new SuffixNode(
                set,
                "",
                node.attributes,
                node.expectations,
                node.exclusiveSuffixData,
                node.termination);
    }

    /**
     * This method generates connections of a SuffixNode.
     * A SuffixNode is surfaceForm of a SuffixForm. (Suffix form ->A1pl_lAr, SuffixNode is lar)
     * We already know the morphotactics of SuffixForms. So we get the specific SuffixNodes that can be connected to a particular SuffixNode.
     * Such as, SuffixForm P1sg_Im can follow A1pl_lAr. Therefore, the SuffixNode lar can only connect to "ım" node of the P1sg_Im suffixForm.
     * Here this connection is generated, as the node reference in the successor form is added to this node.
     * However, if node to be connected does not exist, it is generated as well. And once it is generated and connection is provided
     * Recursively connections to that node are also generated.
     * @param node Node that connections to successive nodes will be generated.
     */
    private void generateNodeConnections(SuffixNode node) {
        // get the successive form sets for this node.
        SuffixData successors = node.suffixSet.connections;
        // iterate over form sets.
        for (SuffixForm successiveForm : successors) {

            // get the nodes for the  suffix form.
            List<SuffixNode> nodesInSuccessor = suffixNodeGenerator.generate(
                    node.attributes,
                    node.expectations,
                    node.exclusiveSuffixData,
                    successiveForm);
            for (SuffixNode nodeInSuccessor : nodesInSuccessor) {
                // if there are expectations for the node, check if it matches with the attributes of the node in successor.
                if (!node.expectations.isEmpty()) {
                    if (!expectationsMatches(node, nodeInSuccessor))
                        continue;
                }
                boolean recurse = false;
                if (!nodeExists(successiveForm, nodeInSuccessor)) {
                    recurse = true;
                }
                nodeInSuccessor = addOrReturnExisting(successiveForm, nodeInSuccessor);
                node.addSuccNode(nodeInSuccessor);
                if (recurse) {
                    generateNodeConnections(nodeInSuccessor);
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

    private boolean nodeExists(SuffixForm set, SuffixNode newNode) {
        Set<SuffixNode> nodes = suffixFormMap.get(set);
        return nodes != null && nodes.contains(newNode);
    }
    
    public void stats() {
        Set<StemNode> stemNodes = getStemNodes();
        System.out.println("Stem Node Count:" + stemNodes.size());
        Set<SuffixNode> rootSuffixNodes = new HashSet<SuffixNode>();
        for (StemNode stemNode : stemNodes) {
            rootSuffixNodes.add(stemNode.getSuffixRootNode());
        }
        System.out.println("Root SuffixNode count:"+rootSuffixNodes.size());
        int nodeCount = 0;
        for(SuffixForm form: suffixFormMap.keySet()) {
            System.out.println(form.toString());
            Set<SuffixNode> nodes = suffixFormMap.get(form);
            nodeCount+=nodes.size();
        }
        System.out.println("SuffixNode count:"+nodeCount);
    }

    public SuffixNode addOrReturnExisting(SuffixForm set, SuffixNode newNode) {

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
