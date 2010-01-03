package zemberek3.repository.lemma;

import zemberek3.structure.Lemma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * provides a single lemma. this may be useful for parsing words with stems provided by user.
 * This class does not require a lemma repository.
 */
public class SingleLemmaProvider implements LemmaProvider {

    private final List<Lemma> stemList = new ArrayList<Lemma>(1);

    public SingleLemmaProvider(Lemma lemma) {
        stemList.add(lemma);
    }

    public List<Lemma> find(String word) {
        return null;
    }

    public Iterator<Lemma> findAndIterate(String word) {
        return null;
    }
}
