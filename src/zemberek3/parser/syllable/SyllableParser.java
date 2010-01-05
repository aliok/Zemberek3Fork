package zemberek3.parser.syllable;

import java.util.List;

public interface SyllableParser<T extends CharSequence> {

    /**
     * returns a list of Strings representing syllables for a given input.
     * @param input input word.
     * @return list of syllables. if word cannot be parsed, an empty list is returned.
     */
    List<String> parse(T input);
}
