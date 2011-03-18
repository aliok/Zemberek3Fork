package zemberek3.lexicon;

import java.util.*;

public class TurkishSuffixes {

    public static final TurkishSuffix Pl = new TurkishSuffix("Pl");
    public static final TurkishSuffix Dat = new TurkishSuffix("Dat");
    public static final TurkishSuffix Loc = new TurkishSuffix("Loc");
    public static final TurkishSuffix Abl = new TurkishSuffix("Abl");
    public static final TurkishSuffix Gen = new TurkishSuffix("Gen");
    public static final TurkishSuffix Acc = new TurkishSuffix("Acc");
    public static final TurkishSuffix Inst = new TurkishSuffix("Inst");

    public static final TurkishSuffix P1sg = new TurkishSuffix("P1sg");
    public static final TurkishSuffix P2sg = new TurkishSuffix("P2sg");
    public static final TurkishSuffix P3sg = new TurkishSuffix("P3sg");
    public static final TurkishSuffix P1pl = new TurkishSuffix("P1pl");
    public static final TurkishSuffix P2pl = new TurkishSuffix("P2pl");
    public static final TurkishSuffix P3pl = new TurkishSuffix("P3pl");

    public static final TurkishSuffix Dim = new TurkishSuffix("Dim");
    public static final TurkishSuffix With = new TurkishSuffix("With");
    public static final TurkishSuffix Without = new TurkishSuffix("Without");
    public static final TurkishSuffix Rel = new TurkishSuffix("Rel");

    public static final TurkishSuffix A1sg = new TurkishSuffix("A1sg");
    public static final TurkishSuffix A2sg = new TurkishSuffix("A2sg");
    public static final TurkishSuffix A3sg = new TurkishSuffix("A3sg");
    public static final TurkishSuffix A1pl = new TurkishSuffix("A1pl");
    public static final TurkishSuffix A2pl = new TurkishSuffix("A2pl");
    public static final TurkishSuffix A3pl = new TurkishSuffix("A3pl");

    public static final TurkishSuffix Become = new TurkishSuffix("Become");

    public static final TurkishSuffix Aor = new TurkishSuffix("Aor");
    public static final TurkishSuffix Prog = new TurkishSuffix("Prog");

    public static final TurkishSuffix Fut = new TurkishSuffix("Fut");
    public static final TurkishSuffix Past = new TurkishSuffix("Past");
    public static final TurkishSuffix Evid = new TurkishSuffix("Evid");
    public static final TurkishSuffix Neg = new TurkishSuffix("Neg");
    public static final TurkishSuffix Cond = new TurkishSuffix("Cond");
    public static final TurkishSuffix Necess = new TurkishSuffix("Necess");
    public static final TurkishSuffix Opt = new TurkishSuffix("Opt");
    public static final TurkishSuffix Pass = new TurkishSuffix("Pass");
    public static final TurkishSuffix Caus = new TurkishSuffix("Caus");
    public static final TurkishSuffix Recip = new TurkishSuffix("Recip");
    public static final TurkishSuffix Reflex = new TurkishSuffix("Reflex");
    public static final TurkishSuffix Abil = new TurkishSuffix("Abil");
    public static final TurkishSuffix Cont = new TurkishSuffix("Cont");


    public static final TurkishSuffix Cop = new TurkishSuffix("Cop");
    public static final TurkishSuffix PastCop = new TurkishSuffix("PastCop");
    public static final TurkishSuffix EvidCop = new TurkishSuffix("EvidCop");
    public static final TurkishSuffix CondCop = new TurkishSuffix("CondCop");
    public static final TurkishSuffix While = new TurkishSuffix("While");

    public static final TurkishSuffix AfterDoing = new TurkishSuffix("AfterDoing");
    public static final TurkishSuffix JustAfter = new TurkishSuffix("JustAfter");

