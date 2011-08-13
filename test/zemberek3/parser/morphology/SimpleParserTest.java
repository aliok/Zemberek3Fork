package zemberek3.parser.morphology;

import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.graph.DynamicLexiconGraph;
import zemberek3.lexicon.graph.DynamicSuffixes;
import zemberek3.lexicon.graph.Suffixes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleParserTest {

    @Test
    public void testSimpleNouns() throws IOException {
        SuffixProvider suffixProvider = getProvider1();

        String[] nouns = {"elma", "armut"};
        List<DictionaryItem> items = getItems(nouns, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);

        SimpleParser parser = new SimpleParser(graph);
        List<ParseResult> results = parser.parse("elmalar");
        for (ParseResult result : results) {
            System.out.println(result.asParseString());
        }

    }

    private List<DictionaryItem> getItems(String[] lines, SuffixProvider suffixProvider) {
        TurkishDictionaryLoader loader = new TurkishDictionaryLoader(suffixProvider);
        List<DictionaryItem> items = new ArrayList<DictionaryItem>();
        for (String line : lines) {
            items.add(loader.loadFromString(line));
        }
        return items;
    }

    public SuffixProvider getProvider1() {
        Suffix Dat = new Suffix("Dat");
        SuffixFormSet Dat_yA = new SuffixFormSet(Dat, "+yA");
        Suffix P1sg = new Suffix("P1sg");
        SuffixFormSet P1sg_Im = new SuffixFormSet(P1sg, "Im");
        Suffix Pnon = new Suffix("Pnon");
        SuffixFormSet Pnon_EMPTY = new SuffixFormSet("Pnon_EMPTY", Pnon, "");
        Suffix Nom = new Suffix("Nom");
        SuffixFormSet Nom_EMPTY = new SuffixFormSet("Nom_EMPTY", Nom, ""); // ben
        Suffix A3sg = new Suffix("A3sg");
        SuffixFormSet A3sg_EMPTY = new SuffixFormSet("A3sg_EMPTY", A3sg, ""); // gel-di-, o-
        Suffix A3pl = new Suffix("A3pl");
        SuffixFormSet A3pl_lAr = new SuffixFormSet(A3pl, "lAr"); // gel-ecek-ler

        DynamicSuffixes suffixes = new DynamicSuffixes();
        
        DynamicSuffixes.Noun_Main.add(A3pl_lAr, A3sg_EMPTY);
        A3sg_EMPTY.add(P1sg_Im, Pnon_EMPTY);
        A3pl_lAr.add(P1sg_Im, Pnon_EMPTY);
        P1sg_Im.add(Nom_EMPTY, Dat_yA);

        suffixes.addSuffixForms(
                DynamicSuffixes.Noun_Main, A3sg_EMPTY, A3pl_lAr,
                P1sg_Im, Pnon_EMPTY, Nom_EMPTY, Dat_yA);

        return suffixes.getSuffixProvider();
    }

}
