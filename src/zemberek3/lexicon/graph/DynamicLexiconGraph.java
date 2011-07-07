package zemberek3.lexicon.graph;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.SuffixNodeGenerator;

import java.util.HashSet;
import java.util.Set;

public class DynamicLexiconGraph {

    Set<SuffixNode> suffixRootNodes = new HashSet<SuffixNode>();
    Set<StemNode> stemNodes = new HashSet<StemNode>();
    
    StemNodeGenerator stemNodeGenerator = new StemNodeGenerator();
    SuffixNodeGenerator suffixNodeGenerator = new SuffixNodeGenerator();

    public void addItem(DictionaryItem item) {

        StemNode[] stems = stemNodeGenerator.generate(item);
        for (StemNode stem : stems) {
            if(!stemNodes.contains(stem)) {
                stem.suffixRootNode = getSuffixRootNode(stem);
            } else {
                // duplicate stem!
            }
        }
    }

    public SuffixNode getSuffixRootNode(StemNode node) {
        return null;
    }


}
