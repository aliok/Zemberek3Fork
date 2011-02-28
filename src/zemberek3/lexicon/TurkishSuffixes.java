package zemberek3.lexicon;

import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.*;

public class TurkishSuffixes {
    static TurkishSuffix Pl_lAr = new TurkishSuffix("Pl_lAr");
    static TurkishSuffix Dat_yA = new TurkishSuffix("Dat_yA");
    static TurkishSuffix Loc_dA = new TurkishSuffix("Loc_dA");
    static TurkishSuffix Abl_dAn = new TurkishSuffix("Abl_dAn");
    static TurkishSuffix Gen_nIn = new TurkishSuffix("Gen_nIn");
    static TurkishSuffix Acc_yI = new TurkishSuffix("Acc_yI");
    static TurkishSuffix Inst_lA;

    static TurkishSuffix P1sg_Im;
    static TurkishSuffix P2sg_In;
    static TurkishSuffix P3sg_sI;
    static TurkishSuffix P1pl_ImIz;
    static TurkishSuffix P2pl_InIz;
    static TurkishSuffix P3pl_lArI;

    static TurkishSuffix Dim_cIk;
    static TurkishSuffix Dim_cAgIz;
    static TurkishSuffix With_lI;
    static TurkishSuffix Without_sIz;
    static TurkishSuffix Since_dIr;
    static TurkishSuffix Related_sAl;
    static TurkishSuffix Drv_lIk;
    static TurkishSuffix Drv_ImsI;
    static TurkishSuffix Rel_kI;
    static TurkishSuffix By_cA;
    static TurkishSuffix Cmp_cA;
    static TurkishSuffix Agt_cI;

    static TurkishSuffix A1sg_yIm;
    static TurkishSuffix A2sg_sIn;
    static TurkishSuffix A3sg;
    static TurkishSuffix A1pl_yIz;
    static TurkishSuffix A2pl_sInIz;
    static TurkishSuffix A3pl_lAr;

    static TurkishSuffix Become_lAs;
    static TurkishSuffix Ly_cA;
    static TurkishSuffix Quite_cA;

    static TurkishSuffix Aor_Ir;
    static TurkishSuffix Prog_Iyor;
    static TurkishSuffix Prog_mAktA;
    static TurkishSuffix Fut_yAcAk;
    static TurkishSuffix Past_dI;
    static TurkishSuffix Evid_mIs;
    static TurkishSuffix Neg_mA;
    static TurkishSuffix Cond_sa;
    static TurkishSuffix Necess_mAlI;
    static TurkishSuffix Opt_yA;
    static TurkishSuffix Pass_In = new TurkishSuffix("Pass_In");
    static TurkishSuffix Caus_dIr;
    static TurkishSuffix Recip_yIs;
    static TurkishSuffix Reflex_In;
    static TurkishSuffix Abil_yAbIl;
    static TurkishSuffix Cont_yAdur;


    static TurkishSuffix Cop_dIr;
    static TurkishSuffix PastCop_ydI;
    static TurkishSuffix EvidCop_ymIs;
    static TurkishSuffix CondCop_ysA;
    static TurkishSuffix While_yken;

    static TurkishSuffix AfterDoing_yIp;
    static TurkishSuffix JustAfter_yIncA;

