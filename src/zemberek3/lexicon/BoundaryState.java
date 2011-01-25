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
}
