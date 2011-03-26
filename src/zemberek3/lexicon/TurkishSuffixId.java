package zemberek3.lexicon;

public enum TurkishSuffixId {
    Pl,
    Dat,
    Loc,
    Abl,
    Gen,
    Acc,
    Inst,
    P1sg,
    P2sg,
    P3sg,
    P1pl,
    P2pl,
    P3pl,
    Dim,
    With,
    Without,
    Rel,
    A1sg,
    A2sg,
    A3sg,
    A1pl,
    A2pl,
    A3pl,
    Become,
    Aor,
    Prog,
    Fut,
    Past,
    Evid,
    Neg,
    Cond,
    Necess,
    Opt,
    Pass,
    Caus,
    Recip,
    Reflex,
    Abil,
    Cop,
    PastCop,
    EvidCop,
    CondCop,
    While,
    AfterDoing,

    NounRoot,
    VerbRoot,
    PronounRoot;

    public static TurkishSuffixId[] CASE = {Dat, Loc, Abl, Gen, Acc, Inst};
    public static TurkishSuffixId[] POSSESSIVE = {P1sg, P2sg, P1pl, P2pl, P3pl};
    public static TurkishSuffixId[] PERSON = {A1sg, A2sg, A1pl, A2pl, A3pl};
    public static TurkishSuffixId[] COPULAR = {Cop, PastCop, EvidCop, CondCop, While};

}
