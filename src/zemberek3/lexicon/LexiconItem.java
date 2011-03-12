package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;

import java.util.List;

public class LexiconItem {
    final String lemma;
    final String root;
    final public PrimaryPos primaryPos;
    final public SecondaryPos secondaryPos;
    AttributeSet<RootAttr> attrs;

    public LexiconItem(String lemma, String root, PrimaryPos primaryPos, SecondaryPos secondaryPos, AttributeSet<RootAttr> attrs) {
        this.lemma = lemma;
        this.root = root;
        this.primaryPos = primaryPos;
        this.secondaryPos = secondaryPos;
        this.attrs = attrs;
    }

    public boolean hasAttribute(RootAttr attribute) {
        attrs.isSet(attribute);
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[" + lemma + ":" + root + "]" + " Pos:" + primaryPos.shortForm);
        if (secondaryPos != SecondaryPos.None)
            sb.append(", ").append(secondaryPos.shortForm);
        if (!attrs.isEmpty()) {
            sb.append(" A:");
            printAttributes(sb, attrs);
        }
        return sb.toString();
    }

    private void printAttributes(StringBuilder sb, AttributeSet<RootAttr> attrs) {
        if (!attrs.isEmpty())
            sb.append("[");
        else return;
        int i = 0;
        List<RootAttr> arr = attrs.getAsList(RootAttr.class);
        for (RootAttr attribute : arr) {
            sb.append(attribute.name());
            if (i++ < arr.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
    }
}
