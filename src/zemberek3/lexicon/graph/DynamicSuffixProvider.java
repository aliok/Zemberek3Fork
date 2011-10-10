package zemberek3.lexicon.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.Suffix;
import zemberek3.lexicon.SuffixFormSet;
import zemberek3.lexicon.SuffixProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamicSuffixProvider implements SuffixProvider {

    //Set<SuffixFormSet> forms = Sets.newHashSet();
    protected Map<SuffixFormSet, SuffixFormSet> formSetLookup = Maps.newHashMap();
    protected Map<String, Suffix> suffixLookup = Maps.newTreeMap();
    protected ArrayListMultimap<String, SuffixFormSet> formsPerSuffix = ArrayListMultimap.create(100, 2);

    protected RandomIdMaker idMaker = new RandomIdMaker(3);

    protected static SuffixFormSet getNull(String suffix, String suffixId) {
        return new SuffixFormSet(suffixId, new Suffix(suffix), "", TerminationType.TRANSFER);
    }

    protected static SuffixFormSet getNull( String suffixId, Suffix suffix) {
        return new SuffixFormSet(suffixId, suffix, "", TerminationType.TRANSFER);
    }

    protected static SuffixFormSet getNull( String suffixId, Suffix suffix, TerminationType type) {
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

    protected void registerForm(SuffixFormSet formSet) {

        if (formSet.isTemplate() || formSetLookup.containsKey(formSet)) {
            return;
        }

        formSetLookup.put(formSet, formSet);

        Set<SuffixFormSet> allSuccessors = formSet.allSuccessors().set;
        Set<SuffixFormSet> toRemove = new HashSet<SuffixFormSet>();
        Set<SuffixFormSet> toAdd = new HashSet<SuffixFormSet>();
        for (SuffixFormSet directSuccessor : formSet.directSuccessors) {
            if (directSuccessor.isTemplate()) {
                SuffixFormSet copyOfTemplate = directSuccessor.copy(idMaker.getNew(directSuccessor.id));
                copyOfTemplate.directSuccessors.retain(allSuccessors);
                copyOfTemplate.successors.retain(allSuccessors);
                if (formSetLookup.containsKey(copyOfTemplate)) {
                    copyOfTemplate = formSetLookup.get(copyOfTemplate);
                }
                toRemove.add(directSuccessor);
                toAdd.add(copyOfTemplate);
            }
        }
        for (SuffixFormSet suffixFormSet : formSet.successors) {
            if (suffixFormSet.template)
                toRemove.add(suffixFormSet);
        }
        formSet.getDirectSuccessors().remove(toRemove);
        formSet.getSuccessors().remove(toRemove);
        formSet.getDirectSuccessors().add(toAdd);
        for (SuffixFormSet suffixFormSet : toAdd) {
            registerForm(suffixFormSet);
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
