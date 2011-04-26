package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;

import java.util.List;
import java.util.Locale;

public class DictionaryItem {
    public final String lemma;
    public final String root;
    public final PrimaryPos primaryPos;
    public final SecondaryPos secondaryPos;
    public final AttributeSet<RootAttr> attrs;
    public final ExclusiveSuffixData suffixData;

    public DictionaryItem(String lemma, String root, PrimaryPos primaryPos, SecondaryPos secondaryPos, AttributeSet<RootAttr> attrs, ExclusiveSuffixData suffixData) {
        this.lemma = lemma.toLowerCase();
        this.primaryPos = primaryPos;
        this.secondaryPos = secondaryPos;
        this.attrs = attrs;
        this.suffixData = suffixData;
        this.root = root;
    }

    public boolean hasAttribute(RootAttr attribute) {
        return attrs.contains(attribute);
    }

    public String getId() {
        return lemma + "_" + primaryPos.shortForm;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryItem that = (DictionaryItem) o;

        if (!attrs.equals(that.attrs)) return false;
        if (!lemma.equals(that.lemma)) return false;
        if (primaryPos != that.primaryPos) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lemma.hashCode();
        result = 31 * result + primaryPos.hashCode();
        result = 31 * result + attrs.hashCode();
        return result;
    }
}
