package zemberek3.structure;

import java.util.Arrays;

import static zemberek3.structure.TurkicLetter.builder;


/**
 * Contains Turkish Letters, Turkish Letter equivalent chars, several helper methods.
 * TurkishAlphanbet only contains small case letters.
 */
public class TurkishAlphabet implements TurkicAlphabet {

    // Turkish specific characters.
    public static final char C_CC = '\u00c7'; // Kuyruklu buyuk c (ch)
    public static final char C_cc = '\u00e7'; // Kuyruklu kucuk c (ch)
    public static final char C_GG = '\u011e'; // Buyuk yumusak g
    public static final char C_gg = '\u011f'; // Kucuk yumusak g
    public static final char C_ii = '\u0131'; // Noktassiz kucuk i
    public static final char C_II = '\u0130'; // Noktali buyuk i
    public static final char C_OO = '\u00d6'; // Noktali buyuk o
    public static final char C_oo = '\u00f6'; // Noktali kucuk o
    public static final char C_SS = '\u015e'; // Kuyruklu buyuk s (sh)
    public static final char C_ss = '\u015f'; // Kuyruklu kucuk s (sh)
    public static final char C_UU = '\u00dc'; // Noktali buyuk u
    public static final char C_uu = '\u00fc'; // Noktali kucuk u

    // letters used in turkish text having circumflex.
    public static final char A_CIRC = '\u00c2'; // sapkali buyuk A
    public static final char a_CIRC = '\u00e2'; // sapkali kucuk A
    public static final char I_CIRC = '\u00ce'; // sapkali buyuk noktasiz i
    public static final char i_CIRC = '\u00ee'; // sapkali kucuk noktasiz i
    public static final char U_CIRC = '\u00db'; // sapkali buyuk U
    public static final char u_CIRC = '\u00fb'; // sapkali kucuk u

    /**
     * Turkish Letters. q,x,w is also added for foreign proper nouns. They are marked as 'foreign'
     */
    public static final TurkicLetter L_a = builder('a', 1).vowel().frontalVowel().build();
    public static final TurkicLetter L_b = builder('b', 2).build();
    public static final TurkicLetter L_c = builder('c', 3).build();
    public static final TurkicLetter L_cc = builder(C_cc, 4).nonEnglish().silent().englishEquivalentChar('c').build();
    public static final TurkicLetter L_d = builder('d', 5).build();
    public static final TurkicLetter L_e = builder('e', 6).vowel().frontalVowel().build();
    public static final TurkicLetter L_f = builder('f', 7).build();
    public static final TurkicLetter L_g = builder('g', 8).build();
    public static final TurkicLetter L_gg = builder(C_gg, 9).nonEnglish().englishEquivalentChar('g').build();
    public static final TurkicLetter L_h = builder('h', 10).build();
    public static final TurkicLetter L_ii = builder(C_ii, 11).vowel().backVowel().nonEnglish().englishEquivalentChar('i').build();
    public static final TurkicLetter L_i = builder('i', 12).vowel().frontalVowel().build();
    public static final TurkicLetter L_j = builder('j', 13).build();
    public static final TurkicLetter L_k = builder('k', 14).silent().build();
    public static final TurkicLetter L_l = builder('l', 15).build();
    public static final TurkicLetter L_m = builder('m', 16).build();
    public static final TurkicLetter L_n = builder('n', 17).build();
    public static final TurkicLetter L_o = builder('o', 18).build();
    public static final TurkicLetter L_oo = builder(C_oo, 19).vowel().roundedVowel().nonEnglish().englishEquivalentChar('o').build();
    public static final TurkicLetter L_p = builder('p', 20).silent().build();
    public static final TurkicLetter L_r = builder('r', 21).build();
    public static final TurkicLetter L_s = builder('s', 22).build();
    public static final TurkicLetter L_ss = builder(C_ss, 23).nonEnglish().englishEquivalentChar('s').build();
    public static final TurkicLetter L_t = builder('t', 24).silent().build();
    public static final TurkicLetter L_u = builder('u', 25).vowel().roundedVowel().frontalVowel().build();
    public static final TurkicLetter L_uu = builder(C_uu, 26).vowel().roundedVowel().frontalVowel().englishEquivalentChar('u').nonEnglish().build();
    public static final TurkicLetter L_v = builder('v', 27).build();
    public static final TurkicLetter L_y = builder('y', 28).build();
    public static final TurkicLetter L_z = builder('z', 29).build();
    public static final TurkicLetter L_q = builder('q', 30).foreign().build();
    public static final TurkicLetter L_w = builder('w', 31).foreign().build();
    public static final TurkicLetter L_x = builder('x', 32).foreign().build();

/*
    static final LetterAttribute VOWELS = new LetterAttribute('a', 'e', 'i', C_ii, 'o', C_oo, 'u', C_uu);
    static final LetterAttribute ROUNDED_VOWELS = new LetterAttribute('o', C_oo, 'u', C_uu);
    static final LetterAttribute FRONTAL_VOWELS = new LetterAttribute('a', 'e', 'i', C_ii);
    static final LetterAttribute CONSONANTS = new LetterAttribute("bcdfghjklmnprstvyzqwx" + String.valueOf(C_cc) + String.valueOf(C_gg) + String.valueOf(C_ss));
    static final LetterAttribute NON_ENGLISH = new LetterAttribute(C_cc, C_gg, C_ss, C_ii, C_ss, C_oo, C_uu);
    static final LetterAttribute FOREIGN = new LetterAttribute('x', 'q', 'w');
*/

