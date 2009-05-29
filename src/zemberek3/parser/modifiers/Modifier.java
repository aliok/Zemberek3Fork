package zemberek3.parser.modifiers;

/**
 * A modifier makes generic modifications in a charsequence. Some examples can be
 * letter insretion or deletion. 
 */
public interface Modifier<T extends CharSequence> {

    T modify(T t);
}
