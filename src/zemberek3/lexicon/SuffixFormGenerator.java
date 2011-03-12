package zemberek3.lexicon;

import com.google.common.collect.Lists;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static zemberek3.structure.TurkishAlphabet.*;
import static zemberek3.structure.TurkishAlphabet.L_ii;
import static zemberek3.structure.TurkishAlphabet.L_uu;

public class SuffixFormGenerator {

    TurkishAlphabet alphabet = new TurkishAlphabet();

    private List<Form> generateFormWithNoVowel(String generationString) {
        ArrayList<Form> forms = new ArrayList<Form>();
        forms.addAll(generateFromSuffixString(generationString + "A"));
        forms.addAll(generateFromSuffixString(generationString + "I"));
        for (Form form : forms) {
            form.surface = new TurkicSeq(generationString, alphabet);
        }
        return forms;
    }


    private List<Form> generateFromSuffixString(String suffixString) {

        ArrayList<Form> forms = new ArrayList<Form>();
        if (suffixString.length() > 0)
            forms.add(new Form());

        List<SuffixToken> list = Lists.newArrayList(new SuffixStringTokenizer(suffixString));

        int i = 0;
        for (SuffixToken token : list) {
            switch (token.type) {
                case LETTER:
                    for (Form seq : forms) {
                        seq.surface.append(token.letter);
                    }
                    break;

                case A_WOVEL:
                    ArrayList<Form> alist = new ArrayList<Form>();
                    for (Form form : forms) {
                        if (!form.surface.hasVowel()) {
                            if (form.surface.length() == 0 && forms.size() == 1) {
                                Form empty = new Form();
                                empty.backwardExpcts.set(PhonAttr.LastLetterVowel);
                                alist.add(empty);
                            }
                            Form af = form.copy().append(L_a);
                            af.backwardExpcts.set(PhonAttr.LastVowelBack);
                            alist.add(af);

                            Form ef = form.copy().append(L_e);
                            ef.backwardExpcts.set(PhonAttr.LastVowelFrontal);
                            alist.add(ef);

                        } else if (form.surface.lastVowel().isFrontalVowel()) {
                            alist.add(form.copy().append(L_e));
                        } else {
                            alist.add(form.copy().append(L_a));
                        }
                    }
                    forms = alist;
                    break;

                case I_WOVEL:
                    ArrayList<Form> ilist = new ArrayList<Form>();
                    for (Form form : forms) {
                        if (!form.surface.hasVowel()) {
                            if (form.surface.length() == 0 && forms.size() == 1) {
                                Form empty = new Form();
                                empty.backwardExpcts.set(PhonAttr.LastLetterVowel);
                                ilist.add(empty);
                            }

                            Form fii = form.copy().append(L_ii);
                            fii.backwardExpcts.set(PhonAttr.LastVowelBack, PhonAttr.LastVowelUnrounded);
                            ilist.add(fii);

                            Form fi = form.copy().append(L_i);
                            fi.backwardExpcts.set(PhonAttr.LastVowelFrontal, PhonAttr.LastVowelUnrounded);
                            ilist.add(fi);

                            Form fu = form.copy().append(L_u);
                            fu.backwardExpcts.set(PhonAttr.LastVowelBack, PhonAttr.LastVowelRounded);
                            ilist.add(fu);

                            Form fuu = form.copy().append(L_uu);
                            fuu.backwardExpcts.set(PhonAttr.LastVowelFrontal, PhonAttr.LastVowelRounded);
                            ilist.add(fuu);
                        } else {
                            boolean frontal = form.surface.lastVowel().frontalVowel;
                            boolean round = form.surface.lastVowel().roundedVowel;
                            if (frontal && round) {
                                ilist.add(form.copy().append(L_uu));
                            } else if (frontal && !round) {
                                ilist.add(form.copy().append(L_i));
                            } else if (!frontal && round) {
                                ilist.add(form.copy().append(L_uu));
                            } else if (!frontal && !round) {
                                ilist.add(form.copy().append(L_ii));
                            }
                        }
                    }
                    forms = ilist;
                    break;

                case DEVOICE:
                    ArrayList<Form> dlist = new ArrayList<Form>();
                    for (Form form : forms) {
                        Form fn = form.copy().append(token.letter);
                        if (i == 0)
                            fn.backwardExpcts.set(PhonAttr.LastLetterNotVoicelessStop);
                        dlist.add(fn);
                        if (form.surface.length() == 0) {
                            fn = form.copy().append(alphabet.devoice(token.letter));
                            if (i == 0)
                                fn.backwardExpcts.set(PhonAttr.LastLetterVoicelessStop);
                            dlist.add(fn);
                        }
                    }
                    forms = dlist;
                    break;

                case VOICE:
                    ArrayList<Form> vlist = new ArrayList<Form>();
                    for (Form form : forms) {
                        Form fn = form.copy().append(token.letter);
                        if (i == list.size() - 1)
                            fn.forwardExpcts.set(PhonAttr.FirstLetterConsonant);
                        vlist.add(fn);
                        fn = form.copy().append(alphabet.voice(token.letter));
                        if (i == list.size() - 1)
                            fn.forwardExpcts.set(PhonAttr.FirstLetterVowel);
                        vlist.add(fn);
                    }
                    forms = vlist;
                    break;

                case APPEND:
                    ArrayList<Form> aplist = new ArrayList<Form>();
                    for (Form form : forms) {
                        Form fn = form.copy().append(token.letter);
                        fn.backwardExpcts.set(PhonAttr.LastLetterVowel);
                        aplist.add(fn);
                        fn = form.copy();
                        fn.forwardExpcts.set(PhonAttr.LastLetterConsonant);
                        aplist.add(fn);
                    }
                    forms = aplist;
                    break;
            }
        }
        return forms;
    }
    //seni seviyorum canim askimcim <3

