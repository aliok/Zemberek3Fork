package zemberek3.repository.stem;

import zemberek3.comparators.CharSequenceComparator;
import zemberek3.structure.Lemma;

import java.util.Iterator;
import java.util.List;

/**
 * Finds stems which appears in the beginning of a CharSequence.
 */
public class PrefixStemProvider implements StemProvider {

    StemRepository stemRepository;
    CharSequenceComparator<String> charSequenceComparator;

    public PrefixStemProvider(
            StemRepository stemRepository,
            CharSequenceComparator<String> charSequenceComparator) {
        this.stemRepository = stemRepository;
        this.charSequenceComparator = charSequenceComparator;
    }

    public List<Lemma> find(String word) {
        return null;
    }

    public Iterator<Lemma> findAndIterate(String word) {
        return null;
    }
}
