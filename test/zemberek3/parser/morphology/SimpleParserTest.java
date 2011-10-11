package zemberek3.parser.morphology;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.graph.*;

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
        assertUnParseable(graph, "elmacığ", "elmacıka","elmamcık","elmayacık","elmalarcık");
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
        assertHasParses(graph, "zeytinyağım", "zeytinyağına", "zeytinyağı", "zeytinyağcık","zeytinyağcığa", "zeytinyağlarım");
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

        static SuffixFormSet Dim_CIK = getSet("Dim", ">cI~k");
        static SuffixFormSet P1sg_Im = getSet("P1sg", "Im");
        static SuffixFormSet Dat_yA = getSet("Dat", "+yA");
        static SuffixFormSet Dat_nA = getSet("Dat", "nA");
        static SuffixFormSet Pnon_TEMPLATE = getTemplate("Pnon", "Pnon_TEMPLATE");
        static SuffixFormSet Nom_TEMPLATE = getTemplate("Nom", "Nom_TEMPLATE");
        static SuffixFormSet A3sg_TEMPLATE = getTemplate("A3sg", "A3sg_TEMPLATE");
        static SuffixFormSet A3pl_lAr = getSet("A3pl", "lAr");
        static Suffix Noun_Root = new Suffix("Noun");
        static SuffixFormSet Noun_TEMPLATE = getTemplate("Noun_TEMPLATE", Noun_Root);
        static SuffixFormSet Noun_Default = getNull("Noun_Default", Noun_Root);
        static SuffixFormSet Noun_Deriv = getTemplate("Noun2Noun", Noun_Root, TerminationType.NON_TERMINAL);

        NounSuffixes() {

            Noun_TEMPLATE.directSuccessors.add(A3pl_lAr, A3sg_TEMPLATE);
            Noun_TEMPLATE.successors.add(P1sg_Im, Pnon_TEMPLATE, Nom_TEMPLATE, Dat_yA, Dat_nA, Dim_CIK, Noun_Deriv);

            Noun_Default.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
            Noun_Default.successors.add(Noun_TEMPLATE.successors).remove(Dat_nA);

            Noun_Deriv.directSuccessors.add(Dim_CIK);
            //Noun2Noun.successors.add(Noun_TEMPLATE.allSuccessors());

            A3sg_TEMPLATE.directSuccessors.add(Pnon_TEMPLATE, P1sg_Im);
            A3sg_TEMPLATE.successors.add(Nom_TEMPLATE, Dat_yA, Dat_nA, Noun_Deriv).add(Noun_Deriv.allSuccessors());

            A3pl_lAr.directSuccessors.add(P1sg_Im, Pnon_TEMPLATE);
            A3pl_lAr.successors.add(Nom_TEMPLATE, Dat_yA);

            P1sg_Im.directSuccessors.add(Nom_TEMPLATE, Dat_yA);

            Pnon_TEMPLATE.directSuccessors.add(Nom_TEMPLATE, Dat_nA, Dat_yA);
            Pnon_TEMPLATE.successors.add(Noun_Deriv).add(Noun_Deriv.allSuccessors());

            Nom_TEMPLATE.directSuccessors.add(Noun_Deriv);
            Nom_TEMPLATE.successors.add(Noun_Deriv.allSuccessors());

            Dim_CIK.directSuccessors.add(Noun_Default.directSuccessors);
            Dim_CIK.successors.add(Noun_Default.allSuccessors().remove(Dim_CIK));

            registerForms(Noun_TEMPLATE, Noun_Deriv, Noun_Default, A3sg_TEMPLATE, A3pl_lAr, P1sg_Im, Pnon_TEMPLATE, Dat_yA, Dat_nA, Dim_CIK, Nom_TEMPLATE);
        }


        public SuffixFormSet getRootSet(DictionaryItem item, SuffixData successorConstraint) {

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
                        SuffixFormSet copyOfTemplate = Noun_TEMPLATE.copy(idMaker.getNew(Noun_TEMPLATE.id));
                        copyOfTemplate.directSuccessors.retain(successorConstraint);
                        copyOfTemplate.successors.retain(successorConstraint);
                        if (formSetLookup.containsKey(copyOfTemplate)) {
                            copyOfTemplate = formSetLookup.get(copyOfTemplate);
                        } else {
                            registerForm(copyOfTemplate);
                        }
                        return copyOfTemplate;
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
                        original.add(Noun_Default.allSuccessors().remove(Dim_CIK, A3pl_lAr, Dat_yA).add(Dat_nA));
                        modified.add(Dim_CIK, Noun_Deriv, A3sg_TEMPLATE, Pnon_TEMPLATE, Nom_TEMPLATE, A3pl_lAr);
                        break;
                    default:
                        break;
                }
            }
        }


    }

   static class VerbSuffixes extends DynamicSuffixProvider {

        static SuffixFormSet Dim_CIK = getSet("Dim", ">cI~k");
        static SuffixFormSet P1sg_Im = getSet("P1sg", "Im");
        static SuffixFormSet Dat_yA = getSet("Dat", "+yA");
        static SuffixFormSet Dat_nA = getSet("Dat", "nA");
        static SuffixFormSet Pnon_TEMPLATE = getTemplate("Pnon", "Pnon_TEMPLATE");
        static SuffixFormSet Nom_TEMPLATE = getTemplate("Nom", "Nom_TEMPLATE");
        static SuffixFormSet A3sg_TEMPLATE = getTemplate("A3sg", "A3sg_TEMPLATE");
        static SuffixFormSet A3pl_lAr = getSet("A3pl", "lAr");
        static Suffix Noun_Root = new Suffix("Noun");
        static SuffixFormSet Noun_TEMPLATE = getTemplate("Noun_TEMPLATE", Noun_Root);
        static SuffixFormSet Noun_Default = getNull("Noun_Default", Noun_Root);
        static SuffixFormSet Noun_Deriv = getTemplate("Noun2Noun", Noun_Root, TerminationType.NON_TERMINAL);

        VerbSuffixes() {

            Noun_TEMPLATE.directSuccessors.add(A3pl_lAr, A3sg_TEMPLATE);
            Noun_TEMPLATE.successors.add(P1sg_Im, Pnon_TEMPLATE, Nom_TEMPLATE, Dat_yA, Dat_nA, Dim_CIK, Noun_Deriv);

            Noun_Default.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
            Noun_Default.successors.add(Noun_TEMPLATE.successors).remove(Dat_nA);

            Noun_Deriv.directSuccessors.add(Dim_CIK);
            //Noun2Noun.successors.add(Noun_TEMPLATE.allSuccessors());

            A3sg_TEMPLATE.directSuccessors.add(Pnon_TEMPLATE, P1sg_Im);
            A3sg_TEMPLATE.successors.add(Nom_TEMPLATE, Dat_yA, Dat_nA, Noun_Deriv).add(Noun_Deriv.allSuccessors());

            A3pl_lAr.directSuccessors.add(P1sg_Im, Pnon_TEMPLATE);
            A3pl_lAr.successors.add(Nom_TEMPLATE, Dat_yA);

            P1sg_Im.directSuccessors.add(Nom_TEMPLATE, Dat_yA);

            Pnon_TEMPLATE.directSuccessors.add(Nom_TEMPLATE, Dat_nA, Dat_yA);
            Pnon_TEMPLATE.successors.add(Noun_Deriv).add(Noun_Deriv.allSuccessors());

            Nom_TEMPLATE.directSuccessors.add(Noun_Deriv);
            Nom_TEMPLATE.successors.add(Noun_Deriv.allSuccessors());

            Dim_CIK.directSuccessors.add(Noun_Default.directSuccessors);
            Dim_CIK.successors.add(Noun_Default.allSuccessors().remove(Dim_CIK));

            registerForms(Noun_TEMPLATE, Noun_Deriv, Noun_Default, A3sg_TEMPLATE, A3pl_lAr, P1sg_Im, Pnon_TEMPLATE, Dat_yA, Dat_nA, Dim_CIK, Nom_TEMPLATE);
        }


        public SuffixFormSet getRootSet(DictionaryItem item, SuffixData successorConstraint) {

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
                        SuffixFormSet copyOfTemplate = Noun_TEMPLATE.copy(idMaker.getNew(Noun_TEMPLATE.id));
                        copyOfTemplate.directSuccessors.retain(successorConstraint);
                        copyOfTemplate.successors.retain(successorConstraint);
                        if (formSetLookup.containsKey(copyOfTemplate)) {
                            copyOfTemplate = formSetLookup.get(copyOfTemplate);
                        } else {
                            registerForm(copyOfTemplate);
                        }
                        return copyOfTemplate;
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
                        original.add(Noun_Default.allSuccessors().remove(Dim_CIK, A3pl_lAr, Dat_yA).add(Dat_nA));
                        modified.add(Dim_CIK, Noun_Deriv, A3sg_TEMPLATE, Pnon_TEMPLATE, Nom_TEMPLATE, A3pl_lAr);
                        break;
                    default:
                        break;
                }
            }
        }


    }

}
