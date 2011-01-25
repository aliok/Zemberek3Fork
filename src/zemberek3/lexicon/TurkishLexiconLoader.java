package zemberek3.lexicon;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurkishLexiconLoader {

    public static List<LexiconItem> load(File input) throws IOException {
        return Files.readLines(input, Charsets.UTF_8, new LexiconFileProcessor());
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
            String cleanWord = cleanWord(word, posInfo);

            Set<MorphemicAttribute> morphemicAttributes = morphemicAttributes(cleanWord, posInfo, line);

            lexiconItems.add(new LexiconItem(
                    word,
                    cleanWord,
                    posInfo.primaryPos,
                    posInfo.secondaryPos,
                    morphemicAttributes.toArray(new MorphemicAttribute[morphemicAttributes.size()])));
            return true;
        }

        static Pattern wordPattern = Pattern.compile("(?:^)(.+?)(?:$|\\[)");

        private String getWord(String line) {
            String word = getGroup1Match(line, wordPattern).trim();
            if (word.length() == 0)
                throw new IllegalArgumentException("Line does not contain word :" + line);
            return word;
        }

        private String cleanWord(String word, PosInfo posInfo) {
            if (posInfo.primaryPos == PrimaryPos.Verb)
                return word.substring(0, word.length() - 3);
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

        private Set<MorphemicAttribute> morphemicAttributes(String word, PosInfo posData, String line) {
            LinkedHashSet<MorphemicAttribute> attributesList = new LinkedHashSet<MorphemicAttribute>(2);
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
            // remove unnecessary items.
            attributesList.remove(MorphemicAttribute.NoVoicing);
            return attributesList;
        }

        static Locale locale = new Locale("tr");

        private void inferMorphemicAttributes(String word, PosInfo posData, Set<MorphemicAttribute> attributesList) {
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
            // general phonetic attributes.
            if (sequence.lastVowel().isRoundedVowel())
                attributesList.add(MorphemicAttribute.LastVowelRounded);
            if (sequence.lastVowel().isFrontalVowel()) {
                attributesList.add(MorphemicAttribute.LastVowelFrontal);
            } else if (attributesList.contains(MorphemicAttribute.InverseHarmony)) {
                // saat, takat
                attributesList.add(MorphemicAttribute.LastVowelFrontal);
                // we no longer need InverseHarmony tag.
                attributesList.remove(MorphemicAttribute.InverseHarmony);
            }
            if (sequence.lastLetter().isVowel()) {
                // elma
                attributesList.add(MorphemicAttribute.LastLetterVowel);
            }
            if (sequence.lastLetter().isVoiceless() && sequence.lastLetter().isStopConsonant()) {
                // kitap
                attributesList.add(MorphemicAttribute.LastLetterVoicelessStop);
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
        List<LexiconItem> items = TurkishLexiconLoader.load(new File("test/data/dev-lexicon.txt"));
        for (LexiconItem item : items) {
            System.out.println(item);
        }
    }

}
