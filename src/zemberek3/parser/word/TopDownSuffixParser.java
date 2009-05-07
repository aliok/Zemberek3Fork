package zemberek3.parser.word;

import zemberek3.structure.*;
import zemberek3.comparators.CharSequenceComparator;
import zemberek3.repository.affix.AffixRepository;

import java.util.List;
import java.util.Iterator;

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

    public List<List<Affix>> parse(LetterSequence input, Stem stem) {
        return null;
    }

    public Iterator<List<Affix>> parseAndIterate(LetterSequence input, Stem stem) {
        return null;
    }

}
