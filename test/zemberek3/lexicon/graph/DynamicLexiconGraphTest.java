package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import zemberek3.lexicon.*;

import java.io.IOException;
import java.util.*;

public class DynamicLexiconGraphTest {

    Suffixes suffixes = new DummySuffixes1();
    StemNodeGenerator stemNodeGenerator = new StemNodeGenerator();
    SuffixNodeGenerator suffixNodeGenerator = new SuffixNodeGenerator();
    TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixes.getSuffixProvider());

    @Test
    public void testSimpleNouns() throws IOException {
        SuffixProvider suffixProvider = suffixes.getSuffixProvider();
        String[] nouns = {"elma", "armut"};
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

}
