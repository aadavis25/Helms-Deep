import java.io.FileNotFoundException;
import java.util.List;
import static java.lang.System.out;

/**
 * List of English language words suitable for use as a dictionary
 * for a spelling checker.
 * 
 * Singleton.  
 * 
 * @author hedlund 
 * 
 */
public class EnglishWordList implements WordListInterface {

	public static final String PATH 
		= "C:\\Users\\aaronmd\\Dropbox\\COPM401\\workspace\\Program10";
	private static final String[] inputFiles 
		= {"english.0", "english.1"
		, "english.2", "english.3"};
	
	private static EnglishWordList ENGLISH_LIST = null;
	private static WordListIndexed d_wordList = new WordListIndexed(0);
	
	private EnglishWordList() throws FileNotFoundException {
		initialize();
	}

	public boolean 
	add( String theName ) {
		return ENGLISH_LIST.add( theName );
	}
	public boolean 
	contains( String theName ) {
		return ENGLISH_LIST.contains( theName );
	}
	
	public static EnglishWordList 
	getInstance() 
		throws FileNotFoundException {
		
		if( ENGLISH_LIST == null ) 
			ENGLISH_LIST = new EnglishWordList();
		return ENGLISH_LIST;
	}
	
	public int size() { return d_wordList.size(); }
	
	/**
	 * Setup WordList with an English language word list.
	 */
	private void
	initialize() 
		throws FileNotFoundException {
		for( int i = 0; i < inputFiles.length; i++ )
			d_wordList.readFile( PATH + inputFiles[i] );
		List<Integer> num = d_wordList.peekRepresentation();
		out.println( num.toString() );
		Integer sum = 0;
		for( Integer val : num )
			sum += val;
		out.println( "sum  = " + sum );
	}

	@Override
	public void clear() {
		
	}

	@Override
	public void readFile(String fileName) throws FileNotFoundException {
		
	}
}
