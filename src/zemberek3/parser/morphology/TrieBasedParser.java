package zemberek3.parser.morphology;

import com.google.common.collect.Lists;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.lexicon.graph.LexiconTree;
import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;

import java.util.List;

public class TrieBasedParser implements MorphParser {
    LexiconGraph graph;
    LexiconTree lexicon = new LexiconTree();

    public TrieBasedParser(LexiconGraph graph) {
        this.graph = graph;
        for (StemNode stemNode : graph.getStems()) {
            lexicon.add(stemNode);
        }
    }

    public List<ParseResult> parse(String input) {
        // get stem candidates.
        List<StemNode> candidates = lexicon.getMatchingStems(input);
        // generate starting tokens with suffix root nodes.
        List<ParseToken> initialTokens = Lists.newArrayList();
        for (StemNode candidate : candidates) {
            String rest = input.substring(candidate.surfaceForm.length());
            initialTokens.add(new ParseToken(candidate, Lists.<SuffixNode>newArrayList(candidate.getSuffixRootNode()), rest));
        }

        // traverse suffix graph.
        List<ParseResult> result = Lists.newArrayList();
        traverseSuffixes(initialTokens, result);
        return result;
    }

    private void traverseSuffixes(List<ParseToken> current, List<ParseResult> completed) {
        List<ParseToken> newtokens = Lists.newArrayList();
        for (ParseToken token : current) {
            boolean matchFound = false;
            for (SuffixNode successor : token.currentNode.getSuccessors()) {
                if (token.rest.startsWith(successor.surfaceForm)) {
                    matchFound = true;
                    newtokens.add(token.getCopy(successor));
                }
            }
            if (!matchFound) {
                if (token.rest.length() == 0 && token.terminal)
                    completed.add(token.getResult());
            }
        }
        if (!newtokens.isEmpty())
            traverseSuffixes(newtokens, completed);
    }
}
