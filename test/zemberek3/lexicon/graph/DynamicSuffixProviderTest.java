package zemberek3.lexicon.graph;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.NullSuffixForm;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixForm;
import zemberek3.lexicon.SuffixFormTemplate;

public class DynamicSuffixProviderTest {

    @Test
    public void testSimpleFormGraph() {

        DynamicSuffixProvider provider = new DynamicSuffixProvider();

        Suffix sf1 = new Suffix("sf1");
        Suffix sf2 = new Suffix("sf2");
        Suffix sf3 = new Suffix("sf3");
        Suffix sf4 = new Suffix("sf4");

        SuffixForm set1 = new SuffixForm(1, "fs1", sf1, "abc");
        SuffixForm set2_1 = new SuffixForm(2, "fs2-1", sf2, "ali");
        SuffixForm set2_2 = new SuffixForm(3, "fs2-2", sf2, "kaan");
        SuffixForm set4 = new SuffixForm(4, "fs4", sf4, "akin");

        SuffixFormTemplate tmp1 = new SuffixFormTemplate(5, "tmp1", sf3, TerminationType.TRANSFER);

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

        provider.registerForms(tmp1, set1, set4);
        Assert.assertEquals(1, set1.connections.size());
        Assert.assertFalse(set1.connections.contains(tmp1));

        SuffixForm nullSet1 = set1.connections.iterator().next();
        Assert.assertEquals("tmp1_1", nullSet1.getId());
        Assert.assertEquals(1, nullSet1.connections.size());
        Assert.assertTrue(nullSet1.connections.contains(set2_2));

        Assert.assertEquals(1, set4.connections.size());
        Assert.assertFalse(set4.connections.contains(tmp1));

        SuffixForm nullSet2 = set4.connections.iterator().next();
        Assert.assertEquals("tmp1_2", nullSet2.getId());
        Assert.assertEquals(1, nullSet2.connections.size());
        Assert.assertTrue(nullSet2.connections.contains(set2_1));
    }


    @Test
    public void testNullSets() {

        DynamicSuffixProvider provider = new DynamicSuffixProvider();

        Suffix sf1 = new Suffix("sf1");
        Suffix sf2 = new Suffix("sf2");
        Suffix sf3 = new Suffix("sf3");
        Suffix sf4 = new Suffix("sf4");

        SuffixForm set1 = new SuffixForm(1, "fs1", sf1, "abc");
        SuffixForm set2_1 = new SuffixForm(2, "fs2-1", sf2, "ali");
        SuffixForm set2_2 = new SuffixForm(3, "fs2-2", sf2, "kaan");
        SuffixForm set4 = new SuffixForm(4, "fs4", sf4, "akin");

        SuffixFormTemplate tmp1 = new SuffixFormTemplate(5, "tmp1", sf3, TerminationType.TRANSFER);

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

        NullSuffixForm null1 = provider.generateNullFormFromTemplate(tmp1, new SuffixData(set2_2));
        NullSuffixForm null1Repeat = provider.generateNullFormFromTemplate(tmp1, new SuffixData(set2_2));

        Assert.assertEquals(null1, null1Repeat);

    }

