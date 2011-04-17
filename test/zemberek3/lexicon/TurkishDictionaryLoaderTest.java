package zemberek3.lexicon;

import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;
import zemberek3.structure.AttributeSet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static zemberek3.lexicon.PrimaryPos.Noun;
import static zemberek3.lexicon.PrimaryPos.Pronoun;
import static zemberek3.lexicon.PrimaryPos.Verb;
import static zemberek3.lexicon.RootAttr.*;

public class TurkishDictionaryLoaderTest {

    SuffixProvider suffixProvider = new TurkishSuffixes().getSuffixProvider();

    @Test
    public void loadNounsFromFileTest() throws IOException {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        List<DictionaryItem> items = loader.load(new File("test/data/test-lexicon-nouns.txt"));

        Assert.assertFalse(items.isEmpty());
        for (DictionaryItem item : items) {
            Assert.assertTrue(item.primaryPos == Noun);
        }
    }

    @Test
    public void nounInferenceTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
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
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
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
    public void suffixDataTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        DictionaryItem item = loader.loadFromString("ben [Pos:Pron; S: +A1sg_EMPTY]");
        Assert.assertEquals(Pronoun, item.primaryPos);
        Assert.assertNotNull(item.suffixData);
        Assert.assertTrue(!item.suffixData.accepts.isEmpty());
        Assert.assertTrue(item.suffixData.rejects.isEmpty());
        Assert.assertTrue(item.suffixData.onlyAccepts.isEmpty());

        item = loader.loadFromString("ben [Pos:Pron; S: -A1sg, +A1sg_EMPTY, +Dim]");
        Assert.assertTrue(item.suffixData.rejects.contains(TurkishSuffixes.A1sg_m));
        Assert.assertTrue(item.suffixData.rejects.contains(TurkishSuffixes.A1sg_yIm));
        Assert.assertTrue(item.suffixData.accepts.contains(TurkishSuffixes.A1sg_EMPTY));
        Assert.assertTrue(item.suffixData.accepts.contains(TurkishSuffixes.Dim_cIg));
        Assert.assertTrue(item.suffixData.accepts.contains(TurkishSuffixes.Dim_cIk));
    }


    @Test
    public void nounVoicingTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
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
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);

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
