package zemberek3.parser.morphology;

import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.graph.DummySuffixes1;
import zemberek3.lexicon.graph.DynamicLexiconGraph;
import zemberek3.lexicon.graph.Suffixes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleParserTest {
    Suffixes suffixes = new DummySuffixes1();
    TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixes.getSuffixProvider());

    @Test
    public void testSimpleNouns() throws IOException {
        String[] nouns = {"elma", "armut"};
        List<DictionaryItem> items = getItems(nouns);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixes.getSuffixProvider());
        graph.addDictionaryItems(items);
        
        SimpleParser parser = new SimpleParser(graph);
        List<ParseResult> results = parser.parse("elmalar");
        for (ParseResult result : results) {
            System.out.println(result.asParseString());
        }

    }

    private List<DictionaryItem> getItems(String[] lines) {
        List<DictionaryItem> items = new ArrayList<DictionaryItem>();
        for (String line : lines) {
            items.add(loader.loadFromString(line));
        }
        return items;
    }
}
