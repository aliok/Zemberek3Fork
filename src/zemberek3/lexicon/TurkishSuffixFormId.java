package zemberek3.lexicon;

import static zemberek3.lexicon.TurkishSuffixId.*;


public enum TurkishSuffixFormId {

    Pl_lAr(Pl, "lAr"),

    Dat_yA(Dat, "+yA"),
    Dat_nA(Dat, "nA"),
    Loc_dA(Loc, ">dA"),
    Loc_ndA(Loc, "ndA"),

    Abl_dAn(Abl, ">dAn"),
    Abl_ndAn(Abl, "ndAn"),

    Gen_nIn(Gen, "+nIn"),

    Acc_yI(Acc, "+yI"),
    Acc_nI(Acc, "nI"),

    Inst_ylA(Inst, "+ylA"),

    P1sg_Im(P1sg, "+Im"),

    P2sg_In(P2sg, "+In"),

    P3sg_sI(P3sg, "+sI"),

    P1pl_ImIz(P1pl, "+ImIz"),

    P2pl_InIz(P2pl, "+InIz"),

    P3pl_lArI(P3pl, "lArI"),

    Dim_cIk(Dim, ">cIk"),
    Dim_cIg(Dim, ">cIğ"),
    Dim_cAgIz(Dim, "cAğIz"),

    With_lI(With, "lI"),

    Without_sIz(Without, "sIz"),

    Rel_ki(Rel, "ki"), // masa-da-ki

    A1sg_yIm(A1sg, "+yIm"), // gel-e-yim
    A1sg_m(A1sg, "m"), // gel-se-m

    A2sg_sIn(A2sg, "+sIn"), // gel-ecek-sin
    A2sg_n(A2sg, "n"), // gel-di-n
    A2sg_sAnA(A2sg, "sAnA"), //gel-sene
    A2sg_EMPTY(A2sg, ""), // gel-

    A3sg_EMPTY(A3sg, ""), // gel-di-
    A3sg_sIn(A3sg, "sIn"), // gel-sin

    A1pl_yIz(A1pl, "+yIz"), // geliyor-uz
    A1pl_k(A1pl, "k"), // gel-di-k
    A1pl_lIm(A1pl, "lIm"), // gel-e-lim

    A2pl_sInIz(A2pl, "+sInIz"), // gel-ecek-siniz
    A2pl_sAnIzA(A2pl, "sAnIzA"), // gel-senize
    A2pl_nIz(A2pl, "nIz"), // gel-di-niz
    A2pl_yIn(A2pl, "+yIn"), // gel-me-yin

    A3pl_lAr(A3pl, "lAr"), // gel-ecek-ler
    A3pl_sInlAr(A3pl, "sInlAr"), // gel-sinler

    Become_lAs(Become, "lAş"),

    Aor_Ir(Aor, "+Ir"), //gel-ir
    Aor_Ar(Aor, "+Ar"), //ser-er
    Aor_z(Aor, "z"), // gel-me-z
    Aor_EMPTY(Aor, ""), // gel-me--yiz

    Prog_Iyor(Prog, "+Iyor"),
    Prog_mAktA(Prog, "mAktA"),

    Fut_yAcAk(Fut, "+yAcAk"),
    Fut_yAcAg(Fut, "+yAcAğ"),

    Past_dI(Past, ">dI"),

    Evid_mIs(Evid, "mIş"),

    Neg_mA(Neg, "mA"), //gel-me
    Neg_m(Neg, "m"), // gel-m-iyor

    Cond_ysA(Cond, "+ysA"),

    Necess_mAlI(Necess, "mAlI"),

    Opt_yA(Opt, "+yA"),

    Pass_In(Pass, "+In"),
    Pass_nIl(Pass, "+nIl"),
    Pass_Il(Pass, "Il"),

    Caus_t(Caus, "t"),
    Caus_tIr(Pass, ">dIr"),

    Recip_yIs(Recip, "+yIş"),
    Recip_Is(Recip, "+Iş"),

    Reflex_In(Reflex, "+In"),

    Abil_yAbil(Abil, "+yAbil"),
    Abil_yA(Abil, "+yA"),

    Cop_dIr(Cop, ">dIr"),

    PastCop_ydI(PastCop, "+ydI"),

    EvidCop_ymIs(EvidCop, "+ymIş"),

    CondCop_ysA(CondCop, "+ysA"),

    While_ken(While, "+yken"),

    AfterDoing_yIncA(AfterDoing, "+yIncA"),


    Noun_Main(NounRoot, ""),
    Noun_Exp_C(NounRoot, ""),
    Noun_Exp_V(NounRoot, ""),
    Noun_Comp_p3sg(NounRoot, ""),
    Noun_Comp_p3sg_Root(NounRoot, ""),


    Verb_Main(VerbRoot, ""),
    Verb_Aor_Ar(VerbRoot, ""),
    Verb_Prog_Drop(VerbRoot, ""),
    Verb_Prog_NotDrop(VerbRoot, ""),
    Verb_Vow_Drop(VerbRoot, ""),
    Verb_Vow_NotDrop(VerbRoot, ""),
    Verb_Exp_C(VerbRoot, ""),
    Verb_Exp_V(VerbRoot, ""),
    Verb_De(VerbRoot, ""),
    Verb_Ye(VerbRoot, ""),
    Verb_Di(VerbRoot, ""),
    Verb_Yi(VerbRoot, ""),

    Pronoun_Main(PronounRoot, ""),
    Pronoun_BenSen(PronounRoot, ""),
    Pronoun_BanSan(PronounRoot, "");

    TurkishSuffixId suffixId;
    String generationWord;

    TurkishSuffixFormId(TurkishSuffixId suffixId, String generationWord) {
        this.suffixId = suffixId;
        this.generationWord = generationWord;
    }

    public static TurkishSuffixFormId[] CASE_FORMS = {Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_nI, Inst_ylA};
    public static TurkishSuffixFormId[] POSSESSIVE_FORMS = {P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    public static TurkishSuffixFormId[] PERSON_FORMS_N = {A1sg_yIm, A2sg_sIn, A3sg_EMPTY, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    public static TurkishSuffixFormId[] COPULAR_FORMS = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken};
    public static TurkishSuffixFormId[] TENSE_DEFAULT_FORMS = {Prog_Iyor, Prog_mAktA, Fut_yAcAg, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir};
}
