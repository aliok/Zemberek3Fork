package zemberek3.lexicon;

import com.google.common.collect.Lists;
import zemberek3.lexicon.tr.PhoneticExpectation;
import zemberek3.lexicon.graph.SuffixData;
import zemberek3.lexicon.graph.SuffixNode;
import zemberek3.lexicon.graph.TerminationType;
import zemberek3.lexicon.tr.PhonAttr;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static zemberek3.lexicon.tr.PhonAttr.*;
import static zemberek3.structure.TurkishAlphabet.*;

public class SuffixNodeGenerator {

    public SuffixNode getEmptyNode(
            AttributeSet<PhonAttr> attrs,
            AttributeSet<PhoneticExpectation> expectations,
            SuffixData suffixData,
            SuffixForm set) {
        return getNodes(attrs, expectations, suffixData, set).get(0);
    }

    public List<SuffixNode> getNodes(
            AttributeSet<PhonAttr> attrs,
            AttributeSet<PhoneticExpectation> expectations,
            SuffixData suffixData,
            SuffixForm set) {

        List<SuffixToken> tokenList = Lists.newArrayList(new SuffixStringTokenizer(set.generation));

        // zero length token
        if (tokenList.size() == 0) {
            return Lists.newArrayList(new SuffixNode(set, "", attrs.copy(), expectations.copy(), suffixData, set.terminationType));
        }

        List<SuffixNode> forms = new ArrayList<SuffixNode>(1);

        // generation of forms. normally only one form is generated. But in situations like cI~k, two Forms are generated.
        TurkicSeq seq = new TurkicSeq();
        int index = 0;
        for (SuffixToken token : tokenList) {
            AttributeSet<PhonAttr> formAttrs = defineMorphemicAttributes(seq, attrs);
            switch (token.type) {
                case LETTER:
                    seq.append(token.letter);
                    if (index == tokenList.size() - 1) {
                        forms.add(new SuffixNode(set, seq.toString(), defineMorphemicAttributes(seq, attrs), set.terminationType));
                    }
                    break;

                case A_WOVEL:
                    if (index == 0 && attrs.contains(LastLetterVowel)) {
                        break;
                    }
                    TurkicLetter lA = TurkicLetter.UNDEFINED;
                    if (formAttrs.contains(LastVowelBack))
                        lA = L_a;
                    else if (formAttrs.contains(LastVowelFrontal))
                        lA = L_e;
                    if (lA == TurkicLetter.UNDEFINED)
                        throw new IllegalArgumentException("Cannot generate A form!");
                    seq.append(lA);
                    if (index == tokenList.size() - 1)
                        forms.add(new SuffixNode(
                                set,
                                seq.toString(),
                                defineMorphemicAttributes(seq, attrs),
                                set.terminationType));
                    break;

                case I_WOVEL:
                    if (index == 0 && attrs.contains(LastLetterVowel))
                        break;
                    TurkicLetter li = TurkicLetter.UNDEFINED;
                    if (formAttrs.containsAll(LastVowelBack, LastVowelRounded))
                        li = L_u;
                    else if (formAttrs.containsAll(LastVowelBack, LastVowelUnrounded))
                        li = L_ii;
                    else if (formAttrs.containsAll(LastVowelFrontal, LastVowelRounded))
                        li = L_uu;
                    else if (formAttrs.containsAll(LastVowelFrontal, LastVowelUnrounded))
                        li = L_i;
                    if (li == TurkicLetter.UNDEFINED)
                        throw new IllegalArgumentException("Cannot generate I form!");
                    seq.append(li);
                    if (index == tokenList.size() - 1)
                        forms.add(new SuffixNode(set, seq.toString(), defineMorphemicAttributes(seq, attrs), set.terminationType));
                    break;

                case APPEND:
                    if (formAttrs.contains(LastLetterVowel)) {
                        seq.append(token.letter);
                    }
                    if (index == tokenList.size() - 1)
                        forms.add(new SuffixNode(
                                set,
                                seq.toString(),
                                defineMorphemicAttributes(seq, attrs),
                                set.terminationType));
                    break;

                case DEVOICE_FIRST:
                    TurkicLetter ld = token.letter;
                    if (formAttrs.contains(LastLetterVoiceless))
                        ld = alphabet.devoice(token.letter);
                    seq.append(ld);
                    if (index == tokenList.size() - 1)
                        forms.add(new SuffixNode(
                                set,
                                seq.toString(),
                                defineMorphemicAttributes(seq, attrs),
                                set.terminationType));
                    break;

                case VOICE_LAST:
                    ld = token.letter;
                    seq.append(ld);
                    if (index == tokenList.size() - 1) {
                        forms.add(new SuffixNode(
                                set,
                                seq.toString(),
                                defineMorphemicAttributes(seq, attrs),
                                new AttributeSet<PhoneticExpectation>(PhoneticExpectation.ConsonantStart),
                                suffixData,
                                set.terminationType));
                        seq.changeLast(alphabet.voice(token.letter));
                        forms.add(new SuffixNode(
                                set,
                                seq.toString(),
                                defineMorphemicAttributes(seq, attrs),
                                new AttributeSet<PhoneticExpectation>(PhoneticExpectation.VowelStart),
                                suffixData,
                                TerminationType.NON_TERMINAL));
                    }
                    break;
            }
            index++;
        }
        return forms;
    }