    private static class Form {

        TurkicSeq surface = new TurkicSeq();
        AttributeSet<PhonAttr> forwardAttrs = new AttributeSet<PhonAttr>();
        AttributeSet<PhonAttr> forwardExpcts = new AttributeSet<PhonAttr>();
        AttributeSet<PhonAttr> backwardAttrs = new AttributeSet<PhonAttr>();
        AttributeSet<PhonAttr> backwardExpcts = new AttributeSet<PhonAttr>();

        Form copy() {
            Form copy = new Form();
            copy.surface = new TurkicSeq(surface);
            copy.backwardAttrs = backwardAttrs.copy();
            copy.backwardExpcts = backwardExpcts.copy();
            copy.forwardAttrs = forwardAttrs.copy();
            copy.forwardExpcts = forwardExpcts.copy();

            return copy;
        }

        Form append(TurkicLetter letter) {
            surface.append(letter);
            return this;
        }
    }

    public SuffixNode[] generateNodes(TurkishSuffix suffix, String generationWord, TurkishSuffix... exclusivePredecessors) {
        List<SuffixNode> nodes = new ArrayList<SuffixNode>();
        List<Form> generatedForms = generateFromSuffixString(generationWord);
        List<Form> noVowelForms = new ArrayList<Form>();
        List<Form> formsToRemove = new ArrayList<Form>();
        for (Form form : generatedForms) {
            if (!form.surface.hasVowel()) {
                noVowelForms.addAll(generateFormWithNoVowel(form.surface.toString()));
                formsToRemove.add(form);
            }
        }
        generatedForms.removeAll(formsToRemove);
        generatedForms.addAll(noVowelForms);
        for (Form form : generatedForms) {
            SuffixNode node = new SuffixNode(suffix, form.surface);
            node.forwardExpectations = form.forwardExpcts;
            node.backwardExpectations = form.backwardExpcts;
            if (exclusivePredecessors.length > 0) {
                node.addExclusivePredecessor(exclusivePredecessors);
            }
            defineMorphemicAttributes(form, form.surface);
            node.forwardAttributes = form.forwardAttrs;
            node.backwardAttributes = form.backwardAttrs;
            nodes.add(node);
        }
        return nodes.toArray(new SuffixNode[nodes.size()]);
    }

    // in suffix, defining morphemic attributes is straight forward.
    private void defineMorphemicAttributes(Form form, TurkicSeq seq) {
        if (seq.hasVowel()) {
            if (seq.lastVowel().isFrontalVowel())
                form.forwardAttrs.set(PhonAttr.LastVowelFrontal);
            else
                form.forwardAttrs.set(PhonAttr.LastVowelBack);
            if (seq.lastVowel().isRoundedVowel())
                form.forwardAttrs.set(PhonAttr.LastVowelRounded);
            else
                form.forwardAttrs.set(PhonAttr.LastVowelUnrounded);
            if (seq.lastLetter().isVowel())
                form.forwardAttrs.set(PhonAttr.LastLetterVowel);
            else
                form.forwardAttrs.set(PhonAttr.LastLetterConsonant);
            if (seq.firstLetter().isVowel())
                form.backwardAttrs.set(PhonAttr.FirstLetterVowel);
            else
                form.backwardAttrs.set(PhonAttr.FirstLetterConsonant);
            if (seq.firstLetter().isFrontalVowel())
                form.backwardAttrs.set(PhonAttr.FirstVowelFrontal);
            else
                form.backwardAttrs.set(PhonAttr.FirstVowelBack);
            if (seq.firstLetter().isRoundedVowel())
                form.backwardAttrs.set(PhonAttr.FirstVowelRounded);
            else
                form.backwardAttrs.set(PhonAttr.FirstVowelUnrounded);
        } else {
            form.forwardAttrs.set(PhonAttr.HasNoVowel);
            form.backwardAttrs.set(PhonAttr.HasNoVowel);
        }
        if (seq.lastLetter().isStopConsonant()) {
            form.forwardAttrs.set(PhonAttr.LastLetterVoicelessStop);
        } else
            form.forwardAttrs.set(PhonAttr.LastLetterNotVoicelessStop);
    }


    private static enum TokenType {
        I_WOVEL,
        A_WOVEL,
        VOICE,
        DEVOICE,
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
                    return new SuffixToken(TokenType.DEVOICE, alphabet.getLetter(generationWord.charAt(pointer++)));
                case '~':
                    return new SuffixToken(TokenType.VOICE, alphabet.getLetter(generationWord.charAt(pointer++)));
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


}
