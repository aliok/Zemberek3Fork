package zemberek3.lexicon;

import java.util.*;

public class TurkishSuffixes {

    static TurkishSuffix Pl = new TurkishSuffix("Pl");
    static SuffixFormSet Pl_lAr = new SuffixFormSet(Pl, "lAr");

    static TurkishSuffix Dat = new TurkishSuffix("Dat");
    static SuffixFormSet Dat_yA = new SuffixFormSet(Dat, "+yA");
    static SuffixFormSet Dat_nA = new SuffixFormSet(Dat, "nA");

    static TurkishSuffix Loc = new TurkishSuffix("Loc");
    static SuffixFormSet Loc_dA = new SuffixFormSet(Loc, ">dA");
    static SuffixFormSet Loc_ndA = new SuffixFormSet(Loc, "ndA");

    static TurkishSuffix Abl = new TurkishSuffix("Abl");
    static SuffixFormSet Abl_dAn = new SuffixFormSet(Abl, ">dAn");
    static SuffixFormSet Abl_ndAn = new SuffixFormSet(Abl, "ndAn");

    static TurkishSuffix Gen = new TurkishSuffix("Gen");
    static SuffixFormSet Gen_nIn = new SuffixFormSet(Gen, "+nIn");

    static TurkishSuffix Acc = new TurkishSuffix("Acc");
    static SuffixFormSet Acc_yI = new SuffixFormSet(Acc, "+yI");
    static SuffixFormSet Acc_nI = new SuffixFormSet(Acc, "nI");

    static TurkishSuffix Inst = new TurkishSuffix("Inst");
    static SuffixFormSet Inst_ylA = new SuffixFormSet(Inst, "+ylA");

    static TurkishSuffix P1sg = new TurkishSuffix("P1sg");
    static SuffixFormSet P1sg_Im = new SuffixFormSet(P1sg, "+Im");

    static TurkishSuffix P2sg = new TurkishSuffix("P2sg");
    static SuffixFormSet P2sg_In = new SuffixFormSet(P2sg, "+In");

    static TurkishSuffix P3sg = new TurkishSuffix("P3sg");
    static SuffixFormSet P3sg_sI = new SuffixFormSet(P3sg, "+sI");

    static TurkishSuffix P1pl = new TurkishSuffix("P1pl");
    static SuffixFormSet P1pl_ImIz = new SuffixFormSet(P1pl, "+ImIz");

    static TurkishSuffix P2pl = new TurkishSuffix("P2pl");
    static SuffixFormSet P2pl_InIz = new SuffixFormSet(P2pl, "+InIz");

    static TurkishSuffix P3pl = new TurkishSuffix("P3pl");
    static SuffixFormSet P3pl_lArI = new SuffixFormSet(P3pl, "lArI");

    static TurkishSuffix Dim = new TurkishSuffix("Dim");
    static SuffixFormSet Dim_cIk = new SuffixFormSet(Dim, ">cIk");
    static SuffixFormSet Dim_cIg = new SuffixFormSet(Dim, ">cIğ");
    static SuffixFormSet Dim_cAgIz = new SuffixFormSet(Dim, "cAğIz");

    static TurkishSuffix With = new TurkishSuffix("With");
    static SuffixFormSet With_lI = new SuffixFormSet(With, "lI");

    static TurkishSuffix Without = new TurkishSuffix("Without");
    static SuffixFormSet Without_sIz = new SuffixFormSet(Without, "sIz");

    static TurkishSuffix Rel = new TurkishSuffix("Rel");
    static SuffixFormSet Rel_ki = new SuffixFormSet(Rel, "ki"); // masa-da-ki

    static TurkishSuffix A1sg = new TurkishSuffix("A1sg");
    static SuffixFormSet A1sg_yIm = new SuffixFormSet(A1sg, "+yIm"); // gel-e-yim
    static SuffixFormSet A1sg_m = new SuffixFormSet(A1sg, "m"); // gel-se-m

    static TurkishSuffix A2sg = new TurkishSuffix("A2sg");
    static SuffixFormSet A2sg_sIn = new SuffixFormSet(A2sg, "+sIn"); // gel-ecek-sin
    static SuffixFormSet A2sg_n = new SuffixFormSet(A2sg, "n"); // gel-di-n
    static SuffixFormSet A2sg_sAnA = new SuffixFormSet(A2sg, "sAnA"); //gel-sene
    static SuffixFormSet A2sg_EMPTY = new SuffixFormSet(A2sg, ""); // gel-

