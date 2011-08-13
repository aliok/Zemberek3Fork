package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import zemberek3.lexicon.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamicSuffixes implements Suffixes {

    public static Suffix NounRoot = new Suffix("NounRoot");
    public static SuffixFormSet Noun_Main = new SuffixFormSet("Noun_Main", NounRoot, "");
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

    private class DynamicSuffixProvider implements SuffixProvider {

        Set<SuffixFormSet> forms = Sets.newHashSet();
        Map<String, SuffixFormSet> formSetLookup = Maps.newHashMap();
        Map<String, Suffix> suffixLookup = Maps.newHashMap();
        ArrayListMultimap<String, SuffixFormSet> formsPerSuffix = ArrayListMultimap.create(100, 2);

        public Suffix getSuffixById(String suffixId) {
            return suffixLookup.get(suffixId);
        }

        public List<SuffixFormSet> getFormsBySuffixId(String suffixId) {
            return formsPerSuffix.get(suffixId);
        }

        public SuffixFormSet getFormById(String suffixSetId) {
            return formSetLookup.get(suffixSetId);
        }

        public Iterable<SuffixFormSet> getAllForms() {
            return forms;
        }

        @Override
        public SuffixFormSet getRootForm(DictionaryItem item) {
            switch (item.primaryPos) {
                case Noun:
                    return Noun_Main;
                default:
                    return DynamicSuffixes.Noun_Main;
            }
        }

        void addForms(SuffixFormSet... setz) {

            for (SuffixFormSet suffixFormSet : setz) {
                if (forms.contains(suffixFormSet))
                    continue;
                forms.add(suffixFormSet);
                formSetLookup.put(suffixFormSet.id, suffixFormSet);
                suffixLookup.put(suffixFormSet.getSuffix().id, suffixFormSet.getSuffix());
                formsPerSuffix.put(suffixFormSet.getSuffix().id, suffixFormSet);
            }
        }

        public Collection<SuffixFormSet> getSets(String... ids) {
            Set<SuffixFormSet> sets = Sets.newHashSet();
            for (String id : ids) {
                sets.add(formSetLookup.get(id));
            }
            return sets;
        }
    }
}
