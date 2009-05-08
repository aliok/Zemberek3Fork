package zemberek3.repository.stem;

import zemberek3.structure.Stem;
import zemberek3.structure.LetterSequence;
import zemberek3.comparators.CharSequenceComparator;

import java.util.List;
import java.util.Iterator;

/**
 * Finds stems which appears in the beginning of a CharSequence.
 */
public class PrefixStemProvider implements StemProvider<LetterSequence> {

    StemRepository<LetterSequence> stemRepository;
    CharSequenceComparator<LetterSequence> charSequenceComparator;

    public PrefixStemProvider(StemRepository<LetterSequence> stemRepository,
                            CharSequenceComparator<LetterSequence> charSequenceComparator) {
        this.stemRepository = stemRepository;
        this.charSequenceComparator = charSequenceComparator;
    }

    public List<Stem> find(LetterSequence word) {
        return null;
    }

    public Iterator<Stem> findAndIterate(LetterSequence word) {
        return null;
    }
}
