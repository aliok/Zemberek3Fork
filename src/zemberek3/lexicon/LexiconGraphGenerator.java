package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static zemberek3.lexicon.RootAttr.*;
import static zemberek3.lexicon.TurkishSuffixes.*;

/**
 * This class gets a list of Lexicon Items and builds the dictionary part of the main graph.
 */
public class LexiconGraphGenerator {
    List<DictionaryItem> dictionary;
    List<Stem> stems = new ArrayList<Stem>();
    TurkishAlphabet alphabet = new TurkishAlphabet();
    TurkishSuffixes suffixes;
    SuffixFormGenerator formGenerator = new SuffixFormGenerator();

    public LexiconGraphGenerator(List<DictionaryItem> dictionary, TurkishSuffixes suffixes) {
        this.dictionary = dictionary;
        this.suffixes = suffixes;
    }

    public void generate() {
        for (DictionaryItem dictionaryItem : dictionary) {
            if (hasModifierAttribute(dictionaryItem)) {
                stems.addAll(Arrays.asList(generateModifiedRootStates(dictionaryItem)));
            } else {
                stems.add(generateRootState(dictionaryItem));
            }
        }
    }

    public List<Stem> getStems() {
        return stems;
    }

    private Stem generateRootState(DictionaryItem dictionaryItem) {
        SuffixFormSet set = suffixes.getRootSuffixFormSet(dictionaryItem.primaryPos);
        AttributeSet<PhonAttr> phoneticAttrs = calculateRootAttributes(dictionaryItem);
        SuffixForm form = formGenerator.getForm(phoneticAttrs, set.generation);
        form = set.addOrReturnExisting(form);
        return new Stem(dictionaryItem.lemma, dictionaryItem, form, true);
    }

    public boolean hasModifierAttribute(DictionaryItem item) {
        return item.attrs.containsAny(modifiers);
    }

    AttributeSet<RootAttr> modifiers = new AttributeSet<RootAttr>(
            Doubling,
            LastVowelDrop,
            ProgressiveVowelDrop,
            Voicing,
            StemChange,
            CompoundP3sg
    );

    private AttributeSet<PhonAttr> calculateRootAttributes(DictionaryItem dictionaryItem) {
        TurkicSeq sequence = new TurkicSeq(dictionaryItem.clean(), alphabet);
        AttributeSet<PhonAttr> attrs = calculateAttributes(sequence);
        if (dictionaryItem.hasAttribute(RootAttr.InverseHarmony)) {
            // saat, takat
            attrs.add(PhonAttr.LastVowelFrontal);
        }
        return attrs;
    }

    private AttributeSet<PhonAttr> calculateAttributes(TurkicSeq sequence) {
        AttributeSet<PhonAttr> attrs = new AttributeSet<PhonAttr>();
        // general phonetic attributes.
        if (sequence.lastVowel().isRounded())
            attrs.add(PhonAttr.LastVowelRounded);
        else
            attrs.add(PhonAttr.LastVowelUnrounded);
        if (sequence.lastVowel().isFrontal()) {
            attrs.add(PhonAttr.LastVowelFrontal);
        } else
            attrs.add(PhonAttr.LastVowelBack);
        if (sequence.lastLetter().isVowel()) {
            // elma
            attrs.add(PhonAttr.LastLetterVowel);
        } else
            attrs.add(PhonAttr.LastLetterConsonant);
        if (sequence.lastLetter().isVoiceless() && sequence.lastLetter().isStopConsonant()) {
            // kitap
            attrs.add(PhonAttr.LastLetterVoicelessStop);
        } else
            attrs.add(PhonAttr.LastLetterNotVoicelessStop);
        return attrs;
    }


