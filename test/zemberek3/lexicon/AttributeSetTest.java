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
    public void setTest() {
        AttributeSet<TestAttr> set = new AttributeSet<TestAttr>();
        set.set(Apple);
        Assert.assertTrue(set.isSet(Apple));
        Assert.assertTrue(set.isAllReset(Orange, Radish));
        set.set(Radish);
        Assert.assertTrue(set.isAllReset(Orange));
    }

    @Test
    public void resetTest() {
        AttributeSet<TestAttr> set = new AttributeSet<TestAttr>();
        set.set(Apple, Orange);
        Assert.assertTrue(set.isAllSet(Apple, Orange));
        Assert.assertTrue(set.isReset(Radish));
        set.reset(Apple, Orange);
        set.set(Radish);
        Assert.assertTrue(set.isSet(Radish));
        Assert.assertTrue(set.isAllReset(Apple, Orange));
    }


}
