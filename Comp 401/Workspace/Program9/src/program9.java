import static java.lang.System.out;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class program9 {
	
	
	public static void main(String[] args){
		
		List<Files> list= listFiles("E:\\Musac");
		CompareName compname = new CompareName();
		sort(compname,list);
		out.println(list.size());
		out.println(list);
		
		CompareSize compsize = new CompareSize();
		sort(compsize,list);
		out.println(list);
		
		setType(".txt");
		List<Files> list2 = listFiles("C:\\Users\\aaronmd\\Desktop\\SampleDir");
		CompareSize compsize2 = new CompareSize();
		sort(compsize2,list2);
		out.println(list2);
		
		List<Files> list3 = listFiles("C:\\Users\\aaronmd\\Desktop\\empty directory");
		out.println(list3);
		
		List<Files> list4 = listFiles("My Cat");
	}
	
	private static String FILE_TYPE = ".mp3";
	
	/**
	 * @observer
	 * @requires theDirectory is an absolute path to a dirListEle
	 * @returns  list of all files whose names contain FILE_TYPE (not dirListEleectories) rooted at
	 *    theDirectory
	 */
	final private static List<Files> listFiles( String theDirectory) {
		ValidateUtilities.checkForContent(theDirectory); //make sure real directory is passed\
		ValidateUtilities.checkForNull(theDirectory);
		
		List<Files> fileList = new ArrayList<Files>();
		File newDirectory = new File(theDirectory);
		File[] dirContents = newDirectory.listFiles();  //directory to file array
		
		for (File dirElement: dirContents){
			
			if (dirElement.getAbsoluteFile().isDirectory())
				fileList.addAll(listFiles(dirElement.toString())); //If directory, start recursion on it
			
			else if (dirElement.getAbsoluteFile().isFile() && dirElement.getName().contains(FILE_TYPE)){    //iff file, add to fileList 
					Files newFile = new Files(dirElement.toString()); //create new array to be added to FileList
					fileList.add(newFile);
			}
			
		}
		return fileList;
	}
	
	/**
	 * @mutator
	 * @requires fileList.size() > 0
	 * @ensures x[i] <= x[i+1] (based on what implementation of CompareFiles comp is)
	 * 
	 */
	private final static void sort(CompareFiles comp, List<Files> fileList) {
		ValidateUtilities.checkForEmpty(fileList);
		for (int outer = 0; outer < fileList.size(); outer++) {
			int smallest = outer;
			for (int inner = outer+1; inner < fileList.size(); inner++) {

				if (comp.compare(fileList.get(inner), fileList.get(smallest))<0) {
					smallest = inner;
				}	
			}
			Files temp = fileList.get(outer);
			fileList.set(outer, fileList.get(smallest));
			fileList.set(smallest, temp);
		}

	}

	private final static void setType( String newType ){
		FILE_TYPE = newType;
	}

}