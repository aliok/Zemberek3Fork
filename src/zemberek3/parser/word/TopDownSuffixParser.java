package zemberek3.parser.word;

import zemberek3.comparators.CharSequenceComparator;
import zemberek3.repository.affix.AffixRepository;
import zemberek3.structure.Affix;
import zemberek3.structure.Alphabet;
import zemberek3.structure.LetterSequence;
import zemberek3.structure.Stem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple suffix parser similar to Zemberek2's mechanism.
 */
public class TopDownSuffixParser implements SuffixParser<LetterSequence> {

    private final CharSequenceComparator<LetterSequence> comparator;
    private final Alphabet alphabet;
    private final AffixRepository suffixes;

    public TopDownSuffixParser(CharSequenceComparator<LetterSequence> comparator,
                               Alphabet alphabet,
                               AffixRepository suffixes) {
        this.comparator = comparator;
        this.alphabet = alphabet;
        this.suffixes = suffixes;
    }

    public static class ParseState {
        LetterSequence sequence;
        int suffixSequence;
    }

    public List<List<Affix>> parse(LetterSequence input, Stem stem) {

        LinkedList<ParseState> parseStack = new LinkedList<ParseState>();
        Affix rootSuffix = suffixes.getRootSuffix(stem);
        // original stem sequence. this may be required after stem's formation is changed during the parse.
        LetterSequence stemSequence = new LetterSequence(stem.getContent(), alphabet);       

        return null;
    }

    public Iterator<List<Affix>> parseIterator(LetterSequence input, Stem stem) {
        return new ParseIterator(input, stem);
    }

    private class ParseIterator implements Iterator<List<Affix>> {

        final LetterSequence input;
        final Stem stem;

        private ParseIterator(LetterSequence input, Stem stem) {
            this.input = input;
            this.stem = stem;
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
