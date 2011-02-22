package zemberek3.lexicon;

public class TurkishSuffixes {
    static TurkishSuffix Pl_lAr = new TurkishSuffix("Pl_lAr");
    static TurkishSuffix Dat_yA = new TurkishSuffix("Dat_yA");;
    static TurkishSuffix Loc_dA = new TurkishSuffix("Loc_dA");
    static TurkishSuffix Abl_dAn;
    static TurkishSuffix Gen_nIn;
    static TurkishSuffix Acc_yI;
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
    static TurkishSuffix Pass_In =  new TurkishSuffix("Pass_In");
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

    static TurkishSuffix[] NOUN_CASE = { Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_yI, Inst_lA};
    static TurkishSuffix[] NOUN_POSS = {P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    static TurkishSuffix[] NOUN_PERSON = {A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    static TurkishSuffix[] COPULAR = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_yken};

    public void generate() {
        SuffixNode nodeA = new SuffixNode(Pl_lAr, "lar");
        SuffixNode nodeE = new SuffixNode(Pl_lAr, "ler", MorphemicAttribute.LastVowelFrontal);
        Pl_lAr.addStates(nodeA, nodeE).
                addSuccessor(NOUN_CASE).
                addSuccessor(COPULAR).
                addSuccessor(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz, By_cA);

        


    }


}
