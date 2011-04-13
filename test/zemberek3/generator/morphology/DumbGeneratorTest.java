package zemberek3.generator.morphology;

import junit.framework.Assert;
import org.jcaki.SimpleTextReader;
import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffix;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.parser.morphology.DumbParser;
import zemberek3.parser.morphology.ParseToken;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class DumbGeneratorTest {
    @Test
    public void regenerateTest() throws IOException {
        LexiconGraph graph = getLexicon();
        DumbParser parser = new DumbParser(graph);
        DumbGenerator generator = new DumbGenerator(graph);
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            List<ParseToken> parseResults = parser.parse(parseable);
            for (ParseToken parseResult : parseResults) {
                List<String> res = generator.generate(parseResult.getDictionaryItem(), parseResult.getSuffixes());
                Assert.assertTrue("Error in:" + parseable, res.contains(parseable));
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
