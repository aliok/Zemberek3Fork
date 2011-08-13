package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import zemberek3.lexicon.*;

import java.io.IOException;
import java.util.*;

public class DynamicLexiconGraphTest {

    @Test
    public void testSimpleNouns() throws IOException {
        SuffixProvider suffixProvider =getProvider1();
        String[] nouns = {"elma"};
        List<DictionaryItem> items = getItems(nouns, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
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

    private List<DictionaryItem> getItems(String[] lines, SuffixProvider suffixProvider) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        List<DictionaryItem> items = new ArrayList<DictionaryItem>();
        for (String line : lines) {
            items.add(loader.loadFromString(line));
        }
        return items;
    }

    public StemNode getNode(String surface, DynamicLexiconGraph graph) {
        Set<StemNode> nodes = graph.getStemNodes();
        for (StemNode node : nodes) {
            if (node.surfaceForm.equals(surface))
                return node;
        }
        return null;
    }

    public SuffixProvider getProvider1() {
        Suffix Dat = new Suffix("Dat");
        SuffixFormSet Dat_yA = new SuffixFormSet(Dat, "+yA");
        Suffix Loc = new Suffix("Loc");
        SuffixFormSet Loc_dA = new SuffixFormSet(Loc, ">dA");
        Suffix P1sg = new Suffix("P1sg");
        SuffixFormSet P1sg_Im = new SuffixFormSet(P1sg, "Im");
        Suffix P3sg = new Suffix("P3sg");
        SuffixFormSet P3sg_sI = new SuffixFormSet(P3sg, "+sI");
        Suffix P1pl = new Suffix("P1pl");
        SuffixFormSet P1pl_ImIz = new SuffixFormSet(P1pl, "ImIz");
        Suffix Pnon = new Suffix("Pnon");
        SuffixFormSet Pnon_EMPTY = new SuffixFormSet("Pnon_EMPTY", Pnon, "");
        Suffix Nom = new Suffix("Nom");
        SuffixFormSet Nom_EMPTY = new SuffixFormSet("Nom_EMPTY", Nom, ""); // ben
        Suffix A3sg = new Suffix("A3sg");
        SuffixFormSet A3sg_EMPTY = new SuffixFormSet("A3sg_EMPTY", A3sg, ""); // gel-di-, o-
        Suffix A3pl = new Suffix("A3pl");
        SuffixFormSet A3pl_lAr = new SuffixFormSet(A3pl, "lAr"); // gel-ecek-ler
        DynamicSuffixes suffixes = new DynamicSuffixes();
        DynamicSuffixes.Noun_Main.add(A3pl_lAr, A3sg_EMPTY);
        A3sg_EMPTY.add(P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz);
        A3pl_lAr.add(P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz);
        Pnon_EMPTY.add(Nom_EMPTY, Dat_yA, Loc_dA);
        P1sg_Im.add(Nom_EMPTY, Dat_yA, Loc_dA);
        P3sg_sI.add(Nom_EMPTY, Dat_yA, Loc_dA);
        P1pl_ImIz.add(Nom_EMPTY, Dat_yA, Loc_dA);
        suffixes.addSuffixForms(
                DynamicSuffixes.Noun_Main, A3sg_EMPTY, A3pl_lAr,
                P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz,
                Nom_EMPTY, Dat_yA, Loc_dA);
        return suffixes.getSuffixProvider();
    }

}
