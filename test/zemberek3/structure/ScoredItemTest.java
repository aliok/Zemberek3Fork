package zemberek3.structure;

import org.junit.Test;
import org.junit.Assert;


public class ScoredItemTest {
    @Test
    public void constructor() {
        ScoredItem<String> si = new ScoredItem<String>("hello", 0.1);
        Assert.assertEquals("hello", si.getItem());
        Assert.assertEquals(0.1d, si.getScore(),0.0000000000001d);
    }
}
