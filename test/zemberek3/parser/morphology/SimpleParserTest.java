package zemberek3.parser.morphology;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.graph.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleParserTest {

    @Test
    public void testVoicing() {
        DynamicLexiconGraph graph = getLexiconGraph("armut");
        assertHasParses(graph, "armutlar", "armuda", "armut", "armutlara");
        assertUnParseable(graph, "armud", "armuta", "armudlar");
    }

    @Test
    public void testSuffixNonDeterminism() {
        DynamicLexiconGraph graph = getLexiconGraph("elma");
        assertHasParses(graph, "elmacığa", "elmacık");
        assertUnParseable(graph, "elmacığ", "elmacıka");
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
        assertHasParses(graph, "zeytinyağına", "zeytinyağım", "zeytinyağları");
        assertUnParseable(graph, "zeytinyağılar", "zeytinyağıcık");
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


    static class RandomIdMaker {
        Random random = new Random();
        Set<String> ids = Collections.synchronizedSet(new HashSet<String>());
        int letterCount;

        RandomIdMaker(int letterCount) {
            this.letterCount = letterCount;
        }

        String getNew() {
            StringBuilder sb = new StringBuilder(letterCount);
            for (int i = 0; i < letterCount; i++) {
                sb.append((char) (random.nextInt(25) + 'A'));
            }
            String res = sb.toString();
            if (!ids.contains(res)) {
                ids.add(res);
                return res;
            }
            return getNew();
        }

        String getNew(String toAppend) {
            return toAppend + "_" + getNew();
        }
    }

    static class NounSuffixes extends DynamicSuffixProvider {

        RandomIdMaker idMaker = new RandomIdMaker(3);

        static SuffixFormSet Dim_CIK = getSet("Dim", ">cI~k");
        static SuffixFormSet P1sg_Im = getSet("P1sg", "Im");
        static SuffixFormSet Dat_yA = getSet("Dat", "+yA");
        static SuffixFormSet Dat_nA = getSet("Dat", "nA");
        static SuffixFormSet Pnon_EMPTY = getTemplate("Pnon", "Pnon_EMPTY");
        static SuffixFormSet Nom_EMPTY = getTemplate("Nom", "Nom_EMPTY");
        static SuffixFormSet A3sg_EMPTY = getTemplate("A3sg", "A3sg_EMPTY");
        static SuffixFormSet A3pl_lAr = getSet("A3pl", "lAr");
        static SuffixFormSet Noun_Main = getTemplate("Noun", "Noun_Main");

        DynamicSuffixes suffixes = new DynamicSuffixes();

        private static SuffixFormSet getSet(String suffixId, String generationStr) {
            return new SuffixFormSet(new Suffix(suffixId), generationStr);
        }

        private static SuffixFormSet getTemplate(String suffixId, String id) {
            return SuffixFormSet.getTemplate(id, new Suffix(suffixId));
        }

        NounSuffixes() {

            Noun_Main.directSuccessors.add(A3pl_lAr, A3sg_EMPTY);
            Noun_Main.successors.add(P1sg_Im, Pnon_EMPTY, Nom_EMPTY, Dat_yA, Dim_CIK);

            A3sg_EMPTY.directSuccessors.add(Pnon_EMPTY, P1sg_Im);
            A3sg_EMPTY.successors.add(Nom_EMPTY, Dat_yA, Dat_nA, Dim_CIK);

            A3pl_lAr.directSuccessors.add(P1sg_Im, Pnon_EMPTY);
            A3pl_lAr.successors.add(Nom_EMPTY, Dat_yA);

            P1sg_Im.directSuccessors.add(Nom_EMPTY, Dat_yA);
            Pnon_EMPTY.successors.add(Dim_CIK);

            Pnon_EMPTY.directSuccessors.add(Nom_EMPTY, Dat_nA, Dat_yA);
            Pnon_EMPTY.successors.add(Dim_CIK);

            Nom_EMPTY.directSuccessors.add(Dim_CIK);
            Dat_yA.directSuccessors.add(Dim_CIK);

            Dim_CIK.directSuccessors.add(Noun_Main);
            Dim_CIK.successors.add(Noun_Main.directSuccessors);
            Dim_CIK.successors.add(Noun_Main.successors.remove(Dim_CIK));

            registerForms(Noun_Main, A3sg_EMPTY, A3pl_lAr, P1sg_Im, Pnon_EMPTY, Dat_yA, Dat_nA, Dim_CIK, Nom_EMPTY);
        }

/*        public SuffixFormSet[] getRootForms(DictionaryItem item) {
            SuffixData[] datas = defineSuccessorSuffixes(item);
            SuffixFormSet[] sets = new SuffixFormSet[datas.length];
            int i = 0;
            for (SuffixData data : datas) {
                sets[i++] = addAndGet(item, data);
            }
            return sets;
        }*/

        public void registerForms(SuffixFormSet... setz) {
            for (SuffixFormSet formSet : setz) {
                registerForm(formSet);
            }
        }

        public void registerForm(SuffixFormSet formSet) {

            if (formSet.isTemplate()) {
                Set<SuffixFormSet> allSuccessors = formSet.allSuccessors().set;
                formSet = formSet.copy(idMaker.getNew(formSet.id));
                formSet.directSuccessors.retain(allSuccessors);
                formSet.successors.retain(allSuccessors);
                if (formSetLookup.containsKey(formSet)) {
                    formSet = formSetLookup.get(formSet);
                }
            }

            if (formSetLookup.containsKey(formSet)) {
                return;
            } else {
                formSetLookup.put(formSet, formSet);
            }

            Set<SuffixFormSet> allSuccessors = formSet.allSuccessors().set;
            Set<SuffixFormSet> toRemove = new HashSet<SuffixFormSet>();
            Set<SuffixFormSet> toAdd = new HashSet<SuffixFormSet>();
            for (SuffixFormSet directSuccessor : formSet.directSuccessors) {
                if (directSuccessor.isTemplate()) {
                    SuffixFormSet copyOfTemplate = directSuccessor.copy(idMaker.getNew(directSuccessor.id));
                    copyOfTemplate.directSuccessors.retain(allSuccessors);
                    copyOfTemplate.successors.retain(allSuccessors);
                    if (formSetLookup.containsKey(copyOfTemplate)) {
                        copyOfTemplate = formSetLookup.get(copyOfTemplate);
                    }
                    toRemove.add(directSuccessor);
                    toAdd.add(copyOfTemplate);
                }
            }
            for (SuffixFormSet suffixFormSet : formSet.successors) {
                if (suffixFormSet.template)
                    toRemove.add(suffixFormSet);
            }
            formSet.getDirectSuccessors().remove(toRemove);
            formSet.getSuccessors().remove(toRemove);
            formSet.getDirectSuccessors().add(toAdd);
            for (SuffixFormSet suffixFormSet : toAdd) {
                registerForm(suffixFormSet);
            }
        }


        public SuffixFormSet getRootSet(DictionaryItem item, SuffixData successorConstraint) {
            switch (item.primaryPos) {
                case Noun:
                    return addAndGet(Noun_Main, successorConstraint);
                default:
                    return addAndGet(Noun_Main, successorConstraint);
            }
        }

        public SuffixFormSet addAndGet(SuffixFormSet setToCopy, SuffixData successorConstraint) {
            return null;
/*            final String uniqueId = setToCopy.id + String.valueOf(idCounter.incrementAndGet());
            SuffixData data = new SuffixData();
            for (SuffixFormSet formSet : copy.directSuccessors) {

            }


            if (successorConstraint.isEmpty()) {
                modified = setToCopy.copy(uniqueId);
            } else
                modified = setToCopy.copy(uniqueId, successorConstraint);
            if (formSetLookup.containsKey(modified)) {
                modified = formSetLookup.get(modified);
                idCounter.decrementAndGet();
            } else {
                formSetLookup.put(modified, modified);
                connect(modified);
            }
            return modified;*/
        }

        public DynamicSuffixes getSuffixes() {
            return suffixes;
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

            for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case CompoundP3sg:
                        original.add(Noun_Main.allSuccessors().remove(Dim_CIK, A3pl_lAr, Dat_yA).add(Dat_nA));
                        modified.add(Dim_CIK, A3sg_EMPTY, Pnon_EMPTY, Nom_EMPTY, A3pl_lAr);
                        break;
                    default:
                        break;
                }
            }
        }


    }

}
