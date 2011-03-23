package zemberek3.lexicon;

import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static zemberek3.lexicon.RootAttr.*;
import static zemberek3.lexicon.RootAttr.Voicing;
import static zemberek3.lexicon.PrimaryPos.*;
import static zemberek3.lexicon.TurkishSuffixes.*;

/**
 * This class gets a list of Lexicon Items and builds the lexicon part of the main graph.
 */
public class LexiconGraphGenerator {
    List<LexiconItem> lexicon;
    List<Stem> stems = new ArrayList<Stem>();
    TurkishAlphabet alphabet = new TurkishAlphabet();

    SuffixFormGenerator formGenerator = new SuffixFormGenerator();
    TurkishSuffixes suffixes = new TurkishSuffixes();

    public LexiconGraphGenerator(List<LexiconItem> lexicon) {
        this.lexicon = lexicon;
    }

    public void generate() {
        for (LexiconItem lexiconItem : lexicon) {
            if (hasModifierAttribute(lexiconItem)) {
                stems.addAll(Arrays.asList(generateModifiedRootStates(lexiconItem)));
            } else {
                stems.add(generateRootState(lexiconItem));
            }
        }
    }

    public List<Stem> getStems() {
        return stems;
    }

    private Stem generateRootState(LexiconItem lexiconItem) {
        SuffixFormSet set = suffixes.getRootSuffixFormSet(lexiconItem.primaryPos);
        AttributeSet<PhonAttr> phoneticAttrs = calculateRootAttributes(lexiconItem);
        SuffixForm form = formGenerator.getForm(phoneticAttrs, set.generation);
        form = set.addOrReturnExisting(form);
        return new Stem(lexiconItem.lemma, lexiconItem, form, true);
    }

