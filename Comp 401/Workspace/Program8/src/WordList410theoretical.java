
import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


class WordListTheoretical {

	/**
	 * WordList - a list of words. Used as a lookup dictionary for a spelling
	 * checker.
	 * 
	 * <P>
	 * This class was written to illustrate: good programming style,
	 *  and maximum use of self checking code. <B>Note
	 * that for a class as simple as WordList, this is overkill.</B> In
	 * practice, an experienced programmer would dispense with much (all?) the
	 * self checking and testing and rely on the correctness of the
	 * implementation of the Java Collection Class TreeSet.
	 * </P>
	 * 
	 * <P>
	 * Implementation Plan 1. add, toString - quick testing using
	 * System.out.println 2. contains, size - begin jUnit testing 3. readFile -
	 * the class is now ready to be used in SpellChecker application 4. clear
	 */

	Set<String> words;
	Set<String> words2;
	Set<String> words3;
	Set<String> words4;
	Set<String> words5;
	Set<String> words6;
	Set<String> words7;
	Set<String> words8;
	Set<String> words9;
	Set<String> words10;
	Set<String> wordsabove;
	final String ILLEGAL_CHARACTERS = ("\\!@1234567890#$%^&*()-_=+|]}[{';:/>.<,~`\'\"");
	
	/**
	 * Default constructor.
	 */
	WordListTheoretical() {
		words = new TreeSet<String>();
		words2 = new TreeSet<String>();
		words3 = new TreeSet<String>();
		words4 = new TreeSet<String>();
		words5 = new TreeSet<String>();
		words6 = new TreeSet<String>();
		words7 = new TreeSet<String>();
		words8 = new TreeSet<String>();
		words9 = new TreeSet<String>();
		words10 = new TreeSet<String>();
		wordsabove = new TreeSet<String>();
		
	}

