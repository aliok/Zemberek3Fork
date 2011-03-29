package zemberek3.lexicon;

import zemberek3.parser.morphology.SuffixForm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A
 */
public class SuffixFormSet {
    // an id that defines the node
    public final String id;
    // parent suffix
    public final TurkishSuffix suffix;
    // generation word.
    public final String generation;

    private Set<SuffixFormSet> successors = new HashSet<SuffixFormSet>();
    private Set<SuffixForm> forms = new HashSet<SuffixForm>();

    public SuffixFormSet(String id, TurkishSuffix suffix, String generation) {
        this.id = id;
        this.suffix = suffix;
        this.generation = generation;
    }

    public SuffixFormSet(TurkishSuffix suffix, String generation) {
        this.suffix = suffix;
        this.generation = generation;
        this.id = suffix.id + "_" + generation;
    }

    public Iterable<SuffixFormSet> getSuccessors() {
        return successors;
    }

    public boolean containsForm(SuffixForm form) {
        return forms.contains(form);
    }

    public SuffixFormSet succ(SuffixFormSet... sets) {
        this.successors.addAll(Arrays.asList(sets));
        return this;
    }

    public SuffixFormSet succ(Iterable<SuffixFormSet> it) {
        for (SuffixFormSet suff : it)
            successors.add(suff);
        return this;
    }

    public SuffixFormSet succ(SuffixFormSet[]... sets) {
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

    public String getId() {
        return id;
    }

    public void addForm(SuffixForm form) {
        forms.add(form);
    }

    public Iterable<SuffixForm> getFormIterator() {
        return forms;
    }

    public SuffixForm addOrReturnExisting(SuffixForm form) {
        if (!forms.contains(form)) {
            forms.add(form);
            return form;
        }
        for (SuffixForm suffixForm : forms) {
            if (suffixForm.equals(form))
                return suffixForm;
        }
        throw new IllegalStateException("Cannot be here.");
    }

    public String toString() {
        return id;
    }
}
