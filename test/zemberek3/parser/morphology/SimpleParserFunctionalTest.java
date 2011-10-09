package zemberek3.parser.morphology;

import junit.framework.Assert;
import org.jcaki.SimpleTextReader;
import org.jcaki.SimpleTextWriter;
import org.junit.Ignore;
import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.SuffixProvider;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.DynamicLexiconGraph;
import zemberek3.lexicon.graph.LexiconGraph;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SimpleParserFunctionalTest {

    public void parseableTest(MorphParser parser) throws IOException {
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            Assert.assertTrue("Could not parse valid word:" + parseable, parser.parse(parseable).size() > 0);
        }
    }


    @Test
    public void trieBasedParseable() throws IOException {
        parseableTest(trieParser(new File("test/data/dev-lexicon.txt")));
    }

    @Test
    public void simpleParserParseable() throws IOException {
        parseableTest(simpleParser(new File("test/data/dev-lexicon.txt")));
    }

    @Test
    public void unparseableTest() throws IOException {
        SimpleParser parser = simpleParser(new File("test/data/dev-lexicon.txt"));
        List<String> unparseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/unparseable.txt")).asStringList();
        for (String wrong : unparseables) {
            Assert.assertTrue("Parses invalid word:" + wrong, parser.parse(wrong).size() == 0);
        }
    }

    @Test
    @Ignore("Performance Test")
    public void speedTest() throws IOException {
        MorphParser parser = simpleParser(new File("test/data/dev-lexicon.txt"));
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        long start = System.currentTimeMillis();
        final long iteration = 1000;
        for (int i = 0; i < iteration; i++) {
            for (String s : parseables) {
                List<ParseResult> results = parser.parse(s);
                if (i == 0) {
                    for (ParseResult result : results) {
                        System.out.println(s + " = " + result.asParseString());
                    }
                }
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Elapsed:" + elapsed + " ms.");
        System.out.println("Speed:" + (iteration * 1000 * parseables.size() / elapsed) + " words/second");
    }

    @Test
    @Ignore("Not a unit Test")
    public void z2Comparison() throws IOException {
        List<String> allWords = SimpleTextReader.trimmingUTF8Reader(
                new File("test/data/z2-vocab.tr")).asStringList();
        SimpleTextWriter stw = SimpleTextWriter.keepOpenUTF8Writer(new File("test/data/unknowns.txt"));
        SimpleParser parser = simpleParser(new File("src/resources/tr/master-dictionary.txt"));
        int pass = 0;
        for (String word : allWords) {
            if (parser.parse(word).size() > 0)
                pass++;
            else
                stw.writeLine(word);
        }
        stw.close();
        System.out.println("Total words:" + allWords.size());
        System.out.println("Passed words:" + pass);
        System.out.println("Ratio=%" + ((double) pass * 100 / allWords.size()));
    }

    private SimpleParser simpleParser(File dictionary) throws IOException {
        DynamicLexiconGraph graph = getLexiconGraph(dictionary);
        return new SimpleParser(graph);
    }

    private TrieBasedParser trieParser(File dictionary) throws IOException {
        DynamicLexiconGraph graph = getLexiconGraph(dictionary);
        return new TrieBasedParser(graph);
    }

    private DynamicLexiconGraph getLexiconGraph(File dictionary) throws IOException {
        SuffixProvider suffixProvider = new TurkishSuffixes();
        List<DictionaryItem> items = new TurkishDictionaryLoader(suffixProvider).load(dictionary);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);
        return graph;
    }


}
