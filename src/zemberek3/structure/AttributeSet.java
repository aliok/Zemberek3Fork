package zemberek3.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * A bit set backed by an 32 bit integer.
 */
public class AttributeSet<T extends IndexedEnum> {

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

    public static <T extends IndexedEnum> AttributeSet<T> emptySet() {
        return (AttributeSet<T>) EMPTY_SET;
    }

    public boolean contains(IndexedEnum indexedEnum) {
        return (data & setMasks[indexedEnum.getBitIndex()]) != 0;
    }

    public boolean containsAll(IndexedEnum... indexedEnum) {
        for (IndexedEnum en : indexedEnum) {
            if (!contains(en))
                return false;
        }
        return true;
    }

    public boolean containsAny(IndexedEnum... indexedEnum) {
        for (IndexedEnum en : indexedEnum) {
            if (contains(en))
                return true;
        }
        return false;
    }

    public boolean containsAny(AttributeSet<T> set) {
        return (this.data & set.data) != 0;
    }

    public boolean containsAll(AttributeSet<T> set) {
        return (this.data & set.data) == set.data;
    }

    public boolean containsNone(IndexedEnum... indexedEnum) {
        for (IndexedEnum en : indexedEnum) {
            if (contains(en))
                return false;
        }
        return true;
    }

    public AttributeSet<T> add(T... enums) {
        for (IndexedEnum en : enums) {
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

    public AttributeSet<T> add(Iterable<T> enumIt) {
        for (IndexedEnum indexedEnum : enumIt) {
            data |= setMasks[indexedEnum.getBitIndex()];
        }
        return this;
    }

    public AttributeSet<T> remove(T... enums) {
        for (IndexedEnum anEnum : enums) {
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
