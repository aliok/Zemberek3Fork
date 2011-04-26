package zemberek3.lexicon.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zemberek3.lexicon.DictionaryItem;
import zemberek3.lexicon.TurkishDictionaryLoader;
import zemberek3.lexicon.TurkishSuffixes;
import zemberek3.structure.TurkishAlphabet;

/**
 * LexiconTree is a simple compact trie that holds stems
 * 
 * @author mdakin@gmail.com
 *
 */
public class LexiconTree {
	
	Node root = new Node();
    static TurkishAlphabet alphabet = new TurkishAlphabet();
    
	public void add(StemNode stem) {
		if (stem == null) { 
			throw new NullPointerException("Input key can not be null");
		}
		char[] chars = stem.surfaceForm.toCharArray();
		Node node = root;
		Node previousNode = null;

		// i holds the char index for input
		int i = 0;
		// fragmentSplitIndex is the index of the last fragment
		int fragmentSplitIndex = 0;
		// While we still have chars left on the input, or no child marked with s[i]
		// is found in subnodes
		while (node != null) {
			previousNode = node;
			node = node.getChildNode(chars[i]);
			if (node == null) {
				// This occurs if there is no child node exist:
				// Input order to clean tree: foo(1). It adds the input to the node.
				// or if input order is foo(1), foobar(2)
				// Just split it from the different char, foo - bar, and add it to
				// node so it will be "root - foo(1) - bar(2)"
				previousNode.addChild(new Node(stem, getSuffix(chars, i)));
			} else {
				// fragment split index finds the different character of input
				fragmentSplitIndex = getSplitPoint(chars, i, node.fragment);
				i += fragmentSplitIndex;
				//
				// if input order : foobar(1), foo(2)
				// split the first node from inequality point
				// root - foo(2) - bar(1)
				// or
				// if input order : foobar (1), fox(2)
				// root - fo - x(2)
				//         \ _ obar(1)
				//				
				if (fragmentSplitIndex < node.fragment.length) {
					Node newNode = new Node(stem, getSuffix(node.fragment, fragmentSplitIndex));
					if (i == chars.length) {
					    node.removeStems();
						node.addStem(stem);
						if (fragmentSplitIndex < node.fragment.length) {
							newNode.children = node.children;
							node.splitAndAdd(newNode, fragmentSplitIndex);
						}
					} else {
						Node n2 = new Node(stem, getSuffix(chars, i));
						newNode.children = node.children;
						node.splitAndAdd(newNode, fragmentSplitIndex);
						node.addChild(n2);
						// Remove the old object.
						node.removeStems();
					}					
					break;
				}
				// Homonym
				if ((i == chars.length && fragmentSplitIndex == node.fragment.length)) {
					node.addStem(stem);
					break;
				}
			}
		}

	}

	/**
	 * Finds the last position of common chars for 2 char arrays relative to a given index.
	 * @param input
	 * @param start
	 * @param fragment
	 * @return 
	 *   for input: "foo" fragment = "foobar" index = 0, returns 3
	 *   for input: "fool" fragment = "foobar" index = 0, returns 3
	 *   for input: "fool" fragment = "foobar" index = 1, returns 2
	 *   for input: "foo" fragment = "obar" index = 1, returns 2
	 *   for input: "xyzfoo" fragment = "foo" index = 3, returns 2
	 *   for input: "xyzfoo" fragment = "xyz" index = 3, returns 0
	 *   for input: "xyz" fragment = "abc" index = 0, returns 0
	 * 
	 */
	static int getSplitPoint(char[] input, int start, char[] fragment) {
		int fragmentIndex = 0;
		while (start < input.length && fragmentIndex < fragment.length
				&& input[start++] == fragment[fragmentIndex]) {
			fragmentIndex++;
		}
		return fragmentIndex;
	}

	private static char[] getSuffix(char[] arr, int index) {
		char[] res = new char[arr.length - index];
		System.arraycopy(arr, index, res, 0, arr.length - index);
		return res;
	}
	
	public String toString() {
		return root != null ? root.dump(false) : "";
	}

	public static class Node {
		private char[] fragment;
		int index;
		private ArrayList<Node> children;
		private ArrayList<StemNode> stems;

		public Node() {	
		}

		public Node(StemNode s, char[] fragment) {
			addStem(s);
			this.fragment = fragment;
			this.index = alphabet.getAphabeticIndex(fragment[0]);
		}

		public void removeStems() {
			stems = null;
		}
		
		public void addStem(StemNode s) {
			if (stems == null) {
				stems = new ArrayList<StemNode>(1);
			}
			if (!stems.contains(s)) {
				stems.add(s);
			}
		}

