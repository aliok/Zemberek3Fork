package zemberek3.repository.stem;

import zemberek3.structure.Stem;
import zemberek3.structure.LetterSequence;

import java.util.List;
import java.util.Collections;
import java.util.Iterator;

/**
 * This provides all possible stems for a given LetterSequence. This is useful for
 * forcing a Word parser to parse a word which does not have a known stem.
 */
public class UnknownStemProvider implements StemProvider<LetterSequence> {

    public List<Stem> find(LetterSequence word) {
       return Collections.emptyList();
    }

    public Iterator<Stem> findAndIterate(LetterSequence word) {
        return null;
    }
}
