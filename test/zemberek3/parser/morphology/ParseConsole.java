package zemberek3.parser.morphology;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.SuffixProvider;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.lexicon.graph.LexiconGraph;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ParseConsole {

    public void doit() throws IOException {
        SimpleParser parser = simpleParser(new File("src/resources/tr/master-dictionary.txt"));
        String input;
        System.out.println("Enter word:");
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equals("exit") && !input.equals("quit")) {
            List<ParseResult> tokens = parser.parse(input);
            if (tokens.size() == 0) {
                System.out.println("cannot be parsed");
            } else {
                for (ParseResult token : tokens) {
                    System.out.println(token.asParseString());
                }
            }
            input = sc.nextLine();
        }
    }

    private SimpleParser simpleParser(File dictionary) throws IOException {
        LexiconGraph graph = getLexiconGraph(dictionary);
        return new SimpleParser(graph);
    }

    private LexiconGraph getLexiconGraph(File dictionary) throws IOException {
        SuffixProvider suffixProvider = new TurkishSuffixes().getSuffixProvider();
        List<DictionaryItem> items = new TurkishDictionaryLoader(suffixProvider).load(dictionary);
        LexiconGraph graph = new LexiconGraph(items, suffixProvider);
        graph.generate();
        return graph;
    }

    public static void main(String[] args) throws IOException {
        new ParseConsole().doit();
    }
}
