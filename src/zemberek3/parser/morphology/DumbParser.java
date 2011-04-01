package zemberek3.parser.morphology;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DumbParser {

    LexiconGraph graph;
    ArrayListMultimap<String, StemNode> stemNodes = ArrayListMultimap.create();

    public DumbParser(LexiconGraph graph) {
        this.graph = graph;
        List<StemNode> stems = graph.getStems();
        for (StemNode stem : stems) {
            stemNodes.put(stem.surfaceForm, stem);
        }
    }

    public List<ParseToken> parse(String input) {
        List<StemNode> candidates = new ArrayList<StemNode>();
        for (int i = 1; i < input.length(); i++) {
            String stem = input.substring(0, i);
            if (stemNodes.containsKey(stem)) {
                candidates.addAll(stemNodes.get(stem));
            }
        }

        List<ParseToken> parseTokens = new ArrayList<ParseToken>();
        for (StemNode candidate : candidates) {
            String rest = input.substring(candidate.surfaceForm.length());
            parseTokens.add(new ParseToken(candidate, Lists.<SuffixNode>newArrayList(candidate.getSuffixRootNode()), rest));
        }
        List<ParseToken> result = new ArrayList<ParseToken>();
        matchingSuccessors(parseTokens, result);
        return result;
    }

    class ParseToken {
        StemNode stemNode;
        SuffixNode currentNode;
        List<SuffixNode> nodeHistory;
        String rest;
        boolean terminal = false;

        ParseToken(StemNode stemNode, List<SuffixNode> nodeHistory, String rest) {
            this.stemNode = stemNode;
            this.currentNode = stemNode.getSuffixRootNode();
            this.nodeHistory = nodeHistory;
            this.rest = rest;
            this.terminal = stemNode.termination == TerminationType.TERMINAL;
        }

        ParseToken(StemNode stemNode, SuffixNode suffixNode, List<SuffixNode> nodeHistory, String rest, boolean terminal) {
            this.stemNode = stemNode;
            this.currentNode = stemNode.getSuffixRootNode();
            this.nodeHistory = nodeHistory;
            this.rest = rest;
            this.terminal = terminal;
            this.currentNode = suffixNode;
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
            return new ParseToken(stemNode, node, new ArrayList<SuffixNode>(nodeHistory), rest.substring(node.surfaceForm.length()), t);
        }
    }

    void matchingSuccessors(List<ParseToken> tokens, List<ParseToken> finished) {
        List<ParseToken> newtokens = new ArrayList<ParseToken>();
        for (ParseToken token : tokens) {
            List<SuffixNode> matches = new ArrayList<SuffixNode>();
            for (SuffixNode successor : token.currentNode.getSuccessors()) {
                if (token.rest.startsWith(successor.surfaceForm)) {
                    matches.add(successor);
                }
            }
            if (matches.size() == 0) {
                if (token.terminal)
                    finished.add(token);
            }
            for (SuffixNode match : matches) {
                newtokens.add(token.getCopy(match));
            }
        }
        if (newtokens.size() > 0)
            matchingSuccessors(newtokens, finished);
    }

    public static void main(String[] args) throws IOException {
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-lexicon.txt"));
        TurkishSuffixes suffixes = new TurkishSuffixes();
        LexiconGraph graph = new LexiconGraph(items, suffixes);
        graph.generate();
        DumbParser parser = new DumbParser(graph);

        long start = System.currentTimeMillis();
        List<ParseToken> results = parser.parse("kapağımıza");
        for (ParseToken result : results) {
            System.out.println(result.stemNode + ":" + result.nodeHistory);
        }
        System.out.println("Elapsed:" + (System.currentTimeMillis() - start));
    }
}