		public void addChild(Node node) {
			if (children == null) {
				children = new ArrayList<Node>(2);
			}
			int pos = getChildIndex (node.index);
			if(pos < 0) {
				children.add(-(pos + 1), node);
			}
		}
		
		// Search based on index values of children Node array. 
		// Returns index of node if it already exists,
		// -(pos +1) position to insert, if no element exist with given index 
		private int getChildIndex(int index) {
			if (children == null || children.size() == 0) {
				return -1;
			}
			int size = children.size();
			
			// Linear search if element count is smaller than a threshold.
			if(size < 6) {
				int i = 0;
				for (; i < size && children.get(i).index < index; i++);
				if (i == size) return -size;
				return children.get(i).index == index ? i : -(i + 1);
			}
			
			// Apply binary search if child count is big.
			int low = 0;
			int high = size - 1;
			while (low <= high) {
				int mid = (low + high) >> 1;
				Node midNode = children.get(mid);
				if (midNode.index < index) {
					low = mid + 1;
				} else if (midNode.index > index) {
					high = mid - 1;
				} else {
					return mid;
				}
			}
			return -(low + 1);
		}
		
		public void splitAndAdd(Node node, int fragmentSplitIndex) {
			fragment = Arrays.copyOf(fragment, fragmentSplitIndex);
			children = null;
			addChild(node);
		}

		public String getString() {
			return fragment == null ? "#" : new String(fragment);
		}

		public Node getChildNode(char c) {
			int childIndex = getChildIndex(alphabet.getAphabeticIndex(c));
			if (childIndex >= 0) {
				return children.get(childIndex);
			}
			return null;
		}

		public Node[] getAllChildNodes() {
			return children.toArray(new Node[children.size()]);
		}

		@Override
		public String toString() {
			String s = getString() + " : ";
			if (children != null) {
				s += "( ";
				for (Node node : children) {
					if (node != null) {
						s += node.getChar() + " ";
					}
				}
				s += ")";
			} else {
				s += ".";
			}
			if (stems != null) {
				for(int i=0; i<stems.size(); i++) {
					s += " * ";
				}
			}
			return s;
		}

		private char getChar() {
			if (fragment == null) { return '#'; }
			return fragment[0];
		}

		/**
		 * Returns string representation of node and all child nodes until leafs.
		 *
		 * @param b string buffer to append.
		 * @param level level of the operation
		 */
		private void toDeepString(StringBuffer b, int level) {
			char[] indentChars = new char[level * 2];
			for (int i = 0; i < indentChars.length; i++)
				indentChars[i] = ' ';
			b.append(indentChars).append(this.toString());
			b.append("\n");
			if (children != null) {
				for (Node subNode : this.children) {
					if (subNode != null) {
						subNode.toDeepString(b, level + 1);
					}
				}
			}
		}

		/**
		 * Flat string representation of node and all child nodes.
		 * Used for testing purposes only. Given a tree like this:
		 *
		 *      a
		 *     / \
		 *    ba  c*
		 *   /
		 *  e*
		 * 
		 * This method returns: a:(bc)|ba:(e)|e:(.)*|c:(.)*
		 *
		 * @param b stringbuffer to append.
		 */
		public final void toFlatString(StringBuffer b) {
			b.append(this.toString().replaceAll(" ", "")).append("|");
			if (children != null) {
				for (Node subNode : this.children) {
					if (subNode != null) {
						subNode.toFlatString(b);
					}
				}
			}
		}

		/**
		 * Returns string representation of Node (and subnodes) for testing.
		 *
		 * @param flat : if true, returns a flat version of node and all sub nodes
		 * using a depth first traversal. if false, returns multiline, indented
		 * version of node tree.
		 * @return a flat or tree string representation of trie.
		 */
		public final String dump(boolean flat) {
			StringBuffer b = new StringBuffer();
			if (flat) toFlatString(b);
			else toDeepString(b, 0);
			return b.toString();
		}

		public boolean hasObject() {
			return (stems != null && stems.size() > 0);
		}

	}
	
	public static void main(String[] args) throws IOException {
		LexiconTree lexicon = new LexiconTree();
		List<DictionaryItem> items = new TurkishDictionaryLoader().load(new File("src/resources/tr/master-dictionary.txt"));
		TurkishSuffixes suffixes = new TurkishSuffixes();
		LexiconGraph graph = new LexiconGraph(items, suffixes.getSuffixProvider());
		graph.generate();
		for(StemNode s : graph.getStems()) {
			lexicon.add(s);
		}
		System.out.println(lexicon.toString());
	}
}
