package zemberek3.repository;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import zemberek3.structure.TurkicAlphabet;
import zemberek3.structure.TurkishAlphabet;

/**
 * A simple online compact String trie.
 * 
 * @author mdakin
 * 
 */
public class StringTrie {
	private Node root = new Node(false, null);
	public int nodesCreated;
	private static final TurkicAlphabet alphabet = new TurkishAlphabet();

	public void add(String s) {
		if (s == null) {
			throw new NullPointerException("Input key can not be null");
		}
		byte[] indexedChars = alphabet.toIndexes(s);
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
			node = node.getChildNode(indexedChars[i]);
			if (node != null) {
				fragmentSplitIndex = getSplitPoint(indexedChars, i, node.fragment);
				i += fragmentSplitIndex;
				if ((fragmentSplitIndex < node.fragment.length)
						|| (i == indexedChars.length && fragmentSplitIndex == node.fragment.length)) {
					break;
				}
			}
		}

		if (node == null) {
			// This occurs if trie is empty, or does not have any start node for
			// given input or if there is a previous trie node which is a prefix of the
			// input.
			//
			// Trie: root, input: foo
			// result: root - foo*
			//
			// Trie: root-foo input:foobar
			// result: root - foo* - bar*
			previousNode.addChild(new Node(true, getSuffix(indexedChars, i)));
			return;
		}
		//
		// Trie: root - foobar input: foo
		// root - foo* - bar*
		//
		// Trie: root - foobar input:fox
		// root - fo - x*
		//         \ _ obar*
		//
		if (node != null) {
			Node newNode = new Node(node.wordNode, getSuffix(node.fragment, fragmentSplitIndex));
			if (i == indexedChars.length) {
				node.wordNode = true;
				if (fragmentSplitIndex < node.fragment.length) {
					newNode.children = node.children;
					node.splitAndAdd(newNode, fragmentSplitIndex);
				}
			} else {
				Node n2 = new Node(true, getSuffix(indexedChars, i));
				newNode.children = node.children;
				node.splitAndAdd(newNode, fragmentSplitIndex);
				node.addChild(n2);
				node.wordNode = false;
			}
		}
	}


	static boolean continueToTraverse() {
		return false;
	}
	
	static int getSplitPoint(byte[] input, int start1, byte[] fragment) {
		int fragmentIndex = 0;
		while (start1 < input.length && fragmentIndex < fragment.length
				&& input[start1++] == fragment[fragmentIndex]) {
			fragmentIndex++;
		}
		return fragmentIndex;
	}

	private static byte[] getSuffix(byte[] arr, int index) {
		byte[] res = new byte[arr.length - index];
		System.arraycopy(arr, index, res, 0, arr.length - index);
		return res;
	}

	public String toFlatString() {
		return root.dump(true);
	}

	public String toDeepString() {
		return root.dump(false);
	}

	// public List<String> getMatchingStrings(String input) {
	// Node node = root;
	// int index = 0;
	// String s = "";
	// List<String> objects = new ArrayList<String>();
	// while (index < input.length()) {
	// node = node.getChildNode((byte)alphabet.getIndex(input.charAt(index)));
	// if (node == null) break;
	// String nodeString = node.getString();
	// s += nodeString;
	// if (input.startsWith(s) && node.hasObject()) {
	// objects.add(node.object);
	// }
	// index += nodeString.length();
	// }
	// return objects;
	// }

	// public void walk() {
	// Node<T> node = root;
	// int index = 0;
	// int nodeCount = 0;
	// List<T> objects = new ArrayList<T>();
	// while (true) {
	// //TODO(mdakin): implement walk.
	// }
	// }

	public Node getRoot() {
		return root;
	}

	public String getInfo() {
		return "Nodes created: " + nodesCreated;
	}

	/**
	 * 
	 * @author mdakin
	 */
	public static class Node {
		private byte[] fragment;
		// int index;
		int attribute;
		boolean wordNode;
		private ArrayList<Node> children;

		public Node(boolean wordNode, byte[] fragment) {
			this.wordNode = wordNode;
			this.fragment = fragment;
			resetChildren();
		}

		@SuppressWarnings("unchecked")
		public void resetChildren() {
			children = new ArrayList<Node>();
		}

		public void addChild(Node node) {
			int x = node.getChar();
			int counter = 0;
			// We keep nodes sorted by their chars
			for (int i = 0; i < children.size(); i++) {
				if (x < children.get(i).getChar()) {
					break;
				}
				counter++;
			}
			children.add(counter, node);
		}

		public void splitAndAdd(Node node, int fragmentSplitIndex) {
			fragment = Arrays.copyOf(fragment, fragmentSplitIndex);
			resetChildren();
			addChild(node);
		}

		public String getString() {
			return getFragmentString();
		}

		public char[] getChars() {
			char[] chars = new char[fragment.length];
			for (int i = 0; i < fragment.length; i++) {
				chars[i] = alphabet.getCharByAlphabeticIndex(fragment[i]);
			}
			return chars;
		}

		public Node getChildNode(int c) {
			if (children == null)
				return null;
			// TODO mdakin: Apply binary search
			for (Node node : children) {
				if (node.fragment[0] == c)
					return node;
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		public Node[] getChildren() {
			return children.toArray(new Node[children.size()]);
		}

		@Override
		public String toString() {
			String s = getString() + " : ";
			if (children != null) {
				s += "( ";
				for (Node node : children) {
					if (node != null) {
						byte b = node.getChar();
						if (b != -1) {
							s += alphabet.getCharByAlphabeticIndex(b) + " ";
						}
					}
				}
				s += ")";
			} else {
				s += ".";
			}
			if (wordNode) {
				s += " * ";
			}
			return s;
		}

		private byte getChar() {
			if (fragment == null) {
				return '#';
			}
			return fragment[0];
		}

		private String getFragmentString() {
			if (fragment == null) {
				return "#";
			}
			StringBuilder frs = new StringBuilder();
			for (byte b : fragment) {
				frs.append(alphabet.getCharByAlphabeticIndex(b));
			}
			return frs.toString();
		}

		/**
		 * Returns string representation of node and all child nodes until
		 * leafs.
		 * 
		 * @param b
		 *            string buffer to append.
		 * @param level
		 *            level of the operation
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
		 * 
		 * Flat string representation of node and all child nodes. Used for
		 * testing purposes only. Given a tree like this:
		 * 
		 * a / \ ba c* / e*
		 * 
		 * This method returns: a:(bc)|ba:(e)|e:(.)*|c:(.)*
		 * 
		 * @param b
		 *            stringbuffer to append.
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
		 * @param flat
		 *            : if true, returns a flat version of node and all sub
		 *            nodes using a depth first traversal. if false, returns
		 *            multiline, indented version of node tree.
		 * @return a flat or tree string representation of trie.
		 */
		public final String dump(boolean flat) {
			StringBuffer b = new StringBuffer();
			if (flat) {
				toFlatString(b);
			} else {
				toDeepString(b, 0);
			}
			return b.toString();
		}

		public boolean hasString() {
			return wordNode;
		}

		/**
		 * Writes content of node and all sub nodes recursively to data output
		 * stream. TODO(mdakin): Serialized size could be improved by writing
		 * less for nodes containing less chars.
		 * 
		 * @param dos
		 *            Data output stream
		 * @throws IOException
		 *             if something goes wrong during write.
		 */
		public void serialize(DataOutputStream dos) throws IOException {
			dos.writeInt(fragment.length);
			// System.out.println("written len: " + str.length);
			for (byte c : fragment) {
				// System.out.println("written char: " + c);
				dos.writeChar(c);
			}
			dos.writeInt(attribute);
			// dos.writeInt(bitmap);
			if (children == null) {
				return;
			}
			for (Node child : children) {
				child.serialize(dos);
			}
		}

		public void deserialize(DataInputStream dis) throws IOException {
			// int len = dis.readInt();
			// // System.out.println("read len: " + len );
			// fragment = new byte[len];
			// for (int i=0; i<len; i++) {
			// fragment[i] = dis.readByte();
			// // System.out.println("read char: " + str[i] );
			// }
			// attribute = dis.readInt();
			// // bitmap = dis.readInt();
			// if (bitmap == 0) {
			// return;
			// } else {
			// // Create the node list in advance to gain performance. We know
			// that it
			// // will contain "number of one bits in the bitmap" count of sub
			// nodes.
			// // So we will not bother expanding the bitmap
			// children = new Node[Integer.bitCount(bitmap)];
			// }
			// int limit = Integer.numberOfLeadingZeros(bitmap);
			// for (int i = Integer.numberOfTrailingZeros(bitmap); i < 32 -
			// limit; i++) {
			// if (hasChild(i)) {
			// Node child = new Node(alphabet.getChar(i));
			// children[getArrayIndex(i)] = child;
			// child.deserialize(dis);
			// // Node n = addNodeFor(TurkishAlphabet.getChar(i));
			// // n.deserialize(dis);
			// }
			// }
		}

	}

	public void save(BufferedOutputStream bufferedOutputStream) {

	}

}
