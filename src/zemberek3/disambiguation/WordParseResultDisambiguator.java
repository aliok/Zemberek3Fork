package zemberek3.disambiguation;

import zemberek3.structure.ScoredItem;
import zemberek3.structure.WordParseResult;

import java.util.List;
import java.util.Iterator;

public interface WordParseResultDisambiguator {

    /**
     * provides an iterator that is generated with a sequence of input Char sequences.
     * Iterator carries a list of ScoredItem's. Each ScoredItem carries a score, and a WordPArseResult.
     * generally highest score would be the most likely parse result for a given word.
     * <p>this is a low level method.
     * @param words a sequence of words.
     * @return an iterator that iterates through each word's scored <code>WordParseResult</code>.
     */
    Iterator<List<ScoredItem<WordParseResult>>> scoreListIterator(List<CharSequence> words);

}
