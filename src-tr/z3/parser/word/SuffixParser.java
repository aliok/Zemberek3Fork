package z3.parser.word;

import z3.structure.Lemma;
import z3.structure.affix.TurkicSuffix;

import java.util.Iterator;
import java.util.List;

/**
 * Finds possible suffix sequences for a given input and Stem.
 */
interface SuffixParser<T extends CharSequence> {

    /**
     * parses the input for a given lemma. it finds all possible suffix combinations.
     *
     * @param input input, a CharSequence implementation.
     * @param lemma  a Stem to use in the parsing.
     * @return List of Words contianing the given Stem and possible affix list combinations.
     *         if parsing is not successfull, and empty list is returned.
     */
    List<List<TurkicSuffix>> parse(T input, Lemma lemma);

    /**
     * provides an iterator over parse results. this may be useful when all results are not required for
     * performance reasons.
     *
     * @param input input a CharSequence implementation.
     * @param lemma  a Stem to use in the parsing.
     * @return an iterator that iterates and parses the input and lemma.
     */
    Iterator<List<TurkicSuffix>> parseIterator(T input, Lemma lemma);
}
