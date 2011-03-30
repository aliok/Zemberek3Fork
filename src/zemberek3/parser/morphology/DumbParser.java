package zemberek3.parser.morphology;

import com.google.common.collect.ArrayListMultimap;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;
import zemberek3.lexicon.graph.MorphNode;
import zemberek3.lexicon.graph.StemNode;

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

    public List<MorphNode> parse(String input) {
        List<StemNode> candidates = new ArrayList<StemNode>();
        for (int i = 1; i < input.length(); i++) {
            String stem = input.substring(0, i);
            if (stemNodes.containsKey(stem)) {
                candidates.addAll(stemNodes.get(stem));
            }
        }
        System.out.println("candidates = " + candidates);
        return Collections.emptyList();
    }

    public static void main(String[] args) throws IOException {
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-lexicon.txt"));
        TurkishSuffixes suffixes = new TurkishSuffixes();
        LexiconGraph graph = new LexiconGraph(items, suffixes);
        graph.generate();
        DumbParser parser = new DumbParser(graph);
        List<MorphNode> result = parser.parse("kapağa");
        System.out.println("result = " + result);
    }
}
