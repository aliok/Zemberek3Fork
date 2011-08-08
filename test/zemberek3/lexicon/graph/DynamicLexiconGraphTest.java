package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import zemberek3.lexicon.*;

import java.io.IOException;
import java.util.*;

public class DynamicLexiconGraphTest {

    TestSuffixes1 suffixes = new TestSuffixes1();
    StemNodeGenerator stemNodeGenerator = new StemNodeGenerator();
    SuffixNodeGenerator suffixNodeGenerator = new SuffixNodeGenerator();
    TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixes.getSuffixProvider());

    @Test
    public void testSimpleNouns() throws IOException {
        SuffixProvider suffixProvider = new TurkishSuffixes().getSuffixProvider();
        String[] nouns = {"elma", "armut"};
        List<DictionaryItem> items = getItems(nouns, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph();
        for (DictionaryItem item : items) {
            graph.addDictionaryItem(item);
        }
        //graph.generate();
        //Assert.assertEquals(3, graph.getStemNodes().size());
        //StemNode nodeArmud = getNode("armud", graph);
        //Assert.assertNotNull(nodeArmud);
        //Assert.assertEquals("armud", nodeArmud.surfaceForm);
        //Set<SuffixFormSet> sets = nodeArmud.getRootSuffixNode().suffixSet.getSuccSetCopy();
    }

    private DictionaryItem getDictionaryItem(String line) {
        return loader.loadFromString(line);
    }

    private List<DictionaryItem> getItems(String[] lines, SuffixProvider suffixProvider) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        List<DictionaryItem> items = new ArrayList<DictionaryItem>();
        for (String line : lines) {
            items.add(loader.loadFromString(line));
        }
        return items;
    }


    public StemNode getNode(String surface, LexiconGraph graph) {
        List<StemNode> nodes = graph.getStems();
        for (StemNode node : nodes) {
            if (node.surfaceForm.equals(surface))
                return node;
        }
        return null;
    }

    class TestSuffixes1 {
        public Suffix Dat = new Suffix("Dat");
        public SuffixFormSet Dat_yA = new SuffixFormSet(Dat, "+yA");

        public Suffix Loc = new Suffix("Loc");
        public SuffixFormSet Loc_dA = new SuffixFormSet(Loc, ">dA");

        public Suffix P1sg = new Suffix("P1sg");
        public SuffixFormSet P1sg_Im = new SuffixFormSet(P1sg, "Im");

        public Suffix P3sg = new Suffix("P3sg");
        public SuffixFormSet P3sg_sI = new SuffixFormSet(P3sg, "+sI");

        public Suffix P1pl = new Suffix("P1pl");
        public SuffixFormSet P1pl_ImIz = new SuffixFormSet(P1pl, "ImIz");

        public Suffix Pnon = new Suffix("Pnon");
        public SuffixFormSet Pnon_EMPTY = new SuffixFormSet("Pnon_EMPTY", Pnon, "");

        public Suffix Nom = new Suffix("Nom");
        public SuffixFormSet Nom_EMPTY = new SuffixFormSet("Nom_EMPTY", Nom, ""); // ben

        public Suffix A3sg = new Suffix("A3sg");
        public SuffixFormSet A3sg_EMPTY = new SuffixFormSet("A3sg_EMPTY", A3sg, ""); // gel-di-, o-

        public Suffix A3pl = new Suffix("A3pl");
        public SuffixFormSet A3pl_lAr = new SuffixFormSet(A3pl, "lAr"); // gel-ecek-ler

        public Suffix NounRoot = new Suffix("NounRoot");
        public SuffixFormSet Noun_Main = new SuffixFormSet("Noun_Main", NounRoot, "");

        public SuffixProvider getSuffixProvider() {
            TurkishSuffixProvider provider = new TurkishSuffixProvider();
            provider.addForms(
                    Noun_Main, A3sg_EMPTY, A3pl_lAr,
                    P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz,
                    Nom_EMPTY, Dat_yA, Loc_dA);
            return provider;
        }

        public TestSuffixes1() {
            Noun_Main.add(A3pl_lAr, A3sg_EMPTY);
            A3sg_EMPTY.add(P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz);
            A3pl_lAr.add(P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz);
            P1sg_Im.add(Nom_EMPTY, Dat_yA, Loc_dA);
            P3sg_sI.add(Nom_EMPTY, Dat_yA, Loc_dA);
            P1pl_ImIz.add(Nom_EMPTY, Dat_yA, Loc_dA);
        }

    }

    class TurkishSuffixProvider implements SuffixProvider {
        Set<SuffixFormSet> forms = Sets.newHashSet();
        Map<String, SuffixFormSet> formSetLookup = Maps.newHashMap();
        Map<String, Suffix> suffixLookup = Maps.newHashMap();
        ArrayListMultimap<String, SuffixFormSet> formsPerSuffix = ArrayListMultimap.create(100, 2);

        public Suffix getSuffixById(String suffixId) {
            return suffixLookup.get(suffixId);
        }

        public List<SuffixFormSet> getFormsBySuffixId(String suffixId) {
            return formsPerSuffix.get(suffixId);
        }

        public SuffixFormSet getFormById(String suffixSetId) {
            return formSetLookup.get(suffixSetId);
        }

        public Iterable<SuffixFormSet> getAllForms() {
            return forms;
        }

        void addForms(SuffixFormSet... setz) {

            for (SuffixFormSet suffixFormSet : setz) {
                if (forms.contains(suffixFormSet))
                    continue;
                forms.add(suffixFormSet);
                formSetLookup.put(suffixFormSet.id, suffixFormSet);
                suffixLookup.put(suffixFormSet.getSuffix().id, suffixFormSet.getSuffix());
                formsPerSuffix.put(suffixFormSet.getSuffix().id, suffixFormSet);
            }
        }

        public Collection<SuffixFormSet> getSets(String... ids) {
            Set<SuffixFormSet> sets = Sets.newHashSet();
            for (String id : ids) {
                sets.add(formSetLookup.get(id));
            }
            return sets;
        }
    }

}
