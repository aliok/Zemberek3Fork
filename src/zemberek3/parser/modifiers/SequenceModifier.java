package zemberek3.parser.modifiers;

/**
 * A modifier makes generic modifications in a charsequence. Some examples can be
 * letter insretion or deletion. 
 */
public interface SequenceModifier<T extends CharSequence> {

    /**
     * this method modifies the input CharSequence implemetor and returns the SequenceModifier for chaining.
     * Keep in mind that input sequence will be modified once method is executed.
     * @param t  input char sequence to be modified.
     * @return a SequenceModifier. usually the class itself for chaining.
     */
    SequenceModifier<T> modify(T t);
}
