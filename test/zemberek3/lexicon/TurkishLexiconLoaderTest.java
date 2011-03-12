package zemberek3.lexicon;

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


}
