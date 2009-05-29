package zemberek3.structure;

/**
 * Linguistic Definition: Any linguistic item to which suffixes can be added. whether a simple
 * root or a combination of a root plus suffix(es).
 * <code>Stem</code> contains the cleaned (ready for parse) form of a dictionary entry and special cases if any.
 */
public class Stem {
    String content;

    public String getContent() {
        return content;
    }
}