    static TurkishSuffix A3sg = new TurkishSuffix("A3sg");
    static SuffixFormSet A3sg_EMPTY = new SuffixFormSet(A3sg, ""); // gel-di-
    static SuffixFormSet A3sg_sIn = new SuffixFormSet(A3sg, "sIn"); // gel-sin

    static TurkishSuffix A1pl = new TurkishSuffix("A1pl");
    static SuffixFormSet A1pl_yIz = new SuffixFormSet(A1pl, "+yIz"); // geliyor-uz
    static SuffixFormSet A1pl_k = new SuffixFormSet(A1pl, "k"); // gel-di-k
    static SuffixFormSet A1pl_lIm = new SuffixFormSet(A1pl, "lIm"); // gel-e-lim

    static TurkishSuffix A2pl = new TurkishSuffix("A2pl");
    static SuffixFormSet A2pl_sInIz = new SuffixFormSet(A2pl, "+sInIz"); // gel-ecek-siniz
    static SuffixFormSet A2pl_sAnIzA = new SuffixFormSet(A2pl, "sAnIzA"); // gel-senize
    static SuffixFormSet A2pl_nIz = new SuffixFormSet(A2pl, "nIz"); // gel-di-niz
    static SuffixFormSet A2pl_yIn = new SuffixFormSet(A2pl, "+yIn"); // gel-me-yin

    static TurkishSuffix A3pl = new TurkishSuffix("A3pl");
    static SuffixFormSet A3pl_lAr = new SuffixFormSet(A3pl, "lAr"); // gel-ecek-ler
    static SuffixFormSet A3pl_sInlAr = new SuffixFormSet(A3pl, "sInlAr"); // gel-sinler

    static TurkishSuffix Become = new TurkishSuffix("Become");
    static SuffixFormSet Become_lAs = new SuffixFormSet(Become, "lAş");

    static TurkishSuffix Aor = new TurkishSuffix("Aor");
    static SuffixFormSet Aor_Ir = new SuffixFormSet(Aor, "+Ir"); //gel-ir
    static SuffixFormSet Aor_Ar = new SuffixFormSet(Aor, "+Ar"); //ser-er
    static SuffixFormSet Aor_z = new SuffixFormSet(Aor, "z"); // gel-me-z
    static SuffixFormSet Aor_EMPTY = new SuffixFormSet(Aor, ""); // gel-me--yiz

    static TurkishSuffix Prog = new TurkishSuffix("Prog");
    static SuffixFormSet Prog_Iyor = new SuffixFormSet(Prog, "+Iyor");
    static SuffixFormSet Prog_mAktA = new SuffixFormSet(Prog, "mAktA");

    static TurkishSuffix Fut = new TurkishSuffix("Fut");
    static SuffixFormSet Fut_yAcAk = new SuffixFormSet(Fut, "+yAcAk");
    static SuffixFormSet Fut_yAcAg = new SuffixFormSet(Fut, "+yAcAğ");

    static TurkishSuffix Past = new TurkishSuffix("Past");
    static SuffixFormSet Past_dI = new SuffixFormSet(Past, ">dI");

    static TurkishSuffix Evid = new TurkishSuffix("Evid");
    static SuffixFormSet Evid_mIs = new SuffixFormSet(Evid, "mIş");

    static TurkishSuffix Neg = new TurkishSuffix("Neg");
    static SuffixFormSet Neg_mA = new SuffixFormSet(Neg, "mA"); //gel-me
    static SuffixFormSet Neg_m = new SuffixFormSet(Neg, "m"); // gel-m-iyor

    static TurkishSuffix Cond = new TurkishSuffix("Cond");
    static SuffixFormSet Cond_ysA = new SuffixFormSet(Cond, "+ysA");

    static TurkishSuffix Necess = new TurkishSuffix("Necess");
    static SuffixFormSet Necess_mAlI = new SuffixFormSet(Necess, "mAlI");

    static TurkishSuffix Opt = new TurkishSuffix("Opt");
    static SuffixFormSet Opt_yA = new SuffixFormSet(Opt, "+yA");

    static TurkishSuffix Pass = new TurkishSuffix("Pass");
    static SuffixFormSet Pass_In = new SuffixFormSet(Pass, "+In");
    static SuffixFormSet Pass_nIl = new SuffixFormSet(Pass, "+nIl");
    static SuffixFormSet Pass_Il = new SuffixFormSet(Pass, "Il");

