package extract;

import com.google.common.collect.Sets;
import org.jcaki.SimpleTextWriter;
import org.jcaki.Strings;
import z3.structure.POS;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurkishWictionaryExtractor {


    static Set<String> typeSet = new HashSet<String>();

    static Set<String> ignoreType = Sets.newHashSet("Önek", "Sonek", "Birleşik Sıfat", "Mecaz",
            "Ad{{Deyimler}}", "Zarf}}", "Birleşik Fiil", "İsim|Türk==={{Söztürü|İsim", "Argo", "Matematik", "Kimya",
            "Yardımcı eylem", "Ek Eylem", "Yanlış", "Çokluk", "Emir", "Deyim", "Simge", "Kız adı", "kadın-Erkek ismi",
            "Soyadı", "Erkek adı", "Erkek ismi", "Kısaltma", "Belirteç", "Kız ismi", "Sayı", "Kısaltılmış sözcük",
            "Ek", "Harf", "Özel isim", "Özel ad");



    public static void main(String[] args) throws XMLStreamException, IOException {
        FileInputStream fileInputStream = new FileInputStream("/home/kodlab/Downloads/trwiktionary.xml");
        XMLStreamReader staxXmlReader = XMLInputFactory.newInstance().createXMLStreamReader(fileInputStream);

        String title = null;
        SimpleTextWriter stw = SimpleTextWriter.keepOpenUTF8Writer(new File("wictionary-lemmas.txt"));
        SimpleTextWriter compoundWriter = SimpleTextWriter.keepOpenUTF8Writer(new File("wictionary-compounds.txt"));
        SimpleTextWriter properNounWriter = SimpleTextWriter.keepOpenUTF8Writer(new File("wictionary-proper-nouns.txt"));
        for (int event = staxXmlReader.next(); event != XMLStreamConstants.END_DOCUMENT; event = staxXmlReader.next()) {
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elName = staxXmlReader.getLocalName();
                    if (elName.equals("title")) {
                        title = staxXmlReader.getElementText();
                    }

                    if (elName.equals("text")) {
                        String content = staxXmlReader.getElementText();
                        List<WikiLemma> lemmas = getLemmas(title, content);
                        for (WikiLemma lemma : lemmas) {

                            if (lemma.lemma.contains(" "))
                                compoundWriter.writeLine(lemma);
                            else if (Character.isUpperCase(lemma.lemma.charAt(0))) {
                                properNounWriter.writeLine(lemma);
                            } else {
                                if (accept(lemma)) {
                                    stw.writeLine(lemma);
                                }
                            }
                        }
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    // System.out.println("End element " + staxXmlReader.getLocalName());
                    break;
                default:
                    break;
            }
        }
        stw.close();
        compoundWriter.close();
        properNounWriter.close();
        staxXmlReader.close();

        for (String s : typeSet) {
            System.out.println("pos = " + s);
        }

    }

    static boolean accept(WikiLemma lemma) {
        return lemma.pos != POS.UNKNOWN &&
                Character.isLowerCase(lemma.lemma.charAt(0)) &&
                Strings.containsNone(lemma.lemma, "0123456789");
    }

    static Pattern pattern = Pattern.compile("(?:Söztürü\\|)(.+?)(?:\\|Türkçe)");

    private static List<WikiLemma> getLemmas(String lemma, String content) {
        List<WikiLemma> lemmas = new ArrayList<WikiLemma>();

        Matcher m = pattern.matcher(content);
        while (m.find()) {
            String type = m.group(1);
            if (type.contains("çekilmiş"))
                continue;
            lemmas.add(new WikiLemma(m.group(1), lemma));
        }
        return lemmas;
    }

  
}
