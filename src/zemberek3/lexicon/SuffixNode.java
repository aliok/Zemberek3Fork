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

    Set<TurkishSuffix> successors = new HashSet<TurkishSuffix>();
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
        this.id = suffix.id+"_"+generation;
    }

    public SuffixNode addSuccessors(TurkishSuffix... suffix) {
        this.successors.addAll(Arrays.asList(suffix));
        return this;
    }

    public SuffixNode addSuccessors(Iterable<TurkishSuffix> it) {
        for(TurkishSuffix suff : it)
         successors.add(suff);
        return this;
    }

    public SuffixNode addSuccessors(TurkishSuffix[]... suffixArrays) {
        for (TurkishSuffix[] suffixArray : suffixArrays) {
            this.successors.addAll(Arrays.asList(suffixArray));
        }
        return this;
    }

    public SuffixNode addExclusivePredecessor(TurkishSuffix... suffixes) {
        exclusivePredecessors.addAll(Arrays.asList(suffixes));
        return this;
    }

    public String getId() {
        return id;
    }

}