    static TurkishSuffix Caus = new TurkishSuffix("Caus");
    static SuffixFormSet Caus_t = new SuffixFormSet(Caus, "t");
    static SuffixFormSet Caus_tIr = new SuffixFormSet(Pass, ">dIr");

    static TurkishSuffix Recip = new TurkishSuffix("Recip");
    static SuffixFormSet Recip_yIs = new SuffixFormSet(Recip, "+yIş");
    static SuffixFormSet Recip_Is = new SuffixFormSet(Recip, "+Iş");

    static TurkishSuffix Reflex = new TurkishSuffix("Reflex");
    static SuffixFormSet Reflex_In = new SuffixFormSet(Reflex, "+In");

    static TurkishSuffix Abil = new TurkishSuffix("Abil");
    static SuffixFormSet Abil_yAbil = new SuffixFormSet(Abil, "+yAbil");
    static SuffixFormSet Abil_yA = new SuffixFormSet(Abil, "+yA");

    static TurkishSuffix Cop = new TurkishSuffix("Cop");
    static SuffixFormSet Cop_dIr = new SuffixFormSet(Cop, ">dIr");

    static TurkishSuffix PastCop = new TurkishSuffix("PastCop");
    static SuffixFormSet PastCop_ydI = new SuffixFormSet(PastCop, "+ydI");

    static TurkishSuffix EvidCop = new TurkishSuffix("EvidCop");
    static SuffixFormSet EvidCop_ymIs = new SuffixFormSet(EvidCop, "+ymIş");

    static TurkishSuffix CondCop = new TurkishSuffix("CondCop");
    static SuffixFormSet CondCop_ysA = new SuffixFormSet(CondCop, "+ysA");

    static TurkishSuffix While = new TurkishSuffix("While");
    static SuffixFormSet While_ken = new SuffixFormSet(While, "+yken");

    static TurkishSuffix AfterDoing = new TurkishSuffix("AfterDoing");
    static SuffixFormSet AfterDoing_yIncA = new SuffixFormSet(AfterDoing, "+yIncA");


    public static TurkishSuffix NounRoot = new TurkishSuffix("NounRoot");
    public static SuffixFormSet Noun_Main = new SuffixFormSet(NounRoot, "");
    public static SuffixFormSet Noun_Exp_C = new SuffixFormSet(NounRoot, "");
    public static SuffixFormSet Noun_Exp_V = new SuffixFormSet(NounRoot, "");
    public static SuffixFormSet Noun_Comp_p3sg = new SuffixFormSet(NounRoot, "");
    public static SuffixFormSet Noun_Comp_p3sg_Root = new SuffixFormSet(NounRoot, "");


    public static TurkishSuffix VerbRoot = new TurkishSuffix("VerbRoot");
    public static SuffixFormSet Verb_Main = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Aor_Ar = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Prog_Drop = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Prog_NotDrop = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Vow_Drop = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Vow_NotDrop = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Exp_C = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Exp_V = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_De = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Ye = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Di = new SuffixFormSet(VerbRoot, "");
    public static SuffixFormSet Verb_Yi = new SuffixFormSet(VerbRoot, "");

    public static TurkishSuffix PronounRoot = new TurkishSuffix("Pronoun");
    public static SuffixFormSet Pronoun_Main = new SuffixFormSet(PronounRoot, "");
    public static SuffixFormSet Pronoun_BenSen = new SuffixFormSet(PronounRoot, "");
    public static SuffixFormSet Pronoun_BanSan = new SuffixFormSet(PronounRoot, "");

