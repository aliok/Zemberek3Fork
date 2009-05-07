package zemberek3.parser.word;

import zemberek3.repository.stem.StemProvider;
import zemberek3.structure.Word;
import zemberek3.structure.LetterSequence;

import java.util.List;
import java.util.Collections;
import java.util.Iterator;

public class TurkishWordParser implements WordParser {

    final SuffixParser parser;
    final PreProcessor<LetterSequence> preProcessor;
    final StemProvider stemProvider;


    public TurkishWordParser(
            SuffixParser parser,
            PreProcessor<LetterSequence> preProcessor,
            StemProvider stemProvider) {
        this.parser = parser;
        this.preProcessor = preProcessor;
        this.stemProvider = stemProvider;
    }

    public List<Word> parse(CharSequence input) {
        return Collections.emptyList();
    }

    public Iterator<Word> parseAndIterate(CharSequence input) {
        return new ParseResultIterator(preProcessor.processForParse(input));
    }

    private class ParseResultIterator implements Iterator<Word> {

        private final LetterSequence input;

        private ParseResultIterator(LetterSequence input) {
            this.input = input;
        }

        public boolean hasNext() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Word next() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not available.");
        }
    }
}
