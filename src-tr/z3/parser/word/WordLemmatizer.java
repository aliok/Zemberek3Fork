package z3.parser.word;

import java.util.List;

/**
 * Finds all possible lemmas for a given input CharSequence.
 */
public interface WordLemmatizer<T extends CharSequence> {

    List<String> findLemmas(T input);
}
