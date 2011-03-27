package zemberek3.lexicon;

import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;
import zemberek3.structure.AttributeSet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static zemberek3.lexicon.PrimaryPos.Noun;
import static zemberek3.lexicon.PrimaryPos.Verb;
import static zemberek3.lexicon.RootAttr.*;

public class TurkishDictionaryLoaderTest {
    @Test
    public void loadNounsFromFileTest() throws IOException {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader();
        List<DictionaryItem> items = loader.load(new File("test/data/test-dictionary-nouns.txt"));

        Assert.assertFalse(items.isEmpty());
        for (DictionaryItem item : items) {
            Assert.assertTrue(item.primaryPos == Noun);
        }
    }

    @Test
    public void nounInferenceTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader();
        DictionaryItem item = loader.loadFromString("elma");
        Assert.assertEquals("elma", item.lemma);
        Assert.assertEquals("elma", item.clean());
        Assert.assertEquals(Noun, item.primaryPos);

        item = loader.loadFromString("elma [Pos:Noun]");
        Assert.assertEquals("elma", item.lemma);
        Assert.assertEquals("elma", item.clean());
        Assert.assertEquals(Noun, item.primaryPos);
    }


    @Test
    public void verbInferenceTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader();
        DictionaryItem item = loader.loadFromString("gelmek");
        Assert.assertEquals("gel", item.clean());
        Assert.assertEquals("gelmek", item.lemma);
        Assert.assertEquals(Verb, item.primaryPos);

        String[] verbs = {"germek", "yarmak", "salmak", "yermek [Pos:Verb]", "etmek [Pos:Verb; A:Voicing]"};
        for (String verb : verbs) {
            item = loader.loadFromString(verb);
            Assert.assertEquals(Verb, item.primaryPos);
        }
    }

    @Test
    public void nounVoicingTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader();
        String[] voicing = {"kabak", "kabak [A:Voicing]", "psikolog", "havuç", "turp [A:Voicing]", "galip", "nohut", "cenk"};
        for (String s : voicing) {
            DictionaryItem item = loader.loadFromString(s);
            Assert.assertEquals(Noun, item.primaryPos);
            Assert.assertEquals("error in:" + s, new AttributeSet<RootAttr>(Voicing), item.attrs);
        }

        String[] novoicing = {"kek", "top", "kulp", "takat [A:NoVoicing]"};
        for (String s : novoicing) {
            DictionaryItem item = loader.loadFromString(s);
            Assert.assertEquals(Noun, item.primaryPos);
            Assert.assertEquals("error in:" + s, new AttributeSet<RootAttr>(NoVoicing), item.attrs);
        }
    }

    @Test
    public void nounAttributesTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader();

        List<ItemAttrPair> testList = Lists.newArrayList(
                testPair("takat [A:NoVoicing, InverseHarmony]", NoVoicing, InverseHarmony),
                testPair("nakit [A: LastVowelDrop]", Voicing, LastVowelDrop),
                testPair("ret [A:Voicing, Doubling]", Voicing, Doubling)
        );
        for (ItemAttrPair pair : testList) {
            DictionaryItem item = loader.loadFromString(pair.str);
            Assert.assertEquals(Noun, item.primaryPos);
            Assert.assertEquals("error in:" + pair.str, pair.attrs, item.attrs);
        }
    }

    private static ItemAttrPair testPair(String s, RootAttr... attrs) {
        return new ItemAttrPair(s, new AttributeSet<RootAttr>(attrs));
    }

    private static class ItemAttrPair {
        String str;
        AttributeSet<RootAttr> attrs;

        private ItemAttrPair(String str, AttributeSet<RootAttr> attrs) {
            this.str = str;
            this.attrs = attrs;
        }
    }


}
