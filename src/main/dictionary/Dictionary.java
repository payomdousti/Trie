package main.dictionary;

import java.util.LinkedList;
import java.util.List;
//import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 
 * A thread-safe concurrent Dictionary singleton that allows for adding words and
 * searching words by prefix. For more information on assumptions on concurrency
 * see the README.
 *
 */

public class Dictionary extends PrefixTree<String> {

	// Create an instance of Dictionary right off the bat for Singleton Pattern
	private static final Dictionary instance = new Dictionary();

	// Store lastReuslt for caching
	private List<String> lastResult;

	// Store lastPrefix in case cache has been cleared 
	// Initialize to a value which will return no results
	private String lastPrefix = "-1";

	// Set aside memory for the singleton
	private Dictionary() {}

	/**
	 * Return the shared Dictionary singleton.
	 * @return the single instance of the dictionary
	 */
	public static Dictionary getInstance() {
		return instance;
	}

	/**
	 * Add a word to the current dictionary.
	 * @param word
	 */
	public void addWord(String word) {
		instance.insert(word, word);
	}

	/**
	 * Get all words that start with or matches prefix.
	 * @param prefix
	 * @return the list of words that match the prefix
	 */
	public List<String> getWords(String prefix) {
		List<String> list = new LinkedList<String>();
		for(String word : instance.findPrefix(prefix)) { list.add(word); }
		instance.setLastPrefix(prefix);
		instance.setLastResult(list);
		return list;
	}

	/**
	 * Cache the last result
	 * 
	 * @param the last result list
	 * 
	 */
	public void setLastResult(List<String> list) {
		instance.lastResult = list;
	}

	/**
	 * Set the last used prefix in case cache needs to be regenerated
	 * 
	 * @param prefix
	 * 
	 */
	public void setLastPrefix(String prefix) {
		instance.lastPrefix = prefix;
	}

	/**
	 * Clear the cache if needed
	 * 
	 */
	public void clearCache() {
		instance.lastResult = null;
	}

	/**
	 * Get the results of the last search from a memory sensitive cache. The cache can
	 * be cleared if the memory is needed and the search should just be performed again.
	 * @return the last result searched
	 */
	public List<String> getLastResults() {
		return instance.lastResult != null ? instance.lastResult : getWords(instance.lastPrefix);
	}
}
