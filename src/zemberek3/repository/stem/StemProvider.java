package zemberek3.repository.stem;

import zemberek3.structure.Stem;

import java.util.Iterator;
import java.util.List;

/**
 * provides stems for a given char sequence. search rules are defined by the implementor.
 */
public interface StemProvider<T extends CharSequence> {

    List<Stem> find(T word);

    Iterator<Stem> findAndIterate(T word);
}
