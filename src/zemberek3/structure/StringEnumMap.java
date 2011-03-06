package zemberek3.structure;

import java.util.HashMap;
import java.util.Map;

public class StringEnumMap<T extends StringEnum> {
    Map<String, T> map = new HashMap<String, T>();
    Class<T> clazz;

    private StringEnumMap(Class<T> clazz) {
        this.clazz = clazz;
        for (T senum : clazz.getEnumConstants()) {
            map.put(senum.getStringForm(), senum);
        }
    }

    public static <T extends StringEnum> StringEnumMap<T> get(Class<T> clazz) {
        return new StringEnumMap<T>(clazz);
    }

    public T getEnum(String s) {
        if (s == null || s.length() == 0)
            throw new IllegalArgumentException("Input String must have content.");
        T res = map.get(s);
        if (res == null)
            throw new IllegalArgumentException("Cannot find a representation of :" + s + " for enum class:" + clazz.getName());
        return res;
    }

    public boolean enumExists(String s) {
        return map.containsKey(s);
    }
}
