package zemberek3.structure;

import zemberek3.structure.affix.Affix;

import java.util.List;

public interface WordParse {

    Lemma getLemma();

    List<Affix> getSuffixes();

    List<Affix> getPrefixes();
}
