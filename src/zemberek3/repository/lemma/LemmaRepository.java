package zemberek3.repository.lemma;

import zemberek3.structure.Lemma;

import java.util.Iterator;

/**
 * An interface for a lemma storage.
 */
public interface LemmaRepository {

    /**
     * add a Lemma to the repository.
     * @param s lemma to add.
     * @return Lemma repository for chaining.
     */
    LemmaRepository add(Lemma s);

    /**
     * provides a LEmma iterator for Lemma. This is useful when early return is required.
     * @return a Lemma iterator.
     */
    Iterator<Lemma> iterator();
}
