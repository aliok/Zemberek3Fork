package zemberek3.lexicon.graph;

import zemberek3.lexicon.*;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.HashMap;
import java.util.Map;

import static zemberek3.lexicon.RootAttr.*;
import static zemberek3.lexicon.TurkishSuffixes.*;
import static zemberek3.lexicon.TurkishSuffixes.Adj_Exp_C;
import static zemberek3.lexicon.TurkishSuffixes.PersPron_Main;


/**
 * This class generates StemNode objects from Dictionary Items. Generated Nodes are not connected.
 */
public class StemNodeGenerator {

    TurkishAlphabet alphabet = new TurkishAlphabet();

    AttributeSet<RootAttr> modifiers = new AttributeSet<RootAttr>(
            Doubling,
            LastVowelDrop,
            ProgressiveVowelDrop,
            InverseHarmony,
            Voicing,
            VoicingOpt,
            StemChange,
            CompoundP3sg
    );

    /**
     * Generates StemNode objects from the dictionary item.
     * <p>Most of the time a single StemNode is generated.
     *
     * @param item DictionaryItem
     * @return one or more StemNode objects.
     */
    public StemNode[] generate(DictionaryItem item) {
        if (hasModifierAttribute(item)) {
            return generateModifiedRootNodes(item);
        } else {
            return new StemNode[]{new StemNode(item.root, item, TerminationType.TERMINAL)};
        }
    }


    public boolean hasModifierAttribute(DictionaryItem item) {
        return item.attrs.containsAny(modifiers);
    }

    private AttributeSet<PhonAttr> calculateAttributes(String input) {
        return calculateAttributes(new TurkicSeq(input, alphabet));
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
        if (sequence.lastLetter().isVoiceless()) {
            attrs.add(PhonAttr.LastLetterVoiceless);
            if (sequence.lastLetter().isStopConsonant()) {
                // kitap
                attrs.add(PhonAttr.LastLetterVoicelessStop);
            }
        } else
            attrs.add(PhonAttr.LastLetterNotVoiceless);
        return attrs;
    }

    private StemNode[] generateModifiedRootNodes(DictionaryItem dicItem) {

        if (dicItem.hasAttribute(CompoundP3sg))
            return handleP3sgCompounds(dicItem);

        TurkicSeq modifiedSeq = new TurkicSeq(dicItem.root, alphabet);
        AttributeSet<PhonAttr> originalAttrs = calculateAttributes(dicItem.root);
        AttributeSet<PhonAttr> modifiedAttrs = originalAttrs.copy();
        AttributeSet<PhoneticExpectation> originalExpectations = new AttributeSet<PhoneticExpectation>();
        AttributeSet<PhoneticExpectation> modifiedExpectations = new AttributeSet<PhoneticExpectation>();
        ExclusiveSuffixData originalSuffixData = new ExclusiveSuffixData();
        ExclusiveSuffixData modifiedSuffixData = new ExclusiveSuffixData();

        for (RootAttr attribute : dicItem.attrs.getAsList(RootAttr.class)) {

            // generate other boundary attributes and modified root state.
            switch (attribute) {
                case Voicing:
                case VoicingOpt:
                    TurkicLetter last = modifiedSeq.lastLetter();
                    TurkicLetter modifiedLetter = alphabet.voice(last);
                    if (modifiedLetter == null) {
                        throw new LexiconException("Voicing letter is not proper in:" + dicItem);
                    }
                    if (dicItem.lemma.endsWith("nk"))
                        modifiedLetter = TurkishAlphabet.L_g;
                    modifiedSeq.changeLetter(modifiedSeq.length() - 1, modifiedLetter);
                    modifiedAttrs.remove(PhonAttr.LastLetterVoicelessStop);
                    originalExpectations.add(PhoneticExpectation.ConsonantStart);
                    modifiedExpectations.add(PhoneticExpectation.VowelStart);
                    break;
                case Doubling:
                    modifiedSeq.append(modifiedSeq.lastLetter());
                    originalExpectations.add(PhoneticExpectation.ConsonantStart);
                    modifiedExpectations.add(PhoneticExpectation.VowelStart);
                    break;
                case LastVowelDrop:
                    modifiedSeq.delete(modifiedSeq.length() - 2);
                    originalExpectations.add(PhoneticExpectation.ConsonantStart);
                    modifiedExpectations.add(PhoneticExpectation.VowelStart);
                    break;
                case InverseHarmony:
                    originalAttrs.add(PhonAttr.LastVowelFrontal);
                    originalAttrs.remove(PhonAttr.LastVowelBack);
                    modifiedAttrs.add(PhonAttr.LastVowelFrontal);
                    modifiedAttrs.remove(PhonAttr.LastVowelBack);
                    break;
                case ProgressiveVowelDrop:
                    modifiedSeq.delete(modifiedSeq.length() - 1);
                    if (modifiedSeq.hasVowel()) {
                        modifiedAttrs = calculateAttributes(modifiedSeq);
                    }
                    break;
                default:
                    break;
            }
        }

        return new StemNode[]{
                new StemNode(dicItem.root, dicItem, TerminationType.TERMINAL, originalExpectations),
                new StemNode(modifiedSeq.toString(), dicItem, TerminationType.NON_TERMINAL, modifiedExpectations)
        };
    }


