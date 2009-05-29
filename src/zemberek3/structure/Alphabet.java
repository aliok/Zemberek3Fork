package zemberek3.structure;

public interface Alphabet<T extends Letter> {

    T getLetter(char c);

    boolean similarAscii(T l1, T l2);
}
