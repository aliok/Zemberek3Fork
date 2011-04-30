package zemberek3.lexicon;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TurkishSuffixes {

    public static Suffix Pl = new Suffix("Pl");
    public static SuffixFormSet Pl_lAr = new SuffixFormSet(Pl, "lAr");

    public static Suffix Dat = new Suffix("Dat");
    public static SuffixFormSet Dat_yA = new SuffixFormSet(Dat, "+yA");
    public static SuffixFormSet Dat_nA = new SuffixFormSet(Dat, "nA");

    public static Suffix Loc = new Suffix("Loc");
    public static SuffixFormSet Loc_dA = new SuffixFormSet(Loc, ">dA");
    public static SuffixFormSet Loc_ndA = new SuffixFormSet(Loc, "ndA");

    public static Suffix Abl = new Suffix("Abl");
    public static SuffixFormSet Abl_dAn = new SuffixFormSet(Abl, ">dAn");
    public static SuffixFormSet Abl_ndAn = new SuffixFormSet(Abl, "ndAn");

    public static Suffix Gen = new Suffix("Gen");
    public static SuffixFormSet Gen_nIn = new SuffixFormSet(Gen, "+nIn");

    public static Suffix Acc = new Suffix("Acc");
    public static SuffixFormSet Acc_yI = new SuffixFormSet(Acc, "+yI");
    public static SuffixFormSet Acc_nI = new SuffixFormSet(Acc, "nI");

    public static Suffix Inst = new Suffix("Inst");
    public static SuffixFormSet Inst_ylA = new SuffixFormSet(Inst, "+ylA");

    public static Suffix P1sg = new Suffix("P1sg");
    public static SuffixFormSet P1sg_Im = new SuffixFormSet(P1sg, "Im");

    public static Suffix P2sg = new Suffix("P2sg");
    public static SuffixFormSet P2sg_In = new SuffixFormSet(P2sg, "In");

    public static Suffix P3sg = new Suffix("P3sg");
    public static SuffixFormSet P3sg_sI = new SuffixFormSet(P3sg, "+sI");

    public static Suffix P1pl = new Suffix("P1pl");
    public static SuffixFormSet P1pl_ImIz = new SuffixFormSet(P1pl, "ImIz");

    public static Suffix P2pl = new Suffix("P2pl");
    public static SuffixFormSet P2pl_InIz = new SuffixFormSet(P2pl, "InIz");

    public static Suffix P3pl = new Suffix("P3pl");
    public static SuffixFormSet P3pl_lArI = new SuffixFormSet(P3pl, "lArI");

    public static Suffix Dim = new Suffix("Dim");
    public static SuffixFormSet Dim_cIk = new SuffixFormSet(Dim, ">cIk");
    public static SuffixFormSet Dim_cIg = new SuffixFormSet(Dim, ">cIğ", false);

    public static Suffix Dim2 = new Suffix("Dim2");
    public static SuffixFormSet Dim2_cAgIz = new SuffixFormSet(Dim2, "cAğIz");

    public static Suffix With = new Suffix("With");
    public static SuffixFormSet With_lI = new SuffixFormSet(With, "lI");

    public static Suffix Without = new Suffix("Without");
    public static SuffixFormSet Without_sIz = new SuffixFormSet(Without, "sIz");

    public static Suffix Rel = new Suffix("Rel");
    public static SuffixFormSet Rel_ki = new SuffixFormSet(Rel, "ki"); // masa-da-ki
    public static SuffixFormSet Rel_kI = new SuffixFormSet(Rel, "kI"); // dünkü

    public static Suffix A1sg = new Suffix("A1sg");
    public static SuffixFormSet A1sg_yIm = new SuffixFormSet(A1sg, "+yIm"); // gel-e-yim
    public static SuffixFormSet A1sg_m = new SuffixFormSet(A1sg, "m"); // gel-se-m
    public static SuffixFormSet A1sg_EMPTY = new SuffixFormSet("A1sg_EMPTY", A1sg, ""); // ben

    public static Suffix A2sg = new Suffix("A2sg");
    public static SuffixFormSet A2sg_sIn = new SuffixFormSet(A2sg, "sIn"); // gel-ecek-sin
    public static SuffixFormSet A2sg_n = new SuffixFormSet(A2sg, "n"); // gel-di-n
    public static SuffixFormSet A2sg_EMPTY = new SuffixFormSet("A2sg_EMPTY", A2sg, ""); // gel, sen,..

    public static Suffix A2sg2 = new Suffix("A2sg2");
    public static SuffixFormSet A2sg2_sAnA = new SuffixFormSet(A2sg2, "sAnA"); //gel-sene

    public static Suffix A2sg3 = new Suffix("A2sg3");
    public static SuffixFormSet A2sg3_yInIz = new SuffixFormSet(A2sg3, "+yInIz"); //gel-iniz

    public static Suffix A3sg = new Suffix("A3sg");
    public static SuffixFormSet A3sg_EMPTY = new SuffixFormSet(A3sg, ""); // gel-di-, o-
    public static SuffixFormSet A3sg_sIn = new SuffixFormSet(A3sg, "sIn"); // gel-sin

    public static Suffix A1pl = new Suffix("A1pl");
    public static SuffixFormSet A1pl_yIz = new SuffixFormSet(A1pl, "+yIz"); // geliyor-uz
    public static SuffixFormSet A1pl_k = new SuffixFormSet(A1pl, "k"); // gel-di-k
    public static SuffixFormSet A1pl_lIm = new SuffixFormSet(A1pl, "lIm"); // gel-e-lim
    public static SuffixFormSet A1pl_EMPTY = new SuffixFormSet("A1pl_EMPTY", A1pl, ""); // biz

    public static Suffix A2pl = new Suffix("A2pl");
    public static SuffixFormSet A2pl_sInIz = new SuffixFormSet(A2pl, "sInIz"); // gel-ecek-siniz
    public static SuffixFormSet A2pl_nIz = new SuffixFormSet(A2pl, "nIz"); // gel-di-niz
    public static SuffixFormSet A2pl_yIn = new SuffixFormSet(A2pl, "+yIn"); // gel-me-yin
    public static SuffixFormSet A2pl_EMPTY = new SuffixFormSet("A2pl_EMPTY", A2pl, ""); // gel-e-lim

    public static Suffix A2pl2 = new Suffix("A2pl2");
    public static SuffixFormSet A2pl2_sAnIzA = new SuffixFormSet(A2pl2, "sAnIzA"); // gel-senize

    public static Suffix A3pl = new Suffix("A3pl");
    public static SuffixFormSet A3pl_lAr = new SuffixFormSet(A3pl, "lAr"); // gel-ecek-ler
    public static SuffixFormSet A3pl_sInlAr = new SuffixFormSet(A3pl, "sInlAr"); // gel-sinler

    public static Suffix Agt = new Suffix("Agt");
    public static SuffixFormSet Agt_cI = new SuffixFormSet(Agt, ">cI"); // araba-cı. Converts to another Noun.
    public static SuffixFormSet Agt_yIcI = new SuffixFormSet(Agt, "+yIcI"); // otur-ucu. converts to both Noun and Adj

    public static Suffix Ness = new Suffix("Ness");
    public static SuffixFormSet Ness_lIk = new SuffixFormSet(Ness, "lIk");
    public static SuffixFormSet Ness_lIg = new SuffixFormSet(Ness, "lIğ", false);

    public static Suffix Become = new Suffix("Become");
    public static SuffixFormSet Become_lAs = new SuffixFormSet(Become, "lAş");

    public static Suffix Resemb = new Suffix("Resemb");
    public static SuffixFormSet Resemb_ImsI = new SuffixFormSet(Resemb, "ImsI"); // udunumsu
    public static SuffixFormSet Resemb_msI = new SuffixFormSet(Resemb, "+msI"); // odunsu

    public static Suffix Related = new Suffix("Related");
    public static SuffixFormSet Related_sAl = new SuffixFormSet(Related, "sAl");

    public static Suffix Aor = new Suffix("Aor");
    public static SuffixFormSet Aor_Ir = new SuffixFormSet(Aor, "+Ir"); //gel-ir
    public static SuffixFormSet Aor_Ar = new SuffixFormSet(Aor, "+Ar"); //ser-er
    public static SuffixFormSet Aor_z = new SuffixFormSet(Aor, "z"); // gel-me-z
    public static SuffixFormSet Aor_EMPTY = new SuffixFormSet("Aor_EMPTY", Aor, "", false); // gel-me--yiz

    public static Suffix AorPart = new Suffix("AorPart"); // convert to an Adjective
    public static SuffixFormSet AorPart_Ir = new SuffixFormSet(AorPart, "+Ir"); //gel-ir
    public static SuffixFormSet AorPart_Ar = new SuffixFormSet(AorPart, "+Ar"); //ser-er
    public static SuffixFormSet AorPart_z = new SuffixFormSet(AorPart, "z"); // gel-me-z

    public static Suffix Prog = new Suffix("Prog");
    public static SuffixFormSet Prog_Iyor = new SuffixFormSet(Prog, "Iyor");

    public static Suffix Prog2 = new Suffix("Prog2");
    public static SuffixFormSet Prog2_mAktA = new SuffixFormSet(Prog2, "mAktA");

    public static Suffix Fut = new Suffix("Fut");
    public static SuffixFormSet Fut_yAcAk = new SuffixFormSet(Fut, "+yAcAk");
    public static SuffixFormSet Fut_yAcAg = new SuffixFormSet(Fut, "+yAcAğ", false);

    public static Suffix FutPart = new Suffix("FutPart");
    public static SuffixFormSet FutPart_yAcAk = new SuffixFormSet(FutPart, "+yAcAk");
    public static SuffixFormSet FutPart_yAcAg = new SuffixFormSet(FutPart, "+yAcAğ", false);

    public static Suffix Past = new Suffix("Past");
    public static SuffixFormSet Past_dI = new SuffixFormSet(Past, ">dI");

    public static Suffix PastPart = new Suffix("PastPart");
    public static SuffixFormSet PastPart_dIk = new SuffixFormSet(PastPart, ">dIk");
    public static SuffixFormSet PastPart_dIg = new SuffixFormSet(PastPart, ">dIğ", false);

    public static Suffix Evid = new Suffix("Evid");
    public static SuffixFormSet Evid_mIs = new SuffixFormSet(Evid, "mIş");

    public static Suffix EvidPart = new Suffix("EvidPart");
    public static SuffixFormSet EvidPart_mIs = new SuffixFormSet(EvidPart, "mIş");

    public static Suffix PresPart = new Suffix("PresPart");
    public static SuffixFormSet PresPart_yAn = new SuffixFormSet(PresPart, "+yAn");

    public static Suffix Neg = new Suffix("Neg");
    public static SuffixFormSet Neg_mA = new SuffixFormSet(Neg, "mA"); //gel-me
    public static SuffixFormSet Neg_m = new SuffixFormSet(Neg, "m", false); // gel-m-iyor

    public static Suffix Cond = new Suffix("Cond");
    public static SuffixFormSet Cond_sA = new SuffixFormSet(Cond, "sA");

    public static Suffix Necess = new Suffix("Necess");
    public static SuffixFormSet Necess_mAlI = new SuffixFormSet(Necess, "mAlI");

    public static Suffix Opt = new Suffix("Opt");
    public static SuffixFormSet Opt_yA = new SuffixFormSet(Opt, "+yA");

    public static Suffix Pass = new Suffix("Pass");
    public static SuffixFormSet Pass_In = new SuffixFormSet(Pass, "+In");
    public static SuffixFormSet Pass_nIl = new SuffixFormSet(Pass, "+nIl");

    public static Suffix Caus = new Suffix("Caus");
    public static SuffixFormSet Caus_t = new SuffixFormSet(Caus, "t");
    public static SuffixFormSet Caus_tIr = new SuffixFormSet(Caus, ">dIr");

    public static Suffix Imp = new Suffix("Imp");
    public static SuffixFormSet Imp_EMPTY = new SuffixFormSet(Imp, "");

    public static Suffix Des = new Suffix("Des");
    public static SuffixFormSet Des_sA = new SuffixFormSet(Des, "sA");

    public static Suffix Recip = new Suffix("Recip");
    public static SuffixFormSet Recip_Is = new SuffixFormSet(Recip, "+Iş");

    public static Suffix Reflex = new Suffix("Reflex");
    public static SuffixFormSet Reflex_In = new SuffixFormSet(Reflex, "+In");

    public static Suffix Abil = new Suffix("Abil");
    public static SuffixFormSet Abil_yAbil = new SuffixFormSet(Abil, "+yAbil");
    public static SuffixFormSet Abil_yA = new SuffixFormSet(Abil, "+yA", false);

    public static Suffix Cop = new Suffix("Cop");
    public static SuffixFormSet Cop_dIr = new SuffixFormSet(Cop, ">dIr");

    public static Suffix PastCop = new Suffix("PastCop");
    public static SuffixFormSet PastCop_ydI = new SuffixFormSet(PastCop, "+y>dI");

    public static Suffix EvidCop = new Suffix("EvidCop");
    public static SuffixFormSet EvidCop_ymIs = new SuffixFormSet(EvidCop, "+ymIş");

    public static Suffix CondCop = new Suffix("CondCop");
    public static SuffixFormSet CondCop_ysA = new SuffixFormSet(CondCop, "+ysA");

    public static Suffix While = new Suffix("While");
    public static SuffixFormSet While_ken = new SuffixFormSet(While, "+yken");

    public static Suffix Equ = new Suffix("Equ");
    public static SuffixFormSet Equ_cA = new SuffixFormSet(Equ, ">cA");
    public static SuffixFormSet Equ_ncA = new SuffixFormSet(Equ, "ncA");

    public static Suffix NotState = new Suffix("NotState");
    public static SuffixFormSet NotState_mAzlIk = new SuffixFormSet(NotState, "mAzlIk");
    public static SuffixFormSet NotState_mAzlIg = new SuffixFormSet(NotState, "mAzlIğ", false);

    public static Suffix ActOf = new Suffix("ActOf");
    public static SuffixFormSet ActOf_mAcA = new SuffixFormSet(ActOf, "mAcA");

    public static Suffix AsIf = new Suffix("AsIf");
    public static SuffixFormSet AsIf_cAsInA = new SuffixFormSet(AsIf, ">cAsInA");

    // Converts to an Adverb.
    public static Suffix AsLongAs = new Suffix("AsLongAs");
    public static SuffixFormSet AsLongAs_dIkcA = new SuffixFormSet(AsLongAs, ">dIkçA");

    public static Suffix When = new Suffix("When");
    public static SuffixFormSet When_yIncA = new SuffixFormSet(When, "+yIncA");

    // It also may have "worthy of doing" meaning after passive. Converts to an Adjective.
    public static Suffix FeelLike = new Suffix("FeelLike");
    public static SuffixFormSet FeelLike_yAsI = new SuffixFormSet(FeelLike, "+yAsI");

    // Converts to an Adverb.
    public static Suffix SinceDoing = new Suffix("SinceDoing");
    public static SuffixFormSet SinceDoing_yAlI = new SuffixFormSet(SinceDoing, "+yAlI");

    // Converts to an Adverb.
    public static Suffix ByDoing = new Suffix("ByDoing");
    public static SuffixFormSet ByDoing_yArAk = new SuffixFormSet(ByDoing, "+yArAk");

    // Converts to an Adverb.
    public static Suffix WithoutDoing = new Suffix("WithoutDoing");
    public static SuffixFormSet WithoutDoing_mAdAn = new SuffixFormSet(WithoutDoing, "mAdAn");

    public static Suffix WithoutDoing2 = new Suffix("WithoutDoing2");
    public static SuffixFormSet WithoutDoing2_mAksIzIn = new SuffixFormSet(WithoutDoing2, "mAksIzIn");

    // Converts to an Adverb.
    public static Suffix AfterDoing = new Suffix("AfterDoing");
    public static SuffixFormSet AfterDoing_yIp = new SuffixFormSet(AfterDoing, "+yIp");

    public static Suffix UnableToDo = new Suffix("UnableToDo");
    public static SuffixFormSet UnableToDo_yAmAdAn = new SuffixFormSet(UnableToDo, "+yAmAdAn");

    public static Suffix InsteadOfDoing = new Suffix("InsteadOfDoing");
    public static SuffixFormSet InsteadOfDoing_mAktAnsA = new SuffixFormSet(InsteadOfDoing, "mAktAnsA");

    // Converts to an Adverb.
    public static Suffix KeepDoing = new Suffix("KeepDoing");
    public static SuffixFormSet KeepDoing_yAgor = new SuffixFormSet(KeepDoing, "+yAgör");

    public static Suffix KeepDoing2 = new Suffix("KeepDoing2");
    public static SuffixFormSet KeepDoing2_yAdur = new SuffixFormSet(KeepDoing2, "+yAdur");

    public static Suffix EverSince = new Suffix("EverSince");
    public static SuffixFormSet EverSince_yAgel = new SuffixFormSet(EverSince, "+yAgel");

    public static Suffix Almost = new Suffix("Almost");
    public static SuffixFormSet Almost_yAyAz = new SuffixFormSet(Almost, "+yAyaz");

    public static Suffix Hastily = new Suffix("Hastily");
    public static SuffixFormSet Hastily_yIver = new SuffixFormSet(Hastily, "+yIver");

    public static Suffix Stay = new Suffix("Stay");
    public static SuffixFormSet Stay_yAkal = new SuffixFormSet(Stay, "+yAkal");

    public static Suffix Inf1 = new Suffix("Inf1");
    public static SuffixFormSet Inf1_mAk = new SuffixFormSet(Inf1, "mAk");

    public static Suffix Inf2 = new Suffix("Inf2");
    public static SuffixFormSet Inf2_mA = new SuffixFormSet(Inf2, "mA");

    public static Suffix Inf3 = new Suffix("Inf3");
    public static SuffixFormSet Inf3_yIs = new SuffixFormSet(Inf3, "+yIş");

    public static Suffix Ly = new Suffix("Ly");
    public static SuffixFormSet Ly_cA = new SuffixFormSet(Ly, ">cA");

    public static Suffix Quite = new Suffix("Quite");
    public static SuffixFormSet Quite_cA = new SuffixFormSet(Quite, ">cA");

    public static Suffix NounRoot = new Suffix("NounRoot");
    public static SuffixFormSet Noun_Main = new SuffixFormSet("Noun_Main", NounRoot, "");
    public static SuffixFormSet Noun_Exp_C = new SuffixFormSet("Noun_Exp_C", NounRoot, "");
    public static SuffixFormSet Noun_Exp_V = new SuffixFormSet("Noun_Exp_V", NounRoot, "");
    public static SuffixFormSet Noun_Comp_P3sg = new SuffixFormSet("Noun_Comp_P3sg", NounRoot, "");
    public static SuffixFormSet Noun_Comp_P3sg_Root = new SuffixFormSet("Noun_Comp_P3sg_Root", NounRoot, "");

    public static Suffix AdjRoot = new Suffix("AdjRoot");
    public static SuffixFormSet Adj_Main = new SuffixFormSet("Adj_Main", AdjRoot, "");
    public static SuffixFormSet Adj_Exp_C = new SuffixFormSet("Adj_Exp_C", AdjRoot, "");
    public static SuffixFormSet Adj_Exp_V = new SuffixFormSet("Adj_Exp_V", AdjRoot, "");

    public static Suffix AdvRoot = new Suffix("AdvRoot");
    public static SuffixFormSet Adv_Main = new SuffixFormSet("Adv_Main", AdvRoot, "");

    public static Suffix InterjRoot = new Suffix("InterjRoot");
    public static SuffixFormSet Interj_Main = new SuffixFormSet("Interj_Main", InterjRoot, "");

    public static Suffix ConjRoot = new Suffix("ConjRoot");
    public static SuffixFormSet Conj_Main = new SuffixFormSet("Conj_Main", ConjRoot, "");

    public static Suffix NumeralRoot = new Suffix("NumeralRoot");
    public static SuffixFormSet Numeral_Main = new SuffixFormSet("Numeral_Main", NumeralRoot, "");

    public static Suffix DetRoot = new Suffix("DetRoot");
    public static SuffixFormSet Det_Main = new SuffixFormSet("Det_Main", DetRoot, "");

    public static Suffix ProperNounRoot = new Suffix("ProperNounRoot");
    public static SuffixFormSet ProperNoun_Main = new SuffixFormSet("ProperNoun_Main", ProperNounRoot, "");

    public static Suffix VerbRoot = new Suffix("VerbRoot");
    public static SuffixFormSet Verb_Main = new SuffixFormSet("Verb_Main", VerbRoot, "");
    public static SuffixFormSet Verb_Prog_Drop = new SuffixFormSet("Verb_Prog_Drop", VerbRoot, "");
    public static SuffixFormSet Verb_Exp_C = new SuffixFormSet("Verb_Exp_C", VerbRoot, "");
    public static SuffixFormSet Verb_Exp_V = new SuffixFormSet("Verb_Exp_V", VerbRoot, "");

    public static Suffix PersPronRoot = new Suffix("PersPronRoot");
    public static SuffixFormSet PersPron_Main = new SuffixFormSet(PersPronRoot, "");
    public static SuffixFormSet PersPron_BenSen = new SuffixFormSet("PersPron_BenSen", PersPronRoot, "");
    public static SuffixFormSet PersPron_BanSan = new SuffixFormSet("PersPron_BanSan", PersPronRoot, "");

    public static final SuffixFormSet[] CASE_FORMS = {Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_yI, Inst_ylA};
    public static final SuffixFormSet[] POSSESSIVE_FORMS = {P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    public static final SuffixFormSet[] PERSON_FORMS_N = {A1sg_yIm, A2sg_sIn, A3sg_EMPTY, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    public static final SuffixFormSet[] PERSON_FORMS_COP = {A1sg_m, A2sg_n, A3sg_EMPTY, A1pl_k, A2pl_nIz, A3pl_lAr};
    public static final SuffixFormSet[] COPULAR_FORMS = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken};

    public SuffixProvider getSuffixProvider() {
        TurkishSuffixProvider provider = new TurkishSuffixProvider();
        provider.addForms(
                Pl_lAr, Dat_yA, Dat_nA, Loc_dA, Loc_ndA, Abl_dAn, Abl_ndAn, Gen_nIn,
                Acc_yI, Acc_nI, Inst_ylA, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz,
                P2pl_InIz, P3pl_lArI, Dim_cIk, Dim_cIg, Dim2_cAgIz, With_lI,
                Without_sIz, Rel_ki, A1sg_yIm, A1sg_m, A1sg_EMPTY, A2sg_sIn,
                A2sg_n, A2sg_EMPTY, A2sg2_sAnA, A2sg3_yInIz, A3sg_EMPTY, A3sg_sIn, A1pl_yIz,
                A1pl_k, A1pl_lIm, A1pl_EMPTY, A2pl_sInIz, A2pl_nIz, A2pl_yIn, A2pl_EMPTY,
                A2pl2_sAnIzA, A3pl_lAr, A3pl_sInlAr, Agt_cI, Agt_yIcI, Ness_lIk, Ness_lIg,
                Become_lAs, Resemb_ImsI, Resemb_msI, Related_sAl, Aor_Ir, Aor_Ar, Aor_z, Des_sA,
                Aor_EMPTY, AorPart_Ir, AorPart_Ar, AorPart_z, Prog_Iyor, Prog2_mAktA, Fut_yAcAk,
                Fut_yAcAg, FutPart_yAcAk, FutPart_yAcAg, Past_dI, PastPart_dIk, PastPart_dIg,
                Evid_mIs, EvidPart_mIs, PresPart_yAn, Neg_mA, Neg_m, Cond_sA,
                Necess_mAlI, Opt_yA, Pass_In, Pass_nIl, Caus_t,
                Caus_tIr, Imp_EMPTY, Recip_Is, Reflex_In, Abil_yAbil, Abil_yA, Cop_dIr,
                PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken, NotState_mAzlIk, NotState_mAzlIg, ActOf_mAcA,
                AsIf_cAsInA, AsLongAs_dIkcA, When_yIncA, FeelLike_yAsI, SinceDoing_yAlI, ByDoing_yArAk, WithoutDoing_mAdAn,
                WithoutDoing2_mAksIzIn, AfterDoing_yIp, UnableToDo_yAmAdAn, InsteadOfDoing_mAktAnsA,
                KeepDoing_yAgor, KeepDoing2_yAdur, EverSince_yAgel,
                Almost_yAyAz, Hastily_yIver, Stay_yAkal, Inf1_mAk, Inf2_mA, Inf3_yIs, Ly_cA,
                Quite_cA, Equ_cA,Equ_ncA,
                Noun_Main, Noun_Exp_C, Noun_Exp_V, Noun_Comp_P3sg, Noun_Comp_P3sg_Root,
                Adj_Main, Adj_Exp_C, Adj_Exp_V,
                Adv_Main, Interj_Main, Verb_Main, Verb_Prog_Drop, Verb_Exp_C, Verb_Exp_V, PersPron_Main, PersPron_BenSen, PersPron_BanSan);
        return provider;
    }

    private class TurkishSuffixProvider implements SuffixProvider {

        Set<SuffixFormSet> forms = Sets.newHashSet();
        Map<String, SuffixFormSet> formSetLookup = Maps.newHashMap();
        Map<String, Suffix> suffixLookup = Maps.newHashMap();
        ArrayListMultimap<String, SuffixFormSet> formsPerSuffix = ArrayListMultimap.create(100, 2);

        public Suffix getSuffixById(String suffixId) {
            return suffixLookup.get(suffixId);
        }

        public List<SuffixFormSet> getFormsBySuffixId(String suffixId) {
            return formsPerSuffix.get(suffixId);
        }

        public SuffixFormSet getFormById(String suffixSetId) {
            return formSetLookup.get(suffixSetId);
        }

        public Iterable<SuffixFormSet> getAllForms() {
            return forms;
        }

        void addForms(SuffixFormSet... setz) {

            for (SuffixFormSet suffixFormSet : setz) {
                if (forms.contains(suffixFormSet))
                    continue;
                forms.add(suffixFormSet);
                formSetLookup.put(suffixFormSet.id, suffixFormSet);
                suffixLookup.put(suffixFormSet.getSuffix().id, suffixFormSet.getSuffix());
                formsPerSuffix.put(suffixFormSet.getSuffix().id, suffixFormSet);
            }
        }
    }

    public TurkishSuffixes() {

        Noun_Main.add(CASE_FORMS, POSSESSIVE_FORMS, COPULAR_FORMS, PERSON_FORMS_N)
                .add(Pl_lAr, Dim_cIg, Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, A3sg_EMPTY, Agt_cI, Resemb_msI,
                        Resemb_ImsI, Ness_lIg, Ness_lIk, Related_sAl, Become_lAs);
        Noun_Exp_V.add(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz, Resemb_ImsI);
        Noun_Exp_C.add(Noun_Main.getSuccSetCopy()).remove(Noun_Exp_V.getSuccSetCopy());
        Noun_Comp_P3sg.add(COPULAR_FORMS, POSSESSIVE_FORMS)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .add(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz);
        Noun_Comp_P3sg_Root.add(With_lI, Without_sIz, Agt_cI, Resemb_msI, Resemb_ImsI, Ness_lIg, Ness_lIk, Related_sAl);

        ProperNoun_Main
                .add(CASE_FORMS, POSSESSIVE_FORMS, COPULAR_FORMS, PERSON_FORMS_N)
                .add(Pl_lAr, Dim_cIg, Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, A3sg_EMPTY, Agt_cI);

        Verb_Main.add(Prog_Iyor, Prog2_mAktA, Fut_yAcAg, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir, AorPart_Ir)
                .add(Neg_mA, Neg_m, Abil_yAbil, Abil_yA, Caus_tIr, Opt_yA, Imp_EMPTY, Agt_yIcI, Des_sA)
                .add(Pass_nIl, NotState_mAzlIk, NotState_mAzlIg, ActOf_mAcA, PastPart_dIg, PastPart_dIk, EvidPart_mIs)
                .add(FutPart_yAcAg, FutPart_yAcAk, PresPart_yAn, AsLongAs_dIkcA)
                .add(Inf1_mAk, Inf2_mA, Inf3_yIs)
                .add(When_yIncA, FeelLike_yAsI, SinceDoing_yAlI, ByDoing_yArAk, WithoutDoing_mAdAn, WithoutDoing2_mAksIzIn)
                .add(AfterDoing_yIp, When_yIncA, UnableToDo_yAmAdAn, InsteadOfDoing_mAktAnsA)
                .add(KeepDoing2_yAdur, KeepDoing_yAgor, EverSince_yAgel, Almost_yAyAz, Hastily_yIver, Stay_yAkal);

        Verb_Exp_V.add(Opt_yA, Fut_yAcAg, Fut_yAcAk, Aor_Ar, AorPart_Ar, Prog_Iyor, PresPart_yAn, Pass_nIl,
                KeepDoing2_yAdur, KeepDoing_yAgor, EverSince_yAgel, Almost_yAyAz, Hastily_yIver, Stay_yAkal,
                When_yIncA, UnableToDo_yAmAdAn, FeelLike_yAsI, SinceDoing_yAlI, ByDoing_yArAk, Inf3_yIs, Abil_yA,
                Abil_yAbil, AfterDoing_yIp, Agt_yIcI);
        Verb_Exp_C.add(Verb_Main.getSuccSetCopy()).remove(Verb_Exp_V.getSuccSetCopy()).remove(Aor_Ir, AorPart_Ir);

        Verb_Prog_Drop.add(Prog_Iyor);

        PersPron_Main.add(CASE_FORMS);
        PersPron_BenSen.add(CASE_FORMS).remove(Dat_yA);
        PersPron_BanSan.add(Dat_yA);

        Adj_Main.add(Noun_Main.getSuccSetCopy()).add(Ly_cA, Become_lAs, Quite_cA);
        Adj_Exp_C.add(Noun_Exp_C.getSuccSetCopy()).add(Ly_cA, Become_lAs, Quite_cA);
        Adj_Exp_V.add(Noun_Exp_V.getSuccSetCopy());
        Become_lAs.add(Verb_Main.getSuccSetCopy());

        Pl_lAr.add(CASE_FORMS, COPULAR_FORMS)
                .add(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz, Equ_cA);

        P1sg_Im.add(CASE_FORMS, COPULAR_FORMS).add(A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3sg_EMPTY, Equ_cA);
        P2sg_In.add(CASE_FORMS, COPULAR_FORMS).add(A1sg_yIm, A1pl_yIz, A3sg_EMPTY, Equ_cA);
        P3sg_sI.add(COPULAR_FORMS)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .add(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz, A3sg_EMPTY, Equ_ncA);
        P1pl_ImIz.add(CASE_FORMS, COPULAR_FORMS).add(A1sg_yIm, A2sg_sIn, A3sg_EMPTY, Equ_cA, A2pl_sInIz);
        P2pl_InIz.add(CASE_FORMS, COPULAR_FORMS).add(A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3sg_EMPTY, Equ_cA);
        P3pl_lArI.add(P3sg_sI.getSuccSetCopy()).add(A1sg_yIm, A2sg_sIn, A3sg_EMPTY, A1pl_yIz, A2pl_sInIz, Equ_ncA);

        Rel_ki.add(COPULAR_FORMS, PERSON_FORMS_N).add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA, Pl_lAr);
        Dat_yA.add(COPULAR_FORMS);
        Dat_nA.add(COPULAR_FORMS);

        Loc_dA.add(COPULAR_FORMS, PERSON_FORMS_N).add(Rel_ki);
        Loc_ndA.add(COPULAR_FORMS, PERSON_FORMS_N).add(Rel_ki);

        Abl_dAn.add(COPULAR_FORMS, PERSON_FORMS_N);
        Abl_ndAn.add(COPULAR_FORMS, PERSON_FORMS_N);

        Gen_nIn.add(COPULAR_FORMS, PERSON_FORMS_N).add(Rel_ki);

        A1sg_yIm.add(Cop_dIr);
        A2sg_sIn.add(Cop_dIr);
        A3sg_EMPTY.add(Cop_dIr);
        A1pl_yIz.add(Cop_dIr);
        A2pl_sInIz.add(Cop_dIr);
        A3pl_lAr.add(COPULAR_FORMS);

        Dim_cIg.add(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz);
        Dim_cIk.add(Loc_dA, Abl_dAn, Inst_ylA, P3pl_lArI, A2sg_sIn, A2pl_sInIz, A3pl_lAr, Pl_lAr).add(COPULAR_FORMS);
        Dim2_cAgIz.add(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N);

        With_lI.add(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N).add(Pl_lAr, Ness_lIg, Ness_lIk, Become_lAs, Ly_cA);
        Without_sIz.add(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N).add(Pl_lAr, Ness_lIg, Ness_lIk, Become_lAs, Ly_cA);

        Resemb_msI.add(CASE_FORMS, PERSON_FORMS_N, COPULAR_FORMS, POSSESSIVE_FORMS)
                .add(Pl_lAr, Ness_lIg, Ness_lIk, With_lI, Without_sIz);
        Resemb_msI.add(Resemb_ImsI.getSuccSetCopy());

        Ness_lIk.add(CASE_FORMS, POSSESSIVE_FORMS, COPULAR_FORMS).add(Pl_lAr, Agt_cI);
        Ness_lIg.add(Ness_lIk.getSuccSetCopy()).retain(Noun_Exp_V.getSuccSetCopy());
        Ness_lIk.remove(Ness_lIg.getSuccSetCopy());

        Related_sAl.add(Adj_Main.getSuccSetCopy()).remove(Dim_cIg, Dim_cIg, Dim2_cAgIz, With_lI, Without_sIz, Related_sAl,
                Resemb_msI, Resemb_msI);
        PastCop_ydI.add(PERSON_FORMS_COP);
        EvidCop_ymIs.add(A1sg_yIm, A2sg_sIn, A3sg_EMPTY, A1pl_yIz, A2pl_sInIz, A3pl_lAr, AsIf_cAsInA);
        CondCop_ysA.add(PERSON_FORMS_COP);
        Cop_dIr.add(A3pl_lAr);

        Neg_mA.add(Aor_z, AorPart_z, Aor_EMPTY, Prog2_mAktA, Imp_EMPTY, Opt_yA, Des_sA,
                Fut_yAcAk, Fut_yAcAg, Past_dI, Evid_mIs, Cond_sA, Abil_yAbil, Necess_mAlI, NotState_mAzlIg, NotState_mAzlIk,
                ActOf_mAcA, PastPart_dIg, PastPart_dIk, FutPart_yAcAg, FutPart_yAcAk, EvidPart_mIs)
                .add(AsLongAs_dIkcA, PresPart_yAn)
                .add(Inf1_mAk, Inf2_mA, Inf3_yIs)
                .add(When_yIncA, FeelLike_yAsI, SinceDoing_yAlI, ByDoing_yArAk, WithoutDoing2_mAksIzIn)
                .add(AfterDoing_yIp, When_yIncA, InsteadOfDoing_mAktAnsA)
                .add(KeepDoing2_yAdur, KeepDoing_yAgor, EverSince_yAgel, Hastily_yIver);

        Neg_m.add(Prog_Iyor);

        Aor_Ar.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA);
        Aor_Ir.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA);
        Aor_z.add(COPULAR_FORMS).add(A3sg_sIn, Cond_sA);
        Aor_EMPTY.add(A1sg_m, A1pl_yIz);

        Set<SuffixFormSet> noParticipleSuff =
                Sets.newHashSet(Become_lAs, Dim_cIg, Dim_cIg, Dim2_cAgIz, With_lI, Without_sIz, Related_sAl,
                        Resemb_msI, Resemb_msI);

        AorPart_Ar.add(Adj_Main.getSuccSetCopy()).remove(noParticipleSuff).add(AsIf_cAsInA);
        AorPart_Ir.add(AorPart_Ar.getSuccSetCopy());
        AorPart_z.add(AorPart_Ar.getSuccSetCopy());

        FutPart_yAcAk.add(Adj_Exp_C.getSuccSetCopy());
        FutPart_yAcAg.add(Adj_Exp_V.getSuccSetCopy());
        PresPart_yAn.add(AorPart_Ar.getSuccSetCopy());

        EvidPart_mIs.add(AorPart_Ar.getSuccSetCopy());

        PastPart_dIk.add(Adj_Exp_C.getSuccSetCopy()).remove(AsIf_cAsInA).remove(noParticipleSuff);
        PastPart_dIg.add(Adj_Exp_V.getSuccSetCopy()).remove(noParticipleSuff);

        Prog_Iyor.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA);
        Prog2_mAktA.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA);

        Fut_yAcAg.add(A1sg_yIm, A1pl_yIz);
        Fut_yAcAk.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA, AsIf_cAsInA).remove(Fut_yAcAg.getSuccSetCopy());

        Past_dI.add(A1sg_m, A2sg_n, A3sg_EMPTY, A1pl_k, A2pl_nIz, A3pl_lAr, CondCop_ysA, PastCop_ydI);
        Evid_mIs.add(PERSON_FORMS_N).add(CondCop_ysA, PastCop_ydI, EvidCop_ymIs, While_ken, Cop_dIr);

        Cond_sA.add(A1sg_m, A2sg_n, A3sg_EMPTY, A1pl_k, A2pl_nIz, A3pl_lAr, PastCop_ydI, EvidCop_ymIs);

        Imp_EMPTY.add(A2sg_EMPTY, A2sg2_sAnA, A2sg3_yInIz, A2pl2_sAnIzA, A2pl_yIn, A3sg_sIn, A3pl_sInlAr);
        Agt_cI.add(CASE_FORMS, PERSON_FORMS_N, POSSESSIVE_FORMS, COPULAR_FORMS).add(Pl_lAr, Become_lAs, With_lI, Without_sIz, Ness_lIg, Ness_lIk);
        Agt_yIcI.add(Agt_cI.getSuccSetCopy());

        Abil_yAbil.add(Verb_Main.getSuccSetCopy()).remove(Abil_yAbil, Abil_yA, Neg_mA).add(Cond_sA);
        Abil_yA.add(Neg_mA, Neg_m);

        Opt_yA.add(A1sg_yIm, A2sg_sIn, A3sg_EMPTY, A1pl_lIm, A2pl_sInIz, A3pl_lAr);
        Des_sA.add(COPULAR_FORMS).add(A1sg_m, A2sg_n, A3sg_EMPTY, A1pl_k, A2pl_nIz, A3pl_lAr, PastCop_ydI, EvidCop_ymIs);

        Caus_t.add(Verb_Main.getSuccSetCopy()).add(Pass_nIl);
        Caus_tIr.add(Verb_Main.getSuccSetCopy()).remove(Caus_tIr).add(Caus_t, Pass_nIl);

        Pass_nIl.add(Verb_Main.getSuccSetCopy()).remove(Pass_In, Pass_nIl);
        Pass_In.add(Verb_Main.getSuccSetCopy()).remove(Pass_In, Pass_nIl);

        Reflex_In.add(Verb_Main.getSuccSetCopy());
        Recip_Is.add(Verb_Main.getSuccSetCopy());

        Inf1_mAk.add(COPULAR_FORMS).add(Abl_dAn, Loc_dA, Inst_ylA);
        Inf2_mA.add(Noun_Main.getSuccessors());
        Inf3_yIs.add(Noun_Main.getSuccessors());

        When_yIncA.add(Dat_yA);
        While_ken.add(Rel_ki).add(PastCop_ydI, EvidCop_ymIs, CondCop_ysA);
        FeelLike_yAsI.add(POSSESSIVE_FORMS).add(EvidCop_ymIs);

        KeepDoing_yAgor.add(Neg_mA.getSuccSetCopy()).remove(Aor_z).add(Aor_Ir, Prog_Iyor);
        KeepDoing2_yAdur.add(KeepDoing_yAgor.getSuccSetCopy());
        EverSince_yAgel.add(KeepDoing_yAgor.getSuccSetCopy());
        Almost_yAyAz.add(KeepDoing_yAgor.getSuccSetCopy());
        Hastily_yIver.add(KeepDoing_yAgor.getSuccSetCopy());
        Necess_mAlI.add(COPULAR_FORMS, PERSON_FORMS_N);
    }
}
