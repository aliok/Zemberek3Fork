package zemberek3.parser.morphology;

import java.util.List;

public interface MorphParser {

    public List<ParseToken> parse(String input);

}
