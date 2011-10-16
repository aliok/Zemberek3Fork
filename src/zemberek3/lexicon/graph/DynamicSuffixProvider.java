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
    private Map<TemplateData, SuffixForm> nullForms = Maps.newHashMap();

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
        SuffixData indirectConnections;
        SuffixData connections;

        TemplateData(String templateId, SuffixData indirectConnections, SuffixData connections) {
            this.templateId = templateId;
            this.indirectConnections = indirectConnections;
            this.connections = connections;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TemplateData that = (TemplateData) o;

            return templateId.equals(that.templateId)
                    && connections.equals(that.connections)
                    && indirectConnections.equals(that.indirectConnections);
        }

        @Override
        public int hashCode() {
            int result = templateId.hashCode();
            result = 31 * result + indirectConnections.hashCode();
            result = 31 * result + connections.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return templateId + " - [ " + connections.set.size() + ", " + indirectConnections.set.size() + "]";
        }
    }

    protected SuffixForm generateSetFromTemplate(SuffixForm templateForm, SuffixData constraints) {
        SuffixData d = new SuffixData(templateForm.connections).retain(constraints);
        SuffixData s = new SuffixData(templateForm.indirectConnections).retain(constraints);
        TemplateData data = new TemplateData(templateForm.id, s, d);
        if (templateData.get(templateForm.id).contains(data)) {
            return nullForms.get(data);
        } else {
            SuffixForm newSet = new SuffixForm(
                    idMaker.get(templateForm.id),
                    templateForm.getSuffix(),
                    templateForm.generation,
                    templateForm.terminationType);
            newSet.connections = d;
            newSet.indirectConnections = s;
            templateData.put(templateForm.id, data);
            nullForms.put(data, newSet);
            return newSet;
        }
    }

    protected SuffixForm generateSetFromTemplate(SuffixForm templateForm, TemplateData tdata) {
        if (templateData.get(templateForm.id).contains(tdata)) {
            return nullForms.get(tdata);
        } else {
            SuffixForm newSet = new SuffixForm(
                    idMaker.get(templateForm.id),
                    templateForm.getSuffix(),
                    templateForm.generation,
                    templateForm.terminationType);
            newSet.connections = tdata.connections;
            newSet.indirectConnections = tdata.indirectConnections;
            templateData.put(templateForm.id, tdata);
            nullForms.put(tdata, newSet);
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

        SuffixData allConnections = formSet.allSuccessors();
        Set<SuffixForm> toRemove = new HashSet<SuffixForm>();
        Set<SuffixForm> toAdd = new HashSet<SuffixForm>();
        for (SuffixForm connection : formSet.connections) {
            if (connection.isTemplate()) {
                SuffixData d = new SuffixData(connection.connections).retain(allConnections);
                SuffixData s = new SuffixData(connection.indirectConnections).retain(allConnections);
                TemplateData tdata = new TemplateData(connection.getId(), s, d);
                SuffixForm nullSet = generateSetFromTemplate(connection, tdata);
                toAdd.add(nullSet);
                toRemove.add(connection);
            }
        }
        for (SuffixForm suffixFormSet : formSet.indirectConnections) {
            if (suffixFormSet.template)
                toRemove.add(suffixFormSet);
        }
        formSet.connections.remove(toRemove);
        formSet.indirectConnections.remove(toRemove);
        formSet.connections.add(toAdd);
        for (SuffixForm suffixFormSet : toAdd) {
            registerForm(suffixFormSet);
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
