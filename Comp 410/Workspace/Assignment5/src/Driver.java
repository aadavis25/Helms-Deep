import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Aaron M Davis
 * last edited: 11/28/11
 * 
 * @pledge This work is mine; I toiled and slaved over it and I'll be darned if I let anyone else use it. Also, I didn't copy anyone else's out
 * of similar respect of their toiling and slaving.
 * @signature Aaron M Davis
 */
public class Driver {
	static TheGraph graph = new TheGraph();
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<DijAlg.DijObj> paths = new ArrayList<DijAlg.DijObj>();
	static String start;
	//main class
	public static void main (String[] args) throws FileNotFoundException{
		//read in the file and add all the strings to list
		readFile("C:\\Users\\aaronmd\\Documents\\My Dropbox\\Comp 410\\Workspace\\Assignment5\\src\\file.txt");
		//add all the node names to the graph, using the first and second of the 3-tuple
		for(int index=0;index<list.size()-1;index+=3){
			graph.add(list.get(index)); graph.add(list.get(index+1));
		}
		//take every 3-tuple and feed it to the addAdjac method to add the edges
		for(int index2=0;index2<list.size()-1;index2+=3)
			graph.addAdjac(list.get(index2),list.get(index2+1),Integer.parseInt(list.get(index2+2)));
		//perform Dijkstras Algorithm and add trace the result paths and add them to theList and print them
		DijAlg dijk = new DijAlg();
		ArrayList<String> theList = dijk.tracePaths(graph, start);
		/* Paths should be
		  Chapel_Hill
		Chapel_Hill->Durham->Raleigh
		Chapel_Hill->Durham->Wake_Forest
		Chapel_Hill->Durham
		Chapel_Hill->Durham->Greensboro->Asheboro
		Chapel_Hill->Durham->Greensboro
		Chapel_Hill->Moscow
		Chapel_Hill->Durham->London->Germany
		Chapel_Hill->Durham->London
		 */
			
	}
	
	public static void readFile( final String fileName ) throws FileNotFoundException {
		Scanner inp = new Scanner(new File( fileName ));
		while( inp.hasNext() ) {
			String line = inp.nextLine();
			if (line.trim().compareTo("STOPHERE") == 0){
				start = (inp.nextLine().trim());
				break;
			}
			String[] word = line.split( " " );
			for( int i = 0; i < 3; i++ )
				list.add(word[i].trim());
			}
		}
	
 	}
	

