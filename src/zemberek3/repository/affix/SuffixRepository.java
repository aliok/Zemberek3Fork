package zemberek3.repository.affix;

import zemberek3.structure.affix.TurkicSuffixType;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.TurkicSuffix;

public interface SuffixRepository {

    TurkicSuffix getSuffixByType(TurkicSuffixType type);

    TurkicSuffix getSuffixByName(String name);

    TurkicSuffix getRootSuffix(Lemma lemma);
}
