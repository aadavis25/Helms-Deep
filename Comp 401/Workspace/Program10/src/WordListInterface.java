

import java.io.FileNotFoundException;

/**
 * The minimal set of behaviors a WordList must provide 
 * to the spelling checker. Preconditions and postconditions
 * in class WordList.
 * 
 * @author hedlund
 *
 */
public interface WordListInterface {

	boolean add( final String theName );

	void clear();

	boolean contains( final String theName );

	void readFile( final String fileName )
		throws FileNotFoundException;

	int size();
}
