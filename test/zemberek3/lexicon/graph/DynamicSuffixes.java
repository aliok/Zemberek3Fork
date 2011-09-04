package zemberek3.lexicon.graph;

import com.google.common.collect.Maps;
import zemberek3.lexicon.PrimaryPos;
import zemberek3.lexicon.SuffixFormSet;
import zemberek3.lexicon.SuffixProvider;

import java.util.Map;

public class DynamicSuffixes implements Suffixes {

    DynamicSuffixProvider provider = new DynamicSuffixProvider();

    @Override
    public SuffixProvider getSuffixProvider() {
        return provider;
    }

    public void addSuffixForm(SuffixFormSet formSet) {
        provider.addForms(formSet);
    }

    public void addSuffixForms(SuffixFormSet... formSets) {
        provider.addForms(formSets);
    }

    Map<PrimaryPos, SuffixFormSet> posMap = Maps.newHashMap();

    public void addRootForPos(PrimaryPos pos, SuffixFormSet set) {
        posMap.put(pos, set);
    }

}
