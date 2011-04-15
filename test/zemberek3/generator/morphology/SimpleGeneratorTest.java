package zemberek3.generator.morphology;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import junit.framework.Assert;
import org.jcaki.SimpleTextReader;
import org.jcaki.Strings;
import org.junit.Ignore;
import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.parser.morphology.ParseToken;
import zemberek3.parser.morphology.SimpleParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @Test
    public void morphemeGenerationTest() throws IOException {
        LexiconGraph graph = getLexicon();
        SimpleParser parser = new SimpleParser(graph);
        SimpleGenerator generator = new SimpleGenerator(graph);
        List<String> testLines = SimpleTextReader.trimmingUTF8Reader(new File("test/data/separate-morphemes.txt")).asStringList();
        ArrayListMultimap<String, String> results = ArrayListMultimap.create(100, 2);
        for (String testLine : testLines) {
            for (String s : Splitter.on(",").trimResults().split(Strings.subStringAfterFirst(testLine, "=")))
                results.put(Strings.subStringUntilFirst(testLine, "=").trim(), s);
        }
        for (String parseable : results.keySet()) {
            List<ParseToken> parseResults = parser.parse(parseable);
            for (ParseToken parseResult : parseResults) {
                String[] res = generator.generateMorphemes(parseResult.getDictionaryItem(), parseResult.getSuffixes());
                String s = Joiner.on("-").join(res);
                Assert.assertTrue("Error in:" + parseable, results.get(parseable).contains(s));
            }
        }
    }

    @Test
    @Ignore("Performance Test")
    public void speedTest() throws IOException {
        LexiconGraph graph = getLexicon();
        SimpleParser parser = new SimpleParser(graph);
        SimpleGenerator generator = new SimpleGenerator(graph);
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        List<ParseToken> parses = new ArrayList<ParseToken>();
        for (String word : parseables) {
            parses.addAll(parser.parse(word));
        }
        long start = System.currentTimeMillis();
        final long iteration = 1000;
        for (int i = 0; i < iteration; i++) {
            for (ParseToken parseToken : parses) {
                String result = generator.generate(parseToken.getDictionaryItem(), parseToken.getSuffixes());
                if (i == 0) {
                    System.out.println(parseToken + " = " + result);
                }
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Elapsed:" + elapsed + " ms.");
        System.out.println("Speed:" + (iteration * 1000 * parses.size() / elapsed) + " words/second");
    }

    private LexiconGraph getLexicon() throws IOException {
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-lexicon.txt"));
        TurkishSuffixes suffixes = new TurkishSuffixes();
        LexiconGraph graph = new LexiconGraph(items, suffixes);
        graph.generate();
        return graph;
    }
}
