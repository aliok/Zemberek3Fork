package zemberek3.structure;

import java.util.List;

/**
 * Represents an affix. (prefix or suffix)
 */
public interface Affix {

    List<Affix> successors();

    List<Affix> predecessors();
}
