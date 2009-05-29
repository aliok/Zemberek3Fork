package zemberek3.service;

import zemberek3.structure.Affix;
import zemberek3.structure.Stem;
import zemberek3.structure.WordParseResult;

import java.util.List;

public interface GeneratorService {

    /**
     * Generates a word using a Stem and a list of suffixes.
     * @param stem Stem of the word.
     * @param suffixes suffixes.
     * @return generated word.
     */
    String generate(Stem stem, List<Affix> suffixes);

    /**
     * Generates a word from a word parse result. Uses stem and affixes.
     * @param wordParseResult word parse results.
     * @return generted word.
     */
    String generate(WordParseResult wordParseResult);
}
