package zemberek3.lexicon;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SuffixGraphGenerator {
    List<SuffixFormSet> rootFormSets;
    SuffixFormGenerator formGenerator = new SuffixFormGenerator();

    public SuffixGraphGenerator(List<SuffixFormSet> rootFormSets) {
        this.rootFormSets = rootFormSets;
    }

    public void generate() {
        generate(rootFormSets, new HashSet<SuffixFormSet>());
    }

    private void generate(Iterable<SuffixFormSet> formSets, Set<SuffixFormSet> finishedSet) {
        List<SuffixFormSet> toProcess = new ArrayList<SuffixFormSet>();
        for (SuffixFormSet rootFormSet : formSets) {
            System.out.println("Processing:" + rootFormSet);
            for (SuffixFormSet succSet : rootFormSet.getSuccessors()) {
                if (finishedSet.contains(succSet))
                    continue;
                else
                    toProcess.add(succSet);
                for (SuffixForm form : rootFormSet.getFormIterator()) {
                    SuffixForm formInSuccessor = formGenerator.getForm(form.attributes, succSet.generation);
                    formInSuccessor = succSet.addOrReturnExisting(formInSuccessor);
                    form.addSuccForm(formInSuccessor);
                }
            }
            finishedSet.add(rootFormSet);
        }
        if (toProcess.size() == 0)
            return;
        generate(toProcess, finishedSet);
    }

    public static void main(String[] args) throws IOException {

        List<LexiconItem> items = new TurkishLexiconLoader().load(new File("test/data/dev-lexicon.txt"));
        TurkishSuffixes suffixes = new TurkishSuffixes();
        LexiconGraphGenerator generator = new LexiconGraphGenerator(items, suffixes);
        generator.generate();
        List<SuffixFormSet> sets = Arrays.asList(
                suffixes.getFormSet(TurkishSuffixFormId.Noun_Main),
                suffixes.getFormSet(TurkishSuffixFormId.Verb_Main));
        SuffixGraphGenerator suffixGraphGenerator = new SuffixGraphGenerator(sets);
        suffixGraphGenerator.generate();
        SuffixFormSet set = suffixes.getFormSet(TurkishSuffixFormId.Pl_lAr);
        System.out.println("Form Set:" + set.generation);
        for (SuffixForm form : set.getFormIterator()) {
            System.out.println("   Form: " + form.surface);
            for (SuffixForm sform : form.getSuccForms()) {
                System.out.println("        -" + sform.surface);
            }
        }
        System.out.println("----");
    }
}



