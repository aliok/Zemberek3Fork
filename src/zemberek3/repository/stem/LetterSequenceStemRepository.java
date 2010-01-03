package zemberek3.repository.stem;

import zemberek3.structure.Lemma;

import java.util.Iterator;

public class LetterSequenceStemRepository implements StemRepository {

    public StemRepository add(Lemma s) {
        return this;
    }

    public Iterator<Lemma> iterator() {
        return null;
    }
}
