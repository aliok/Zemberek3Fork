package zemberek3.lexicon;

import com.google.common.collect.Sets;
import zemberek3.structure.TurkicSeq;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuffixNode {
    TurkishSuffix suffix;
    String content;
    Set<SuffixNode> successors = new HashSet<SuffixNode>();
    Set<MorphemicAttribute> attributes = new HashSet<MorphemicAttribute>();
    boolean terminal = true;
    boolean mergeMorphemicAttributes = false;

    public SuffixNode(TurkishSuffix suffix, String content, MorphemicAttribute... attributes) {
        this.suffix = suffix;
        this.content = content;
        this.attributes = Sets.newHashSet(attributes);
    }

    public SuffixNode(TurkishSuffix suffix, String content, Set<MorphemicAttribute> attributes) {
        this.suffix = suffix;
        this.content = content;
        this.attributes = Sets.newHashSet(attributes);
    }


    public SuffixNode add(MorphemicAttribute... morphemicAttributes) {
        attributes.addAll(Arrays.asList(morphemicAttributes));
        return this;
    }

    public SuffixNode remove(MorphemicAttribute morphemicAttribute) {
        attributes.remove(morphemicAttribute);
        return this;
    }

    public boolean isMergeMorphemicAttributes() {
        return mergeMorphemicAttributes;
    }

    public void setMergeMorphemicAttributes(boolean mergeMorphemicAttributes) {
        this.mergeMorphemicAttributes = mergeMorphemicAttributes;
    }
}
