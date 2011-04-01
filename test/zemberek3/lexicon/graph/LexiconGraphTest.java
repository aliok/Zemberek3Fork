package zemberek3.lexicon.graph;

import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.SuffixFormSet;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;

import java.io.IOException;
import java.util.*;

public class LexiconGraphTest {

    @Test
    public void testSimpleNouns() throws IOException {
        String[] nouns = {"elma", "armut"};
        List<DictionaryItem> items = getItems(nouns);
        LexiconGraph graph = new LexiconGraph(items, new TurkishSuffixes());
        graph.generate();
        Assert.assertEquals(3, graph.getStems().size());
        StemNode nodeArmud = getNode("armud", graph);
        Assert.assertNotNull(nodeArmud);
        Assert.assertEquals("armud", nodeArmud.surfaceForm);
        Assert.assertEquals(TurkishSuffixes.Noun_Exp_V, nodeArmud.getSuffixRootNode().suffixSet);
        Set<SuffixFormSet> sets = nodeArmud.getSuffixRootNode().suffixSet.getSuccessors();
        Assert.assertTrue(Collections.disjoint(sets, Arrays.asList(TurkishSuffixes.Pl_lAr, TurkishSuffixes.A2sg_sIn)));
    }

    private List<DictionaryItem> getItems(String[] lines) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader();
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
