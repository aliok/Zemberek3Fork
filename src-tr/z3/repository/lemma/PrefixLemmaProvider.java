package z3.repository.lemma;

import z3.comparators.CharSequenceComparator;
import zemberek3.structure.Lemma;

import java.util.Iterator;
import java.util.List;

/**
 * Finds stems which appears in the beginning of a CharSequence.
 */
public class PrefixLemmaProvider implements LemmaProvider {

    LemmaRepository lemmaRepository;
    CharSequenceComparator<String> charSequenceComparator;

    public PrefixLemmaProvider(
            LemmaRepository lemmaRepository,
            CharSequenceComparator<String> charSequenceComparator) {
        this.lemmaRepository = lemmaRepository;
        this.charSequenceComparator = charSequenceComparator;
    }

    public List<Lemma> find(String word) {
        return null;
    }

    public Iterator<Lemma> findAndIterate(String word) {
        return null;
    }
}
