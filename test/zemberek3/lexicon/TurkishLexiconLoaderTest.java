package zemberek3.lexicon;

import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;
import zemberek3.structure.AttributeSet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TurkishLexiconLoaderTest {
    @Test
    public void loadNounsFromFileTest() throws IOException {
        TurkishLexiconLoader loader = new TurkishLexiconLoader();
        List<LexiconItem> items = loader.load(new File("test/data/test-lexicon-nouns.txt"));

        Assert.assertFalse(items.isEmpty());
        for (LexiconItem item : items) {
            Assert.assertTrue(item.primaryPos == PrimaryPos.Noun);
        }
    }

    @Test
    public void nounAttributesTest1() {
        TurkishLexiconLoader loader = new TurkishLexiconLoader();
        LexiconItem item = loader.loadFromString("elma");
        Assert.assertEquals("elma", item.root);
        Assert.assertEquals("elma", item.lemma);
        Assert.assertEquals(PrimaryPos.Noun, item.primaryPos);

        item = loader.loadFromString("elma [Pos:Noun]");
        Assert.assertEquals("elma", item.root);
        Assert.assertEquals("elma", item.lemma);
        Assert.assertEquals(PrimaryPos.Noun, item.primaryPos);
    }


    @Test
    public void nounVoicingTest() {
        TurkishLexiconLoader loader = new TurkishLexiconLoader();
        String[] voicing = {"kabak", "kabak [A:Voicing]", "psikolog", "havuç", "turp [A:Voicing]", "galip", "nohut", "cenk"};
        for (String s : voicing) {
            LexiconItem item = loader.loadFromString(s);
            Assert.assertEquals(PrimaryPos.Noun, item.primaryPos);
            Assert.assertEquals("error in:" + s, new AttributeSet<RootAttr>(RootAttr.Voicing), item.attrs);
        }

        String[] novoicing = {"kek", "top", "kulp", "takat [A:NoVoicing]"};
        for (String s : novoicing) {
            LexiconItem item = loader.loadFromString(s);
            Assert.assertEquals(PrimaryPos.Noun, item.primaryPos);
            Assert.assertEquals("error in:" + s, new AttributeSet<RootAttr>(RootAttr.NoVoicing), item.attrs);
        }
    }

    public void nounAttributesTest() {
        TurkishLexiconLoader loader = new TurkishLexiconLoader();

        List<ItemAttrPair> testList = Lists.newArrayList(
                testPair("takat [A:NoVoicing, InverseHarmony]", RootAttr.NoVoicing, RootAttr.InverseHarmony),
                testPair("nakit [A: LastVowelDrop]", RootAttr.Voicing, RootAttr.LastVowelDrop),
                testPair("ret [A:Voicing, Doubling]", RootAttr.Voicing, RootAttr.LastVowelDrop)
        );
        for (ItemAttrPair pair : testList) {
            LexiconItem item = loader.loadFromString(pair.str);
            Assert.assertEquals(PrimaryPos.Noun, item.primaryPos);
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
