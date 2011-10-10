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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleParserFunctionalTest {

    public void parseableTest(MorphParser parser) throws IOException {
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            Assert.assertTrue("Could not parse valid word:" + parseable, parser.parse(parseable).size() > 0);
        }
    }

    @Test
    public void simpleParse() throws IOException {
        DynamicLexiconGraph graph = getLexiconGraph(new File("test/data/dev-lexicon.txt"));
        SimpleParser simpleParser = new SimpleParser(graph);
        List<String> parseables = SimpleTextReader.trimmingUTF8Reader(new File("test/data/parseable.txt")).asStringList();
        for (String parseable : parseables) {
            List<ParseResult> results = simpleParser.parse(parseable);
            if (results.size() > 0) {
                System.out.print(parseable + " : ");
                for (ParseResult parseResult : results) {
                    System.out.print(parseResult.asOflazerFormat() + "   ");
                }
                System.out.println();

            } else {
                //  System.out.println("ERROR:" + parseable);
            }
            //Assert.assertTrue("Could not parse valid word:" + parseable, parser.parse(parseable).size() > 0);
        }
    }

    @Test
    public void testVoicing() {
        DynamicLexiconGraph graph = getLexiconGraph("armut");
        assertHasParses(graph, "armut", "armuda", "armutlar", "armutlara");
        assertUnParseable(graph, "armud", "armuta", "armudlar");
    }

    @Test
    public void testCompounds() {
        DynamicLexiconGraph graph = getLexiconGraph("zeytinyağı [A:CompoundP3sg ;R:zeytinyağ]");
        assertHasParses(graph, "zeytinyağım", "zeytinyağına", "zeytinyağı", "zeytinyağcık", "zeytinyağlarım");
        assertUnParseable(graph, "zeytinyağılar", "zeytinyağıcık");
    }

    @Test
    public void testCompoundsVoicing() {
        DynamicLexiconGraph graph = getLexiconGraph("atkuyruğu [A:CompoundP3sg, Voicing ; R:atkuyruk]");
        assertHasParses(graph, "atkuyrukçuk", "atkuyruğu", "atkuyruklarım");
        assertUnParseable(graph, "atkuyruğlarım", "atkuyruk");
    }

    @Test
    public void testWithAndWithout() {
        DynamicLexiconGraph graph = getLexiconGraph("elma", "kitap");
        assertHasParses(graph, "elmalı", "elmasız", "kitaplı", "kitapsız");
        assertUnParseable(graph, "elmayalı", "elmalarlı", "elmadasız", "elmalarsız");
    }

    @Test
    public void testBecome() {
        // noun
        DynamicLexiconGraph graph = getLexiconGraph("elma", "mavi [P:Adj]");
        assertHasParses(graph, "elmalaş");
        // adj
        assertHasParses(graph, "mavileş");
    }

    @Test
    public void testQuiteAndLy() {
        // Adj-Adj and Adj-Adv
        DynamicLexiconGraph graph = getLexiconGraph("hızlı [P:Adj]");
        assertHasParses(graph, "hızlıca");
    }

    @Test
    public void testRelated() {
        // Noun-Adj
        DynamicLexiconGraph graph = getLexiconGraph("elma");
        assertHasParses(graph, "elmadaki");
        assertUnParseable(graph, "elmaki", "elmayaki");
        //TODO: add akşamki etc. uses Rel_kI instead of Rel_ki
    }

    @Test
    public void testDim() {
        // Noun-Noun
        DynamicLexiconGraph graph = getLexiconGraph("armut");
        assertHasParses(graph, "armutçuk", "armutçuğu", "armutcağız", "armutcağızı");
        assertUnParseable(graph, "armutçuğ", "armutçukcuk", "armutçukcağız", "armutcağızcık");
        // TODO: check oflazer parse "babacım = baba+Noun+A3sg+Pnon+Nom^DB+Noun+Dim+A3sg+P1sg+Nom
    }

    @Test
    public void testResembl() {
        // Noun-Noun
        DynamicLexiconGraph graph = getLexiconGraph("armut", "yeşil [P:Adj]");
        assertHasParses(graph, "armutsu", "armudumsu", "yeşilsi", "yeşilimsi");
        // TODO: oflazer uses JustLike for this. It parses words like "tuhafsı","arabası" as JustLike
    }

    @Test
    public void testCausative() {
        // Noun-Noun
        DynamicLexiconGraph graph = getLexiconGraph("yapmak", "aramak");
        assertHasParses(graph, "yaptır", "yaptırt", "yaptırttır", "arat", "arattır", "arattırt");
        assertUnParseable(graph, "yapt", "aratır");
    }

    @Test
    public void testPassive() {
        // Noun-Noun
        DynamicLexiconGraph graph = getLexiconGraph("yapmak", "aramak", "gelmek");
        assertHasParses(graph, "aranıl", "yapıl", "aran", "gelin", "gelinil");
        assertUnParseable(graph, "aral", "gelil");
        // causative and passive
        assertHasParses(graph, "yaptırıl", "yaptırtıl", "yaptırttırıl", "aratıl", "arattırıl", "arattırtıl");
    }

    @Test
    public void testNegative() {
        // Noun-Noun
        DynamicLexiconGraph graph = getLexiconGraph("aramak", "gelmek");
        assertHasParses(graph, "arama", "gelme");
    }

    @Test
    public void testPrograesive() {
        // Noun-Noun
        DynamicLexiconGraph graph = getLexiconGraph("aramak", "gelmek");
        assertHasParses(graph, "arıyor", "aramıyor", "geliyor", "gelmiyor");
        assertHasParses(graph, "aramakta", "aramamakta", "gelmekte", "gelmemekte");
    }

    @Test
    public void testAorist() {
        // Noun-Noun
        DynamicLexiconGraph graph = getLexiconGraph("aramak", "gitmek [A:Voicing, Aorist_A]","gelmek [A:NonTransitive, Aorist_I]");
        assertHasParses(graph, "arar", "gider", "gelir", "aramaz");
        assertUnParseable(graph, "geler", "gidir");
    }


    private void assertHasParses(DynamicLexiconGraph graph, String... words) {
        SimpleParser parser = new SimpleParser(graph);
        for (String word : words) {
            List<ParseResult> results = parser.parse(word);
            Assert.assertTrue("No parse for:" + word, results.size() > 0);
            for (ParseResult result : results) {
                System.out.println(word + "= " + result.asParseString());
            }
        }
    }

    private void assertUnParseable(DynamicLexiconGraph graph, String... words) {
        SimpleParser parser = new SimpleParser(graph);
        for (String word : words) {
            List<ParseResult> results = parser.parse(word);
            Assert.assertTrue("Unexpected parse for:" + word + " parse:" + results, results.size() == 0);
        }
    }

    private DynamicLexiconGraph getLexiconGraph(String... words) {
        SuffixProvider suffixProvider = new TurkishSuffixes();
        List<DictionaryItem> items = getItems(words, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);
        return graph;
    }

    private List<DictionaryItem> getItems(String[] lines, SuffixProvider suffixProvider) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        List<DictionaryItem> items = new ArrayList<DictionaryItem>();
        for (String line : lines) {
            items.add(loader.loadFromString(line));
        }
        return items;
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
