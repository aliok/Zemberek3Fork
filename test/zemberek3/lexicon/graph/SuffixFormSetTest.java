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

    @Test
    public void copyTest() {
        Suffix suffix = new Suffix("SUFFIX");
        SuffixFormSet sf1 = new SuffixFormSet("sf1", suffix, "lAr", TerminationType.TERMINAL);
        SuffixFormSet sf3 = new SuffixFormSet("sf3", suffix, "k", TerminationType.TERMINAL);

        SuffixFormSet sf4 = new SuffixFormSet("sf4", suffix, "l", TerminationType.TERMINAL);
        SuffixFormSet sf6 = new SuffixFormSet("sf6", suffix, "l", TerminationType.TERMINAL);

        sf1.directSuccessors.add(sf3);
        sf1.successors.add(sf6); // sf6 and sf4 equals.
        SuffixFormSet sf5 = new SuffixFormSet("sf5", suffix, "m", TerminationType.TERMINAL);

        SuffixData data = new SuffixData(sf3,sf4,sf5);
        SuffixFormSet copy = sf1.copy("Copy of sf1",data);

        Assert.assertTrue(copy.directSuccessors.contains(sf3));
        Assert.assertTrue(copy.successors.contains(sf4));
        Assert.assertTrue(copy.successors.contains(sf6));
        Assert.assertTrue(copy.successors.contains(sf5));


    }
}
