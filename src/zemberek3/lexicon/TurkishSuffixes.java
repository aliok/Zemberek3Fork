package zemberek3.lexicon;

import java.util.HashMap;
import java.util.Map;

public class TurkishSuffixes {

    static TurkishSuffix Pl = new TurkishSuffix("Pl");
    static SuffixFormSet Pl_lAr = newSet(Pl, "lAr");

    static TurkishSuffix Dat = new TurkishSuffix("Dat");
    static SuffixFormSet Dat_yA = newSet(Dat, "+yA");
    static SuffixFormSet Dat_nA = newSet(Dat, "nA");

    static TurkishSuffix Loc = new TurkishSuffix("Loc");
    static SuffixFormSet Loc_dA = newSet(Loc, ">dA");
    static SuffixFormSet Loc_ndA = newSet(Loc, "ndA");

    static TurkishSuffix Abl = new TurkishSuffix("Abl");
    static SuffixFormSet Abl_dAn = newSet(Abl, ">dAn");
    static SuffixFormSet Abl_ndAn = newSet(Abl, "ndAn");

    static TurkishSuffix Gen = new TurkishSuffix("Gen");
    static SuffixFormSet Gen_nIn = newSet(Gen, "+nIn");

    static TurkishSuffix Acc = new TurkishSuffix("Acc");
    static SuffixFormSet Acc_yI = newSet(Acc, "+yI");
    static SuffixFormSet Acc_nI = newSet(Acc, "nI");

    static TurkishSuffix Inst = new TurkishSuffix("Inst");
    static SuffixFormSet Inst_ylA = newSet(Inst, "+ylA");

    static TurkishSuffix P1sg = new TurkishSuffix("P1sg");
    static SuffixFormSet P1sg_Im = newSet(P1sg, "+Im");

    static TurkishSuffix P2sg = new TurkishSuffix("P2sg");
    static SuffixFormSet P2sg_In = newSet(P2sg, "+In");

    static TurkishSuffix P3sg = new TurkishSuffix("P3sg");
    static SuffixFormSet P3sg_sI = newSet(P3sg, "+sI");

    static TurkishSuffix P1pl = new TurkishSuffix("P1pl");
    static SuffixFormSet P1pl_ImIz = newSet(P1pl, "+ImIz");

    static TurkishSuffix P2pl = new TurkishSuffix("P2pl");
    static SuffixFormSet P2pl_InIz = newSet(P2pl, "+InIz");

    static TurkishSuffix P3pl = new TurkishSuffix("P3pl");
    static SuffixFormSet P3pl_lArI = newSet(P3pl, "lArI");

    static TurkishSuffix Dim = new TurkishSuffix("Dim");
    static SuffixFormSet Dim_cIk = newSet(Dim, ">cIk");
    static SuffixFormSet Dim_cIg = newSet(Dim, ">cIğ");
    static SuffixFormSet Dim_cAgIz = newSet(Dim, "cAğIz");

    static TurkishSuffix With = new TurkishSuffix("With");
    static SuffixFormSet With_lI = newSet(With, "lI");

    static TurkishSuffix Without = new TurkishSuffix("Without");
    static SuffixFormSet Without_sIz = newSet(Without, "sIz");

    static TurkishSuffix Rel = new TurkishSuffix("Rel");
    static SuffixFormSet Rel_ki = newSet(Rel, "ki"); // masa-da-ki

    static TurkishSuffix A1sg = new TurkishSuffix("A1sg");
    static SuffixFormSet A1sg_yIm = newSet(A1sg, "+yIm"); // gel-e-yim
    static SuffixFormSet A1sg_m = newSet(A1sg, "m"); // gel-se-m

