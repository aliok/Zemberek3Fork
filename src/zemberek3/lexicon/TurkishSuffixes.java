package zemberek3.lexicon;

import com.google.common.collect.Lists;
import zemberek3.structure.AttributeSet;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.util.*;

import static zemberek3.structure.TurkishAlphabet.*;

public class TurkishSuffixes {

    public static final TurkishSuffix Pl_lAr = new TurkishSuffix("Pl_lAr");
    public static final TurkishSuffix Dat_yA = new TurkishSuffix("Dat_yA");
    public static final TurkishSuffix Loc_dA = new TurkishSuffix("Loc_dA");
    public static final TurkishSuffix Abl_dAn = new TurkishSuffix("Abl_dAn");
    public static final TurkishSuffix Gen_nIn = new TurkishSuffix("Gen_nIn");
    public static final TurkishSuffix Acc_yI = new TurkishSuffix("Acc_yI");
    public static final TurkishSuffix Inst_lA = new TurkishSuffix("Inst_lA");

    public static final TurkishSuffix P1sg_Im = new TurkishSuffix("P1sg_Im");
    public static final TurkishSuffix P2sg_In = new TurkishSuffix("P2sg_In");
    public static final TurkishSuffix P3sg_sI = new TurkishSuffix("P3sg_sI");
    public static final TurkishSuffix P1pl_ImIz = new TurkishSuffix("P1pl_ImIz");
    public static final TurkishSuffix P2pl_InIz = new TurkishSuffix("P2pl_InIz");
    public static final TurkishSuffix P3pl_lArI = new TurkishSuffix("P3pl_lArI");

    public static final TurkishSuffix Dim_cIk = new TurkishSuffix("Dim_cIk");
    public static final TurkishSuffix Dim_cAgIz = new TurkishSuffix("Dim_cAgIz");
    public static final TurkishSuffix With_lI = new TurkishSuffix("With_lI");
    public static final TurkishSuffix Without_sIz = new TurkishSuffix("Without_sIz");
    public static final TurkishSuffix Rel_ki = new TurkishSuffix("Rel_ki");

    public static final TurkishSuffix A1sg_yIm = new TurkishSuffix("A1sg_yIm");
    public static final TurkishSuffix A2sg_sIn = new TurkishSuffix("A2sg_sIn");
    public static final TurkishSuffix A3sg = new TurkishSuffix("A3sg");
    public static final TurkishSuffix A1pl_yIz = new TurkishSuffix("A1pl_yIz");
    public static final TurkishSuffix A2pl_sInIz = new TurkishSuffix("A2pl_sInIz");
    public static final TurkishSuffix A3pl_lAr = new TurkishSuffix("A3pl_lAr");

    public static final TurkishSuffix Become_lAs = new TurkishSuffix("Become_lAs");

    public static final TurkishSuffix Aor_Ir = new TurkishSuffix("Aor_Ir");
    public static final TurkishSuffix Prog_Iyor = new TurkishSuffix("Prog_Iyor");
    public static final TurkishSuffix Prog_mAktA = new TurkishSuffix("Prog_mAktA");
    public static final TurkishSuffix Fut_yAcAk = new TurkishSuffix("Fut_yAcAk");
    public static final TurkishSuffix Past_dI = new TurkishSuffix("Past_dI");
    public static final TurkishSuffix Evid_mIs = new TurkishSuffix("Evid_mIs");
    public static final TurkishSuffix Neg_mA = new TurkishSuffix("Neg_mA");
    public static final TurkishSuffix Cond_sa = new TurkishSuffix("Cond_sa");
    public static final TurkishSuffix Necess_mAlI = new TurkishSuffix("Necess_mAlI");
    public static final TurkishSuffix Opt_yA = new TurkishSuffix("Opt_yA");
    public static final TurkishSuffix Pass_In = new TurkishSuffix("Pass_In");
    public static final TurkishSuffix Caus_dIr = new TurkishSuffix("Caus_dIr");
    public static final TurkishSuffix Recip_yIs = new TurkishSuffix("Recip_yIs");
    public static final TurkishSuffix Reflex_In = new TurkishSuffix("Reflex_In");
    public static final TurkishSuffix Abil_yAbIl = new TurkishSuffix("Abil_yAbIl");
    public static final TurkishSuffix Cont_yAdur = new TurkishSuffix("Cont_yAdur");


    public static final TurkishSuffix Cop_dIr = new TurkishSuffix("Cop_dIr");
    public static final TurkishSuffix PastCop_ydI = new TurkishSuffix("PastCop_ydI");
    public static final TurkishSuffix EvidCop_ymIs = new TurkishSuffix("EvidCop_ymIs");
    public static final TurkishSuffix CondCop_ysA = new TurkishSuffix("CondCop_ysA");
    public static final TurkishSuffix While_yken = new TurkishSuffix("While_yken");

    public static final TurkishSuffix AfterDoing_yIp = new TurkishSuffix("AfterDoing_yIp");
    public static final TurkishSuffix JustAfter_yIncA = new TurkishSuffix("JustAfter_yIncA");

