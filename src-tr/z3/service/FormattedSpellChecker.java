package z3.service;

import z3.generator.word.WordFormatter;
import z3.parser.word.WordParser;
import z3.structure.TurkicWordParse;

import java.util.Iterator;

/**
 * A spell checker verifies if a String is written correctly.
 * This spell checker checks if a word is written according to it's original writing style.
 * Such that, if a word must start with a capital letter, this will result false if given input
 * starts with a small case letter.
 * <p>Example: input "Safiyeye" will return false because it must be written as "Safiye'ye"
 * @author afsina
 */
public class FormattedSpellChecker implements SpellChecker<String> {

    private final WordParser parser;
    private final WordFormatter wordFormatter;

    public FormattedSpellChecker(
            WordParser parser,
            WordFormatter wordFormatter) {
        this.parser = parser;
        this.wordFormatter = wordFormatter;
    }

    /**
     * returns true if input is spelled correctly.
     * @param input input word.
     * @return if word is spelled coreectly or not
     */
    public boolean check(String input) {

        Iterator<TurkicWordParse> it = parser.parseIterator(input);
        while (it.hasNext()) {
            // check if input is formatted correctly.
            if (wordFormatter.format(input, it.next()).equals(input))
                return true;
        }
        return false;
    }

}
