package zemberek3.lexicon.graph;

import com.google.common.collect.Maps;
import zemberek3.lexicon.*;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import javax.naming.event.NamingExceptionEvent;
import java.util.*;

import static zemberek3.lexicon.RootAttr.*;
import static zemberek3.lexicon.TurkishSuffixes.*;

/**
 * This class gets a list of Lexicon Items and builds the dictionary part of the main graph.
 */
public class LexiconGraph {
    List<DictionaryItem> dictionary;
    List<StemNode> stems = new ArrayList<StemNode>();
    TurkishAlphabet alphabet = new TurkishAlphabet();
    final SuffixProvider suffixes;
    SuffixFormGenerator formGenerator = new SuffixFormGenerator();

    private Map<SuffixFormSet, Set<SuffixNode>> suffixFormMap = Maps.newConcurrentMap();

    public LexiconGraph(List<DictionaryItem> dictionary, SuffixProvider suffixProvider) {
        this.suffixes = suffixProvider;
        this.dictionary = dictionary;
        for (SuffixFormSet set : suffixes.getAllForms()) {
            suffixFormMap.put(set.remove(), new HashSet<SuffixNode>());
        }
    }

    public void generate() {
        // generate stems.
        for (DictionaryItem dictionaryItem : dictionary) {
            if (hasModifierAttribute(dictionaryItem)) {
                stems.addAll(Arrays.asList(generateModifiedRootNodes(dictionaryItem)));
            } else {
                stems.add(generateRootNode(dictionaryItem));
            }
        }
        // generate suffix form graph
        generateSuffixForms(suffixFormMap.keySet());
    }

    public List<StemNode> getStems() {
        return stems;
    }

    private StemNode generateRootNode(DictionaryItem dictionaryItem) {
        RootSuffixSetBuilder rsb = new RootSuffixSetBuilder(dictionaryItem);
        SuffixFormSet set = rsb.original;
        AttributeSet<PhonAttr> phoneticAttrs = calculateAttributes(dictionaryItem.root);
        SuffixNode node = addOrReturnExisting(set, formGenerator.getEmptyNode(phoneticAttrs, AttributeSet.<PhoneticExpectation>emptySet(), set));
        return new StemNode(dictionaryItem.root, dictionaryItem, node, TerminationType.TERMINAL);
    }

    public boolean hasModifierAttribute(DictionaryItem item) {
        return item.attrs.containsAny(modifiers);
    }

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
        if (dicItem.hasAttribute(StemChange))
            return handleSpecialStems(dicItem);

        TurkicSeq modifiedSeq = new TurkicSeq(dicItem.root, alphabet);
        AttributeSet<PhonAttr> originalAttrs = calculateAttributes(dicItem.root);
        AttributeSet<PhonAttr> modifiedAttrs = originalAttrs.copy();
        AttributeSet<PhoneticExpectation> originalExpectations = new AttributeSet<PhoneticExpectation>();
        AttributeSet<PhoneticExpectation> modifiedExpectations = new AttributeSet<PhoneticExpectation>();

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
        RootSuffixSetBuilder rssb = new RootSuffixSetBuilder(dicItem);

        SuffixNode origForm = addOrReturnExisting(rssb.original, formGenerator.getEmptyNode(originalAttrs, originalExpectations, rssb.original));
        SuffixNode modiForm = addOrReturnExisting(rssb.modified, formGenerator.getEmptyNode(modifiedAttrs, modifiedExpectations, rssb.modified));

