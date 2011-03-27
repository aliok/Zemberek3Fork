package zemberek3.lexicon;

import java.util.HashMap;
import java.util.Map;

/**
 * LexicoGraph holds a graph structure that represents a complete Lexicon of the language. This graph cane be used
 * for generating parser and generator mechanisms.
 */
public class LexiconGraph {

    Map<String, SuffixFormSet> suffixFormSetMap = new HashMap<String, SuffixFormSet>();

    public boolean addFormSet(SuffixFormSet formSet) {
        if (suffixFormSetMap.containsKey(formSet.getId()))
            return false;
        suffixFormSetMap.put(formSet.getId(), formSet);
        return true;
    }

    public SuffixFormSet getSuffixFormsetById(String id) {
        return suffixFormSetMap.get(id);
    }

}
