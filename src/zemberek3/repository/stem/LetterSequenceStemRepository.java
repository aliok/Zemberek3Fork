package zemberek3.repository.stem;

import zemberek3.structure.Stem;
import zemberek3.structure.LetterSequence;
import zemberek3.comparators.CharSequenceComparator;

import java.util.List;

public class LetterSequenceStemRepository implements StemRepository<LetterSequence> {

    public List<Stem> findStems(LetterSequence s) {
        return null;
    }

    public List<Stem> findStems(LetterSequence s, CharSequenceComparator<LetterSequence> comparator) {
        return null;
    }


}
