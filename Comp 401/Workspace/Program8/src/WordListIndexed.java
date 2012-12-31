import java.util.ArrayList;
import java.util.List;


public class WordListIndexed extends WordList {
	final List<String> words;
	final List<List<String>> listwords;
	String ILLEGAL_CHARACTERS = ("\\!@#$%^&*()-_=+|]}[{';:/>.<,~`\'\"");
	
	/**
	 * default constructor
	 */
	WordListIndexed(){
		words = new ArrayList<String>();
		listwords = new ArrayList<List<String>>();
		listwords.add(words);	
	}
	//overridden simply so the new words that is created is an array and not a set
	@Override
	protected final void needNewSet(String theWord){
		if (theWord.length() > listwords.size() && listwords.size()<11 ){
			for (int index =0; index < theWord.length()-listwords.size(); index++){
				ValidateUtilities.checkForLegalAddPosition(index, listwords);
				List<String> words = new ArrayList<String>();
				listwords.add(words);
			}
		}
	}
}
