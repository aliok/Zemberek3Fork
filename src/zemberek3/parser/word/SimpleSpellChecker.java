package zemberek3.parser.word;

import zemberek3.structure.Word;
import zemberek3.generator.word.WordFormatter;
import zemberek3.cache.CharSequenceCache;

import java.util.Iterator;

public class SimpleSpellChecker implements SpellChecker<String> {

    private final WordParser parser;
    private final CharSequenceCache<String> cache;
    private final WordFormatter wordFormatter;

    public SimpleSpellChecker(
            WordParser parser,
            CharSequenceCache<String> cache,
            WordFormatter wordFormatter) {
        this.parser = parser;
        this.cache = cache;
        this.wordFormatter = wordFormatter;
    }

    public boolean check(String input) {
        if (cache.check(input))
            return true;
        Iterator<Word> it = parser.parseIterator(input);
        while (it.hasNext()) {
            if (wordFormatter.format(input, it.next()).equals(input))
                return true;
        }
        return false;
    }

    public boolean checkUnformatted(String input) {
        return cache.check(input) || parser.parseIterator(input).hasNext();
    }
}
