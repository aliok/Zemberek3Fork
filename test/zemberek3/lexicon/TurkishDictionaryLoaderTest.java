package zemberek3.lexicon;

import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.jcaki.SimpleTextReader;
import org.jcaki.Strings;
import org.junit.Ignore;
import org.junit.Test;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;
import zemberek3.structure.TurkishAlphabetTest;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
        Assert.assertEquals("elma", item.root);
        Assert.assertEquals(Noun, item.primaryPos);

        item = loader.loadFromString("elma [Pos:Noun]");
        Assert.assertEquals("elma", item.lemma);
        Assert.assertEquals("elma", item.root);
        Assert.assertEquals(Noun, item.primaryPos);
    }


    @Test
    public void verbInferenceTest() {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        DictionaryItem item = loader.loadFromString("gelmek");
        Assert.assertEquals("gel", item.root);
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

    @Test
    @Ignore("Not a unit Test. Only loads the master dictionary.")
    public void masterDictionaryLoadTest() throws IOException {
        SuffixProvider sp = new TurkishSuffixes().getSuffixProvider();
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(sp);

        List<DictionaryItem> items = loader.load(new File("src/resources/tr/master-dictionary.txt"));
        TurkishAlphabet alphabet = new TurkishAlphabet();

        Set<String> novoicingStuff = new HashSet<String>();
        Locale tr = new Locale("tr");
        List<String> allZ2 = SimpleTextReader.trimmingUTF8Reader(new File("/home/afsina/projects/zemberek3/src/resources/tr/master-dictionary.txt")).asStringList();
        for (String s : allZ2) {
            if (s.startsWith("#"))
                continue;
            String clean = Strings.subStringUntilFirst(s.trim(), " ").toLowerCase(tr).replaceAll("[\\-']", "");
            if (s.contains("Noun") && !s.contains("Compound") && !s.contains("PropNoun")
                    && !clean.endsWith("et") && !clean.endsWith("ist") && !clean.endsWith("lik")
                    && !clean.endsWith("lık") && !clean.endsWith("lük") && !clean.endsWith("luk")
                    && !clean.endsWith("ot")) {
                TurkicSeq seq = new TurkicSeq(clean, alphabet);
                if (seq.vowelCount() > 1 && seq.lastLetter().isStopConsonant() && !s.contains("Vo")) {
                    novoicingStuff.add(s);
                    System.out.println(clean);
                }
            }
        }


        for (DictionaryItem item : items) {
            if ((item.primaryPos == PrimaryPos.Noun || item.primaryPos == PrimaryPos.Adjective) && item.secondaryPos != SecondaryPos.ProperNoun &&
                    item.hasAttribute(RootAttr.Voicing)) {

            }
        }

        System.out.println(items.size());

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
