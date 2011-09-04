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
    public TerminationType terminationType = TerminationType.TERMINAL;

    public SuffixData successors = new SuffixData();
    public SuffixData directSuccessors = new SuffixData();

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

    public SuffixFormSet(String id, Suffix suffix, String generation, TerminationType terminationType) {
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

    /**
     * Generates a copy of this SuffixSet. However, it overwrites successor data using the input SuffixData.
     *
     * @param allSuccessors all successors to owewrite the copied ones. it eliminates the direct successors which does not
     *                      exist in allSuccessors, and remaining ones are used as 'successor'
     * @return copy set.
     */
    public SuffixFormSet copy(SuffixData allSuccessors) {
        SuffixFormSet copy = new SuffixFormSet(
                id,
                suffix,
                generation,
                terminationType
        );

        for (SuffixFormSet successor : allSuccessors) {
            if (directSuccessors.contains(successor))
                copy.directSuccessors.add(successor);
            else
                copy.successors.add(successor);
        }
        return copy;
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
        if (!directSuccessors.equals(that.directSuccessors)) return false;
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
        for (SuffixFormSet successor : directSuccessors) {
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
