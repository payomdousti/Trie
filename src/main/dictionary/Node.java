package main.dictionary;

import java.util.Map;
import java.util.TreeMap;

public class Node<T> {

	// Set of children in the trie
	// Hashmap was not used because it wastes too much memory due to load factor balancing 
	// TreeMap was used because it is more memory efficient than a HashMap although it is slower O(lg(n)) vs. O(1)
	private Map<Character, Node<T>> children = new TreeMap<Character, Node<T>>(); 

	private Character charKey; // Char at the current level of the trie, acts as a key to the prefix
	private T prefixVal;	// The prefix including the characters traversed to get to this node
	private boolean terminal; // Does this node mark the end of a word?



	/**
	 * 
	 * @return the character at the current trie node
	 */
	public Character getChar() {
		return charKey;
	}

	/**
	 * Set the character key at the current trie node
	 * 
	 */
	public void setChar(Character charKey) {
		this.charKey = charKey;
	}

	/**
	 * 
	 * @return the prefix at this trie level
	 */
	public T getPrefix() {
		return prefixVal;
	}

	/**
	 * Set the prefix including all characters traversed to this point 
	 * including the current node's char
	 * 
	 */
	public void setPrefix(T prefixVal) {
		this.prefixVal = prefixVal;
	}

	/**
	 * A terminal node is marking which denotes that a node represents the end of a word
	 * 
	 * @return whether this node marks the end of a word
	 */
	public boolean isTerminal() {
		return terminal;
	}

	/**
	 * Mark that this node represents the end of a word
	 * 
	 */
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}

	/**
	 * 
	 * @return The set of child trie nodes
	 */
	public Map<Character, Node<T>> getChildren() {
		return children;
	}

	/**
	 * Set the children for this node
	 * 
	 */
	public void setChildren(Map<Character, Node<T>> children) {
		this.children = children;
	}
}
