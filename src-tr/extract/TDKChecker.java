package extract;

import org.jcaki.IOs;
import org.jcaki.SimpleTextReader;
import org.jcaki.SimpleTextWriter;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TDKChecker {

    static String url = "http://www.tdk.gov.tr/TR/Genel/SozBul.aspx?F6E10F8892433CFFAAF6AA849816B2EF4376734BED947CDE&Kelime=";

    public static class CheckResult {
        String word;
        boolean exist;

        public CheckResult(String word, boolean exist) {
            this.word = word;
            this.exist = exist;
        }

        public String toString() {
            return word + " : " + exist + " : ";
        }
    }

    private static Pattern contentPattern = Pattern.compile("(?:ctl00_ContentPlaceHolder1_SorguSonucu)(.+?)(?:sozlukSayac)");


    class Checker implements Callable<CheckResult> {

        final String word;

        Checker(String word) {
            this.word = word;
        }


        public CheckResult call() throws Exception {

            File f = new File("/home/afsina/data/tdk/html/" + word + ".html");
            if(f.exists())
               return new CheckResult(word,true);

            try {
                String content = IOs.readAsString(IOs.getReader(new URI(url + word).toURL().openStream()));
                if (!content.contains("sözü bulunamadı")) {
                    Matcher m = contentPattern.matcher(content);
                    if (m.find()) {
                        content = m.group(1);
                        SimpleTextWriter.oneShotUTF8Writer(f).writeLine(content);
                    } else {
                        System.out.println("No dice: " + word);
                    }
                }
                return new CheckResult(word, !content.contains("sözü bulunamadı"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new CheckResult(word,false);
            }
        }
    }

    List<CheckResult> checkAll(List<String> all) throws ExecutionException, InterruptedException {
        List<CheckResult> result = new ArrayList<CheckResult>();
        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<Future<CheckResult>> futures = new ArrayList<Future<CheckResult>>();
        for (String s : all) {
            futures.add(executor.submit(new Checker(s)));
        }
        executor.shutdown();
        int i = 0;
        for (Future<CheckResult> future : futures) {
            result.add(future.get());
            if (i++ % 50 == 0)
                System.out.println(i);
        }
        return result;
    }


    // color=mediumblue><I>isim, eskimiş</I>

    public void generateZemberekStuff() throws IOException, ExecutionException, InterruptedException {
        Set<String> zemLemmas = new HashSet<String>(ZemberekWikiCrossCheck.getZemberekLemmas());
        List<WikiLemma> wikiLemmas = ZemberekWikiCrossCheck.getWikiLemmas();
        for (WikiLemma wikiLemma : wikiLemmas) {
            zemLemmas.add(wikiLemma.lemma);
        }
        List<CheckResult> result = new TDKChecker().checkAll(new ArrayList<String>(zemLemmas));
        SimpleTextWriter stw = SimpleTextWriter.keepOpenUTF8Writer(new File("red.txt"));
        for (CheckResult checkResult : result) {
            if (!checkResult.exist)
                stw.writeLine(checkResult);
        }
        stw.close();


    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        new TDKChecker().generateZemberekStuff();
    }

    private static void tipvsvs() throws IOException, ExecutionException, InterruptedException {
        List<String> unknownWords = SimpleTextReader.trimmingUTF8Reader(new File("diff-ADJECTIVE.txt")).asStringList();
        List<CheckResult> result = new TDKChecker().checkAll(unknownWords);
        SimpleTextWriter stw = SimpleTextWriter.keepOpenUTF8Writer(new File("kabul-sıfat.txt"));
        for (CheckResult checkResult : result) {
            if (checkResult.exist)
                stw.writeLine(checkResult);
        }
        stw.close();
    }

    public static void hack() {

    }
}