    static TurkishSuffix[] NOUN_CASE = {Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_yI, Inst_lA};
    static TurkishSuffix[] NOUN_POSS = {P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    static TurkishSuffix[] NOUN_PERSON = {A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    static TurkishSuffix[] COPULAR = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_yken};

    public void generate() {
        Pl_lAr.addNodes(generateNodes(Pl_lAr, "lAr")).
                addSuccessor(NOUN_CASE).
                addSuccessor(COPULAR).
                addSuccessor(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz, By_cA);
        Acc_yI.addNodes(generateNodes(Acc_yI, "+yI"));

        
    }

    TurkishAlphabet alphabet = new TurkishAlphabet();

    private List<TurkicSeq> generateFromSuffixString(String suffixString) {

        ArrayList<TurkicSeq> sequences = new ArrayList<TurkicSeq>();
        if (suffixString.length() > 0)
            sequences.add(new TurkicSeq());

        Iterator<SuffixToken> tokenIterator = new SuffixStringTokenizer(suffixString);
        while (tokenIterator.hasNext()) {
            SuffixToken token = tokenIterator.next();
            switch (token.type) {

                case LETTER:
                    for (TurkicSeq seq : sequences) {
                        seq.append(token.l);
                    }
                    break;

                case A_WOVEL:
                    ArrayList<TurkicSeq> alist = new ArrayList<TurkicSeq>();
                    for (TurkicSeq seq : sequences) {
                        if (!seq.hasVowel()) {
                            if (seq.length() == 0 && sequences.size() == 1)
                                alist.add(new TurkicSeq());
                            alist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_a));
                            alist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_e));
                        } else if (seq.lastVowel().isFrontalVowel()) {
                            alist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_e));
                        } else {
                            alist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_a));
                        }
                    }
                    sequences = alist;
                    break;

                case I_WOVEL:
                    ArrayList<TurkicSeq> ilist = new ArrayList<TurkicSeq>();
                    for (TurkicSeq seq : sequences) {
                        if (!seq.hasVowel()) {
                            if (seq.length() == 0 && sequences.size() == 1)
                                ilist.add(new TurkicSeq());
                            ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_ii));
                            ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_i));
                            ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_u));
                            ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_uu));
                        } else {
                            boolean frontal = seq.lastVowel().frontalVowel;
                            boolean round = seq.lastVowel().roundedVowel;
                            if (frontal && round) {
                                ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_uu));
                            } else if (frontal && !round) {
                                ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_i));
                            } else if (!frontal && round) {
                                ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_uu));
                            } else if (!frontal && !round) {
                                ilist.add(new TurkicSeq(seq).append(TurkishAlphabet.L_ii));
                            }
                        }
                    }
                    sequences = ilist;
                    break;

                case DEVOICE:
                    ArrayList<TurkicSeq> dlist = new ArrayList<TurkicSeq>();
                    for (TurkicSeq sequence : sequences) {
                        dlist.add(new TurkicSeq(sequence).append(token.l));
                        dlist.add(new TurkicSeq(sequence).append(alphabet.devoice(token.l)));
                    }
                    sequences = dlist;
                    break;

                case VOICE:
                    ArrayList<TurkicSeq> vlist = new ArrayList<TurkicSeq>();
                    for (TurkicSeq sequence : sequences) {
                        vlist.add(new TurkicSeq(sequence).append(token.l));
                        vlist.add(new TurkicSeq(sequence).append(alphabet.voice(token.l)));
                    }
                    sequences = vlist;
                    break;

                case APPEND:
                    ArrayList<TurkicSeq> aplist = new ArrayList<TurkicSeq>();
                    for (TurkicSeq sequence : sequences) {
                        aplist.add(new TurkicSeq(sequence).append(token.l));
                        aplist.add(new TurkicSeq(sequence));
                    }
                    sequences = aplist;
                    break;
            }
        }
        return sequences;
    }

    private SuffixNode[] generateNodes(TurkishSuffix suffix, String generationWord) {
        List<SuffixNode> nodes = new ArrayList<SuffixNode>();
        for (TurkicSeq sequence : generateFromSuffixString(generationWord)) {
            nodes.add(new SuffixNode(suffix, sequence.toString(), defineMorphemicAttributes(sequence)));
        }
        return nodes.toArray(new SuffixNode[nodes.size()]);
    }

    // in suffix, defining morphemic attributes is straight forward.
    private Set<MorphAttr> defineMorphemicAttributes(TurkicSeq seq) {
        Set<MorphAttr> attributes = new HashSet<MorphAttr>();
        if (seq.hasVowel()) {
            if (seq.lastVowel().isFrontalVowel())
                attributes.add(MorphAttr.LastVowelFrontal);
        }
        if (seq.lastLetter().isVowel()) {
            attributes.add(MorphAttr.LastLetterVowel);
        }
        if (seq.lastLetter().isStopConsonant()) {
            attributes.add(MorphAttr.LastLetterVoicelessStop);
        }
        return attributes;
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
        TurkicLetter l;

        private SuffixToken(TokenType type, TurkicLetter l) {
            this.type = type;
            this.l = l;
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

    public static void main(String[] args) {
        TurkishSuffixes suffixes = new TurkishSuffixes();
        //   suffixes.generateFromSuffixString("In");
    }
}
