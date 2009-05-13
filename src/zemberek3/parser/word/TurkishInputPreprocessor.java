package zemberek3.parser.word;

import zemberek3.structure.Stem;
import zemberek3.structure.LetterSequence;
import zemberek3.structure.Alphabet;

public class TurkishInputPreprocessor implements InputPreProcessor<LetterSequence> {

    Alphabet alphabet;

    public LetterSequence processForParse(CharSequence input) {
        return null;
    }

    public LetterSequence modifyForStem(LetterSequence input, Stem stem) {
        return null;
    }
}
