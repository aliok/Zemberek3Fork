package zemberek3.lexicon;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuffixNode {
    TurkishSuffix suffix;
    String content;
    Set<SuffixNode> successors = new HashSet<SuffixNode>();
    Set<MorphAttr> attributes = new HashSet<MorphAttr>();
    boolean terminal = true;
    boolean mergeMorphemicAttributes = false;

    public SuffixNode(TurkishSuffix suffix, String content, MorphAttr... attributes) {
        this.suffix = suffix;
        this.content = content;
        this.attributes = Sets.newHashSet(attributes);
    }

    public SuffixNode(TurkishSuffix suffix, String content, Set<MorphAttr> attributes) {
        this.suffix = suffix;
        this.content = content;
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