    static TurkishSuffix A2sg = new TurkishSuffix("A2sg");
    static SuffixFormSet A2sg_sIn = newSet(A2sg, "+sIn"); // gel-ecek-sin
    static SuffixFormSet A2sg_n = newSet(A2sg, "n"); // gel-di-n
    static SuffixFormSet A2sg_sAnA = newSet(A2sg, "sAnA"); //gel-sene
    static SuffixFormSet A2sg_EMPTY = newSet("A2sg_EMPTY", A2sg, ""); // gel-

    static TurkishSuffix A3sg = new TurkishSuffix("A3sg");
    static SuffixFormSet A3sg_EMPTY = newSet(A3sg, ""); // gel-di-
    static SuffixFormSet A3sg_sIn = newSet(A3sg, "sIn"); // gel-sin

    static TurkishSuffix A1pl = new TurkishSuffix("A1pl");
    static SuffixFormSet A1pl_yIz = newSet(A1pl, "+yIz"); // geliyor-uz
    static SuffixFormSet A1pl_k = newSet(A1pl, "k"); // gel-di-k
    static SuffixFormSet A1pl_lIm = newSet(A1pl, "lIm"); // gel-e-lim

    static TurkishSuffix A2pl = new TurkishSuffix("A2pl");
    static SuffixFormSet A2pl_sInIz = newSet(A2pl, "+sInIz"); // gel-ecek-siniz
    static SuffixFormSet A2pl_sAnIzA = newSet(A2pl, "sAnIzA"); // gel-senize
    static SuffixFormSet A2pl_nIz = newSet(A2pl, "nIz"); // gel-di-niz
    static SuffixFormSet A2pl_yIn = newSet(A2pl, "+yIn"); // gel-me-yin

    static TurkishSuffix A3pl = new TurkishSuffix("A3pl");
    static SuffixFormSet A3pl_lAr = newSet(A3pl, "lAr"); // gel-ecek-ler
    static SuffixFormSet A3pl_sInlAr = newSet(A3pl, "sInlAr"); // gel-sinler

    static TurkishSuffix Become = new TurkishSuffix("Become");
    static SuffixFormSet Become_lAs = newSet(Become, "lAş");

    static TurkishSuffix Aor = new TurkishSuffix("Aor");
    static SuffixFormSet Aor_Ir = newSet(Aor, "+Ir"); //gel-ir
    static SuffixFormSet Aor_Ar = newSet(Aor, "+Ar"); //ser-er
    static SuffixFormSet Aor_z = newSet(Aor, "z"); // gel-me-z
    static SuffixFormSet Aor_EMPTY = newSet("Aor_EMPTY", Aor, ""); // gel-me--yiz

    static TurkishSuffix Prog = new TurkishSuffix("Prog");
    static SuffixFormSet Prog_Iyor = newSet(Prog, "+Iyor");
    static SuffixFormSet Prog_mAktA = newSet(Prog, "mAktA");

    static TurkishSuffix Fut = new TurkishSuffix("Fut");
    static SuffixFormSet Fut_yAcAk = newSet(Fut, "+yAcAk");
    static SuffixFormSet Fut_yAcAg = newSet(Fut, "+yAcAğ");

    static TurkishSuffix Past = new TurkishSuffix("Past");
    static SuffixFormSet Past_dI = newSet(Past, ">dI");

    static TurkishSuffix Evid = new TurkishSuffix("Evid");
    static SuffixFormSet Evid_mIs = newSet(Evid, "mIş");

    static TurkishSuffix Neg = new TurkishSuffix("Neg");
    static SuffixFormSet Neg_mA = newSet(Neg, "mA"); //gel-me
    static SuffixFormSet Neg_m = newSet(Neg, "m"); // gel-m-iyor

    static TurkishSuffix Cond = new TurkishSuffix("Cond");
    static SuffixFormSet Cond_ysA = newSet(Cond, "+ysA");

    static TurkishSuffix Necess = new TurkishSuffix("Necess");
    static SuffixFormSet Necess_mAlI = newSet(Necess, "mAlI");

    static TurkishSuffix Opt = new TurkishSuffix("Opt");
    static SuffixFormSet Opt_yA = newSet(Opt, "+yA");

