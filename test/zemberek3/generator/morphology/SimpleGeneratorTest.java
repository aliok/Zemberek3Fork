package zemberek3.generator.morphology;

import junit.framework.Assert;
import org.jcaki.SimpleTextReader;
import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.parser.morphology.SimpleParser;
import zemberek3.parser.morphology.ParseToken;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class SimpleGeneratorTest {
    @Test
    public void regenerateTest() throws IOException {
        LexiconGraph graph = getLexicon();
        SimpleParser parser = new SimpleParser(graph);
        SimpleGenerator generator = new SimpleGenerator(graph);
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            List<ParseToken> parseResults = parser.parse(parseable);
            for (ParseToken parseResult : parseResults) {
                String res = generator.generate(parseResult.getDictionaryItem(), parseResult.getSuffixes());
                Assert.assertEquals("Error in:" + parseable, parseable, res);
            }
        }
    }

    private LexiconGraph getLexicon() throws IOException {
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-lexicon.txt"));
        TurkishSuffixes suffixes = new TurkishSuffixes();
        LexiconGraph graph = new LexiconGraph(items, suffixes);
        graph.generate();
        return graph;
    }
}
