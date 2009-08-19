package zemberek3.repository.affix;

import zemberek3.repository.affix.turkic.AffixType;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.Affix;

public interface SuffixRepository<T extends Affix> {

    T getSuffixByType(AffixType type); 

    T getSuffixByName(String name);

    T getRootSuffix(Lemma lemma);
}
