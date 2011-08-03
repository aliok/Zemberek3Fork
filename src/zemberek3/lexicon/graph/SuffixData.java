package zemberek3.lexicon.graph;

import zemberek3.lexicon.SuffixFormSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SuffixData {
    public Set<SuffixFormSet> set = new HashSet<SuffixFormSet>();

    public SuffixData(Set<SuffixFormSet> set) {
        this.set = set;
    }

    public SuffixData(SuffixFormSet... set) {
        this.set.addAll(Arrays.asList(set));
    }

    public SuffixData clear() {
        this.set.clear();
        return this;
    }

    public SuffixData add(SuffixFormSet... sets) {
        this.set.addAll(Arrays.asList(sets));
        return this;
    }

    public SuffixData add(Iterable<SuffixFormSet> it) {
        for (SuffixFormSet suff : it)
            set.add(suff);
        return this;
    }

    public SuffixData add(SuffixFormSet[]... sets) {
        for (SuffixFormSet[] suffixArray : sets) {
            this.set.addAll(Arrays.asList(suffixArray));
        }
        return this;
    }

    public SuffixData remove(SuffixFormSet... sets) {
        for (SuffixFormSet set : sets) {
            this.set.remove(set);
        }
        return this;
    }

    public SuffixData remove(Iterable<SuffixFormSet> it) {
        for (SuffixFormSet suff : it)
            set.remove(suff);
        return this;
    }

    public SuffixData retain(Collection<SuffixFormSet> coll) {
        set.retainAll(coll);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixData that = (SuffixData) o;

        if (!set.equals(that.set)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }
}