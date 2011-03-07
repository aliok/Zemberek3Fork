package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicSeq;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuffixNode {
    TurkishSuffix suffix;
    String content;
    TurkicSeq seq;
    Set<SuffixNode> successors = new HashSet<SuffixNode>();

    AttributeSet<PhonAttr> forwardAttributes = new AttributeSet<PhonAttr>();
    AttributeSet<PhonAttr> forwardExpectations = new AttributeSet<PhonAttr>();
    AttributeSet<PhonAttr> backwardAttributes = new AttributeSet<PhonAttr>();
    AttributeSet<PhonAttr> backwardExpectations = new AttributeSet<PhonAttr>();

    Set<TurkishSuffix> exclusivePredecessors = new HashSet<TurkishSuffix>();
    boolean terminal = true;
    boolean mergeMorphemicAttributes = false;

    public SuffixNode(TurkishSuffix suffix, TurkicSeq seq) {
        this.suffix = suffix;
        this.seq = seq;
        this.content = seq.toString();
    }

    public SuffixNode addExclusivePredecessor(TurkishSuffix... suffixes) {
        exclusivePredecessors.addAll(Arrays.asList(suffixes));
        return this;
    }

    public boolean isMergeMorphemicAttributes() {
        return mergeMorphemicAttributes;
    }

    public void setMergeMorphemicAttributes(boolean mergeMorphemicAttributes) {
        this.mergeMorphemicAttributes = mergeMorphemicAttributes;
    }
}
