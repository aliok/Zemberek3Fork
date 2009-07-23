package zemberek3.structure;

import zemberek3.structure.affix.Affix;

import java.util.List;

public interface WordParseResult {

    Stem getStem();

    List<Affix> getSuffixes();

    List<Affix> getPrefixes();
}
