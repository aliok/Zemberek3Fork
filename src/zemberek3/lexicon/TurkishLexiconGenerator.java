package zemberek3.lexicon;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.TurkishAlphabet;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurkishLexiconGenerator {

    public static void convert(File input, File output) throws IOException {
        List<LexiconItem> items = Files.readLines(input, Charsets.UTF_8, new LexiconFileProcessor());
        for (LexiconItem item : items) {
            System.out.println(item);
        }
    }

    static class LexiconFileProcessor implements LineProcessor<List<LexiconItem>> {

        TurkishAlphabet alphabet = new TurkishAlphabet();
        List<LexiconItem> lexiconItems = new ArrayList<LexiconItem>();

        public boolean processLine(String line) throws IOException {
            line = line.trim();
            if (line.length() == 0 || line.startsWith("#"))
                return true;
            String word = getWord(line);
            PosInfo posInfo = getPosData(word, line);
            MorphemicAttribute[] morphemicAttributes = morphemicAttributes(word, posInfo, line);
            lexiconItems.add(new LexiconItem(word, posInfo.primaryPos, posInfo.secondaryPos, morphemicAttributes));
            return true;
        }

        static Pattern wordPattern = Pattern.compile("(?:^)(.+?)(?:$|\\[)");

        private String getWord(String line) {
            String word = getGroup1Match(line, wordPattern).trim();
            if (word.length() == 0)
                throw new IllegalArgumentException("Line does not contain word :" + line);
            return word;
        }

        static Pattern posPattern = Pattern.compile("(?:Pos:)(.+?)(?:;|\\])");

        private PosInfo getPosData(String word, String line) {
            String posString = getGroup1Match(line, posPattern).trim();
            if (posString.length() == 0) {
                //infer the type.
                return new PosInfo(inferPrimaryPos(word), inferSecondaryPos(word));
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
                if (primaryPos == null) {
                    primaryPos = inferPrimaryPos(word);
                }
                if (secondaryPos == null) {
                    secondaryPos = inferSecondaryPos(word);
                }
                return new PosInfo(primaryPos, secondaryPos);
            }

        }

        private PrimaryPos inferPrimaryPos(String word) {
            if (word.endsWith("mek") || word.endsWith("mak")) {
                return PrimaryPos.Verb;
            } else {
                return PrimaryPos.Noun;
            }
        }

        private SecondaryPos inferSecondaryPos(String word) {
            if (Character.isUpperCase(word.charAt(0))) {
                return SecondaryPos.ProperNoun;
            } else return SecondaryPos.None;
        }

        static Pattern attributePattern = Pattern.compile("(?:A:)(.+?)(?:;|\\])");

        private MorphemicAttribute[] morphemicAttributes(String word, PosInfo posData, String line) {
            List<MorphemicAttribute> attributesList = new ArrayList<MorphemicAttribute>(2);
            String attributeStr = getGroup1Match(line, attributePattern).trim();
            if (attributeStr.length() == 0) {
                inferMorphemicAttributes(word, posData, attributesList);
            } else {
                for (String s : Splitter.on(",").split(attributeStr)) {
                    s = s.trim();
                    if (!MorphemicAttribute.converter().enumExists(s))
                        throw new LexiconGenerationException("Unrecognized attribute data [" + s + "] in :" + line);
                    MorphemicAttribute morphemicAttribute = MorphemicAttribute.converter().getEnum(s);
                    attributesList.add(morphemicAttribute);
                }
                inferMorphemicAttributes(word, posData, attributesList);
            }
            attributesList = new ArrayList<MorphemicAttribute>(new LinkedHashSet<MorphemicAttribute>(attributesList));
            return attributesList.toArray(new MorphemicAttribute[attributesList.size()]);
        }

        static Locale locale = new Locale("tr");
        private void inferMorphemicAttributes(String word, PosInfo posData, List<MorphemicAttribute> attributesList) {
            TurkicLetterSequence sequence = new TurkicLetterSequence(word.toLowerCase(locale), alphabet);
            switch (posData.primaryPos) {
                case Verb:
                    // if a verb ends with a wovel, and -Iyor suffix is appended, last vowel drops.
                    if (sequence.lastLetter().isVowel())
                        attributesList.add(MorphemicAttribute.ProgressiveVowelDrop);
                    // if verb has more than 1 syllable and there is no Aorist_A label, add Aorist_I.
                    if (sequence.vowelCount() > 1 && !attributesList.contains(MorphemicAttribute.Aorist_A))
                        attributesList.add(MorphemicAttribute.Aorist_I);
                    // if verb has 1 syllable and there is no Aorist_I label, add Aorist_A
                    if (sequence.vowelCount() == 1 && !attributesList.contains(MorphemicAttribute.Aorist_I)) {
                        attributesList.add(MorphemicAttribute.Aorist_A);
                    }
                    break;
                case Noun:
                case Adjective:
                    // if a noun or adjective has more than one syllable and last letter is a stop consonant, add voicing.
                    if (sequence.vowelCount() > 1
                            && sequence.lastLetter().isStopConsonant()
                            && !attributesList.contains(MorphemicAttribute.NoVoicing)
                            && !attributesList.contains(MorphemicAttribute.InverseHarmony))
                        attributesList.add(MorphemicAttribute.Voicing);
                    if (word.endsWith("nk"))
                        attributesList.add(MorphemicAttribute.Voicing);
                    if (word.endsWith("og"))
                        attributesList.add(MorphemicAttribute.Voicing);

                    break;
            }
        }

        public List<LexiconItem> getResult() {
            return lexiconItems;
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

        @Override
        public String toString() {
            return primaryPos.shortForm + "-" + secondaryPos.shortForm;
        }
    }

    public static void main(String[] args) throws IOException {
        TurkishLexiconGenerator.convert(new File("test/data/dev-lexicon.txt"), new File("auto-generated-lexicon.txt"));
    }

}

