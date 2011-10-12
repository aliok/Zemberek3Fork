package zemberek3.lexicon.graph;

import zemberek3.lexicon.SuffixFormSet;

import java.util.*;

public class SuffixData implements Iterable<SuffixFormSet> {
    public Set<SuffixFormSet> set = new HashSet<SuffixFormSet>();

    public SuffixData(Set<SuffixFormSet> set) {
        this.set = set;
    }

    public SuffixData(SuffixFormSet... set) {
        this.set.addAll(Arrays.asList(set));
    }

    public SuffixData(SuffixData... suffixDatas) {
        for (SuffixData suffixData : suffixDatas) {
            this.set.addAll(suffixData.set);
        }
    }

    public SuffixData() {
    }

    public boolean contains(SuffixFormSet suffSet) {
        return set.contains(suffSet);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public SuffixData clear() {
        this.set.clear();
        return this;
    }

    public SuffixData add(SuffixFormSet... sets) {
        this.set.addAll(Arrays.asList(sets));
        return this;
    }

    public SuffixData add(SuffixData... datas) {
        for (SuffixData suffixData : datas) {
            this.set.addAll(suffixData.set);
        }
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

    public SuffixData remove(SuffixData... datas) {
        for (SuffixData data : datas) {
            this.set.removeAll(data.set);
        }
        return this;
    }

    public SuffixData copy() {
        return new SuffixData(set);
    }

    public SuffixData remove(Collection<SuffixFormSet> it) {
        for (SuffixFormSet suff : it) {
            set.remove(suff);
        }
        return this;
    }

    public SuffixData retain(Collection<SuffixFormSet> coll) {
        set.retainAll(coll);
        return this;
    }

    public SuffixData retain(SuffixData data) {
        set.retainAll(data.set);
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
        int result = 31;
        for (SuffixFormSet successor : set) {
            result = 31 * result + successor.getId().hashCode();
        }
        return result;
    }

    @Override
    public Iterator<SuffixFormSet> iterator() {
        return set.iterator();
    }
}
