package zemberek3.service;

import zemberek3.cache.CharSequenceCache;
import zemberek3.generator.word.WordFormatter;
import zemberek3.parser.word.WordParser;
import zemberek3.structure.WordParseResult;

import java.util.Iterator;

/**
 * A simple spell checker verifies if a String is written correctly.
 * This spell checker checks if a word is written according to it's original writing style.
 * Such that, if a word must start with a capital letter, this will result false if given input
 * starts with a small case letter.
 * <p>Example: input "Safiyeye" will return false because it must be written as "Safiye'ye"
 * @author afsina
 */
public class FormattedSpellChecker implements SpellChecker<String> {

    private final WordParser parser;
    private final CharSequenceCache<String> cache;
    private final WordFormatter wordFormatter;

    public FormattedSpellChecker(
            WordParser parser,
            CharSequenceCache<String> cache,
            WordFormatter wordFormatter) {
        this.parser = parser;
        this.cache = cache;
        this.wordFormatter = wordFormatter;
    }

    public boolean check(String input) {
        // check cache.
        if (cache.check(input))
            return true;

        Iterator<WordParseResult> it = parser.parseIterator(input);
        while (it.hasNext()) {
            // check if input is formatted correctly.
            if (wordFormatter.format(input, it.next()).equals(input))
                return true;
        }
        return false;
    }

}
