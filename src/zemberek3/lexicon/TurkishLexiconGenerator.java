package zemberek3.lexicon;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import javax.swing.text.Position;
import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurkishLexiconGenerator {

    public static void convert(File input, File output) throws IOException {
        long elapsedTime = Files.readLines(input, Charsets.UTF_8, new LexiconFileProcessor());

    }

    static class LexiconFileProcessor implements LineProcessor<Long> {

        public boolean processLine(String line) throws IOException {
            line = line.trim();
            if (line.length() == 0 || line.startsWith("#"))
                return true;
            String word = getWord(line);
            PosInfo posInfo = getPosData(word, line);
            return true;
        }

        static Pattern wordPattern = Pattern.compile("(?:^)(.?+)(?:$|\\[)");

        private String getWord(String line) {
            String word = getGroup1Match(line, wordPattern).trim();
            if (word.length() == 0)
                throw new IllegalArgumentException("Line does not contain word :" + line);
            return word;
        }

        static Pattern posPattern = Pattern.compile("(?:Pos:)(.?+)(?:;|\\])");

        private PosInfo getPosData(String word, String line) {
            String posString = getGroup1Match(line, posPattern).trim();
            if (posString.length() == 0) {
                //infer the type.
                if (word.endsWith("mek") || word.endsWith("mak")) {
                    System.out.println("Verb: " + word);
                    return new PosInfo(PrimaryPos.Verb, SecondaryPos.None);
                } else {
                    System.out.println("Noun: " + word);
                    return new PosInfo(PrimaryPos.Noun, SecondaryPos.None);
                }
            } else {
                PrimaryPos primaryPos = null;
                SecondaryPos secondaryPos = null;
                for (String s : Splitter.on(",").split(posString)) {
                    s = s.trim();
                    if (PrimaryPos.converter().enumExists(s)) {
                        if (primaryPos != null)
                            throw new LexiconGenerationException("Multiple primary pos in :" + line);
                        else primaryPos = PrimaryPos.converter().getEnum(s);
                    } else if (SecondaryPos.converter().enumExists(s)) {
                        if (secondaryPos != null)
                            throw new LexiconGenerationException("Multiple secondary pos in :" + line);
                        else secondaryPos = SecondaryPos.converter().getEnum(s);
                    } else
                        throw new LexiconGenerationException("Unrecognized pos data [" + s + "] in :" + line);
                }
                return new PosInfo(primaryPos, secondaryPos);
            }

        }

        public Long getResult() {
            return null;
        }

        private String getGroup1Match(String line, Pattern pattern) {
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                return m.group(1);
            } else return "";
        }
    }

    static class PosInfo {
        PrimaryPos primaryPos;
        SecondaryPos secondaryPos;

        PosInfo(PrimaryPos primaryPos, SecondaryPos secondaryPos) {
            this.primaryPos = primaryPos;
            this.secondaryPos = secondaryPos;
        }
    }

}
