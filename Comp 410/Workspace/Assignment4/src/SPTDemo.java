/**
 * @author AaronDavis
 * "On my honor, I have neither given nor received unauthorized aid on this assignment"
 * last modified: 10/18/11
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class SPTDemo {
	
	static SPTNode<String> tree = new SPTNode<String>(null);
	public static void main (String[] args) throws IOException{
		
		 SPTreeDemo(tree);
		    
		}
	
	private static void SPTreeDemo(SPTNode<String> tree) throws IOException{
	
	BufferedReader inn = new BufferedReader(new InputStreamReader(System.in));
	String line, elt;
	  
	  int size; //size of tree as entered by user
	  
	  //Maximum and minimum String lengths for nodes
	  int MAX = 13;
	  int MIN = 5;

	  //Take in a size from the user, must be a positive number
	  System.out.print("What size tree would you like today? ");	
	  line = inn.readLine();
	  size = Integer.parseInt(line);
	  if(size <0){
		  System.out.println("Please type a positive number.");
		  SPTreeDemo(tree);
	  }
	  //generate the tree
	  for (int i=0; i<size; i++) {
	      elt = MyRandom.nextString(MIN, MAX);
	    //  System.out.println(elt); used for testing
	      if (tree.getElement() == null){
	    	  tree.setElement(elt);
	    	  tree.setRoot(tree);
	      }
	      else tree.getRoot().insert(elt);
	  }
	  
	  //Take input for operations and perform them
	  while(true){
	  System.out.print("What would you like to do to the tree? ");
	 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	  line = in.readLine();
	  if (line.equalsIgnoreCase("insert")){
		  System.out.print("What value should I insert? ");
		  String val = in.readLine();
		  System.out.println(tree.getRoot().insert(val));
		  System.out.println("There have been " +tree.getDups() + " duplicates.");
	  }
	  else if (line.equalsIgnoreCase("find")){
		  System.out.print("What value should I find? (ye be warned, this operation will splay the tree) ");
		  in = new BufferedReader(new InputStreamReader(System.in));
		  String val = in.readLine();
		  System.out.println(tree.getRoot().find(val));
	  } 
	  else if (line.equalsIgnoreCase("remove")){
		  System.out.print("What value should I remove? ");
		  in = new BufferedReader(new InputStreamReader(System.in));
		  String val = in.readLine();
		  System.out.println(tree.getRoot().remove(val));
	  } 
	  else if (line.equalsIgnoreCase("size")){
		  System.out.println(tree.getRoot().getSize());
		  System.out.println("There have been " +tree.getDups() + " duplicates.");
	  }
	  else if (line.equalsIgnoreCase("sort")){
		  sort(tree.getRoot());
	  }
	  else if (line.equalsIgnoreCase("print")){
		  tree.getRoot().print();
	  }
	  
	  else if ( line.equalsIgnoreCase("quit")){  
		  break; 
	  }
	 }
	}
	
	//sorting method in alphabetical order
	private static void sort(SPTNode<String> t){
		if (t != null){
			sort(t.getLeft());
			System.out.println(t.getElement());
			sort(t.getRight());
		}
	}
}
	

