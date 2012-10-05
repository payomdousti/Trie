package main.dictionary;

import java.util.List;
/**
 * Tries are a compact data structure which allow for fast word prefix lookups
 * 
 */
public interface Trie<T> {
	/**
	 * Insert nodes to a trie
	 */
	public void insert(String key, T value);

	/**
	 * Look for words which have a certain prefix
	 */
	public List<T> findPrefix(String prefix);
}
