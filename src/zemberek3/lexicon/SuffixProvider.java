package zemberek3.lexicon;

import zemberek3.lexicon.graph.SuffixData;

import java.util.List;

public interface SuffixProvider {

    Suffix getSuffixById(String suffixId);

    List<SuffixForm> getFormsBySuffixId(String suffixId);

    Iterable<SuffixForm> getAllForms();

    SuffixData[] defineSuccessorSuffixes(DictionaryItem item);

    SuffixForm getRootSet(DictionaryItem item, SuffixData successors);

}
