package zemberek3.lexicon;

import zemberek3.lexicon.graph.DynamicSuffixProvider;
import zemberek3.lexicon.graph.SuffixData;
import zemberek3.lexicon.graph.TerminationType;

import java.util.List;

public class TurkishSuffixes extends DynamicSuffixProvider {

    // ------------ case suffixes ---------------------------

    public static Suffix Dat = new Suffix("Dat");
    public static SuffixForm Dat_yA = new SuffixForm(Dat, "+yA");
    public static SuffixForm Dat_nA = new SuffixForm(Dat, "nA");

    public static Suffix Loc = new Suffix("Loc");
    public static SuffixForm Loc_dA = new SuffixForm(Loc, ">dA");
    public static SuffixForm Loc_ndA = new SuffixForm(Loc, "ndA");

    public static Suffix Abl = new Suffix("Abl");
    public static SuffixForm Abl_dAn = new SuffixForm(Abl, ">dAn");
    public static SuffixForm Abl_ndAn = new SuffixForm(Abl, "ndAn");

    public static Suffix Gen = new Suffix("Gen");
    public static SuffixForm Gen_nIn = new SuffixForm(Gen, "+nIn");

    public static Suffix Acc = new Suffix("Acc");
    public static SuffixForm Acc_yI = new SuffixForm(Acc, "+yI");
    public static SuffixForm Acc_nI = new SuffixForm(Acc, "nI");

    public static Suffix Inst = new Suffix("Inst");
    public static SuffixForm Inst_ylA = new SuffixForm(Inst, "+ylA");

    public static Suffix Nom = new Suffix("Nom");
    public static SuffixForm Nom_TEMPLATE = getTemplate("Nom_TEMPLATE", Nom);

    // ----------------- possesive ----------------------------

    public static Suffix Pnon = new Suffix("Pnon");
    public static SuffixForm Pnon_TEMPLATE = getTemplate("Pnon_TEMPLATE", Pnon);

    public static Suffix P1sg = new Suffix("P1sg");
    public static SuffixForm P1sg_Im = new SuffixForm(P1sg, "Im");

    public static Suffix P2sg = new Suffix("P2sg");
    public static SuffixForm P2sg_In = new SuffixForm(P2sg, "In");

    public static Suffix P3sg = new Suffix("P3sg");
    public static SuffixForm P3sg_sI = new SuffixForm(P3sg, "+sI");

    public static Suffix P1pl = new Suffix("P1pl");
    public static SuffixForm P1pl_ImIz = new SuffixForm(P1pl, "ImIz");

    public static Suffix P2pl = new Suffix("P2pl");
    public static SuffixForm P2pl_InIz = new SuffixForm(P2pl, "InIz");

    public static Suffix P3pl = new Suffix("P3pl");
    public static SuffixForm P3pl_lArI = new SuffixForm(P3pl, "lArI");

    // -------------- Number-Person agreement --------------------

    public static Suffix A1sg = new Suffix("A1sg");
    public static SuffixForm A1sg_yIm = new SuffixForm(A1sg, "+yIm"); // gel-e-yim
    public static SuffixForm A1sg_m = new SuffixForm(A1sg, "m"); // gel-se-m
    public static SuffixForm A1sg_TEMPLATE = getTemplate("A1sg_TEMPLATE", A1sg); // ben

    public static Suffix A2sg = new Suffix("A2sg");
    public static SuffixForm A2sg_sIn = new SuffixForm(A2sg, "sIn"); // gel-ecek-sin
    public static SuffixForm A2sg_n = new SuffixForm(A2sg, "n"); // gel-di-n
    public static SuffixForm A2sg_TEMPLATE = getTemplate("A2sg_TEMPLATE", A2sg); // gel, sen,..
    public static SuffixForm A2sg2_sAnA = new SuffixForm(A2sg, "sAnA"); //gel-sene
    public static SuffixForm A2sg3_yInIz = new SuffixForm(A2sg, "+yInIz"); //gel-iniz

    public static Suffix A3sg = new Suffix("A3sg");
    public static SuffixForm A3sg_TEMPLATE = getTemplate("A3sg_TEMPLATE", A3sg); // gel-di-, o-
    public static SuffixForm A3sg_Verb_TEMPLATE = getTemplate("A3sg_Verb_TEMPLATE", A3sg); // gel-di-, o-
    public static SuffixForm A3sg_sIn = new SuffixForm(A3sg, "sIn"); // gel-sin

    public static Suffix A1pl = new Suffix("A1pl");
    public static SuffixForm A1pl_yIz = new SuffixForm(A1pl, "+yIz"); // geliyor-uz
    public static SuffixForm A1pl_k = new SuffixForm(A1pl, "k"); // gel-di-k
    public static SuffixForm A1pl_lIm = new SuffixForm(A1pl, "lIm"); // gel-e-lim
    public static SuffixForm A1pl_TEMPLATE = getTemplate("A1pl_TEMPLATE", A1pl); // biz

    public static Suffix A2pl = new Suffix("A2pl");
    public static SuffixForm A2pl_sInIz = new SuffixForm(A2pl, "sInIz"); // gel-ecek-siniz
    public static SuffixForm A2pl_nIz = new SuffixForm(A2pl, "nIz"); // gel-di-niz
    public static SuffixForm A2pl_yIn = new SuffixForm(A2pl, "+yIn"); // gel-me-yin
    public static SuffixForm A2pl_TEMPLATE = getTemplate("A2pl_TEMPLATE", A2pl); // gel-e-lim

    public static Suffix A2pl2 = new Suffix("A2pl2");
    public static SuffixForm A2pl2_sAnIzA = new SuffixForm(A2pl2, "sAnIzA"); // gel-senize

    public static Suffix A3pl = new Suffix("A3pl");
    public static SuffixForm A3pl_lAr = new SuffixForm(A3pl, "lAr"); // gel-ecek-ler
    public static SuffixForm A3pl_Verb_lAr_After_Tense = new SuffixForm("A3pl_Verb_lAr_After_Tense", A3pl, "lAr"); // gel-ecek-ler
    public static SuffixForm A3pl_Verb_lAr = new SuffixForm("A3pl_Verb_lAr", A3pl, "lAr"); // gel-ecek-ler
    public static SuffixForm A3pl_Comp_lAr = new SuffixForm("A3pl_Comp_lAr", A3pl, "lAr", TerminationType.NON_TERMINAL); //zeytinyağlarımız
    public static SuffixForm A3pl_sInlAr = new SuffixForm(A3pl, "sInlAr"); // gel-sinler

    // ------------ derivatioonal ----------------------

    public static Suffix Dim = new Suffix("Dim");
    public static SuffixForm Dim_cIk = new SuffixForm(Dim, ">cI~k");

    public static Suffix Dim2 = new Suffix("Dim2");
    public static SuffixForm Dim2_cAgIz = new SuffixForm(Dim2, "cAğIz");

    public static Suffix With = new Suffix("With");
    public static SuffixForm With_lI = new SuffixForm(With, "lI");

    public static Suffix Without = new Suffix("Without");
    public static SuffixForm Without_sIz = new SuffixForm(Without, "sIz");

    public static Suffix Rel = new Suffix("Rel");
    public static SuffixForm Rel_ki = new SuffixForm(Rel, "ki"); // masa-da-ki
    public static SuffixForm Rel_kI = new SuffixForm(Rel, "kI"); // dünkü

    public static Suffix Agt = new Suffix("Agt");
    public static SuffixForm Agt_cI = new SuffixForm(Agt, ">cI"); // araba-cı. Converts to another Noun.
    public static SuffixForm Agt_yIcI_2Noun = new SuffixForm("Agt_yIcI_2Noun", Agt, "+yIcI"); // otur-ucu. converts to both Noun and Adj
    public static SuffixForm Agt_yIcI_2Adj = new SuffixForm("Agt_yIcI_2Adj", Agt, "+yIcI"); // otur-ucu. converts to both Noun and Adj

    public static Suffix Ness = new Suffix("Ness");
    public static SuffixForm Ness_lIk = new SuffixForm(Ness, "lI~k");

    public static Suffix Become = new Suffix("Become");
    public static SuffixForm Become_lAs = new SuffixForm(Become, "lAş");
    public static SuffixForm Become_Adj_lAs = new SuffixForm("Become_Adj_lAs", Become, "lAş");

    public static Suffix Acquire = new Suffix("Acquire");
    public static SuffixForm Acquire_lAn = new SuffixForm(Acquire, "lAn");

    public static Suffix Resemb = new Suffix("Resemb");
    public static SuffixForm Resemb_ImsI = new SuffixForm(Resemb, "ImsI"); // udunumsu
    public static SuffixForm Resemb_msI = new SuffixForm(Resemb, "+msI"); // odunsu

    public static SuffixForm Resemb_Adj_ImsI = new SuffixForm("Resemb_Adj_ImsI", Resemb, "ImsI"); // udunumsu
    public static SuffixForm Resemb_Adj_msI = new SuffixForm("Resemb_Adj_msI", Resemb, "+msI"); // odunsu

    public static Suffix Related = new Suffix("Related");
    public static SuffixForm Related_sAl = new SuffixForm(Related, "sAl");

    // ----------------------------  verbal tense --------------------------------

    public static Suffix Aor = new Suffix("Aor");
    public static SuffixForm Aor_Ir = new SuffixForm(Aor, "+Ir"); //gel-ir
    public static SuffixForm Aor_Ar = new SuffixForm(Aor, "+Ar"); //ser-er
    public static SuffixForm Aor_z = new SuffixForm(Aor, "z"); // gel-me-z
    public static SuffixForm Aor_EMPTY = getTemplate("Aor_EMPTY", Aor, TerminationType.NON_TERMINAL); // gel-me--yiz

