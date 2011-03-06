package zemberek3.structure;

import java.util.HashMap;
import java.util.Map;


public class BitEnumMap<T extends BitEnum> {
    Map<Integer, T> map = new HashMap<Integer, T>();
    Class<T> clazz;

    private BitEnumMap(Class<T> clazz) {
        this.clazz = clazz;
        for (T senum : clazz.getEnumConstants()) {
            map.put(senum.getBitIndex(), senum);
        }
    }

    public static <T extends BitEnum> BitEnumMap<T> get(Class<T> clazz) {
        return new BitEnumMap<T>(clazz);
    }

    public T getEnum(int s) {
        if (s < 0)
            throw new IllegalArgumentException("Input must be positive.");
        T res = map.get(s);
        if (res == null)
            throw new IllegalArgumentException("Cannot find a representation of :" + s + " for enum class:" + clazz.getName());
        return res;
    }

    public boolean enumExists(int i) {
        return map.containsKey(i);
    }
}
