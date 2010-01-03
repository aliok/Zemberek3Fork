package zemberek3.repository.affix;

import zemberek3.repository.affix.turkic.AffixType;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.TurkicSuffix;

public interface SuffixRepository {

    TurkicSuffix getSuffixByType(AffixType type);

    TurkicSuffix getSuffixByName(String name);

    TurkicSuffix getRootSuffix(Lemma lemma);
}
