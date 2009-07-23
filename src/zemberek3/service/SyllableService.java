package zemberek3.service;

import java.util.List;

public interface SyllableService {

    /**
     * Returns the syllables as a String List. if word cannot be parsed, an emty list is returned.     *
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
     * Splits a word to two pieces using a space constraint.
     * Strings is splitted from a syllable index. if <code>spaceAvaliable</code>
     * is smaller than the length of the string, it will return ["word", ""]. if it is not possible to
     * fit first syllable to the <code>spaceAvailable</code> it will return an empty array.
     * <p>Example for Turkish:
     * <p><code>("merhaba", 4) -> ["mer","haba"]</code>
     * <p><code>("merhaba", 5) -> ["merha","ba"]</code>
     * <p><code>("merhaba", 2) -> []</code>
     * <p><code>("dddaddd", 2) -> []</code>
     * <p><code>("merhaba", 8) -> ["merhaba",""]</code>
     *
     * @param input          input String.
     * @param spaceAvailable the available space
     * @return an array of two Strings or an empty array.
     */
    String[] split(String input, int spaceAvailable);
}
