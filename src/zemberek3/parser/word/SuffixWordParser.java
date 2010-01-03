package zemberek3.parser.word;

import zemberek3.structure.Lemma;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.SuffixWord;
import zemberek3.structure.WordParse;
import zemberek3.structure.affix.Affix;
import zemberek3.repository.stem.StemProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An implementation of <code>WordParser</code>. Suitable for aglutinative languages with suffixes.
 */
public class SuffixWordParser implements WordParser {

    final SuffixParser<TurkicLetterSequence> suffixParser;
    final InputPreProcessor<TurkicLetterSequence> inputPreProcessor;
    final StemProvider lemmaProvider;


    public SuffixWordParser(
            SuffixParser<TurkicLetterSequence> suffixParser,
            InputPreProcessor<TurkicLetterSequence> inputPreProcessor,
            StemProvider lemmaProvider) {
        this.suffixParser = suffixParser;
        this.inputPreProcessor = inputPreProcessor;
        this.lemmaProvider = lemmaProvider;
    }

    public List<WordParse> parse(CharSequence input) {
        // sanitize input.
        final TurkicLetterSequence processed = inputPreProcessor.processForParse(input);

        // find lemma candidates.
        List<Lemma> lemmas = lemmaProvider.find(processed.toString());
        if (lemmas.size() == 0)
            return Collections.emptyList();

        // get suffix parse results.
        List<WordParse> wordParses = new ArrayList<WordParse>();
        for (Lemma lemma : lemmas) {
            // process the input for lemma if necessary.
            final TurkicLetterSequence processedForStem = inputPreProcessor.modifyForStem(processed, lemma);
            // get suffix parse results and form Words.
            Iterator<List<Affix>> suffixParseIterator = suffixParser.parseIterator(processedForStem, lemma);
            while (suffixParseIterator.hasNext()) {
                wordParses.add(new SuffixWord(lemma, suffixParseIterator.next()));
            }
        }
        return wordParses;
    }

    public Iterator<WordParse> parseIterator(CharSequence input) {
        return new ParseResultIterator(inputPreProcessor.processForParse(input));
    }

    private class ParseResultIterator implements Iterator<WordParse> {

        private final TurkicLetterSequence input;
        private final Iterator<Lemma> stemIterator;
        private Iterator<List<Affix>> suffixParseIterator;
        private Lemma currentLemma;

        private ParseResultIterator(TurkicLetterSequence input) {
            this.input = input;
            stemIterator = lemmaProvider.findAndIterate(input.toString());
            suffixParseIterator = getSuffixIterator();
        }

        private Iterator<List<Affix>> getSuffixIterator() {
            if (stemIterator.hasNext()) {
                currentLemma = stemIterator.next();
                // process the input for lemma if necessary.
                final TurkicLetterSequence processedForStem = inputPreProcessor.modifyForStem(input, currentLemma);
                return suffixParser.parseIterator(processedForStem, currentLemma);
            } else {
                return null;
            }
        }

        public boolean hasNext() {
            if (!suffixParseIterator.hasNext()) {
                suffixParseIterator = getSuffixIterator();
                if (suffixParseIterator == null)
                    return false;
            }
            return suffixParseIterator.hasNext();
        }

        public WordParse next() {
            return new SuffixWord(currentLemma, suffixParseIterator.next());
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }
    }

}
