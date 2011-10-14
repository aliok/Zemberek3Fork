package zemberek3.lexicon;

import zemberek3.lexicon.graph.SuffixData;
import zemberek3.lexicon.graph.TerminationType;

public class SuffixForm {
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

    public boolean template = false;

    public SuffixForm(String id, Suffix suffix, String generation) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
    }

    public SuffixData allSuccessors() {
        return new SuffixData(successors, directSuccessors);
    }

    public SuffixForm(Suffix suffix, String generation, TerminationType terminationType) {
        this.id = suffix.id + "_" + generation;
        this.suffix = suffix;
        this.generation = generation;
        this.terminationType = terminationType;
    }

    public SuffixForm(String id, Suffix suffix, String generation, TerminationType terminationType) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
        this.terminationType = terminationType;
    }

    public static SuffixForm getTemplate(String id, Suffix suffix) {
        SuffixForm set = new SuffixForm(id, suffix, "", TerminationType.TRANSFER);
        set.template = true;
        return set;
    }

    public static SuffixForm getTemplate(String id, Suffix suffix, TerminationType type) {
        SuffixForm set = new SuffixForm(id, suffix, "", type);
        set.template = true;
        return set;
    }


    public SuffixForm(Suffix suffix, String generation) {
        this.suffix = suffix;
        this.generation = generation;
        this.id = suffix.id + "_" + generation;
    }

    public boolean isTerminal() {
        return terminationType == TerminationType.TERMINAL;
    }

    public boolean isNullMorpheme() {
        return generation == null || generation.length() == 0;
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

        SuffixForm that = (SuffixForm) o;

        if (template != that.template) return false;
        if (!directSuccessors.equals(that.directSuccessors)) return false;
        if (!generation.equals(that.generation)) return false;
        if (!id.equals(that.id)) return false;
/*        if (!successors.equals(that.successors)) return false;
        if (!suffix.equals(that.suffix)) return false;*/
        if (terminationType != that.terminationType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + suffix.hashCode();
        result = 31 * result + generation.hashCode();
        result = 31 * result + terminationType.hashCode();
/*        result = 31 * result + successors.hashCode();
        result = 31 * result + directSuccessors.hashCode();*/
        result = 31 * result + (template ? 1 : 0);
        return result;
    }

    public boolean isTemplate() {
        return template;
    }
}
