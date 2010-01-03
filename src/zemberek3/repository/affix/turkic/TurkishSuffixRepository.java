package zemberek3.repository.affix.turkic;

import zemberek3.repository.affix.SuffixRepository;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.TurkicSuffix;

import java.util.*;

public class TurkishSuffixRepository implements SuffixRepository {

    List<TurkicSuffix> suffixList = new ArrayList<TurkicSuffix>();

    public TurkicSuffix getSuffixByType(AffixType type) {
        return null;
    }

    public TurkicSuffix getSuffixByName(String name) {
        return null;
    }

    public TurkicSuffix getRootSuffix(Lemma lemma) {
        return null;
    }

    private Map<AffixType, MutableSuffix> suffixBuilders = new HashMap<AffixType, MutableSuffix>();

    private static class MutableSuffix {
        AffixType type;
        List<MutableSuffix> builder = new ArrayList<MutableSuffix>();
        List<AffixType> successiveSuffixes = new ArrayList<AffixType>();
        String generators;

        public MutableSuffix(AffixType type) {
            this.type = type;
        }

        public MutableSuffix successiveSuffixes(AffixType... affixTypes) {
            successiveSuffixes.addAll(Arrays.asList(affixTypes));
            return this;
        }

        public MutableSuffix successiveSuffixes(List<AffixType>... affixTypes) {
            for (List<AffixType> affixTypeList : affixTypes) {
                successiveSuffixes.addAll(affixTypeList);
            }
            return this;
        }

        public TurkicSuffix build() {
            return new TurkicSuffix();
        }

    }

    private void initialize() {
        AffixType[] types = TurkishSuffixType.values();
        for (AffixType type : types) {
            suffixBuilders.put(type, new MutableSuffix(type));
        }

        TurkicSuffix nominalNoun = suffixBuilders.get(TurkishSuffixType.N_NOMINAL).build();

    }

}
