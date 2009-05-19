package zemberek3.parser.word;

import zemberek3.structure.WordParseResult;
import zemberek3.structure.Stem;

import java.util.List;
import java.util.ArrayList;

public class SimpleWordStemmer implements WordStemmer {
    final WordParser parser;

    public SimpleWordStemmer(WordParser parser) {
        this.parser = parser;
    }

    public List findStems(CharSequence input) {
        List<WordParseResult> results = parser.parse(input);
        List<Stem> stems = new ArrayList<Stem>();
        for (WordParseResult result : results) {
            stems.add(result.getStem());
        }
        return stems;
    }
}
