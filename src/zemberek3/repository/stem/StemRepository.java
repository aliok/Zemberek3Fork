package zemberek3.repository.stem;

import zemberek3.structure.Stem;

import java.util.Iterator;

public interface StemRepository<T extends CharSequence> {

    StemRepository add(Stem s);

    Iterator<Stem> iterator();

}
