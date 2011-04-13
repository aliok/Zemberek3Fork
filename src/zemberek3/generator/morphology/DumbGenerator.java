package zemberek3.generator.morphology;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffix;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.lexicon.graph.StemNode;
import zemberek3.lexicon.graph.SuffixNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
            } else if (singeStems.containsKey(item)) {
                multiStems.put(item, singeStems.get(item));
                singeStems.remove(item);
                multiStems.put(item, stemNode);
            } else
                singeStems.put(item, stemNode);
        }
    }

    public List<String[]> generateMorphemes(DictionaryItem item, List<TurkishSuffix> suffixes) {
        List<GenerationToken> tokens = getTokens(item, suffixes);
        List<String[]> results = new ArrayList<String[]>(tokens.size());
        for (GenerationToken token : tokens) {
            results.add(token.getAsMorphemes());
        }
        return results;
    }

    public List<String> generate(DictionaryItem item, List<TurkishSuffix> suffixes) {
        List<GenerationToken> tokens = getTokens(item, suffixes);
        List<String> results = new ArrayList<String>(tokens.size());
        for (GenerationToken token : tokens) {
            results.add(token.getAsString());
        }
        return results;
    }

    private List<GenerationToken> getTokens(DictionaryItem item, List<TurkishSuffix> suffixes) {
        // find nodes for the dictionary item.
        List<StemNode> nodeList = new ArrayList<StemNode>();
        if (singeStems.containsKey(item))
            nodeList.add(singeStems.get(item));
        else if (multiStems.containsKey(item))
            nodeList.addAll(multiStems.get(item));

        // generate starting tokens with suffix root nodes.
        List<GenerationToken> initialTokens = new ArrayList<GenerationToken>(2);
        for (StemNode candidate : nodeList) {
            initialTokens.add(new GenerationToken(candidate, suffixes));
        }

        // traverse suffix graph.
        List<GenerationToken> result = new ArrayList<GenerationToken>(2);
        traverseSuffixes(initialTokens, result);
        return result;
    }

    private void traverseSuffixes(List<GenerationToken> current, List<GenerationToken> completed) {
        List<GenerationToken> newtokens = Lists.newArrayList();
        for (GenerationToken token : current) {
            if (token.nodesLeft.size() == 0) {
                if (token.terminal)
                    completed.add(token);
                continue;
            }
            for (SuffixNode successor : token.currentNode.getSuccessors()) {
                if (successor.getSuffixSet().getSuffix() == token.getSuffix()) {
                    newtokens.add(token.getCopy(successor));
                }
            }
        }
        if (newtokens.size() > 0)
            traverseSuffixes(newtokens, completed);
    }
}
