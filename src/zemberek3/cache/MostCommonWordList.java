package zemberek3.cache;

import java.util.Set;
import java.util.HashSet;

/**
 * a simple Set based word list. containing 5000 most commonly used words.
 */
public class MostCommonWordList implements CharSequenceCache {

    Set<CharSequence> words = new HashSet<CharSequence>(5000);

    public boolean check(CharSequence word) {
        return words.contains(word);
    }

    public void flush() {
        // do nothing.
    }
}
