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

    //Set<SuffixFormSet> forms = Sets.newHashSet();
    protected Map<SuffixFormSet, SuffixFormSet> formSetLookup = Maps.newHashMap();
    protected Map<String, Suffix> suffixLookup = Maps.newHashMap();
    protected ArrayListMultimap<String, SuffixFormSet> formsPerSuffix = ArrayListMultimap.create(100, 2);
    private HashMultimap<String, TemplateData> templateData = HashMultimap.create(100, 10);
    private Map<TemplateData, SuffixFormSet> templateSets = Maps.newHashMap();

    protected IdMaker idMaker = new IdMaker(3);

    protected static SuffixFormSet getNull(String suffix, String suffixId) {
        return new SuffixFormSet(suffixId, new Suffix(suffix), "", TerminationType.TRANSFER);
    }

    protected static SuffixFormSet getNull(String suffixId, Suffix suffix) {
        return new SuffixFormSet(suffixId, suffix, "", TerminationType.TRANSFER);
    }

    protected static SuffixFormSet getNull(String suffixId, Suffix suffix, TerminationType type) {
        return new SuffixFormSet(suffixId, suffix, "", type);
    }

    protected static SuffixFormSet getSet(String suffixId, String generationStr) {
        return new SuffixFormSet(new Suffix(suffixId), generationStr);
    }

    protected static SuffixFormSet getTemplate(String suffixId, String id) {
        return SuffixFormSet.getTemplate(id, new Suffix(suffixId));
    }

    protected static SuffixFormSet getTemplate(String id, Suffix suffix) {
        return SuffixFormSet.getTemplate(id, suffix);
    }

    protected static SuffixFormSet getTemplate(String id, Suffix suffix, TerminationType type) {
        return SuffixFormSet.getTemplate(id, suffix, type);
    }

    public Suffix getSuffixById(String suffixId) {
        return suffixLookup.get(suffixId);
    }

    public List<SuffixFormSet> getFormsBySuffixId(String suffixId) {
        return formsPerSuffix.get(suffixId);
    }

    public Iterable<SuffixFormSet> getAllForms() {
        return formSetLookup.keySet();
    }

    @Override
    public SuffixData[] defineSuccessorSuffixes(DictionaryItem item) {
        return new SuffixData[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SuffixFormSet getRootSet(DictionaryItem item, SuffixData successors) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void registerForms(SuffixFormSet... setz) {
        for (SuffixFormSet formSet : setz) {
            registerForm(formSet);
        }
    }

    class TemplateData {
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

    protected SuffixFormSet generateSetFromTemplate(SuffixFormSet set, SuffixData constraints) {
        SuffixData d = new SuffixData(set.directSuccessors).retain(constraints);
        SuffixData s = new SuffixData(set.successors).retain(constraints);
        TemplateData data = new TemplateData(set.id, s, d);
        if (templateData.get(set.id).contains(data)) {
            return templateSets.get(data);
        } else {
            SuffixFormSet newSet = new SuffixFormSet(
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

    protected void registerForm(SuffixFormSet formSet) {

        // if this is a template, we put basic template data to a lookup table. we will use this table later to detect
        // duplicates of newly generated FormSets.
        if (formSet.isTemplate()) {
            if (!templateData.containsKey(formSet.id))
                templateData.put(formSet.id, new TemplateData(formSet.id, formSet.successors, formSet.directSuccessors));
            return;
        }

        if (formSetLookup.containsKey(formSet)) {
            return;
        }

        formSetLookup.put(formSet, formSet);

        SuffixData allSuccessors = formSet.allSuccessors();
        Set<SuffixFormSet> toRemove = new HashSet<SuffixFormSet>();
        Set<SuffixFormSet> toAdd = new HashSet<SuffixFormSet>();
        for (SuffixFormSet directSuccessor : formSet.directSuccessors) {
            if (directSuccessor.isTemplate()) {
                SuffixFormSet nullSet = generateSetFromTemplate(directSuccessor, allSuccessors);
                if (nullSet == null)
                    System.out.println("crap");
                toAdd.add(nullSet);
                toRemove.add(directSuccessor);
            }
        }
        for (SuffixFormSet suffixFormSet : formSet.successors) {
            if (suffixFormSet.template)
                toRemove.add(suffixFormSet);
        }
        formSet.directSuccessors.remove(toRemove);
        formSet.successors.remove(toRemove);
        formSet.directSuccessors.add(toAdd);
        for (SuffixFormSet suffixFormSet : toAdd) {
            if (suffixFormSet == null)
                System.out.println("crap");
            registerForm(suffixFormSet);
        }
    }

    public void dumpPath(SuffixFormSet set, int level) {
        if (level == 0)
            return;
        System.out.println("--------------------------SET:" + set.id);
        System.out.println("D:");
        for (SuffixFormSet direct : set.directSuccessors) {
            System.out.println(direct.id);
        }
        System.out.println("S:");
        for (SuffixFormSet sec : set.successors) {
            System.out.println(sec.id);
        }
        for (SuffixFormSet direct : set.directSuccessors) {
            dumpPath(direct, level - 1);
        }
    }


    protected void addForms(SuffixFormSet... setz) {
        for (SuffixFormSet suffixFormSet : setz) {
            if (formSetLookup.containsKey(suffixFormSet))
                continue;
            formSetLookup.put(suffixFormSet, suffixFormSet);
            suffixLookup.put(suffixFormSet.getSuffix().id, suffixFormSet.getSuffix());
            formsPerSuffix.put(suffixFormSet.getSuffix().id, suffixFormSet);
        }
    }
}
