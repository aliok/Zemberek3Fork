package zemberek3.lexicon;

public class LexiconItem {
    String lemma;
    String root;
    public PrimaryPos primaryPos;
    public SecondaryPos secondaryPos;
    MorphAttr[] attributes;

    public LexiconItem(String lemma, String root, PrimaryPos primaryPos, SecondaryPos secondaryPos, MorphAttr[] attributes) {
        this.lemma = lemma;
        this.root = root;
        this.primaryPos = primaryPos;
        this.secondaryPos = secondaryPos;
        this.attributes = attributes;
    }

    public boolean hasAttribute(MorphAttr attribute) {
        for (MorphAttr morphAttr : attributes) {
            if(attribute== morphAttr)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[" + lemma + ":" + root + "]" + " Pos:" + primaryPos.shortForm);
        if (secondaryPos != SecondaryPos.None)
            sb.append(", ").append(secondaryPos.shortForm);
        if (attributes.length > 0)
            sb.append(" A:");
        int i = 0;
        for (MorphAttr attribute : attributes) {
            sb.append(attribute.getStringForm());
            if (i++ < attributes.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
