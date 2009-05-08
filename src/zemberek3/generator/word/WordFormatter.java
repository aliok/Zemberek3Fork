package zemberek3.generator.word;

import zemberek3.structure.Word;

public interface WordFormatter {
    CharSequence format(CharSequence input, Word word);
}
