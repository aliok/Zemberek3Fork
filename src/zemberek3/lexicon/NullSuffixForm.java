package zemberek3.lexicon;

import zemberek3.lexicon.graph.SuffixData;
import zemberek3.lexicon.graph.TerminationType;

public class NullSuffixForm extends SuffixForm {

    SuffixFormTemplate template;

    public NullSuffixForm(int index, String id, SuffixFormTemplate template) {
        super(index, id, template.suffix, template.terminationType);
        this.template = template;
    }

    public NullSuffixForm(int index, String id, SuffixFormTemplate template, TerminationType terminationType) {
        super(index, id, template.suffix, terminationType);
        this.template = template;
    }

    public SuffixForm getTemplate() {
        return template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NullSuffixForm that = (NullSuffixForm) o;

        if (!template.id.equals(that.template.id)) return false;
        if (!connections.equals(that.connections)) return false;
        if (!indirectConnections.equals(that.indirectConnections)) return false;
        if (terminationType != that.terminationType) return false;

        return true;
    }

    public NullSuffixForm copy() {
        NullSuffixForm copy = new NullSuffixForm(index, id, template, terminationType);
        copy.connections = new SuffixData(this.connections);
        copy.indirectConnections = new SuffixData(this.indirectConnections);
        return copy;
    }

    @Override
    public int hashCode() {
        int result = template.id.hashCode();
        result = 31 * result + terminationType.hashCode();
        result = 31 * result + connections.hashCode();
        result = 31 * result + indirectConnections.hashCode();
        return result;
    }
}
