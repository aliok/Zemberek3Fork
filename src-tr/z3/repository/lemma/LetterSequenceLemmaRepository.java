package z3.repository.lemma;

import zemberek3.structure.Lemma;

import java.util.Iterator;

public class LetterSequenceLemmaRepository implements LemmaRepository {

    public LemmaRepository add(Lemma s) {
        return this;
    }

    public Iterator<Lemma> iterator() {
        return null;
    }
}
