package zemberek3.repository.affix;

import zemberek3.structure.affix.TurkicSuffix;

import java.util.List;

public interface SuffixLoader {
    List<TurkicSuffix> loadAll();
}
