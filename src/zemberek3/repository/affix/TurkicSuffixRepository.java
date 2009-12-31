package zemberek3.repository.affix;

import zemberek3.repository.affix.turkic.AffixType;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.Affix;
import zemberek3.structure.affix.TurkicSuffix;

import java.util.HashSet;
import java.util.Set;

public class TurkicSuffixRepository implements SuffixRepository<TurkicSuffix> {

    final SuffixLoader loader;
    final Set<Affix> affixes;

    public TurkicSuffixRepository(SuffixLoader loader) {
        this.loader = loader;
        affixes = new HashSet<Affix>(loader.loadAll());
    }

    public TurkicSuffix getSuffixByType(AffixType type) {
        return null; 
    }

    public TurkicSuffix getSuffixByName(String name) {
        return null;
    }

    public TurkicSuffix getRootSuffix(Lemma lemma) {
        return null;
    }
}