    static TurkishSuffix Pass = new TurkishSuffix("Pass");
    static SuffixFormSet Pass_In = newSet(Pass, "+In");
    static SuffixFormSet Pass_nIl = newSet(Pass, "+nIl");
    static SuffixFormSet Pass_Il = newSet(Pass, "Il");

    static TurkishSuffix Caus = new TurkishSuffix("Caus");
    static SuffixFormSet Caus_t = newSet(Caus, "t");
    static SuffixFormSet Caus_tIr = newSet(Pass, ">dIr");

    static TurkishSuffix Recip = new TurkishSuffix("Recip");
    static SuffixFormSet Recip_yIs = newSet(Recip, "+yIş");
    static SuffixFormSet Recip_Is = newSet(Recip, "+Iş");

    static TurkishSuffix Reflex = new TurkishSuffix("Reflex");
    static SuffixFormSet Reflex_In = newSet(Reflex, "+In");

    static TurkishSuffix Abil = new TurkishSuffix("Abil");
    static SuffixFormSet Abil_yAbil = newSet(Abil, "+yAbil");
    static SuffixFormSet Abil_yA = newSet(Abil, "+yA");

    static TurkishSuffix Cop = new TurkishSuffix("Cop");
    static SuffixFormSet Cop_dIr = newSet(Cop, ">dIr");

    static TurkishSuffix PastCop = new TurkishSuffix("PastCop");
    static SuffixFormSet PastCop_ydI = newSet(PastCop, "+ydI");

    static TurkishSuffix EvidCop = new TurkishSuffix("EvidCop");
    static SuffixFormSet EvidCop_ymIs = newSet(EvidCop, "+ymIş");

    static TurkishSuffix CondCop = new TurkishSuffix("CondCop");
    static SuffixFormSet CondCop_ysA = newSet(CondCop, "+ysA");

    static TurkishSuffix While = new TurkishSuffix("While");
    static SuffixFormSet While_ken = newSet(While, "+yken");

    static TurkishSuffix AfterDoing = new TurkishSuffix("AfterDoing");
    static SuffixFormSet AfterDoing_yIncA = newSet(AfterDoing, "+yIncA");


    public static TurkishSuffix NounRoot = new TurkishSuffix("NounRoot");
    public static SuffixFormSet Noun_Main = newSet("Noun_Main", NounRoot, "");
    public static SuffixFormSet Noun_Exp_C = newSet("Noun_Exp_C", NounRoot, "");
    public static SuffixFormSet Noun_Exp_V = newSet("Noun_Exp_V", NounRoot, "");
    public static SuffixFormSet Noun_Comp_P3sg = newSet("Noun_Comp_P3sg", NounRoot, "");
    public static SuffixFormSet Noun_Comp_P3sg_Root = newSet("Noun_Comp_P3sg_Root", NounRoot, "");


    public static TurkishSuffix VerbRoot = new TurkishSuffix("VerbRoot");
    public static SuffixFormSet Verb_Main = newSet("Verb_Main", VerbRoot, "");
    public static SuffixFormSet Verb_Aor_Ar = newSet("Verb_Aor_Ar", VerbRoot, "");
    public static SuffixFormSet Verb_Prog_Drop = newSet("Verb_Prog_Drop", VerbRoot, "");
    public static SuffixFormSet Verb_Prog_NotDrop = newSet("Verb_Prog_NotDrop", VerbRoot, "");
    public static SuffixFormSet Verb_Vow_Drop = newSet("Verb_Vow_Drop", VerbRoot, "");
    public static SuffixFormSet Verb_Vow_NotDrop = newSet("Verb_Vow_NotDrop", VerbRoot, "");
    public static SuffixFormSet Verb_Exp_C = newSet("Verb_Exp_C", VerbRoot, "");
    public static SuffixFormSet Verb_Exp_V = newSet("Verb_Exp_V", VerbRoot, "");

