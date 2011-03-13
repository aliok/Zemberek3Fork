package zemberek3.lexicon;

import junit.framework.Assert;
import org.junit.Test;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.BitEnum;

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
        Assert.assertFalse(new AttributeSet<TestAttr>(TestAttr.Apple).isEmpty());
    }

    


}
