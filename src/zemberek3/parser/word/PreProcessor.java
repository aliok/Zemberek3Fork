package zemberek3.parser.word;

import zemberek3.structure.Stem;

/**
 * it preprocess the input char sequence. Such as lower casing, or eliminating foreign characters.
 */
public interface PreProcessor<T extends CharSequence> {

    /**
     * it processes the input to make it ready for the parse operation.
     * @param input input char sequence.
     * @return processed input.
     */
    T processForParse(CharSequence input);

    /**
     * makes modifications for a Stem if necessary. this is usually used for abbreviations in a language.
     * @param input, input char sequence.
     * @param stem, Stem that may cause input to be modified.
     * @return modified form.
     */
    T modifyForStem(CharSequence input, Stem stem);
}
