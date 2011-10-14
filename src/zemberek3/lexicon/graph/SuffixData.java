package zemberek3.lexicon.graph;

import zemberek3.lexicon.SuffixForm;

import java.util.*;

public class SuffixData implements Iterable<SuffixForm> {
    public Set<SuffixForm> set = new HashSet<SuffixForm>();

    public SuffixData(Set<SuffixForm> set) {
        this.set = set;
    }

    public SuffixData(SuffixForm... set) {
        this.set.addAll(Arrays.asList(set));
    }

    public SuffixData(SuffixData... suffixDatas) {
        for (SuffixData suffixData : suffixDatas) {
            this.set.addAll(suffixData.set);
        }
    }

    public SuffixData() {
    }

    public boolean contains(SuffixForm suffSet) {
        return set.contains(suffSet);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public SuffixData clear() {
        this.set.clear();
        return this;
    }

    public SuffixData add(SuffixForm... sets) {
        this.set.addAll(Arrays.asList(sets));
        return this;
    }

    public SuffixData add(SuffixData... datas) {
        for (SuffixData suffixData : datas) {
            this.set.addAll(suffixData.set);
        }
        return this;
    }

    public SuffixData add(Iterable<SuffixForm> it) {
        for (SuffixForm suff : it)
            set.add(suff);
        return this;
    }

    public SuffixData add(SuffixForm[]... sets) {
        for (SuffixForm[] suffixArray : sets) {
            this.set.addAll(Arrays.asList(suffixArray));
        }
        return this;
    }

    public SuffixData remove(SuffixForm... sets) {
        for (SuffixForm set : sets) {
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

    public SuffixData remove(Collection<SuffixForm> it) {
        for (SuffixForm suff : it) {
            set.remove(suff);
        }
        return this;
    }

    public SuffixData retain(Collection<SuffixForm> coll) {
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
        for (SuffixForm successor : set) {
            result = 31 * result + successor.getId().hashCode();
        }
        return result;
    }

    @Override
    public Iterator<SuffixForm> iterator() {
        return set.iterator();
    }
}
