package zemberek3.generator.morphology;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishSuffix;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DumbGenerator {

    LexiconGraph graph;

    ArrayListMultimap<DictionaryItem, StemNode> multiStems = ArrayListMultimap.create(1000, 2);
    Map<DictionaryItem, StemNode> singeStems = Maps.newHashMap();

    public DumbGenerator(LexiconGraph graph) {
        this.graph = graph;
        for (StemNode stemNode : graph.getStems()) {
            final DictionaryItem item = stemNode.getDictionaryItem();
            if (multiStems.containsKey(item)) {
                multiStems.put(item, stemNode);
            }
            if (singeStems.containsKey(item)) {
                singeStems.remove(item);
                multiStems.put(item, stemNode);
            } else
                singeStems.put(item, stemNode);
        }
    }

    public List<String> generate(DictionaryItem item, List<TurkishSuffix> suffixes) {

        // find nodes for the dictionary item.
        List<StemNode> nodeList = new ArrayList<StemNode>();
        if (singeStems.containsKey(item))
            nodeList.add(singeStems.get(item));
        else if (multiStems.containsKey(item))
            nodeList.addAll(multiStems.get(item));

        // generate starting tokens with suffix root nodes.
        List<GenerationToken> initialTokens = Lists.newArrayList();
        for (StemNode candidate : nodeList) {
            String current = candidate.surfaceForm;
            initialTokens.add(new GenerationToken(candidate, Lists.<SuffixNode>newArrayList(candidate.getSuffixRootNode())));
        }

        // traverse suffix graph.
        List<GenerationToken> result = Lists.newArrayList();
        traverseSuffixes(initialTokens, result);
        return Lists.newArrayList();
    }

   private void traverseSuffixes(List<GenerationToken> current, List<GenerationToken> completed) {
       /* List<GenerationToken> newtokens = Lists.newArrayList();
        for (GenerationToken token : current) {
            List<SuffixNode> matches = Lists.newArrayList();
            for (SuffixNode successor : token.currentNode.getSuccessors()) {
                if (successor.getSuffixSet().getSuffix()==)) {
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
            traverseSuffixes(newtokens, completed);*/
    }
}
