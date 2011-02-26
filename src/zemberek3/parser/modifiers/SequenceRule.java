package zemberek3.parser.modifiers;

import zemberek3.structure.TurkicSeq;

/**
 * this is an interface for checking if a sequence is complying with a rule.
 */
public interface SequenceRule {
    boolean check(TurkicSeq sequence);
}