    public static Suffix Prog = new Suffix("Prog");
    public static SuffixForm Prog_Iyor = new SuffixForm(Prog, "Iyor");

    public static Suffix Prog2 = new Suffix("Prog2");
    public static SuffixForm Prog2_mAktA = new SuffixForm(Prog2, "mAktA");

    public static Suffix Fut = new Suffix("Fut");
    public static SuffixForm Fut_yAcAk = new SuffixForm(Fut, "+yAcA~k");

    public static Suffix Past = new Suffix("Past");
    public static SuffixForm Past_dI = new SuffixForm(Past, ">dI");

    public static Suffix Evid = new Suffix("Evid");
    public static SuffixForm Evid_mIs = new SuffixForm(Evid, "mIş");

    // ---------------------------------------------------

    public static Suffix PastPart = new Suffix("PastPart");
    public static SuffixForm PastPart_dIk_2Noun = new SuffixForm("PastPart_dIk_2Noun", PastPart, ">dI~k");
    public static SuffixForm PastPart_dIk_2Adj = new SuffixForm("PastPart_dIk_2Noun", PastPart, ">dI~k");

    public static Suffix AorPart = new Suffix("AorPart"); // convert to an Adjective
    public static SuffixForm AorPart_Ir_2Adj = new SuffixForm("AorPart_Ir_2Adj", AorPart, "+Ir"); //gel-ir
    public static SuffixForm AorPart_Ar_2Adj = new SuffixForm("AorPart_Ar_2Adj", AorPart, "+Ar"); //ser-er
    public static SuffixForm AorPart_z_2Adj = new SuffixForm("AorPart_z_2Noun", AorPart, "z"); // gel-me-z

    public static Suffix FutPart = new Suffix("FutPart");
    public static SuffixForm FutPart_yAcAk_2Adj = new SuffixForm("FutPart_yAcAk_2Adj", FutPart, "+yAcA~k");
    public static SuffixForm FutPart_yAcAk_2Noun = new SuffixForm("FutPart_yAcAk_2Noun", FutPart, "+yAcA~k");

    public static Suffix EvidPart = new Suffix("EvidPart");
    public static SuffixForm EvidPart_mIs_2Noun = new SuffixForm("EvidPart_mIs_2Noun", EvidPart, "mIş");
    public static SuffixForm EvidPart_mIs_2Adj = new SuffixForm("EvidPart_mIs_2Adj", EvidPart, "mIş");

    public static Suffix PresPart = new Suffix("PresPart");
    public static SuffixForm PresPart_yAn = new SuffixForm(PresPart, "+yAn");

    public static Suffix Pos = new Suffix("Pos");
    public static SuffixForm Pos_EMPTY = getTemplate("Pos_EMPTY", Pos); // Verb Positive Null Morpheme template.

    public static Suffix Neg = new Suffix("Neg");
    public static SuffixForm Neg_mA = new SuffixForm(Neg, "mA"); //gel-me
    public static SuffixForm Neg_m = new SuffixForm(Neg, "m", TerminationType.NON_TERMINAL); // gel-m-iyor

    //TODO: May be redundant. Cond_Cop may suffice
    public static Suffix Cond = new Suffix("Cond");
    public static SuffixForm Cond_sA = new SuffixForm(Cond, "sA");

    public static Suffix Necess = new Suffix("Necess");
    public static SuffixForm Necess_mAlI = new SuffixForm(Necess, "mAlI");

    public static Suffix Opt = new Suffix("Opt");
    public static SuffixForm Opt_yA = new SuffixForm(Opt, "+yA");

    public static Suffix Pass = new Suffix("Pass");
    public static SuffixForm Pass_In = new SuffixForm(Pass, "+In");
    public static SuffixForm Pass_InIl = new SuffixForm(Pass, "+InIl");
    public static SuffixForm Pass_nIl = new SuffixForm(Pass, "+nIl");

    public static Suffix Caus = new Suffix("Caus");
    public static SuffixForm Caus_t = new SuffixForm(Caus, "t");
    public static SuffixForm Caus_tIr = new SuffixForm(Caus, ">dIr");

    public static Suffix Imp = new Suffix("Imp");
    public static SuffixForm Imp_TEMPLATE = getTemplate("Imp_TEMPLATE", Imp);

    public static Suffix Des = new Suffix("Des");
    public static SuffixForm Des_sA = new SuffixForm(Des, "sA");

    public static Suffix Recip = new Suffix("Recip");
    public static SuffixForm Recip_Is = new SuffixForm(Recip, "+Iş");
    public static SuffixForm Recip_yIs = new SuffixForm(Recip, "+yIş");

    public static Suffix Reflex = new Suffix("Reflex");
    public static SuffixForm Reflex_In = new SuffixForm(Reflex, "+In");

    public static Suffix Abil = new Suffix("Abil");
    public static SuffixForm Abil_yAbil = new SuffixForm(Abil, "+yAbil");
    public static SuffixForm Abil_yA = new SuffixForm(Abil, "+yA", TerminationType.NON_TERMINAL);

    // TODO: Copular suffixes are not correct in verbs here. causes verb derivation.
    public static Suffix Cop = new Suffix("Cop");
    public static SuffixForm Cop_dIr = new SuffixForm(Cop, ">dIr");

    public static SuffixForm PastCop_ydI = new SuffixForm("PastCop_ydI", Past, "+y>dI");

    public static SuffixForm EvidCop_ymIs = new SuffixForm("EvidCop_ymIs", Evid, "+ymIş");

    public static SuffixForm CondCop_ysA = new SuffixForm("CondCop_ysA", Cond, "+ysA");

    public static Suffix While = new Suffix("While");
    public static SuffixForm While_ken = new SuffixForm(While, "+yken");

    public static Suffix Pres = new Suffix("Pres");  // Present tense only appears after a zero morpheme verb derivation such as "kalemdir"
    public static SuffixForm Pres_TEMPLATE = getTemplate("Pres_TEMPLATE", Pres);

    public static Suffix Equ = new Suffix("Equ");
    public static SuffixForm Equ_cA = new SuffixForm(Equ, ">cA");
    public static SuffixForm Equ_ncA = new SuffixForm(Equ, "ncA");

    public static Suffix NotState = new Suffix("NotState");
    public static SuffixForm NotState_mAzlIk = new SuffixForm(NotState, "mAzlI~k");

    public static Suffix ActOf = new Suffix("ActOf");
    public static SuffixForm ActOf_mAcA = new SuffixForm(ActOf, "mAcA");

    public static Suffix AsIf = new Suffix("AsIf");
    public static SuffixForm AsIf_cAsInA = new SuffixForm(AsIf, ">cAsInA");

    // Converts to an Adverb.
    public static Suffix AsLongAs = new Suffix("AsLongAs");
    public static SuffixForm AsLongAs_dIkcA = new SuffixForm(AsLongAs, ">dIkçA");

    public static Suffix When = new Suffix("When");
    public static SuffixForm When_yIncA = new SuffixForm(When, "+yIncA");

    // It also may have "worthy of doing" meaning after passive. Converts to an Adjective.
    public static Suffix FeelLike = new Suffix("FeelLike");
    public static SuffixForm FeelLike_yAsI_2Noun = new SuffixForm("FeelLike_yAsI_2Noun", FeelLike, "+yAsI");
    public static SuffixForm FeelLike_yAsI_2Adj = new SuffixForm("FeelLike_yAsI_2Adj", FeelLike, "+yAsI");

    // Converts to an Adverb.
    public static Suffix SinceDoing = new Suffix("SinceDoing");
    public static SuffixForm SinceDoing_yAlI = new SuffixForm(SinceDoing, "+yAlI");

    // Converts to an Adverb.
    public static Suffix ByDoing = new Suffix("ByDoing");
    public static SuffixForm ByDoing_yArAk = new SuffixForm(ByDoing, "+yArAk");

    // Converts to an Adverb.
    // TODO: this should have a Neg_null effect
    public static Suffix WithoutDoing = new Suffix("WithoutDoing");
    public static SuffixForm WithoutDoing_mAdAn = new SuffixForm(WithoutDoing, "mAdAn");

    // Converts to an Adverb.
    public static Suffix UntilDoing = new Suffix("UntilDoing");
    public static SuffixForm UntilDoing_yAsIyA = new SuffixForm(UntilDoing, "+yAsIyA");


    public static Suffix WithoutDoing2 = new Suffix("WithoutDoing2");
    public static SuffixForm WithoutDoing2_mAksIzIn = new SuffixForm(WithoutDoing2, "mAksIzIn");

    // Converts to an Adverb.
    public static Suffix AfterDoing = new Suffix("AfterDoing");
    public static SuffixForm AfterDoing_yIp = new SuffixForm(AfterDoing, "+yIp");

    public static Suffix UnableToDo = new Suffix("UnableToDo");
    public static SuffixForm UnableToDo_yAmAdAn = new SuffixForm(UnableToDo, "+yAmAdAn");

    public static Suffix InsteadOfDoing = new Suffix("InsteadOfDoing");
    public static SuffixForm InsteadOfDoing_mAktAnsA = new SuffixForm(InsteadOfDoing, "mAktAnsA");

    // Converts to an Adverb.
    public static Suffix KeepDoing = new Suffix("KeepDoing");
    public static SuffixForm KeepDoing_yAgor = new SuffixForm(KeepDoing, "+yAgör");

