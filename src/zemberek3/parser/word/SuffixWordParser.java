package zemberek3.parser.word;

import zemberek3.repository.stem.StemProvider;
import zemberek3.structure.*;
import zemberek3.structure.affix.Affix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An implementation of <code>WordParser</code>. Suitable for aglutinative languages with suffixes.
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

    public List<WordParseResult> parse(CharSequence input) {
        // sanitize input.
        final LetterSequence processed = inputPreProcessor.processForParse(input);

        // find stem candiadtes.
        List<Stem> stems = stemProvider.find(processed);
        if (stems.size() == 0)
            return Collections.emptyList();

        // get suffix parse results.
        List<WordParseResult> wordParseResults = new ArrayList<WordParseResult>();
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

    public Iterator<WordParseResult> parseIterator(CharSequence input) {
        return new ParseResultIterator(inputPreProcessor.processForParse(input));
    }

    private class ParseResultIterator implements Iterator<WordParseResult> {

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

        public WordParseResult next() {
            return new SuffixWord(currentStem, suffixParseIterator.next());
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }
    }

}
