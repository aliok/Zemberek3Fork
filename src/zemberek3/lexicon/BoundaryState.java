package zemberek3.lexicon;

import java.util.HashSet;
import java.util.Set;

public class BoundaryState {
    PrimaryPos primaryPos;
    Set<MorphemicAttribute> attributes = new HashSet<MorphemicAttribute>();

    public BoundaryState(PrimaryPos primaryPos) {
        this.primaryPos = primaryPos;
    }

    public void add(MorphemicAttribute morphemicAttribute) {
        attributes.add(morphemicAttribute);
    }

    public void remove(MorphemicAttribute morphemicAttribute) {
        attributes.remove(morphemicAttribute);
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
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoundaryState that = (BoundaryState) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;
        if (primaryPos != that.primaryPos) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryPos != null ? primaryPos.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }
}
