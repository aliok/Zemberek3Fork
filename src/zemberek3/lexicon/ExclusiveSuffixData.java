package zemberek3.lexicon;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExclusiveSuffixData {
    public Set<SuffixFormSet> accepts = Sets.newHashSet();
    public Set<SuffixFormSet> rejects = Sets.newHashSet();
    public Set<SuffixFormSet> onlyAccepts = Sets.newHashSet();
}
