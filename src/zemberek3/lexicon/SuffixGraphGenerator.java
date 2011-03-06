package zemberek3.lexicon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SuffixGraphGenerator {
    List<BoundaryNode> boundaryNodes;
    TurkishSuffixes suffixes = new TurkishSuffixes();


    public SuffixGraphGenerator(List<BoundaryNode> boundaryNodes) {
        this.boundaryNodes = boundaryNodes;
    }

    public void generate() {

        for (BoundaryNode boundaryNode : boundaryNodes) {

            // we get the possible suffixes that can attach to this boundary node looking at POS value.
            List<TurkishSuffix> suffixesToFollow = suffixes.getSuffixesForPos(boundaryNode.primaryPos);
            // if there is any exclusive suffixes defined for this  boundary node, only those suffixes can follow.
            if (boundaryNode.exclusiveSuffixes.size() > 0)
                suffixesToFollow = new ArrayList<TurkishSuffix>(boundaryNode.exclusiveSuffixes);
            // we eliminate the restricted suffixes from the list, if any.
            for (TurkishSuffix restricted : boundaryNode.restrictedSuffixes) {
                suffixesToFollow.remove(restricted);
            }

            // we iterate throuh the TurkıshSuffix'es that can follow this BoundaryNode and attach the proper variation
            // of the suffix (we call that variation a SuffixNode) to this boundary. Such as, for TurkishSuffix[Pl_lAr],
            // and BoundaryNode[last vowel frontal], variation will be the SuffixNode[ler]. This desicion will be made
            // using the matching morphemic attributes of the both side.
            for (TurkishSuffix turkishSuffix : suffixesToFollow) {
                for (SuffixNode suffixNode : turkishSuffix.nodes) {



                }
            }

        }

    }



}
