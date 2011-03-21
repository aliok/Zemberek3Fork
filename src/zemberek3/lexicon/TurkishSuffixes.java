package zemberek3.lexicon;

import java.util.*;

public class TurkishSuffixes {

    static TurkishSuffix Pl = new TurkishSuffix("Pl");
    static SuffixNode Pl_lAr = new SuffixNode(Pl, "lAr");

    static TurkishSuffix Dat = new TurkishSuffix("Dat");
    static SuffixNode Dat_yA = new SuffixNode(Dat, "+yA");
    static SuffixNode Dat_nA = new SuffixNode(Dat, "nA");

    static TurkishSuffix Loc = new TurkishSuffix("Loc");
    static SuffixNode Loc_dA = new SuffixNode(Loc, ">dA");
    static SuffixNode Loc_ndA = new SuffixNode(Loc, "ndA");

    static TurkishSuffix Abl = new TurkishSuffix("Abl");
    static SuffixNode Abl_dAn = new SuffixNode(Abl, ">dAn");
    static SuffixNode Abl_ndAn = new SuffixNode(Abl, "ndAn");

    static TurkishSuffix Gen = new TurkishSuffix("Gen");
    static SuffixNode Gen_nIn = new SuffixNode(Gen, "+nIn");

    static TurkishSuffix Acc = new TurkishSuffix("Acc");
    static SuffixNode Acc_yI = new SuffixNode(Acc, "+yI");
    static SuffixNode Acc_nI = new SuffixNode(Acc, "nI");

    static TurkishSuffix Inst = new TurkishSuffix("Inst");
    static SuffixNode Inst_ylA = new SuffixNode(Inst, "+ylA");

    static TurkishSuffix P1sg = new TurkishSuffix("P1sg");
    static SuffixNode P1sg_Im = new SuffixNode(P1sg, "+Im");

    static TurkishSuffix P2sg = new TurkishSuffix("P2sg");
    static SuffixNode P2sg_In = new SuffixNode(P2sg, "+In");

    static TurkishSuffix P3sg = new TurkishSuffix("P3sg");
    static SuffixNode P3sg_sI = new SuffixNode(P3sg, "+sI");

    static TurkishSuffix P1pl = new TurkishSuffix("P1pl");
    static SuffixNode P1pl_ImIz = new SuffixNode(P1pl, "+ImIz");

    static TurkishSuffix P2pl = new TurkishSuffix("P2pl");
    static SuffixNode P2pl_InIz = new SuffixNode(P2pl, "+InIz");

    static TurkishSuffix P3pl = new TurkishSuffix("P3pl");
    static SuffixNode P3pl_lArI = new SuffixNode(P3pl, "lArI");

    static TurkishSuffix Dim = new TurkishSuffix("Dim");
    static SuffixNode Dim_cIk = new SuffixNode(Dim, ">cIk");
    static SuffixNode Dim_cIg = new SuffixNode(Dim, ">cIğ");
    static SuffixNode Dim_cAgIz = new SuffixNode(Dim, "cAğIz");

    static TurkishSuffix With = new TurkishSuffix("With");
    static SuffixNode With_lI = new SuffixNode(With, "lI");

    static TurkishSuffix Without = new TurkishSuffix("Without");
    static SuffixNode Without_sIz = new SuffixNode(Without, "sIz");

    static TurkishSuffix Rel = new TurkishSuffix("Rel");
    static SuffixNode Rel_ki = new SuffixNode(Rel, "ki"); // masa-da-ki

    static TurkishSuffix A1sg = new TurkishSuffix("A1sg");
    static SuffixNode A1sg_yIm = new SuffixNode(A1sg, "+yIm"); // gel-e-yim
    static SuffixNode A1sg_m = new SuffixNode(A1sg, "m"); // gel-se-m

    static TurkishSuffix A2sg = new TurkishSuffix("A2sg");
    static SuffixNode A2sg_sIn = new SuffixNode(A2sg, "+sIn"); // gel-ecek-sin
    static SuffixNode A2sg_n = new SuffixNode(A2sg, "n"); // gel-di-n
    static SuffixNode A2sg_sAnA = new SuffixNode(A2sg, "sAnA"); //gel-sene
    static SuffixNode A2sg_EMPTY = new SuffixNode(A2sg, ""); // gel-

    static TurkishSuffix A3sg = new TurkishSuffix("A3sg");
    static SuffixNode A3sg_EMPTY = new SuffixNode(A3sg, ""); // gel-di-
    static SuffixNode A3sg_sIn = new SuffixNode(A3sg, "sIn"); // gel-sin

