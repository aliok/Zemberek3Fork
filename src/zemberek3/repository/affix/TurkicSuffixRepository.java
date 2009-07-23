package zemberek3.repository.affix;

import zemberek3.structure.Stem;
import zemberek3.structure.affix.TurkicSuffix;
import zemberek3.structure.affix.Affix;

import java.util.Set;
import java.util.HashSet;

public class TurkicSuffixRepository implements SuffixRepository<TurkicSuffix> {

    final SuffixLoader loader;
    final Set<Affix> affixes;

    public TurkicSuffixRepository(SuffixLoader loader) {
        this.loader = loader;
        affixes = new HashSet<Affix>(loader.loadAll());
    }

    public TurkicSuffix getSuffixByName(String name) {
        return null;
    }

    public TurkicSuffix getRootSuffix(Stem stem) {
        return null;
    }
}
