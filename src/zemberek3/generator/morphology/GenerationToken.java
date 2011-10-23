package zemberek3.generator.morphology;

import com.google.common.base.Joiner;
import zemberek3.lexicon.NullSuffixForm;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;

import java.util.ArrayList;
import java.util.List;

public class GenerationToken {
    StemNode stemNode;
    SuffixNode currentNode;
    List<Suffix> nodesLeft;
    List<String> formList = new ArrayList<String>();
    boolean terminal;

    public GenerationToken(StemNode stemNode, List<Suffix> nodesLeft) {
        this.stemNode = stemNode;
        this.currentNode = stemNode.getSuffixRootNode();
        this.nodesLeft = nodesLeft;
        this.formList.add(stemNode.surfaceForm);
        terminal = stemNode.isTerminal();
    }

    public Suffix getSuffix() {
        return nodesLeft.get(0);
    }

    public GenerationToken(StemNode stemNode, SuffixNode currentNode, List<Suffix> nodesLeft, List<String> formList, boolean terminal) {
        this.stemNode = stemNode;
        this.currentNode = currentNode;
        this.nodesLeft = nodesLeft;
        this.formList = formList;
        this.terminal = terminal;
    }

    public String getAsString() {
        return Joiner.on("").join(formList);
    }

    public String[] getAsMorphemes() {
        return formList.toArray(new String[formList.size()]);
    }

    GenerationToken getCopy(SuffixNode node) {
        boolean t = terminal;
        switch (node.termination) {
            case TERMINAL:
                t = true;
                break;
            case NON_TERMINAL:
                t = false;
                break;
        }
        List<Suffix> hist = nodesLeft.subList(1, nodesLeft.size());
        List<String> formList = new ArrayList<String>(this.formList);
        formList.add(node.surfaceForm);
        return new GenerationToken(stemNode, node, hist, formList, t);
    }

    GenerationToken getForNull(SuffixNode node) {
        boolean t = terminal;
        switch (node.termination) {
            case TERMINAL:
                t = true;
                break;
            case NON_TERMINAL:
                t = false;
                break;
        }
        return new GenerationToken(stemNode, node, new ArrayList<Suffix>(nodesLeft), formList, t);
    }

}
