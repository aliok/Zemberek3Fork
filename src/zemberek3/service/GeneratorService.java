package zemberek3.service;

import zemberek3.structure.Lemma;
import zemberek3.structure.TurkicWordParse;
import zemberek3.structure.affix.TurkicSuffix;

import java.util.List;

public interface GeneratorService {

    /**
     * Generates a word using a Stem and a list of suffixes.
     * @param lemma lexeme of the word.
     * @param suffixes suffixes.
     * @return generated word.
     */
    String generate(Lemma lemma, List<TurkicSuffix> suffixes);

    /**
     * Generates a word from a word parse result. Uses lemma and affixes.
     * @param wordParse word parse results.
     * @return generted word.
     */
    String generate(TurkicWordParse wordParse);
}
