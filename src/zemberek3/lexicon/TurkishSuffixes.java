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
        Noun_TEMPLATE.directSuccessors.add(A3pl_lAr, A3pl_Comp_lAr, A3sg_TEMPLATE);
        Noun_TEMPLATE.successors
                .add(POSSESSIVE_FORMS, CASE_FORMS)
                .add(Cop_dIr, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken)
                .add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, Cop_dIr)
                .add(A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Acc_nI, Equ_ncA)
                .add(Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, Agt_cI, Resemb_msI, Resemb_ImsI, Ness_lIk, Related_sAl)
                .add(Become_lAs, Acquire_lAn, Pres_TEMPLATE)
                .add(Noun2Noun, Noun2Adj, Noun2Verb, Noun2VerbCopular);

        // default noun suffix form. we remove some suffixes so that words like araba-na (dative)
        Noun_Default.directSuccessors.add(A3pl_lAr, A3sg_TEMPLATE);
        Noun_Default.successors
                .add(Noun_TEMPLATE.successors)
                .remove(Dat_nA, Loc_ndA, Abl_ndAn, Acc_nI);

        Noun2Noun.directSuccessors.add(Dim_cIk, Dim2_cAgIz, Agt_cI, Ness_lIk);

        Noun2Adj.directSuccessors.add(With_lI, Without_sIz, Resemb_msI, Resemb_ImsI, Rel_ki, Related_sAl);

        Noun2Verb.directSuccessors.add(Become_lAs, Acquire_lAn);

        Noun2VerbCopular.directSuccessors.add(Pres_TEMPLATE, PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken);
        Noun2VerbCopular.successors.add(A1sg_m, A2sg_n, A3sg_Verb_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_Verb_lAr);
        Noun2VerbCopular.successors.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, Cop_dIr);

        Adj2Noun.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
        Adj2Noun.successors.add(Noun_TEMPLATE.successors).remove(Related_sAl, Become_lAs, Resemb_ImsI, Resemb_msI);

        Adj2Adj.directSuccessors.add(Quite_cA, Resemb_Adj_ImsI, Resemb_Adj_msI);

        Adj2Adv.directSuccessors.add(Ly_cA);

        Adv2Noun.directSuccessors.add(A3sg_TEMPLATE);
        Adv2Noun.successors.add(Pnon_TEMPLATE, Dat_yA);

        Adv2Adj.directSuccessors.add(Rel_ki); // ararkenki

        Adj2Verb.directSuccessors.add(Become_Adj_lAs, Acquire_lAn).add(COPULAR_FORMS);

        Verb2Verb.directSuccessors.add(Caus_t, Caus_tIr, Pass_In, Pass_nIl, Pass_InIl, Abil_yA);

        Verb2VerbCompounds.directSuccessors.add(KeepDoing_yAgor, KeepDoing2_yAdur, EverSince_yAgel, Almost_yAyAz, Hastily_yIver, Stay_yAkal);

        Verb2VerbAbility.directSuccessors.add(Abil_yAbil);

        Verb2Noun.directSuccessors.add(Inf1_mAk, Inf2_mA, Inf3_yIs, FeelLike_yAsI_2Noun, Agt_yIcI_2Noun, NotState_mAzlIk);
        Verb2Noun.successors.add(Noun_TEMPLATE.successors);

        Verb2NounPart.directSuccessors.add(PastPart_dIk_2Noun, EvidPart_mIs_2Noun, FutPart_yAcAk_2Noun);
        Verb2AdjPart.directSuccessors.add(PastPart_dIk_2Adj, EvidPart_mIs_2Adj, FutPart_yAcAk_2Adj, AorPart_Ar_2Adj, AorPart_Ir_2Adj, AorPart_z_2Adj, PresPart_yAn);

        Verb2Adv.directSuccessors.add(When_yIncA, SinceDoing_yAlI, UnableToDo_yAmAdAn, ByDoing_yArAk, WithoutDoing_mAdAn, WithoutDoing2_mAksIzIn)
                .add(InsteadOfDoing_mAktAnsA, AsLongAs_dIkcA, AfterDoing_yIp);

        Verb2Adj.directSuccessors.add(When_yIncA, FeelLike_yAsI_2Adj, Agt_yIcI_2Adj);

        Adv_TEMPLATE.directSuccessors.add(Adv2Noun, Adv2Adj);

        Adj_TEMPLATE.directSuccessors.add(Adj2Noun, Adj2Adj, Adj2Adv, Adj2Verb);
        Adj_TEMPLATE.successors.add(
                Adj2Noun.allSuccessors(),
                Adj2Adj.allSuccessors(),
                Adj2Adv.allSuccessors(),
                Adj2Verb.allSuccessors());

        Adj_Default.directSuccessors.add(Adj_TEMPLATE.directSuccessors);
        Adj_Default.successors.add(Adj_TEMPLATE.successors);

        // P3sg compound suffixes. (full form. such as zeytinyağı-na)
        Noun_Comp_P3sg.directSuccessors.add(A3sg_TEMPLATE);
        Noun_Comp_P3sg.successors.add(POSSESSIVE_FORMS)
                .add(Pnon_TEMPLATE, Nom_TEMPLATE)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA)
                .add(A1sg_yIm, A1pl_yIz, A2sg_sIn, A2pl_sInIz);

        // P3sg compound suffixes. (root form. such as zeytinyağ-lar-ı)
        Noun_Comp_P3sg_Root.directSuccessors.add(A3pl_Comp_lAr, A3sg_TEMPLATE); // A3pl_Comp_lAr is used, because zeytinyağ-lar is not allowed.
        Noun_Comp_P3sg_Root.successors
                .add(Pnon_TEMPLATE, Nom_TEMPLATE, With_lI, Without_sIz, Agt_cI, Resemb_msI, Resemb_ImsI, Ness_lIk, Related_sAl)
                .add(P3pl_lArI)
                .add(Noun2Noun.allSuccessors())
                .add(Noun2Noun)
                .add(Noun2Adj.allSuccessors())
                .add(Noun2Adj);

        // Proper noun default //TODO: should be a template
        ProperNoun_Main.directSuccessors.add(A3pl_lAr, A3sg_TEMPLATE);
        ProperNoun_Main.successors
                .add(CASE_FORMS, POSSESSIVE_FORMS)
                .add(Dim_cIk, Dim2_cAgIz, With_lI, Without_sIz, A3sg_TEMPLATE, Agt_cI, Ness_lIk);

        Verb_TEMPLATE.directSuccessors.add(Neg_mA, Neg_m, Pos_EMPTY, Verb2Verb);
        Verb_TEMPLATE.successors
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

        Verb_Default.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Verb_Default.successors.add(Verb_TEMPLATE.successors).remove(Caus_t);

        //---------------------------- Noun -----------------------------------------------------------------------

        A3pl_lAr.directSuccessors.add(POSSESSIVE_FORMS).remove(P3pl_lArI);
        A3pl_lAr.successors
                .add(Noun2VerbCopular)
                .add(Noun2VerbCopular.allSuccessors())
                .add(CASE_FORMS)
                .add(A1pl_yIz, A2pl_sInIz);

        //TODO: check below.
        A3pl_Comp_lAr.directSuccessors.add(A3pl_lAr.directSuccessors);
        A3pl_Comp_lAr.successors.add(CASE_FORMS)
                .add(A1pl_yIz, A2pl_sInIz);

        A3sg_TEMPLATE.directSuccessors.add(POSSESSIVE_FORMS);
        A3sg_TEMPLATE.successors
                .add(Noun_TEMPLATE.successors).remove(POSSESSIVE_FORMS)
                .add(Noun2Noun.allSuccessors()).add(Noun2Noun)
                .add(Noun2VerbCopular.allSuccessors())
                .add(Noun2Adj.allSuccessors().add(Noun2Adj));

        Nom_TEMPLATE.directSuccessors.add(Noun2Noun, Noun2Adj, Noun2Verb, Noun2VerbCopular);

        Nom_TEMPLATE.successors
                .add(Noun2Noun.allSuccessors())
                .add(Noun2Adj.allSuccessors())
                .add(Noun2VerbCopular.allSuccessors())
                .add(Noun2Verb.allSuccessors());

        Pres_TEMPLATE.directSuccessors.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr);
        Pres_TEMPLATE.successors.add(Cop_dIr);

        A1sg_yIm.directSuccessors.add(Cop_dIr);
        A2sg_sIn.directSuccessors.add(Cop_dIr);
        A3sg_Verb_TEMPLATE.directSuccessors.add(Cop_dIr);
        A1pl_yIz.directSuccessors.add(Cop_dIr);
        A2pl_sInIz.directSuccessors.add(Cop_dIr);
        A3pl_Verb_lAr.directSuccessors.add(Evid_mIs, Past_dI, Cond_sA, Cop_dIr);

        Dim_cIk.directSuccessors.add(Noun_Default.directSuccessors);
        Dim_cIk.successors.add(Noun_Default.allSuccessors().add(Noun2VerbCopular).remove(Dim_cIk, Dim2_cAgIz));

        Agt_cI.directSuccessors.add(Noun_Default.directSuccessors);
        Agt_cI.successors.add(Noun_Default.allSuccessors().add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors()).remove(Agt_cI));

        Dim2_cAgIz.directSuccessors.add(Noun_Default.directSuccessors);
        Dim2_cAgIz.successors.add(Noun_Default.allSuccessors().remove(Dim_cIk, Dim2_cAgIz));

        Ness_lIk.directSuccessors.add(Noun_Default.directSuccessors);
        Ness_lIk.successors.add(Noun_Default.allSuccessors().remove(Ness_lIk));

        Pnon_TEMPLATE.directSuccessors
                .add(CASE_FORMS)
                .add(Dat_nA, Loc_ndA, Abl_ndAn, Acc_nI);
        Pnon_TEMPLATE.successors
                .add(Nom_TEMPLATE.directSuccessors)
                .add(Noun2Noun.allSuccessors())
                .add(Noun2Verb.allSuccessors())
                .add(Noun2VerbCopular.allSuccessors())
                .add(Noun2Adj.allSuccessors());

        P1sg_Im.directSuccessors.add(CASE_FORMS);
        P1sg_Im.successors.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P2sg_In.directSuccessors.add(CASE_FORMS);
        P2sg_In.successors.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P3sg_sI.directSuccessors.add(Nom_TEMPLATE, Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA, Equ_ncA);
        P3sg_sI.successors.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P1pl_ImIz.directSuccessors.add(CASE_FORMS);
        P1pl_ImIz.successors.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P2pl_InIz.directSuccessors.add(CASE_FORMS);
        P2pl_InIz.successors.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        P3pl_lArI.directSuccessors.add(CASE_FORMS);
        P3pl_lArI.successors.add(Noun2VerbCopular).add(Noun2VerbCopular.allSuccessors());

        With_lI.directSuccessors.add(Adj_TEMPLATE.directSuccessors);
        With_lI.successors.add(Adj_TEMPLATE.successors);

        Without_sIz.directSuccessors.add(Adj_TEMPLATE.directSuccessors);
        Without_sIz.successors.add(Adj_TEMPLATE.successors);

        // Noun->Adj derivation elmadaki=elma+Loc-Adj+Rel
        Loc_dA.directSuccessors.add(Noun2Adj, Noun2VerbCopular);
        Loc_dA.successors.add(Rel_ki).add(Noun2VerbCopular.allSuccessors());

        Loc_ndA.directSuccessors.add(Noun2Adj, Noun2VerbCopular);
        Loc_ndA.successors.add(Rel_ki).add(Noun2VerbCopular.allSuccessors());

        Dat_nA.directSuccessors.add(Noun2VerbCopular);
        Dat_nA.successors.add(Noun2VerbCopular.allSuccessors());

        Dat_yA.directSuccessors.add(Noun2VerbCopular);
        Dat_yA.successors.add(Noun2VerbCopular.allSuccessors());

        Gen_nIn.directSuccessors.add(Noun2VerbCopular);
        Gen_nIn.successors.add(Noun2VerbCopular.allSuccessors());

        Dat_yA.directSuccessors.add(Noun2VerbCopular);
        Dat_yA.successors.add(Noun2VerbCopular.allSuccessors());

        Abl_dAn.directSuccessors.add(Noun2VerbCopular);
        Abl_dAn.successors.add(Noun2VerbCopular.allSuccessors());

        Abl_ndAn.directSuccessors.add(Noun2VerbCopular);
        Abl_ndAn.successors.add(Noun2VerbCopular.allSuccessors());

        Inst_ylA.directSuccessors.add(Noun2VerbCopular);
        Inst_ylA.successors.add(Noun2VerbCopular.allSuccessors());

        Equ_cA.directSuccessors.add(Noun2VerbCopular);
        Equ_cA.successors.add(Noun2VerbCopular.allSuccessors());

        Rel_ki.directSuccessors.add(Adj2Noun);
        Rel_ki.successors.add(Adj2Noun.successors).add(A3sg_TEMPLATE, Pnon_TEMPLATE, Dat_nA, Loc_ndA, Abl_ndAn, Gen_nIn, Acc_nI, Inst_ylA, Equ_ncA);

        Resemb_msI.directSuccessors.add(Adj_TEMPLATE.directSuccessors);
        Resemb_msI.successors.add(Adj_TEMPLATE.successors);

        Resemb_ImsI.directSuccessors.add(Adj_TEMPLATE.directSuccessors);
        Resemb_ImsI.successors.add(Adj_TEMPLATE.successors);

        Resemb_Adj_msI.directSuccessors.add(Adj_TEMPLATE.directSuccessors);
        Resemb_Adj_msI.successors.add(Adj_TEMPLATE.successors);

        Resemb_Adj_ImsI.directSuccessors.add(Adj_TEMPLATE.directSuccessors);
        Resemb_Adj_ImsI.successors.add(Adj_TEMPLATE.successors);

        //---------------------------- Adjective -----------------------------------------------------------------------

        Become_lAs.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Become_lAs.successors.add(Verb_TEMPLATE.successors);

        Acquire_lAn.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Acquire_lAn.successors.add(Verb_TEMPLATE.successors);

        Become_Adj_lAs.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Become_Adj_lAs.successors.add(Verb_TEMPLATE.successors);

        Quite_cA.directSuccessors.add(Adj_TEMPLATE.directSuccessors);

        Ly_cA.directSuccessors.add(Adv_TEMPLATE.directSuccessors);

        //---------------------------- Verb ----------------------------------------------------------------------------

        Pos_EMPTY.directSuccessors
                .add(Verb2VerbCompounds, Verb2Noun, Verb2Adv, Verb2Adj, Verb2AdjPart, Verb2NounPart, Verb2VerbAbility,
                        Imp_TEMPLATE, Prog_Iyor, Prog2_mAktA, Fut_yAcAk, Aor_Ar, Aor_Ir, Past_dI, Evid_mIs)
                .add(Cond_sA, Necess_mAlI, Opt_yA, Des_sA);
        Pos_EMPTY.successors
                .add(Verb_Default.successors)
                .add(A2pl2_sAnIzA, A2pl_yIn)
                .add(Verb2AdjPart.directSuccessors, Verb2NounPart.directSuccessors)
                .remove(Neg_m, Neg_mA);

        Neg_mA.directSuccessors.add(Verb2VerbCompounds, Verb2VerbAbility, Verb2Noun, Verb2Verb, Verb2Adv, Verb2Adj, Verb2AdjPart, Verb2NounPart,
                Aor_z, Aor_EMPTY, Prog2_mAktA, Imp_TEMPLATE, Opt_yA, Des_sA,
                Fut_yAcAk, Past_dI, Evid_mIs, Necess_mAlI, NotState_mAzlIk,
                ActOf_mAcA);

        Neg_mA.successors.add(Verb2VerbCompounds.directSuccessors, Verb2AdjPart.directSuccessors, Verb2NounPart.directSuccessors,
                Verb2Noun.directSuccessors, Verb2Adv.directSuccessors, Verb2Adj.directSuccessors)
                .add(A2sg_TEMPLATE, A1sg_yIm, A2sg_sIn, A2sg2_sAnA, A2sg3_yInIz, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A2pl2_sAnIzA, A2pl_yIn, A3pl_Verb_lAr, A3sg_sIn, A3pl_sInlAr)
                .add(Abil_yAbil);

        Neg_m.directSuccessors.add(Prog_Iyor);

        Imp_TEMPLATE.directSuccessors.add(A2sg_TEMPLATE, A2sg2_sAnA, A2sg3_yInIz, A2pl2_sAnIzA, A2pl_yIn, A3sg_sIn, A3pl_sInlAr);

        Caus_t.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Caus_t.successors.add(Verb_TEMPLATE.allSuccessors()).add(Pass_nIl).add(Caus_tIr).remove(Caus_t);

        Caus_tIr.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Caus_tIr.successors.add(Verb_TEMPLATE.allSuccessors()).add(Pass_nIl).add(Caus_t).remove(Caus_tIr);

        Pass_nIl.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Pass_nIl.successors.add(Verb_TEMPLATE.allSuccessors()).remove(Caus_t, Caus_tIr, Pass_nIl, Pass_InIl, Pass_In);

        Pass_In.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Pass_In.successors.add(Verb_TEMPLATE.allSuccessors()).remove(Caus_t, Caus_tIr, Pass_nIl, Pass_InIl, Pass_In);

        Pass_InIl.directSuccessors.add(Verb_TEMPLATE.directSuccessors);
        Pass_InIl.successors.add(Verb_TEMPLATE.allSuccessors()).remove(Caus_t, Caus_tIr, Pass_nIl, Pass_InIl, Pass_In);

        // TODO: geliyorlarmış and geliyormuşlar are parsed.
        Prog_Iyor.directSuccessors.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);
        Prog2_mAktA.directSuccessors.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        Fut_yAcAk.directSuccessors.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        Aor_Ar.directSuccessors.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);
        Aor_Ir.directSuccessors.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);
        Aor_z.directSuccessors.add(A3sg_Verb_TEMPLATE, A3sg_sIn).add(PastCop_ydI, EvidCop_ymIs, CondCop_ysA, While_ken, Cop_dIr);
        Aor_EMPTY.directSuccessors.add(A1sg_m, A1pl_yIz);

        AorPart_Ar_2Adj.directSuccessors.add(Adj2Noun);
        AorPart_Ar_2Adj.successors.add(Adj2Noun.allSuccessors());
        AorPart_Ir_2Adj.directSuccessors.add(Adj2Noun);
        AorPart_Ir_2Adj.successors.add(Adj2Noun.allSuccessors());
        AorPart_z_2Adj.directSuccessors.add(Adj2Noun);
        AorPart_z_2Adj.successors.add(Adj2Noun.allSuccessors());

        PastPart_dIk_2Noun.directSuccessors.add(A3sg_TEMPLATE);
        PastPart_dIk_2Noun.successors.add(POSSESSIVE_FORMS, CASE_FORMS);

        EvidPart_mIs_2Noun.directSuccessors.add(A3pl_lAr, A3sg_TEMPLATE);
        EvidPart_mIs_2Noun.successors.add(POSSESSIVE_FORMS, CASE_FORMS);

        // Oflazer suggests only with A3pl. I think A3sg is also possible.
        FutPart_yAcAk_2Noun.directSuccessors.add(A3pl_lAr, A3sg_TEMPLATE);
        FutPart_yAcAk_2Noun.successors.add(POSSESSIVE_FORMS, CASE_FORMS);

        PastPart_dIk_2Adj.directSuccessors.add(Adj2Noun);
        PastPart_dIk_2Adj.successors.add(Adj2Noun.allSuccessors());

        EvidPart_mIs_2Adj.directSuccessors.add(Adj2Noun);
        EvidPart_mIs_2Adj.successors.add(Adj2Noun.allSuccessors());

        FutPart_yAcAk_2Adj.directSuccessors.add(Adj2Noun);
        FutPart_yAcAk_2Noun.successors.add(Adj2Noun.allSuccessors());

        PresPart_yAn.directSuccessors.add(Adj2Noun);
        PresPart_yAn.successors.add(Adj2Noun.allSuccessors());

        Past_dI.directSuccessors.add(A1sg_m, A2sg_n, A3sg_Verb_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_Verb_lAr, CondCop_ysA, PastCop_ydI);

        Evid_mIs.directSuccessors.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr)
                .add(CondCop_ysA, PastCop_ydI, EvidCop_ymIs, While_ken, Cop_dIr);

        Cond_sA.directSuccessors.add(A1sg_m, A2sg_n, A3sg_Verb_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_Verb_lAr, PastCop_ydI, EvidCop_ymIs);

        PastCop_ydI.directSuccessors.add(PERSON_FORMS_COP);
        EvidCop_ymIs.directSuccessors.add(A1sg_yIm, A2sg_sIn, A3sg_Verb_TEMPLATE, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr, AsIf_cAsInA);
        CondCop_ysA.directSuccessors.add(PERSON_FORMS_COP);
        Cop_dIr.directSuccessors.add(A3pl_Verb_lAr);

        // TODO: may be too broad
        Inf1_mAk.directSuccessors.add(A3sg_TEMPLATE);
        Inf1_mAk.successors.add(Pnon_TEMPLATE, Nom_TEMPLATE, Inst_ylA, Dat_yA, Abl_dAn, Acc_yI, Noun2Adj, Noun2Noun, Noun2VerbCopular);
        Inf1_mAk.successors.add(Noun2Adj.allSuccessors(), Noun2Noun.allSuccessors(), Noun2VerbCopular.allSuccessors());
        Inf2_mA.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
        Inf2_mA.successors.add(Noun_TEMPLATE.successors);
        Inf3_yIs.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
        Inf3_yIs.successors.add(Noun_TEMPLATE.successors);
        NotState_mAzlIk.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
        NotState_mAzlIk.successors.add(Noun_TEMPLATE.successors);


        Abil_yA.directSuccessors.add(Neg_mA, Neg_m);
        Abil_yA.successors.add(Neg_mA.allSuccessors());

        Abil_yAbil.directSuccessors.add(Neg_mA.directSuccessors).add(Aor_Ir, Prog_Iyor).remove(Verb2VerbAbility);
        Abil_yAbil.successors.add(Neg_mA.successors);

        // TODO: removing false positives like geliveriver may be necessary

        KeepDoing_yAgor.directSuccessors.add(Pos_EMPTY.directSuccessors, Neg_mA.directSuccessors);
        KeepDoing_yAgor.successors.add(Pos_EMPTY.successors, Neg_mA.successors);

        KeepDoing2_yAdur.directSuccessors.add(Pos_EMPTY.directSuccessors, Neg_mA.directSuccessors);
        KeepDoing2_yAdur.successors.add(Pos_EMPTY.successors, Neg_mA.successors);

        EverSince_yAgel.directSuccessors.add(Pos_EMPTY.directSuccessors, Neg_mA.directSuccessors);
        EverSince_yAgel.successors.add(Pos_EMPTY.successors, Neg_mA.successors);

        Almost_yAyAz.directSuccessors.add(Pos_EMPTY.directSuccessors, Neg_mA.directSuccessors);
        Almost_yAyAz.successors.add(Pos_EMPTY.successors, Neg_mA.successors);

        Hastily_yIver.directSuccessors.add(Pos_EMPTY.directSuccessors, Neg_mA.directSuccessors);
        Hastily_yIver.successors.add(Pos_EMPTY.successors, Neg_mA.successors);

        Stay_yAkal.directSuccessors.add(Pos_EMPTY.directSuccessors, Neg_mA.directSuccessors);
        Stay_yAkal.successors.add(Pos_EMPTY.successors, Neg_mA.successors);

        Necess_mAlI.directSuccessors.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_yIz, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

        Des_sA.directSuccessors.add(A1sg_m, A2sg_n, A3sg_TEMPLATE, A1pl_k, A2pl_nIz, A3pl_lAr, PastCop_ydI, EvidCop_ymIs);

        When_yIncA.directSuccessors.add(Adv2Noun);
        When_yIncA.successors.add(Adv2Noun.allSuccessors());

        //TODO ararkendi ararkenmiş legal?
        While_ken.directSuccessors.add(Adv2Adj);
        While_ken.successors.add(Rel_ki);

        // TODO: FeelLike_yAsI_2Noun, Agt_yIcI_Noun may be too broad.
        FeelLike_yAsI_2Noun.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
        FeelLike_yAsI_2Noun.successors.add(Noun_TEMPLATE.successors);

        FeelLike_yAsI_2Adj.directSuccessors.add(Adj_TEMPLATE);

        Agt_yIcI_2Noun.directSuccessors.add(Noun_TEMPLATE.directSuccessors);
        Agt_yIcI_2Noun.successors.add(Noun_TEMPLATE.successors);

        Agt_yIcI_2Adj.directSuccessors.add(Adj_TEMPLATE);

        Opt_yA.directSuccessors.add(A3sg_Verb_TEMPLATE, A1sg_yIm, A2sg_sIn, A1pl_lIm, A2pl_sInIz, A3pl_Verb_lAr).add(COPULAR_FORMS);

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
