package zemberek3.lexicon;

public class LexiconItem {
    String content;
    public PrimaryPos primaryPos;
    public SecondaryPos secondaryPos;
    MorphemicAttribute[] attributes;

    public LexiconItem(String content, PrimaryPos primaryPos, SecondaryPos secondaryPos, MorphemicAttribute[] attributes) {
        this.content = content;
        this.primaryPos = primaryPos;
        this.secondaryPos = secondaryPos;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(content + " Pos:" + primaryPos.shortForm);
        if (secondaryPos != SecondaryPos.None)
            sb.append(", ").append(secondaryPos.shortForm);
        if (attributes.length > 0)
            sb.append(" A:");
        int i = 0;
        for (MorphemicAttribute attribute : attributes) {
            sb.append(attribute.getStringForm());
            if (i++ < attributes.length-1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
