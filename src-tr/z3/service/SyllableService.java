package z3.service;

import java.util.List;

/**
 * Provides syllable related operations.
 */
public interface SyllableService {

    /**
     * Returns the syllables as a String List. if word cannot be parsed, an emty list is returned.
     * <p>Example for Turkish:
     * <p><code>("merhaba") -> ["mer","ha","ba"]</code>
     * <p><code>("mr") -> []</code>
     * <p><code>("al") -> ["al"]</code>
     *
     * @param input input string.
     * @return syllables as string list. if there is no syllables, an empty list.
     */
    List<String> syllables(String input);

    /**
     * Finds the splitting index of a word for a space constraint.
     * if <code>spaceAvaliable</code>
     * is smaller than the length of the string, it will return word's length. if it is not possible to
     * fit first syllable to the <code>spaceAvailable</code> it will return -1.
     * <p>Example for Turkish:
     * <p><code>("merhaba", 4) -> 3 ["mer-haba"]</code>
     * <p><code>("merhaba", 6) -> 5 ["merha-ba"]</code>
     * <p><code>("merhaba", 2) -> -1 []</code>
     * <p><code>("dddaddd", 2) -> -1 []</code>
     * <p><code>("merhaba", 8) -> 7 ["merhaba"]</code>
     * @param input          input String.
     * @param spaceAvailable the available space
     * @return an integer.
     *
     */
    int splitIndex(String input, int spaceAvailable);
}
