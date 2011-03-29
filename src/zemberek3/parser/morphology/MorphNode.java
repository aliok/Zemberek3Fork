package zemberek3.parser.morphology;

import java.util.ArrayList;
import java.util.List;

public abstract class MorphNode {
    String form;

    protected MorphNode(String form, TerminationType termination) {
        this.form = form;
        this.termination = termination;
    }

    TerminationType termination;
    List<MorphNode> connections = new ArrayList<MorphNode>(1);


}
