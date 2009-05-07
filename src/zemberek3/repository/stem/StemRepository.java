package zemberek3.repository.stem;

import zemberek3.structure.Stem;
import zemberek3.comparators.CharSequenceComparator;

import java.util.List;

public interface StemRepository<T extends CharSequence> {

    List<Stem> findStems(T s);

    List<Stem> findStems(T s, CharSequenceComparator<T> comparator);
}
