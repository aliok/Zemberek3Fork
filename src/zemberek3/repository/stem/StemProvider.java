package zemberek3.repository.stem;

import zemberek3.structure.Stem;

import java.util.Iterator;
import java.util.List;

/**
 * provides stems for a given char sequence. search rules are defined by the implementor.
 */
public interface StemProvider<T extends CharSequence> {

    /**
     * Finds all items matching with <code>word</code>
     * @param word input for searching
     * @return all Stem's matching with the input word.
     */
    List<Stem> find(T word);

    Iterator<Stem> findAndIterate(T word);
}
