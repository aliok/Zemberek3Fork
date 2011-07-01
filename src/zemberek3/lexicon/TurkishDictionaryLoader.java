package zemberek3.lexicon;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static zemberek3.structure.TurkishAlphabet.*;

public class TurkishDictionaryLoader {

    SuffixProvider suffixProvider;

    public TurkishDictionaryLoader(SuffixProvider suffixProvider) {
        this.suffixProvider = suffixProvider;
    }

    public TurkishDictionaryLoader() {
        this.suffixProvider = new TurkishSuffixes().getSuffixProvider();
    }

    public List<DictionaryItem> load(File input) throws IOException {
        return Files.readLines(input, Charsets.UTF_8, new LexiconFileProcessor(suffixProvider));
    }

    public DictionaryItem loadFromString(String lexiconItemString) {
        return new LexiconItemMaker(lexiconItemString, suffixProvider).getItem();
    }

    static class LexiconItemMaker {
        final String line;
        SuffixProvider suffixProvider;

        static final TurkishAlphabet alphabet = new TurkishAlphabet();

        LexiconItemMaker(String line, SuffixProvider suffixProvider) {
            this.line = line;
            this.suffixProvider = suffixProvider;
        }

        DictionaryItem getItem() {
            String word = getWord();
            PosInfo posInfo = getPosData(line);
            String cleanWord = generateRoot(word, posInfo);

            AttributeSet<RootAttr> rootAttrs = morphemicAttributes(cleanWord, posInfo);
            ExclusiveSuffixData suffixData = getSuffixData();
            return new DictionaryItem(
                    word,
                    cleanWord,
                    posInfo.primaryPos,
                    posInfo.secondaryPos,
                    rootAttrs,
                    suffixData);
        }

        static Pattern wordPattern = Pattern.compile("(?:^)(.+?)(?:$|\\[)");

        String getWord() {
            String word = getGroup1Match(wordPattern).trim();
            if (word.length() == 0)
                throw new IllegalArgumentException("Line does not contain word :" + line);
            return word;
        }

        static Pattern rootPattern = Pattern.compile("(?:R:)(.+?)(?:;|\\])");

        String generateRoot(String word, PosInfo posInfo) {
            // if root is explicitly set, use it.
            String rootStr = getGroup1Match(rootPattern).trim();
            if (rootStr.length() > 0) {
                word = rootStr;
            }
            if (posInfo.primaryPos == PrimaryPos.Verb)
                word = word.substring(0, word.length() - 3);
            word = word.toLowerCase(locale).replaceAll("â", "a").replaceAll("î", "i").replaceAll("\u00e2", "u");
            return word.replaceAll("[\\-']", "");
        }

        static Pattern posPattern = Pattern.compile("(?:P:)(.+?)(?:;|\\])");

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
                            throw new RuntimeException("Multiple primary pos in :" + line);
                        else primaryPos = PrimaryPos.converter().getEnum(s);
                    } else if (SecondaryPos.converter().enumExists(s)) {
                        if (secondaryPos != null)
                            throw new RuntimeException("Multiple secondary pos in :" + line);
                        else secondaryPos = SecondaryPos.converter().getEnum(s);
                    } else
                        throw new RuntimeException("Unrecognized pos data [" + s + "] in :" + line);
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
            if (Character.isUpperCase(word.charAt(0)))
                return PrimaryPos.Noun;
            else if (word.endsWith("mek") || word.endsWith("mak")) {
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
                        throw new RuntimeException("Unrecognized attribute data [" + s + "] in :" + line);
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
            final TurkicLetter last = sequence.lastLetter();
            switch (posData.primaryPos) {
                case Verb:
                    // if a verb ends with a wovel, and -Iyor suffix is appended, last vowel drops.
                    if (last.isVowel()) {
                        attributesList.add(RootAttr.ProgressiveVowelDrop);
                        attributesList.add(RootAttr.Passive_In);
                    }
                    // if verb has more than 1 syllable and there is no Aorist_A label, add Aorist_I.
                    if (sequence.vowelCount() > 1 && !attributesList.contains(RootAttr.Aorist_A))
                        attributesList.add(RootAttr.Aorist_I);
                    // if verb has 1 syllable and there is no Aorist_I label, add Aorist_A
                    if (sequence.vowelCount() == 1 && !attributesList.contains(RootAttr.Aorist_I)) {
                        attributesList.add(RootAttr.Aorist_A);
                    }
                    if (last == L_l) {
                        attributesList.add(RootAttr.Passive_In);
                    }
                    if (last.isVowel() || (last == L_l || last == L_r) && sequence.vowelCount() > 1)
                        attributesList.add(RootAttr.Causative_t);
                    break;
                case Noun:
                case Adjective:
                    if (attributesList.contains(RootAttr.VoicingOpt)) {
                        attributesList.remove(RootAttr.Voicing);
                        attributesList.remove(RootAttr.NoVoicing);
                        break;
                    }
                    // if a noun or adjective has more than one syllable and last letter is a stop consonant, add voicing.
                    if (sequence.vowelCount() > 1
                            && last.isStopConsonant()
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

        static Pattern suffixPattern = Pattern.compile("(?:S:)(.+?)(?:;|\\])");

        private ExclusiveSuffixData getSuffixData() {
            String attributeStr = getGroup1Match(suffixPattern).trim();
            ExclusiveSuffixData esd = new ExclusiveSuffixData();
            if (attributeStr.length() == 0)
                return null;
            for (String token : Splitter.on(',').omitEmptyStrings().trimResults().split(attributeStr)) {
                if (token.length() < 2)
                    throw new LexiconException("Unexepected Suffix token in line: " + line);
                String suffixOrFormId = token.substring(1);
                List<SuffixFormSet> sets = new ArrayList<SuffixFormSet>();
                SuffixFormSet set = suffixProvider.getFormById(suffixOrFormId);
                if (set == null) {
                    List<SuffixFormSet> ss = suffixProvider.getFormsBySuffixId(suffixOrFormId);
                    if (ss == null)
                        throw new LexiconException("Cannot identify Suffix or SuffixFormSet Id:" + suffixOrFormId + " in line:" + line);
                    sets.addAll(ss);
                } else sets.add(set);
                switch (token.charAt(0)) {
                    case '+':
                        esd.accepts.addAll(sets);
                        break;
                    case '-':
                        esd.rejects.addAll(sets);
                        break;
                    case '*':
                        esd.onlyAccepts.addAll(sets);
                        break;
                }
            }
            return esd;
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
        SuffixProvider suffixProvider;

        LexiconFileProcessor(SuffixProvider suffixProvider) {
            this.suffixProvider = suffixProvider;
        }

        public boolean processLine(String line) throws IOException {
            line = line.trim();
            if (line.length() == 0 || line.startsWith("#"))
                return true;

            dictionaryItems.add(new LexiconItemMaker(line, suffixProvider).getItem());
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
        List<DictionaryItem> items = new TurkishDictionaryLoader(new TurkishSuffixes().getSuffixProvider()).load(new File("test/data/dev-dictionary.txt"));
        for (DictionaryItem item : items) {
            System.out.println(item);
        }
    }
}

