package zemberek3.structure;

import zemberek3.structure.affix.Affix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurkicWordParse {

    private final Lemma lemma;
    private final List<Affix> suffixes;

    public TurkicWordParse(Lemma lemma, List<Affix> suffixes) {
        this.lemma = lemma;
        this.suffixes = suffixes;
    }

    public Lemma getLemma() {
        return lemma;
    }

    public List<Affix> getSuffixes() {
        return new ArrayList<Affix>(suffixes);
    }
}
