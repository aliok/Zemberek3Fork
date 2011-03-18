package zemberek3.lexicon;

import java.util.*;

public class TurkishSuffix {

    String id;
    List<TurkishSuffix> successors = new ArrayList<TurkishSuffix>();
    List<SuffixNode> nodes = new ArrayList<SuffixNode>();
    Map<String, SuffixNode> nodeMap = new HashMap<String, SuffixNode>();

    public TurkishSuffix(String id) {
        this.id = id;
    }

    public TurkishSuffix addSuccessors(TurkishSuffix... suffix) {
        this.successors.addAll(Arrays.asList(suffix));
        return this;
    }

    public TurkishSuffix addSuccessors(TurkishSuffix[]... suffixArrays) {
        for (TurkishSuffix[] suffixArray : suffixArrays) {
            this.successors.addAll(Arrays.asList(suffixArray));
        }
        return this;
    }

    public TurkishSuffix addNode(SuffixNode... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        for (SuffixNode node : nodes) {
            nodeMap.put(node.getId(), node);
        }
        return this;
    }

    @Override
    public String toString() {
        return id;
    }
}
