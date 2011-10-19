package zemberek3.parser.morphology;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;

import java.util.ArrayList;
import java.util.List;

public class ParseResult {
    final StemNode stem;
    final List<SuffixNode> suffixNodes;

    public ParseResult(StemNode stem, List<SuffixNode> suffixes) {
        this.stem = stem;
        this.suffixNodes = suffixes;
    }

    public List<Suffix> getSuffixNodes() {
        List<Suffix> res = new ArrayList<Suffix>(suffixNodes.size());
        int i = 0;
        for (SuffixNode suffixNode : suffixNodes) {
            if (i == 0) {
                i++;
                continue;
            }
            res.add(suffixNode.getSuffixSet().getSuffix());
        }
        return res;
    }

    public String getLemma() {
        return stem.getDictionaryItem().lemma;
    }

    public DictionaryItem getDictionaryItem() {
        return stem.getDictionaryItem();
    }

    public String asParseString() {
        StringBuilder sb = new StringBuilder("[" + stem.surfaceForm + ":" + stem.getDictionaryItem().lemma + "-" + stem.getDictionaryItem().primaryPos + "]");
        sb.append("[");
        int i = 0;
        for (SuffixNode suffixNode : suffixNodes) {
            sb.append(suffixNode.getSuffixSet().id).append(":").append(suffixNode.surfaceForm);
            if (i++ < suffixNodes.size() - 1)
                sb.append(" + ");
        }
        sb.append("]");
        return sb.toString();
    }

    public String asOflazerFormat() {
        StringBuilder sb = new StringBuilder(stem.getDictionaryItem().lemma).append(" ");
        int i = 0;
        for (SuffixNode suffixNode : suffixNodes) {
            sb.append(suffixNode.getSuffixSet().getSuffix());
            if (i++ < suffixNodes.size() - 1)
                sb.append("+");
        }
        return sb.toString();
    }

    public String toString() {
        return asParseString();
    }

}
