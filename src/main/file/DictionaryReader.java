package main.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.dictionary.Dictionary;

/**
 * 
 * A file reader that reads a list of newline delimited words into a Dictionary.
 *
 */
public class DictionaryReader {
    
	private Scanner scanner;

	/**
     * Create a new dictionary reader for the file at fileLoc.
     * @param fileLoc
	 * @throws FileNotFoundException 
     */
    public DictionaryReader(String fileLoc) throws FileNotFoundException {
        this.scanner = new Scanner(new File(fileLoc));
    }
    
    /**
     * Fill the Dictionary with all words in the file.
     * @param dictionary
     */
    public void fillDictionary(Dictionary dictionary) {
        while(this.scanner.hasNextLine()) {
        	dictionary.addWord(scanner.nextLine());
        }
    }   
}
