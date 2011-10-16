package zemberek3.lexicon.graph;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixForm;

public class DynamicSuffixProviderTest {

    @Test
    public void testSimpleFormGraph() {
        Suffix sf1 = new Suffix("sf1");
        Suffix sf2 = new Suffix("sf2");
        Suffix sf3 = new Suffix("sf3");
        Suffix sf4 = new Suffix("sf4");

        SuffixForm set1 = new SuffixForm("fs1", sf1, "abc");
        SuffixForm set2_1 = new SuffixForm("fs2-1", sf2, "ali");
        SuffixForm set2_2 = new SuffixForm("fs2-2", sf2, "kaan");
        SuffixForm set4 = new SuffixForm("fs4", sf4, "akin");
        SuffixForm tmp1 = SuffixForm.getTemplate("tmp1", sf3, TerminationType.TRANSFER);

        //
        //  set4 -.........->set2_2
        //        \       /
        //  set1--->tmp1-/
        //   |           \
        //   .............-> set2_1        --- Direct link  ... indirect link.

        set1.connections.add(tmp1);
        set1.indirectConnections.add(set2_2);
        set4.connections.add(tmp1);
        set4.indirectConnections.add(set2_1);
        tmp1.connections.add(set2_1);
        tmp1.connections.add(set2_2);

        DynamicSuffixProvider provider = new DynamicSuffixProvider();
        provider.registerForms(tmp1, set1, set4);
        Assert.assertEquals(1, set1.connections.set.size());
        Assert.assertFalse(set1.connections.contains(tmp1));

        SuffixForm nullSet1 = set1.connections.set.iterator().next();
        Assert.assertEquals("tmp1_1", nullSet1.getId());
        Assert.assertEquals(1, nullSet1.connections.set.size());
        Assert.assertTrue(nullSet1.connections.contains(set2_2));

        Assert.assertEquals(1, set4.connections.set.size());
        Assert.assertFalse(set4.connections.contains(tmp1));

        SuffixForm nullSet2 = set4.connections.set.iterator().next();
        Assert.assertEquals("tmp1_2", nullSet2.getId());
        Assert.assertEquals(1, nullSet2.connections.set.size());
        Assert.assertTrue(nullSet2.connections.contains(set2_1));

    }
}
