package zemberek3.disambiguation;

import zemberek3.structure.WordParseResult;
import zemberek3.structure.ScoredItem;

import java.util.List;

public interface WordParseResultDisambiguator {

    /**
     * this method finds the best posible ParseResult fpr
     * @param words a sequence of words.
     * @return an iterator that iterates through each word's scored
     */
    List<ScoredItem<WordParseResult>> scoreListIterator(List<List<WordParseResult>> words);

}
