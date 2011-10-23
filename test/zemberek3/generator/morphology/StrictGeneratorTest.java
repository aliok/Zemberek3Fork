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
import zemberek3.lexicon.SuffixProvider;
import zemberek3.lexicon.tr.TurkishDictionaryLoader;
import zemberek3.lexicon.tr.TurkishSuffixes;
import zemberek3.lexicon.graph.DynamicLexiconGraph;
import zemberek3.parser.morphology.ParseResult;
import zemberek3.parser.morphology.SimpleParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrictGeneratorTest {
    @Test
    public void regenerateTest() throws IOException {
        DynamicLexiconGraph graph = getLexicon();
        SimpleParser parser = new SimpleParser(graph);
        StrictGenerator generator = new StrictGenerator(graph);
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            List<ParseResult> parseResults = parser.parse(parseable);
            for (ParseResult parseResult : parseResults) {
                String[] res = generator.generate(parseResult.getDictionaryItem(), parseResult.getSuffixNodes());
                boolean found = false;
                for (String re : res) {
                    if (re.equals(parseable))
                        found = true;
                }
                Assert.assertTrue("Error in:" + parseable + " with parse:" + parseResult, found);
            }
        }
    }

    @Test
    public void regenerateTest2() throws IOException {
        DynamicLexiconGraph graph = getLexicon();
        SimpleParser parser = new SimpleParser(graph);
        StrictGenerator generator = new StrictGenerator(graph);
        String word = "elmada";
        List<ParseResult> parseResults = parser.parse(word);
        for (ParseResult parseResult : parseResults) {
            System.out.println(parseResult);
            String[] res = generator.generate(parseResult.getDictionaryItem(), parseResult.getSuffixNodes());
            System.out.println(Arrays.toString(res));
        }
    }

    @Test
    public void morphemeGenerationTest() throws IOException {
        DynamicLexiconGraph graph = getLexicon();
        SimpleParser parser = new SimpleParser(graph);
        StrictGenerator generator = new StrictGenerator(graph);
        List<String> testLines = SimpleTextReader.trimmingUTF8Reader(new File("test/data/separate-morphemes.txt")).asStringList();
        ArrayListMultimap<String, String> results = ArrayListMultimap.create(100, 2);
        for (String testLine : testLines) {
            for (String s : Splitter.on(",").trimResults().split(Strings.subStringAfterFirst(testLine, "=")))
                results.put(Strings.subStringUntilFirst(testLine, "=").trim(), s);
        }
        for (String parseable : results.keySet()) {
            List<ParseResult> parseResults = parser.parse(parseable);
            for (ParseResult parseResult : parseResults) {
                String[] res = generator.generateMorphemes(parseResult.getDictionaryItem(), parseResult.getSuffixNodes());
                String s = Joiner.on("-").join(res);
                Assert.assertTrue("Error in:" + parseable, results.get(parseable).contains(s));
            }
        }
    }

    @Test
    @Ignore("Performance Test")
    public void speedTest() throws IOException {
        DynamicLexiconGraph graph = getLexicon();
        SimpleParser parser = new SimpleParser(graph);
        StrictGenerator generator = new StrictGenerator(graph);
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        List<ParseResult> parses = new ArrayList<ParseResult>();
        for (String word : parseables) {
            parses.addAll(parser.parse(word));
        }
        long start = System.currentTimeMillis();
        final long iteration = 1000;
        for (int i = 0; i < iteration; i++) {
            for (ParseResult parseToken : parses) {
                String[] result = generator.generate(parseToken.getDictionaryItem(), parseToken.getSuffixNodes());
                if (i == 0) {
                    System.out.println(parseToken + " = " + Arrays.toString(result));
                }
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Elapsed:" + elapsed + " ms.");
        System.out.println("Speed:" + (iteration * 1000 * parses.size() / elapsed) + " words/second");
    }

    SuffixProvider suffixProvider = new TurkishSuffixes();

    private DynamicLexiconGraph getLexicon() throws IOException {
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-lexicon.txt"));
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);
        return graph;
    }
}
