import java.util.Random;
import static java.lang.System.out;


/*Aaron Davis
*9/11/11 Removed for loop after BubbleSort,
*Made second set of loops add theArray[arrayIndex] while moving elts down in sort
*/
public class Sorter {
	
	public static void main (String[] args){
		long ST,ET; int[] A = (randGen(100)); int[] B = (randGen(1000)); int[] C = (randGen(10000)); int[] D = (randGen(20000)); int[] E = (randGen(30000)); int[] F = (randGen(40000)); int[] G = (randGen(50000));int[] H = (randGen(100000));
		
		ST=System.nanoTime(); out.println( sort(A)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
		
		ST=System.nanoTime(); out.println( sort(B)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
		
		ST=System.nanoTime(); out.println( sort(C)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
		
		ST=System.nanoTime(); out.println( sort(D)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
		
		ST=System.nanoTime(); out.println( sort(E)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
		
		ST=System.nanoTime(); out.println( sort(F)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
		
		ST=System.nanoTime(); out.println( sort(G)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
		
		ST=System.nanoTime(); out.println( sort(H)); ET=System.nanoTime(); 
		out.println("Found in " +(ET-ST)+ " units.");
	}
	
	public static int[] randGen(int size){
		int[] vals = new int[size];
	     Random gen = new Random(); 
	     for (int i=0; i<size; i++) {
	        vals[i] = gen.nextInt(100);//range of 100
	     }
	     return vals;
	}

	public static int sort(int[] theArray){
		
		int k =(theArray.length)/2;
		int[] sort = new int[k];
		//add elements
		for (int index=0;index<k;index++)
			sort[index]=theArray[index];
		//BubbleSort sort
		for (int outer=1; outer < k; outer++) {
			for (int inner=0; inner < k-outer; inner++) {
				if (sort[inner] < sort[inner+1]) {
					int temp = sort[inner+1]; 
					sort[inner+1] = sort[inner];  
					sort[inner] = temp;
				}
			}
		}
		//for (int index=0;index<k;index++) //Used for testing
			//out.println(sort[index]);
		for (int theArrayIndex=k+1; theArrayIndex < theArray.length; theArrayIndex++) {
			if (theArray[theArrayIndex]>sort[k-1]){ //does this element matter?
				for (int sortIndex=k-1;sortIndex>0;sortIndex--){
					if (theArray[theArrayIndex]>sort[sortIndex] && theArray[theArrayIndex] < sort[sortIndex-1]){//Find out where theArray[theArrayIndex] belongs in sort,
						sort[sortIndex] = theArray[theArrayIndex]; //and replace the element in theArray[theArrayIndex]'s new spot	
						break; //breaks out of second for-loop, no need to look further
					}
					else sort[sortIndex] = sort[sortIndex-1];  //move everything down one place starting from the end,
					
				}
			}
		}
		return sort[k-1];	//element at sort[k] should be the kth element in theArray 
	}
}
		