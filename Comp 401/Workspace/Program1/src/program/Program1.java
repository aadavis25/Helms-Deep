package program;

public class Program1 {


//Program 1 - copy this class and complete the assignment.
//Turn in a printout of the class Program1 and 
//the output. Put your name on the first page.
//Due - Tue 1/18 at the start of class. If you are new to Java
//or have problems installing the tools, late assignments
//will be accepted.

/*
* For each method, write the code clearly and concisely.
* The array, dht, contains the highest temperature reached each day for days 0, 1,  ... 
* dht.length = number of elements in array dht.
*/

	private static int[] dht;
	
	public static void main(String[] args) {

		initialize();
		System.out.println("highestTemp = " + highestTemp(dht));
		System.out.println("hottestDay = " + hottestDay(dht));
		System.out.println("countDupliucates = " + countDuplicates(dht));
		removeDuplicates(dht);
		for (int index=0;index<dht.length;index++)
			System.out.println(dht[index]);
		//System.out.println("removeDuplicates = " + );

		// Add calls to remaining methods

	}
	
	public static void initialize() {
		dht = new int[] {41,68,77,74,74,68,88};
		// dht.length = 7
		// Extra challenge - do the work of the initiazlize all
		// in one Java statement.
	}
	
	/*
	 * Return the highest temperature reached during the recorded period.
	 * Don't print it out. Return it.  For dht above, return 88.
	 * You may assume for this method and all others that 
	 * temp.length >= 1
	 */
	public static int highestTemp( int[] temp ) {
		int max=0;
		for (int  index=0; index<temp.length; index++){
			if  (temp[index] > max){
				max=temp[index];
			}
		}
	return max;
	}
	
	/*
	 * Return the index of hottest day. For dht above, return 6.
	 */
	public static int hottestDay( int[] temp ) {
		int max=temp[0];
		int day=0;
		for (int  index=0; index<temp.length; index++){
			if  (temp[index] > max){
				max=temp[index];
				day=index;
			}
		}
	return day;
	}
	
	
	/*
	 * Count duplicate temperatures.
	 * For dht above, return 2. There is one duplicate each for 68 and 74.
	 */
	public static int countDuplicates( int[] temp) {
		int dups=0;
		
		for (int index=0; index<temp.length; index++){
			for (int index1=index+1; index1<temp.length; index1++){
			if ((temp[index]==temp[index1]) && (index!=index1)){
				
				dups = dups+1;
			}
		}
		}
	return dups;
	}
	
	/*
	 * Delete all duplicated temperatures. Compress the array to avoid
	 * gaps in the middle and pad out the array with the value -999 at the end.
	 * For dht above, change it to [41,68,77,74,88,-999,-999].
	 */
	public static void removeDuplicates( int[] temp ) {
		int dups=0;
		int[] dupsarray = new int[] {0,0,0};
		for (int index=0; index<temp.length; index++){
			for (int indexx=(index+1); indexx<temp.length; indexx++){
			if ((temp[index]==temp[indexx]) && (index!=indexx)){
				dups += 1; 
				dupsarray[dups]=indexx;
			}
	}
}
		for (int index=1;index<dupsarray.length;index++){
			temp[dupsarray[index]]=-999;
		}
		for(int index=0; index<(temp.length-1); index++){
			if ((temp[index]==-999)&&(temp[index+1]!=-999)){
			temp[index]= temp[index+1];
			temp[index+1]=-999;}
			else if ((temp[index]==-999)&&(temp[index+1]==-999)&&(index<5)){
				temp[index]=temp[index+2];
				temp[index+2]=-999;
		}
	}
	}
		
}