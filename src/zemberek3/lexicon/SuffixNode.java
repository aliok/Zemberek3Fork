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

    private Set<SuffixNode> successors = new HashSet<SuffixNode>();

    public SuffixNode(String id, TurkishSuffix suffix, String generation) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
    }

    public SuffixNode(TurkishSuffix suffix, String generation) {
        this.suffix = suffix;
        this.generation = generation;
        this.id = suffix.id + "_" + generation;
    }

    public Iterable<SuffixNode> getSuccessors() {
        return successors;
    }

    public SuffixNode succ(SuffixNode... suffix) {
        this.successors.addAll(Arrays.asList(suffix));
        return this;
    }

    public SuffixNode succ(Iterable<SuffixNode> it) {
        for (SuffixNode suff : it)
            successors.add(suff);
        return this;
    }

    public SuffixNode succ(SuffixNode[]... suffixArrays) {
        for (SuffixNode[] suffixArray : suffixArrays) {
            this.successors.addAll(Arrays.asList(suffixArray));
        }
        return this;
    }

    public SuffixNode remove(SuffixNode... suffixNodes) {
        for (SuffixNode suffixNode : suffixNodes) {
            this.successors.remove(suffixNode);
        }
        return this;
    }

    public SuffixNode remove(Iterable<SuffixNode> it) {
        for (SuffixNode suff : it)
            successors.remove(suff);
        return this;
    }

    public String getId() {
        return id;
    }

}
