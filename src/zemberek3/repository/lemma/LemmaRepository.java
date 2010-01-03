package zemberek3.repository.lemma;

import zemberek3.structure.Lemma;

import java.util.Iterator;

public interface LemmaRepository {

    LemmaRepository add(Lemma s);

    Iterator<Lemma> iterator();

}