    private StemNode[] handleP3sgCompounds(DictionaryItem item) {
        StemNode[] nodes = new StemNode[2];
        nodes[0] = new StemNode(item.lemma, item, TerminationType.TERMINAL);
        TurkicSeq modifiedSeq = new TurkicSeq(item.root, alphabet);
        nodes[1] = new StemNode(modifiedSeq.toString(), item, TerminationType.NON_TERMINAL);
        return nodes;
    }


    static class RootSuffixSetBuilder {
        ExclusiveSuffixData original;
        ExclusiveSuffixData modified;

        RootSuffixSetBuilder(DictionaryItem item) {

            switch (item.primaryPos) {
                case Noun:
                    getForNoun(item);
                    break;
                case Verb:
                    getForVerb(item);
                    break;
            }
        }


        private void getForVerb(DictionaryItem item) {
            for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case Aorist_A:
                        original.accepts.add(Aor_Ar, AorPart_Ar);
                        original.rejects.add(Aor_Ir, AorPart_Ir);
                        modified.accepts.add(Aor_Ar, AorPart_Ar);
                        modified.rejects.add(Aor_Ir, AorPart_Ir);
                        break;
                    case Aorist_I:
                        original.accepts.add(Aor_Ir, AorPart_Ir);
                        original.rejects.add(Aor_Ar, AorPart_Ar);
                        modified.accepts.add(Aor_Ir, AorPart_Ir);
                        modified.rejects.add(Aor_Ar, AorPart_Ar);
                        break;
                    case Passive_In:
                        original.accepts.add(Pass_In);
                        original.rejects.add(Pass_nIl);
                        break;
                    case LastVowelDrop:
                        original.rejects.add(Pass_nIl);
                        modified.onlyAccepts.add(Pass_nIl);
                        break;
/*                    case VoicingOpt:
                        modified.remove(Verb_Exp_C.getSuccessors());
                        break;*/
                    case ProgressiveVowelDrop:
                        original.rejects.add(Prog_Iyor);
                        modified.onlyAccepts.add(Prog_Iyor);
                        break;
                    case NonTransitive:
                        original.rejects.add(Caus_t, Caus_tIr);
                        modified.rejects.add(Caus_t, Caus_tIr);
                        break;
                    case Reflexive:
                        original.accepts.add(Reflex_In);
                        modified.accepts.add(Reflex_In);
                        break;
                    case Reciprocal:
                        original.accepts.add(Recip_Is);
                        modified.accepts.add(Recip_Is);
                        break;
                    case Causative_t:
                        original.rejects.add(Caus_tIr);
                        original.accepts.add(Caus_t);
                        modified.rejects.add(Caus_tIr);
                        modified.accepts.add(Caus_t);
                        break;
                    default:
                        break;
                }
            }
        }

        void getForNoun(DictionaryItem item) {
            for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case CompoundP3sg:
                        original.accepts.add(TurkishSuffixes.Noun_Comp_P3sg.getSuccSetCopy());
                        modified.onlyAccepts.add(TurkishSuffixes.Noun_Comp_P3sg_Root.getSuccSetCopy());
                        break;
                    default:
                        break;
                }
            }
        }

    }


}
