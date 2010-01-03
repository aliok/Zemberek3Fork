package zemberek3.parser.word;

import zemberek3.comparators.CharSequenceComparator;
import zemberek3.repository.affix.SuffixRepository;
import zemberek3.structure.TurkicAlphabet;
import zemberek3.structure.Lemma;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.affix.Affix;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple suffix parser similar to Zemberek2's mechanism.
 */
public class TopDownSuffixParser implements SuffixParser<TurkicLetterSequence> {

    private final CharSequenceComparator<TurkicLetterSequence> comparator;
    private final TurkicAlphabet alphabet;
    private final SuffixRepository suffixes;

    public TopDownSuffixParser(CharSequenceComparator<TurkicLetterSequence> comparator,
                               TurkicAlphabet alphabet,
                               SuffixRepository suffixes) {
        this.comparator = comparator;
        this.alphabet = alphabet;
        this.suffixes = suffixes;
    }

    private static class ParseState {
        TurkicLetterSequence sequence;
        int suffixSequence;
    }

    public List<List<Affix>> parse(TurkicLetterSequence input, Lemma lemma) {

        LinkedList<ParseState> parseStack = new LinkedList<ParseState>();
        Affix rootSuffix = suffixes.getRootSuffix(lemma);
        // original lemma sequence. this may be required after lemma's formation is changed during the parse.
        TurkicLetterSequence stemSequence = new TurkicLetterSequence(lemma.getContent(), alphabet);

        return null;
    }

    public Iterator<List<Affix>> parseIterator(TurkicLetterSequence input, Lemma lemma) {
        return new ParseIterator(input, lemma);
    }

    private class ParseIterator implements Iterator<List<Affix>> {

        final TurkicLetterSequence input;
        final Lemma lemma;

        private ParseIterator(TurkicLetterSequence input, Lemma lemma) {
            this.input = input;
            this.lemma = lemma;
        }

        public boolean hasNext() {
            return false;
        }

        public List<Affix> next() {
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not available.");
        }
    }

}