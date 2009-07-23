package zemberek3.util;

import java.util.*;

public class CollectionUtils {

    public static <T> EmptyIterator<T> getEmptyIterator() {
        return new EmptyIterator<T>();
    }

    private static class EmptyIterator<T> implements Iterator<T> {

        public boolean hasNext() {
            return false;
        }

        public T next() {
            return null;
        }

        public void remove() {
            // do nothing
        }
    }

    public static <T> Set<T> newHashSet(T... tarray) {
        return new HashSet<T>(Arrays.asList(tarray));
    }

    public static <T> List<T> newArrayList(T... tarray) {
        return Arrays.asList(tarray);
    }

}
