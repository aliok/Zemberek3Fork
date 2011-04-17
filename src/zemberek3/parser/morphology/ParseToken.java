package zemberek3.parser.morphology;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;
import zemberek3.lexicon.graph.TerminationType;

import java.util.ArrayList;
import java.util.List;

public class ParseToken {
    StemNode stemNode;
    SuffixNode currentNode;
    List<SuffixNode> nodeHistory;
    String rest;
    boolean terminal = false;

    public ParseToken(StemNode stemNode, List<SuffixNode> nodeHistory, String rest) {
        this.stemNode = stemNode;
        this.currentNode = stemNode.getSuffixRootNode();
        this.nodeHistory = nodeHistory;
        this.rest = rest;
        this.terminal = stemNode.termination == TerminationType.TERMINAL;
    }

    public DictionaryItem getDictionaryItem() {
        return stemNode.getDictionaryItem();
    }

    public List<Suffix> getSuffixes() {
        List<Suffix> res = new ArrayList<Suffix>(nodeHistory.size());
        int i = 0;
        for (SuffixNode suffixNode : nodeHistory) {
            if(i==0) {
                i++;
                continue;
            }
            res.add(suffixNode.getSuffixSet().getSuffix());
        }
        return res;
    }

    ParseToken(StemNode stemNode, SuffixNode suffixNode, List<SuffixNode> nodeHistory, String rest, boolean terminal) {
        this.stemNode = stemNode;
        this.currentNode = stemNode.getSuffixRootNode();
        this.nodeHistory = nodeHistory;
        this.rest = rest;
        this.terminal = terminal;
        this.currentNode = suffixNode;
    }

    public String getRest() {
        return rest;
    }

    public SuffixNode getCurrentNode() {
        return currentNode;
    }

    ParseToken getCopy(SuffixNode node) {
        boolean t = terminal;
        switch (node.termination) {
            case TERMINAL:
                t = true;
                break;
            case NON_TERMINAL:
                t = false;
                break;
        }
        ArrayList<SuffixNode> hist = new ArrayList<SuffixNode>(nodeHistory);
        hist.add(node);
        return new ParseToken(stemNode, node, hist, rest.substring(node.surfaceForm.length()), t);
    }

    @Override
    public String toString() {
        return stemNode.surfaceForm + ":" + nodeHistory;
    }

    public String asParseString() {
        StringBuilder sb = new StringBuilder("[" + stemNode.surfaceForm + ":" + stemNode.getDictionaryItem().lemma + "-" + stemNode.getDictionaryItem().primaryPos + "]");
        sb.append("[");
        int i = 0;
        for (SuffixNode suffixNode : nodeHistory) {
            sb.append(suffixNode.getSuffixSet().getSuffix()).append(":").append(suffixNode.surfaceForm);
            if (i++ < nodeHistory.size() - 1)
                sb.append(" + ");
        }
        sb.append("]");
        return sb.toString();
    }
}
