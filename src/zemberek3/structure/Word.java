package zemberek3.structure;

import java.util.List;

public interface Word {

    Stem getStem();

    List<Affix> getSuffixes();
}
