package zemberek3.parser.morphology;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.graph.*;
import zemberek3.lexicon.tr.RootAttr;
import zemberek3.lexicon.tr.TurkishDictionaryLoader;

import java.util.*;

public class SimpleParserTest {

    @Test
    public void testVoicing() {
        DynamicLexiconGraph graph = getLexiconGraph("armut");
        assertHasParses(graph, "armut", "armuda", "armutlar", "armutlara");
        assertUnParseable(graph, "armud", "armuta", "armudlar");
    }

    @Test
    public void testSuffixNonDeterminism() {
        DynamicLexiconGraph graph = getLexiconGraph("elma");
        assertHasParses(graph, "elmacığa", "elmacık");
        assertUnParseable(graph, "elmacığ", "elmacıka", "elmamcık", "elmayacık", "elmalarcık");
    }

    @Test
    public void testInverseHarmony() {
        DynamicLexiconGraph graph = getLexiconGraph("saat [A: NoVoicing, InverseHarmony]");
        assertHasParses(graph, "saate", "saat", "saatler", "saatlere");
        assertUnParseable(graph, "saata", "saatlar", "saada", "saade");
    }

    @Test
    public void testVowelDrop() {
        DynamicLexiconGraph graph = getLexiconGraph("ağız [A: LastVowelDrop]", "nakit [A:LastVowelDrop]", "vakit [A:LastVowelDrop, NoVoicing]");
        assertHasParses(graph, "vakitlere", "ağza", "ağız", "ağızlar", "nakit", "nakitlere", "nakde", "vakit", "vakte");
        assertUnParseable(graph, "ağz", "ağıza", "ağzlar", "nakd", "nakt", "nakite", "nakda", "vakide", "vakda", "vakite", "vakt");
    }

    @Test
    public void testDoubling() {
        DynamicLexiconGraph graph = getLexiconGraph("ret [A:Voicing, Doubling]");
        assertHasParses(graph, "ret", "retler", "redde");
        assertUnParseable(graph, "rede", "rete", "redler", "red");
    }

    @Test
    public void testCompounds() {
        DynamicLexiconGraph graph = getLexiconGraph("zeytinyağı [A:CompoundP3sg ;R:zeytinyağ]");
        assertHasParses(graph, "zeytinyağım", "zeytinyağına", "zeytinyağı", "zeytinyağcık", "zeytinyağcığa", "zeytinyağlarım");
        assertUnParseable(graph, "zeytinyağılar", "zeytinyağıcık");
    }

    @Test
    public void testCompoundsVoicing() {
        DynamicLexiconGraph graph = getLexiconGraph("atkuyruğu [A:CompoundP3sg, Voicing ; R:atkuyruk]");
        assertHasParses(graph, "atkuyruğu", "atkuyruklarım", "atkuyrukçuk");
        assertUnParseable(graph, "atkuyruğlarım", "atkuyruk");
    }

    @Test
    public void testMultiWordDictionary() {
        DynamicLexiconGraph graph = getLexiconGraph("armut", "elma", "kabak", "kek");
        assertHasParses(graph, "armuda", "armutlara", "elmacığa", "keke", "kekçiklere");
    }

    private DynamicLexiconGraph getLexiconGraph(String... words) {
        SuffixProvider suffixProvider = new NounSuffixes();
        List<DictionaryItem> items = getItems(words, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);
        return graph;
    }

    private void assertHasParses(DynamicLexiconGraph graph, String... words) {
        SimpleParser parser = new SimpleParser(graph);
        for (String word : words) {
            List<ParseResult> results = parser.parse(word);
            if (results.size() == 0)
                parser.dump(word);
            Assert.assertTrue("No parse for:" + word, results.size() > 0);
            for (ParseResult result : results) {
                System.out.println(word + "= " + result.asParseString());
            }
        }
    }

