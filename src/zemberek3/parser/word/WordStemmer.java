package zemberek3.parser.word;

import zemberek3.structure.Stem;

import java.util.List;

public interface WordStemmer<T extends CharSequence> {

    List<Stem> findStems(T input);

}
