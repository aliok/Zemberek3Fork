package z3.parser.modifiers;

import zemberek3.structure.TurkicSeq;

/**
 * A modifier makes generic modifications in a charsequence. Some examples can be
 * letter insretion or deletion. 
 */
public interface SequenceModifier {

    /**
     * this method modifies the input CharSequence implemetor and returns the SequenceModifier for chaining.
     * Keep in mind that input sequence will be modified once method is executed.
     * @param sequence  input char sequence to be modified.
     * @return a SequenceModifier. usually the class itself for chaining.
     */
    SequenceModifier modify(TurkicSeq sequence);
}