    private static TurkicLetter[] TURKISH_LETTERS = {
            L_a, L_b, L_c, L_cc, L_d, L_e, L_f, L_g,
            L_gg, L_h, L_ii, L_i, L_j, L_k, L_l, L_m,
            L_n, L_o, L_oo, L_p, L_r, L_s, L_ss, L_t,
            L_u, L_uu, L_v, L_y, L_z, L_q, L_w, L_x};

    private static final int ALPHABET_LETTER_COUNT = TURKISH_LETTERS.length;

    // 0x15f is the maximum char value in turkish speific characters.
    private static final int MAX_CHAR_VALUE = 0x15f + 1;
    private static TurkicLetter[] CHAR_TO_LETTER_LOOKUP = new TurkicLetter[MAX_CHAR_VALUE];
    private static char[] TURKISH_ALPHABET_CHARS = new char[MAX_CHAR_VALUE];
    private static int[] TURKISH_ALPHABET_INDEXES = new int[MAX_CHAR_VALUE];
    private static boolean[] VALID_CHAR_TABLE = new boolean[MAX_CHAR_VALUE];

    static {
        Arrays.fill(CHAR_TO_LETTER_LOOKUP, TurkicLetter.UNDEFINED);
        Arrays.fill(TURKISH_ALPHABET_INDEXES, -1);
        Arrays.fill(VALID_CHAR_TABLE, false);
        for (TurkicLetter turkicLetter : TURKISH_LETTERS) {
            CHAR_TO_LETTER_LOOKUP[turkicLetter.charValue()] = turkicLetter;
            TURKISH_ALPHABET_CHARS[turkicLetter.alphabeticIndex() - 1] = turkicLetter.charValue();
            TURKISH_ALPHABET_INDEXES[turkicLetter.charValue()] = turkicLetter.alphabeticIndex();
            VALID_CHAR_TABLE[turkicLetter.charValue()] = true;
        }
    }

    /**
     * returns the TurkicLetter equivalent of character c.
     *
     * @param c input character
     * @return TurkishLetter equivalent.
     * @throws IllegalArgumentException if input character is out of alphabet.
     */
    public TurkicLetter getLetter(char c) {
        if (c >= MAX_CHAR_VALUE || !VALID_CHAR_TABLE[c])
            throw new IllegalArgumentException("Unexpected char:" + c);
        else return CHAR_TO_LETTER_LOOKUP[c];
    }

    /**
     * returns the TurkicLetter equivalent with given alphabetic index. index starts from 1.
     *
     * @param alphabeticIndex alphabetical index. starts from 1
     * @return TurkicLetter for given alphabetical index.
     * @throws IllegalArgumentException if index is [< 1] or [> alphabetsize]
     */
    public TurkicLetter getLetter(int alphabeticIndex) {
        if (alphabeticIndex < 1 || alphabeticIndex > ALPHABET_LETTER_COUNT)
            throw new IllegalArgumentException("Unexpected alphabetic index:" + alphabeticIndex);
        return TURKISH_LETTERS[alphabeticIndex - 1];
    }

