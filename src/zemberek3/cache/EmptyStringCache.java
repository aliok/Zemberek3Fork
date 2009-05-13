package zemberek3.cache;

public class EmptyStringCache implements CharSequenceCache {
    public boolean check(CharSequence cs) {
        return false;
    }

    public void flush() {
        //do nothing.
    }
}
