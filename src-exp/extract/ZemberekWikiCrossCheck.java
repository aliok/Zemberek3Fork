package extract;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import net.zemberek.yapi.DilBilgisi;
import net.zemberek.yapi.KelimeTipi;
import net.zemberek.yapi.Kok;
import net.zemberek.yapi.TurkceDilBilgisi;
import org.jcaki.IOs;
import org.jcaki.SimpleTextReader;
import org.jcaki.SimpleTextWriter;
import org.jcaki.Strings;
import zemberek3.structure.POS;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.*;


public class ZemberekWikiCrossCheck {

    static Map<KelimeTipi, POS> posMap = new HashMap<KelimeTipi, POS>();

    static {
        posMap.put(KelimeTipi.BAGLAC, POS.CONJUNCTION);
        posMap.put(KelimeTipi.FIIL, POS.VERB);
        posMap.put(KelimeTipi.SIFAT, POS.ADJECTIVE);
        posMap.put(KelimeTipi.ZAMIR, POS.PRONOUN);
        posMap.put(KelimeTipi.ISIM, POS.NOUN);
        posMap.put(KelimeTipi.EDAT, POS.PARTICLE);
        posMap.put(KelimeTipi.ZARF, POS.ADVERB);
        posMap.put(KelimeTipi.ZAMAN, POS.ADVERB);
        posMap.put(KelimeTipi.UNLEM, POS.INTERJECTION);
    }

    static SetMultimap<POS, String> wikiTypeSetMap = HashMultimap.create();
    static SetMultimap<POS, String> zemberekTypeSetMap = HashMultimap.create();

    public static void main(String[] args) throws IOException {
        Set<String> zemLemmas = new HashSet<String>(getZemberekLemmas());
        List<WikiLemma> wikiLemmas = getWikiLemmas();
        for (WikiLemma wikiLemma : wikiLemmas) {
            zemLemmas.add(wikiLemma.lemma);
        }

    }

    private static void findUnknownStuff() throws IOException {
        DilBilgisi db = new TurkceDilBilgisi(new TurkiyeTurkcesi());
        List<WikiLemma> wikiLemmas = getWikiLemmas();
        for (WikiLemma wikiLemma : wikiLemmas) {
            wikiTypeSetMap.put(wikiLemma.pos, wikiLemma.lemma);
        }
        for (Kok kok : db.kokler().tumKokler()) {
            if (!posMap.containsKey(kok.tip())) {
                System.out.println(kok);
            } else {
                zemberekTypeSetMap.put(posMap.get(kok.tip()), kok.icerik());
            }
        }

        for (POS pos : wikiTypeSetMap.keySet()) {
            List<String> unknown = getMissingLemmas(pos);
            Collections.sort(unknown, Collator.getInstance(new Locale("tr")));
            SimpleTextWriter.oneShotUTF8Writer(new File("diff-" + pos.name() + ".txt")).writeLines(unknown);
        }
    }

    private static List<String> getMissingLemmas(POS pos) {
        List<String> unknown = new ArrayList<String>();
        Set<String> wikiWords = wikiTypeSetMap.get(pos);
        Set<String> zemberekWords = zemberekTypeSetMap.get(pos);
        for (String s : wikiWords) {
            if (!zemberekWords.contains(s)) {
                unknown.add(s);
            }
        }
        return unknown;
    }

    public static List<WikiLemma> getWikiLemmas() throws IOException {
        List<WikiLemma> lemmas = new ArrayList<WikiLemma>();
        for (String line : SimpleTextReader.trimmingIterableLineReader(
                IOs.getClassPathResourceAsStream("/resources/tr/wictionary/wictionary-lemmas.txt"), "UTF-8")) {
            lemmas.add(new WikiLemma(
                    Strings.subStringAfterLast(line, ":").trim(),
                    Strings.subStringUntilLast(line, ":").trim()));
        }
        return lemmas;
    }

    public static List<String> getZemberekLemmas() throws IOException {
        List<String> lemmas = new ArrayList<String>();
        for (String line : SimpleTextReader.trimmingIterableLineReader(
                IOs.getClassPathResourceAsStream("/resources/tr/master-dictionary.txt"), "UTF-8")) {
            lemmas.add(Strings.subStringUntilFirst(line, " ").trim());
        }
        return lemmas;
    }
}
