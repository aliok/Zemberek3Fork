package zemberek3.repository.affix;

import zemberek3.structure.Affix;
import zemberek3.structure.Stem;

public interface AffixRepository<T extends Affix> {
    T getAffixByName(String name);

    T getRootSuffix(Stem stem);
}
