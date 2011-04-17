package zemberek3.lexicon.graph;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;

import java.io.IOException;
import java.util.*;

public class LexiconGraphTest {

    SuffixProvider suffixProvider = new TurkishSuffixes().getSuffixProvider();

    @Test
    public void testSimpleNouns() throws IOException {
        String[] nouns = {"elma", "armut"};
        List<DictionaryItem> items = getItems(nouns);
        LexiconGraph graph = new LexiconGraph(items, suffixProvider);
        graph.generate();
        Assert.assertEquals(3, graph.getStems().size());
        StemNode nodeArmud = getNode("armud", graph);
        Assert.assertNotNull(nodeArmud);
        Assert.assertEquals("armud", nodeArmud.surfaceForm);
        Set<SuffixFormSet> sets = nodeArmud.getSuffixRootNode().suffixSet.getSuccSetCopy();
        Assert.assertTrue(Collections.disjoint(sets, Arrays.asList(TurkishSuffixes.Pl_lAr, TurkishSuffixes.A2sg_sIn)));
    }

    private List<DictionaryItem> getItems(String[] lines) {
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

}