    @Test
    public void testRegister() {

        DynamicSuffixProvider provider = new DynamicSuffixProvider();

        Suffix sf1 = new Suffix("sf1");
        Suffix sf2 = new Suffix("sf2");
        Suffix sf3 = new Suffix("sf3");
        Suffix sf4 = new Suffix("sf4");
        Suffix sf5 = new Suffix("sf4");
        Suffix sf6 = new Suffix("sf6");
        Suffix sf7 = new Suffix("sf6");
        Suffix sf8 = new Suffix("sf6");
        Suffix sf9 = new Suffix("sf6");

        SuffixForm frm1 = DynamicSuffixProvider.getForm("fs1", sf1, "abc");
        SuffixForm frm2_1 = DynamicSuffixProvider.getForm("fs2-1", sf2, "ali");
        SuffixForm frm2_2 = DynamicSuffixProvider.getForm("fs2-2", sf2, "kaan");
        SuffixForm frm4 = DynamicSuffixProvider.getForm("fs4", sf4, "akin");
        SuffixForm frm5 = DynamicSuffixProvider.getForm("frm5", sf6, "dadada");
        SuffixForm frm6 = DynamicSuffixProvider.getForm("frm6", sf9, "aguagu");

        SuffixFormTemplate tmp1 = DynamicSuffixProvider.getTemplate("tmp1", sf3, TerminationType.TRANSFER);
        SuffixFormTemplate tmp2 = DynamicSuffixProvider.getTemplate("tmp2", sf5, TerminationType.TRANSFER);
        SuffixFormTemplate tmp3 = DynamicSuffixProvider.getTemplate("tmp3", sf7, TerminationType.TRANSFER);
        SuffixFormTemplate tmp4 = DynamicSuffixProvider.getTemplate("tmp4", sf8, TerminationType.TRANSFER);

        //
        //        /--frm5.............
        //       /         \         \
        //      -->frm4- -.........->frm2_2
        //     /          \ \          /
        //   tmp2-->frm1--->tmp1------
        //     \      \      /        \
        //      \      .....C......-> frm2_1        --- Direct link  ... indirect link.
        //       \........ /........../
        //        \
        //         \---- tmp3----tmp4--- frm6
        //          \............./......./
        //

        tmp2.connections.add(frm4, frm1, frm5, tmp3);
        tmp2.indirectConnections.add(tmp1, frm2_2, frm2_1, tmp4, frm6);

        frm1.connections.add(tmp1);
        frm1.indirectConnections.add(frm2_2);

        frm4.connections.add(tmp1);
        frm4.indirectConnections.add(frm2_1);

        frm5.connections.add(tmp1);
        frm5.indirectConnections.add(frm2_2);

        tmp1.connections.add(frm2_1, frm2_2);

        tmp3.connections.add(tmp4);
        tmp3.indirectConnections.add(frm6);

        tmp4.connections.add(frm6);

        // register tmp2. It should not effect the graph.
        provider.registerForm(tmp2);
        Assert.assertEquals(0, provider.getFormCount());
        // after registering frm1, there should be 2 forms registered internally. frm1 and a nullForm from tmp1
        provider.registerForm(frm1);
        Assert.assertEquals(2, provider.getFormCount());
        // after registering frm4, there should be 4 forms registered internally. frm4 and another nullForm from tmp1 with different connections.
        provider.registerForm(frm4);
        Assert.assertEquals(4, provider.getFormCount());
        // if we attempt to re-register, should not effect the graph.
        provider.registerForm(frm4);
        Assert.assertEquals(4, provider.getFormCount());
        // we register frm5. it should not generate a null morpheme from tmp1 because it was already generated when frm4 is registered.
        provider.registerForm(frm5);
        Assert.assertEquals(5, provider.getFormCount());
        provider.registerForms(frm2_1, frm2_2);
        Assert.assertEquals(7, provider.getFormCount());
        // now we generate a nullmorpheme from tmp2 and register it. We apply a constraint so frm1 is out of connections
        SuffixData constraint = new SuffixData(tmp2.connections).remove(frm1).add(tmp2.indirectConnections);
        NullSuffixForm null2_1 = provider.generateNullFormFromTemplate(tmp2, constraint);
        Assert.assertFalse(null2_1.connections.contains(frm1));
        Assert.assertTrue(null2_1.connections.contains(frm4));
        Assert.assertTrue(null2_1.connections.contains(frm5));
        provider.registerForm(null2_1.copy());
        // null morphemes for tmp3 and tm4 also will be registered internally.
        Assert.assertEquals(10, provider.getFormCount());
        // we try to do the same. it should not effect the graph.
        SuffixData constraint2 = new SuffixData(tmp2.connections).remove(frm1).add(tmp2.indirectConnections);
        NullSuffixForm null2_2 = provider.generateNullFormFromTemplate(tmp2, constraint2);
        Assert.assertEquals(null2_1, null2_2);
        provider.registerForm(null2_2.copy());
        Assert.assertEquals(10, provider.getFormCount());
        SuffixData constraint3 = new SuffixData(tmp2.allConnections());
        NullSuffixForm null2_3 = provider.generateNullFormFromTemplate(tmp2, constraint3);
        provider.registerForm(null2_3.copy());
        Assert.assertNotSame(null2_3, null2_2);
        Assert.assertEquals(11, provider.getFormCount());

    }


}
