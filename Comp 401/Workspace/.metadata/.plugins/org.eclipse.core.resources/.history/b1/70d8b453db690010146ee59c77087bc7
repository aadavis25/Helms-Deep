
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WordListIndexed implements WordListInterface {

		private static int MAX_INDEX;
		
			// Class invariant:
			// dict.get( 1 ... MAX_INDEX ) are each WordLists that store
			//    only words of length 1, 2, ... MAX_INDEX respecitively.
			// dict.get(1+MAX_INDEX) holds all words with more than
			//    MAX_INDEX characters.
			// dict.get(0) is not used.
		private List<WordList> dict = new ArrayList<WordList>( 2+MAX_INDEX );
		
		public WordListIndexed(int theMaxIndex ) {
			MAX_INDEX = theMaxIndex;
			for( int i = 0; i <= 1+MAX_INDEX; i++ )
				dict.add( new WordList() );
		} 
		
		public boolean add( final String theWord ) {
			if( isLegal( theWord) )
			  return dict.get( computeIndex( theWord )).add( theWord );
			return false;
		}

		public boolean isLegal( String theWord ) {
			return true;
		}
		
		public void	clear() {
			for( int i = 0; i <= 1+MAX_INDEX; i++ )
				dict.get(i).clear();
			assert( 0 == size() );
		}
	
		private static int 
		computeIndex( String theWord ) {
			return Math.min( theWord.length(), 1+MAX_INDEX );
		}
		
		public boolean
		contains( String theWord ) {
			return dict.get( computeIndex( theWord )).contains( theWord );
		}
	
		/**
		 * Used for testing.
		 */
		public WordList
		getWordList( int theIndex ) {
			assert( legalIndex( theIndex ) );
			return dict.get( theIndex );
		}

		private static boolean 
		legalIndex( int theIndex ) {
			return( 1 <= theIndex && theIndex <= 1+MAX_INDEX );
		}
		
		/**
		 * Expose representation for testing.
		 * @return
		 */
		public List<Integer>
		peekRepresentation() {
			List<Integer> result = new ArrayList<Integer>(1+MAX_INDEX);
			for( int i = 0; i <= 1+MAX_INDEX; i++ )
				result.add( dict.get(i).size());
			return result;
		}
		
		/**
		 * Add contents of file to this word list.
		 * Mutator
		 * Pre - input file consists of words separated
		 * by one or more spaces.
		 * @param name of file to read
		 * @throws FileNotFoundException
		 */
		public void
		readFile( final String fileName ) 
			throws FileNotFoundException {
			
			Scanner inp = new Scanner(
				new File( fileName ));
			while( inp.hasNext() ) {
				String line = inp.nextLine();
				String[] word = line.split( " " );
				for( int i = 0; i < word.length; i++ )
					add( word[i].trim() );
			}
		}
		
		public int
		size() {
			int result = 0;
			for( int i = 0; i <= 1+MAX_INDEX; i++ )
				result += dict.get(i).size();
			return result;
		}
			
		public String toStringRepresentation() {
			String result = "";
			for( int i = 1; i <= 1+MAX_INDEX; i++ ) {
				if( dict.get(i).size() != 0 )
					result += i + "   " + dict.get(i).toString() + "\n";
			}
			return result;
		}
		
}