package zemberek3.lexicon;

import zemberek3.lexicon.graph.RootSuffixSetBuilder;
import zemberek3.lexicon.graph.SuffixData;

import java.util.List;

public interface SuffixProvider {

    Suffix getSuffixById(String suffixId);

    List<SuffixFormSet> getFormsBySuffixId(String suffixId);

    Iterable<SuffixFormSet> getAllForms();

    SuffixData[] defineSuccessorSuffixes(DictionaryItem item);

    SuffixFormSet getRootSet(DictionaryItem item, SuffixData successors);

}
