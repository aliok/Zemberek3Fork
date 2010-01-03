package zemberek3.structure;

import org.junit.Assert;
import org.junit.Test;

public class TurkishAlphabetTest {

    @Test
    public void getLetterByChar() {
        TurkishAlphabet alphabet = new TurkishAlphabet();
        Assert.assertEquals(alphabet.getLetter('c'), TurkishAlphabet.L_c);
        Assert.assertEquals(alphabet.getLetter('a'), TurkishAlphabet.L_a);
        Assert.assertEquals(alphabet.getLetter('w'), TurkishAlphabet.L_w);
        Assert.assertEquals(alphabet.getLetter('z'), TurkishAlphabet.L_z);
        Assert.assertEquals(alphabet.getLetter('x'), TurkishAlphabet.L_x);
        Assert.assertEquals(alphabet.getLetter(TurkishAlphabet.C_cc), TurkishAlphabet.L_cc);
        Assert.assertEquals(alphabet.getLetter(TurkishAlphabet.C_ii), TurkishAlphabet.L_ii);
    }

    @Test
    public void getLetterByIndex() {
        TurkishAlphabet alphabet = new TurkishAlphabet();
        Assert.assertEquals(alphabet.getLetter(4), TurkishAlphabet.L_cc);
        Assert.assertEquals(alphabet.getLetter(1), TurkishAlphabet.L_a);
        Assert.assertEquals(alphabet.getLetter(3), TurkishAlphabet.L_c);
        Assert.assertEquals(alphabet.getLetter(29), TurkishAlphabet.L_z);
        Assert.assertEquals(alphabet.getLetter(32), TurkishAlphabet.L_x);
        Assert.assertEquals(alphabet.getLetter(11), TurkishAlphabet.L_ii);
    }

    @Test
    public void getAlphabeticIndex() {
        TurkishAlphabet alphabet = new TurkishAlphabet();
        Assert.assertEquals(alphabet.getAphabeticIndex('a'), 1);
        Assert.assertEquals(alphabet.getAphabeticIndex('c'), 3);
        Assert.assertEquals(alphabet.getAphabeticIndex('z'), 29);
        Assert.assertEquals(alphabet.getAphabeticIndex('x'), 32);
        Assert.assertEquals(alphabet.getAphabeticIndex(TurkishAlphabet.C_cc), 4);
        Assert.assertEquals(alphabet.getAphabeticIndex(TurkishAlphabet.C_ii), 11);
    }

    @Test
    public void getEnglishquivalentLetter() {
        TurkishAlphabet alphabet = new TurkishAlphabet();
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_a), TurkishAlphabet.L_a);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_x), TurkishAlphabet.L_x);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_cc), TurkishAlphabet.L_c);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_ii), TurkishAlphabet.L_i);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_ss), TurkishAlphabet.L_s);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_gg), TurkishAlphabet.L_g);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_oo), TurkishAlphabet.L_o);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.L_uu), TurkishAlphabet.L_u);
    }

    @Test
    public void getEnglishquivalentLetterByChar() {
        TurkishAlphabet alphabet = new TurkishAlphabet();
        Assert.assertEquals(alphabet.getEnglishquivalentLetter('a'), TurkishAlphabet.L_a);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter('x'), TurkishAlphabet.L_x);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.C_cc), TurkishAlphabet.L_c);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.C_ii), TurkishAlphabet.L_i);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.C_ss), TurkishAlphabet.L_s);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.C_gg), TurkishAlphabet.L_g);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.C_oo), TurkishAlphabet.L_o);
        Assert.assertEquals(alphabet.getEnglishquivalentLetter(TurkishAlphabet.C_uu), TurkishAlphabet.L_u);
    }

    @Test
    public void englishEqual() {
        TurkishAlphabet alphabet = new TurkishAlphabet();
        Assert.assertTrue(alphabet.englishEqual('a', 'a'));
        Assert.assertTrue(alphabet.englishEqual('x', 'x'));
        Assert.assertTrue(alphabet.englishEqual(TurkishAlphabet.C_gg, 'g'));
        Assert.assertTrue(alphabet.englishEqual(TurkishAlphabet.C_cc, 'c'));
        Assert.assertTrue(alphabet.englishEqual(TurkishAlphabet.C_ii, 'i'));
        Assert.assertTrue(alphabet.englishEqual(TurkishAlphabet.C_ss, 's'));
        Assert.assertTrue(alphabet.englishEqual(TurkishAlphabet.C_oo, 'o'));
        Assert.assertTrue(alphabet.englishEqual(TurkishAlphabet.C_uu, 'u'));
        Assert.assertFalse(alphabet.englishEqual(TurkishAlphabet.C_uu, 'a'));
    }


    @Test
    public void getEnglishquivalentChar() {
        TurkishAlphabet alphabet = new TurkishAlphabet();
        Assert.assertEquals(alphabet.getEnglishquivalentChar('a'), 'a');
        Assert.assertEquals(alphabet.getEnglishquivalentChar('x'), 'x');
        Assert.assertEquals(alphabet.getEnglishquivalentChar(TurkishAlphabet.C_gg), 'g');
        Assert.assertEquals(alphabet.getEnglishquivalentChar(TurkishAlphabet.C_cc), 'c');
        Assert.assertEquals(alphabet.getEnglishquivalentChar(TurkishAlphabet.C_ii), 'i');
        Assert.assertEquals(alphabet.getEnglishquivalentChar(TurkishAlphabet.C_ss), 's');
        Assert.assertEquals(alphabet.getEnglishquivalentChar(TurkishAlphabet.C_oo), 'o');
        Assert.assertEquals(alphabet.getEnglishquivalentChar(TurkishAlphabet.C_uu), 'u');
        Assert.assertNotSame(alphabet.getEnglishquivalentChar(TurkishAlphabet.C_uu), 'a');
    }
}
