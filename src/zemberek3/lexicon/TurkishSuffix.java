package zemberek3.lexicon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TurkishSuffix {

    String id;
    List<TurkishSuffix> successors = new ArrayList<TurkishSuffix>();
    List<SuffixNode> nodes = new ArrayList<SuffixNode>();

    public TurkishSuffix(String id) {
        this.id = id;
    }

    public TurkishSuffix addSuccessor(TurkishSuffix... suffix) {
        this.successors.addAll(Arrays.asList(suffix));
        return this;
    }

    public TurkishSuffix addNodes(SuffixNode... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        return this;
    }

    @Override
    public String toString() {
        return id;
    }
}
