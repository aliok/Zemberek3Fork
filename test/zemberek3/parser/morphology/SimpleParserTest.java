package zemberek3.parser.morphology;

import org.junit.Test;
import zemberek3.lexicon.*;
import zemberek3.lexicon.graph.DynamicLexiconGraph;
import zemberek3.lexicon.graph.DynamicSuffixes;
import zemberek3.lexicon.graph.Suffixes;
import zemberek3.lexicon.graph.TerminationType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleParserTest {

    @Test
    public void testSimpleNouns() throws IOException {
        SuffixProvider suffixProvider = getProvider2();

        String[] nouns = {"armut"};
        List<DictionaryItem> items = getItems(nouns, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);

        SimpleParser parser = new SimpleParser(graph);
        List<ParseResult> results = parser.parse("armut");
        for (ParseResult result : results) {
            System.out.println(result.asParseString());
        }

    }

    @Test
    public void testSuffixNonDeterminism() throws IOException {
        SuffixProvider suffixProvider = getProvider3();

        String[] nouns = {"elma"};
        List<DictionaryItem> items = getItems(nouns, suffixProvider);
        DynamicLexiconGraph graph = new DynamicLexiconGraph(suffixProvider);
        graph.addDictionaryItems(items);

        SimpleParser parser = new SimpleParser(graph);
        List<ParseResult> results = parser.parse("elmacık");
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
        SuffixFormSet Pnon_EMPTY = new SuffixFormSet("Pnon_EMPTY", Pnon, "", TerminationType.TRANSFER);
        Suffix Nom = new Suffix("Nom");
        SuffixFormSet Nom_EMPTY = new SuffixFormSet("Nom_EMPTY", Nom, "", TerminationType.TRANSFER);
        Suffix A3sg = new Suffix("A3sg");
        SuffixFormSet A3sg_EMPTY = new SuffixFormSet("A3sg_EMPTY", A3sg, "", TerminationType.TRANSFER);
        Suffix A3pl = new Suffix("A3pl");
        SuffixFormSet A3pl_lAr = new SuffixFormSet(A3pl, "lAr"); // gel-ecek-ler

        DynamicSuffixes suffixes = new DynamicSuffixes();

        DynamicSuffixes.Noun_Main.add(A3pl_lAr, A3sg_EMPTY);
        A3sg_EMPTY.add(P1sg_Im, Pnon_EMPTY);
        A3pl_lAr.add(P1sg_Im, Pnon_EMPTY);
        P1sg_Im.add(Nom_EMPTY, Dat_yA);
        Pnon_EMPTY.add(Nom_EMPTY, Dat_yA);

        suffixes.addSuffixForms(
                DynamicSuffixes.Noun_Main, A3sg_EMPTY, A3pl_lAr,
                P1sg_Im, Pnon_EMPTY, Nom_EMPTY, Dat_yA);

        return suffixes.getSuffixProvider();
    }

    private SuffixFormSet getSet(String suffixId, String generationStr) {
        return new SuffixFormSet(new Suffix(suffixId), generationStr);
    }

    private SuffixFormSet getNullSet(String suffixId, String id) {
        return new SuffixFormSet(id, new Suffix(suffixId), "", TerminationType.TRANSFER);
    }


    public SuffixProvider getProvider2() {
        SuffixFormSet P1sg_Im = getSet("P1sg", "Im");
        SuffixFormSet Pnon_EMPTY = getNullSet("Pnon", "Pnon_EMPTY");
        SuffixFormSet A3sg_EMPTY = getNullSet("A3sg", "A3sg_EMPTY");
        SuffixFormSet A3pl_lAr = getSet("A3pl", "lAr");

        DynamicSuffixes suffixes = new DynamicSuffixes();

        DynamicSuffixes.Noun_Main.add(A3pl_lAr, A3sg_EMPTY);
        A3sg_EMPTY.add(P1sg_Im, Pnon_EMPTY);
        A3pl_lAr.add(P1sg_Im, Pnon_EMPTY);

        suffixes.addSuffixForms(DynamicSuffixes.Noun_Main, A3sg_EMPTY, A3pl_lAr, P1sg_Im, Pnon_EMPTY);

        return suffixes.getSuffixProvider();
    }

    public SuffixProvider getProvider3() {
        SuffixFormSet Dim_CIK = getSet("Dim", ">cI~k");
        SuffixFormSet P1sg_Im = getSet("P1sg", "Im");
        SuffixFormSet Dat_yA = getSet("Dat", "+yA");
        SuffixFormSet Pnon_EMPTY = getNullSet("Pnon", "Pnon_EMPTY");
        SuffixFormSet Pnon_EMPTY_ = getNullSet("Pnon", "Pnon_EMPTY_");
        SuffixFormSet Nom_EMPTY = getNullSet("Nom", "Nom_EMPTY");
        SuffixFormSet Nom_EMPTY_ = getNullSet("Nom", "Nom_EMPTY_");
        SuffixFormSet A3sg_EMPTY = getNullSet("A3sg", "A3sg_EMPTY");
        SuffixFormSet A3pl_lAr = getSet("A3pl", "lAr");

        DynamicSuffixes suffixes = new DynamicSuffixes();

        DynamicSuffixes.Noun_Main.add(A3pl_lAr, A3sg_EMPTY);
        A3sg_EMPTY.add(P1sg_Im, Pnon_EMPTY, Pnon_EMPTY_);
        A3pl_lAr.add(P1sg_Im, Pnon_EMPTY, Pnon_EMPTY_);
        Pnon_EMPTY.add(Nom_EMPTY, Nom_EMPTY_, Dat_yA);
        Pnon_EMPTY_.add(Nom_EMPTY_);
        Nom_EMPTY.add(Dim_CIK);
        Dim_CIK.add(DynamicSuffixes.Noun_Main);

        suffixes.addSuffixForms(
                DynamicSuffixes.Noun_Main, A3sg_EMPTY, A3pl_lAr,
                P1sg_Im, Pnon_EMPTY,Pnon_EMPTY_, Dat_yA, Dim_CIK, Nom_EMPTY, Nom_EMPTY_);

        return suffixes.getSuffixProvider();
    }

}
