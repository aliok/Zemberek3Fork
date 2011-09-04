package zemberek3.lexicon.graph;

import zemberek3.lexicon.*;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import static zemberek3.lexicon.RootAttr.*;
import static zemberek3.lexicon.TurkishSuffixes.*;


/**
 * This class generates StemNode objects from Dictionary Items. Generated Nodes are not connected.
 */
public class StemNodeGenerator {

    TurkishAlphabet alphabet = new TurkishAlphabet();
    SuffixProvider suffixProvider;

    public StemNodeGenerator(SuffixProvider suffixProvider) {
        this.suffixProvider = suffixProvider;
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
            AttributeSet<PhonAttr> phoneticAttributes = calculateAttributes(item.root);
            return new StemNode[]{new StemNode(
                    item.root,
                    item,
                    TerminationType.TERMINAL,
                    phoneticAttributes,
                    AttributeSet.<PhoneticExpectation>emptySet())};
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

        StemNode original = new StemNode(dicItem.root, dicItem, originalAttrs, originalExpectations);
        StemNode modified = new StemNode(modifiedSeq.toString(), dicItem, modifiedAttrs, modifiedExpectations);

        SuffixData[] roots = suffixProvider.defineSuccessorSuffixes(dicItem);

        original.exclusiveSuffixData = roots[0];
        modified.exclusiveSuffixData = roots[1];
        if (original.equals(modified))
            return new StemNode[]{original};

        modified.setTermination(TerminationType.NON_TERMINAL);
        return new StemNode[]{original, modified};
    }


    private StemNode[] handleP3sgCompounds(DictionaryItem item) {
        //TODO: phonetic attribute calculation is missing.
        StemNode[] nodes = new StemNode[2];
        nodes[0] = new StemNode(item.lemma, item, TerminationType.TERMINAL);
        TurkicSeq modifiedSeq = new TurkicSeq(item.root, alphabet);
        nodes[1] = new StemNode(modifiedSeq.toString(), item, TerminationType.NON_TERMINAL);

        SuffixData[] roots = suffixProvider.defineSuccessorSuffixes(item);
        nodes[0].exclusiveSuffixData = roots[0];
        nodes[1].exclusiveSuffixData = roots[1];

        return nodes;
    }

}
