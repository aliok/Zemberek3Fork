package zemberek3.lexicon;

import java.util.ArrayList;
import java.util.List;

public class SuffixGraphGenerator {
    List<SuffixFormSet> rootForms;
    TurkishSuffixes suffixes = new TurkishSuffixes();

    public SuffixGraphGenerator(List<SuffixFormSet> rootForms) {
        this.rootForms = rootForms;
    }

    public void generate() {
        for (SuffixFormSet rootForm : rootForms) {
            for (SuffixFormSet form : rootForm.getSuccessors()) {
                // needs some magic to build the suffix forms and connections recursively.
            }
        }
    }



}
