package zemberek3.lexicon.graph;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import zemberek3.lexicon.ExclusiveSuffixData;
import zemberek3.lexicon.PhonAttr;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixFormSet;
import zemberek3.structure.AttributeSet;

import java.util.Set;

public class SuffixNodeTest {

    @Test
    public void equalityTest() {
        SuffixFormSet dummySet = new SuffixFormSet("dummy-set", new Suffix("dummy-suffix"), "lar");
        SuffixNode node1 = new SuffixNode(
                dummySet,
                "ler",
                new AttributeSet<PhonAttr>(PhonAttr.LastVowelBack),
                TerminationType.TERMINAL
        );

        SuffixNode node2 = new SuffixNode(
                dummySet,
                "ler",
                new AttributeSet<PhonAttr>(PhonAttr.LastVowelBack),
                TerminationType.TERMINAL
        );

        Assert.assertEquals(node1, node2);

        SuffixNode node3 = new SuffixNode(
                dummySet,
                "ler",
                new AttributeSet<PhonAttr>(PhonAttr.LastVowelBack),
                new AttributeSet<PhoneticExpectation>(PhoneticExpectation.ConsonantStart),
                new ExclusiveSuffixData(),
                TerminationType.TERMINAL
        );

        Assert.assertNotSame(node1, node3);
        Assert.assertNotSame(node2, node3);

        Set<SuffixNode> nodeSet = Sets.newHashSet(node1, node2, node3);
        Assert.assertEquals(2, nodeSet.size());

    }
}
