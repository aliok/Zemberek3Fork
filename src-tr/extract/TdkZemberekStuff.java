package extract;

import org.jcaki.Files;
import org.jcaki.SimpleTextReader;
import org.jcaki.SimpleTextWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TdkZemberekStuff {


    public static void read() throws IOException {
        List<File> files = Files.getFilesSorted(
                new File("/home/afsina/data/tdk/html"),
                Files.getNameSortingComparator(new Locale("tr")));
        List<String> lines = new ArrayList<String>();
        int i = 0;
        for (File file : files) {
            String word = file.getName().replaceAll("\\.html", "");
            String content = SimpleTextReader.trimmingUTF8Reader(file).asString();
            if (content.contains("isim")) {
                if (Character.isUpperCase(word.charAt(0)))
                    lines.add(word + " [PropNoun]");
                else
                    lines.add(word + " [Noun]");
            }
            if (content.contains("sıfat")) {
                if (Character.isUpperCase(word.charAt(0)))
                    lines.add(word + " [PropNoun]");
                else
                    lines.add(word + " [Adj]");
            }
            if (content.contains("zarf"))
                lines.add(word + " [Adv]");
            if (content.contains("ünlem"))
                lines.add(word + " [Interj]");
            if (content.contains("edat"))
                lines.add(word + " [Particle]");
            if (content.contains("zamir"))
                lines.add(word + " [Pron]");
            if (content.contains("bağlaç"))
                lines.add(word + " [Conj]");
            if (content.replace("fiiller", "").contains("fiil"))
                lines.add(word + " [Verb]");
            if (++i % 100 == 0)
                System.out.println(i);
        }
        SimpleTextWriter.oneShotUTF8Writer(new File("all-tdk.txt")).writeLines(lines);
    }

    public static void main(String[] args) throws IOException {
        read();
    }
}