    static TurkishSuffix A1pl = new TurkishSuffix("A1pl");
    static SuffixNode A1pl_yIz = new SuffixNode(A1pl, "+yIz"); // geliyor-uz
    static SuffixNode A1pl_k = new SuffixNode(A1pl, "k"); // gel-di-k
    static SuffixNode A1pl_lIm = new SuffixNode(A1pl, "lIm"); // gel-e-lim

    static TurkishSuffix A2pl = new TurkishSuffix("A2pl");
    static SuffixNode A2pl_sInIz = new SuffixNode(A2pl, "+sInIz"); // gel-ecek-siniz
    static SuffixNode A2pl_sAnIzA = new SuffixNode(A2pl, "sAnIzA"); // gel-senize
    static SuffixNode A2pl_nIz = new SuffixNode(A2pl, "nIz"); // gel-di-niz
    static SuffixNode A2pl_yIn = new SuffixNode(A2pl, "+yIn"); // gel-me-yin

    static TurkishSuffix A3pl = new TurkishSuffix("A3pl");
    static SuffixNode A3pl_lAr = new SuffixNode(A3pl, "lAr"); // gel-ecek-ler
    static SuffixNode A3pl_sInlAr = new SuffixNode(A3pl, "sInlAr"); // gel-sinler

    static TurkishSuffix Become = new TurkishSuffix("Become");
    static SuffixNode Become_lAs = new SuffixNode(Become, "lAş");

    static TurkishSuffix Aor = new TurkishSuffix("Aor");
    static SuffixNode Aor_Ir = new SuffixNode(Aor, "+Ir"); //gel-ir
    static SuffixNode Aor_Ar = new SuffixNode(Aor, "+Ar"); //ser-er
    static SuffixNode Aor_z = new SuffixNode(Aor, "z"); // gel-me-z
    static SuffixNode Aor_EMPTY = new SuffixNode(Aor, ""); // gel-me--yiz

    static TurkishSuffix Prog = new TurkishSuffix("Prog");
    static SuffixNode Prog_Iyor = new SuffixNode(Prog, "+Iyor");
    static SuffixNode Prog_mAktA = new SuffixNode(Prog, "mAktA");

    static TurkishSuffix Fut = new TurkishSuffix("Fut");
    static SuffixNode Fut_yAcAk = new SuffixNode(Fut, "+yAcAk");
    static SuffixNode Fut_yAcAğ = new SuffixNode(Fut, "+yAcAğ");

    static TurkishSuffix Past = new TurkishSuffix("Past");
    static SuffixNode Past_dI = new SuffixNode(Past, ">dI");

    static TurkishSuffix Evid = new TurkishSuffix("Evid");
    static SuffixNode Evid_dI = new SuffixNode(Evid, "mIş");

    static TurkishSuffix Neg = new TurkishSuffix("Neg");
    static SuffixNode Neg_mA = new SuffixNode(Neg, "mA"); //gel-me
    static SuffixNode Neg_m = new SuffixNode(Neg, "m"); // gel-m-iyor

    static TurkishSuffix Cond = new TurkishSuffix("Cond");
    static SuffixNode Cond_ysA = new SuffixNode(Cond, "+ysA");

    static TurkishSuffix Necess = new TurkishSuffix("Necess");
    static SuffixNode Necess_mAlI = new SuffixNode(Necess, "mAlI");

    static TurkishSuffix Opt = new TurkishSuffix("Opt");
    static SuffixNode Opt_yA = new SuffixNode(Opt, "+yA");

    static TurkishSuffix Pass = new TurkishSuffix("Pass");
    static SuffixNode Pass_In = new SuffixNode(Pass, "+In");
    static SuffixNode Pass_nIl = new SuffixNode(Pass, "+nIl");
    static SuffixNode Pass_Il = new SuffixNode(Pass, "Il");

    static TurkishSuffix Caus = new TurkishSuffix("Caus");
    static SuffixNode Caus_t = new SuffixNode(Caus, "t");
    static SuffixNode Caus_tIr = new SuffixNode(Pass, ">dIr");

    static TurkishSuffix Recip = new TurkishSuffix("Recip");
    static SuffixNode Recip_yIs = new SuffixNode(Recip, "+yIş");
    static SuffixNode Recip_Is = new SuffixNode(Recip, "+Iş");

    static TurkishSuffix Reflex = new TurkishSuffix("Reflex");
    static SuffixNode Reflex_In = new SuffixNode(Reflex, "+In");

