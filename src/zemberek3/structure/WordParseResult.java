package zemberek3.structure;

import java.util.List;

public interface WordParseResult {

    Stem getStem();

    List<Affix> getSuffixes();

    List<Affix> getPrefixes();
}
