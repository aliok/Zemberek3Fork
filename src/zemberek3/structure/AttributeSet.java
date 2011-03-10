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

    public boolean isSet(int bitIndex) {
        return (data & setMasks[bitIndex]) != 0;
    }

    public AttributeSet<T> copy() {
        return new AttributeSet<T>(data);
    }

    public boolean isSet(BitEnum bitEnum) {
        return (data & setMasks[bitEnum.getBitIndex()]) != 0;
    }

    public boolean isReset(int bitIndex) {
        return (data & setMasks[bitIndex]) == 0;
    }

    public boolean isReset(BitEnum bitEnum) {
        return (data & setMasks[bitEnum.getBitIndex()]) == 0;
    }

    public AttributeSet set(int bitIndex) {
        data |= setMasks[bitIndex];
        return this;
    }

    public AttributeSet set(T... enums) {
        for (BitEnum en : enums) {
            data |= setMasks[en.getBitIndex()];
        }
        return this;
    }

    private AttributeSet(int data) {
        this.data = data;
    }


    public AttributeSet() {
        this.data = 0;
    }

    public AttributeSet set(Iterable<T> enumIt) {
        for (BitEnum bitEnum : enumIt) {
            data |= setMasks[bitEnum.getBitIndex()];
        }
        return this;
    }

    public AttributeSet reset(int bitIndex) {
        data &= resetMasks[bitIndex];
        return this;
    }

    public AttributeSet reset(T... enums) {
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
            if (isSet(t)) {
                tt.add(t);
            }
        }
        return tt;
    }
}
