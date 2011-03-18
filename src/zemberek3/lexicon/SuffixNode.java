package zemberek3.lexicon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A
 */
public class SuffixNode {
    // an id that defines the node
    String id;
    // parent suffix
    TurkishSuffix suffix;
    // generation word.
    String generation;

    Set<SuffixNode> successors = new HashSet<SuffixNode>();
    Set<TurkishSuffix> exclusivePredecessors = new HashSet<TurkishSuffix>();

    public SuffixNode(String id, TurkishSuffix suffix, String generation) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
    }

    public SuffixNode(TurkishSuffix suffix, String generation, TurkishSuffix... exclusivePredecessors) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
        addExclusivePredecessor(exclusivePredecessors);
    }

    public SuffixNode(TurkishSuffix suffix, String generation) {
        this.suffix = suffix;
        this.generation = generation;
        this.id = generation;
    }


    public SuffixNode addExclusivePredecessor(TurkishSuffix... suffixes) {
        exclusivePredecessors.addAll(Arrays.asList(suffixes));
        return this;
    }

    public String getId() {
        return id;
    }

}
