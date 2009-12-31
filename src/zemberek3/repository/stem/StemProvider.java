package zemberek3.repository.stem;

import zemberek3.structure.Lemma;

import java.util.Iterator;
import java.util.List;

/**
 * provides stems for a given char sequence. search mechanism and matching rules are defined by the implementor.
 */
public interface StemProvider {

    /**
     * Finds all items matching with <code>word</code>
     * @param word input for searching
     * @return all Stem's matching with the input word.
     */
    List<Lemma> find(String word);

    /**
     * Brings an iterator over matching stems for the given <code>word</code>.
     * @param word input for searching
     * @return an iterator for matching stems.
     */
    Iterator<Lemma> findAndIterate(String word);
}
