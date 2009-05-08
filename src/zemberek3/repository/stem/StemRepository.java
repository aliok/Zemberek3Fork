package zemberek3.repository.stem;

import zemberek3.structure.Stem;
import zemberek3.comparators.CharSequenceComparator;

import java.util.List;
import java.util.Iterator;

public interface StemRepository<T extends CharSequence> {

    StemRepository add(Stem s);

    StemRepository remove(Stem s);

    Iterator<Stem> iterator();

}
