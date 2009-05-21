package zemberek3.service;

import zemberek3.structure.WordParseResult;
import zemberek3.structure.Stem;

import java.util.List;

/**
 * Provides easy access for single word related operations.
 */
public interface ParserService {

    List<WordParseResult> parse(String input);

    List<String> morphemes(String input);

    List<Stem> stems(String input);

}
