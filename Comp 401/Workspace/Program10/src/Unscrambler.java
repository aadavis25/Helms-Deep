import java.io.FileNotFoundException;
import static java.lang.System.out;

public class Unscrambler {

	public static void main(String[] args) throws FileNotFoundException{
		
		WordListInterface wordList = new WordListIndexed(10);
		wordList.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program10\\src\\english0.txt");
		wordList.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program10\\src\\english1.txt");
		wordList.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program10\\src\\english2.txt");
		wordList.readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 401\\Workspace\\Program10\\src\\english3.txt");
		
		StringPermutationIterator mezia = new StringPermutationIterator("mezia");
		StringPermutationIterator cidde = new StringPermutationIterator("cidde");
		StringPermutationIterator hucnah = new StringPermutationIterator("hucnah");
		StringPermutationIterator bouste = new StringPermutationIterator("bouste");
		StringPermutationIterator ueassrkpc = new StringPermutationIterator("ueassrkpc");
		StringPermutationIterator empty = new StringPermutationIterator("");
		StringPermutationIterator notaword = new StringPermutationIterator("notaword");
		StringPermutationIterator dsaniwch = new StringPermutationIterator("dsaniwch");
		StringPermutationIterator ictyvor = new StringPermutationIterator("ICTYVOR");
		
		while (mezia.hasNext()){
			String s = mezia.next();
			if (wordList.contains(s)){
				out.println(s);
			}	
		}
	}}