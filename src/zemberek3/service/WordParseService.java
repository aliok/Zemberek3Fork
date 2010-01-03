package zemberek3.service;

import zemberek3.structure.Lemma;
import zemberek3.structure.TurkicWordParse;

import java.util.List;

/**
 * Provides easy access for single word parse related operations.
 */
public interface WordParseService {

    /**
     * Retrieves the list of Parse results for a given word. if there is no parse, an empty list is returned.
     *
     * @param input single input word.
     * @return all possible parses.
     */
    List<TurkicWordParse> parse(String input);

    /**
     * returns all possible morphem lists. if there is no morphemes, an empty list.
     * example:
     * elmasi -> [["elma", "si"],["elmas","i"]]
     * kitaba -> [["kitab", "a"]]
     * hatta -> [["hatt", "a"],["hatta"]]
     * @param input single input word.
     * @return all possible morphemes as a list of list of string.
     */
    List<List<String>> morphemes(String input);

    /**
     * retruns all possible stems for a given word. if there is no parse possible it will return an empty list.
     * example:
     * elmasi -> ["elma", "elmas"]
     * kitaba -> ["kitab"]
     * hatta -> ["hatt","hatta"]
     *
     * @param input single input word.
     * @return a list of possible stems.
     */
    List<String> stems(String input);

    /**
     * retruns all possible lemmas for a given word. if there is no parse possible it will return an empty list.
     * example:
     * elmasi -> [elma, elmas]
     * kitaba -> [kitap]
     * hatta -> [hat , hatta]
     *
     * @param input single input word.
     * @return a list of possible stems.
     */
    List<Lemma> lemmas(String input);
}
