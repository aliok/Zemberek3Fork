package zemberek3.lexicon.graph;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

import org.junit.Test;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.PrimaryPos;

public class LexiconTreeTest {
	
	private StemNode createStemNode(String surfaceForm) {
		DictionaryItem di = new DictionaryItem(surfaceForm, surfaceForm, PrimaryPos.Noun, null, null, null);
		StemNode sn = new StemNode(surfaceForm, di, null, null);
		return sn;
	}
	
	@Test
	public void empty(){
		LexiconTree lt = new LexiconTree();
		List<StemNode> stems = lt.getMatchingStems("foo");
		assertEquals(stems.size(), 0);
	}
	
	@Test
	public void singleItem() {
		LexiconTree lt = new LexiconTree();
		StemNode sn = createStemNode("elma");
		lt.add(sn);
		List<StemNode> stems = lt.getMatchingStems("elma");
		assertEquals(1, stems.size());
		assertTrue(stems.contains(sn));
	}
	
	@Test
	public void distinctStems() {
		LexiconTree lt = new LexiconTree();
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
		LexiconTree lt = new LexiconTree();
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
		LexiconTree lt = new LexiconTree();
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
		LexiconTree lt = new LexiconTree();
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
        LexiconTree lt = new LexiconTree();
        StemNode sn3 = createStemNode("fix");
        StemNode sn1 = createStemNode("foobar");
        StemNode sn2 = createStemNode("foxes");
        lt.add(sn1);
        lt.add(sn2);
        lt.add(sn3);
        System.out.println(lt);
    }     
	
}