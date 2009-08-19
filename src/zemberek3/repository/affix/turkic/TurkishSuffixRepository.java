package zemberek3.repository.affix.turkic;

import zemberek3.repository.affix.SuffixRepository;
import zemberek3.structure.Lemma;
import zemberek3.structure.affix.Affix;
import zemberek3.structure.affix.TurkicSuffix;

import java.util.*;

public class TurkishSuffixRepository implements SuffixRepository {

    List<TurkicSuffix> suffixList = new ArrayList<TurkicSuffix>();

    public Affix getSuffixByType(AffixType type) {
        return null;
    }

    public Affix getSuffixByName(String name) {
        return null;
    }

    public Affix getRootSuffix(Lemma lemma) {
        return null;
    }

    private Map<AffixType, SuffixBuilder> suffixBuilders = new HashMap<AffixType, SuffixBuilder>();

    static class SuffixBuilder {
        AffixType type;
        List<SuffixBuilder> builder = new ArrayList<SuffixBuilder>();
        List<AffixType> successiveSuffixes = new ArrayList<AffixType>();

        public SuffixBuilder(AffixType type) {
            this.type = type;
        }

        public SuffixBuilder successiveSuffixes(AffixType... affixTypes) {
            successiveSuffixes.addAll(Arrays.asList(affixTypes));
            return this;
        }

        public SuffixBuilder successiveSuffixes(List<AffixType>... affixTypes) {
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
            suffixBuilders.put(type, new SuffixBuilder(type));
        }

        TurkicSuffix nominalNoun = suffixBuilders.get(TurkishSuffixType.N_NOMINAL).build();

    }

}
