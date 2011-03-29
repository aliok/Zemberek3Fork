package zemberek3.parser.morphology;

import zemberek3.lexicon.TurkishSuffix;

public class SuffixNode extends MorphNode {
    TurkishSuffix suffix;

    public SuffixNode(TurkishSuffix suffix, String form, TerminationType termination) {
        super(form, termination);
        this.suffix = suffix;
    }

}
