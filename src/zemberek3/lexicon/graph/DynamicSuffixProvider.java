package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import zemberek3.lexicon.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicSuffixProvider implements SuffixProvider {

    protected Map<SuffixForm, SuffixForm> formSetLookup = Maps.newHashMap();
    protected Map<String, Suffix> suffixLookup = Maps.newHashMap();
    protected ArrayListMultimap<String, SuffixForm> formsPerSuffix = ArrayListMultimap.create(100, 2);
    private Map<NullSuffixForm, NullSuffixForm> nullFormsUnprocessed = Maps.newHashMap();

    protected IdMaker idMaker = new IdMaker(3);
    protected static AtomicInteger indexMaker = new AtomicInteger();

    public static NullSuffixForm getNull(String suffixId, SuffixFormTemplate template) {
        return new NullSuffixForm(getNewIndex(), suffixId, template);
    }

    public static SuffixForm getNull(String suffixId, SuffixFormTemplate template, TerminationType type) {
        return new NullSuffixForm(getNewIndex(), suffixId, template, type);
    }

    public static SuffixForm getForm(Suffix suffix, String generationStr) {
        return new SuffixForm(getNewIndex(), suffix, generationStr);
    }

    public static SuffixForm getForm(String id, Suffix suffix, String generationStr) {
        return new SuffixForm(getNewIndex(), id, suffix, generationStr);
    }

    public static SuffixForm getForm(String id, Suffix suffix, String generationStr, TerminationType type) {
        return new SuffixForm(getNewIndex(), id, suffix, generationStr, type);
    }

    public static SuffixFormTemplate getTemplate(String id, Suffix suffix) {
        return new SuffixFormTemplate(getNewIndex(), id, suffix);
    }

    public static SuffixFormTemplate getTemplate(String id, Suffix suffix, TerminationType type) {
        return new SuffixFormTemplate(getNewIndex(), id, suffix, type);
    }

    private static int getNewIndex() {
        return indexMaker.getAndIncrement();
    }

    public Suffix getSuffixById(String suffixId) {
        return suffixLookup.get(suffixId);
    }

    public List<SuffixForm> getFormsBySuffixId(String suffixId) {
        return formsPerSuffix.get(suffixId);
    }

    public Iterable<SuffixForm> getAllForms() {
        return formSetLookup.keySet();
    }

    public int getFormCount() {
        return formSetLookup.size();
    }

    @Override
    public SuffixData[] defineSuccessorSuffixes(DictionaryItem item) {
        return new SuffixData[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SuffixForm getRootSet(DictionaryItem item, SuffixData successors) {
        return null;
    }

    protected void registerForms(SuffixForm... setz) {
        for (SuffixForm formSet : setz) {
            registerForm(formSet);
        }
    }

    protected NullSuffixForm generateNullFormFromTemplate(SuffixFormTemplate templateForm, SuffixData constraints) {
        NullSuffixForm nullForm = new NullSuffixForm(-1, idMaker.get(templateForm.id), templateForm);
        nullForm.connections = new SuffixData(templateForm.connections).retain(constraints);
        nullForm.indirectConnections = new SuffixData(templateForm.indirectConnections).retain(constraints);

        if (nullFormsUnprocessed.containsKey(nullForm)) {
            return nullFormsUnprocessed.get(nullForm);
        } else {
            nullForm.index = getNewIndex();
            nullFormsUnprocessed.put(nullForm, nullForm);
            return nullForm;
        }
    }

    protected void registerForm(SuffixForm formSet) {

        // if this is a template, we put basic template data to a lookup table. we will use this table later to detect
        // duplicates of newly generated FormSets.
        if (formSet instanceof SuffixFormTemplate) {
            return;
        }

        if (formSetLookup.containsKey(formSet)) {
            return;
        }

        SuffixData allConnections = formSet.allConnections();
        List<SuffixForm> templateFormsToRemove = new ArrayList<SuffixForm>();
        List<SuffixForm> nullFormsToRegister = new ArrayList<SuffixForm>();
        for (SuffixForm connection : formSet.connections) {
            if (connection instanceof SuffixFormTemplate) {
                NullSuffixForm nullForm =
                        generateNullFormFromTemplate((SuffixFormTemplate) connection, new SuffixData(allConnections)).copy();
                nullFormsToRegister.add(nullForm);
                templateFormsToRemove.add(connection);
            }
        }

        formSet.connections.remove(templateFormsToRemove);
        // we dont need indirect connection data anymore.
        formSet.indirectConnections.clear();
        formSet.connections.add(nullFormsToRegister);

        formSet.index = getNewIndex();

        formSetLookup.put(formSet, formSet);

        for (SuffixForm form : nullFormsToRegister) {
            registerForm(form);
        }
    }

    public void dumpPath(SuffixForm set, int level) {
        if (level == 0)
            return;
        System.out.println("--------------------------SET:" + set.id);
        System.out.println("D:");
        for (SuffixForm direct : set.connections) {
            System.out.println(direct.id);
        }
        System.out.println("S:");
        for (SuffixForm sec : set.indirectConnections) {
            System.out.println(sec.id);
        }
        for (SuffixForm direct : set.connections) {
            dumpPath(direct, level - 1);
        }
    }
}
