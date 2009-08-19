package zemberek3.parser.word;

import zemberek3.comparators.CharSequenceComparator;
import zemberek3.repository.affix.SuffixRepository;
import zemberek3.structure.Alphabet;
import zemberek3.structure.Lemma;
import zemberek3.structure.LetterSequence;
import zemberek3.structure.affix.Affix;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple suffix parser similar to Zemberek2's mechanism.
 */
public class TopDownSuffixParser implements SuffixParser<LetterSequence> {

    private final CharSequenceComparator<LetterSequence> comparator;
    private final Alphabet alphabet;
    private final SuffixRepository suffixes;

    public TopDownSuffixParser(CharSequenceComparator<LetterSequence> comparator,
                               Alphabet alphabet,
                               SuffixRepository suffixes) {
        this.comparator = comparator;
        this.alphabet = alphabet;
        this.suffixes = suffixes;
    }

    private static class ParseState {
        LetterSequence sequence;
        int suffixSequence;
    }

    public List<List<Affix>> parse(LetterSequence input, Lemma lemma) {

        LinkedList<ParseState> parseStack = new LinkedList<ParseState>();
        Affix rootSuffix = suffixes.getRootSuffix(lemma);
        // original lemma sequence. this may be required after lemma's formation is changed during the parse.
        LetterSequence stemSequence = new LetterSequence(lemma.getContent(), alphabet);

        return null;
    }

    public Iterator<List<Affix>> parseIterator(LetterSequence input, Lemma lemma) {
        return new ParseIterator(input, lemma);
    }

    private class ParseIterator implements Iterator<List<Affix>> {

        final LetterSequence input;
        final Lemma lemma;

        private ParseIterator(LetterSequence input, Lemma lemma) {
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