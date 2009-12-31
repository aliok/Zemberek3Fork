package zemberek3.parser.word;

import java.util.List;

public interface WordStemmer<T extends CharSequence> {

    List<String> findStems(T word);

}