    public static final TurkishSuffix[] NOUN_CASE = {Dat, Loc, Abl, Gen, Acc, Inst};
    public static final TurkishSuffix[] NOUN_POSS = {P1sg, P2sg, P1pl, P2pl, P3pl};
    public static final TurkishSuffix[] NOUN_PERSON = {A1sg, A2sg, A1pl, A2pl, A3pl};
    public static final TurkishSuffix[] COPULAR = {Cop, PastCop, EvidCop, CondCop, While};

    public static final List<TurkishSuffix> NOUN_ROOT_SUFFIXES = new ArrayList<TurkishSuffix>();

    static {
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(NOUN_CASE));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(NOUN_POSS));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(COPULAR));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(NOUN_PERSON));
        NOUN_ROOT_SUFFIXES.addAll(Arrays.asList(Dim, With, Without));
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

        Pl.addNode(new SuffixNode(Pl, "lAr"))
                .addSuccessors(NOUN_CASE, COPULAR)
                .addSuccessors(P1sg, P2sg, P1pl, P2pl, A1pl, A2pl);

        Dat.addNode(new SuffixNode(Dat, "+yA")).addSuccessors(COPULAR);
        Dat.addNode(new SuffixNode(Dat, "nA", Rel, P3sg, P3pl));

        Loc.addNode(new SuffixNode(Loc, ">dA")).addSuccessors(COPULAR);
        Loc.addNode(new SuffixNode(Loc, "ndA", Rel, P3sg, P3pl));

        Abl.addNode(new SuffixNode(Abl, ">dAn")).addSuccessors(COPULAR);
        Abl.addNode(new SuffixNode(Abl, "ndAn", Rel, P3sg, P3pl));

        Gen.addNode(new SuffixNode(Gen, "+nIn")).addSuccessors(COPULAR).addSuccessors(Rel);

        Acc.addNode(new SuffixNode(Acc, "+yI"))
                .addNode(new SuffixNode(Abl, "nI", Rel, P3sg, P3pl));

        P1sg.addNode(new SuffixNode(P1sg, "Im")).
                addSuccessors(NOUN_CASE, COPULAR).
                addSuccessors(A2sg, A2pl);

        P2sg.addNode(new SuffixNode(P2sg, "In")).
                addSuccessors(NOUN_CASE, COPULAR).
                addSuccessors(A1sg, A1pl);

        P3sg.addNode(new SuffixNode(P3sg, "+sI")).
                addSuccessors(NOUN_CASE, COPULAR).
                addSuccessors(A1sg, A2sg, A1pl, A2pl);


        Dim.addNode(new SuffixNode(Dim, ">cI~k")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl, With, Without);

        Dim.addNode(new SuffixNode(Dim, ">cağız")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl, With, Without);

        With.addNode(new SuffixNode(With, "lI")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl);

        Without.addNode(new SuffixNode(Without, "sIz")).
                addSuccessors(NOUN_CASE, COPULAR, NOUN_POSS, NOUN_PERSON).
                addSuccessors(Pl);

        Rel.addNode(new SuffixNode(Rel, "ki")).addSuccessors(NOUN_CASE, COPULAR, NOUN_PERSON);

        A1sg.addNode(new SuffixNode(A1sg, "+yIm")).addSuccessors(Cop);
        A2sg.addNode(new SuffixNode(A2sg, "sIn")).addSuccessors(Cop);
        A1pl.addNode(new SuffixNode(A1pl, "+yIz")).addSuccessors(Cop);
        A2pl.addNode(new SuffixNode(A2pl, "sInIz")).addSuccessors(Cop);
        A3pl.addNode(new SuffixNode(A3pl, "lAr")).addSuccessors(Cop);

        Cop.addNode(new SuffixNode(Cop, ">dIr"));
        PastCop.addNode(new SuffixNode(PastCop, "+y>dI")).addSuccessors(NOUN_PERSON);
        EvidCop.addNode(new SuffixNode(EvidCop, "+ymIş")).addSuccessors(NOUN_PERSON);
        CondCop.addNode(new SuffixNode(CondCop, "+ysA")).addSuccessors(NOUN_PERSON);
        While.addNode(new SuffixNode(While, "+yken"));
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
