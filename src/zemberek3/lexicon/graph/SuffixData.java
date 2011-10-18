package zemberek3.lexicon.graph;

import zemberek3.lexicon.SuffixForm;

import java.util.*;

public class SuffixData implements Iterable<SuffixForm> {
    private Set<SuffixForm> set = new HashSet<SuffixForm>();
    private BitSet bitSet = new BitSet();

    public SuffixData(Set<SuffixForm> forms) {
        this.set = forms;
        for (SuffixForm suffixForm : forms) {
            bitSet.set(suffixForm.index);
        }
    }

    public SuffixData(SuffixForm... forms) {
        add(forms);
    }

    public int size() {
        return set.size();
    }

    public Set<SuffixForm> getSet() {
        return set;
    }

    public SuffixData(SuffixData... suffixDatas) {
        add(suffixDatas);
    }

    public SuffixData() {
    }

    public boolean contains(SuffixForm form) {
        return bitSet.get(form.index);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public SuffixData clear() {
        this.set.clear();
        this.bitSet.clear();
        return this;
    }

    public SuffixData add(SuffixForm... forms) {
        for (SuffixForm suffixForm : forms) {
            addForm(suffixForm);
        }
        return this;
    }

    public SuffixData add(SuffixData... datas) {
        for (SuffixData suffixData : datas) {
            for (SuffixForm suffixForm : suffixData) {
                addForm(suffixForm);
            }
        }
        return this;
    }

    public SuffixData add(Iterable<SuffixForm> it) {
        for (SuffixForm suff : it) {
            addForm(suff);
        }
        return this;
    }

    private void addForm(SuffixForm suff) {
        set.add(suff);
        bitSet.set(suff.index);
    }

    private void removeForm(SuffixForm suff) {
        set.remove(suff);
        bitSet.clear(suff.index);
    }

    public SuffixData add(SuffixForm[]... forms) {
        for (SuffixForm[] suffixArray : forms) {
            add(Arrays.asList(suffixArray));
        }
        return this;
    }

    public SuffixData remove(SuffixForm... forms) {
        for (SuffixForm set : forms) {
            removeForm(set);
        }
        return this;
    }

    public SuffixData remove(SuffixData... datas) {
        for (SuffixData data : datas) {
            remove(data.set);
        }
        return this;
    }

    public SuffixData copy() {
        return new SuffixData(set);
    }

    public SuffixData remove(Collection<SuffixForm> it) {
        for (SuffixForm suff : it) {
            removeForm(suff);
        }
        return this;
    }

    public SuffixData retain(Collection<SuffixForm> coll) {
        set.retainAll(coll);
        BitSet bset = new BitSet();
        for (SuffixForm suffixForm : coll) {
            bset.set(suffixForm.index);
        }
        bitSet.and(bset);
        return this;
    }

    public SuffixData retain(SuffixData data) {
        retain(data.set);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixData that = (SuffixData) o;

        if (!bitSet.equals(that.bitSet)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bitSet.hashCode();
    }

    @Override
    public Iterator<SuffixForm> iterator() {
        return set.iterator();
    }
}
