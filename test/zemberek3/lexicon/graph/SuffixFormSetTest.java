package zemberek3.lexicon.graph;

import org.junit.Assert;
import org.junit.Test;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixForm;

public class SuffixFormSetTest {
    @Test
    public void equalityTest() {
        Suffix suffix = new Suffix("SUFFIX");
        SuffixForm sf1 = new SuffixForm("sf1", suffix, "lAr", TerminationType.TERMINAL);
        SuffixForm sf2 = new SuffixForm("sf2", suffix, "lAr", TerminationType.TERMINAL);
        Assert.assertTrue(sf1.equals(sf2));
        Assert.assertTrue(sf2.equals(sf1));

        SuffixForm sf3 = new SuffixForm("sf3", suffix, "k", TerminationType.TERMINAL);
        Assert.assertFalse(sf1.equals(sf3));

        sf1.directSuccessors.add(sf3);
        Assert.assertFalse(sf1.equals(sf2));

        sf2.directSuccessors.add(sf3);
        Assert.assertTrue(sf1.equals(sf2));

        sf1.directSuccessors.remove(sf3);
        SuffixForm sf4 = new SuffixForm("sf4", suffix, "lAr", TerminationType.NON_TERMINAL);
        Assert.assertFalse(sf4.equals(sf2));
    }

}
