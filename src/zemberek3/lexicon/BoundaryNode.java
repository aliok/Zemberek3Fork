package zemberek3.lexicon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BoundaryNode implements Cloneable {

    // this defines the main suffixes that can follow this boundary node. However, if exclusiveSuffixes
    // has any element, other suffixes cannot follow this node.
    PrimaryPos primaryPos;


    Set<MorphAttr> attributes = new HashSet<MorphAttr>();

    //If this list is not empty, ONLY the exclusive suffixes in the list can follow this Node.
    Set<TurkishSuffix> exclusiveSuffixes = new HashSet<TurkishSuffix>();

    //If this set is not empty, NONE of the suffixes can follow this boundary node.
    Set<TurkishSuffix> restrictedSuffixes = new HashSet<TurkishSuffix>();

    public BoundaryNode(PrimaryPos primaryPos) {
        this.primaryPos = primaryPos;
    }

    public BoundaryNode(PrimaryPos primaryPos, Set<MorphAttr> attributes) {
        this.primaryPos = primaryPos;
        this.attributes = attributes;
    }

    public BoundaryNode(PrimaryPos primaryPos, MorphAttr... attributes) {
        this.primaryPos = primaryPos;
        this.attributes.addAll(Arrays.asList(attributes));
    }

    public BoundaryNode add(MorphAttr morphAttr) {
        attributes.add(morphAttr);
        return this;
    }

    public BoundaryNode remove(MorphAttr morphAttr) {
        attributes.remove(morphAttr);
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

    public Set<MorphAttr> getAttributes() {
        return attributes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[Pos:" + primaryPos.shortForm);
        if (attributes.size() > 0)
            sb.append(" A:");
        int i = 0;
        for (MorphAttr attribute : attributes) {
            sb.append(attribute.getStringForm());
            if (i++ < attributes.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        sb.append(exclusiveSuffixes.toString());
        sb.append(restrictedSuffixes.toString());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoundaryNode that = (BoundaryNode) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;
        if (primaryPos != that.primaryPos) return false;
        if (restrictedSuffixes != null ? !restrictedSuffixes.equals(that.restrictedSuffixes) : that.restrictedSuffixes != null)
            return false;
        if (exclusiveSuffixes != null ? !exclusiveSuffixes.equals(that.exclusiveSuffixes) : that.exclusiveSuffixes != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryPos != null ? primaryPos.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (exclusiveSuffixes != null ? exclusiveSuffixes.hashCode() : 0);
        result = 31 * result + (restrictedSuffixes != null ? restrictedSuffixes.hashCode() : 0);
        return result;
    }
}
