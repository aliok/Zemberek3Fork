package zemberek3.lexicon.tr;

import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.junit.Test;
import zemberek3.lexicon.NullSuffixForm;
import zemberek3.lexicon.SuffixForm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * User: Ali Ok (ali.ok@innflow.com)
 * Date: 2012-04-22
 * Time: 4:48 PM
 */
public class TurkishSuffixesRegisterTest {

    @Test
    public void testAllOnGraphAreRegistered() {
        StubTurkishSuffixes turkishSuffixes = new StubTurkishSuffixes();

        Set<SuffixForm> allRegistered = new HashSet<SuffixForm>();
        for (SuffixForm suffixForm : turkishSuffixes.getAllForms()) {
            allRegistered.add(suffixForm);
        }
        allRegistered.addAll(turkishSuffixes.getFormLookupMap().values());

        Set<SuffixForm> explored = findSuffixForms(allRegistered);

        Assert.assertEquals("Explored node count is not equal to registered node count, difference " + Sets.difference(explored, allRegistered),
                explored.size(), allRegistered.size());
    }

    private static Set<SuffixForm> findSuffixForms(Set<SuffixForm> allRegistered) {
        LinkedList<SuffixForm> toExplore = new LinkedList<SuffixForm>(allRegistered);
        Set<SuffixForm> explored = new HashSet<SuffixForm>();

        while (!toExplore.isEmpty()) {
            final SuffixForm next = toExplore.pop();
            if (explored.contains(next)) {
                continue;
            } else {
                for (SuffixForm connectedForm : next.allConnections()) {
                    if (explored.contains(connectedForm))
                        continue;

                    if (connectedForm instanceof NullSuffixForm) {
                        final NullSuffixForm nullForm = (NullSuffixForm) connectedForm;
                        final SuffixForm template = nullForm.getTemplate();
                        if (!explored.contains(template))
                            toExplore.add(template);
                    }

                    toExplore.add(connectedForm);

                }

                explored.add(next);
            }
        }

        return explored;
    }

    class StubTurkishSuffixes extends TurkishSuffixes {

        public Map<String, SuffixForm> getFormLookupMap() {
            return this.formLookupByName;
        }
    }


}
