package zemberek3.lexicon;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurkishDictionaryLoader {

    public List<DictionaryItem> load(File input) throws IOException {
        return Files.readLines(input, Charsets.UTF_8, new LexiconFileProcessor());
    }

    DictionaryItem loadFromString(String lexiconItemString) {
        return new LexiconItemMaker(lexiconItemString).getItem();
    }

    static class LexiconItemMaker {
        final String line;

        static final TurkishAlphabet alphabet = new TurkishAlphabet();

        LexiconItemMaker(String line) {
            this.line = line;
        }

        DictionaryItem getItem() {
            String word = getWord();
            PosInfo posInfo = getPosData(word);
            String cleanWord = cleanWord(word, posInfo);

            AttributeSet<RootAttr> rootAttrs = morphemicAttributes(cleanWord, posInfo);
            return new DictionaryItem(
                    word,
                    posInfo.primaryPos,
                    posInfo.secondaryPos,
                    rootAttrs);
        }

        static Pattern wordPattern = Pattern.compile("(?:^)(.+?)(?:$|\\[)");

        String getWord() {
            String word = getGroup1Match(wordPattern).trim();
            if (word.length() == 0)
                throw new IllegalArgumentException("Line does not contain word :" + line);
            return word;
        }

        String cleanWord(String word, PosInfo posInfo) {
            if (posInfo.primaryPos == PrimaryPos.Verb)
                return word.substring(0, word.length() - 3);
            if (posInfo.secondaryPos == SecondaryPos.ProperNoun) {
                return word.toLowerCase(locale);
            }
            return word;
        }

        static Pattern posPattern = Pattern.compile("(?:Pos:)(.+?)(?:;|\\])");

        PosInfo getPosData(String word) {
            String posString = getGroup1Match(posPattern).trim();
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

        private AttributeSet<RootAttr> morphemicAttributes(String word, PosInfo posData) {
            LinkedHashSet<RootAttr> attributesList = new LinkedHashSet<RootAttr>(2);
            String attributeStr = getGroup1Match(attributePattern).trim();
            if (attributeStr.length() == 0) {
                inferMorphemicAttributes(word, posData, attributesList);
            } else {
                for (String s : Splitter.on(",").split(attributeStr)) {
                    s = s.trim();
                    if (!RootAttr.converter().enumExists(s))
                        throw new LexiconGenerationException("Unrecognized attribute data [" + s + "] in :" + line);
                    RootAttr rootAttr = RootAttr.converter().getEnum(s);
                    attributesList.add(rootAttr);
                }
                inferMorphemicAttributes(word, posData, attributesList);
            }
            return new AttributeSet<RootAttr>(attributesList);
        }

        static Locale locale = new Locale("tr");

        private void inferMorphemicAttributes(
                String word,
                PosInfo posData,
                Set<RootAttr> attributesList) {
            TurkicSeq sequence = new TurkicSeq(word.toLowerCase(locale), alphabet);
            switch (posData.primaryPos) {
                case Verb:
                    // if a verb ends with a wovel, and -Iyor suffix is appended, last vowel drops.
                    if (sequence.lastLetter().isVowel())
                        attributesList.add(RootAttr.ProgressiveVowelDrop);
                    // if verb has more than 1 syllable and there is no Aorist_A label, add Aorist_I.
                    if (sequence.vowelCount() > 1 && !attributesList.contains(RootAttr.Aorist_A))
                        attributesList.add(RootAttr.Aorist_I);
                    // if verb has 1 syllable and there is no Aorist_I label, add Aorist_A
                    if (sequence.vowelCount() == 1 && !attributesList.contains(RootAttr.Aorist_I)) {
                        attributesList.add(RootAttr.Aorist_A);
                    }
                    break;
                case Noun:
                case Adjective:
                    // if a noun or adjective has more than one syllable and last letter is a stop consonant, add voicing.
                    if (sequence.vowelCount() > 1
                            && sequence.lastLetter().isStopConsonant()
                            && !attributesList.contains(RootAttr.NoVoicing)
                            && !attributesList.contains(RootAttr.InverseHarmony))
                        attributesList.add(RootAttr.Voicing);
                    else if (word.endsWith("nk") || word.endsWith("og"))
                        attributesList.add(RootAttr.Voicing);
                    else if (!attributesList.contains(RootAttr.Voicing))
                        attributesList.add(RootAttr.NoVoicing);
                    break;
            }
        }

        private String getGroup1Match(Pattern pattern) {
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                return m.group(1);
            } else return "";
        }

    }

    static class LexiconFileProcessor implements LineProcessor<List<DictionaryItem>> {

        List<DictionaryItem> dictionaryItems = new ArrayList<DictionaryItem>();

        public boolean processLine(String line) throws IOException {
            line = line.trim();
            if (line.length() == 0 || line.startsWith("#"))
                return true;

            dictionaryItems.add(new LexiconItemMaker(line).getItem());
            return true;
        }

        public List<DictionaryItem> getResult() {
            return dictionaryItems;
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
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-dictionary.txt"));
        for (DictionaryItem item : items) {
            System.out.println(item);
        }
    }
}
