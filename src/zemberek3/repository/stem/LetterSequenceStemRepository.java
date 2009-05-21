package zemberek3.repository.stem;

import zemberek3.structure.LetterSequence;
import zemberek3.structure.Stem;

import java.util.Iterator;

public class LetterSequenceStemRepository implements StemRepository<LetterSequence> {

    public StemRepository add(Stem s) {
        return this;
    }

    public Iterator<Stem> iterator() {
        return null;
    }
}
