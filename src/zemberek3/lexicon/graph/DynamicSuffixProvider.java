package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import zemberek3.lexicon.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamicSuffixProvider implements SuffixProvider {

    //Set<SuffixForm> forms = Sets.newHashSet();
    protected Map<SuffixForm, SuffixForm> formSetLookup = Maps.newHashMap();
    protected Map<String, Suffix> suffixLookup = Maps.newHashMap();
    protected ArrayListMultimap<String, SuffixForm> formsPerSuffix = ArrayListMultimap.create(100, 2);
    private HashMultimap<String, TemplateData> templateData = HashMultimap.create(100, 10);
    private Map<TemplateData, SuffixForm> templateSets = Maps.newHashMap();

    protected IdMaker idMaker = new IdMaker(3);

    protected static SuffixForm getNull(String suffixId, Suffix suffix) {
        return new SuffixForm(suffixId, suffix, "", TerminationType.TRANSFER);
    }

    protected static SuffixForm getNull(String suffixId, Suffix suffix, TerminationType type) {
        return new SuffixForm(suffixId, suffix, "", type);
    }

    protected static SuffixForm getSet(String suffixId, String generationStr) {
        return new SuffixForm(new Suffix(suffixId), generationStr);
    }

    protected static SuffixForm getTemplate(String suffixId, String id) {
        return SuffixForm.getTemplate(id, new Suffix(suffixId));
    }

    protected static SuffixForm getTemplate(String id, Suffix suffix) {
        return SuffixForm.getTemplate(id, suffix);
    }

    protected static SuffixForm getTemplate(String id, Suffix suffix, TerminationType type) {
        return SuffixForm.getTemplate(id, suffix, type);
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void registerForms(SuffixForm... setz) {
        for (SuffixForm formSet : setz) {
            registerForm(formSet);
        }
    }

    protected class TemplateData {
        String templateId;
        SuffixData successors;
        SuffixData directSuccessors;

        TemplateData(String templateId, SuffixData successors, SuffixData directSuccessors) {
            this.templateId = templateId;
            this.successors = successors;
            this.directSuccessors = directSuccessors;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TemplateData that = (TemplateData) o;

            return templateId.equals(that.templateId)
                    && directSuccessors.equals(that.directSuccessors)
                    && successors.equals(that.successors);
        }

        @Override
        public int hashCode() {
            int result = templateId.hashCode();
            result = 31 * result + successors.hashCode();
            result = 31 * result + directSuccessors.hashCode();
            return result;
        }

    }

    protected SuffixForm generateSetFromTemplate(SuffixForm set, SuffixData constraints) {
        SuffixData d = new SuffixData(set.directSuccessors).retain(constraints);
        SuffixData s = new SuffixData(set.successors).retain(constraints);
        TemplateData data = new TemplateData(set.id, s, d);
        if (templateData.get(set.id).contains(data)) {
            return templateSets.get(data);
        } else {
            SuffixForm newSet = new SuffixForm(
                    idMaker.get(set.id),
                    set.getSuffix(),
                    set.generation,
                    set.terminationType);
            newSet.directSuccessors = d;
            newSet.successors = s;
            templateData.put(set.id, data);
            templateSets.put(data, newSet);
            return newSet;
        }
    }

    protected SuffixForm generateSetFromTemplate(SuffixForm set, TemplateData tdata) {
        if (templateData.get(set.id).contains(tdata)) {
            return templateSets.get(tdata);
        } else {
            SuffixForm newSet = new SuffixForm(
                    idMaker.get(set.id),
                    set.getSuffix(),
                    set.generation,
                    set.terminationType);
            newSet.directSuccessors = tdata.directSuccessors;
            newSet.successors = tdata.successors;
            templateData.put(set.id, tdata);
            templateSets.put(tdata, newSet);
            return newSet;
        }
    }

    protected void registerForm(SuffixForm formSet) {

        // if this is a template, we put basic template data to a lookup table. we will use this table later to detect
        // duplicates of newly generated FormSets.
        if (formSet.isTemplate()) {
            if (!templateData.containsKey(formSet.id))
                templateData.put(formSet.id, null);
            return;
        }

        if (formSetLookup.containsKey(formSet)) {
            return;
        }

        formSetLookup.put(formSet, formSet);

        SuffixData allSuccessors = formSet.allSuccessors();
        Set<SuffixForm> toRemove = new HashSet<SuffixForm>();
        Set<SuffixForm> toAdd = new HashSet<SuffixForm>();
        for (SuffixForm directSuccessor : formSet.directSuccessors) {
            if (directSuccessor.isTemplate()) {
                SuffixData d = new SuffixData(directSuccessor.directSuccessors).retain(allSuccessors);
                SuffixData s = new SuffixData(directSuccessor.successors).retain(allSuccessors);
                TemplateData tdata = new TemplateData(directSuccessor.getId(), s, d);
                SuffixForm nullSet = generateSetFromTemplate(directSuccessor, tdata);
                toAdd.add(nullSet);
                toRemove.add(directSuccessor);
            }
        }
        for (SuffixForm suffixFormSet : formSet.successors) {
            if (suffixFormSet.template)
                toRemove.add(suffixFormSet);
        }
        formSet.directSuccessors.remove(toRemove);
        formSet.successors.remove(toRemove);
        formSet.directSuccessors.add(toAdd);
        for (SuffixForm suffixFormSet : toAdd) {
            registerForm(suffixFormSet);
        }
    }

    public void dumpPath(SuffixForm set, int level) {
        if (level == 0)
            return;
        System.out.println("--------------------------SET:" + set.id);
        System.out.println("D:");
        for (SuffixForm direct : set.directSuccessors) {
            System.out.println(direct.id);
        }
        System.out.println("S:");
        for (SuffixForm sec : set.successors) {
            System.out.println(sec.id);
        }
        for (SuffixForm direct : set.directSuccessors) {
            dumpPath(direct, level - 1);
        }
    }


    protected void addForms(SuffixForm... setz) {
        for (SuffixForm suffixFormSet : setz) {
            if (formSetLookup.containsKey(suffixFormSet))
                continue;
            formSetLookup.put(suffixFormSet, suffixFormSet);
            suffixLookup.put(suffixFormSet.getSuffix().id, suffixFormSet.getSuffix());
            formsPerSuffix.put(suffixFormSet.getSuffix().id, suffixFormSet);
        }
    }
}
