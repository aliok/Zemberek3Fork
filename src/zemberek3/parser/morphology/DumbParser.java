package zemberek3.parser.morphology;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DumbParser {

    LexiconGraph graph;
    ArrayListMultimap<String, StemNode> multiStems = ArrayListMultimap.create(1000,2);
    Map<String, StemNode> singeStems = new HashMap<String, StemNode>();

    public DumbParser(LexiconGraph graph) {
        this.graph = graph;
        for (StemNode stemNode : graph.getStems()) {
            final String surfaceForm = stemNode.surfaceForm;
            if (multiStems.containsKey(stemNode)) {
                multiStems.put(surfaceForm, stemNode);
            }
            if (singeStems.containsKey(surfaceForm)) {
                singeStems.remove(surfaceForm);
                multiStems.put(surfaceForm, stemNode);
            } else
                singeStems.put(surfaceForm, stemNode);
        }
    }

    public List<ParseToken> parse(String input) {
        // get stem candidates.
        List<StemNode> candidates = Lists.newArrayList();
        for (int i = 1; i <= input.length(); i++) {
            String stem = input.substring(0, i);
            if (singeStems.containsKey(stem)) {
                candidates.add(singeStems.get(stem));
            } else if(multiStems.containsKey(stem)) {
                candidates.addAll(multiStems.get(stem));
            }
        }

        // generate starting tokens with suffix root nodes.
        List<ParseToken> initialTokens = Lists.newArrayList();
        for (StemNode candidate : candidates) {
            String rest = input.substring(candidate.surfaceForm.length());
            initialTokens.add(new ParseToken(candidate, Lists.<SuffixNode>newArrayList(candidate.getSuffixRootNode()), rest));
        }

        // traverse suffix graph.
        List<ParseToken> result = Lists.newArrayList();
        traverseSuffixes(initialTokens, result);
        return result;
    }

    private void traverseSuffixes(List<ParseToken> current, List<ParseToken> completed) {
        List<ParseToken> newtokens = Lists.newArrayList();
        for (ParseToken token : current) {
            List<SuffixNode> matches = Lists.newArrayList();
            for (SuffixNode successor : token.currentNode.getSuccessors()) {
                if (token.rest.startsWith(successor.surfaceForm)) {
                    matches.add(successor);
                }
            }
            if (matches.size() == 0) {
                if (token.rest.length() == 0 && token.terminal)
                    completed.add(token);
            }
            for (SuffixNode match : matches) {
                newtokens.add(token.getCopy(match));
            }
        }
        if (newtokens.size() > 0)
            traverseSuffixes(newtokens, completed);
    }
}
