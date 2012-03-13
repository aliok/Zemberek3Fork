package zemberek3.lexicon;

/**
 * This is the representation of a Suffix. It only contains an id.
 */
public class Suffix {

    public final String id;

    public Suffix(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suffix suffix = (Suffix) o;

        return id.equals(suffix.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
