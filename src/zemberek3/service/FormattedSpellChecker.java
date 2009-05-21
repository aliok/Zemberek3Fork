package zemberek3.service;

import zemberek3.cache.CharSequenceCache;
import zemberek3.generator.word.WordFormatter;
import zemberek3.structure.LetterSequence;
import zemberek3.structure.WordParseResult;
import zemberek3.service.SpellChecker;
import zemberek3.parser.word.WordParser;

import java.util.Iterator;

/**
 * A simple spell checker checks is a String is spelled correctly.
 * This spell checker checks if a word is written according to it's original writing style.
 * Such that, if a word must start with a capital letter, this will result false if given input
 * starts with a small case letter.
 */
public class FormattedSpellChecker implements SpellChecker<String> {

    private final WordParser<LetterSequence> parser;
    private final CharSequenceCache<String> cache;
    private final WordFormatter wordFormatter;

    public FormattedSpellChecker(
            WordParser<LetterSequence> parser,
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
