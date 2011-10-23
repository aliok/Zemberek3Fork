package zemberek3.lexicon.graph;

import org.junit.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.tr.StemNodeGenerator;
import zemberek3.lexicon.tr.TurkishDictionaryLoader;
import zemberek3.lexicon.tr.TurkishSuffixes;

public class StemNodeGeneratorTest {

    static TurkishSuffixes suffixes = new TurkishSuffixes();

    @Test
    public void empty() {
        StemNodeGenerator generator = new StemNodeGenerator(suffixes);
        DictionaryItem kitap = getDictionaryItem("kitap");
        StemNode[] nodes = generator.generate(kitap);
        Assert.assertEquals(2, nodes.length);
        DictionaryItem odun = getDictionaryItem("odun");
        StemNode[] odunNodes = generator.generate(odun);
        Assert.assertEquals(1, odunNodes.length);
    }

    private DictionaryItem getDictionaryItem(String line) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixes);
        return loader.loadFromString(line);
    }

}
