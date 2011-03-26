package zemberek3.lexicon;

import java.util.*;

import static zemberek3.lexicon.TurkishSuffixFormId.*;

public class TurkishSuffixes {

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
                return getFormSet(Noun_Main);
            case Verb:
                return getFormSet(Verb_Main);
        }
        return getFormSet(Noun_Main);
    }

    private static Map<String, TurkishSuffix> suffixMap = new HashMap<String, TurkishSuffix>();
    private static Map<String, SuffixFormSet> suffixFormSetMap = new HashMap<String, SuffixFormSet>();

    private void initializeSuffixes() {
        for (TurkishSuffixId tsid : TurkishSuffixId.values()) {
            suffixMap.put(tsid.name(), new TurkishSuffix(tsid.name()));
        }
    }

    public SuffixFormSet getFormSet(TurkishSuffixFormId id) {
        return suffixFormSetMap.get(id.name());
    }

    private void succ(TurkishSuffixFormId id, Iterable<SuffixFormSet>... iterables) {
        SuffixFormSet set = getFormSet(id);
        for (Iterable<SuffixFormSet> iterable : iterables) {
            set.succ(iterable);
        }
    }

    private void remove(TurkishSuffixFormId id, Iterable<SuffixFormSet>... iterables) {
        SuffixFormSet set = getFormSet(id);
        for (Iterable<SuffixFormSet> iterable : iterables) {
            set.remove(iterable);
        }
    }

    private static void initializeSuffixeFormSets() {
        for (TurkishSuffixFormId tsfid : TurkishSuffixFormId.values()) {
            suffixFormSetMap.put(tsfid.name(), new SuffixFormSet(tsfid.name(), suffixMap.get(tsfid.suffixId.name()), tsfid.generationWord));
        }
    }

    private static Iterable<SuffixFormSet> it(TurkishSuffixFormId... ids) {
        ArrayList<SuffixFormSet> sets = new ArrayList<SuffixFormSet>();
        for (TurkishSuffixFormId id : ids) {
            sets.add(suffixFormSetMap.get(id.name()));
        }
        return sets;
    }

    private static Iterable<SuffixFormSet> it(TurkishSuffixFormId[]... idArr) {
        ArrayList<SuffixFormSet> sets = new ArrayList<SuffixFormSet>();
        for (TurkishSuffixFormId[] forIds : idArr) {
            for (TurkishSuffixFormId id : forIds) {
                sets.add(suffixFormSetMap.get(id.name()));
            }
        }
        return sets;
    }

    public TurkishSuffixes() {
        initializeSuffixes();
        initializeSuffixeFormSets();
        succ(Noun_Main,
                it(CASE_FORMS, COPULAR_FORMS, PERSON_FORMS_N),
                it(Pl_lAr, Dim_cIg, Dim_cIk, Dim_cAgIz, With_lI, Without_sIz));
        succ(Noun_Exp_C,
                it(Loc_dA, Abl_dAn, Inst_ylA, P3pl_lArI, A2sg_sIn, A2pl_sInIz, A3pl_lAr, With_lI, Without_sIz),
                it(COPULAR_FORMS));
        succ(Noun_Exp_V,
                it(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz));
        succ(Noun_Comp_p3sg,
                it(COPULAR_FORMS),
                it(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA),
                it(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz));
        succ(Noun_Comp_p3sg_Root,
                it(With_lI, Without_sIz));

        succ(Verb_Main,
                it(Prog_Iyor, Prog_mAktA, Fut_yAcAg, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir),
                it(Neg_mA, Neg_m, Abil_yAbil, Abil_yA, Pass_In, Caus_tIr, AfterDoing_yIncA));

        Iterable<SuffixFormSet> verbMainSuccessors = getFormSet(Verb_Main).getSuccessors();
        succ(Verb_Aor_Ar, verbMainSuccessors, it(Aor_Ar));
        remove(Verb_Aor_Ar, it(Aor_Ir));

        succ(Verb_Vow_Drop, it(Pass_Il));
        succ(Verb_Vow_NotDrop, verbMainSuccessors);
        remove(Verb_Vow_NotDrop, it(Pass_Il));
        succ(Verb_Prog_Drop, it(Prog_Iyor));
        succ(Verb_Prog_NotDrop, verbMainSuccessors);
        remove(Verb_Prog_NotDrop, it(Prog_Iyor));
        succ(Verb_Ye, verbMainSuccessors);
        remove(Verb_Ye, it(Prog_Iyor, Fut_yAcAg, Fut_yAcAk, Opt_yA));
        succ(Verb_De, verbMainSuccessors);
        remove(Verb_De, it(Prog_Iyor, Fut_yAcAg, Fut_yAcAk, Opt_yA, AfterDoing_yIncA));

        succ(Verb_Yi, it(Opt_yA, Fut_yAcAg, Fut_yAcAk, AfterDoing_yIncA));
        succ(Verb_Di, it(Opt_yA, Fut_yAcAg, Fut_yAcAk));
        succ(Verb_Exp_V, it(Opt_yA, Fut_yAcAg, Fut_yAcAg, Aor_Ar, Prog_Iyor));
        succ(Verb_Exp_C, verbMainSuccessors);
        remove(Verb_Exp_C, getFormSet(Verb_Exp_V).getSuccessors(), it(Aor_Ir));

        succ(Pronoun_Main, it(CASE_FORMS));
        succ(Pronoun_BenSen, it(CASE_FORMS));
        remove(Pronoun_BenSen, it(Dat_yA));
        succ(Pronoun_BanSan, it(Dat_yA));

        succ(Pl_lAr,
                it(CASE_FORMS, COPULAR_FORMS),
                it(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz));

        succ(P1sg_Im, it(CASE_FORMS, COPULAR_FORMS), it(A2sg_sIn, A2pl_sInIz));
        succ(P2sg_In, it(CASE_FORMS, COPULAR_FORMS), it(A1sg_yIm, A1pl_yIz));
        succ(P3sg_sI, it(COPULAR_FORMS)
                , it(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                , it(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz));
        succ(P3pl_lArI, getFormSet(P3sg_sI).getSuccessors());

        succ(Rel_ki, it(COPULAR_FORMS, PERSON_FORMS_N), it(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA));
        succ(Dat_yA, it(COPULAR_FORMS));
        succ(Dat_nA, it(COPULAR_FORMS));

        succ(Loc_dA, it(COPULAR_FORMS));
        succ(Loc_ndA, it(COPULAR_FORMS));

        succ(Abl_dAn, it(COPULAR_FORMS));
        succ(Abl_ndAn, it(COPULAR_FORMS));

        succ(Gen_nIn, it(COPULAR_FORMS), it(Rel_ki));
        succ(Dim_cIg, it(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz));
        succ(Dim_cIk, it(Loc_dA, Abl_dAn, Inst_ylA, P3pl_lArI, A2sg_sIn, A2pl_sInIz, A3pl_lAr), it(COPULAR_FORMS));
        succ(Dim_cAgIz, it(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N));

        succ(With_lI, it(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N), it(Pl_lAr));
        succ(Without_sIz, it(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N), it(Pl_lAr));

        succ(PastCop_ydI, it(PERSON_FORMS_N));
        succ(EvidCop_ymIs, it(PERSON_FORMS_N));
        succ(CondCop_ysA, it(PERSON_FORMS_N));

        succ(Neg_mA, it(Aor_z, Aor_EMPTY, Fut_yAcAk, Fut_yAcAg, Past_dI, Evid_mIs, Cond_ysA, Abil_yAbil, Necess_mAlI));
        succ(Neg_m, it(Prog_Iyor));

        succ(Aor_Ar, it(PERSON_FORMS_N, COPULAR_FORMS), it(Cond_ysA));
        succ(Aor_Ir, it(PERSON_FORMS_N, COPULAR_FORMS), it(Cond_ysA));
        succ(Aor_z, it(COPULAR_FORMS), it(A3sg_sIn, Cond_ysA));
        succ(Aor_EMPTY, it(A1sg_m, A3sg_EMPTY), it(Cond_ysA));

        succ(Prog_Iyor, it(PERSON_FORMS_N, COPULAR_FORMS), it(Cond_ysA));
        succ(Prog_mAktA, it(PERSON_FORMS_N, COPULAR_FORMS), it(Cond_ysA));

    }
}
