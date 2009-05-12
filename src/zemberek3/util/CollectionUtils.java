package zemberek3.util;

import java.util.Iterator;

public class CollectionUtils {


    public static <T> EmptyIterator<T> getEmptyIterator() {
        return new EmptyIterator<T>();
    }

    private static class EmptyIterator<T> implements Iterator<T> {

        public boolean hasNext() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public T next() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void remove() {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}
