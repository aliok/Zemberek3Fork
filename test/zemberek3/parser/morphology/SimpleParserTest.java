package zemberek3.parser.morphology;

import junit.framework.Assert;
import org.jcaki.SimpleTextReader;
import org.junit.Ignore;
import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.SuffixProvider;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SimpleParserTest {

    @Test
    public void parseableTest() throws IOException {
        SimpleParser parser = getParser();
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            Assert.assertTrue("Could not parse valid word:" + parseable, parser.parse(parseable).size() > 0);
        }
    }

    @Test
    public void unparseableTest() throws IOException {
        SimpleParser parser = getParser();
        List<String> unparseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/unparseable.txt")).asStringList();
        for (String wrong : unparseables) {
            Assert.assertTrue("Parses invalid word:" + wrong, parser.parse(wrong).size() == 0);
        }
    }

    @Test
    @Ignore("Performance Test")
    public void speedTest() throws IOException {
        SimpleParser parser = getParser();
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        long start = System.currentTimeMillis();
        final long iteration = 1000;
        for (int i = 0; i < iteration; i++) {
            for (String s : parseables) {
                List<ParseToken> results = parser.parse(s);
                if (i == 0) {
                    for (ParseToken result : results) {
                        System.out.println(s + " = " + result.asParseString());
                    }
                }
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Elapsed:" + elapsed + " ms.");
        System.out.println("Speed:" + (iteration * 1000 * parseables.size() / elapsed) + " words/second");
    }

    private SimpleParser getParser() throws IOException {
        SuffixProvider suffixProvider = new TurkishSuffixes().getSuffixProvider();
        List<DictionaryItem> items = new TurkishDictionaryLoader(suffixProvider).load(new File("test/data/dev-lexicon.txt"));
        LexiconGraph graph = new LexiconGraph(items, suffixProvider);
        graph.generate();
        return new SimpleParser(graph);
    }
}