    /**
     * returns the alphabetic index of a char.
     *
     * @param c char
     * @return alphabetic index.
     * @throws IllegalArgumentException if char is out of alphabet.
     */
    public int getAphabeticIndex(char c) {
        if (!isValid(c))
            throw new IllegalArgumentException("unexpected char:" + c);
        return TURKISH_ALPHABET_INDEXES[c];
    }

    /**
     * retrieves a character from alphabetical index.
     *
     * @param alphabeticIndex index
     * @return char.
     * @throws IllegalArgumentException if alphabeticIndex is [< 1] or [> alphabetsize]
     */
    public char getCharByAlphabeticIndex(int alphabeticIndex) {
        if (alphabeticIndex < 1 || alphabeticIndex > ALPHABET_LETTER_COUNT)
            throw new IllegalArgumentException("Unexpected alphabetic index:" + alphabeticIndex);
        return TURKISH_ALPHABET_CHARS[alphabeticIndex - 1];
    }

    // ------------------------ ASCII equivalency ----------------------------------

    private static TurkicLetter[] ENGLISH_EQUIVALENT_LETTER_LOOKUP = {
            L_a, L_b, L_c, L_c, L_d, L_e, L_f, L_g,
            L_g, L_h, L_i, L_i, L_j, L_k, L_l, L_m,
            L_n, L_o, L_o, L_p, L_r, L_s, L_s, L_t,
            L_u, L_u, L_v, L_y, L_z, L_q, L_w, L_x};

    private static char[] ENGLISH_EQUIVALENT_CHARS_LOOKUP = new char[MAX_CHAR_VALUE];

    static {
        Arrays.fill(ENGLISH_EQUIVALENT_CHARS_LOOKUP, (char) 0);
        for (TurkicLetter turkicLetter : TURKISH_LETTERS) {
            ENGLISH_EQUIVALENT_CHARS_LOOKUP[turkicLetter.charValue] = turkicLetter.englishEquivalentChar();
        }
    }

    /**
     * returns the English equivalnet letter. such as [a->a] and [c with cedil -> c]
     *
     * @param letter turkicletter
     * @return english equivalent letter.
     */
    public TurkicLetter getEnglishquivalentLetter(TurkicLetter letter) {
        return ENGLISH_EQUIVALENT_LETTER_LOOKUP[letter.alphabeticIndex() - 1];
    }

    /**
     * checks if two characters are enlishcharacter equal.
     *
     * @param c1 first char
     * @param c2 second char.
     * @return true if equals or enlishequivalents are same.
     */
    public boolean englishEqual(char c1, char c2) {
        return (isValid(c1) && isValid(c2)) &&
                (c1 == c2 || ENGLISH_EQUIVALENT_CHARS_LOOKUP[c1] == ENGLISH_EQUIVALENT_CHARS_LOOKUP[c2]);
    }

    public char getEnglishquivalentChar(char c) {
        if (!isValid(c))
            throw new IllegalArgumentException("unexpected char:" + c);
        return CHAR_TO_LETTER_LOOKUP[c].englishEquivalentChar();
    }

    public TurkicLetter getEnglishquivalentLetter(char c) {
        if (!isValid(c))
            throw new IllegalArgumentException("unexpected char:" + c);
        return ENGLISH_EQUIVALENT_LETTER_LOOKUP[getAphabeticIndex(c) - 1];
    }

    // ----------------------- TurkicAlphabet Interface methods -------------------------

    public final boolean isValid(char c) {
        return c < MAX_CHAR_VALUE && VALID_CHAR_TABLE[c];
    }

    private static class LetterAttribute {
        private long attributeMap;

        public LetterAttribute(char... chars) {
            for (char aChar : chars) {
                if (aChar >= MAX_CHAR_VALUE || !VALID_CHAR_TABLE[aChar])
                    throw new IllegalArgumentException("unexpected char:" + aChar);
                attributeMap &= 0x01 << TURKISH_ALPHABET_INDEXES[aChar];
            }
        }

        public LetterAttribute(String str) {
            this(str.toCharArray());
        }

        public boolean contains(char c) {
            if (c >= MAX_CHAR_VALUE || !VALID_CHAR_TABLE[c])
                throw new IllegalArgumentException("unexpected char:" + c);
            return (attributeMap & (0x01 << TURKISH_ALPHABET_INDEXES[c])) != 0;
        }
    }
}
