
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


class WordList {

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

	final Set<String> words;
	final List<Set<String>> listwords;
	String ILLEGAL_CHARACTERS = ("\\!@#$%^&*()-_=+|]}[{';:/>.<,~`\'\"");
	
	/**
	 * Default constructor.
	 */
	WordList() {
		words = new TreeSet<String>();
		listwords = new ArrayList<Set<String>>();
		listwords.add(words);
		
	}

	/**
	 * Add specified word to this word list if not already present. Return true
	 * if theWord was not present in the WordList.
	 */
	final protected boolean add(String theWord) {
		if (!isLegalWord(theWord))
			return false;
		needNewSet(theWord);
		int[] oldsizeandindex = testSizeAdd(theWord); //oldsizeand index is the old size ([0]) and the index of whichever element of listwords that theWord was added to ([1]) 
		return (listwords.get(oldsizeandindex[1]).size() > oldsizeandindex[0]);//it is used to determine whether or not the size of this element changed
	}
	
	/**
	 * adds theWord to the correct <code>words</code> and returns the old size and index of the array theWord was added to 
	 * for comparison to the current size of words 
	 * @Mutator
	 * @param theWord
	 * @return old size of the specific <code>words</code> that theWord got added to
	 */
protected final int[] testSizeAdd(String theWord){
	int[] oldsizeandindex={0,0};
	if (theWord.length()>10){
		oldsizeandindex[0] = listwords.get(10).size();
		listwords.get(10).add(theWord);
		oldsizeandindex[1] = 10;
	}
	else {
		oldsizeandindex[0] = listwords.get(theWord.length()-1).size();
		listwords.get(theWord.length()-1).add(theWord);
		oldsizeandindex[1] = theWord.length()-1;
	}
	return oldsizeandindex;
}
	/**
	 * @Mutator iff a new words needs to be added
	 * @param theWord
	 * @returns true if theWord was successfully added
	 * 			false if it wasn't added
	 */
	protected void needNewSet(String theWord){
		if (theWord.length() > listwords.size() && listwords.size()<=10 ){
			for (int index =0; index < theWord.length()-listwords.size(); index++){
				ValidateUtilities.checkForLegalAddPosition(index, listwords);
				Set<String> words = new TreeSet<String>();
				listwords.add(words);
			}
		}
		
	}
	/**
	 * @Observer
	 * @param theWord
	 * @return false if theWord contains illegal character
	 * 			true iff theWord does not contains illegal character
	 */
	protected final boolean isLegalWord(String theWord){
		boolean result = true;
		for ( int index=0; index<ILLEGAL_CHARACTERS.length(); index++ ){
			if (theWord.lastIndexOf(ILLEGAL_CHARACTERS.charAt(index)) == -1){
				result = false;
			}
		}
		return result;
	}
	/**
	 * @Mutator iff the character is not in ILLEGAL_CHARACTERS
	 * @param character
	 * @return true if ILLEGAL_CHARACTERS did not previously contain the character
	 * 			false if the character was already in ILLEGAL_CHARACTERS and the character was not added 
	 */
	protected final boolean addIllegalChar(String character){
		int size = ILLEGAL_CHARACTERS.length();
		ILLEGAL_CHARACTERS.concat(character);
		return (size<ILLEGAL_CHARACTERS.length());
	}
	/**
	 * @Mutator iff the character is in ILLEGAL_CHARACTERS
	 * @param character
	 * @return true if ILLEGAL_CHARACTERS did previously contain the character
	 * 			false if the character was not in ILLEGAL_CHARACTERS and the character was not removed 
	 */
	protected final boolean removeIllegalCharacter(String character){
		int size = ILLEGAL_CHARACTERS.length();
		ILLEGAL_CHARACTERS.replaceAll(character, "");
		return (size>ILLEGAL_CHARACTERS.length());
	}
	
	/**
	 * @Mutator
	 * does what its name implies
	 */
	protected final void clear() {
		for (int index =0; index<listwords.size();index++){
			ValidateUtilities.checkForLegalIndex(index, listwords);
			listwords.get(index).clear();
		}
	}

	/**
	 * 
	 * @param theWord
	 * @return true if the character is in one of the lists
	 *  		false if it isn't
	 */
	protected final boolean contains(String theWord) {
		boolean result = false;
		for (int index =0; index<listwords.size();index++){
			ValidateUtilities.checkForLegalIndex(index, listwords);
			if (listwords.get(index).contains(theWord))
				
				result= true;
		}
			return result;
		}

	/**
	 * Add contents of file to this word list.
	 * 
	 * @Mutator
	 * @requires input file consists of words separated by one or more spaces.
	 * @param fileName -
	 *            name of file to read
	 * @throws FileNotFoundException
	 */
	protected final void readFile(String fileName) throws FileNotFoundException {

		Scanner inp = new Scanner(new File(fileName));
		while (inp.hasNext()) {
			String line = inp.nextLine();
			String[] word = line.split(" ");
			for (int i = 0; i < word.length; i++)
				listwords.get(word.length-1).add(word[i].trim());
		}
	}

	/**
	 * @Observer
	 * Return the number of entries in this WordList. Used for testing.
	 */
	protected final int size() {
		int size=0;
		for (int index =0; index<listwords.size();index++)
			size+=listwords.get(index).size();
	return size;
	}

	/**
	 * @Observer
	 * Return a String representation of all words in this WordList
	 */
	public final String toString() {
		String string="";
		for (int index =0; index<listwords.size();index++){
			ValidateUtilities.checkForLegalIndex(index, listwords);
			string+=listwords.get(index).toString();
		}
		return string;
	}

}
