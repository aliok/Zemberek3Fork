package zemberek3.parser.word;

import z3.comparators.CharSequenceComparator;
import z3.repository.affix.TurkishSuffixRepository;
import zemberek3.structure.Lemma;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;
import zemberek3.structure.affix.TurkicSuffix;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple suffix parser similar to Zemberek2's mechanism.
 */
public class TopDownSuffixParser implements SuffixParser<TurkicSeq> {

    private final CharSequenceComparator<TurkicSeq> comparator;
    private final TurkishAlphabet alphabet;
    private final TurkishSuffixRepository suffixes;

    public TopDownSuffixParser(CharSequenceComparator<TurkicSeq> comparator,
                               TurkishAlphabet alphabet,
                               TurkishSuffixRepository suffixes) {
        this.comparator = comparator;
        this.alphabet = alphabet;
        this.suffixes = suffixes;
    }

    private static class ParseState {
        TurkicSeq sequence;
        int suffixSequence;
    }

    public List<List<TurkicSuffix>> parse(TurkicSeq input, Lemma lemma) {

        LinkedList<ParseState> parseStack = new LinkedList<ParseState>();
        TurkicSuffix rootSuffix = suffixes.getRootSuffix(lemma);
        // original lemma sequence. this may be required after lemma's formation is changed during the parse.
        TurkicSeq stemSequence = new TurkicSeq(lemma.getContent(), alphabet);

        return null;
    }

    public Iterator<List<TurkicSuffix>> parseIterator(TurkicSeq input, Lemma lemma) {
        return new ParseIterator(input, lemma);
    }

    private class ParseIterator implements Iterator<List<TurkicSuffix>> {

        final TurkicSeq input;
        final Lemma lemma;

        private ParseIterator(TurkicSeq input, Lemma lemma) {
            this.input = input;
            this.lemma = lemma;
        }

        public boolean hasNext() {
            return false;
        }

        public List<TurkicSuffix> next() {
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not available.");
        }
    }

}