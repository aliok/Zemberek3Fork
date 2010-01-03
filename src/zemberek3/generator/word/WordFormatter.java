package zemberek3.generator.word;

import zemberek3.structure.TurkicWordParse;


public interface WordFormatter {

    /**
     * Input independent word formatting.
     * @param wordParse word to format.
     * @return returns the formatted word.
     */
    CharSequence format(TurkicWordParse wordParse);
    
    /**
     * Input dependend Word formatting. such as for Turkish, if parse result is a Word containing
     * "ankara" as stem and it has a suffix "ya", this returns "Ankara'ya". The actual shape is determined by the input.
     *
     * @param input input char sequence that is used for forming the Word. input is used because result will have the
     * same format as input. such as
     * @param wordParse is the parse result of the input.
     * @return formatted result.
     */
    CharSequence format(CharSequence input, TurkicWordParse wordParse);
}