    private Stem[] generateModifiedRootStates(DictionaryItem lexItem) {

        TurkicSeq modifiedSeq = new TurkicSeq(lexItem.clean(), alphabet);
        AttributeSet<PhonAttr> originalAttrs = calculateRootAttributes(lexItem);
        AttributeSet<PhonAttr> modifiedAttrs = originalAttrs.copy();

        for (RootAttr attribute : lexItem.attrs.getAsList(RootAttr.class)) {

            // generate other boundary attributes and modified root state.
            switch (attribute) {
                case Voicing:
                    if (lexItem.hasAttribute(RootAttr.CompoundP3sg))
                        break;
                    TurkicLetter last = modifiedSeq.lastLetter();
                    TurkicLetter modifiedLetter = alphabet.voice(last);
                    if (modifiedLetter == null) {
                        throw new LexiconGenerationException("Voicing letter is not proper in:" + lexItem);
                    }
                    if (lexItem.lemma.endsWith("nk"))
                        modifiedLetter = TurkishAlphabet.L_g;
                    modifiedSeq.changeLetter(modifiedSeq.length() - 1, modifiedLetter);
                    break;
                case Doubling:
                    modifiedSeq.append(modifiedSeq.lastLetter());
                    break;
                case LastVowelDrop:
                    modifiedSeq.delete(modifiedSeq.length() - 2);
                    break;
                case ProgressiveVowelDrop:
                    modifiedSeq.delete(modifiedSeq.length() - 1);
                    break;
                case StemChange:
                    return handleSpecialStems(lexItem);
                case CompoundP3sg:
                    return handleP3sgCompounds(lexItem);
                default:
                    break;
            }
        }
        SuffixFormSet origSet = null;
        SuffixFormSet modSet = null;
        switch (lexItem.primaryPos) {
            case Noun:
                if (lexItem.attrs.containsAny(Voicing, Doubling, LastVowelDrop)) {
                    origSet = TurkishSuffixes.Noun_Exp_C;
                    modSet = TurkishSuffixes.Noun_Exp_V;
                }
                if (lexItem.attrs.contains(Voicing)) {
                    modifiedAttrs.remove(PhonAttr.LastLetterVoicelessStop);
                }
            case Verb:
                if (lexItem.attrs.contains(Voicing)) {
                    origSet = TurkishSuffixes.Verb_Vow_NotDrop;
                    modSet = TurkishSuffixes.Verb_Vow_Drop;
                } else if (lexItem.attrs.contains(LastVowelDrop)) {
                    origSet = TurkishSuffixes.Verb_Vow_NotDrop;
                    modSet = TurkishSuffixes.Verb_Vow_Drop;
                } else if (lexItem.attrs.contains(ProgressiveVowelDrop)) {
                    origSet = TurkishSuffixes.Verb_Prog_NotDrop;
                    modSet = TurkishSuffixes.Verb_Prog_Drop;
                } else if (lexItem.attrs.contains(Aorist_A)) {
                    modSet = TurkishSuffixes.Verb_Aor_Ar;
                }

        }
        if (origSet == null || modSet == null) {
            throw new IllegalStateException("Cannot find suffix root form to connect for dictionary item:" + lexItem);
        }

        SuffixForm origForm = origSet.addOrReturnExisting(formGenerator.getForm(originalAttrs, origSet.generation));
        SuffixForm modiForm = modSet.addOrReturnExisting(formGenerator.getForm(modifiedAttrs, modSet.generation));

        return new Stem[]{
                new Stem(lexItem.clean(), lexItem, origForm, true),
                new Stem(modifiedSeq.toString(), lexItem, modiForm, false)
        };

    }

    // handle stem changes demek-diyecek , beni-bana
    private Stem[] handleSpecialStems(DictionaryItem item) {

        if (item.getId().equals("yemek_Verb")) {
            Stem[] stems = new Stem[3];
            SuffixForm formYe = Verb_Ye.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            stems[0] = new Stem(item.clean(), item, formYe, true);
            SuffixForm formProg = Verb_Prog_Drop.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            stems[1] = new Stem(item.lemma.substring(0, 1), item, formProg, false);
            SuffixForm formYi = Verb_Yi.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            stems[2] = new Stem("yi", item, formYi, false);
            return stems;
        }
        if (item.getId().equals("demek_Verb")) {
            Stem[] stems = new Stem[3];
            SuffixForm formDe = Verb_De.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            stems[0] = new Stem(item.clean(), item, formDe, true);
            SuffixForm formProg = Verb_Prog_Drop.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            stems[1] = new Stem(item.lemma.substring(0, 1), item, formProg, false);
            SuffixForm formDi = Verb_Di.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            stems[2] = new Stem("di", item, formDi, false);
            return stems;
        }
        if (item.getId().equals("ben_Pron") || item.getId().equals("sen_Pron")) {
            Stem[] stems = new Stem[2];
            SuffixForm formBenSen = Pron_BenSen.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            stems[0] = new Stem(item.clean(), item, formBenSen, true);
            SuffixForm formBanSan = Pron_BanSan.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
            if (item.lemma.equals("ben"))
                stems[1] = new Stem("ban", item, formBanSan, false);
            else
                stems[1] = new Stem("san", item, formBanSan, false);
            return stems;
        }
        throw new IllegalArgumentException("Lexicon Item with special stem change cannot be handled:" + item);
    }

