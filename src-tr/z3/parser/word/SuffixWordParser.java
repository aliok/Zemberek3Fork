package z3.parser.word;

import z3.repository.lemma.LemmaProvider;
import z3.structure.Lemma;
import z3.structure.TurkicWordParse;
import z3.structure.affix.TurkicSuffix;
import zemberek3.structure.TurkicSeq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An implementation of <code>WordParser</code>. Suitable for aglutinative languages with suffixes.
 */
public class SuffixWordParser implements WordParser {

    final SuffixParser<TurkicSeq> suffixParser;
    final InputPreProcessor<TurkicSeq> inputPreProcessor;
    final LemmaProvider lemmaProvider;

    public SuffixWordParser(
            SuffixParser<TurkicSeq> suffixParser,
            InputPreProcessor<TurkicSeq> inputPreProcessor,
            LemmaProvider lemmaProvider) {
        this.suffixParser = suffixParser;
        this.inputPreProcessor = inputPreProcessor;
        this.lemmaProvider = lemmaProvider;
    }

    public List<TurkicWordParse> parse(CharSequence input) {
        // sanitize input.
        final TurkicSeq processed = inputPreProcessor.processForParse(input);

        // find lemma candidates.
        List<Lemma> lemmas = lemmaProvider.find(processed.toString());
        if (lemmas.size() == 0)
            return Collections.emptyList();

        // get suffix parse results.
        List<TurkicWordParse> wordParses = new ArrayList<TurkicWordParse>();
        for (Lemma lemma : lemmas) {
            // process the input for lemma if necessary.
            final TurkicSeq processedForStem = inputPreProcessor.modifyForStem(processed, lemma);
            // get suffix parse results and form Words.
            Iterator<List<TurkicSuffix>> suffixParseIterator = suffixParser.parseIterator(processedForStem, lemma);
            while (suffixParseIterator.hasNext()) {
                wordParses.add(new TurkicWordParse(lemma, suffixParseIterator.next()));
            }
        }
        return wordParses;
    }

    public Iterator<TurkicWordParse> parseIterator(CharSequence input) {
        return new ParseResultIterator(inputPreProcessor.processForParse(input));
    }

    private class ParseResultIterator implements Iterator<TurkicWordParse> {

        private final TurkicSeq input;
        private final Iterator<Lemma> stemIterator;
        private Iterator<List<TurkicSuffix>> suffixParseIterator;
        private Lemma currentLemma;

        private ParseResultIterator(TurkicSeq input) {
            this.input = input;
            stemIterator = lemmaProvider.findAndIterate(input.toString());
            suffixParseIterator = getSuffixIterator();
        }

        private Iterator<List<TurkicSuffix>> getSuffixIterator() {
            if (stemIterator.hasNext()) {
                currentLemma = stemIterator.next();
                // process the input for lemma if necessary.
                final TurkicSeq processedForStem = inputPreProcessor.modifyForStem(input, currentLemma);
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

        public TurkicWordParse next() {
            return new TurkicWordParse(currentLemma, suffixParseIterator.next());
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }
    }

}
