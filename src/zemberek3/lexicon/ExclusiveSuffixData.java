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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExclusiveSuffixData that = (ExclusiveSuffixData) o;

        if (!accepts.equals(that.accepts)) return false;
        if (!onlyAccepts.equals(that.onlyAccepts)) return false;
        if (!rejects.equals(that.rejects)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accepts.hashCode();
        result = 31 * result + rejects.hashCode();
        result = 31 * result + onlyAccepts.hashCode();
        return result;
    }
}
