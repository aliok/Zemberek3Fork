package zemberek3.repository.affix;

import zemberek3.structure.affix.Affix;
import zemberek3.structure.Stem;

public interface SuffixRepository<T extends Affix> {
    T getSuffixByName(String name);

    T getRootSuffix(Stem stem);
}
