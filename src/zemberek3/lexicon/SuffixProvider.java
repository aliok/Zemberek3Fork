package zemberek3.lexicon;

import zemberek3.lexicon.graph.StemNode;

import java.util.List;

public interface SuffixProvider {

    Suffix getSuffixById(String suffixId);

    List<SuffixFormSet> getFormsBySuffixId(String suffixId);

    SuffixFormSet getFormById(String suffixSetId);

    Iterable<SuffixFormSet> getAllForms();

    SuffixFormSet getRootForm(DictionaryItem item);

}
