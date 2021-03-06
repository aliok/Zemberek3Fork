package z3.parser.word;

import z3.structure.TurkicWordParse;

import java.util.Iterator;
import java.util.List;

/**
 * it parses the input sequence. Finding lemma and affixes.
 */
public interface WordParser {

    /**
     * finds a list of Words matching the input.
     * @param input the input CharSequence.
     * @return possible parse results as a Word list.
     */
    List<TurkicWordParse> parse(CharSequence input);

    /**
     * provides an iterator for the parse results of a given input CharSequence.
     * @param input input CharSequence
     * @return an Iterator that iterates through parse results.
     */
    Iterator<TurkicWordParse> parseIterator(CharSequence input);

}
