package zemberek3.parser;

import junit.framework.Assert;
import org.jcaki.SimpleTextReader;
import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.parser.morphology.DumbParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DumbParserTest {

    @Test
    public void parseableTest() throws IOException {
        DumbParser parser = getParser();
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            Assert.assertTrue("Could not parse valid word:" + parseable, parser.parse(parseable).size() > 0);
        }
    }

    @Test
    public void unparseableTest() throws IOException {
        DumbParser parser = getParser();
        List<String> unparseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/unparseable.txt")).asStringList();
        for (String wrong : unparseables) {
            Assert.assertTrue("Parses invalid word:" + wrong, parser.parse(wrong).size() == 0);
        }
    }

    private DumbParser getParser() throws IOException {
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-lexicon.txt"));
        TurkishSuffixes suffixes = new TurkishSuffixes();
        LexiconGraph graph = new LexiconGraph(items, suffixes);
        graph.generate();
        return new DumbParser(graph);
    }
}
