package zemberek3.lexicon;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurkishLexiconGenerator {

    public static void convert(File input, File output) throws IOException {
        long elapsedTime = Files.readLines(input, Charsets.UTF_8, new LexiconFileProcessor() );

    }

    static class LexiconFileProcessor implements LineProcessor<Long> {

        public boolean processLine(String s) throws IOException {
            s = s.trim();
            if (s.length() == 0 || s.startsWith("#"))
                return true;

            String posData = getPosData(s);

            if (posData.length() == 0) {

            }


            return true;
        }

        static Pattern wordPattern = Pattern.compile("(?:^)(.?+)(?:$|\\[)");

        private String getPosData(String line) {
            String word = getGroup1Match(line, wordPattern).trim();
            if (word.length() == 0)
                throw new IllegalArgumentException("Line does not contain word :" + line);
            return word;
        }

        static Pattern posPattern = Pattern.compile("(?:Pos:)(.?+)(?:;|\\])");

        private String getPosData(String word, String line) {
            String posString = getGroup1Match(line, posPattern);
            //if (posString.length() == 0)
            return posString;
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