    static TurkishSuffix Abil = new TurkishSuffix("Abil");
    static SuffixNode Abil_yAbil = new SuffixNode(Abil, "+yAbil");
    static SuffixNode Abil_A = new SuffixNode(Abil, "A");

    static TurkishSuffix Cop = new TurkishSuffix("Cop");
    static SuffixNode Cop_dIr = new SuffixNode(Cop, ">dIr");

    static TurkishSuffix PastCop = new TurkishSuffix("PastCop");
    static SuffixNode PastCop_ydI = new SuffixNode(PastCop, "+ydI");

    static TurkishSuffix EvidCop = new TurkishSuffix("EvidCop");
    static SuffixNode EvidCop_ymIs = new SuffixNode(EvidCop, "+ymIş");

    static TurkishSuffix CondCop = new TurkishSuffix("CondCop");
    static SuffixNode CondCop_ysA = new SuffixNode(CondCop, "+ysA");

    static TurkishSuffix While = new TurkishSuffix("While");
    static SuffixNode While_ken = new SuffixNode(While, "+yken");

    static TurkishSuffix AfterDoing = new TurkishSuffix("AfterDoing");

    public static final TurkishSuffix[] CASE = {Dat, Loc, Abl, Gen, Acc, Inst};
    public static final SuffixNode[] CASE_FORMS = {Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_nI, Inst_ylA};
    public static final TurkishSuffix[] POSSESSIVE = {P1sg, P2sg, P1pl, P2pl, P3pl};
    public static final SuffixNode[] POSSESSIVE_FORMS = {P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    public static final TurkishSuffix[] PERSON = {A1sg, A2sg, A1pl, A2pl, A3pl};
    public static final SuffixNode[] PERSON_FORMS_N = {A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    public static final TurkishSuffix[] COPULAR = {Cop, PastCop, EvidCop, CondCop, While};
    public static final SuffixNode[] COPULAR_FORMS = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken};

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

    static Map<String, SuffixNode> nodeMap = new HashMap<String, SuffixNode>();

    static void add(SuffixNode... nodes) {
        for (SuffixNode node : nodes) {
            if (nodeMap.containsKey(node.id))
                throw new IllegalArgumentException("There is already a suffix node with same id:" + node.id);
            nodeMap.put(node.id, node);
        }
    }

    public void generate() {

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

/*        Acc_yI.add(new SuffixNode(Acc, "+yI"));
        add(new SuffixNode(Acc, "nI", Rel, P3sg, P3pl));


        SuffixNode dim = new SuffixNode(Dim, ">cI~k").
                succ(CASE, COPULAR, POSSESSIVE, PERSON).
                succ(Pl, With, Without);
        add(dim);
        add(new SuffixNode(Dim, ">cağız").
                succ(dim.successors));

        add(new SuffixNode(With, "lI").
                succ(CASE, COPULAR, POSSESSIVE, PERSON).
                succ(Pl));

        add(new SuffixNode(Without, "sIz").
                succ(CASE, COPULAR, POSSESSIVE, PERSON).
                succ(Pl));

        add(new SuffixNode(Rel, "ki").succ(CASE, COPULAR, PERSON));

        add(new SuffixNode(A1sg, "+yIm").succ(Cop));
        add(new SuffixNode(A2sg, "sIn").succ(Cop));
        add(new SuffixNode(A1pl, "+yIz").succ(Cop));
        add(new SuffixNode(A2pl, "sInIz").succ(Cop));
        add(new SuffixNode(A3pl, "lAr").succ(Cop));

        add(new SuffixNode(Cop, ">dIr"));
        add(new SuffixNode(PastCop, "+y>dI").succ(PERSON));
        add(new SuffixNode(EvidCop, "+ymIş").succ(PERSON));
        add(new SuffixNode(CondCop, "+ysA").succ(PERSON));
        add(new SuffixNode(While, "+yken"));

        add(new SuffixNode(Prog, "Iyor").succ(PERSON));

        add(new SuffixNode(Neg, "mA").succ(Past, Fut, Evid, Aor));
        add(new SuffixNode(Neg, "m").succ(Prog));
   */

    }

    public static void main(String[] args) {
        TurkishSuffixes suffixes = new TurkishSuffixes();
        suffixes.generate();
        for (SuffixNode node : TurkishSuffixes.PastCop.nodes) {
            System.out.println(node.id);
        }

        System.out.println("Noun root suffix count: " + NOUN_ROOT_SUFFIXES.size());
        int i = 0;
        for (TurkishSuffix suffix : NOUN_ROOT_SUFFIXES) {
            i += suffix.nodes.size();
        }
        System.out.println("SuffixNode Count: " + i);
    }
}
