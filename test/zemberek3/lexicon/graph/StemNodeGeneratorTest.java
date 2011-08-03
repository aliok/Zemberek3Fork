package zemberek3.lexicon.graph;

import org.junit.Assert;
import org.junit.Test;
import zemberek3.lexicon.*;

import java.util.List;

public class StemNodeGeneratorTest {
	@Test
	public void empty(){
		StemNodeGenerator generator = new StemNodeGenerator();
        DictionaryItem kitap = getDictionaryItem("kitap");
        StemNode[] nodes = generator.generate(kitap);
        Assert.assertEquals(2, nodes.length);
        DictionaryItem odun = getDictionaryItem("odun");
        StemNode[] odunNodes = generator.generate(odun);
        Assert.assertEquals(1, odunNodes.length);

	}

    private DictionaryItem getDictionaryItem(String line) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(new DummySuffixProvider());
        return loader.loadFromString(line);
    }

    class DummySuffixProvider implements SuffixProvider {

        @Override
        public Suffix getSuffixById(String suffixId) {
            return null;
        }

        @Override
        public List<SuffixFormSet> getFormsBySuffixId(String suffixId) {
            return null;
        }

        @Override
        public SuffixFormSet getFormById(String suffixSetId) {
            return null;
        }

        @Override
        public Iterable<SuffixFormSet> getAllForms() {
            return null;
        }
    }
}
