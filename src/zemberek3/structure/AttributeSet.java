package zemberek3.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * A bit set backed by an 32 bit integer.
 */
public class AttributeSet<T extends BitEnum> {

    private int data;

    private static final int[] setMasks = new int[32];
    private static final long[] resetMasks = new long[32];

    static {
        for (int i = 0; i < 32; i++) {
            setMasks[i] = 0x1 << i;
            resetMasks[i] = ~setMasks[i];
        }
    }

    public AttributeSet<T> copy() {
        return new AttributeSet<T>(data);
    }

    private static final AttributeSet EMPTY_SET = new AttributeSet();

    public static <T extends BitEnum> AttributeSet<T> emptySet() {
        return (AttributeSet<T>) EMPTY_SET;
    }

    public boolean contains(BitEnum bitEnum) {
        return (data & setMasks[bitEnum.getBitIndex()]) != 0;
    }

    public boolean containsAll(BitEnum... bitEnum) {
        for (BitEnum en : bitEnum) {
            if (!contains(en))
                return false;
        }
        return true;
    }

    public boolean containsAll(AttributeSet<T> set) {
        return (this.data & set.data) == set.data;
    }

    public boolean containsNone(BitEnum... bitEnum) {
        for (BitEnum en : bitEnum) {
            if (contains(en))
                return false;
        }
        return true;
    }

    public AttributeSet add(T... enums) {
        for (BitEnum en : enums) {
            data |= setMasks[en.getBitIndex()];
        }
        return this;
    }

    private AttributeSet(int data) {
        this.data = data;
    }

    public AttributeSet(Iterable<T> enumIt) {
        add(enumIt);
    }

    public AttributeSet(T... enumIt) {
        add(enumIt);
    }

    public AttributeSet() {
        this.data = 0;
    }

    public AttributeSet add(Iterable<T> enumIt) {
        for (BitEnum bitEnum : enumIt) {
            data |= setMasks[bitEnum.getBitIndex()];
        }
        return this;
    }

    public AttributeSet remove(T... enums) {
        for (BitEnum anEnum : enums) {
            data &= resetMasks[anEnum.getBitIndex()];
        }
        return this;
    }

    public boolean isEmpty() {
        return data == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeSet that = (AttributeSet) o;
        return data == that.data;
    }

    @Override
    public int hashCode() {
        return data;
    }

    public List<T> getAsList(Class<T> clazz) {
        List<T> tt = new ArrayList<T>();
        for (T t : clazz.getEnumConstants()) {
            if (contains(t)) {
                tt.add(t);
            }
        }
        return tt;
    }
}
