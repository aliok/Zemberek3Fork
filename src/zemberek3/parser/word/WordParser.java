package zemberek3.parser.word;

import zemberek3.structure.WordParse;

import java.util.Iterator;
import java.util.List;

/**
 * it parses the input sequence. Finding stem and affixes.
 */
public interface WordParser {

    /**
     * finds a list of Words matching the input.
     * @param input the input CharSequence.
     * @return possible parse results as a Word list.
     */
    List<WordParse> parse(CharSequence input);

    /**
     * provides an iterator for the parse results of a given input CharSequence.
     * @param input input CharSequence
     * @return an Iterator that iterates through parse results.
     */
    Iterator<WordParse> parseIterator(CharSequence input);

}
