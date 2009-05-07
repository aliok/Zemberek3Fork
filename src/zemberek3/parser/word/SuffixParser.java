package zemberek3.parser.word;

import zemberek3.structure.Affix;
import zemberek3.structure.Stem;

import java.util.List;
import java.util.Iterator;

/**
 * Tries to find possible suffix sequences for a given input and Stem.
 */
public interface SuffixParser<T extends CharSequence> {

    /**
     * parses the input for a given stem. it finds all possible affix combinations.
     *
     * @param input input a CharSequence implementation.
     * @param stem  a Stem to use in the parsing.
     * @return List of Words contianing the given Stem and possible affix list combinations.
     *         if parsing is not successfull, and empty list is returned.
     */
    List<List<Affix>> parse(T input, Stem stem);

    /**
     * parses the input for a given stem. it finds all possible affix combinations.
     *
     * @param input input a CharSequence implementation.
     * @param stem  a Stem to use in the parsing.
     * @return List of Words contianing the given Stem and possible affix list combinations.
     *         if parsing is not successfull, and empty list is returned.
     */
    Iterator<List<Affix>> parseAndIterate(T input, Stem stem);
}
