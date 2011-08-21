package zemberek3.lexicon;

import zemberek3.lexicon.graph.SuffixData;
import zemberek3.lexicon.graph.TerminationType;

public class SuffixFormSet {
    // an id that defines the node
    public final String id;
    // parent suffix
    public final Suffix suffix;
    // generation word.
    public final String generation;
    // can be an end suffix.
    TerminationType terminationType = TerminationType.TERMINAL;

    private SuffixData successors = new SuffixData();
    private SuffixData directSuccessors = new SuffixData();

    public SuffixFormSet(String id, Suffix suffix, String generation) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
    }

    public SuffixFormSet(Suffix suffix, String generation, TerminationType terminationType) {
        this.id = suffix.id + "_" + generation;
        this.suffix = suffix;
        this.generation = generation;
        this.terminationType = terminationType;
    }

    public SuffixFormSet(String id, Suffix suffix, String generation,TerminationType terminationType) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
        this.terminationType = terminationType;
    }

    public SuffixFormSet(Suffix suffix, String generation) {
        this.suffix = suffix;
        this.generation = generation;
        this.id = suffix.id + "_" + generation;
    }

    public boolean isTerminal() {
        return terminationType == TerminationType.TERMINAL;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixFormSet that = (SuffixFormSet) o;

        if (!generation.equals(that.generation)) return false;
        if (!successors.equals(that.successors)) return false;
        if (!suffix.equals(that.suffix)) return false;
        if (terminationType != that.terminationType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = suffix.hashCode();
        result = 31 * result + generation.hashCode();
        result = 31 * result + terminationType.hashCode();
        for (SuffixFormSet successor : successors) {
           result = 31 * result + successor.getId().hashCode();
        }
        return result;
    }

    public SuffixData getSuccessors() {
        return successors;
    }

    public SuffixData getDirectSuccessors() {
        return directSuccessors;
    }
}
