package z3.service;

import z3.structure.Lemma;
import z3.structure.TurkicWordParse;
import z3.structure.affix.TurkicSuffix;

import java.util.List;

public interface GeneratorService {

    /**
     * Generates a word using a Lemma and a list of suffixes.
     * @param lemma lemma of the word.
     * @param suffixes suffixes.
     * @return generated word.
     */
    String generate(Lemma lemma, List<TurkicSuffix> suffixes);

    /**
     * Generates a word from a word parse result. Uses lemma and suffixes.
     * @param wordParse word parse results.
     * @return generted word.
     */
    String generate(TurkicWordParse wordParse);
}
