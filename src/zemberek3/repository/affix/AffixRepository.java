package zemberek3.repository.affix;

import zemberek3.structure.Affix;

public interface AffixRepository<T extends Affix> {
    T getAffixByName(String name);
}
