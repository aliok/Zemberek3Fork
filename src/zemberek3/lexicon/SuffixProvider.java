package zemberek3.lexicon;

import java.util.List;

public interface SuffixProvider {

    TurkishSuffix getSuffixById(String suffixId);

    List<SuffixFormSet> getFormsBySuffixId(String suffixId);

    SuffixFormSet getSuffixFormSetById(String suffixSetId);

    Iterable<SuffixFormSet> getAllFormSets();

}
