package zemberek3.lexicon.graph;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

import com.google.common.collect.Lists;
import org.junit.Test;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.PrimaryPos;
import zemberek3.structure.TurkishAlphabet;

public class StemTrieTest {

    static Random r = new Random(0xCAFEDEADBEEFL);
    TurkishAlphabet alphabet = new TurkishAlphabet();

    private List<String> generateRandomWords(int number){
        List<String> randomWords = Lists.newArrayList();
        for (int i=0; i<number; i++) {
            int len = r.nextInt(20) + 1;
            char[] chars = new char[len];
            for (int j = 0; j < len ; j++) {
                chars[j] = alphabet.getCharByAlphabeticIndex(r.nextInt(TurkishAlphabet.ALPHABET_LETTER_COUNT) + 1);
            }
            randomWords.add(new String(chars));
        }
        return randomWords;
    }

	private StemNode createStemNode(String surfaceForm) {
		DictionaryItem di = new DictionaryItem(surfaceForm, surfaceForm, PrimaryPos.Noun, null, null, null);
		return new StemNode(surfaceForm, di, TerminationType.TERMINAL);
	}
	
	@Test
	public void empty(){
		StemTrie lt = new StemTrie();
		List<StemNode> stems = lt.getMatchingStems("foo");
		assertEquals(stems.size(), 0);
	}
	
	@Test
	public void singleItem() {
		StemTrie lt = new StemTrie();
		StemNode sn = createStemNode("elma");
		lt.add(sn);
		List<StemNode> stems = lt.getMatchingStems("elma");
		assertEquals(1, stems.size());
		assertTrue(stems.contains(sn));
	}
	
	@Test
	public void distinctStems() {
		StemTrie lt = new StemTrie();
		StemNode sn = createStemNode("elma");
		StemNode sn2 = createStemNode("armut");
		lt.add(sn);
		lt.add(sn2);
		List<StemNode> stems = lt.getMatchingStems("elma");
		assertEquals(1, stems.size());
		assertTrue(stems.contains(sn));
		stems = lt.getMatchingStems("armut");
		assertEquals(1, stems.size());
		assertTrue(stems.contains(sn2));
	}
	
	@Test
	public void stemsSharingSamePrefixOrder1() {
		StemTrie lt = new StemTrie();
		StemNode sn = createStemNode("elma");
		StemNode sn2 = createStemNode("elmas");
		lt.add(sn);
		lt.add(sn2);
		System.out.println(lt);
		List<StemNode> stems = lt.getMatchingStems("elma");
		assertEquals(1, stems.size());
		assertTrue(stems.contains(sn));
		stems = lt.getMatchingStems("elmas");
		assertEquals(2, stems.size());
		assertTrue(stems.contains(sn));
		assertTrue(stems.contains(sn2));
	}	
	
	@Test
	public void stemsSharingSamePrefixOrder2() {
		StemTrie lt = new StemTrie();
		StemNode sn = createStemNode("elmas");
		StemNode sn2 = createStemNode("elma");
		lt.add(sn);
		lt.add(sn2);
		System.out.println(lt);
		List<StemNode> stems = lt.getMatchingStems("elma");
		assertEquals(1, stems.size());
		assertTrue(stems.contains(sn2));
		stems = lt.getMatchingStems("elmas");
		assertEquals(2, stems.size());
		assertTrue(stems.contains(sn));
		assertTrue(stems.contains(sn2));
	}	
	
	@Test
	public void stemsSharingSamePrefix3Stems() {
		StemTrie lt = new StemTrie();
		StemNode sn = createStemNode("elmas");
		StemNode sn2 = createStemNode("el");
		StemNode sn3 = createStemNode("elma");
		lt.add(sn);
		lt.add(sn2);
		lt.add(sn3);
		System.out.println(lt);
		List<StemNode> stems = lt.getMatchingStems("elma");
		assertEquals(2, stems.size());
		assertTrue(stems.contains(sn3));
		assertTrue(stems.contains(sn2));
		assertFalse(stems.contains(sn));
		stems = lt.getMatchingStems("el");
		assertEquals(1, stems.size());
		assertTrue(stems.contains(sn2));
		assertFalse(stems.contains(sn));
		assertFalse(stems.contains(sn3));
		stems = lt.getMatchingStems("elmas");
		assertEquals(3, stems.size());
		assertTrue(stems.contains(sn));
		assertTrue(stems.contains(sn2));
		assertTrue(stems.contains(sn3));
	}	
	
    @Test
    public void stemsSharingPartialPrefix1() {
        StemTrie lt = new StemTrie();
        StemNode sn3 = createStemNode("fix");
        StemNode sn1 = createStemNode("foobar");
        StemNode sn2 = createStemNode("foxes");
        lt.add(sn1);
        lt.add(sn2);
        lt.add(sn3);
        System.out.println(lt);
    }

    @Test
    public void testBigNumberOfBigWords() {
        StemTrie lt = new StemTrie();
        List<String> words = generateRandomWords(10000);
        List<StemNode> nodes = Lists.newArrayList();
        for (String s : words) {
            StemNode n =  createStemNode(s);
            lt.add(n);
            nodes.add(n);
        }
        for (StemNode node : nodes) {
            List<StemNode> res = lt.getMatchingStems(node.surfaceForm);
            assertTrue(res.contains(node));
            assertTrue(res.get(res.size() - 1).surfaceForm.equals(node.surfaceForm));
            for (StemNode n : res) {
                // Check if all stems are a prefix of last one on the tree. 
                assertTrue(res.get(res.size() - 1).surfaceForm.startsWith(n.surfaceForm));
            }
        }
    }
	
}