    private void assertLongParses(DynamicLexiconGraph graph, String word, String... parses) {
        SimpleParser parser = new SimpleParser(graph);

        List<ParseResult> results = parser.parse(word);
        Assert.assertTrue("Cannot parse:" + word, results.size() > 0);

        Set<String> parseStrins = new HashSet<String>();
        for (ParseResult result : results) {
            parseStrins.add(result.asParseString());
        }
        for (String parse : parses) {
            Assert.assertTrue("Cannot parse: parse for:" + word, parseStrins.contains(parse));
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

        static SuffixForm Dim_CIK = getForm(new Suffix("Dim"), ">cI~k");
        static SuffixForm P1sg_Im = getForm(new Suffix("P1sg"), "Im");
        static SuffixForm Dat_yA = getForm(new Suffix("Dat"), "+yA");
        static SuffixForm Dat_nA = getForm(new Suffix("Dat"), "nA");
        static SuffixFormTemplate Pnon_TEMPLATE = getTemplate("Pnon_TEMPLATE", new Suffix("Pnon"));
        static SuffixFormTemplate Nom_TEMPLATE = getTemplate("Nom_TEMPLATE", new Suffix("Nom"));
        static SuffixFormTemplate A3sg_TEMPLATE = getTemplate("A3sg_TEMPLATE", new Suffix("A3sg"));
        static SuffixForm A3pl_lAr = getForm(new Suffix("A3pl"), "lAr");
        static Suffix Noun_Root = new Suffix("Noun");
        static SuffixFormTemplate Noun_TEMPLATE = getTemplate("Noun_TEMPLATE", Noun_Root);
        static SuffixForm Noun_Default = getNull("Noun_Default", Noun_TEMPLATE);
        static SuffixFormTemplate Noun_Deriv = getTemplate("Noun2Noun", Noun_Root, TerminationType.NON_TERMINAL);

        NounSuffixes() {

            Noun_TEMPLATE.connections.add(A3pl_lAr, A3sg_TEMPLATE);
            Noun_TEMPLATE.indirectConnections.add(P1sg_Im, Pnon_TEMPLATE, Nom_TEMPLATE, Dat_yA, Dat_nA, Dim_CIK, Noun_Deriv);

            Noun_Default.connections.add(Noun_TEMPLATE.connections);
            Noun_Default.indirectConnections.add(Noun_TEMPLATE.indirectConnections).remove(Dat_nA);

            Noun_Deriv.connections.add(Dim_CIK);
            //Noun2Noun.indirectConnections.add(Noun_TEMPLATE.allConnections());

            A3sg_TEMPLATE.connections.add(Pnon_TEMPLATE, P1sg_Im);
            A3sg_TEMPLATE.indirectConnections.add(Nom_TEMPLATE, Dat_yA, Dat_nA, Noun_Deriv).add(Noun_Deriv.allConnections());

            A3pl_lAr.connections.add(P1sg_Im, Pnon_TEMPLATE);
            A3pl_lAr.indirectConnections.add(Nom_TEMPLATE, Dat_yA);

            P1sg_Im.connections.add(Nom_TEMPLATE, Dat_yA);

            Pnon_TEMPLATE.connections.add(Nom_TEMPLATE, Dat_nA, Dat_yA);
            Pnon_TEMPLATE.indirectConnections.add(Noun_Deriv).add(Noun_Deriv.allConnections());

            Nom_TEMPLATE.connections.add(Noun_Deriv);
            Nom_TEMPLATE.indirectConnections.add(Noun_Deriv.allConnections());

            Dim_CIK.connections.add(Noun_Default.connections);
            Dim_CIK.indirectConnections.add(Noun_Default.allConnections().remove(Dim_CIK));

            registerForms(Noun_TEMPLATE, Noun_Deriv, A3sg_TEMPLATE, Pnon_TEMPLATE, Nom_TEMPLATE);
            registerForms(Noun_Default, A3pl_lAr, P1sg_Im, Dat_yA, Dat_nA, Dim_CIK);
        }


        public SuffixForm getRootSet(DictionaryItem item, SuffixData successorConstraint) {

            if (successorConstraint.isEmpty()) {
                switch (item.primaryPos) {
                    case Noun:
                        return Noun_Default;
                    default:
                        throw new UnsupportedOperationException("In this class only some noun morphemes exist.");
                }
            } else {
                switch (item.primaryPos) {
                    case Noun:
                        NullSuffixForm nullForm = generateNullFormFromTemplate(Noun_TEMPLATE, successorConstraint);
                        registerForm(nullForm.copy());
                        return nullForm;
                    default:
                        throw new UnsupportedOperationException("In this class only some noun morphemes exist.");

                }

            }
        }

        public SuffixData[] defineSuccessorSuffixes(DictionaryItem item) {

            SuffixData original = new SuffixData();
            SuffixData modified = new SuffixData();

            PrimaryPos primaryPos = item.primaryPos;

            switch (primaryPos) {
                case Noun:
                    getForNoun(item, original, modified);
                    break;
                default:
                    throw new UnsupportedOperationException("In this class only some noun morphemes exist.");
            }
            return new SuffixData[]{original, modified};
        }

        void getForNoun(DictionaryItem item, SuffixData original, SuffixData modified) {

            for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case CompoundP3sg:
                        original.add(Noun_Default.allConnections().remove(Dim_CIK, A3pl_lAr, Dat_yA).add(Dat_nA));
                        modified.add(Dim_CIK, Noun_Deriv, A3sg_TEMPLATE, Pnon_TEMPLATE, Nom_TEMPLATE, A3pl_lAr);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