        return new StemNode[]{
                new StemNode(dicItem.root, dicItem, origForm, TerminationType.TERMINAL),
                new StemNode(modifiedSeq.toString(), dicItem, modiForm, TerminationType.NON_TERMINAL)
        };
    }

    public SuffixNode addOrReturnExisting(SuffixFormSet set, SuffixNode newNode) {

        if (!suffixFormMap.containsKey(set)) {
            suffixFormMap.put(set, new HashSet<SuffixNode>());
        }
        Set<SuffixNode> nodes = suffixFormMap.get(set);
        if (!nodes.contains(newNode)) {
            nodes.add(newNode);
            return newNode;
        }
        for (SuffixNode node : nodes) {
            if (node.equals(newNode))
                return node;
        }
        throw new IllegalStateException("Cannot be here.");
    }

    private boolean nodeExists(SuffixFormSet set, SuffixNode newNode) {
        Set<SuffixNode> nodes = suffixFormMap.get(set);
        return nodes.contains(newNode);
    }

    public SuffixNode getSuffixRootNode(AttributeSet<PhonAttr> attrs, AttributeSet<PhoneticExpectation> expectations, SuffixFormSet set) {
        return addOrReturnExisting(set, formGenerator.getEmptyNode(attrs, expectations, set));
    }

    public SuffixNode getSuffixRootNode(AttributeSet<PhonAttr> attrs, SuffixFormSet set) {
        return addOrReturnExisting(set, formGenerator.getEmptyNode(attrs, AttributeSet.<PhoneticExpectation>emptySet(), set));
    }

    // handle stem changes demek-diyecek , beni-bana
    private StemNode[] handleSpecialStems(DictionaryItem item) {

        if (item.getId().equals("yemek_Verb")) {
            SuffixFormSet Verb_Ye = new SuffixFormSet("Verb_Ye", VerbRoot, "");
            SuffixFormSet Verb_Yi = new SuffixFormSet("Verb_Yi", VerbRoot, "");
            Verb_Ye.add(Verb_Main.getSuccSetCopy()).remove(Abil_yA, Abil_yAbil, Prog_Iyor, Fut_yAcAk,
                    FutPart_yAcAk, Opt_yA, When_yIncA, AfterDoing_yIp, PresPart_yAn, KeepDoing_yAgor,
                    KeepDoing2_yAdur, FeelLike_yAsI, UnableToDo_yAmAdAn).add(Pass_In, Recip_Is, Inf3_yIs);
            Verb_Yi.add(Opt_yA, Fut_yAcAk, FutPart_yAcAk, When_yIncA, AfterDoing_yIp, Abil_yA,
                    Abil_yAbil, Recip_yIs, Inf3_yIs, FeelLike_yAsI, PresPart_yAn, KeepDoing_yAgor, KeepDoing2_yAdur,
                    FeelLike_yAsI, UnableToDo_yAmAdAn);
            StemNode[] stems = new StemNode[3];
            SuffixNode formYe = getSuffixRootNode(calculateAttributes(item.root), Verb_Ye);
            stems[0] = new StemNode(item.root, item, formYe, TerminationType.TERMINAL);
            AttributeSet<PhonAttr> attrs = calculateAttributes(item.root).remove(PhonAttr.LastLetterVowel).add(PhonAttr.LastLetterConsonant);
            SuffixNode formProg = getSuffixRootNode(attrs, Verb_Prog_Drop);
            stems[1] = new StemNode("y", item, formProg, TerminationType.NON_TERMINAL);
            SuffixNode formYi = getSuffixRootNode(calculateAttributes(item.root), Verb_Yi);
            stems[2] = new StemNode("yi", item, formYi, TerminationType.NON_TERMINAL);
            return stems;
        }
        if (item.getId().equals("demek_Verb")) {
            SuffixFormSet Verb_De = new SuffixFormSet("Verb_De", VerbRoot, "");
            SuffixFormSet Verb_Di = new SuffixFormSet("Verb_Di", VerbRoot, "");
            // modification rule does not apply for some suffixes for "demek". like deyip, not diyip
            Verb_De.add(Verb_Main.getSuccSetCopy())
                    .remove(Abil_yA, Abil_yAbil, Prog_Iyor, Fut_yAcAk, FutPart_yAcAk, Opt_yA,
                            PresPart_yAn, PresPart_yAn, KeepDoing_yAgor, KeepDoing2_yAdur, FeelLike_yAsI, UnableToDo_yAmAdAn)
                    .add(Pass_In);
            Verb_Di.add(Opt_yA, Fut_yAcAk, FutPart_yAcAk, Abil_yA, Abil_yAbil, PresPart_yAn,
                    PresPart_yAn, KeepDoing_yAgor, KeepDoing2_yAdur, FeelLike_yAsI, UnableToDo_yAmAdAn);
            StemNode[] stems = new StemNode[3];
            SuffixNode formDe = getSuffixRootNode(calculateAttributes(item.root), Verb_De);
            stems[0] = new StemNode(item.root, item, formDe, TerminationType.TERMINAL);
            AttributeSet<PhonAttr> attrs = calculateAttributes(item.root).remove(PhonAttr.LastLetterVowel).add(PhonAttr.LastLetterConsonant);
            SuffixNode formProg = getSuffixRootNode(attrs, Verb_Prog_Drop);
            stems[1] = new StemNode("d", item, formProg, TerminationType.NON_TERMINAL);
            SuffixNode formDi = getSuffixRootNode(calculateAttributes(item.root), Verb_Di);
            stems[2] = new StemNode("di", item, formDi, TerminationType.NON_TERMINAL);
            return stems;
        }
        if (item.getId().equals("ben_Pron") || item.getId().equals("sen_Pron")) {
            StemNode[] stems = new StemNode[2];
            SuffixNode formBenSen = getSuffixRootNode(calculateAttributes(item.root), PersPron_BenSen);
            stems[0] = new StemNode(item.root, item, formBenSen, TerminationType.TERMINAL);
            SuffixNode formBanSan = getSuffixRootNode(calculateAttributes(item.root), PersPron_BanSan);
            if (item.lemma.equals("ben"))
                stems[1] = new StemNode("ban", item, formBanSan, TerminationType.NON_TERMINAL);
            else
                stems[1] = new StemNode("san", item, formBanSan, TerminationType.NON_TERMINAL);
            return stems;
        }
        throw new IllegalArgumentException("Lexicon Item with special stem change cannot be handled:" + item);
    }


    private StemNode[] handleP3sgCompounds(DictionaryItem item) {
        StemNode[] nodes = new StemNode[2];
        SuffixNode original = getSuffixRootNode(calculateAttributes(item.lemma), Noun_Comp_P3sg);
        nodes[0] = new StemNode(item.lemma, item, original, TerminationType.TERMINAL);
        TurkicSeq modifiedSeq = new TurkicSeq(item.root, alphabet);
        SuffixNode mod = getSuffixRootNode(calculateAttributes(item.root), Noun_Comp_P3sg_Root);
        nodes[1] = new StemNode(modifiedSeq.toString(), item, mod, TerminationType.NON_TERMINAL);
        return nodes;
    }

    public void generateSuffixForms(Set<SuffixFormSet> startForms) {
        Set<SuffixFormSet> toProcess = new HashSet<SuffixFormSet>();
        for (SuffixFormSet rootFormSet : startForms) {
            for (SuffixFormSet succSet : rootFormSet.getSuccessorsIterable()) {
                for (SuffixNode node : suffixFormMap.get(rootFormSet)) {
                    List<SuffixNode> nodesInSuccessor = formGenerator.getNodes(node.attributes, node.expectations, succSet);
                    for (SuffixNode nodeInSuccessor : nodesInSuccessor) {
                        if (!nodeExists(succSet, nodeInSuccessor)) {
                            toProcess.add(succSet);
                        }
                        nodeInSuccessor = addOrReturnExisting(succSet, nodeInSuccessor);
                        if (node.expectations.isEmpty() ||
                                (node.expectations.contains(PhoneticExpectation.ConsonantStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterConsonant)) ||
                                (node.expectations.contains(PhoneticExpectation.VowelStart) && nodeInSuccessor.attributes.contains(PhonAttr.FirstLetterVowel)))
                            node.addSuccNode(nodeInSuccessor);
                    }
                }
            }
        }
        if (toProcess.size() == 0)
            return;
        generateSuffixForms(toProcess);
    }


    static class RootSuffixKey {
        PrimaryPos pos;
        AttributeSet<RootAttr> attrs;
        Set<SuffixFormSet> sets;

        RootSuffixKey(DictionaryItem item, Set<SuffixFormSet> sets) {
            this.pos = item.primaryPos;
            this.attrs = item.attrs;
            this.sets = sets;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RootSuffixKey rootSuffixKey = (RootSuffixKey) o;

            if (!attrs.equals(rootSuffixKey.attrs)) return false;
            if (pos != rootSuffixKey.pos) return false;
            if (!sets.equals(rootSuffixKey.sets)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = pos.hashCode();
            result = 31 * result + attrs.hashCode();
            result = 31 * result + sets.hashCode();
            return result;
        }
    }

    static Map<RootSuffixKey, SuffixFormSet> dynamicFormSetMap = new HashMap<RootSuffixKey, SuffixFormSet>();

    static class RootSuffixSetBuilder {
        SuffixFormSet original;
        SuffixFormSet modified;

        RootSuffixSetBuilder(DictionaryItem item) {

            switch (item.primaryPos) {
                case Noun:
                    if (item.secondaryPos == SecondaryPos.ProperNoun) {
                        original = ProperNoun_Main;
                        modified = ProperNoun_Main;
                    } else {
                        getForNoun(item);
                        if (item.secondaryPos == SecondaryPos.Time) {
                            original.add(Rel_ki);
                        }
                    }
                    break;
                case Verb:
                    getForVerb(item);
                    break;
                case Adjective:
                    getForAdjective(item);
                    break;
                case Pronoun:
                    getForPronoun(item);
                    break;
                case Adverb:
                    original = Adv_Main;
                    modified = Adv_Main;
                    break;
                case Interjection:
                    original = Interj_Main;
                    modified = Interj_Main;
                    break;
                case Numeral:
                    original = Numeral_Main;
                    modified = Numeral_Main;
                    break;
                case Determiner:
                    original = Det_Main;
                    modified = Det_Main;
                    break;
                case Conjunction:
                    original = Conj_Main;
                    modified = Conj_Main;
                    break;
                case Particle:
                    if (item.secondaryPos == SecondaryPos.Question) {
                        original = Ques_mI;
                        modified = Ques_mI;
                    } else {
                        original = Particle_Main;
                        modified = Particle_Main;
                    }
                    break;
                default:
                    original = Noun_Main;
                    modified = Noun_Main;
                    break;
            }
            if (item.suffixData != null) {
                original.remove(item.suffixData.rejects);
                original.add(item.suffixData.accepts);
                modified.remove(item.suffixData.rejects);
                modified.add(item.suffixData.accepts);
            }
        }

        private SuffixFormSet addOrReturnExisting(DictionaryItem item, SuffixFormSet set) {
            RootSuffixKey key = new RootSuffixKey(item, set.getSuccessors());
            if (dynamicFormSetMap.containsKey(key)) {
                return dynamicFormSetMap.get(key);
            } else {
                dynamicFormSetMap.put(key, set);
                return set;
            }
        }

        private void getForVerb(DictionaryItem item) {
            original = new SuffixFormSet("Verb", TurkishSuffixes.VerbRoot, "");
            modified = new SuffixFormSet("Verb-Mod", TurkishSuffixes.VerbRoot, "");
            original.add(Verb_Main.getSuccSetCopy());
            modified.add(Verb_Main.getSuccSetCopy());
            for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case Aorist_A:
                        original.add(Aor_Ar, AorPart_Ar).remove(Aor_Ir, AorPart_Ir);
                        modified.add(Aor_Ar, AorPart_Ar).remove(Aor_Ir, AorPart_Ir);
                        break;
                    case Aorist_I:
                        original.add(Aor_Ir, AorPart_Ir).remove(Aor_Ar, AorPart_Ar);
                        modified.add(Aor_Ir, AorPart_Ir).remove(Aor_Ar, AorPart_Ar);
                        break;
                    case Passive_In:
                        original.remove(Pass_nIl).add(Pass_In);
                        break;
                    case LastVowelDrop:
                        original.remove(Pass_nIl);
                        modified.clear().add(Pass_nIl);
                        break;
                    case VoicingOpt:
                        modified.remove(Verb_Exp_C.getSuccessors());
                        break;
                    case Voicing:
                        original.remove(Verb_Exp_V.getSuccessors());
                        modified.remove(Verb_Exp_C.getSuccessors());
                        break;
                    case ProgressiveVowelDrop:
                        original.remove(Prog_Iyor);
                        modified.clear().add(Prog_Iyor);
                        break;
                    case NonTransitive:
                        original.remove(Caus_t, Caus_tIr);
                        modified.remove(Caus_t, Caus_tIr);
                        break;
                    case Reflexive:
                        original.add(Reflex_In);
                        modified.add(Reflex_In);
                        break;
                    case Reciprocal:
                        original.add(Recip_Is);
                        modified.add(Recip_Is);
                        break;
                    case Causative_t:
                        original.remove(Caus_tIr).add(Caus_t);
                        modified.remove(Caus_tIr).add(Caus_t);
                        break;
                    default:
                        break;
                }
            }
            original = addOrReturnExisting(item, original);
            modified = addOrReturnExisting(item, modified);
        }

        void getForNoun(DictionaryItem item) {
            original = new SuffixFormSet("Noun", TurkishSuffixes.NounRoot, "");
            modified = new SuffixFormSet("Noun-Mod", TurkishSuffixes.NounRoot, "");
            original.add(Noun_Main.getSuccSetCopy());
            modified.add(Noun_Main.getSuccSetCopy());
            for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case VoicingOpt:
                        modified.remove(Noun_Exp_C.getSuccessors());
                        break;
                    case Voicing:
                    case Doubling:
                    case LastVowelDrop:
                        original.remove(Noun_Exp_V.getSuccessors());
                        modified.remove(Noun_Exp_C.getSuccessors());
                        break;
                    case CompoundP3sg:
                        original.clear().add(TurkishSuffixes.Noun_Comp_P3sg.getSuccSetCopy());
                        modified.clear().add(TurkishSuffixes.Noun_Comp_P3sg_Root.getSuccSetCopy());
                        break;
                    default:
                        break;
                }
            }
            original = addOrReturnExisting(item, original);
            modified = addOrReturnExisting(item, modified);
        }

        void getForAdjective(DictionaryItem item) {
            original = new SuffixFormSet("Adjective", TurkishSuffixes.AdjRoot, "");
            modified = new SuffixFormSet("Adjective-Mod", TurkishSuffixes.AdjRoot, "");
            original.add(Adj_Main.getSuccSetCopy());
            modified.add(Adj_Main.getSuccSetCopy());
            for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
                switch (attribute) {
                    case Voicing:
                    case Doubling:
                    case LastVowelDrop:
                        original.remove(Adj_Exp_V.getSuccessors());
                        modified.remove(Adj_Exp_C.getSuccessors());
                        break;
                    default:
                        break;
                }
            }
            original = addOrReturnExisting(item, original);
            modified = addOrReturnExisting(item, modified);
        }

        void getForPronoun(DictionaryItem item) {
            original = new SuffixFormSet("Pronoun", TurkishSuffixes.PersPronRoot, "");
            modified = new SuffixFormSet("Pronoun-Mod", TurkishSuffixes.PersPronRoot, "");
            original.add(PersPron_Main.getSuccSetCopy());
            modified.add(PersPron_Main.getSuccSetCopy());
            original = addOrReturnExisting(item, original);
            modified = addOrReturnExisting(item, modified);
        }

    }

}
