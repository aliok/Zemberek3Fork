package zemberek3.repository.stem;

import zemberek3.structure.Lemma;

import java.util.Iterator;

public interface StemRepository {

    StemRepository add(Lemma s);

    Iterator<Lemma> iterator();

}
