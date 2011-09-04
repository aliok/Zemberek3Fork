package zemberek3.lexicon.graph;

import zemberek3.lexicon.DictionaryItem;

public interface RootSuffixSetBuilder {
   SuffixData[] getRootSuffixSets(DictionaryItem item);
}
