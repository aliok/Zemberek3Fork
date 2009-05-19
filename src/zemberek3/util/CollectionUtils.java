package zemberek3.util;

import java.util.Iterator;

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

}
