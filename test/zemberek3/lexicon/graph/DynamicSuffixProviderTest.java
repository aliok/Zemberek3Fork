package zemberek3.lexicon.graph;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixFormSet;

public class DynamicSuffixProviderTest {

    @Test
    public void testSimpleFormGraph() {
        Suffix sf1 = new Suffix("sf1");
        Suffix sf2 = new Suffix("sf2");
        Suffix sf3 = new Suffix("sf3");
        Suffix sf4 = new Suffix("sf4");

        SuffixFormSet set1 = new SuffixFormSet("fs1", sf1, "abc");
        SuffixFormSet set2_1 = new SuffixFormSet("fs2-1", sf2, "ali");
        SuffixFormSet set2_2 = new SuffixFormSet("fs2-2", sf2, "kaan");
        SuffixFormSet set4 = new SuffixFormSet("fs4", sf4, "akin");
        SuffixFormSet tmp1 = SuffixFormSet.getTemplate("tmp1", sf3, TerminationType.TRANSFER);

        //
        //  set4 -.........->set2_2
        //        \       /
        //  set1--->tmp1-/
        //   |           \
        //   .............-> set2_1        --- Direct link  ... indirect link.

        set1.directSuccessors.add(tmp1);
        set1.successors.add(set2_2);
        set4.directSuccessors.add(tmp1);
        set4.successors.add(set2_1);
        tmp1.directSuccessors.add(set2_1);
        tmp1.directSuccessors.add(set2_2);

        DynamicSuffixProvider provider = new DynamicSuffixProvider();
        provider.registerForms(tmp1, set1, set4);
        Assert.assertEquals(1, set1.directSuccessors.set.size());
        Assert.assertFalse(set1.directSuccessors.contains(tmp1));

        SuffixFormSet nullSet1 = set1.directSuccessors.set.iterator().next();
        Assert.assertEquals("tmp1_1", nullSet1.getId());
        Assert.assertEquals(1, nullSet1.directSuccessors.set.size());
        Assert.assertTrue(nullSet1.directSuccessors.contains(set2_2));

        Assert.assertEquals(1, set4.directSuccessors.set.size());
        Assert.assertFalse(set4.directSuccessors.contains(tmp1));

        SuffixFormSet nullSet2 = set4.directSuccessors.set.iterator().next();
        Assert.assertEquals("tmp1_2", nullSet2.getId());
        Assert.assertEquals(1, nullSet2.directSuccessors.set.size());
        Assert.assertTrue(nullSet2.directSuccessors.contains(set2_1));

    }
}