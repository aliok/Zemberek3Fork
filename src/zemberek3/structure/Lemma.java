package zemberek3.structure;

/**
 * <code>Lemma</code> contains the cleaned (ready for parse) form of a dictionary entry and special cases if any.
 */
public class Lemma {

    private String content;
    private POS pos;

    public Lemma(String content, POS pos) {
        this.content = content;
        this.pos = pos;
    }

    public String getContent() {
        return content;
    }

    public POS getPos() {
        return pos;
    }
}
