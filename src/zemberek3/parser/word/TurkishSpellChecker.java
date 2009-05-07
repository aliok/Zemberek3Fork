package zemberek3.parser.word;

import zemberek3.structure.Word;
import zemberek3.generator.word.WordFormatter;
import zemberek3.cache.CharSequenceCache;

import java.util.Iterator;

public class TurkishSpellChecker implements SpellChecker {

    final WordParser parser;
    final CharSequenceCache cache;
    final WordFormatter wordFormatter;

    public TurkishSpellChecker(
            WordParser parser,
            CharSequenceCache cache,
            WordFormatter wordFormatter) {
        this.parser = parser;
        this.cache = cache;
        this.wordFormatter = wordFormatter;
    }

    public boolean check(CharSequence input) {
        if (cache.check(input))
            return true;
        Iterator<Word> it = parser.parseAndIterate(input);
        while(it.hasNext()) {
           if(wordFormatter.format(it.next()).equals(input))
              return true;
        }
        return false;
    }

    public boolean checkUnformatted(CharSequence input) {
        return cache.check(input) || parser.parseAndIterate(input).hasNext();
    }
}
