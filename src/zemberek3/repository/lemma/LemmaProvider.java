package zemberek3.repository.lemma;

import zemberek3.structure.Lemma;

import java.util.Iterator;
import java.util.List;

/**
 * provides lemmas for a given char sequence. search mechanism and matching rules are defined by the implementor.
 */
public interface LemmaProvider {

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
