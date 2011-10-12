package zemberek3.lexicon.graph;

import org.junit.Assert;
import org.junit.Test;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixFormSet;

import java.util.HashSet;
import java.util.Set;

public class SuffixFormSetTest {
    @Test
    public void equalityTest() {
        Suffix suffix = new Suffix("SUFFIX");
        SuffixFormSet sf1 = new SuffixFormSet("sf1", suffix, "lAr", TerminationType.TERMINAL);
        SuffixFormSet sf2 = new SuffixFormSet("sf2", suffix, "lAr", TerminationType.TERMINAL);
        Assert.assertTrue(sf1.equals(sf2));
        Assert.assertTrue(sf2.equals(sf1));

        SuffixFormSet sf3 = new SuffixFormSet("sf3", suffix, "k", TerminationType.TERMINAL);
        Assert.assertFalse(sf1.equals(sf3));

        sf1.directSuccessors.add(sf3);
        Assert.assertFalse(sf1.equals(sf2));

        sf2.directSuccessors.add(sf3);
        Assert.assertTrue(sf1.equals(sf2));

        sf1.directSuccessors.remove(sf3);
        SuffixFormSet sf4 = new SuffixFormSet("sf4", suffix, "lAr", TerminationType.NON_TERMINAL);
        Assert.assertFalse(sf4.equals(sf2));
    }

}
