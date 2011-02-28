package zemberek3.lexicon;

import com.google.common.collect.Sets;
import zemberek3.structure.TurkicSeq;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuffixNode {
    TurkishSuffix suffix;
    String content;
    TurkicSeq seq;
    Set<SuffixNode> successors = new HashSet<SuffixNode>();
    Set<MorphAttr> attributes = new HashSet<MorphAttr>();
    Set<TurkishSuffix> exclusivePredecessors = new HashSet<TurkishSuffix>();
    boolean terminal = true;
    boolean mergeMorphemicAttributes = false;

    public SuffixNode(TurkishSuffix suffix, TurkicSeq seq, MorphAttr... attributes) {
        this.suffix = suffix;
        this.seq = seq;
        this.content = seq.toString();
        this.attributes = Sets.newHashSet(attributes);
    }

    public SuffixNode addExclusivePredecessor(TurkishSuffix... suffixes) {
        exclusivePredecessors.addAll(Arrays.asList(suffixes));
        return this;
    }

    public SuffixNode(TurkishSuffix suffix, TurkicSeq seq, Set<MorphAttr> attributes) {
        this.suffix = suffix;
        this.seq = seq;
        this.content = seq.toString();
        this.attributes = Sets.newHashSet(attributes);
    }

    public SuffixNode add(MorphAttr... morphAttrs) {
        attributes.addAll(Arrays.asList(morphAttrs));
        return this;
    }

    public SuffixNode remove(MorphAttr morphAttr) {
        attributes.remove(morphAttr);
        return this;
    }

    public boolean isMergeMorphemicAttributes() {
        return mergeMorphemicAttributes;
    }

    public void setMergeMorphemicAttributes(boolean mergeMorphemicAttributes) {
        this.mergeMorphemicAttributes = mergeMorphemicAttributes;
    }
}