    public static SuffixFormSet Verb_Ye = newSet("Verb_Ye", VerbRoot, "");
    public static SuffixFormSet Verb_Yi = newSet("Verb_Yi", VerbRoot, "");


    public static SuffixFormSet Verb_De = newSet("Verb_De", VerbRoot, "");
    public static SuffixFormSet Verb_Di = newSet("Verb_Di", VerbRoot, "");


    public static TurkishSuffix PronounRoot = new TurkishSuffix("Pronoun");
    public static SuffixFormSet Pron_Main = newSet(PronounRoot, "");
    public static SuffixFormSet Pron_BenSen = newSet("Pron_BenSen", PronounRoot, "");
    public static SuffixFormSet Pron_BanSan = newSet("Pron_BanSan", PronounRoot, "");


    public static final SuffixFormSet[] CASE_FORMS = {Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_nI, Inst_ylA};
    public static final SuffixFormSet[] POSSESSIVE_FORMS = {P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    public static final SuffixFormSet[] PERSON_FORMS_N = {A1sg_yIm, A2sg_sIn, A3sg_EMPTY, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    public static final SuffixFormSet[] COPULAR_FORMS = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken};
    public static final SuffixFormSet[] TENSE_DEFAULT_FORMS = {Prog_Iyor, Prog_mAktA, Fut_yAcAg, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir};

    static Map<String, SuffixFormSet> suffixSets = new HashMap<String, SuffixFormSet>();

    static SuffixFormSet newSet(TurkishSuffix suffix, String generation) {
        String id = suffix + "_" + generation;
        return newSet(id, suffix, generation);
    }

    static SuffixFormSet newSet(String id, TurkishSuffix suffix, String generation) {
        if (suffixSets.containsKey(id))
            throw new IllegalArgumentException("There is already a suffix set with same id:" + id);
        SuffixFormSet newSet = new SuffixFormSet(id, suffix, generation);
        suffixSets.put(id, newSet);
        return newSet;
    }

    public SuffixFormSet getRootSuffixFormSet(PrimaryPos pos) {
        switch (pos) {
            case Noun:
                return Noun_Main;
            case Verb:
                return Verb_Main;
        }
        return Noun_Main;
    }

    public Iterable<SuffixFormSet> getSets() {
        return suffixSets.values();
    }

    private static Map<String, TurkishSuffix> suffixMap = new HashMap<String, TurkishSuffix>();

    public TurkishSuffixes() {

        Noun_Main.succ(CASE_FORMS, COPULAR_FORMS, PERSON_FORMS_N)
                .succ(Pl_lAr, Dim_cIg, Dim_cIk, Dim_cAgIz, With_lI, Without_sIz);
        Noun_Main.succ(Pl_lAr);
        Noun_Exp_C.succ(Loc_dA, Abl_dAn, Inst_ylA, P3pl_lArI, A2sg_sIn, A2pl_sInIz, A3pl_lAr, With_lI, Without_sIz)
                .succ(COPULAR_FORMS);
        Noun_Exp_V.succ(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz);
        Noun_Comp_P3sg.succ(COPULAR_FORMS)
                .succ(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .succ(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz);
        Noun_Comp_P3sg_Root.succ(With_lI, Without_sIz);

        Verb_Main.succ(Prog_Iyor, Prog_mAktA, Fut_yAcAg, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir)
                .succ(Neg_mA, Neg_m, Abil_yAbil, Abil_yA, Pass_In, Caus_tIr, AfterDoing_yIncA);
        Verb_Aor_Ar.succ(Verb_Main.getSuccessors()).remove(Aor_Ir).succ(Aor_Ar);
        Verb_Vow_Drop.succ(Pass_Il);
        Verb_Vow_NotDrop.succ(Verb_Main.getSuccessors()).remove(Pass_Il);
        Verb_Prog_Drop.succ(Prog_Iyor);
        Verb_Prog_NotDrop.succ(Verb_Main.getSuccessors()).remove(Prog_Iyor);

        Verb_Ye.succ(Verb_Main.getSuccessors()).remove(Prog_Iyor, Fut_yAcAg, Fut_yAcAk, Opt_yA);
        Verb_Yi.succ(Opt_yA, Fut_yAcAg, Fut_yAcAk, AfterDoing_yIncA);
        // modification rule does not apply for some suffixes for "demek". like deyip, not diyip
        Verb_De.succ(Verb_Main.getSuccessors()).remove(Prog_Iyor, Fut_yAcAg, Fut_yAcAk, Opt_yA, AfterDoing_yIncA);
        Verb_Di.succ(Opt_yA, Fut_yAcAg, Fut_yAcAk);

        Verb_Exp_V.succ(Opt_yA, Fut_yAcAg, Fut_yAcAg, Aor_Ar, Prog_Iyor);
        Verb_Exp_C.succ(Verb_Main.getSuccessors()).remove(Verb_Exp_V.getSuccessors()).remove(Aor_Ir);

        Pron_Main.succ(CASE_FORMS);
        Pron_BenSen.succ(CASE_FORMS).remove(Dat_yA);
        Pron_BanSan.succ(Dat_yA);

        Pl_lAr.succ(CASE_FORMS, COPULAR_FORMS)
                .succ(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz);

        P1sg_Im.succ(CASE_FORMS, COPULAR_FORMS).succ(A2sg_sIn, A2pl_sInIz);
        P2sg_In.succ(CASE_FORMS, COPULAR_FORMS).succ(A1sg_yIm, A1pl_yIz);
        P3sg_sI.succ(COPULAR_FORMS)
                .succ(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .succ(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz);
        P3pl_lArI.succ(P3sg_sI.getSuccessors());

        Rel_ki.succ(COPULAR_FORMS, PERSON_FORMS_N).succ(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA);
        Dat_yA.succ(COPULAR_FORMS);
        Dat_nA.succ(COPULAR_FORMS);

        Loc_dA.succ(COPULAR_FORMS);
        Loc_ndA.succ(COPULAR_FORMS);

        Abl_dAn.succ(COPULAR_FORMS);
        Abl_ndAn.succ(COPULAR_FORMS);

        Gen_nIn.succ(COPULAR_FORMS).succ(Rel_ki);

        Dim_cIg.succ(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz);
        Dim_cIk.succ(Loc_dA, Abl_dAn, Inst_ylA, P3pl_lArI, A2sg_sIn, A2pl_sInIz, A3pl_lAr).succ(COPULAR_FORMS);
        Dim_cAgIz.succ(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N);

        With_lI.succ(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N).succ(Pl_lAr);
        Without_sIz.succ(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N).succ(Pl_lAr);

        PastCop_ydI.succ(PERSON_FORMS_N);
        EvidCop_ymIs.succ(PERSON_FORMS_N);
        CondCop_ysA.succ(PERSON_FORMS_N);

        Neg_mA.succ(Aor_z, Aor_EMPTY, Fut_yAcAk, Fut_yAcAg, Past_dI, Evid_mIs, Cond_ysA, Abil_yAbil, Necess_mAlI);
        Neg_m.succ(Prog_Iyor);

        Aor_Ar.succ(PERSON_FORMS_N, COPULAR_FORMS).succ(Cond_ysA);
        Aor_Ir.succ(PERSON_FORMS_N, COPULAR_FORMS).succ(Cond_ysA);
        Aor_z.succ(COPULAR_FORMS).succ(A3sg_sIn, Cond_ysA);
        Aor_EMPTY.succ(A1sg_m, A3sg_EMPTY).succ(Cond_ysA);

        Prog_Iyor.succ(PERSON_FORMS_N, COPULAR_FORMS).succ(Cond_ysA);
        Prog_mAktA.succ(PERSON_FORMS_N, COPULAR_FORMS).succ(Cond_ysA);

    }
}
