package zemberek3.structure;

public interface Alphabet<T extends Letter> {

    T getLetter(char c);

    boolean equalsAscii(T l1, T l2);
}
