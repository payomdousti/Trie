package main.dictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A trie implementation which implements the ability to add words and find prefixed words
 * 
 */

public class PrefixTree<T> implements Trie<T> {

	// Set an empty root node, standard with tries
	private Node<T> root = new Node<T>();

	public void insert(String key, T value) {
		insertHelper(root, key, 0, value);
	}

	private void insertHelper(Node<T> currNode, String key, int pos, T value) {

		// Use the current char as the key for traversal
		Character c = key.charAt(pos);
		// Traverse down the trie if possible
		Node<T> nextNode = currNode.getChildren().get(c);

		// Char not found in the traversal
		if (nextNode == null) {

			// Add a new prefix node to the trie
			nextNode = new Node<T>();
			nextNode.setChar(c);

			// Are there more chars to look at? 
			if (pos < key.length() - 1) {
				// Recursively the rest of the characters as nodes
				insertHelper(nextNode, key, pos + 1, value);
			} else {
				// Mark this node as the end of a word
				// Terminal is necessary since end of words exist in the middle of tries
				nextNode.setPrefix(value);
				nextNode.setTerminal(true);
			}
			// Insert the node
			currNode.getChildren().put(c, nextNode);
		} else {
			// More chars, no need to insert but keep traversing
			if (pos < key.length() - 1) {
				insertHelper(nextNode, key, pos + 1, value);
			} else {
				// Case where this word exists in the trie already, no need to do anything
			}
		}
	}

	public List<T> findPrefix(String prefix) {

		// Break the word into characters
		char[] ch = prefix.toCharArray();

		// Start traversal at the root of the trie
		Node<T> node = root;

		// Traverse each character
		for (int i = 0; i < ch.length; i++) {
			// Go one level deeper with each subsequent character
			node = node.getChildren().get(ch[i]);
			// Quit digging if you can't find a letter
			if (node == null) { break; }
		}

		// List to store the words in the trie that are prefixed by the search term
		List<T> searchResults = new LinkedList<T>();

		// If you successfully found the entire prefix, get the rest of the search results
		if (node != null) { getSearchResults(node, searchResults); }

		return searchResults;
	}

	private void getSearchResults(Node<T> currNode, List<T> searchResults) {

		// If the current node marks the end of a word, enter the word into the list
		// This is not necessarily a leaf
		if (currNode.isTerminal()) { searchResults.add(currNode.getPrefix()); }

		// Iterate over every child since these are guaranteed to be prefixed by the search term
		Map<Character, Node<T>> children = currNode.getChildren();
		Iterator<Character> childIter = children.keySet().iterator();
		while (childIter.hasNext()) {
			Character ch = (Character)childIter.next();
			Node<T> nextNode = children.get(ch);
			// Recurse through all children
			getSearchResults(nextNode, searchResults);
		}
	}
}
