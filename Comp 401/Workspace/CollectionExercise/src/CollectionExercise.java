import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static java.lang.System.out;

public class CollectionExercise {

	public static List<String> origList = new ArrayList<String>();
	public static List<String> newList = new ArrayList<String>();
	
	public static void main(String[] args){
		Question0();
		out.print("1 = " );Question1index();
		out.print("1 = " );Question1iterator();
		out.print("1 = " );Question1foreach();
		out.print("2 = " );Question2();
		out.print("3 = " ); Question3();
		out.print("4 = " );Question4();
		out.print("5 = " );Question5();
		out.print("6 = " );Question6();
		out.print("7 = " );Question7();
		out.print("8 = " );Question8();
		out.print("9 = " );Question9();
		out.print("10 = " );Question10();
		out.print("11 = " );Question11();
		out.print("12 = " );Question12();
		out.print("13 = " );Question13();
		
	}
	
	public static void Question0(){
	origList.add("Ralph");
	origList.add("Aaron");
	origList.add("Katie");
	origList.add("Paul");
	origList.add("Paul");
	origList.add("Steve");
	origList.add("Paul");
	
	/* List<String> origList = new List<String>() - Illegal (initiation of an interface)
	*  List<String> origList = new ArrayList<String>() - Legal
	*  List<String> origList = new LinkedList<String>() - Legal
	*  ArrayList<String> origList = new ArrayList<String>() - Legal, not a good idea
	*  ArrayList<String> origList = new LinkedList<String>() - Legal, not a good idea
	*/
	}
	 public static void Question1index(){
		 for( int index=0; index < origList.size(); index++ ){
			 out.println(origList.get(index));
		 }
	 }
	 public static void Question1iterator(){
		 Iterator<String> itr = origList.iterator();
		 while (itr.hasNext())
			 out.println(itr.next());
	 }
	 public static void Question1foreach(){
		 for(String s : origList)
			 out.println(s);
	 }
	 
	 public static void Question2(){
		 origList.set(0, "Kye");
		 out.println(origList);
	 }
	 public static void Question3(){
	 List<String> cpy = origList;
	 cpy.clear();
	 out.println(cpy);
	 origList.add("Ralph");
		origList.add("Aaron");
		origList.add("Katie");
		origList.add("Paul");
		origList.add("Steve");
		origList.add("Paul");
		origList.add("Paul");
	 /*
	  * origList is cleared also by clearing cpy.
	  */
	 }
	 public static void Question4(){
		 for ( int index=0; index<origList.size();index++ )
			 newList.add(origList.get(index));
		 out.println(newList);
		 newList.clear();//So it can be used again
	 } /*newList and origList may not be equal in size due to the unknown factor
	    *factor of expansion, but will be equal as far as "value" goes.
	    */
	 public static void Question5(){
		 Iterator<String> itr =  origList.iterator();
		 while (itr.hasNext())
			 newList.add(itr.next());
		 out.println(newList);
		 newList.clear();//So it can be used again
	 }
	 public static void Question6(){
		 int timesrun=0;
		 for (String s: origList)
			 newList.add(s);
		 out.println(newList);
		 newList.clear();//So it can be used again
	 }
	 public static void Question7(){
		 if (origList.contains("Paul"))
			 origList.remove("Paul");
		 out.println(origList);
	 	}
	 public static void Question8(){
		 if (origList.contains("Paul")){
			 List<String> remove = new ArrayList<String>();
			 remove.add("Paul");
			 origList.removeAll(remove);
		 }
		 out.println(origList);
	 }
	 public static void Question9(){
		 List<String> origList2 = origList; //Copied so I could reuse origList
		 for (int index=0;index < origList2.size()-(index+1);index++){
			 String temp1 = origList2.get(index);
			 String temp2 = origList2.get(origList2.size()-(index+1));
			 origList2.set(index, temp2);
			 origList2.set(origList2.size()-(index+1), temp1);
		 }
		 out.println(origList2);
	 }
	 public static void Question10(){
		 List<String> origList2 = origList; //Copied so I could reuse origList
		 List<String> temp = new ArrayList<String>();
		 int run=0;
		 Iterator<String> itr = origList2.iterator();
		 while (itr.hasNext()){
			 temp.add(itr.next());
		 }
		 origList2 = temp;
		 out.println(origList2);
	 }
	 public static Map<String, Integer> grades = new HashMap<String, Integer>();
	//Must use Integer instead of int because int can't be stored as a key 
	 public static void Question11(){
			 grades.put("Ralph", 56);
			 grades.put("Katie", 89);
			 grades.put("Stephen", 12);
			 grades.put("Aaron", 93);
			 grades.put("Kye", 101);
		 out.println("Ralph got a " + grades.get("Ralph"));
		 out.println("Stephen got a " + grades.get("Stephen"));
		 out.println("Kye got a " + grades.get("Kye"));
	 }
	 public static void Question12(){
		 out.println(grades.keySet());	 
		 Set<String> gradesAlpha = new TreeSet<String>();
		 Iterator<String> itr = grades.keySet().iterator();
		 while (itr.hasNext())
			 gradesAlpha.add(itr.next());
		 
		 out.println(gradesAlpha);
	 }
	 
	public static void Question13(){
		int total =0;
		int numgrades =0;
		Iterator<String> itr = grades.keySet().iterator();
		 while (itr.hasNext()){
			total += grades.get(itr.next()); 
			numgrades++;
		 }
		int average = total/numgrades;
		out.println("Ralph was off the average by " + (grades.get("Ralph") - average));
		out.println("Katie was off the average by " + (grades.get("Katie") - average));
		out.println("Stephen was off the average by " + (grades.get("Stephen") - average));
		out.println("Aaron was off the average by " + (grades.get("Aaron") - average));
		out.println("Kye was off the average by " + (grades.get("Kye") - average));
	
}
}