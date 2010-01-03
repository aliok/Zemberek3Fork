package zemberek3.parser.word;

import zemberek3.structure.Lemma;

/**
 * it preprocess the input char sequence. Such as lower casing, or eliminating foreign characters.
 */
public interface InputPreProcessor<T extends CharSequence> {

    /**
     * it processes the input to make it ready for the parse operation.
     * @param input input char sequence.
     * @return processed input.
     */
    T processForParse(CharSequence input);

    /**
     * makes modifications in the input using the lemma if necessary.
     * this is usually used for abbreviations in a language.
     * Such as for Turkish, if an abbreviation does not include a vowel, this method may insert a temporary vowel
     * required for parsing.
     * <p>Example for Turkish:
     * <code>input: "tdkya", Stem:[tdk, ABBRV], may return "tdkaya"</code>  
     * @param input, input char sequence.
     * @param lemma, Stem that may cause input to be modified.
     * @return modified form.
     */
    T modifyForStem(T input, Lemma lemma);
}
