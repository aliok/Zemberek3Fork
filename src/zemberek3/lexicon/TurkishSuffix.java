package zemberek3.lexicon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TurkishSuffix {

    String id;
    String generationString;
    List<TurkishSuffix> successors = new ArrayList<TurkishSuffix>();
    List<SuffixNode> nodes = new ArrayList<SuffixNode>();

    public TurkishSuffix(String id, String generationString) {
        this.id = id;
        this.generationString = generationString;
    }

    public TurkishSuffix addSuccessor(TurkishSuffix... suffix) {
        this.successors.addAll(Arrays.asList(suffix));
        return this;
    }

    public TurkishSuffix addStates(SuffixNode... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        return this;
    }

}
