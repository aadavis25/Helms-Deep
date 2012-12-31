/*
*
* Program 3 
* Aaron Davis 
*  
* Comp 401 Hedlund
*  
* I have neither given nor received unauthorized aid 
* on this program. Aaron Davis 
*  
*/
	// Program 3 - copy this class and complete the assignment.
	public class Program3 {

		private static int[] dht;

		public static void main(String[] args) {
			System.out.print("Test for method daysAbove89: ");  testDaysAbove89();
			System.out.print("Test for method lastDayAbove89: "); testLastDayAbove89();
			System.out.print("Test for method longestHotSpell: "); testLongestHotSpell();
			
			
		}

		// Pre: temp.length > 0
		// Post: return number of days for which the temperature
		//   was above 89
		public static int daysAbove89(int[] temp) {
			int numDaysAbove89=0;
			for (int dayIndex=0; dayIndex<temp.length; dayIndex++){
				if (temp[dayIndex]>89){
					numDaysAbove89++;
				}
	
			}
			return numDaysAbove89;
		}

		//	Input			Expected Return
		//	[90, 78, 91, 42, 99]		3
		//	[78, 42, 31, -5]		0
		//  complete this test plan
		public static void testDaysAbove89() {
			int [] test1_1 = {34,56,123,89,90,98};
			int [] test1_2 = {100,101,102,116,56,97};
			int [] test1_3 = {89,89,89,89,89};
			int [] test1_4 = {0};
			int [] test1_5 = {23,23,23,23,23};
			int [] test1_6 = {23,98,12,100,43,90};
			int [] test1_7 = {};
			System.out.print(daysAbove89(test1_1));
			System.out.print(daysAbove89(test1_2));
			System.out.print(daysAbove89(test1_3));
			System.out.print(daysAbove89(test1_4));
			System.out.print(daysAbove89(test1_5));
			System.out.print(daysAbove89(test1_6));
			System.out.print(daysAbove89(test1_7));
		}

		// Pre: temp.length > 0
		// Post: return the index of the last day in the array
		//    for which the temperature was above 89 decrees.
		public static int lastDayAbove89(int[] temp) {
			int lastDay=0;
			for (int dayIndex=0; dayIndex<temp.length; dayIndex++){
				if (temp[dayIndex]>89){
				lastDay=dayIndex+1;
				}
	
			}
			return lastDay;
		}

		public static void testLastDayAbove89() {
			int [] test2_1 = {34,56,123,89,90,98};
			int [] test2_2 = {100,101,102,116,56,45};
			int [] test2_3 = {89,89,89,89,89};
			int [] test2_4 = {0};
			int [] test2_5 = {90,23,23,23,23};
			int [] test2_6 = {23,98,12,100,43,90};
			int [] test2_7 = {};
			System.out.print(lastDayAbove89(test2_1));
			System.out.print(lastDayAbove89(test2_2));
			System.out.print(lastDayAbove89(test2_3));
			System.out.print(lastDayAbove89(test2_4));
			System.out.print(lastDayAbove89(test2_5));
			System.out.print(lastDayAbove89(test2_6));
			System.out.print(lastDayAbove89(test2_7));
		}

		// A hot spell is defined to be consecutive days
		// all above 89 degrees. For example,
		// in [89, 90, 92, 99, 88, 100, 90] the longest hot
		// spell is [90, 92, 99]
		// Pre: temp.length > 0
		// Post: return length of longest hot spell
		public static int longestHotSpell(int[] temp) {
			int longestSpell=0;
			int tempLongestSpell=0;
			for (int dayIndex=0;dayIndex<temp.length;dayIndex++){
					if (temp[dayIndex]>89 && dayIndex<temp.length-1){
						tempLongestSpell++;
					}
					else if (tempLongestSpell>longestSpell && dayIndex<temp.length-1){
						longestSpell = tempLongestSpell;
						tempLongestSpell=0;
					}
					else if	(dayIndex==temp.length-1 && temp[dayIndex]>89 && tempLongestSpell+1>longestSpell){
						longestSpell=tempLongestSpell+1;
					}
			}
			
			return longestSpell;
		}

		public static void testLongestHotSpell() {
			int [] test3_1 = {123,89,90,98,34,90,91,90};
			int [] test3_2 = {100,101,102,116,56,45,90};
			int [] test3_3 = {100,820,89,89,89,100,345,685};
			int [] test3_4 = {0};
			int [] test3_5 = {90,899,23,23,400,401,402,403,405,406};
			int [] test3_6 = {23,98,12,100,200,43,90};
			int [] test3_7 = {};
			System.out.print(longestHotSpell(test3_1));
			System.out.print(longestHotSpell(test3_2));
			System.out.print(longestHotSpell(test3_3));
			System.out.print(longestHotSpell(test3_4));
			System.out.print(longestHotSpell(test3_5));
			System.out.print(longestHotSpell(test3_6));
			System.out.print(longestHotSpell(test3_7));
		}	

		// Reimplement this method from the previous
		// assignment. However, your goal is NOT to write
		// code that is algorithmically efficient (efficient
		// use of computer resourse). Your goal is
		// "quick and dirty."  Give no thought to efficiency.
		// Write code that requires least programming effort
		// by making use of Java's built in capabilities.
		// Hint: Arrays
		// You do not have to test this method.
		public static int countDuplicates(int[] temp) {
			int numDuplicates=0;
			int[] temp2 = temp;
			for (int index=0; index<temp.length;index++){
				if (temp[index]==temp2[index]){
						numDuplicates++;
					}
				
			}
			return numDuplicates;	
		}
	}
