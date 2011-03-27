package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;

import java.util.List;
import java.util.Locale;

public class DictionaryItem {
    final String lemma;
    final public PrimaryPos primaryPos;
    final public SecondaryPos secondaryPos;
    AttributeSet<RootAttr> attrs;

    static final Locale locale = new Locale("tr");

    public DictionaryItem(String lemma, PrimaryPos primaryPos, SecondaryPos secondaryPos, AttributeSet<RootAttr> attrs) {
        this.lemma = lemma;
        this.primaryPos = primaryPos;
        this.secondaryPos = secondaryPos;
        this.attrs = attrs;
    }

    public boolean hasAttribute(RootAttr attribute) {
        return attrs.contains(attribute);
    }

    public String clean() {
        if (primaryPos == PrimaryPos.Verb)
            return lemma.substring(0, lemma.length() - 3);
        if (secondaryPos == SecondaryPos.ProperNoun) {
            return lemma.toLowerCase(locale);
        }
        return lemma;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[" + lemma + "]" + " Pos:" + primaryPos.shortForm);
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
