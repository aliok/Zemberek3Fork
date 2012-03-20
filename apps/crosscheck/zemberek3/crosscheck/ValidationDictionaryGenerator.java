package zemberek3.crosscheck;

import org.jcaki.Files;
import org.jcaki.SimpleTextReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationDictionaryGenerator {
    File htmlRoot;
    List<File> dirs = new ArrayList<File>();

    public ValidationDictionaryGenerator(File htmlRoot) {
        this.htmlRoot = htmlRoot;
        dirs = Files.getDirectories(htmlRoot, false);
    }

    Pattern HEADER_PATTERN = Pattern.compile("(?:<th scope='col'>)(.+?)(?:</th>)", Pattern.DOTALL | Pattern.MULTILINE);
    Pattern NAME_PATTERN = Pattern.compile("(?:<b>)(.+?)(?:</b>)", Pattern.DOTALL | Pattern.MULTILINE);
    Pattern TYPE_PATTERN = Pattern.compile("(?:<i><b>)(.+?)(?:</b>)", Pattern.DOTALL | Pattern.MULTILINE);
    Pattern ATTR_PATTERN = Pattern.compile("(?:</b>)(.+?)(?:<br>)", Pattern.DOTALL | Pattern.MULTILINE);
    Pattern TYPE_GROUP_PATTERN = Pattern.compile("(?:<i>)(.+?)(?:$)", Pattern.DOTALL | Pattern.MULTILINE);
    Pattern ORIGIN_PATTERN = Pattern.compile("(?:</b>)(.+?)(?:</i>$)", Pattern.DOTALL | Pattern.MULTILINE);

    private List<String> group1Matches(String s, Pattern p) {
        Matcher m = p.matcher(s);
        List<String> res = new ArrayList<String>();
        while (m.find()) {
            res.add(m.group(1).trim());
        }
        return res;
    }

    private String group1Match(String s, Pattern p) {
        Matcher m = p.matcher(s);
        List<String> res = new ArrayList<String>();
        if (m.find()) {
            return m.group(1).trim();
        }
        return "";
    }

    void collectHeaderData() throws IOException {
        Set<String> attr = new HashSet<String>();
        for (File dir : dirs) {
            List<File> files = Files.crawlDirectory(dir, false, Files.extensionFilter("html"));
            for (File file : files) {
                String content = SimpleTextReader.trimmingUTF8Reader(file).asString();
                List<String> headers = group1Matches(content, HEADER_PATTERN);
                for (String header : headers) {
                    String name = group1Match(header, NAME_PATTERN);
                    String typeData = group1Match(header, TYPE_PATTERN);
                    String attrData = group1Match(header, ATTR_PATTERN);
                    String origin = group1Match(group1Match(header, TYPE_GROUP_PATTERN), ORIGIN_PATTERN);
                    System.out.println(header);
                    System.out.println("name = " + name);
                    System.out.println("attrData = " + attrData);
                    System.out.println("typeData = " + typeData);
                    System.out.println("origin = " + origin);
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ValidationDictionaryGenerator gen = new ValidationDictionaryGenerator(new File("/home/afsina/data/tdk/html"));
        gen.collectHeaderData();
    }


    class Item {
        String lemma;
        String propertyString;
        TdkPos mainType;
        Origin originLang;
        String originLemma;
        Domain mainDomain;
        String pronunciation;
        List<ItemEntry> entries = new ArrayList<ItemEntry>();
        List<String> idioms = new ArrayList<String>();
    }

    class ItemEntry {
        TdkPos type;
        String meaning;
        String example;
        Domain domain;
    }

    enum TdkPos {
        NOUN, VERB, ADJECTIVE, ADVERB, CONJUNCTION
    }

    enum Origin {
        TURKISH, ARABIC, PERSIAN, ENGLISH, FRENCH, GERMAN, ITALIAN, GREEK, ARMENIAN
    }

    enum Domain {
        GENERAL, TECHNICAL, SLANG, COMMON, GEOLOGY, IDIOM, MEDICAL, BOTANIC, ZOOLOGY, LAW, OLD
    }
}
