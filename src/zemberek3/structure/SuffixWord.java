package zemberek3.structure;

import java.util.Collections;
import java.util.List;

public class SuffixWord implements WordParseResult {

    Stem stem;
    List<Affix> suffixes;

    public SuffixWord(Stem stem, List<Affix> suffixes) {
        this.stem = stem;
        this.suffixes = suffixes;
    }

    public Stem getStem() {
        return stem;
    }

    public List<Affix> getSuffixes() {
        return Collections.emptyList();
    }

    public List<Affix> getPrefixes() {
        return Collections.emptyList();
    }
}