    public static final TurkishSuffix[] NOUN_CASE = {Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_yI, Inst_lA};
    public static final TurkishSuffix[] NOUN_POSS = {P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    public static final TurkishSuffix[] NOUN_PERSON = {A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    public static final TurkishSuffix[] COPULAR = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_yken};

    public static final List<TurkishSuffix> NOUN_ROOT_SUFFIXES = new ArrayList<TurkishSuffix>();

    static {
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(NOUN_CASE));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(NOUN_POSS));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(COPULAR));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(NOUN_PERSON));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(Dim_cIk, Dim_cAgIz, With_lI, Without_sIz));
    }

    public List<TurkishSuffix> getSuffixesForPos(PrimaryPos pos) {
        switch (pos) {
            case Noun:
                return NOUN_ROOT_SUFFIXES;
        }
        return Collections.emptyList();
    }
    
    SuffixFormGenerator generator;

    public void generate() {

        Pl_lAr.addNodes(generator.generateNodes(Pl_lAr, "lAr"))
                .addSuccessors(NOUN_CASE, COPULAR)
                .addSuccessors(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz);

        Dat_yA.addNodes(generator.generateNodes(Dat_yA, "+yA"))
                .addSuccessors(COPULAR);

        Dat_yA.addNodes(generator.generateNodes(Dat_yA, "nA", Rel_ki, P3sg_sI, P3pl_lArI));

        Loc_dA.addNodes(generator.generateNodes(Loc_dA, ">dA")).addSuccessors(COPULAR);
        Loc_dA.addNodes(generator.generateNodes(Loc_dA, "ndA", Rel_ki, P3sg_sI, P3pl_lArI));

        Abl_dAn.addNodes(generator.generateNodes(Abl_dAn, ">dAn")).addSuccessors(COPULAR);
        Abl_dAn.addNodes(generator.generateNodes(Abl_dAn, "ndAn", Rel_ki, P3sg_sI, P3pl_lArI));

        Gen_nIn.addNodes(generator.generateNodes(Gen_nIn, "+nIn"))
                .addSuccessors(COPULAR)
                .addSuccessors(Rel_ki);

        Acc_yI.addNodes(generator.generateNodes(Acc_yI, "+yI"))
                .addNodes(generator.generateNodes(Abl_dAn, "nI", Rel_ki, P3sg_sI, P3pl_lArI));

        P1sg_Im.addNodes(generator.generateNodes(P1sg_Im, "Im")).
                addSuccessors(NOUN_CASE, COPULAR).
                addSuccessors(A2sg_sIn, A2pl_sInIz);

        P2sg_In.addNodes(generator.generateNodes(P2sg_In, "In")).
                addSuccessors(NOUN_CASE, COPULAR).
                addSuccessors(A1sg_yIm, A1pl_yIz);

        P3sg_sI.addNodes(generator.generateNodes(P3sg_sI, "+sI")).
                addSuccessors(NOUN_CASE, COPULAR).
                addSuccessors(A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz);


        Dim_cIk.addNodes(generator.generateNodes(Dim_cIk, ">cI~k")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl_lAr, With_lI, Without_sIz);

        Dim_cAgIz.addNodes(generator.generateNodes(Dim_cAgIz, ">cağız")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl_lAr, With_lI, Without_sIz);

        With_lI.addNodes(generator.generateNodes(With_lI, "lI")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl_lAr);

        Without_sIz.addNodes(generator.generateNodes(Without_sIz, "sIz")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl_lAr);

        Rel_ki.addNodes(generator.generateNodes(Rel_ki, "ki")).addSuccessors(NOUN_CASE, COPULAR, NOUN_PERSON);

        A1sg_yIm.addNodes(generator.generateNodes(A1sg_yIm, "+yIm")).addSuccessors(Cop_dIr);
        A2sg_sIn.addNodes(generator.generateNodes(A2sg_sIn, "sIn")).addSuccessors(Cop_dIr);
        A1pl_yIz.addNodes(generator.generateNodes(A1pl_yIz, "+yIz")).addSuccessors(Cop_dIr);
        A2pl_sInIz.addNodes(generator.generateNodes(A2pl_sInIz, "sInIz")).addSuccessors(Cop_dIr);
        A3pl_lAr.addNodes(generator.generateNodes(A3pl_lAr, "lAr")).addSuccessors(Cop_dIr);

        Cop_dIr.addNodes(generator.generateNodes(Cop_dIr, ">dIr"));
        PastCop_ydI.addNodes(generator.generateNodes(PastCop_ydI, "+y>dI")).addSuccessors(NOUN_PERSON);
        EvidCop_ymIs.addNodes(generator.generateNodes(EvidCop_ymIs, "+ymIş")).addSuccessors(NOUN_PERSON);
        CondCop_ysA.addNodes(generator.generateNodes(CondCop_ysA, "+ysA")).addSuccessors(NOUN_PERSON);
        While_yken.addNodes(generator.generateNodes(While_yken, "+yken"));
    }

    public static void main(String[] args) {
        TurkishSuffixes suffixes = new TurkishSuffixes();
        suffixes.generate();
        for (SuffixNode node : TurkishSuffixes.PastCop_ydI.nodes) {
            System.out.println(node.content);
        }

        System.out.println("Noun root suffix count: " + NOUN_ROOT_SUFFIXES.size());
        int i = 0;
        for (TurkishSuffix suffix : NOUN_ROOT_SUFFIXES) {
            i += suffix.nodes.size();
        }
        System.out.println("SuffixNode Count: " + i);
    }
}
