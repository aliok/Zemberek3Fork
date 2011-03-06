package zemberek3.lexicon;

import zemberek3.structure.TinyEnumSet;

public class LexiconItem {
    String lemma;
    String root;
    public PrimaryPos primaryPos;
    public SecondaryPos secondaryPos;
    RootAttr[] attributes;
    TinyEnumSet phoneticAttrs;

    public LexiconItem(String lemma, String root, PrimaryPos primaryPos, SecondaryPos secondaryPos, RootAttr[] attributes) {
        this.lemma = lemma;
        this.root = root;
        this.primaryPos = primaryPos;
        this.secondaryPos = secondaryPos;
        this.attributes = attributes;
    }

    public boolean hasAttribute(RootAttr attribute) {
        for (RootAttr rootAttr : attributes) {
            if(attribute== rootAttr)
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
        for (RootAttr attribute : attributes) {
            sb.append(attribute.getStringForm());
            if (i++ < attributes.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
