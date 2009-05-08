package zemberek3.cache;

import java.util.Set;
import java.util.HashSet;

/**
 * a simple Set based word list. containing 5000 most commonly used words.
 */
public class MostCommonWordList implements CharSequenceCache<String> {

    Set<String> words = new HashSet<String>(5000);

    public boolean check(String word) {
        return words.contains(word);
    }

    public void flush() {
        // do nothing.
    }
}