    public static final TurkishSuffix[] CASE = {Dat, Loc, Abl, Gen, Acc, Inst};
    public static final SuffixFormSet[] CASE_FORMS = {Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_nI, Inst_ylA};
    public static final TurkishSuffix[] POSSESSIVE = {P1sg, P2sg, P1pl, P2pl, P3pl};
    public static final SuffixFormSet[] POSSESSIVE_FORMS = {P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    public static final TurkishSuffix[] PERSON = {A1sg, A2sg, A1pl, A2pl, A3pl};
    public static final SuffixFormSet[] PERSON_FORMS_N = {A1sg_yIm, A2sg_sIn, A3sg_EMPTY, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    public static final TurkishSuffix[] COPULAR = {Cop, PastCop, EvidCop, CondCop, While};
    public static final SuffixFormSet[] COPULAR_FORMS = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken};
    public static final SuffixFormSet[] TENSE_DEFAULT_FORMS = {Prog_Iyor, Prog_mAktA, Fut_yAcAg, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir};

    public static final List<TurkishSuffix> NOUN_ROOT_SUFFIXES = new ArrayList<TurkishSuffix>();

    static {
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(CASE));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(POSSESSIVE));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(COPULAR));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(PERSON));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(Dim, With, Without));
    }

    public List<TurkishSuffix> getSuffixesForPos(PrimaryPos pos) {
        switch (pos) {
            case Noun:
                return NOUN_ROOT_SUFFIXES;
        }
        return Collections.emptyList();
    }

    static Map<String, SuffixFormSet> nodeMap = new HashMap<String, SuffixFormSet>();

    static void add(SuffixFormSet... sets) {
        for (SuffixFormSet set : sets) {
            if (nodeMap.containsKey(set.id))
                throw new IllegalArgumentException("There is already a suffix set with same id:" + set.id);
            nodeMap.put(set.id, set);
        }
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

    public TurkishSuffixes() {

     //   Noun_Main.succ(CASE_FORMS, COPULAR_FORMS, PERSON_FORMS_N)
     //           .succ(Pl_lAr, Dim_cIg, Dim_cIk, Dim_cAgIz, With_lI, Without_sIz);
        Noun_Main.succ(Pl_lAr);
        Noun_Exp_C.succ(Loc_dA, Abl_dAn, Inst_ylA, P3pl_lArI, A2sg_sIn, A2pl_sInIz, A3pl_lAr, With_lI, Without_sIz)
                .succ(COPULAR_FORMS);
        Noun_Exp_V.succ(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz);
        Noun_Comp_p3sg.succ(COPULAR_FORMS)
                .succ(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .succ(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz);
        Noun_Comp_p3sg_Root.succ(With_lI, Without_sIz);

        Verb_Main.succ(Prog_Iyor, Prog_mAktA, Fut_yAcAg, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir)
                .succ(Neg_mA, Neg_m, Abil_yAbil, Abil_yA, Pass_In, Caus_tIr, AfterDoing_yIncA);
        Verb_Aor_Ar.succ(Verb_Main.getSuccessors()).remove(Aor_Ir).succ(Aor_Ar);
        Verb_Vow_Drop.succ(Pass_Il);
        Verb_Vow_NotDrop.succ(Verb_Main.getSuccessors()).remove(Pass_Il);
        Verb_Prog_Drop.succ(Prog_Iyor);
        Verb_Prog_NotDrop.succ(Verb_Main.getSuccessors()).remove(Prog_Iyor);
        Verb_Ye.succ(Verb_Main.getSuccessors()).remove(Prog_Iyor, Fut_yAcAg, Fut_yAcAk, Opt_yA);
        Verb_De.succ(Verb_Main.getSuccessors()).remove(Prog_Iyor, Fut_yAcAg, Fut_yAcAk, Opt_yA, AfterDoing_yIncA);
        Verb_Yi.succ(Opt_yA, Fut_yAcAg, Fut_yAcAk, AfterDoing_yIncA);
        Verb_Di.succ(Opt_yA, Fut_yAcAg, Fut_yAcAk);
        Verb_Exp_V.succ(Opt_yA, Fut_yAcAg, Fut_yAcAg, Aor_Ar, Prog_Iyor);
        Verb_Exp_C.succ(Verb_Main.getSuccessors()).remove(Verb_Exp_V.getSuccessors()).remove(Aor_Ir);

        Pronoun_Main.succ(CASE_FORMS);
        Pronoun_BenSen.succ(CASE_FORMS).remove(Dat_yA);
        Pronoun_BanSan.succ(Dat_yA);


        Pl_lAr.succ(CASE_FORMS, COPULAR_FORMS)
                .succ(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz);

        // TODO: possesion + person = noun-verb derivation
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

    public static void main(String[] args) {
        TurkishSuffixes suffixes = new TurkishSuffixes();
        for (SuffixFormSet set : TurkishSuffixes.PastCop.formSets) {
            System.out.println(set.id);
        }
        System.out.println("Noun root suffix count: " + NOUN_ROOT_SUFFIXES.size());
        int i = 0;
        for (TurkishSuffix suffix : NOUN_ROOT_SUFFIXES) {
            i += suffix.formSets.size();
        }
        System.out.println("SuffixFormSet Count: " + i);
    }
}
