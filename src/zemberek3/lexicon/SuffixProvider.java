package zemberek3.lexicon;

import java.util.List;

public interface SuffixProvider {

    Suffix getSuffixById(String suffixId);

    List<SuffixFormSet> getFormsBySuffixId(String suffixId);

    SuffixFormSet getFormById(String suffixSetId);

    Iterable<SuffixFormSet> getAllForms();

}
