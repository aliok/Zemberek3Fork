package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixFormSet;
import zemberek3.lexicon.SuffixProvider;

import java.util.List;
import java.util.Map;

public class DynamicSuffixProvider implements SuffixProvider {

    //Set<SuffixFormSet> forms = Sets.newHashSet();
    protected Map<SuffixFormSet, SuffixFormSet> formSetLookup = Maps.newHashMap();
    protected Map<String, Suffix> suffixLookup = Maps.newHashMap();
    protected ArrayListMultimap<String, SuffixFormSet> formsPerSuffix = ArrayListMultimap.create(100, 2);
    
    public Suffix getSuffixById(String suffixId) {
        return suffixLookup.get(suffixId);
    }

    public List<SuffixFormSet> getFormsBySuffixId(String suffixId) {
        return formsPerSuffix.get(suffixId);
    }

    public Iterable<SuffixFormSet> getAllForms() {
        return formSetLookup.keySet();
    }

    @Override
    public SuffixData[] defineSuccessorSuffixes(DictionaryItem item) {
        return new SuffixData[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SuffixFormSet getRootSet(DictionaryItem item) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SuffixFormSet addAndGet(SuffixFormSet setToCopy, SuffixData successors) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    void addForms(SuffixFormSet... setz) {
        for (SuffixFormSet suffixFormSet : setz) {
            if (formSetLookup.containsKey(suffixFormSet))
                continue;
            formSetLookup.put(suffixFormSet, suffixFormSet);
            suffixLookup.put(suffixFormSet.getSuffix().id, suffixFormSet.getSuffix());
            formsPerSuffix.put(suffixFormSet.getSuffix().id, suffixFormSet);
        }
    }
}
