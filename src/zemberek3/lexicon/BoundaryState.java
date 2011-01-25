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

}
