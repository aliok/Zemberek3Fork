package z3.disambiguation;

import z3.structure.TurkicWordParse;
import z3.structure.ScoredItem;

import java.util.Iterator;
import java.util.List;

public interface WordParseResultDisambiguator {

    /**
     * provides an iterator that is generated with a sequence of input Char sequences.
     * Iterator carries a list of ScoredItem's. Each ScoredItem carries a score, and a WordParse.
     * generally highest score would be the most likely parse result for a given word.
     * <p>this is a low level method.
     * @param words a sequence of words.
     * @return an iterator that iterates through each word's scored <code>WordParse</code> list..
     */
    Iterator<List<ScoredItem<TurkicWordParse>>> scoreListIterator(List<String> words);

}
