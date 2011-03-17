package zemberek3.lexicon;

import com.google.common.collect.Lists;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.*;

import static zemberek3.lexicon.PhonAttr.*;
import static zemberek3.structure.TurkishAlphabet.*;

public class SuffixFormGenerator {

    List<SuffixForm> suffixNodes(
            AttributeSet<PhonAttr> attrs,
            AttributeSet<PhonAttr> expectations,
            String generationString) {
        List<SuffixToken> tokenList = Lists.newArrayList(new SuffixStringTokenizer(generationString));

        // zero length token
        if (tokenList.size() == 0) {
            Lists.newArrayList(new SuffixForm(new TurkicSeq(), attrs.copy(), expectations.copy()));
        }

        // generation of forms. normally only one form is generated. But in situations like cI~k, two Forms are generated.
        List<SuffixForm> forms = Lists.newArrayList(new SuffixForm(new TurkicSeq()));
        int index = 0;
        for (SuffixToken token : tokenList) {
            switch (token.type) {
                case LETTER:
                    for (SuffixForm form : forms) {
                        form.sequence.append(token.letter);
                    }
                    break;

                case A_WOVEL:
                    if (index == 0 && attrs.contains(LastLetterVowel)) {
                        break;
                    }
                    for (SuffixForm form : forms) {
                        AttributeSet<PhonAttr> fromAttrs = defineMorphemicAttributes(form.sequence, attrs);
                        TurkicLetter lA = TurkicLetter.UNDEFINED;
                        if (fromAttrs.contains(LastVowelBack))
                            lA = L_a;
                        else if (fromAttrs.contains(LastVowelFrontal))
                            lA = L_e;
                        if (lA == TurkicLetter.UNDEFINED)
                            throw new IllegalArgumentException("Cannot generate A form!");
                        form.sequence.append(lA);
                    }
                    break;
                case I_WOVEL:
                    for (SuffixForm form : forms) {
                        AttributeSet<PhonAttr> fromAttrs = defineMorphemicAttributes(form.sequence, attrs);
                        if (index == 0 && attrs.contains(LastLetterVowel))
                            break;
                        TurkicLetter li = TurkicLetter.UNDEFINED;
                        if (fromAttrs.containsAll(LastVowelBack, LastVowelRounded))
                            li = L_u;
                        else if (fromAttrs.containsAll(LastVowelBack, LastVowelUnrounded))
                            li = L_ii;
                        else if (fromAttrs.containsAll(LastVowelFrontal, LastVowelRounded))
                            li = L_uu;
                        else if (fromAttrs.containsAll(LastVowelFrontal, LastVowelUnrounded))
                            li = L_i;
                        if (li == TurkicLetter.UNDEFINED)
                            throw new IllegalArgumentException("Cannot generate I form!");
                        form.sequence.append(li);
                    }
                    break;
                case APPEND:
                    for (SuffixForm form : forms) {
                        AttributeSet<PhonAttr> fromAttrs = defineMorphemicAttributes(form.sequence, attrs);
                        if (fromAttrs.contains(LastLetterVowel)) {
                            form.sequence.append(token.letter);
                        }
                    }

                    break;

                case DEVOICE_FIRST:
                    for (SuffixForm form : forms) {
                        AttributeSet<PhonAttr> fromAttrs = defineMorphemicAttributes(form.sequence, attrs);
                        TurkicLetter ld = token.letter;
                        if (fromAttrs.contains(LastLetterVoicelessStop))
                            ld = alphabet.devoice(token.letter);
                        form.sequence.append(ld);
                    }
                    break;

                case VOICE_LAST:
                    List<SuffixForm> nf = Lists.newArrayList();
                    for (SuffixForm form : forms) {
                        SuffixForm second = form.copy();
                        form.sequence.append(token.letter);
                        form.expectations.add(FirstLetterConsonant);
                        nf.add(form);
                        second.sequence.append(alphabet.voice(token.letter));
                        second.expectations.add(FirstLetterVowel);
                        nf.add(second);                        
                    }
                    forms = nf;
                    break;
            }
            index++;
        }
        for (SuffixForm form : forms) {
            form.attributes = defineMorphemicAttributes(form.sequence, attrs);
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
        } else {
            // we transfer vowel attributes from the predecessor attributes.
            attrs = predecessorAttrs.copy();
            attrs.add(LastLetterConsonant, FirstLetterConsonant, HasNoVowel);
            attrs.remove(FirstLetterVowel, LastLetterVowel);
        }
        if (seq.lastLetter().isStopConsonant()) {
            attrs.add(LastLetterVoicelessStop);
        } else
            attrs.add(LastLetterNotVoicelessStop);
        return attrs;
    }

    AttributeSet<PhonAttr> defineMorphemicAttributes(TurkicSeq seq) {
        return defineMorphemicAttributes(seq, AttributeSet.<PhonAttr>emptySet());
    }

    TurkishAlphabet alphabet = new TurkishAlphabet();

    private static enum TokenType {
        I_WOVEL,
        A_WOVEL,
        VOICE_LAST,
        DEVOICE_FIRST,
        APPEND,
        LETTER
    }

    private static class SuffixToken {
        TokenType type;
        TurkicLetter letter;

        private SuffixToken(TokenType type, TurkicLetter letter) {
            this.type = type;
            this.letter = letter;
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
            char p = generationWord.charAt(pointer++);
            switch (p) {
                case '+':
                    return new SuffixToken(TokenType.APPEND, alphabet.getLetter(generationWord.charAt(pointer++)));
                case '>':
                    return new SuffixToken(TokenType.DEVOICE_FIRST, alphabet.getLetter(generationWord.charAt(pointer++)));
                case '~':
                    return new SuffixToken(TokenType.VOICE_LAST, alphabet.getLetter(generationWord.charAt(pointer++)));
                case 'I':
                    return new SuffixToken(TokenType.I_WOVEL, TurkicLetter.UNDEFINED);
                case 'A':
                    return new SuffixToken(TokenType.A_WOVEL, TurkicLetter.UNDEFINED);
                default:
                    return new SuffixToken(TokenType.LETTER, alphabet.getLetter(p));
            }
        }

        public void remove() {
        }
    }

    public SuffixNode[] generateNodes(TurkishSuffix suffix, String generationWord, TurkishSuffix... exclusivePredecessors) {
        return new SuffixNode[0];
    }

}
