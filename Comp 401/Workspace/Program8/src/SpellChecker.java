import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class SpellChecker {

	public static void main(String[] args) throws FileNotFoundException{
		readInWords();
		spellCheck("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\TestInput.txt");
		spellCheck2("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\TestInput.txt");
	}
	
	
	public static WordList wordlist = new WordList();
	public static WordList wordlistindexed = new WordListIndexed();
	
	
	
	public static void readInWords() throws FileNotFoundException{
		wordlist.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english0.txt");
		wordlist.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english1.txt");
		wordlist.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english2.txt");
		wordlist.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english3.txt");
		wordlistindexed.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english0.txt");
		wordlistindexed.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english1.txt");
		wordlistindexed.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english2.txt");
		wordlistindexed.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program8\\src\\english3.txt");
	}
	public static void spellCheck( String theString )throws FileNotFoundException{
		ArrayList<String> wordsToCheck = stringToWords(theString);
		String word = "";
		for (int index=0;index<wordsToCheck.size(); index++){	
			word = wordsToCheck.get(index);
			if (!wordlist.contains(word)){
				out.println("There is a spelling error at word " +(index+1));
				out.println("The word that is misspelled is " +word);
			}
		}
		}
	public static void spellCheck2( String theString )throws FileNotFoundException{
		ArrayList<String> wordsToCheck = stringToWords(theString);
		String word = "";
		for (int index=0;index<wordsToCheck.size(); index++){	
			word = wordsToCheck.get(index);
			if (!wordlistindexed.contains(word)){
				out.println("There is a spelling error at word " +(index+1));
				out.println("The word that is misspelled is " +word);
			}
		}
		}
		
	public static ArrayList<String> stringToWords(String theString) throws FileNotFoundException{
		
		
		
		
		Scanner inp = new Scanner(new File(theString));
		ArrayList<String> wordsToCheck = new ArrayList<String>();
		while (inp.hasNext()) {
		String line = inp.nextLine();
		String [] word = line.split(" ");
		for (int i = 0; i < word.length; i++)
			wordsToCheck.add(word[i].trim());
		}
		return wordsToCheck;
	}
}
