package zemberek3.crosscheck;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import org.jcaki.IOs;
import org.jcaki.SimpleTextReader;
import org.jcaki.SimpleTextWriter;
import org.jcaki.Strings;
import zemberek3.structure.TurkishAlphabet;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TDKVerifier {
    File htmlRoot;
    String tdkRootPrefix;

    public TDKVerifier(File htmlRoot, String tdkRootPrefix) {
        this.htmlRoot = htmlRoot;
        this.tdkRootPrefix = tdkRootPrefix;
    }

    public static void generateLemmaFileFromBigDictionary(File input, File singleWord, File multiWordFile) throws IOException {
        List<String> allLines = SimpleTextReader.trimmingUTF8Reader(input).asStringList();
        Set<String> singleWordLemmas = new LinkedHashSet<String>();
        Set<String> multiWords = new LinkedHashSet<String>();
        for (String line : allLines) {
            if (!Strings.containsNone(line, "0123456789*():;-.+"))
                continue;
            line = line.replaceAll("[!?]", "");
            if (line.indexOf(' ') < 0) {
                singleWordLemmas.add(line);
                if (!Strings.containsNone(line, "âîÂû")) {
                    String noCirc = line.replaceAll("â", "a").replaceAll("î", "i").replaceAll("Â", "A").replaceAll("û", "ü");
                    String noCirc2 = line.replaceAll("â", "a").replaceAll("î", "i").replaceAll("Â", "A").replaceAll("û", "u");
                    singleWordLemmas.add(noCirc);
                    singleWordLemmas.add(noCirc2);
                }
            } else
                multiWords.add(line);
        }
        SimpleTextWriter.oneShotUTF8Writer(singleWord).writeLines(singleWordLemmas);
        SimpleTextWriter.oneShotUTF8Writer(multiWordFile).writeLines(multiWords);
    }

    static Pattern contentPattern = Pattern.compile("(<table width='630' border=0  id='hor-minimalist-a'><thead>)(.+?)(?:<span id=\"parmak\">)", Pattern.DOTALL | Pattern.MULTILINE);

    public static String extracContentOnly(String all) {
        Matcher m = contentPattern.matcher(all);
        if (m.find()) {
            return m.group(1) + m.group(2);
        }
        return "";
    }

    public boolean retrieveAndSaveWord(String word) throws IOException {
        InputStream is = URI.create(tdkRootPrefix + word).toURL().openStream();
        String allHtml = new SimpleTextReader(is, "utf-8").asString();
        if (allHtml.contains("<b>" + word)) {
            File file = getFileForWord(word);
            if (!file.exists()) {
                String content = extracContentOnly(allHtml);
                if (content.length() > 0) {
                    SimpleTextWriter.oneShotUTF8Writer(file).write(content);
                    return true;
                } else {
                    System.out.println("No content :" + word);
                    return false;
                }
            } else return true;
        }
        return false;
    }

    static Locale trLocale = new Locale("tr");

    private File getFileForWord(String word) {
        File dir = new File(htmlRoot, String.valueOf(word.charAt(0)));
        if (!dir.exists() && !dir.mkdirs())
            throw new IllegalStateException("Cannot create dir " + dir);
        return new File(dir, word + ".html");
    }

    static Random rnd = new Random();

    public List<String> loadAll(List<String> words, Set<String> ignore) throws IOException {
        List<String> notFound = new ArrayList<String>();
        for (String word : words) {
            if (ignore.contains(word)) {
                System.out.println(word + " ignored");
                continue;
            }
            File file = getFileForWord(word);
            if (!file.exists()) {
                try {
                    if (retrieveAndSaveWord(word)) {
                        System.out.println(word + "=" + file);
                        try {
                            Thread.sleep(rnd.nextInt(700));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else
                        notFound.add(word);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(word + " already exist");
            }

        }
        return notFound;
    }


    public static void main(String[] args) throws IOException {
/*        generateLemmaFileFromBigDictionary(
                new File("src/resources/tr/sozluk.txt"),
                new File("src/resources/tr/single-lemma.txt"),
                new File("src/resources/tr/multi-lemma.txt")
        );*/

        TDKVerifier verifier = new TDKVerifier(
                new File("/home/afsina/data/tdk/html"),
                "http://www.tdk.gov.tr/index.php?option=com_gts&arama=gts&kelime=");

        File lemmaFile = new File("src/resources/tr/single-lemma.txt");
        List<String> all = SimpleTextReader.trimmingUTF8Reader(lemmaFile).asStringList();

        File ignoreFile = new File("src/resources/tr/ignore.txt");
        Set<String> ignore = new HashSet<String>();
        if (ignoreFile.exists())
            ignore = new HashSet<String>(SimpleTextReader.trimmingUTF8Reader(ignoreFile).asStringList());

        List<String> notFound = verifier.loadAll(all.subList(10000, 12000), ignore);

        ignore.addAll(notFound);

        SimpleTextWriter.oneShotUTF8Writer(ignoreFile).writeLines(ignore);


    }


}
