package zemberek3.parser.morphology;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.graph.*;

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
        SuffixProvider suffixProvider = new NounSuffixes();
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


    static class NounSuffixes extends DynamicSuffixProvider {

        static SuffixFormSet Dim_CIK = getSet("Dim", ">cI~k");
        static SuffixFormSet P1sg_Im = getSet("P1sg", "Im");
        static SuffixFormSet Dat_yA = getSet("Dat", "+yA");
        static SuffixFormSet Pnon_EMPTY = getNullSet("Pnon", "Pnon_EMPTY");
        static SuffixFormSet Nom_EMPTY = getNullSet("Nom", "Nom_EMPTY");
        static SuffixFormSet A3sg_EMPTY = getNullSet("A3sg", "A3sg_EMPTY");
        static SuffixFormSet A3pl_lAr = getSet("A3pl", "lAr");
        static SuffixFormSet Noun_Main = getNullSet("Noun", "Noun_Main");

        private static SuffixFormSet getSet(String suffixId, String generationStr) {
            return new SuffixFormSet(new Suffix(suffixId), generationStr);
        }

        private static SuffixFormSet getNullSet(String suffixId, String id) {
            return new SuffixFormSet(id, new Suffix(suffixId), "", TerminationType.TRANSFER);
        }

        NounSuffixes() {
            DynamicSuffixes suffixes = new DynamicSuffixes();
            suffixes.addRootForPos(PrimaryPos.Noun, Noun_Main);

            Noun_Main.directSuccessors.add(A3pl_lAr, A3sg_EMPTY);
            Noun_Main.successors.add(P1sg_Im, Dat_yA, Dim_CIK);

            A3sg_EMPTY.directSuccessors.add(Pnon_EMPTY);

            A3pl_lAr.directSuccessors.add(P1sg_Im, Pnon_EMPTY);
            A3pl_lAr.successors.add(Dat_yA);

            P1sg_Im.directSuccessors.add(Nom_EMPTY, Dat_yA);
            Pnon_EMPTY.directSuccessors.add(Nom_EMPTY);

            Dim_CIK.directSuccessors.add(Noun_Main);

            suffixes.addSuffixForms(
                    Noun_Main, A3sg_EMPTY, A3pl_lAr,
                    P1sg_Im, Pnon_EMPTY, Dat_yA, Dim_CIK, Nom_EMPTY);
        }

/*        public SuffixFormSet[] getRootForms(DictionaryItem item) {
            SuffixData[] datas = defineSuccessorSuffixes(item);
            SuffixFormSet[] sets = new SuffixFormSet[datas.length];
            int i = 0;
            for (SuffixData data : datas) {
                sets[i++] = getSet(item, data);
            }
            return sets;
        }*/

        public SuffixFormSet getRootSet(DictionaryItem item) {
            switch (item.primaryPos) {
                case Noun:
                    return Noun_Main;
                default:
                    return Noun_Main;
            }
        }

        public SuffixFormSet getSet(SuffixFormSet setToCopy, SuffixData successors) {
            SuffixFormSet modified = setToCopy.copy(successors);
            if (formSetLookup.containsKey(modified))
                modified = formSetLookup.get(modified);
            else
                formSetLookup.put(modified, modified);
            return modified;
        }

        public SuffixData[] defineSuccessorSuffixes(DictionaryItem item) {

            SuffixData original = new SuffixData();
            SuffixData modified = new SuffixData();

            PrimaryPos primaryPos = item.primaryPos;

            switch (primaryPos) {
                case Noun:
                    getForNoun(item, original, modified);
                    break;
                case Verb:
                    getForVerb(item, original, modified);
                    break;
            }
            return new SuffixData[]{original, modified};
        }

        private void getForVerb(DictionaryItem item, SuffixData original, SuffixData modified) {

        }

        void getForNoun(DictionaryItem item, SuffixData original, SuffixData modified) {

            /*   for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case CompoundP3sg:
                        original.add(Noun_Comp_P3sg.getSuccessors().copy());
                        modified.clear().add(Noun_Comp_P3sg_Root.getSuccessors().copy());
                        break;
                    default:
                        break;
                }
            }*/
        }


    }

}
