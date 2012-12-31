import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.err;
import static java.lang.System.out;

/**
 * WordList - a list of words. Used as a lookup dictionary for
 * a spelling checker. A word is a sequence of one or more
 * upper or lower case letters or apostrophes.
 * 
 * <P>Class Invariant: isWord( word ) for each  word in this
 * WordList. No illformed words may occur in this WordList.</P>
 * 
 * <P>Note that this class was written to illustrate programming
 * practices that we will learn this semester. It is not expected
 * that you will turn in a solution similar to this code for 
 * the first several programming assignments this semester.</P>
 * 
 * <P>This class was written to illustrate: good programming style,
 * maximum use of programming by contract, maximum use of
 * self checking code, and thorough testing. <B>Note that for a
 * class as simple as WordList, this is overkill.</B> In practice, an
 * experienced programmer would dispense with much (all?) the self
 * checking and testing and rely on the correctness of the implmentation
 * of the Java Collection Class TreeSet. </P>
 * 
 * <P>This class illustrates member functions that are sometimes added to a
 * class simply to facilitate testing: clone, equals, size, toString.</P>
 * 
 * <P>Implementation Plan
 *    1. add, toString - quick testing using System.out.println
 *    2. contains, size - begin jUnit testing
 *    3. readFile - the class is now ready to be used in SpellChecker application
 *    4. clear
 *    
 *  SpellChecker3 derived from SpellChecker2 by omitting clone, equals, and
 *  hashCode.
 *  
 *  TODO - replace args/utils with single library.
 * 
 * @author hedlund  hedlund@cs.unc.edu
 */
public final class WordList implements WordListInterface {
	
		// WordList is implemented as a restricted interface to
		// a Set of legal words.
	private Set<String> words;
	
		// An unmodifiable view of words that is used by all
		// Observer member functions. This ensures that Observers
		// can't modify this WordList.
	private Set<String> wordsSafe;
	
	
	/**
	 * Default constructor.
	 */
	public WordList() {
		words = new TreeSet<String>();
		wordsSafe = Collections.unmodifiableSet( words );
		//log.info( "WordList created" );
	}
	
	/**
	 * Add specified word to this word list if not already present.
	 * Mutator
	 * Pre - true
	 * @param  theWord to be added to this WordList
	 * @return true iff the specified word is an acceptable entry
	 * in this WordList.
	 */
	public boolean 
	add( final String theWord ) {
		final int oldSize = size(); 
		boolean result = false;
		if( isWord( theWord )) {
			result = true;
			words.add( theWord );
		}
		else {
			out.println( "WordList: add: illegal word = >>>"
					+ theWord + "<<<" );
			result = false;
		}
			// Post
			// if false returned then WordList unchanged and it does
			//    not contain theWord
			// if true returned then WordList contains theWord and
			//    the size of the WordList may have been increased by 1.
		assert( ( !result && size() == oldSize && !contains( theWord ))
				|| result && contains( theWord )
				&& ( size() == oldSize || size() == 1+oldSize )); 
		//Invariant.invariant( ( !result && size() == oldSize && !contains( theWord ))
				//|| result && contains( theWord )
					//&& ( size() == oldSize || size() == 1+oldSize )); 
		return result;
	}
	
	/**
	 * Remove all the words from this word list.
	 * Useful for testing.
	 * Mutator
	 * Pre - true
	 */
	public void
	clear() {
		words.clear();
			// Post
		assert( 0 == size() );
		//Invariant.invariant( 0 == size() ); 
	}

	/**
	 * Is the specified word in this WordList?
	 * Observer
	 * Pre - true
	 * Postcondition - a necessary condition is:
	 *    if return then dict.add(theWord).size() ==
	 *    		old_dict.size()
	 *    else dict.add(theWord).size() = 1 + old_dict.size()
	 * 
	 * @param theWord - element whose presence in the dictionary is to be 
	 * tested
	 * @return true if this WordList contains the specified element.
	 */
	public boolean 
	contains( final String theWord ) {
		final boolean result = wordsSafe.contains( theWord );
		return result;
	}

	/**
	 * Return true iff this WordList is in a legal state
	 * @return true iff this WordList is in a legal state
	 */
	public boolean
	invariant() { 
			// This class invariant is maintained by 'add' which 
			// screens out illegal words and does not allow them
			// to be entered into a WordList. This member function
			// could be eliminated (but, in general, every class
			// should have an invariant member function).
		return true; 
		}

	/**
	 * Is the specified input a legal entry for a WordList?
	 * @param theWord - a string to test for acceptance
	 * @return true iff the specified input is an acceptable entry 
	 * for a WordList
	 */
	public static boolean 
	isWord( final String theWord ) {
		boolean result = false;
		if( theWord.length() > 0 ) {
			Pattern pat = Pattern.compile("^[a-zA-Z']*$" );
			Matcher mat = pat.matcher( theWord );
			if( mat.matches() ) result = true;
		}
		return result;
	}
	
	/**
	 * Add contents of file to this word list.
	 * Mutator
	 * Pre - input file consists of words separated
	 * by one or more spaces.
	 * @param fileName - name of file to read
	 * @throws FileNotFoundException
	 */
	public void
	readFile( final String fileName ) 
		throws FileNotFoundException {
		
		final Scanner inp = new Scanner(
			new File( fileName ));
		while( inp.hasNext() ) {
			String line = inp.nextLine();
			String[] word = line.split( " " );
			for( int i = 0; i < word.length; i++ )
				words.add( word[i].trim() );
		}
	}
	
	/**
	 * Return the number of entries in this WordList.
	 * Used for testing.
	 * Observer
	 * Pre - true
	 * Post - result >= 0
	 * 
	 * @return the number of entries in the WordList
	 */
	public int
	size() {
		return wordsSafe.size();
	}
	
	
	/**
	 * Return a String representation of all words in this WordList
	 * Observer
	 * Pre - true
	 * @return String with all the words in this WordList
	 */
	@Override public String
	toString() {
		return wordsSafe.toString();
	}
}
