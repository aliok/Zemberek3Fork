package zemberek3.lexicon;

import org.junit.Assert;
import org.junit.Test;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.Arrays;
import java.util.List;

import static zemberek3.lexicon.PhonAttr.*;

public class SuffixFormGeneratorTest {

    static TurkishAlphabet alphabet = new TurkishAlphabet();

    @Test
    public void suffixFormAHarmonyTest() {
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        List<SuffixForm> forms = sfg.suffixNodes(set(LastVowelBack), set(), "lAr");
        Assert.assertEquals(1, forms.size());
        Assert.assertEquals("lar", forms.get(0).surface());
        Assert.assertTrue(forms.get(0).attributes.containsAll(LastLetterConsonant, LastVowelBack, LastVowelUnrounded));
        Assert.assertTrue(forms.get(0).expectations.isEmpty());

        forms = sfg.suffixNodes(set(LastVowelBack, LastVowelRounded), set(), "lAr");
        Assert.assertEquals(1, forms.size());
        Assert.assertEquals("lar", forms.get(0).surface());

        forms = sfg.suffixNodes(set(LastVowelFrontal, LastVowelRounded), set(), "lAr");
        Assert.assertEquals("ler", forms.get(0).surface());
    }

    @Test
    public void suffixFormIHarmonyTest() {
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        List<SuffixForm> forms = sfg.suffixNodes(set(LastVowelBack, LastVowelRounded), set(), "sIn");
        Assert.assertEquals(1, forms.size());
        Assert.assertEquals("sın", forms.get(0).surface());
        Assert.assertTrue(forms.get(0).attributes.containsAll(LastLetterConsonant, LastVowelBack, LastVowelUnrounded));
        Assert.assertTrue(forms.get(0).expectations.isEmpty());

        forms = sfg.suffixNodes(set(LastVowelBack, LastVowelRounded), set(), "sInIz");
        Assert.assertEquals(1, forms.size());
        Assert.assertEquals("lar", forms.get(0).surface());

        forms = sfg.suffixNodes(set(LastVowelFrontal, LastVowelRounded), set(), "lAr");
        Assert.assertEquals("ler", forms.get(0).surface());
    }

    @Test
    public void surfaceFormFunctionalTest() {
        Triple[] triples = {
                new Triple("kalem", "lAr", "ler"),
                new Triple("kalem", "lArA", "lere"),
                new Triple("kan", "lAr", "lar"),
                new Triple("kan", "lArAt", "larat"),
                new Triple("kan", "Ar", "ar"),
                new Triple("kaba", "lAr", "lar"),
                new Triple("kaba", "Ar", "r"),
                new Triple("kedi", "lAr", "ler"),
                new Triple("kedi", "lArA", "lere"),
                new Triple("kart", "lAr", "lar"),
                new Triple("a", "lAr", "lar"),
                new Triple("ee", "lAr", "ler"),

                new Triple("kalem", "lIk", "lik"),
                new Triple("kedi", "lIk", "lik"),
                new Triple("kabak", "lIk", "lık"),
                new Triple("kuzu", "lIk", "luk"),
                new Triple("göz", "lIk", "lük"),
                new Triple("gö", "lIk", "lük"),
                new Triple("ö", "lIk", "lük"),

                new Triple("kalem", "lArI", "leri"),
                new Triple("arı", "lArI", "ları"),
                new Triple("odun", "lArI", "ları"),
                new Triple("odun", "lIrA", "lura"),

                new Triple("kale", "+yA", "ye"),
                new Triple("kale", "+nA", "ne"),
                new Triple("kalem", "+yA", "e"),
                new Triple("kale", "+yI", "yi"),
                new Triple("kalem", "+yI", "i"),
                new Triple("kale", "+yIr", "yir"),
                new Triple("kale", "+yAr", "yer"),

                new Triple("kitap", ">cA", "ça"),
                new Triple("gitar", ">cA", "ca"),
                new Triple("kalem", ">cA", "ce"),
                new Triple("kale", ">cA", "ce"),
                new Triple("kitap", ">dAn", "tan"),
                new Triple("gitar", ">dIn", "dın"),
                new Triple("kalem", ">dA", "de"),
                new Triple("kale", ">dArI", "deri"),

                new Triple("kitap", ">cI~k", "çık", "çığ"),
                new Triple("kalem", ">cI~k", "cik", "ciğ"),
                new Triple("kalem", ">cI~p", "cip", "cib")

        };
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        for (Triple triple : triples) {
            List<SuffixForm> forms = sfg.suffixNodes(
                    sfg.defineMorphemicAttributes(new TurkicSeq(triple.predecessor, alphabet)),
                    set(), //no expectation.
                    triple.generationWord);
            Assert.assertEquals("Error in:" + triple, triple.expectedSize(), forms.size());
            Assert.assertEquals("Error in:" + triple, triple.expectedSurface[0], forms.get(0).surface());
        }
    }


    private AttributeSet<PhonAttr> set(PhonAttr... attributes) {
        return new AttributeSet<PhonAttr>(attributes);
    }

    private class Triple {
        String predecessor;
        String generationWord;
        String expectedSurface[];

        private Triple(String predecessor, String generationWord, String... expectedSurface) {
            this.predecessor = predecessor;
            this.generationWord = generationWord;
            this.expectedSurface = expectedSurface;
        }

        public int expectedSize() {
            return expectedSurface.length;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "predecessor='" + predecessor + '\'' +
                    ", generationWord='" + generationWord + '\'' +
                    ", expectedSurface=" + (expectedSurface == null ? null : Arrays.asList(expectedSurface)) +
                    '}';
        }
    }

}
