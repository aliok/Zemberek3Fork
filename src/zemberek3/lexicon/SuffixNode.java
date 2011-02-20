package zemberek3.lexicon;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class SuffixNode {
    TurkishSuffix suffix;
    String content;
    Set<SuffixNode> successors = new HashSet<SuffixNode>();
    Set<MorphemicAttribute> attributes = new HashSet<MorphemicAttribute>();
    boolean endState = true;
    boolean mergeMorphemicAttributes = false;

    public SuffixNode(TurkishSuffix suffix, String content, MorphemicAttribute... attributes) {
        this.suffix = suffix;
        this.content = content;
        this.attributes = Sets.newHashSet(attributes);
    }

    public boolean isEndState() {
        return endState;
    }

    public void setEndState(boolean endState) {
        this.endState = endState;
    }

    public boolean isMergeMorphemicAttributes() {
        return mergeMorphemicAttributes;
    }

    public void setMergeMorphemicAttributes(boolean mergeMorphemicAttributes) {
        this.mergeMorphemicAttributes = mergeMorphemicAttributes;
    }
}
