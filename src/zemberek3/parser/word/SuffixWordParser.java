package zemberek3.parser.word;

import zemberek3.repository.stem.StemProvider;
import zemberek3.structure.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An implementation of Word Parser. Suitable for aglutinative languages with suffixes.
 */
public class SuffixWordParser implements WordParser {

    final SuffixParser<LetterSequence> suffixParser;
    final InputPreProcessor<LetterSequence> inputPreProcessor;
    final StemProvider<LetterSequence> stemProvider;


    public SuffixWordParser(
            SuffixParser<LetterSequence> suffixParser,
            InputPreProcessor<LetterSequence> inputPreProcessor,
            StemProvider<LetterSequence> stemProvider) {
        this.suffixParser = suffixParser;
        this.inputPreProcessor = inputPreProcessor;
        this.stemProvider = stemProvider;
    }

    public List<Word> parse(CharSequence input) {
        // sanitize input.
        final LetterSequence processed = inputPreProcessor.processForParse(input);

        // fin stem candiadtes.
        List<Stem> stems = stemProvider.find(processed);
        if (stems.size() == 0)
            return Collections.emptyList();

        // get suffix parse results.
        List<Word> wordParseResults = new ArrayList<Word>();
        for (Stem stem : stems) {
            // process the input for stem if necessary.
            final LetterSequence processedForStem = inputPreProcessor.modifyForStem(processed, stem);
            // get suffix parse results and form Words.
            Iterator<List<Affix>> suffixParseIterator = suffixParser.parseIterator(processedForStem, stem);
            while (suffixParseIterator.hasNext()) {
                wordParseResults.add(new SuffixWord(stem, suffixParseIterator.next()));
            }
        }
        return wordParseResults;
    }

    public Iterator<Word> parseIterator(CharSequence input) {
        return new ParseResultIterator(inputPreProcessor.processForParse(input));
    }

    private class ParseResultIterator implements Iterator<Word> {

        private final LetterSequence input;
        private final Iterator<Stem> stemIterator;
        private Iterator<List<Affix>> suffixParseIterator;
        private Stem currentStem;

        private ParseResultIterator(LetterSequence input) {
            this.input = input;
            stemIterator = stemProvider.findAndIterate(input);
            suffixParseIterator = getSuffixIterator();
        }

        private Iterator<List<Affix>> getSuffixIterator() {
            if (stemIterator.hasNext()) {
                currentStem = stemIterator.next();
                // process the input for stem if necessary.
                final LetterSequence processedForStem = inputPreProcessor.modifyForStem(input, currentStem);
                return suffixParser.parseIterator(processedForStem, currentStem);
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

        public Word next() {
            return new SuffixWord(currentStem, suffixParseIterator.next());
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }
    }

}
