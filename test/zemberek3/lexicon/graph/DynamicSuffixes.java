package zemberek3.lexicon.graph;

import com.google.common.collect.Maps;
import zemberek3.lexicon.PrimaryPos;
import zemberek3.lexicon.SuffixForm;
import zemberek3.lexicon.SuffixProvider;

import java.util.Map;

public class DynamicSuffixes implements Suffixes {

    DynamicSuffixProvider provider = new DynamicSuffixProvider();

    @Override
    public SuffixProvider getSuffixProvider() {
        return provider;
    }

    public void addSuffixForm(SuffixForm formSet) {
        provider.addForms(formSet);
    }

    public void addSuffixForms(SuffixForm... formSets) {
        provider.addForms(formSets);
    }

    Map<PrimaryPos, SuffixForm> posMap = Maps.newHashMap();

    public void addRootForPos(PrimaryPos pos, SuffixForm set) {
        posMap.put(pos, set);
    }

}