    // in suffix, defining morphemic attributes is straight forward.
    AttributeSet<PhonAttr> defineMorphemicAttributes(TurkicSeq seq, AttributeSet<PhonAttr> predecessorAttrs) {
        if (seq.length() == 0)
            return predecessorAttrs.copy();
        AttributeSet<PhonAttr> attrs = new AttributeSet<PhonAttr>();
        if (seq.hasVowel()) {
            if (seq.lastVowel().isFrontal())
                attrs.add(LastVowelFrontal);
            else
                attrs.add(LastVowelBack);
            if (seq.lastVowel().isRounded())
                attrs.add(LastVowelRounded);
            else
                attrs.add(LastVowelUnrounded);
            if (seq.lastLetter().isVowel())
                attrs.add(LastLetterVowel);
            else
                attrs.add(LastLetterConsonant);
            if (seq.firstLetter().isVowel())
                attrs.add(FirstLetterVowel);
            else
                attrs.add(FirstLetterConsonant);
        } else {
            // we transfer vowel attributes from the predecessor attributes.
            attrs = predecessorAttrs.copy();
            attrs.add(LastLetterConsonant, FirstLetterConsonant, HasNoVowel);
            attrs.remove(LastLetterVowel);
        }
        if (seq.lastLetter().isVoiceless()) {
            attrs.add(PhonAttr.LastLetterVoiceless);
            if (seq.lastLetter().isStopConsonant()) {
                // kitap
                attrs.add(PhonAttr.LastLetterVoicelessStop);
            }
        } else
            attrs.add(PhonAttr.LastLetterNotVoiceless);
        return attrs;
    }

    AttributeSet<PhonAttr> defineMorphemicAttributes(TurkicSeq seq) {
        return defineMorphemicAttributes(seq, AttributeSet.<PhonAttr>emptySet());
    }

    TurkishAlphabet alphabet = new TurkishAlphabet();

    private static enum TokenType {
        I_WOVEL,
        A_WOVEL,
        DEVOICE_FIRST,
        VOICE_LAST,
        APPEND,
        LETTER
    }

    private static class SuffixToken {
        TokenType type;
        TurkicLetter letter;
        boolean append = false;

        private SuffixToken(TokenType type, TurkicLetter letter) {
            this.type = type;
            this.letter = letter;
        }

        private SuffixToken(TokenType type, TurkicLetter letter, boolean append) {
            this.type = type;
            this.letter = letter;
            this.append = append;
        }
    }

    class SuffixStringTokenizer implements Iterator<SuffixToken> {

        private int pointer;
        private final String generationWord;

        public SuffixStringTokenizer(String generationWord) {
            this.generationWord = generationWord;
        }

        public boolean hasNext() {
            return generationWord != null && pointer < generationWord.length();
        }

        public SuffixToken next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no elements left!");
            }
            char c = generationWord.charAt(pointer++);
            char cNext = 0;
            if (pointer < generationWord.length())
                cNext = generationWord.charAt(pointer);

            switch (c) {
                case '+':
                    pointer++;
                    if (cNext == 'I') {
                        return new SuffixToken(TokenType.I_WOVEL, TurkicLetter.UNDEFINED, true);
                    } else if (cNext == 'A') {
                        return new SuffixToken(TokenType.A_WOVEL, TurkicLetter.UNDEFINED, true);
                    } else {
                        return new SuffixToken(TokenType.APPEND, alphabet.getLetter(cNext));
                    }
                case '>':
                    pointer++;
                    return new SuffixToken(TokenType.DEVOICE_FIRST, alphabet.getLetter(cNext));
                case '~':
                    pointer++;
                    return new SuffixToken(TokenType.VOICE_LAST, alphabet.getLetter(cNext));
                case 'I':
                    return new SuffixToken(TokenType.I_WOVEL, TurkicLetter.UNDEFINED);
                case 'A':
                    return new SuffixToken(TokenType.A_WOVEL, TurkicLetter.UNDEFINED);
                default:
                    return new SuffixToken(TokenType.LETTER, alphabet.getLetter(c));

            }
        }

        public void remove() {
        }
    }
}