    public boolean hasModifierAttribute(LexiconItem item) {
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

    private AttributeSet<PhonAttr> calculateRootAttributes(LexiconItem lexiconItem) {
        AttributeSet<PhonAttr> attrs = new AttributeSet<PhonAttr>();
        TurkicSeq sequence = new TurkicSeq(lexiconItem.clean(), alphabet);
        // general phonetic attributes.
        if (sequence.lastVowel().isRounded())
            attrs.add(PhonAttr.LastVowelRounded);
        else
            attrs.add(PhonAttr.LastVowelUnrounded);
        if (sequence.lastVowel().isFrontal()) {
            attrs.add(PhonAttr.LastVowelFrontal);
        } else if (lexiconItem.hasAttribute(RootAttr.InverseHarmony)) {
            // saat, takat
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


    private Stem[] generateModifiedRootStates(LexiconItem lexItem) {

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
                if (lexItem.attrs.contains(LastVowelDrop)) {
                    origSet = TurkishSuffixes.Verb_Vow_NotDrop;
                    modSet = TurkishSuffixes.Verb_Vow_Drop;
                } else if (lexItem.attrs.contains(ProgressiveVowelDrop)) {
                    origSet = TurkishSuffixes.Verb_Prog_NotDrop;
                    modSet = TurkishSuffixes.Verb_Prog_Drop;
                }
        }
        if (origSet == null || modSet == null) {
            throw new IllegalStateException("Cannot find suffix root form to connect for lexicon item:" + lexItem);
        }

        SuffixForm origForm = origSet.addOrReturnExisting(formGenerator.getForm(originalAttrs, origSet.generation));
        SuffixForm modiForm = modSet.addOrReturnExisting(formGenerator.getForm(modifiedAttrs, modSet.generation));

        return new Stem[]{
                new Stem(lexItem.clean(), lexItem, origForm, true),
                new Stem(modifiedSeq.toString(), lexItem, modiForm, false)
        };

    }

    // handle stem changes demek-diyecek , beni-bana
    private Stem[] handleSpecialStems(LexiconItem lexiconItem) {
        return new Stem[0];
/*        if ((lexiconItem.lemma.equals("yemek") || lexiconItem.lemma.equals("demek")) && lexiconItem.primaryPos == Verb) {
            Stem[] nodes = new Stem[3];
            BoundaryNode originalNode =
                    new BoundaryNode(Verb, PhonAttr.LastVowelFrontal, PhonAttr.LastLetterVowel)
                            .addRestrictedsuffixes(Prog, Fut, Opt);
            nodes[0] = new Stem(lexiconItem.lemma, lexiconItem, originalNode, true);

            BoundaryNode progressiveNode = new BoundaryNode(Verb, PhonAttr.LastVowelFrontal).addExclusiveSuffix(Prog);
            nodes[1] = new Stem(lexiconItem.lemma.substring(0, 1), lexiconItem, progressiveNode, false);

            BoundaryNode modifiedNode = new BoundaryNode(
                    originalNode.getPrimaryPos(),
                    originalNode.getAttributes(),
                    originalNode.getExpectations())
                    .addExclusiveSuffix(Fut, Opt);
            // modification rule does not apply for some suffixes for "demek". like deyip, not deyip
            if (lexiconItem.lemma.equals("ye")) {
                modifiedNode.addExclusiveSuffix(AfterDoing);
            }
            nodes[2] = new Stem(lexiconItem.lemma.substring(0, 1) + "i", lexiconItem, modifiedNode, false);
            return nodes;
        } else if ((lexiconItem.lemma.equals("ben") || lexiconItem.lemma.equals("sen")) && lexiconItem.primaryPos == Pronoun) {
            Stem[] nodes = new Stem[2];

            BoundaryNode originalNode = new BoundaryNode(Pronoun, PhonAttr.LastVowelFrontal).addRestrictedsuffixes(Dat);
            nodes[0] = new Stem(lexiconItem.lemma, lexiconItem, originalNode, true);

            BoundaryNode modifiedNode = new BoundaryNode(
                    originalNode.getPrimaryPos(),
                    originalNode.getAttributes(),
                    originalNode.getExpectations())
                    .addExclusiveSuffix(Dat);
            if (lexiconItem.lemma.equals("ben"))
                nodes[1] = new Stem("ban", lexiconItem, modifiedNode, false);
            else
                nodes[1] = new Stem("san", lexiconItem, modifiedNode, false);
            return nodes;
        }
        throw new IllegalArgumentException("Lexicon Item with special stem change cannot be handled:" + lexiconItem);
        */
    }

    private Stem[] handleP3sgCompounds(LexiconItem lexiconItem) {
        return new Stem[0];
        /*
        Stem[] nodes = new Stem[2];
        // atkuyruGu -
        BoundaryNode originalState = new BoundaryNode(lexiconItem.primaryPos, calculateRootAttributes(lexiconItem))
                .addExclusiveSuffix(TurkishSuffixes.CASE)
                .addExclusiveSuffix(TurkishSuffixes.COPULAR)
                .addExclusiveSuffix(TurkishSuffixes.POSSESSIVE);
        nodes[0] = new Stem(lexiconItem.lemma, lexiconItem, originalState, true);

        TurkicSeq modifiedSeq = new TurkicSeq(lexiconItem.lemma, alphabet);
        // TODO: phonetic properties needs to be found again nicely.
        BoundaryNode modifiedNode = new BoundaryNode(lexiconItem.primaryPos, calculateRootAttributes(lexiconItem)).
                addExclusiveSuffix(Pl, With, P3pl);
        modifiedNode.getAttributes().remove(PhonAttr.LastLetterVowel);

        String modified = modifiedSeq.eraseLast().toString();
        // for cases compund's last word has voicing. atkuruGu
        if (lexiconItem.hasAttribute(Voicing)) {
            // hack for cases denizbiyoloGu - denizbiyologlari
            if (modified.endsWith("o" + String.valueOf(TurkishAlphabet.C_gg)))
                modifiedSeq.eraseLast().append(TurkishAlphabet.L_g);
            else {
                TurkicLetter l = alphabet.devoice(modifiedSeq.lastLetter());
                modifiedSeq.changeLetter(modifiedSeq.length() - 1, l);
            }
            nodes[1] = new Stem(modifiedSeq.toString(), lexiconItem, modifiedNode, false);
        } else {
            nodes[1] = new Stem(modified, lexiconItem, modifiedNode, false);
        }
        return nodes;
        */
    }


    public static void main(String[] args) throws IOException {
        List<LexiconItem> items = new TurkishLexiconLoader().load(new File("test/data/dev-lexicon.txt"));
        LexiconGraphGenerator generator = new LexiconGraphGenerator(items);
        generator.generate();
        List<Stem> stems = generator.getStems();
        for (Stem stem : stems) {
            System.out.println(stem);
        }
    }


}
