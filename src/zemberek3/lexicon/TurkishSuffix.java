package zemberek3.lexicon;

import java.util.*;

public class TurkishSuffix {

    String id;
    List<TurkishSuffix> successors = new ArrayList<TurkishSuffix>();
    List<SuffixFormSet> formSets = new ArrayList<SuffixFormSet>();
    Map<String, SuffixFormSet> nodeMap = new HashMap<String, SuffixFormSet>();

    public TurkishSuffix(String id) {
        this.id = id;
    }

    public TurkishSuffix addSuccessors(TurkishSuffix... suffix) {
        this.successors.addAll(Arrays.asList(suffix));
        return this;
    }

    public TurkishSuffix addSuccessors(TurkishSuffix[]... suffixArrays) {
        for (TurkishSuffix[] suffixArray : suffixArrays) {
            this.successors.addAll(Arrays.asList(suffixArray));
        }
        return this;
    }

    public TurkishSuffix addNode(SuffixFormSet... sets) {
        this.formSets.addAll(Arrays.asList(sets));
        for (SuffixFormSet set : sets) {
            nodeMap.put(set.getId(), set);
        }
        return this;
    }

    @Override
    public String toString() {
        return id;
    }
}
