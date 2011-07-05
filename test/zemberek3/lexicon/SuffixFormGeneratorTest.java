package zemberek3.lexicon;

import org.junit.Assert;
import org.junit.Test;
import zemberek3.lexicon.graph.PhoneticExpectation;
import zemberek3.lexicon.graph.SuffixNode;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.Arrays;

import static zemberek3.lexicon.PhonAttr.*;

public class SuffixFormGeneratorTest {

    static TurkishAlphabet alphabet = new TurkishAlphabet();

    @Test
    public void suffixFormAHarmonyTest() {
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        SuffixNode node = getFirstNodeNoExpectatios(sfg, set(LastVowelBack), "lAr");
        Assert.assertEquals("lar", node.surfaceForm);
        Assert.assertTrue(node.getAttributes().containsAll(LastLetterConsonant, LastVowelBack, LastVowelUnrounded));

        node = getFirstNodeNoExpectatios(sfg, set(LastVowelBack, LastVowelRounded), "lAr");
        Assert.assertEquals("lar", node.surfaceForm);

        node = getFirstNodeNoExpectatios(sfg, set(LastVowelFrontal, LastVowelRounded), "lAr");
        Assert.assertEquals("ler", node.surfaceForm);
    }

    @Test
    public void suffixFormIHarmonyTest() {
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        SuffixNode form = getFirstNodeNoExpectatios(sfg, set(LastVowelBack, LastVowelUnrounded), "sIn");
        Assert.assertEquals("sın", form.surfaceForm);
        Assert.assertTrue(form.getAttributes().containsAll(LastLetterConsonant, LastVowelBack, LastVowelUnrounded));

        form = getFirstNodeNoExpectatios(sfg, set(LastVowelBack, LastVowelRounded), "sInIz");
        Assert.assertEquals("sunuz", form.surfaceForm);
    }

    @Test
    public void emptyFormTest() {
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        SuffixNode node = getFirstNodeNoExpectatios(sfg, set(LastVowelBack, LastVowelRounded, LastLetterConsonant), "");
        Assert.assertEquals("", node.surfaceForm);
        Assert.assertTrue(node.getAttributes().containsAll(LastVowelBack, LastVowelRounded, LastLetterConsonant));
    }

    @Test
    public void novowelFormTest() {
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        SuffixNode node = getFirstNodeNoExpectatios(sfg, set(LastVowelBack, LastVowelRounded, LastLetterVowel), "m");
        Assert.assertEquals("m", node.surfaceForm);
        Assert.assertTrue(node.getAttributes().containsAll(LastVowelBack, LastVowelRounded, LastLetterConsonant));
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

                new Triple("kale", "+In", "n"),
                new Triple("kale", "+An", "n"),
                new Triple("kalem", "InA", "ine"),
                new Triple("kale", "InI", "ni"),

                new Triple("kitap", ">cA", "ça"),
                new Triple("sarraf", ">cA", "ça"),
                new Triple("makas", ">cA", "ça"),
                new Triple("tokat", ">cA", "ça"),
                new Triple("kaş", ">cA", "ça"),
                new Triple("fok", ">cA", "ça"),
                new Triple("gitar", ">cA", "ca"),
                new Triple("kalem", ">cA", "ce"),
                new Triple("kale", ">cA", "ce"),
                new Triple("kitap", ">dAn", "tan"),
                new Triple("gitar", ">dIn", "dın"),
                new Triple("kalem", ">dA", "de"),
                new Triple("kale", ">dArI", "deri"),

                new Triple("kale", "+y>cI", "yci"),
                new Triple("kitap", "+y>cI", "çı")

        };
        SuffixFormGenerator sfg = new SuffixFormGenerator();
        for (Triple triple : triples) {
            SuffixNode form = getFirstNodeNoExpectatios(sfg,
                    sfg.defineMorphemicAttributes(new TurkicSeq(triple.predecessor, alphabet)),
                    triple.generationWord);
            Assert.assertEquals("Error in:" + triple, triple.expectedSurface, form.surfaceForm);
        }
    }


    private AttributeSet<PhonAttr> set(PhonAttr... attributes) {
        return new AttributeSet<PhonAttr>(attributes);
    }

    private SuffixNode getFirstNodeNoExpectatios(SuffixFormGenerator sfg, AttributeSet<PhonAttr> attributes, String generation) {
        SuffixFormSet dummySet = new SuffixFormSet("dummy-form", new Suffix("dummy"), generation);
        return sfg.getNodes(attributes, AttributeSet.<PhoneticExpectation>emptySet(), dummySet).get(0);
    }

    private class Triple {
        String predecessor;
        String generationWord;
        String expectedSurface;

        private Triple(String predecessor, String generationWord, String expectedSurface) {
            this.predecessor = predecessor;
            this.generationWord = generationWord;
            this.expectedSurface = expectedSurface;
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