    private Stem[] handleP3sgCompounds(DictionaryItem item) {

        Stem[] nodes = new Stem[2];
        // atkuyruGu -
        SuffixForm original = Noun_Comp_P3sg.addOrReturnExisting(formGenerator.getForm(calculateRootAttributes(item), ""));
        nodes[0] = new Stem(item.clean(), item, original, true);

        TurkicSeq modifiedSeq = new TurkicSeq(item.lemma, alphabet);
        String modified = modifiedSeq.eraseLast().toString();
        // for cases compund's last word has voicing. atkuruGu
        if (item.hasAttribute(Voicing)) {
            // hack for cases denizbiyoloGu - denizbiyologlari
            if (modified.endsWith("o" + String.valueOf(TurkishAlphabet.C_gg)))
                modifiedSeq.eraseLast().append(TurkishAlphabet.L_g);
            else {
                TurkicLetter l = alphabet.devoice(modifiedSeq.lastLetter());
                modifiedSeq.changeLetter(modifiedSeq.length() - 1, l);
            }
            SuffixForm mod = Noun_Comp_P3sg_Root.addOrReturnExisting(formGenerator.getForm(calculateAttributes(modifiedSeq), ""));
            nodes[1] = new Stem(modifiedSeq.toString(), item, mod, false);
        } else {
            SuffixForm mod = Noun_Comp_P3sg_Root.addOrReturnExisting(formGenerator.getForm(calculateAttributes(modifiedSeq), ""));
            nodes[1] = new Stem(modified, item, mod, false);
        }
        return nodes;
    }


    public void generateSuffixForms(Iterable<SuffixFormSet> startForms) {
        generateSuffixForms(startForms, new HashSet<SuffixFormSet>());
    }

    private void generateSuffixForms(Iterable<SuffixFormSet> formSets, Set<SuffixFormSet> finishedSet) {
        List<SuffixFormSet> toProcess = new ArrayList<SuffixFormSet>();
        for (SuffixFormSet rootFormSet : formSets) {
            for (SuffixFormSet succSet : rootFormSet.getSuccessors()) {
                if (finishedSet.contains(succSet))
                    continue;
                else
                    toProcess.add(succSet);
                for (SuffixForm form : rootFormSet.getFormIterator()) {
                    SuffixForm formInSuccessor = formGenerator.getForm(form.attributes, succSet.generation);
                    formInSuccessor = succSet.addOrReturnExisting(formInSuccessor);
                    form.addSuccForm(formInSuccessor);
                }
            }
            finishedSet.add(rootFormSet);
        }
        if (toProcess.size() == 0)
            return;
        generateSuffixForms(toProcess, finishedSet);
    }

    public static void main(String[] args) throws IOException {
        List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("test/data/dev-lexicon.txt"));
        TurkishSuffixes suffixes = new TurkishSuffixes();
        LexiconGraphGenerator generator = new LexiconGraphGenerator(items, suffixes);
        generator.generate();
        List<Stem> stems = generator.getStems();
        for (Stem stem : stems) {
            System.out.println(stem);
        }
        List<SuffixFormSet> sets = Arrays.asList(Noun_Main, Verb_Main);
        generator.generateSuffixForms(sets);
        SuffixFormSet set = TurkishSuffixes.Pl_lAr;
        System.out.println("Form Set:" + set.generation);
        for (SuffixForm form : set.getFormIterator()) {
            System.out.println("   Form: " + form.surface);
            for (SuffixForm sform : form.getSuccForms()) {
                System.out.println("        -" + sform.surface);
            }
        }
        System.out.println("----");
    }
}