    public static Suffix KeepDoing2 = new Suffix("KeepDoing2");
    public static SuffixForm KeepDoing2_yAdur = new SuffixForm(KeepDoing2, "+yAdur");

    public static Suffix EverSince = new Suffix("EverSince");
    public static SuffixForm EverSince_yAgel = new SuffixForm(EverSince, "+yAgel");

    public static Suffix Almost = new Suffix("Almost");
    public static SuffixForm Almost_yAyAz = new SuffixForm(Almost, "+yAyaz");

    public static Suffix Hastily = new Suffix("Hastily");
    public static SuffixForm Hastily_yIver = new SuffixForm(Hastily, "+yIver");

    public static Suffix Stay = new Suffix("Stay");
    public static SuffixForm Stay_yAkal = new SuffixForm(Stay, "+yAkal");

    public static Suffix Inf1 = new Suffix("Inf1");
    public static SuffixForm Inf1_mAk = new SuffixForm(Inf1, "mAk");

    public static Suffix Inf2 = new Suffix("Inf2");
    public static SuffixForm Inf2_mA = new SuffixForm(Inf2, "mA");

    public static Suffix Inf3 = new Suffix("Inf3");
    public static SuffixForm Inf3_yIs = new SuffixForm(Inf3, "+yIş");

    // TODO is below a valid morpheme? 
/*    public static Suffix NounDeriv = new Suffix("NounDeriv");
    public static SuffixForm NounDeriv_nIm = new SuffixForm(NounDeriv, "+nIm");*/

    public static Suffix Ly = new Suffix("Ly");
    public static SuffixForm Ly_cA = new SuffixForm(Ly, ">cA");

    public static Suffix Quite = new Suffix("Quite");
    public static SuffixForm Quite_cA = new SuffixForm(Quite, ">cA");

    public static Suffix Ordinal = new Suffix("Ordinal");
    public static SuffixForm Ordinal_IncI = new SuffixForm(Ordinal, "+IncI");

    public static Suffix Grouping = new Suffix("Grouping");
    public static SuffixForm Grouping_sAr = new SuffixForm(Grouping, "+şAr");

