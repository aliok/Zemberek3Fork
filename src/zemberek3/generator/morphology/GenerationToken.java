package zemberek3.generator.morphology;

import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;

import java.util.ArrayList;
import java.util.List;

public class GenerationToken {
    StemNode stemNode;
    SuffixNode currentNode;
    List<SuffixNode> nodesLeft;
    String currentForm;

    public GenerationToken(StemNode stemNode, List<SuffixNode> nodesLeft) {
        this.stemNode = stemNode;
        this.currentNode = stemNode.getSuffixRootNode();
        this.nodesLeft = nodesLeft;
        this.currentForm =stemNode.surfaceForm;
    }

    public GenerationToken(StemNode stemNode, SuffixNode currentNode, List<SuffixNode> nodesLeft, String currentForm) {
        this.stemNode = stemNode;
        this.currentNode = currentNode;
        this.nodesLeft = nodesLeft;
        this.currentForm = currentForm;
    }

    GenerationToken getCopy(SuffixNode node) {
        ArrayList<SuffixNode> hist = new ArrayList<SuffixNode>(nodesLeft);
        hist.add(node);
        return new GenerationToken(stemNode, node, hist, currentForm);
    }
}
