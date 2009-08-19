package zemberek3.structure;

/**
 * An Alphabet is a collection of symbols used for written language.
 */
public interface Alphabet<T extends Letter> {

    T getLetter(char c);

    boolean similarAscii(T l1, T l2);
}