	/**
	 * Add specified word to this word list if not already present. Return true
	 * if theWord was not present in the WordList.
	 */
	boolean add(String theWord) {
		if (!isLegalWord(theWord)) //guard statement
			return false;
		return whichList(theWord);
	}
	/**
	 * @Observer
	 * @param theWord
	 * @return false if theWord contains illegal character
	 * 			true iff theWord does not contains illegal character
	 */
	private final boolean isLegalWord(String theWord){
		boolean result = true;
		for ( int index=0; index<ILLEGAL_CHARACTERS.length(); index++ ){
			if (theWord.lastIndexOf(ILLEGAL_CHARACTERS.charAt(index)) != -1){  //if the word has an index of one of the illegal characters
				result = false;													//the word has an illegal character, return false & do not add
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param theWord
	 * @return true if the word was not already in the list
	 * 			false if the word was in the list
	 */
	private final boolean whichList(String theWord){
		int length = theWord.length();
		switch (length){
		case 1: int oldSize = words.size(); wordsabove.add(theWord); return words.size() > oldSize;
		case 2: int oldSize2 = words.size(); words2.add(theWord); return words2.size() > oldSize2;
		case 3: int oldSize3 = words.size(); words3.add(theWord); return words3.size() > oldSize3;
		case 4: int oldSize4 = words.size(); words4.add(theWord); return words4.size() > oldSize4;
		case 5: int oldSize5 = words.size(); words5.add(theWord); return words5.size() > oldSize5;
		case 6: int oldSize6 = words.size(); words6.add(theWord); return words6.size() > oldSize6;
		case 7: int oldSize7 = words.size(); words7.add(theWord); return words7.size() > oldSize7;
		case 8: int oldSize8 = words.size(); words8.add(theWord); return words8.size() > oldSize8;
		case 9: int oldSize9 = words.size(); words9.add(theWord); return words9.size() > oldSize9;
		case 10: int oldSize10 = words.size(); words10.add(theWord); return words10.size() > oldSize10;
		default: int oldSizeabove = words.size(); wordsabove.add(theWord); return wordsabove.size() > oldSizeabove;
		}
	}
	/**
	 * @Mutator iff the character is not in ILLEGAL_CHARACTERS
	 * @param character
	 * @return true if ILLEGAL_CHARACTERS did not previously contain the character
	 * 			false if the character was already in ILLEGAL_CHARACTERS and the character was not added 
	 */
	final boolean addIllegalChar(String character){
		if (!ILLEGAL_CHARACTERS.contains(character)){
			ILLEGAL_CHARACTERS.concat(character);
			return true;
		}
		return false;
	}
	/**
	 * @Mutator iff the character is in ILLEGAL_CHARACTERS
	 * @param character
	 * @return true if ILLEGAL_CHARACTERS did previously contain the character
	 * 			false if the character was not in ILLEGAL_CHARACTERS and the character was not removed 
	 */
	final boolean removeIllegalCharacter(String character){
		if (ILLEGAL_CHARACTERS.contains(character)){
			ILLEGAL_CHARACTERS.replaceAll(character, "");
			return true;
		}
		return false;
	}
	
	/**
	 * @Mutator
	 * does what its name implies
	 */
	final void clear() {
		words.clear();
		words2.clear();
		words3.clear();
		words4.clear();
		words5.clear();
		words6.clear();
		words7.clear();
		words8.clear();
		words9.clear();
		words10.clear();
		wordsabove.clear();
		
	}

	/**
	 * 
	 * @param theWord
	 * @return true if the character is in one of the lists
	 *  		false if it isn't
	 */
	final boolean contains(String theWord) {
		return whichContains(theWord);
		}
	
	private final boolean whichContains( String theWord){
		if (words.contains(theWord))
			return true;
		else if (words2.contains(theWord))
			return true;
		else if (words3.contains(theWord))
			return true;
		else if (words4.contains(theWord))
			return true;
		else if (words5.contains(theWord))
			return true;
		else if (words6.contains(theWord))
			return true;
		else if (words7.contains(theWord))
			return true;
		else if (words8.contains(theWord))
			return true;
		else if (words9.contains(theWord))
			return true;
		else if (words10.contains(theWord))
			return true;
		else if (wordsabove.contains(theWord))
			return true;
		else return false;
	}

	/**
	 * Add contents of file to this word list.
	 * 
	 * @requires input file consists of words separated by one or more spaces.
	 * @param fileName -
	 *            name of file to read
	 * @throws FileNotFoundException
	 */
	final void readFile(String fileName) throws FileNotFoundException {

		Scanner inp = new Scanner(new File(fileName));
		while (inp.hasNext()) {
			String line = inp.nextLine();
			String[] word = line.split(" ");
			int length =0;
			for (int i = 0; i < word.length; i++){  //add each word to appropriate list
				length = word[i].length();
				switch (length){
				case 1: wordsabove.add(word[i]);
				case 2: words2.add(word[i]);
				case 3: words3.add(word[i]);
				case 4: words4.add(word[i]);
				case 5: words5.add(word[i]);
				case 6: words6.add(word[i]);
				case 7: words7.add(word[i]);
				case 8: words8.add(word[i]);
				case 9: words9.add(word[i]); 
				case 10: words10.add(word[i]); 
				default: wordsabove.add(word[i]); 
				}
			}
		}
	}

	/**
	 * Return the number of entries in this WordList. Used for testing.
	 */
	final int size() {
		return words.size()+words2.size()+words3.size()+words4.size()+words5.size()+words6.size()+words7.size()+words8.size()+words9.size()+words10.size()+wordsabove.size();
	}

	/**
	 * Return a String representation of all words in this WordList
	 */
	final public String toString() {
		String str;
		str = words.toString()+words2.toString()+words3.toString()+words4.toString()+words5.toString()+words6.toString()+words7.toString()+words8.toString()+words9.toString()+words10.toString()+wordsabove.toString();
		return str;
	}

}
