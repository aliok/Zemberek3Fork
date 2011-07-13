package zemberek3.lexicon;

import com.google.common.collect.Sets;
import zemberek3.lexicon.graph.SuffixData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExclusiveSuffixData {
    public SuffixData accepts = new SuffixData();
    public SuffixData rejects = new SuffixData();
    public SuffixData onlyAccepts = new SuffixData();
}
