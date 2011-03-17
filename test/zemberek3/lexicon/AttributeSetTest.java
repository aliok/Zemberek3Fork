package zemberek3.lexicon;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.BitEnum;

import static zemberek3.lexicon.AttributeSetTest.TestAttr.Apple;
import static zemberek3.lexicon.AttributeSetTest.TestAttr.Orange;
import static zemberek3.lexicon.AttributeSetTest.TestAttr.Radish;

public class AttributeSetTest {

    enum TestAttr implements BitEnum {

        Apple(0), Orange(1), Radish(3);

        int index;

        TestAttr(int index) {
            this.index = index;
        }

        public int getBitIndex() {
            return index;
        }
    }

    @Test
    public void constructorTest() {
        Assert.assertTrue(new AttributeSet<TestAttr>().isEmpty());
        Assert.assertFalse(new AttributeSet<TestAttr>(Apple).isEmpty());
    }


    @Test
    public void addTest() {
        AttributeSet<TestAttr> set = new AttributeSet<TestAttr>();
        set.add(Apple);
        Assert.assertTrue(set.contains(Apple));
        Assert.assertTrue(set.containsNone(Orange, Radish));
        set.add(Radish);
        Assert.assertTrue(set.containsNone(Orange));
    }

    @Test
    public void removeTest() {
        AttributeSet<TestAttr> set = new AttributeSet<TestAttr>();
        set.add(Apple, Orange);
        Assert.assertTrue(set.containsAll(Apple, Orange));
        Assert.assertTrue(!set.contains(Radish));
        set.remove(Apple, Orange);
        set.add(Radish);
        Assert.assertTrue(set.contains(Radish));
        Assert.assertTrue(set.containsNone(Apple, Orange));
    }


}
