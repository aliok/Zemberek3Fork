package zemberek3.parser.morphology;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import zemberek3.lexicon.graph.*;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleParser implements MorphParser {

    DynamicLexiconGraph graph;
    ArrayListMultimap<String, StemNode> multiStems = ArrayListMultimap.create(1000, 2);
    Map<String, StemNode> singeStems = new HashMap<String, StemNode>();

    public SimpleParser(DynamicLexiconGraph graph) {
        this.graph = graph;
        for (StemNode stemNode : graph.getStemNodes()) {
            final String surfaceForm = stemNode.surfaceForm;
            if (multiStems.containsKey(surfaceForm)) {
                multiStems.put(surfaceForm, stemNode);
            } else if (singeStems.containsKey(surfaceForm)) {
                multiStems.put(surfaceForm, singeStems.get(surfaceForm));
                singeStems.remove(surfaceForm);
                multiStems.put(surfaceForm, stemNode);
            } else
                singeStems.put(surfaceForm, stemNode);
        }
    }

    public List<ParseResult> parse(String input) {
        // get stem candidates.
        List<StemNode> candidates = Lists.newArrayList();
        for (int i = 1; i <= input.length(); i++) {
            String stem = input.substring(0, i);
            if (singeStems.containsKey(stem)) {
                candidates.add(singeStems.get(stem));
            } else if (multiStems.containsKey(stem)) {
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
                    if (token.rest.length() > 0) {
                        newtokens.add(token.getCopy(successor));
                        matchFound = true;
                    } else {
                        if (successor.termination != TerminationType.NON_TERMINAL) {
                            newtokens.add(token.getCopy(successor));
                            matchFound = true;
                        }
                    }
                } else {
//                    System.out.println("No match:" +  successor.getSuffixSet().getId() );
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

    public void dump(String input) {
        // get stem candidates.
        System.out.println("  Input:" + input);
        List<StemNode> candidates = Lists.newArrayList();
        for (int i = 1; i <= input.length(); i++) {
            String stem = input.substring(0, i);
            if (singeStems.containsKey(stem)) {
                candidates.add(singeStems.get(stem));
            } else if (multiStems.containsKey(stem)) {
                candidates.addAll(multiStems.get(stem));
            }
        }
        System.out.println("  Stem Nodes:");
        for (StemNode candidate : candidates) {
            System.out.println(candidate);
        }

        System.out.println("  Traverse Paths:");
        // generate starting tokens with suffix root nodes.
        List<ParseToken> initialTokens = Lists.newArrayList();
        for (StemNode candidate : candidates) {
            String rest = input.substring(candidate.surfaceForm.length());
            initialTokens.add(new ParseToken(candidate, Lists.<SuffixNode>newArrayList(candidate.getSuffixRootNode()), rest));
        }

        // traverse suffix graph.
        List<ParseResult> result = Lists.newArrayList();
        dumpTraverse(initialTokens, result);

    }

    private void dumpTraverse(List<ParseToken> current, List<ParseResult> completed) {
        List<ParseToken> newtokens = Lists.newArrayList();
        for (ParseToken token : current) {
            boolean matchFound = false;
            for (SuffixNode successor : token.currentNode.getSuccessors()) {
                if (token.rest.startsWith(successor.surfaceForm)) {
                    System.out.println(successor.getSuffixSet().getId());
                    final ParseToken copy = token.getCopy(successor);
                    if (token.rest.length() > 0) {
                        newtokens.add(copy);
                        matchFound = true;
                    } else {
                        if (successor.termination != TerminationType.NON_TERMINAL) {
                            newtokens.add(copy);
                            matchFound = true;
                        }
                    }
                }
            }
            if (!matchFound) {
                if (token.rest.length() == 0 && token.terminal)
                    completed.add(token.getResult());
                else {
                    System.out.println("Failed:" + token.getResult());
                }
            }
        }
        if (!newtokens.isEmpty())
            dumpTraverse(newtokens, completed);
    }
}
