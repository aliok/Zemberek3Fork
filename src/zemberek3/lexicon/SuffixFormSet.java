package zemberek3.lexicon;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SuffixFormSet {
    // an id that defines the node
    public final String id;
    // parent suffix
    public final Suffix suffix;
    // generation word.
    public final String generation;
    // can be an end suffix.
    boolean terminal = true;

    private Set<SuffixFormSet> successors = new HashSet<SuffixFormSet>();

    public SuffixFormSet(String id, Suffix suffix, String generation) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
    }

    public SuffixFormSet(Suffix suffix, String generation, boolean  terminal) {
        this.id = suffix.id + "_" + generation;
        this.suffix = suffix;
        this.generation = generation;
        this.terminal = terminal;
    }


    public SuffixFormSet(String id, Suffix suffix, String generation, boolean terminal) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
        this.terminal = terminal;
    }

    public SuffixFormSet(Suffix suffix, String generation) {
        this.suffix = suffix;
        this.generation = generation;
        this.id = suffix.id + "_" + generation;
    }

    public Iterable<SuffixFormSet> getSuccessorsIterable() {
        return successors;
    }

    public Set<SuffixFormSet> getSuccSetCopy() {
        return new HashSet<SuffixFormSet>(successors);
    }

    public Set<SuffixFormSet> getSuccessors() {
        return successors;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public SuffixFormSet clear() {
        this.successors.clear();
        return this;
    }

    public SuffixFormSet add(SuffixFormSet... sets) {
        this.successors.addAll(Arrays.asList(sets));
        return this;
    }

    public SuffixFormSet add(Iterable<SuffixFormSet> it) {
        for (SuffixFormSet suff : it)
            successors.add(suff);
        return this;
    }

    public SuffixFormSet add(SuffixFormSet[]... sets) {
        for (SuffixFormSet[] suffixArray : sets) {
            this.successors.addAll(Arrays.asList(suffixArray));
        }
        return this;
    }

    public SuffixFormSet remove(SuffixFormSet... sets) {
        for (SuffixFormSet set : sets) {
            this.successors.remove(set);
        }
        return this;
    }

    public SuffixFormSet remove(Iterable<SuffixFormSet> it) {
        for (SuffixFormSet suff : it)
            successors.remove(suff);
        return this;
    }

    public SuffixFormSet retain(Collection<SuffixFormSet> coll) {
        successors.retainAll(coll);
        return this;
    }

    public Suffix getSuffix() {
        return suffix;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return id;
    }
}
