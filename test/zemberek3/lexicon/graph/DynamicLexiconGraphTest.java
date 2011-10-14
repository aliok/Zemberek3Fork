package zemberek3.lexicon.graph;

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
        //Set<SuffixForm> sets = nodeArmud.getRootSuffixNode().suffixSet.getSuccSetCopy();
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
        SuffixForm Dat_yA = new SuffixForm(Dat, "+yA");
        Suffix Loc = new Suffix("Loc");
        SuffixForm Loc_dA = new SuffixForm(Loc, ">dA");
        Suffix P1sg = new Suffix("P1sg");
        SuffixForm P1sg_Im = new SuffixForm(P1sg, "Im");
        Suffix P3sg = new Suffix("P3sg");
        SuffixForm P3sg_sI = new SuffixForm(P3sg, "+sI");
        Suffix P1pl = new Suffix("P1pl");
        SuffixForm P1pl_ImIz = new SuffixForm(P1pl, "ImIz");
        Suffix Pnon = new Suffix("Pnon");
        SuffixForm Pnon_EMPTY = new SuffixForm("Pnon_TEMPLATE", Pnon, "");
        Suffix Nom = new Suffix("Nom");
        SuffixForm Nom_EMPTY = new SuffixForm("Nom_TEMPLATE", Nom, ""); // ben
        Suffix A3sg = new Suffix("A3sg");
        SuffixForm A3sg_EMPTY = new SuffixForm("A3sg_TEMPLATE", A3sg, ""); // gel-di-, o-
        Suffix A3pl = new Suffix("A3pl");
        SuffixForm A3pl_lAr = new SuffixForm(A3pl, "lAr"); // gel-ecek-ler
        DynamicSuffixes suffixes = new DynamicSuffixes();
    //    DynamicSuffixes.Noun_TEMPLATE.getSuccessors().add(A3pl_lAr, A3sg_TEMPLATE);
        A3sg_EMPTY.successors.add(P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz);
        A3pl_lAr.successors.add(P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz);
        Pnon_EMPTY.successors.add(Nom_EMPTY, Dat_yA, Loc_dA);
        P1sg_Im.successors.add(Nom_EMPTY, Dat_yA, Loc_dA);
        P3sg_sI.successors.add(Nom_EMPTY, Dat_yA, Loc_dA);
        P1pl_ImIz.successors.add(Nom_EMPTY, Dat_yA, Loc_dA);
        suffixes.addSuffixForms(
               /* DynamicSuffixes.Noun_TEMPLATE, */A3sg_EMPTY, A3pl_lAr,
                P1sg_Im, P3sg_sI, Pnon_EMPTY, P1pl_ImIz,
                Nom_EMPTY, Dat_yA, Loc_dA);
        return suffixes.getSuffixProvider();
    }

}
