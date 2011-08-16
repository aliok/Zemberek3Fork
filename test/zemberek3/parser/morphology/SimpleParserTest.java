package zemberek3.parser.morphology;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.graph.DynamicLexiconGraph;
import zemberek3.lexicon.graph.DynamicSuffixes;
import zemberek3.lexicon.graph.TerminationType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleParserTest {

    @Test
    public void testVoicing() {
        DynamicLexiconGraph graph = getLexiconGraph("armut");
        assertParses(graph, "armut", "armuda", "armutlar", "armutlara");
        assertUnParseable(graph, "armud", "armuta", "armudlar");
    }

    @Test
    public void testSuffixNonDeterminism() {
        DynamicLexiconGraph graph = getLexiconGraph("elma");
        assertParses(graph, "elmacığa", "elmacık");
        assertUnParseable(graph, "elmacığ", "elmacıka");
    }

    @Test
    public void testInverseHarmony() {
        DynamicLexiconGraph graph = getLexiconGraph("saat [A: NoVoicing, InverseHarmony]");
        assertParses(graph, "saate", "saat", "saatler", "saatlere");
        assertUnParseable(graph, "saata", "saatlar", "saada", "saade");
    }

    @Test
    public void testVowelDrop() {
        DynamicLexiconGraph graph = getLexiconGraph("ağız [A: LastVowelDrop]", "nakit [A:LastVowelDrop]", "vakit [A:LastVowelDrop, NoVoicing]");
        assertParses(graph, "vakitlere", "ağza", "ağız", "ağızlar", "nakit", "nakitlere", "nakde", "vakit", "vakte");
        assertUnParseable(graph, "ağz", "ağıza", "ağzlar", "nakd", "nakt", "nakite", "nakda", "vakide", "vakda", "vakite", "vakt");
    }

    @Test
    public void testDoubling() {
        DynamicLexiconGraph graph = getLexiconGraph("ret [A:Voicing, Doubling]");
        assertParses(graph, "ret", "retler", "redde");
        assertUnParseable(graph, "rede", "rete", "redler", "red");
    }

    private DynamicLexiconGraph getLexiconGraph(String... words) {
        SuffixProvider suffixProvider = getProvider3();
        List<DictionaryItem> items = getItems(words, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);
        return graph;
    }

    private void assertParses(DynamicLexiconGraph graph, String... words) {
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

    private List<DictionaryItem> getItems(String[] lines, SuffixProvider suffixProvider) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        List<DictionaryItem> items = new ArrayList<DictionaryItem>();
        for (String line : lines) {
            items.add(loader.loadFromString(line));
        }
        return items;
    }


    private SuffixFormSet getSet(String suffixId, String generationStr) {
        return new SuffixFormSet(new Suffix(suffixId), generationStr);
    }

    private SuffixFormSet getNullSet(String suffixId, String id) {
        return new SuffixFormSet(id, new Suffix(suffixId), "", TerminationType.TRANSFER);
    }

    public SuffixProvider getProvider3() {
        SuffixFormSet Dim_CIK = getSet("Dim", ">cI~k");
        SuffixFormSet P1sg_Im = getSet("P1sg", "Im");
        SuffixFormSet Dat_yA = getSet("Dat", "+yA");
        SuffixFormSet Pnon_EMPTY = getNullSet("Pnon", "Pnon_EMPTY");
        SuffixFormSet Pnon_Main_EMPTY = getNullSet("Pnon", "Pnon_Main_EMPTY");
        SuffixFormSet Nom_EMPTY = getNullSet("Nom", "Nom_EMPTY");
        SuffixFormSet Nom_Main_EMPTY = getNullSet("Nom", "Nom_Main_EMPTY");
        SuffixFormSet A3sg_EMPTY = getNullSet("A3sg", "A3sg_EMPTY");
        SuffixFormSet A3sg_Main_EMPTY = getNullSet("A3sg", "A3sg_Main_EMPTY");
        SuffixFormSet A3pl_lAr = getSet("A3pl", "lAr");

        DynamicSuffixes suffixes = new DynamicSuffixes();

        DynamicSuffixes.Noun_Main.add(A3pl_lAr, A3sg_Main_EMPTY);
        A3sg_EMPTY.add(P1sg_Im, Pnon_EMPTY);
        A3sg_Main_EMPTY.add(P1sg_Im, Pnon_Main_EMPTY);
        A3pl_lAr.add(P1sg_Im, Pnon_EMPTY);
        Pnon_EMPTY.add(Nom_EMPTY, Dat_yA);
        Pnon_Main_EMPTY.add(Dat_yA, Nom_Main_EMPTY);
        Nom_Main_EMPTY.add(Dim_CIK);
        Dim_CIK.add(DynamicSuffixes.Noun_Main);

        suffixes.addSuffixForms(
                DynamicSuffixes.Noun_Main, A3sg_EMPTY, A3sg_Main_EMPTY, A3pl_lAr,
                P1sg_Im, Pnon_EMPTY, Pnon_Main_EMPTY, Dat_yA, Dim_CIK, Nom_EMPTY, Nom_Main_EMPTY);

        return suffixes.getSuffixProvider();
    }

}
