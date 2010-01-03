package zemberek3.repository.affix;

import zemberek3.structure.affix.TurkicSuffixType;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.TurkicSuffix;

import java.util.HashSet;
import java.util.Set;

public class TurkicSuffixRepository implements SuffixRepository {

    final SuffixLoader loader;
    final Set<TurkicSuffix> affixes;

    public TurkicSuffixRepository(SuffixLoader loader) {
        this.loader = loader;
        affixes = new HashSet<TurkicSuffix>(loader.loadAll());
    }

    public TurkicSuffix getSuffixByType(TurkicSuffixType type) {
        return null; 
    }

    public TurkicSuffix getSuffixByName(String name) {
        return null;
    }

    public TurkicSuffix getRootSuffix(Lemma lemma) {
        return null;
    }
}
