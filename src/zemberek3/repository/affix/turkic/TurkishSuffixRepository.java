package zemberek3.repository.affix.turkic;

import zemberek3.repository.affix.SuffixRepository;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.TurkicSuffix;

import java.util.*;

public class TurkishSuffixRepository implements SuffixRepository {

    List<TurkicSuffix> suffixList = new ArrayList<TurkicSuffix>();

    public TurkicSuffix getSuffixByType(TurkicSuffixType type) {
        return null;
    }

    public TurkicSuffix getSuffixByName(String name) {
        return null;
    }

    public TurkicSuffix getRootSuffix(Lemma lemma) {
        return null;
    }

    private Map<TurkicSuffixType, MutableSuffix> suffixBuilders = new HashMap<TurkicSuffixType, MutableSuffix>();

    private static class MutableSuffix {
        TurkicSuffixType type;
        List<MutableSuffix> builder = new ArrayList<MutableSuffix>();
        List<TurkicSuffixType> successiveSuffixes = new ArrayList<TurkicSuffixType>();
        String generators;

        public MutableSuffix(TurkicSuffixType type) {
            this.type = type;
        }

        public MutableSuffix successiveSuffixes(TurkicSuffixType... affixTypes) {
            successiveSuffixes.addAll(Arrays.asList(affixTypes));
            return this;
        }

        public MutableSuffix successiveSuffixes(List<TurkicSuffixType>... affixTypes) {
            for (List<TurkicSuffixType> affixTypeList : affixTypes) {
                successiveSuffixes.addAll(affixTypeList);
            }
            return this;
        }

        public TurkicSuffix build() {
            return new TurkicSuffix();
        }

    }

    private void initialize() {
        TurkicSuffixType[] types = TurkicSuffixType.values();
        for (TurkicSuffixType type : types) {
            suffixBuilders.put(type, new MutableSuffix(type));
        }

        TurkicSuffix nominalNoun = suffixBuilders.get(TurkicSuffixType.N_NOMINAL).build();

    }

}