    public static Suffix NounRoot = new Suffix("Noun");
    public static SuffixForm Noun_TEMPLATE = getTemplate("Noun_TEMPLATE", NounRoot);
    public static SuffixForm Noun2Noun = getTemplate("Noun2Noun", NounRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Adj2Noun = getTemplate("Adj2Noun", NounRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Adv2Noun = getTemplate("Adv2Noun", NounRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2Noun = getTemplate("Verb2Noun", NounRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2NounPart = getTemplate("Verb2NounPart", NounRoot, TerminationType.NON_TERMINAL);

    public static SuffixForm Noun_Default = getNull("Noun_Default", NounRoot);
    public static SuffixForm Noun_Comp_P3sg = getTemplate("Noun_Comp_P3sg", NounRoot);
    public static SuffixForm Noun_Comp_P3sg_Root = getTemplate("Noun_Comp_P3sg_Root", NounRoot);

    public static Suffix AdjRoot = new Suffix("Adj");
    public static SuffixForm Adj_TEMPLATE = getTemplate("Adj_TEMPLATE", AdjRoot);
    public static SuffixForm Noun2Adj = getTemplate("Noun2Adj", AdjRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Adj2Adj = getTemplate("Adj2Adj", AdjRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Adv2Adj = getTemplate("Adv2Adj", AdjRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2Adj = getTemplate("Verb2Adj", AdjRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2AdjPart = getTemplate("Verb2AdjPart", AdjRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Adj_Main_Rel = getNull("Adj_TEMPLATE", AdjRoot);
    public static SuffixForm Adj_Default = getNull("Adj_Default", AdjRoot, TerminationType.TERMINAL);

    public static Suffix AdvRoot = new Suffix("AdvRoot");

    public static SuffixForm Adv_TEMPLATE = getTemplate("Adv_TEMPLATE", AdvRoot);
    public static SuffixForm Adj2Adv = getTemplate("Adj2Adv", AdvRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2Adv = getTemplate("Verb2Adv", AdvRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Adv_Default = getNull("Adv_Default", AdvRoot);

    public static Suffix InterjRoot = new Suffix("Interj");
    public static SuffixForm Interj_Main = getTemplate("Interj_Main", InterjRoot);

    public static Suffix ConjRoot = new Suffix("Conj");
    public static SuffixForm Conj_Main = getTemplate("Conj_Main", ConjRoot);

    public static Suffix NumeralRoot = new Suffix("Numeral");
    public static SuffixForm Numeral_Main = getTemplate("Numeral_Main", NumeralRoot);

    public static Suffix DetRoot = new Suffix("Det");
    public static SuffixForm Det_Main = getTemplate("Det_Main", DetRoot);

    public static Suffix ProperNounRoot = new Suffix("ProperNounRoot");
    public static SuffixForm ProperNoun_Main = getTemplate("ProperNoun_Main", ProperNounRoot);

    public static Suffix VerbRoot = new Suffix("Verb");
    public static SuffixForm Verb_TEMPLATE = getTemplate("Verb_TEMPLATE", VerbRoot);
    public static SuffixForm Adj2Verb = getTemplate("Adj2Verb", VerbRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Noun2Verb = getTemplate("Noun2Verb", VerbRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Noun2VerbCopular = getTemplate("Noun2VerbCopular", VerbRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2Verb = getTemplate("Verb2Verb", VerbRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2VerbAbility = getTemplate("Verb2VerbAbility", VerbRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb2VerbCompounds = getTemplate("Verb2VerbCompounds", VerbRoot, TerminationType.NON_TERMINAL);
    public static SuffixForm Verb_Default = getNull("Verb_Default", VerbRoot);
    public static SuffixForm Verb_Prog_Drop = new SuffixForm("Verb_Prog_Drop", VerbRoot, "");

    public static Suffix PersPronRoot = new Suffix("PersPron");
    public static SuffixForm PersPron_TEMPLATE = getTemplate("PersPron_TEMPLATE", PersPronRoot);
    public static SuffixForm PersPron_BenSen = getTemplate("PersPron_BenSen", PersPronRoot);
    public static SuffixForm PersPron_BanSan = getTemplate("PersPron_BanSan", PersPronRoot);

    public static Suffix QuesRoot = new Suffix("Ques");
    public static SuffixForm Ques_mI = getTemplate("Ques_mI", QuesRoot);

    public static Suffix ParticleRoot = new Suffix("Particle");
    public static SuffixForm Particle_Main = getTemplate("Particle_Main", ParticleRoot);

    // TODO: add time root. (with Rel_ki + Noun)

    public static final SuffixForm[] CASE_FORMS = {Nom_TEMPLATE, Dat_yA, Loc_dA, Abl_dAn, Gen_nIn, Acc_yI, Inst_ylA, Equ_cA};
    public static final SuffixForm[] POSSESSIVE_FORMS = {Pnon_TEMPLATE, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, P3pl_lArI};
    public static final SuffixForm[] PERSON_FORMS_N = {A1sg_yIm, A2sg_sIn, A3sg_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_lAr};
    public static final SuffixForm[] PERSON_FORMS_COP = {A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr};
    public static final SuffixForm[] COPULAR_FORMS = {Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken};

    public TurkishSuffixes() {

        //---------------------------- Root and Derivation Morphemes ---------------------------------------------------

        // noun template. it has all possible suffix forms that a noun can have
        Noun_TEMPLATE.connections.add(A3pl_lAr, A3pl_Comp_lAr, A3sg_TEMPLATE);
        Noun_TEMPLATE.indirectConnections
                .add(POSSESSIVE_FORMS, CASE_FORMS)
                .add(Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken)
                .add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, Cop_dIr)
                .add(A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Acc_nI, Equ_ncA)
                .add(Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, Agt_cI, Resemb_msI, Resemb_ImsI, Ness_lIk, Related_sAl)
                .add(Become_lAs, Acquire_lAn, Pres_TEMPLATE)
                .add(Noun2Noun, Noun2Adj, Noun2Verb, Noun2VerbCopular);

        // default noun suffix form. we remove some suffixes so that words like araba-na (dative)
        Noun_Default.connections.add(A3pl_lAr, A3sg_TEMPLATE);
        Noun_Default.indirectConnections
                .add(Noun_TEMPLATE.indirectConnections)
                .remove(Dat_nA, Loc_ndA, Abl_ndAn, Acc_nI);

        Noun2Noun.connections.add(Dim_cIk, Dim2_cAgIz, Agt_cI, Ness_lIk);

        Noun2Adj.connections.add(With_lI, Without_sIz, Resemb_msI, Resemb_ImsI, Rel_ki, Related_sAl);

        Noun2Verb.connections.add(Become_lAs, Acquire_lAn);

        Noun2VerbCopular.connections.add(Pres_TEMPLATE, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken);
        Noun2VerbCopular.indirectConnections.add(A1sg_m, A2sg_n, A3sg_Verb_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_Verb_lAr);
        Noun2VerbCopular.indirectConnections.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, Cop_dIr);

        Adj2Noun.connections.add(Noun_TEMPLATE.connections);
        Adj2Noun.indirectConnections.add(Noun_TEMPLATE.indirectConnections).remove(Related_sAl, Become_lAs, Resemb_ImsI, Resemb_msI);

        Adj2Adj.connections.add(Quite_cA, Resemb_Adj_ImsI, Resemb_Adj_msI);

        Adj2Adv.connections.add(Ly_cA);

        Adv2Noun.connections.add(A3sg_TEMPLATE);
        Adv2Noun.indirectConnections.add(Pnon_TEMPLATE, Dat_yA);

        Adv2Adj.connections.add(Rel_ki); // ararkenki

        Adj2Verb.connections.add(Become_Adj_lAs, Acquire_lAn).add(COPULAR_FORMS);

        Verb2Verb.connections.add(Caus_t, Caus_tIr, Pass_In, Pass_nIl, Pass_InIl, Abil_yA);

        Verb2VerbCompounds.connections.add(KeepDoing_yAgor, KeepDoing2_yAdur, EverSince_yAgel, Almost_yAyAz, Hastily_yIver, Stay_yAkal);

        Verb2VerbAbility.connections.add(Abil_yAbil);

        Verb2Noun.connections.add(Inf1_mAk, Inf2_mA, Inf3_yIs, FeelLike_yAsI_2Noun, Agt_yIcI_2Noun, NotState_mAzlIk);
        Verb2Noun.indirectConnections.add(Noun_TEMPLATE.indirectConnections);

        Verb2NounPart.connections.add(PastPart_dIk_2Noun, EvidPart_mIs_2Noun, FutPart_yAcAk_2Noun);
        Verb2AdjPart.connections.add(PastPart_dIk_2Adj, EvidPart_mIs_2Adj, FutPart_yAcAk_2Adj, AorPart_Ar_2Adj, AorPart_Ir_2Adj, AorPart_z_2Adj, PresPart_yAn);

        Verb2Adv.connections.add(When_yIncA, SinceDoing_yAlI, UnableToDo_yAmAdAn, ByDoing_yArAk, WithoutDoing_mAdAn, WithoutDoing2_mAksIzIn)
                .add(InsteadOfDoing_mAktAnsA, AsLongAs_dIkcA, AfterDoing_yIp, AsIf_cAsInA);

        Verb2Adj.connections.add(When_yIncA, FeelLike_yAsI_2Adj, Agt_yIcI_2Adj);

        Adv_TEMPLATE.connections.add(Adv2Noun, Adv2Adj);

        Adj_TEMPLATE.connections.add(Adj2Noun, Adj2Adj, Adj2Adv, Adj2Verb);
        Adj_TEMPLATE.indirectConnections.add(
                Adj2Noun.allSuccessors(),
                Adj2Adj.allSuccessors(),
                Adj2Adv.allSuccessors(),
                Adj2Verb.allSuccessors());

        Adj_Default.connections.add(Adj_TEMPLATE.connections);
        Adj_Default.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        // P3sg compound suffixes. (full form. such as zeytinyağı-na)
        Noun_Comp_P3sg.connections.add(A3sg_TEMPLATE);
        Noun_Comp_P3sg.indirectConnections.add(POSSESSIVE_FORMS)
                .add(Pnon_TEMPLATE, Nom_TEMPLATE)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .add(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz);

        // P3sg compound suffixes. (root form. such as zeytinyağ-lar-ı)
        Noun_Comp_P3sg_Root.connections.add(A3pl_Comp_lAr, A3sg_TEMPLATE); // A3pl_Comp_lAr is used, because zeytinyağ-lar is not allowed.
        Noun_Comp_P3sg_Root.indirectConnections
                .add(Pnon_TEMPLATE, Nom_TEMPLATE, With_lI, Without_sIz, Agt_cI, Resemb_msI, Resemb_ImsI, Ness_lIk, Related_sAl)
                .add(P3pl_lArI)
                .add(Noun2Noun.allSuccessors())
                .add(Noun2Noun)
                .add(Noun2Adj.allSuccessors())
                .add(Noun2Adj);

        // Proper noun default //TODO: should be a template
        ProperNoun_Main.connections.add(A3pl_lAr, A3sg_TEMPLATE);
        ProperNoun_Main.indirectConnections
                .add(CASE_FORMS, POSSESSIVE_FORMS)
                .add(Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, A3sg_TEMPLATE, Agt_cI, Ness_lIk);

        Verb_TEMPLATE.connections.add(Neg_mA, Neg_m, Pos_EMPTY, Verb2Verb);
        Verb_TEMPLATE.indirectConnections
                .add(Prog_Iyor, Prog2_mAktA, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir, Aor_Ar, Aor_z, AorPart_Ir_2Adj, AorPart_Ar_2Adj)
                .add(Abil_yAbil, Abil_yA, Caus_tIr, Caus_t, Opt_yA, Imp_TEMPLATE, Agt_yIcI_2Adj, Agt_yIcI_2Noun, Des_sA)
                .add(NotState_mAzlIk, ActOf_mAcA, PastPart_dIk_2Adj, PastPart_dIk_2Noun, EvidPart_mIs_2Adj, EvidPart_mIs_2Noun, Pass_In, Pass_nIl, Pass_InIl)
                .add(FutPart_yAcAk_2Adj, FutPart_yAcAk_2Noun, PresPart_yAn, AsLongAs_dIkcA, A2pl2_sAnIzA)
                .add(A1sg_yIm, A2sg_sIn, A2sg_TEMPLATE, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr, A3sg_sIn, A3pl_sInlAr, A2sg2_sAnA, A2sg3_yInIz)
                .add(Inf1_mAk, Inf2_mA, Inf3_yIs, Necess_mAlI)
                .add(When_yIncA, FeelLike_yAsI_2Adj, FeelLike_yAsI_2Noun, SinceDoing_yAlI, ByDoing_yArAk, WithoutDoing_mAdAn, WithoutDoing2_mAksIzIn)
                .add(AfterDoing_yIp, When_yIncA, UnableToDo_yAmAdAn, InsteadOfDoing_mAktAnsA, A3pl_Verb_lAr_After_Tense)
                .add(KeepDoing2_yAdur, KeepDoing_yAgor, EverSince_yAgel, Almost_yAyAz, Hastily_yIver, Stay_yAkal, Recip_Is)
                .add(UntilDoing_yAsIyA, Verb2VerbCompounds, Verb2Noun, Verb2Adv, Verb2Adj, Verb2NounPart, Verb2AdjPart, Verb2VerbAbility);

        Verb_Default.connections.add(Verb_TEMPLATE.connections);
        Verb_Default.indirectConnections.add(Verb_TEMPLATE.indirectConnections).remove(Caus_t);

        //---------------------------- Noun -----------------------------------------------------------------------

        A3pl_lAr.connections.add(POSSESSIVE_FORMS).remove(P3pl_lArI);
        A3pl_lAr.indirectConnections
                .add(Noun2VerbCopular)
                .add(Noun2VerbCopular.allSuccessors())
                .add(CASE_FORMS)
                .add(A1pl_yIz, A2pl_sInIz);

        //TODO: check below.
        A3pl_Comp_lAr.connections.add(A3pl_lAr.connections);
        A3pl_Comp_lAr.indirectConnections.add(CASE_FORMS)
                .add(A1pl_yIz, A2pl_sInIz);

        A3sg_TEMPLATE.connections.add(POSSESSIVE_FORMS);
        A3sg_TEMPLATE.indirectConnections
                .add(Noun_TEMPLATE.indirectConnections).remove(POSSESSIVE_FORMS)
                .add(Noun2Noun.allSuccessors()).add(Noun2Noun)
                .add(Noun2VerbCopular.allSuccessors())
                .add(Noun2Adj.allSuccessors().add(Noun2Adj));

        Nom_TEMPLATE.connections.add(Noun2Noun, Noun2Adj, Noun2Verb, Noun2VerbCopular);

        Nom_TEMPLATE.indirectConnections
                .add(Noun2Noun.allSuccessors())
                .add(Noun2Adj.allSuccessors())
                .add(Noun2VerbCopular.allSuccessors())
                .add(Noun2Verb.allSuccessors());

        Pres_TEMPLATE.connections.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr);
        Pres_TEMPLATE.indirectConnections.add(Cop_dIr);

        A1sg_yIm.connections.add(Cop_dIr);
        A2sg_sIn.connections.add(Cop_dIr);
        A3sg_Verb_TEMPLATE.connections.add(Cop_dIr, Verb2Adv);
        A3sg_Verb_TEMPLATE.indirectConnections.add(AsIf_cAsInA);
        A1pl_yIz.connections.add(Cop_dIr, Verb2Adv);
        A1pl_yIz.indirectConnections.add(AsIf_cAsInA);
        A2pl_sInIz.connections.add(Cop_dIr, Verb2Adv);
        A2pl_sInIz.indirectConnections.add(AsIf_cAsInA);
        A3pl_Verb_lAr.connections.add(Evid_mIs, Past_dI, Cond_sA, Cop_dIr, Verb2Adv);
        A3pl_Verb_lAr.indirectConnections.add(AsIf_cAsInA);

        Dim_cIk.connections.add(Noun_Default.connections);
        Dim_cIk.indirectConnections.add(Noun_Default.allSuccessors().add(Noun2VerbCopular).remove(Dim_cIk, Dim2_cAgIz));

        Agt_cI.connections.add(Noun_Default.connections);
        Agt_cI.indirectConnections.add(Noun_Default.allSuccessors().add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors()).remove(Agt_cI));

        Dim2_cAgIz.connections.add(Noun_Default.connections);
        Dim2_cAgIz.indirectConnections.add(Noun_Default.allSuccessors().remove(Dim_cIk, Dim2_cAgIz));

        Ness_lIk.connections.add(Noun_Default.connections);
        Ness_lIk.indirectConnections.add(Noun_Default.allSuccessors().remove(Ness_lIk));

        Pnon_TEMPLATE.connections
                .add(CASE_FORMS)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Acc_nI);
        Pnon_TEMPLATE.indirectConnections
                .add(Nom_TEMPLATE.connections)
                .add(Noun2Noun.allSuccessors())
                .add(Noun2Verb.allSuccessors())
                .add(Noun2VerbCopular.allSuccessors())
                .add(Noun2Adj.allSuccessors());

        P1sg_Im.connections.add(CASE_FORMS);
        P1sg_Im.indirectConnections.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P2sg_In.connections.add(CASE_FORMS);
        P2sg_In.indirectConnections.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P3sg_sI.connections.add(Nom_TEMPLATE, Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA, Equ_ncA);
        P3sg_sI.indirectConnections.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P1pl_ImIz.connections.add(CASE_FORMS);
        P1pl_ImIz.indirectConnections.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P2pl_InIz.connections.add(CASE_FORMS);
        P2pl_InIz.indirectConnections.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P3pl_lArI.connections.add(CASE_FORMS);
        P3pl_lArI.indirectConnections.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        With_lI.connections.add(Adj_TEMPLATE.connections);
        With_lI.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        Related_sAl.connections.add(Adj_TEMPLATE.connections);
        Related_sAl.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        Without_sIz.connections.add(Adj_TEMPLATE.connections);
        Without_sIz.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        // Noun->Adj derivation elmadaki=elma+Loc-Adj+Rel
        Loc_dA.connections.add(Noun2Adj, Noun2VerbCopular);
        Loc_dA.indirectConnections.add(Rel_ki).add(Noun2VerbCopular.allSuccessors());

        Loc_ndA.connections.add(Noun2Adj, Noun2VerbCopular);
        Loc_ndA.indirectConnections.add(Rel_ki).add(Noun2VerbCopular.allSuccessors());

        Dat_nA.connections.add(Noun2VerbCopular);
        Dat_nA.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Dat_yA.connections.add(Noun2VerbCopular);
        Dat_yA.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Gen_nIn.connections.add(Noun2VerbCopular);
        Gen_nIn.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Dat_yA.connections.add(Noun2VerbCopular);
        Dat_yA.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Abl_dAn.connections.add(Noun2VerbCopular);
        Abl_dAn.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Abl_ndAn.connections.add(Noun2VerbCopular);
        Abl_ndAn.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Inst_ylA.connections.add(Noun2VerbCopular);
        Inst_ylA.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Equ_cA.connections.add(Noun2VerbCopular);
        Equ_cA.indirectConnections.add(Noun2VerbCopular.allSuccessors());

        Rel_ki.connections.add(Adj2Noun);
        Rel_ki.indirectConnections.add(Adj2Noun.indirectConnections).add(A3sg_TEMPLATE, Pnon_TEMPLATE, Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA, Equ_ncA);

        Resemb_msI.connections.add(Adj_TEMPLATE.connections);
        Resemb_msI.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        Resemb_ImsI.connections.add(Adj_TEMPLATE.connections);
        Resemb_ImsI.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        Resemb_Adj_msI.connections.add(Adj_TEMPLATE.connections);
        Resemb_Adj_msI.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        Resemb_Adj_ImsI.connections.add(Adj_TEMPLATE.connections);
        Resemb_Adj_ImsI.indirectConnections.add(Adj_TEMPLATE.indirectConnections);

        //---------------------------- Adjective -----------------------------------------------------------------------

        Become_lAs.connections.add(Verb_TEMPLATE.connections);
        Become_lAs.indirectConnections.add(Verb_TEMPLATE.indirectConnections);

        Acquire_lAn.connections.add(Verb_TEMPLATE.connections);
        Acquire_lAn.indirectConnections.add(Verb_TEMPLATE.indirectConnections);

        Become_Adj_lAs.connections.add(Verb_TEMPLATE.connections);
        Become_Adj_lAs.indirectConnections.add(Verb_TEMPLATE.indirectConnections);

        Quite_cA.connections.add(Adj_TEMPLATE.connections);

        Ly_cA.connections.add(Adv_TEMPLATE.connections);

        //---------------------------- Verb ----------------------------------------------------------------------------

        Pos_EMPTY.connections
                .add(Verb2VerbCompounds, Verb2Noun, Verb2Adv, Verb2Adj, Verb2AdjPart, Verb2NounPart, Verb2VerbAbility,
                        Imp_TEMPLATE, Prog_Iyor, Prog2_mAktA, Fut_yAcAk, Aor_Ar, Aor_Ir, Past_dI, Evid_mIs)
                .add(Cond_sA, Necess_mAlI, Opt_yA, Des_sA);
        Pos_EMPTY.indirectConnections
                .add(Verb_Default.indirectConnections)
                .add(A2pl2_sAnIzA, A2pl_yIn)
                .add(Verb2AdjPart.connections, Verb2NounPart.connections)
                .remove(Neg_m, Neg_mA);

        Neg_mA.connections.add(Verb2VerbCompounds, Verb2VerbAbility, Verb2Noun, Verb2Verb, Verb2Adv, Verb2Adj, Verb2AdjPart, Verb2NounPart,
                Aor_z, Aor_EMPTY, Prog2_mAktA, Imp_TEMPLATE, Opt_yA, Des_sA,
                Fut_yAcAk, Past_dI, Evid_mIs, Necess_mAlI, NotState_mAzlIk,
                ActOf_mAcA);

        Neg_mA.indirectConnections.add(Verb2VerbCompounds.connections, Verb2AdjPart.connections, Verb2NounPart.connections,
                Verb2Noun.connections, Verb2Adv.connections, Verb2Adj.connections)
                .add(A2sg_TEMPLATE, A1sg_m, A1sg_yIm, A2sg_sIn, A2sg2_sAnA, A2sg3_yInIz, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A2pl2_sAnIzA, A2pl_yIn, A3pl_Verb_lAr, A3sg_sIn, A3pl_sInlAr)
                .add(Abil_yAbil);

        Neg_m.connections.add(Prog_Iyor);

        Imp_TEMPLATE.connections.add(A2sg_TEMPLATE, A2sg2_sAnA, A2sg3_yInIz, A2pl2_sAnIzA, A2pl_yIn, A3sg_sIn, A3pl_sInlAr);

        Caus_t.connections.add(Verb_TEMPLATE.connections);
        Caus_t.indirectConnections.add(Verb_TEMPLATE.allSuccessors()).add(Pass_nIl).add(Caus_tIr).remove(Caus_t);

        Caus_tIr.connections.add(Verb_TEMPLATE.connections);
        Caus_tIr.indirectConnections.add(Verb_TEMPLATE.allSuccessors()).add(Pass_nIl).add(Caus_t).remove(Caus_tIr);

        Pass_nIl.connections.add(Verb_TEMPLATE.connections);
        Pass_nIl.indirectConnections.add(Verb_TEMPLATE.allSuccessors()).remove(Caus_t, Caus_tIr, Pass_nIl, Pass_InIl, Pass_In);

        Pass_In.connections.add(Verb_TEMPLATE.connections);
        Pass_In.indirectConnections.add(Verb_TEMPLATE.allSuccessors()).remove(Caus_t, Caus_tIr, Pass_nIl, Pass_InIl, Pass_In);

        Pass_InIl.connections.add(Verb_TEMPLATE.connections);
        Pass_InIl.indirectConnections.add(Verb_TEMPLATE.allSuccessors()).remove(Caus_t, Caus_tIr, Pass_nIl, Pass_InIl, Pass_In);

        Prog_Iyor.connections.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        Prog2_mAktA.connections.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        Fut_yAcAk.connections.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        Aor_Ar.connections.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);
        Aor_Ar.indirectConnections.add(Verb2Adv, AsIf_cAsInA);
        Aor_Ir.connections.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);
        Aor_Ir.indirectConnections.add(Verb2Adv, AsIf_cAsInA);
        Aor_z.connections.add(A3sg_Verb_TEMPLATE, A3sg_sIn).add(PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken, Cop_dIr);
        Aor_z.indirectConnections.add(Verb2Adv, AsIf_cAsInA);
        Aor_EMPTY.connections.add(A1sg_m, A1pl_yIz);

        AorPart_Ar_2Adj.connections.add(Adj2Noun);
        AorPart_Ar_2Adj.indirectConnections.add(Adj2Noun.allSuccessors());
        AorPart_Ir_2Adj.connections.add(Adj2Noun);
        AorPart_Ir_2Adj.indirectConnections.add(Adj2Noun.allSuccessors());
        AorPart_z_2Adj.connections.add(Adj2Noun);
        AorPart_z_2Adj.indirectConnections.add(Adj2Noun.allSuccessors());

        PastPart_dIk_2Noun.connections.add(A3sg_TEMPLATE);
        PastPart_dIk_2Noun.indirectConnections.add(POSSESSIVE_FORMS, CASE_FORMS);

        EvidPart_mIs_2Noun.connections.add(A3pl_lAr, A3sg_TEMPLATE);
        EvidPart_mIs_2Noun.indirectConnections.add(POSSESSIVE_FORMS, CASE_FORMS);

        // Oflazer suggests only with A3pl. I think A3sg is also possible.
        FutPart_yAcAk_2Noun.connections.add(A3pl_lAr, A3sg_TEMPLATE);
        FutPart_yAcAk_2Noun.indirectConnections.add(POSSESSIVE_FORMS, CASE_FORMS);

        PastPart_dIk_2Adj.connections.add(Adj2Noun);
        PastPart_dIk_2Adj.indirectConnections.add(Adj2Noun.allSuccessors());

        EvidPart_mIs_2Adj.connections.add(Adj2Noun);
        EvidPart_mIs_2Adj.indirectConnections.add(Adj2Noun.allSuccessors());

        FutPart_yAcAk_2Adj.connections.add(Adj2Noun);
        FutPart_yAcAk_2Noun.indirectConnections.add(Adj2Noun.allSuccessors());

        PresPart_yAn.connections.add(Adj2Noun);
        PresPart_yAn.indirectConnections.add(Adj2Noun.allSuccessors());

        Past_dI.connections.add(A1sg_m, A2sg_n, A3sg_Verb_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_Verb_lAr, CondCop_ysA, PastCop_ydI);

        Evid_mIs.connections.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr)
                .add(CondCop_ysA, PastCop_ydI, EvidCop_ymIs, While_ken, Cop_dIr);

        Cond_sA.connections.add(A1sg_m, A2sg_n, A3sg_Verb_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_Verb_lAr, PastCop_ydI, EvidCop_ymIs);

        PastCop_ydI.connections.add(PERSON_FORMS_COP);
        EvidCop_ymIs.connections.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr);
        EvidCop_ymIs.indirectConnections.add(Verb2Adv, AsIf_cAsInA);
        CondCop_ysA.connections.add(PERSON_FORMS_COP);
        Cop_dIr.connections.add(A3pl_Verb_lAr);

        // TODO: may be too broad
        Inf1_mAk.connections.add(A3sg_TEMPLATE);
        Inf1_mAk.indirectConnections.add(Pnon_TEMPLATE, Nom_TEMPLATE, Inst_ylA, Dat_yA, Abl_dAn, Acc_yI, Noun2Adj, Noun2Noun, Noun2VerbCopular);
        Inf1_mAk.indirectConnections.add(Noun2Adj.allSuccessors(), Noun2Noun.allSuccessors(), Noun2VerbCopular.allSuccessors());
        Inf2_mA.connections.add(Noun_TEMPLATE.connections);
        Inf2_mA.indirectConnections.add(Noun_TEMPLATE.indirectConnections);
        Inf3_yIs.connections.add(Noun_TEMPLATE.connections);
        Inf3_yIs.indirectConnections.add(Noun_TEMPLATE.indirectConnections);
        NotState_mAzlIk.connections.add(Noun_TEMPLATE.connections);
        NotState_mAzlIk.indirectConnections.add(Noun_TEMPLATE.indirectConnections);


        Abil_yA.connections.add(Neg_mA, Neg_m);
        Abil_yA.indirectConnections.add(Neg_mA.allSuccessors());

        Abil_yAbil.connections.add(Neg_mA.connections).add(Aor_Ir, Prog_Iyor).remove(Verb2VerbAbility);
        Abil_yAbil.indirectConnections.add(Neg_mA.indirectConnections);

        // TODO: removing false positives like geliveriver may be necessary

        KeepDoing_yAgor.connections.add(Pos_EMPTY.connections, Neg_mA.connections);
        KeepDoing_yAgor.indirectConnections.add(Pos_EMPTY.indirectConnections, Neg_mA.indirectConnections);

        KeepDoing2_yAdur.connections.add(Pos_EMPTY.connections, Neg_mA.connections);
        KeepDoing2_yAdur.indirectConnections.add(Pos_EMPTY.indirectConnections, Neg_mA.indirectConnections);

        EverSince_yAgel.connections.add(Pos_EMPTY.connections, Neg_mA.connections);
        EverSince_yAgel.indirectConnections.add(Pos_EMPTY.indirectConnections, Neg_mA.indirectConnections);

        Almost_yAyAz.connections.add(Pos_EMPTY.connections, Neg_mA.connections);
        Almost_yAyAz.indirectConnections.add(Pos_EMPTY.indirectConnections, Neg_mA.indirectConnections);

        Hastily_yIver.connections.add(Pos_EMPTY.connections, Neg_mA.connections);
        Hastily_yIver.indirectConnections.add(Pos_EMPTY.indirectConnections, Neg_mA.indirectConnections);

        Stay_yAkal.connections.add(Pos_EMPTY.connections, Neg_mA.connections);
        Stay_yAkal.indirectConnections.add(Pos_EMPTY.indirectConnections, Neg_mA.indirectConnections);

        Necess_mAlI.connections.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        Des_sA.connections.add(A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr, PastCop_ydI, EvidCop_ymIs);

        When_yIncA.connections.add(Adv2Noun);
        When_yIncA.indirectConnections.add(Adv2Noun.allSuccessors());

        //TODO ararkendi ararkenmiş legal?
        While_ken.connections.add(Adv2Adj);
        While_ken.indirectConnections.add(Rel_ki);

        // TODO: FeelLike_yAsI_2Noun, Agt_yIcI_Noun may be too broad.
        FeelLike_yAsI_2Noun.connections.add(Noun_TEMPLATE.connections);
        FeelLike_yAsI_2Noun.indirectConnections.add(Noun_TEMPLATE.indirectConnections);

        FeelLike_yAsI_2Adj.connections.add(Adj_TEMPLATE);

        Agt_yIcI_2Noun.connections.add(Noun_TEMPLATE.connections);
        Agt_yIcI_2Noun.indirectConnections.add(Noun_TEMPLATE.indirectConnections);

        Agt_yIcI_2Adj.connections.add(Adj_TEMPLATE);

        Opt_yA.connections.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_lIm, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        registerForms(
                Noun_TEMPLATE, Verb_TEMPLATE, Adj_TEMPLATE, Adv_TEMPLATE, Pnon_TEMPLATE,
                Noun_Comp_P3sg, Noun_Comp_P3sg_Root,

                Noun2Adj, Noun2Noun, Noun2Verb, Noun2VerbCopular,
                Adj2Adj, Adj2Adv, Adj2Noun, Adj2Verb,
                Verb2Adj, Verb2Verb, Verb2VerbCompounds, Verb2Noun, Verb2Adv,

                Pres_TEMPLATE,

                Noun_Default, Verb_Default, Adj_Default, Resemb_Adj_ImsI, Resemb_msI,
                Pass_InIl,
                Nom_TEMPLATE, Dat_yA, Dat_nA, Loc_dA, Loc_ndA, Abl_dAn, Abl_ndAn, Gen_nIn,
                Acc_yI, Acc_nI, Inst_ylA,
                Pnon_TEMPLATE, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, P3pl_lArI,
                Dim_cIk, Dim2_cAgIz,
                With_lI, Without_sIz, Rel_ki, Rel_kI,

                A1sg_yIm, A1sg_m, A1sg_TEMPLATE, A2sg_sIn, A2sg_n, A2sg_TEMPLATE, A2sg2_sAnA,
                A3sg_TEMPLATE, A3sg_Verb_TEMPLATE, A2sg3_yInIz, A3sg_sIn,
                A1pl_yIz, A1pl_k, A1pl_lIm, A1pl_TEMPLATE, A2pl_sInIz, A2pl_nIz, A2pl_yIn, A2pl_TEMPLATE, A2pl2_sAnIzA,
                A3pl_lAr, A3pl_Verb_lAr, A3pl_sInlAr, Agt_cI, Agt_yIcI_2Adj, Agt_yIcI_2Noun,

                Ness_lIk,
                Become_lAs, Become_Adj_lAs, Acquire_lAn,
                Resemb_ImsI, Resemb_msI, Related_sAl,
                Aor_Ir, Aor_Ar, Aor_z, Des_sA,
                Aor_EMPTY, AorPart_Ar_2Adj, AorPart_Ir_2Adj, AorPart_z_2Adj,
                Prog_Iyor, Prog2_mAktA, Fut_yAcAk,
                FutPart_yAcAk_2Adj, FutPart_yAcAk_2Noun, Past_dI, PastPart_dIk_2Noun, PastPart_dIk_2Adj,
                Evid_mIs, EvidPart_mIs_2Adj, EvidPart_mIs_2Noun, PresPart_yAn, Neg_mA, Neg_m, Cond_sA,
                Necess_mAlI, Opt_yA, Pass_In, Pass_nIl, Caus_t,
                Caus_tIr, Imp_TEMPLATE, Recip_Is, Recip_yIs, Reflex_In, Abil_yAbil, Abil_yA, Cop_dIr,
                PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken, NotState_mAzlIk, ActOf_mAcA,
                AsIf_cAsInA, AsLongAs_dIkcA, When_yIncA, FeelLike_yAsI_2Adj, FeelLike_yAsI_2Noun, SinceDoing_yAlI, ByDoing_yArAk, WithoutDoing_mAdAn,
                WithoutDoing2_mAksIzIn, AfterDoing_yIp, UnableToDo_yAmAdAn, InsteadOfDoing_mAktAnsA,
                KeepDoing_yAgor, KeepDoing2_yAdur, EverSince_yAgel,
                Almost_yAyAz, Hastily_yIver, Stay_yAkal, Inf1_mAk, Inf2_mA, Inf3_yIs, Ly_cA,
                Quite_cA, Equ_cA, Equ_ncA, UntilDoing_yAsIyA,
                A3pl_Comp_lAr, Interj_Main, Verb_Prog_Drop, PersPron_BenSen, PersPron_BanSan,
                Ordinal_IncI, Grouping_sAr);

/*
        Noun_TEMPLATE.add(CASE_FORMS, POSSESSIVE_FORMS, COPULAR_FORMS, PERSON_FORMS_N)
                .add(Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, Agt_cI, Resemb_msI,
                        Resemb_ImsI, Ness_lIk, Related_sAl, Become_lAs, Equ_cA);
        Noun_Exp_V.add(Dat_yA, Acc_yI, Gen_nIn, P1sg_Im, P2sg_In, P3sg_sI, P1pl_ImIz, P2pl_InIz, A1sg_yIm, A1pl_yIz, Resemb_ImsI);
        Noun_Exp_C.add(Noun_TEMPLATE.getSuccSetCopy()).remove(Noun_Exp_V.getSuccSetCopy());
        Noun_Comp_P3sg.add(COPULAR_FORMS, POSSESSIVE_FORMS)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .add(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz);
        Noun_Comp_P3sg_Root.add(With_lI, Without_sIz, Agt_cI, Resemb_msI, Resemb_ImsI, Ness_lIk, Related_sAl,
                P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, P3pl_lArI, A3pl_Comp_lAr);

        ProperNoun_Main
                .add(CASE_FORMS, POSSESSIVE_FORMS, COPULAR_FORMS, PERSON_FORMS_N)
                .add(Pl_lAr, Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, A3sg_TEMPLATE, Agt_cI, Ness_lIk);

        Verb_TEMPLATE.add(Prog_Iyor, Prog2_mAktA, Fut_yAcAk, Past_dI, Evid_mIs, Aor_Ir, AorPart_Ir)
                .add(Neg_mA, Neg_m, Abil_yAbil, Abil_yA, Caus_tIr, Opt_yA, Imp_TEMPLATE, Agt_yIcI, Des_sA)
                .add(Pass_nIl, NotState_mAzlIk, ActOf_mAcA, PastPart_dIk, EvidPart_mIs)
                .add(FutPart_yAcAk, PresPart_yAn, AsLongAs_dIkcA)
                .add(Inf1_mAk, Inf2_mA, Inf3_yIs)
                .add(When_yIncA, FeelLike_yAsI, SinceDoing_yAlI, ByDoing_yArAk, WithoutDoing_mAdAn, WithoutDoing2_mAksIzIn)
                .add(AfterDoing_yIp, When_yIncA, UnableToDo_yAmAdAn, InsteadOfDoing_mAktAnsA)
                .add(KeepDoing2_yAdur, KeepDoing_yAgor, EverSince_yAgel, Almost_yAyAz, Hastily_yIver, Stay_yAkal, Recip_Is)
                .add(NounDeriv_nIm, UntilDoing_yAsIyA);

        Verb_Exp_V.add(Opt_yA, Fut_yAcAk, Aor_Ar, AorPart_Ar, Prog_Iyor, PresPart_yAn, Pass_nIl,
                KeepDoing2_yAdur, KeepDoing_yAgor, EverSince_yAgel, Almost_yAyAz, Hastily_yIver, Stay_yAkal,
                When_yIncA, UnableToDo_yAmAdAn, FeelLike_yAsI, SinceDoing_yAlI, ByDoing_yArAk, Inf3_yIs, Abil_yA,
                Abil_yAbil, AfterDoing_yIp, Agt_yIcI, FutPart_yAcAk, Imp_EMPTY_V).remove(Imp_TEMPLATE);
        Verb_Exp_C.add(Verb_TEMPLATE.getSuccSetCopy())
                .remove(Verb_Exp_V.getSuccSetCopy()).remove(Aor_Ir, AorPart_Ir, Imp_TEMPLATE)
                .add(Imp_EMPTY_V);

        Verb_Prog_Drop.add(Prog_Iyor);

        Adv_TEMPLATE.add(COPULAR_FORMS);

        PersPron_TEMPLATE.add(CASE_FORMS).add(PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken);
        PersPron_BenSen.add(PersPron_TEMPLATE.getSuccSetCopy()).remove(Dat_yA);
        PersPron_BanSan.add(Dat_yA);

        Ques_mI.add(PERSON_FORMS_N).add(Cop_dIr, EvidCop_ymIs, PastCop_ydI);

        Adj_TEMPLATE.add(Noun_TEMPLATE.getSuccSetCopy()).add(Ly_cA, Become_lAs, Quite_cA).remove(Related_sAl);
        Adj_Exp_C.add(Noun_Exp_C.getSuccSetCopy()).add(Ly_cA, Become_lAs, Quite_cA);
        Adj_Exp_V.add(Noun_Exp_V.getSuccSetCopy());
        Become_lAs.add(Verb_TEMPLATE.getSuccSetCopy());
        Quite_cA.add(Noun_TEMPLATE.getSuccSetCopy()).remove(Related_sAl);

        Numeral_Main.add(COPULAR_FORMS, CASE_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N)
                .add(Ordinal_IncI, Grouping_sAr, With_lI, Without_sIz, Ness_lIk, Pl_lAr);

        Ordinal_IncI.add(Numeral_Main.getSuccSetCopy()).remove(Ordinal_IncI, Grouping_sAr);
        Grouping_sAr.add(With_lI, Ness_lIk, Abl_dAn).add(COPULAR_FORMS);

        Pl_lAr.add(CASE_FORMS, COPULAR_FORMS)
                .add(P1sg_Im, P2sg_In, P1pl_ImIz, P2pl_InIz, A1pl_yIz, A2pl_sInIz, Equ_cA);

        P1sg_Im.add(CASE_FORMS, COPULAR_FORMS).add(A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3sg_TEMPLATE, Equ_cA);
        P2sg_In.add(CASE_FORMS, COPULAR_FORMS).add(A1sg_yIm, A1pl_yIz, A3sg_TEMPLATE, Equ_cA);
        P3sg_sI.add(COPULAR_FORMS)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .add(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz, A3sg_TEMPLATE, Equ_ncA);
        P1pl_ImIz.add(CASE_FORMS, COPULAR_FORMS).add(A1sg_yIm, A2sg_sIn, A3sg_TEMPLATE, Equ_cA, A2pl_sInIz);
        P2pl_InIz.add(CASE_FORMS, COPULAR_FORMS).add(A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3sg_TEMPLATE, Equ_cA);
        P3pl_lArI.add(P3sg_sI.getSuccSetCopy()).add(A1sg_yIm, A2sg_sIn, A3sg_TEMPLATE, A1pl_yIz, A2pl_sInIz, Equ_ncA);

        Rel_ki.add(COPULAR_FORMS, PERSON_FORMS_N).add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA, Pl_lAr);
        Rel_kI.add(Rel_ki.getSuccSetCopy());
        Dat_yA.add(COPULAR_FORMS);
        Dat_nA.add(COPULAR_FORMS);

        Loc_dA.add(COPULAR_FORMS, PERSON_FORMS_N).add(Rel_ki);
        Loc_ndA.add(COPULAR_FORMS, PERSON_FORMS_N).add(Rel_ki);

        Inst_ylA.add(COPULAR_FORMS, PERSON_FORMS_N, POSSESSIVE_FORMS);

        Abl_dAn.add(COPULAR_FORMS, PERSON_FORMS_N);
        Abl_ndAn.add(COPULAR_FORMS, PERSON_FORMS_N);

        Gen_nIn.add(COPULAR_FORMS, PERSON_FORMS_N).add(Rel_ki);

        A1sg_yIm.add(Cop_dIr);
        A2sg_sIn.add(Cop_dIr);
        A3sg_TEMPLATE.add(Cop_dIr);
        A1pl_yIz.add(Cop_dIr);
        A2pl_sInIz.add(Cop_dIr);
        A3pl_lAr.add(Pl_lAr.getSuccSetCopy());
        A3pl_Comp_lAr.add(Pl_lAr.getSuccSetCopy());

        Dim_cIk.add(Loc_dA, Abl_dAn, Inst_ylA, P3pl_lArI, A2sg_sIn, A2pl_sInIz, A3pl_lAr, Pl_lAr, Inst_ylA).add(COPULAR_FORMS);
        Dim2_cAgIz.add(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N).add(Pl_lAr);

        With_lI.add(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N).add(Pl_lAr, Ness_lIk, Become_lAs, Ly_cA);
        Without_sIz.add(CASE_FORMS, COPULAR_FORMS, POSSESSIVE_FORMS, PERSON_FORMS_N).add(Pl_lAr, Ness_lIk, Become_lAs, Ly_cA);

        Resemb_msI.add(CASE_FORMS, PERSON_FORMS_N, COPULAR_FORMS, POSSESSIVE_FORMS)
                .add(Pl_lAr, Ness_lIk, With_lI, Without_sIz, Become_lAs);
        Resemb_ImsI.add(Resemb_ImsI.getSuccSetCopy());

        Ness_lIk.add(CASE_FORMS, POSSESSIVE_FORMS, COPULAR_FORMS, CASE_FORMS).add(Pl_lAr, Agt_cI, With_lI, Without_sIz, Equ_cA);

        Related_sAl.add(Adj_TEMPLATE.getSuccSetCopy()).remove(Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, Related_sAl,
                Resemb_msI, Resemb_msI);
        PastCop_ydI.add(PERSON_FORMS_COP);
        EvidCop_ymIs.add(A1sg_yIm, A2sg_sIn, A3sg_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_lAr, AsIf_cAsInA);
        CondCop_ysA.add(PERSON_FORMS_COP);
        Cop_dIr.add(A3pl_lAr);

        Neg_mA.add(Aor_z, AorPart_z, Aor_EMPTY, Prog2_mAktA, Imp_TEMPLATE, Opt_yA, Des_sA,
                Fut_yAcAk, Past_dI, Evid_mIs, Cond_sA, Abil_yAbil, Necess_mAlI, NotState_mAzlIk,
                ActOf_mAcA, PastPart_dIk, FutPart_yAcAk, EvidPart_mIs, Agt_yIcI)
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

        Set<SuffixForm> noParticipleSuff =
                Sets.newHashSet(Become_lAs, Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, Related_sAl,
                        Resemb_msI, Resemb_msI);

        AorPart_Ar.add(Adj_TEMPLATE.getSuccSetCopy()).remove(noParticipleSuff).add(AsIf_cAsInA);
        AorPart_Ir.add(AorPart_Ar.getSuccSetCopy());
        AorPart_z.add(AorPart_Ar.getSuccSetCopy());

        FutPart_yAcAk.add(Adj_Exp_C.getSuccSetCopy());
        NotState_mAzlIk.add(Adj_Exp_C.getSuccSetCopy());

        PresPart_yAn.add(AorPart_Ar.getSuccSetCopy());

        EvidPart_mIs.add(AorPart_Ar.getSuccSetCopy());

        PastPart_dIk.add(Adj_Exp_C.getSuccSetCopy()).remove(AsIf_cAsInA).remove(noParticipleSuff);

        Prog_Iyor.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA);
        Prog2_mAktA.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA);

        Fut_yAcAk.add(PERSON_FORMS_N, COPULAR_FORMS).add(Cond_sA, AsIf_cAsInA);

        Past_dI.add(A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr, CondCop_ysA, PastCop_ydI);
        Evid_mIs.add(PERSON_FORMS_N).add(CondCop_ysA, PastCop_ydI, EvidCop_ymIs, While_ken, Cop_dIr);

        Cond_sA.add(A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr, PastCop_ydI, EvidCop_ymIs);

        Imp_TEMPLATE.add(A2sg_TEMPLATE, A2sg2_sAnA, A2sg3_yInIz, A2pl2_sAnIzA, A2pl_yIn, A3sg_sIn, A3pl_sInlAr);
        Imp_EMPTY_C.add(A2sg_TEMPLATE, A2sg2_sAnA, A2pl2_sAnIzA, A3sg_sIn, A3pl_sInlAr);
        Imp_EMPTY_V.add(A2sg3_yInIz, A2pl_yIn);
        Agt_cI.add(CASE_FORMS, PERSON_FORMS_N, POSSESSIVE_FORMS, COPULAR_FORMS).add(Pl_lAr, Become_lAs, With_lI, Without_sIz, Ness_lIk);
        Agt_yIcI.add(Agt_cI.getSuccSetCopy());

        Abil_yAbil.add(Verb_TEMPLATE.getSuccSetCopy()).remove(Abil_yAbil, Abil_yA, Neg_mA, Pass_nIl).add(Cond_sA, Pass_In);
        Abil_yA.add(Neg_mA, Neg_m);

        Opt_yA.add(A1sg_yIm, A2sg_sIn, A3sg_TEMPLATE, A1pl_lIm, A2pl_sInIz, A3pl_lAr);
        Des_sA.add(COPULAR_FORMS).add(A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr, PastCop_ydI, EvidCop_ymIs);

        Caus_t.add(Verb_TEMPLATE.getSuccSetCopy()).add(Pass_nIl);
        Caus_tIr.add(Verb_TEMPLATE.getSuccSetCopy()).remove(Caus_tIr).add(Caus_t, Pass_nIl);

        Pass_nIl.add(Verb_TEMPLATE.getSuccSetCopy()).remove(Pass_In, Pass_nIl, Caus_tIr).add(Caus_t);
        Pass_In.add(Verb_TEMPLATE.getSuccSetCopy()).remove(Pass_In);

        Reflex_In.add(Verb_TEMPLATE.getSuccSetCopy());
        Recip_Is.add(Verb_TEMPLATE.getSuccSetCopy()).remove(Recip_Is);
        Recip_yIs.add(Verb_TEMPLATE.getSuccSetCopy()).remove(Recip_Is);

        Inf1_mAk.add(COPULAR_FORMS).add(Abl_dAn, Loc_dA, Inst_ylA);
        Inf2_mA.add(Noun_TEMPLATE.getSuccessors());
        Inf3_yIs.add(Noun_TEMPLATE.getSuccessors());

        NounDeriv_nIm.add(Noun_TEMPLATE.getSuccSetCopy());

        When_yIncA.add(Dat_yA);
        While_ken.add(Rel_ki).add(PastCop_ydI, EvidCop_ymIs, CondCop_ysA);
        FeelLike_yAsI.add(POSSESSIVE_FORMS).add(COPULAR_FORMS);

        KeepDoing_yAgor.add(Neg_mA.getSuccSetCopy()).remove(Aor_z).add(Neg_mA, Neg_m, Aor_Ir, Prog_Iyor);
        KeepDoing2_yAdur.add(KeepDoing_yAgor.getSuccSetCopy());
        EverSince_yAgel.add(KeepDoing_yAgor.getSuccSetCopy());
        Almost_yAyAz.add(KeepDoing_yAgor.getSuccSetCopy());
        Hastily_yIver.add(KeepDoing_yAgor.getSuccSetCopy());
        Stay_yAkal.add(KeepDoing_yAgor.getSuccSetCopy());
        Necess_mAlI.add(COPULAR_FORMS, PERSON_FORMS_N);
*/
    }

    @Override
    public SuffixForm getRootSet(DictionaryItem item, SuffixData successorConstraint) {
        if (successorConstraint.isEmpty()) {
            switch (item.primaryPos) {
                case Noun:
                    return Noun_Default;
                case Adjective:
                    return Adj_Default;
                case Verb:
                    return Verb_Default;
                case Adverb:
                    return Adv_Default;
                default:
                    return Noun_Default;
            }
        } else {
            SuffixForm template;
            switch (item.primaryPos) {
                case Noun:
                    template = Noun_TEMPLATE;
                    break;
                case Adjective:
                    template = Adj_TEMPLATE;
                    break;
                case Verb:
                    template = Verb_TEMPLATE;
                    break;
                default:
                    template = Noun_TEMPLATE;
            }
            SuffixForm copy = generateSetFromTemplate(template, successorConstraint);
            registerForm(copy);
            return copy;
        }
    }

    @Override
    public SuffixData[] defineSuccessorSuffixes(DictionaryItem item) {
        SuffixData original = new SuffixData();
        SuffixData modified = new SuffixData();

        PrimaryPos primaryPos = item.primaryPos;

        switch (primaryPos) {
            case Noun:
                getForNoun(item, original, modified);
                break;
            case Verb:
                getForVerb(item, original, modified);
                break;
            default:
                getForNoun(item, original, modified);
        }
        return new SuffixData[]{original, modified};
    }


    private void getForNoun(DictionaryItem item, SuffixData original, SuffixData modified) {

        for (RootAttr attribute : item.attrs.getAsList(RootAttr.class)) {
            switch (attribute) {
                case CompoundP3sg:
                    original.add(Noun_Comp_P3sg.allSuccessors());
                    modified.add(Noun_Comp_P3sg_Root.allSuccessors());
                    break;
                default:
                    break;
            }
        }
    }

    private void getForVerb(DictionaryItem item, SuffixData original, SuffixData modified) {
        original.add(Verb_TEMPLATE.allSuccessors().remove(Caus_t));
        modified.add(Verb_TEMPLATE.allSuccessors().remove(Caus_t));
        List<RootAttr> attrList = item.attrs.getAsList(RootAttr.class);
        for (RootAttr attribute : attrList) {
            switch (attribute) {
                case Aorist_A:
                    original.add(Aor_Ar, AorPart_Ar_2Adj);
                    original.remove(Aor_Ir, AorPart_Ir_2Adj);
                    if (!item.attrs.contains(RootAttr.ProgressiveVowelDrop)) {
                        modified.add(Aor_Ar, AorPart_Ar_2Adj);
                        modified.remove(Aor_Ir, AorPart_Ir_2Adj);
                    }
                    break;
                case Aorist_I:
                    original.add(Aor_Ir, AorPart_Ir_2Adj);
                    original.remove(Aor_Ar, AorPart_Ar_2Adj);
                    if (!item.attrs.contains(RootAttr.ProgressiveVowelDrop)) {
                        modified.add(Aor_Ir, AorPart_Ir_2Adj);
                        modified.remove(Aor_Ar, AorPart_Ar_2Adj);
                    }
                    break;
                case Passive_In:
                    original.add(Pass_In);
                    original.add(Pass_InIl);
                    original.remove(Pass_nIl);
                    break;
                case LastVowelDrop:
                    original.remove(Pass_nIl);
                    modified.clear().add(Pass_nIl, Verb2Verb);
                    break;
/*                    case VoicingOpt:
                        modified.remove(Verb_Exp_C.getSuccessors());
                        break;*/
                case ProgressiveVowelDrop:
                    original.remove(Prog_Iyor);
                    modified.clear().add(Pos_EMPTY, Prog_Iyor);
                    break;
                case NonTransitive:
                    original.remove(Caus_t, Caus_tIr);
                    modified.remove(Caus_t, Caus_tIr);
                    break;
                case Reflexive:
                    original.add(Reflex_In);
                    modified.add(Reflex_In);
                    break;
                case Reciprocal:
                    original.add(Recip_Is);
                    modified.add(Recip_Is);
                    break;
                case Causative_t:
                    original.remove(Caus_tIr);
                    original.add(Caus_t);
                    if (!item.attrs.contains(RootAttr.ProgressiveVowelDrop)) {
                        modified.remove(Caus_tIr);
                        modified.add(Caus_t);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
