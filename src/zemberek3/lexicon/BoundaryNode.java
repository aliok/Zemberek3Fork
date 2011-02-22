package zemberek3.lexicon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BoundaryNode implements Cloneable {
    PrimaryPos primaryPos;
    Set<MorphemicAttribute> attributes = new HashSet<MorphemicAttribute>();
    Set<LexiconItem> exceptionalLexiconItems = new HashSet<LexiconItem>();
    Set<TurkishSuffix> exclusiveSuffixes = new HashSet<TurkishSuffix>();
    Set<TurkishSuffix> restrictedSuffixes = new HashSet<TurkishSuffix>();

    public BoundaryNode(PrimaryPos primaryPos) {
        this.primaryPos = primaryPos;
    }

    public BoundaryNode(PrimaryPos primaryPos, MorphemicAttribute... attributes) {
        this.primaryPos = primaryPos;
        this.attributes.addAll(Arrays.asList(attributes));
    }

    @Override
    public BoundaryNode clone() {
        try {
            return (BoundaryNode) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BoundaryNode add(MorphemicAttribute morphemicAttribute) {
        attributes.add(morphemicAttribute);
        return this;
    }

    public BoundaryNode remove(MorphemicAttribute morphemicAttribute) {
        attributes.remove(morphemicAttribute);
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

    public String toString() {
        StringBuilder sb = new StringBuilder("[Pos:" + primaryPos.shortForm);
        if (attributes.size() > 0)
            sb.append(" A:");
        int i = 0;
        for (MorphemicAttribute attribute : attributes) {
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
        if (exceptionalLexiconItems != null ? !exceptionalLexiconItems.equals(that.exceptionalLexiconItems) : that.exceptionalLexiconItems != null)
            return false;
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
        result = 31 * result + (exceptionalLexiconItems != null ? exceptionalLexiconItems.hashCode() : 0);
        result = 31 * result + (exclusiveSuffixes != null ? exclusiveSuffixes.hashCode() : 0);
        result = 31 * result + (restrictedSuffixes != null ? restrictedSuffixes.hashCode() : 0);
        return result;
    }
}
