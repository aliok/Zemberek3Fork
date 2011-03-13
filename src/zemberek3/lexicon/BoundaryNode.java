package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;

import java.util.*;

public class BoundaryNode implements Cloneable {

    // this defines the main suffixes that can follow this boundary node. However, if exclusiveSuffixes
    // has any element, other suffixes cannot follow this node.
    PrimaryPos primaryPos;

    AttributeSet<PhonAttr> forwardAttributes = new AttributeSet<PhonAttr>();
    AttributeSet<PhonAttr> forwardExpectations = new AttributeSet<PhonAttr>();

    //If this list is not empty, ONLY the exclusive suffixes in the list can follow this Node.
    Set<TurkishSuffix> exclusiveSuffixes = new HashSet<TurkishSuffix>();

    //If this set is not empty, NONE of the suffixes can follow this boundary node.
    Set<TurkishSuffix> restrictedSuffixes = new HashSet<TurkishSuffix>();

    // set of suffix nodes this boundary nodes connects to. Suffix nodes are the actual nodes that is used in the
    // graph. 
    Set<SuffixNode> suffixNodes = new HashSet<SuffixNode>();

    public BoundaryNode(PrimaryPos primaryPos) {
        this.primaryPos = primaryPos;
    }

    public BoundaryNode(PrimaryPos primaryPos, Iterable<PhonAttr> forwardAttributes) {
        this.primaryPos = primaryPos;
        this.forwardAttributes.add(forwardAttributes);
    }

    public BoundaryNode(PrimaryPos primaryPos, PhonAttr... forwardAttributes) {
        this.primaryPos = primaryPos;
        this.forwardAttributes.add(forwardAttributes);
    }

    public BoundaryNode(PrimaryPos primaryPos, AttributeSet<PhonAttr> forwardAttributes, AttributeSet<PhonAttr> forwardExpectations) {
        this.primaryPos = primaryPos;
        this.forwardAttributes = forwardAttributes;
        this.forwardExpectations = forwardExpectations;
    }

    public AttributeSet<PhonAttr> getForwardExpectations() {
        return forwardExpectations;
    }

    public AttributeSet<PhonAttr> getForwardAttributes() {
        return forwardExpectations;
    }

    public BoundaryNode addNodes(SuffixNode... suffixNode) {
        this.suffixNodes.addAll(Arrays.asList(suffixNode));
        return this;
    }

    public BoundaryNode addExclusiveSuffix(TurkishSuffix... suffix) {
        exclusiveSuffixes.addAll(Arrays.asList(suffix));
        return this;
    }

    public BoundaryNode addRestrictedsuffixes(TurkishSuffix... suffix) {
        restrictedSuffixes.addAll(Arrays.asList(suffix));
        return this;
    }

    public PrimaryPos getPrimaryPos() {
        return primaryPos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[Pos:" + primaryPos.shortForm + "]");
        printAttributes(sb, forwardAttributes, " FATTR:");
        printAttributes(sb, forwardExpectations, " FEXPC:");
        if (exclusiveSuffixes.size() > 0) {
            sb.append(" exc suffixes:");
            sb.append(exclusiveSuffixes.toString());
        }
        if (restrictedSuffixes.size() > 0) {
            sb.append(" restrc suffixes:");
            sb.append(restrictedSuffixes.toString());
        }
        return sb.toString();
    }

    private void printAttributes(StringBuilder sb, AttributeSet<PhonAttr> attrs, String str) {
        if (!attrs.isEmpty())
            sb.append("[").append(str);
        else return;
        int i = 0;
        List<PhonAttr> arr = attrs.getAsList(PhonAttr.class);
        for (PhonAttr attribute : arr) {
            sb.append(attribute.name());
            if (i++ < arr.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoundaryNode that = (BoundaryNode) o;

        if (forwardAttributes != null ? !forwardAttributes.equals(that.forwardAttributes) : that.forwardAttributes != null)
            return false;
        if (exclusiveSuffixes != null ? !exclusiveSuffixes.equals(that.exclusiveSuffixes) : that.exclusiveSuffixes != null)
            return false;
        if (forwardExpectations != null ? !forwardExpectations.equals(that.forwardExpectations) : that.forwardExpectations != null)
            return false;
        if (primaryPos != that.primaryPos) return false;
        if (restrictedSuffixes != null ? !restrictedSuffixes.equals(that.restrictedSuffixes) : that.restrictedSuffixes != null)
            return false;
        if (suffixNodes != null ? !suffixNodes.equals(that.suffixNodes) : that.suffixNodes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryPos != null ? primaryPos.hashCode() : 0;
        result = 31 * result + (forwardAttributes != null ? forwardAttributes.hashCode() : 0);
        result = 31 * result + (forwardExpectations != null ? forwardExpectations.hashCode() : 0);
        result = 31 * result + (exclusiveSuffixes != null ? exclusiveSuffixes.hashCode() : 0);
        result = 31 * result + (restrictedSuffixes != null ? restrictedSuffixes.hashCode() : 0);
        result = 31 * result + (suffixNodes != null ? suffixNodes.hashCode() : 0);
        return result;
    }
}
