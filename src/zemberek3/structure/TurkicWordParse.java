package zemberek3.structure;

import zemberek3.structure.affix.TurkicSuffix;

import java.util.ArrayList;
import java.util.List;

public class TurkicWordParse {

    private final Lemma lemma;
    private final List<TurkicSuffix> suffixes;

    public TurkicWordParse(Lemma lemma, List<TurkicSuffix> suffixes) {
        this.lemma = lemma;
        this.suffixes = suffixes;
    }

    public Lemma getLemma() {
        return lemma;
    }

    public List<TurkicSuffix> getSuffixes() {
        return new ArrayList<TurkicSuffix>(suffixes);
    }
}