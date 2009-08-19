package zemberek3.repository.stem;

import zemberek3.comparators.CharSequenceComparator;
import zemberek3.structure.LetterSequence;
import zemberek3.structure.Lemma;

import java.util.Iterator;
import java.util.List;

/**
 * Finds stems which appears in the beginning of a CharSequence.
 */
public class PrefixStemProvider implements StemProvider<LetterSequence> {

    StemRepository<LetterSequence> stemRepository;
    CharSequenceComparator<LetterSequence> charSequenceComparator;

    public PrefixStemProvider(
            StemRepository<LetterSequence> stemRepository,
            CharSequenceComparator<LetterSequence> charSequenceComparator) {
        this.stemRepository = stemRepository;
        this.charSequenceComparator = charSequenceComparator;
    }

    public List<Lemma> find(LetterSequence word) {
        return null;
    }

    public Iterator<Lemma> findAndIterate(LetterSequence word) {
        return null;
    }
}
