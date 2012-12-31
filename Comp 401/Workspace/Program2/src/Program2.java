public class Program2 {// Write code for the following methods.  You do NOT
	// i) Have to follow Ready-Aim-Fire-Finish
	// ii) Thoroughly test your code (although your code should
//	      be correct)
	//
	// But DO
	// i) Write code in good style.  See the "Java Programming Style"
//	    link on the web page.
	// ii) Use an efficient algorithm.  Do not do the following unless
//	     absolutely necessary
//	     a) make a copy of an array (or String)
//	     b) create a second array (or String) as in countDuplicates1
//	        method in the solution to Program1

	

		public static void main(String[] args) {
			
			String s = "1233abcd22";
			System.out.println( "digitCount(" + s + ") = " +
					digitCount(s));
			
			String s2 = "radar";
			String s3 = "racecar";
			String s4 = "development";
			System.out.println(isPalindrome(s2));
			System.out.println(isPalindrome(s3));
			System.out.println(isPalindrome(s4));
				
			
		}
		
		
		// Return the number of digits in str.  For example
		// str = "1233abcd22" has 6 digits.
		//
		// Useful Java language features:
		// i) str.charAt(index) returns the character at position
		//    'index' within str.  As with arrays, indexing starts
		//    at 0.  For example str.charAt(4) = 'a'
		//    Note that Strings as specified with double quoutes
		//    ("1233abcd22") and single characters (data type 'char')
		//    with single quotes ('a').
		// ii) For any character, you can tell if it is a digit by using
		//    char ch = '1';
		//    Character.isDigit(ch)
		//    will return a boolean result 'true'
		public static int digitCount(String str ) {
			int count=0;
			for (int index=0;index<str.length();index++){
				if (Character.isDigit(str.charAt(index))){
					count+=1;
				}
			}
			return count;
		}
		
		// Pre: str contains only lower case letters (no
		//   punctuation, spaces, etc.
		// Post: return T iff str is a palindrome
		//   (the same when read forwards as backwards)
		//   ('iff' = 'if and only if')
		//
		// This is the first time that we are writing the Ready step
		// as a 'contract'.  Pre (= precondition) is what the method
		// may assume about the data.  It does not have to check that
		// this is actually true (this is the responsibility of the 
		// caller).  Post (= postcondition) is what the methods
		// guarantees will be true when the method returns.
		// More, much more, about contracts in the coming weeks.
		public static boolean isPalindrome(String str) {
			int count=0;
			char ident='C';
			boolean yesPalindrome=false;
			if (str.length()%2==1){
				ident='A';
				for(int index=0;index<((str.length()-1)/2);index++){
					if (str.charAt(index)==(str.charAt((str.length()-1)-index))){
						count+=1;
					}}}
			else if (str.length()%2==0){
				ident='B';
				for(int index=0;index<((str.length())/2);index++){
					if (str.charAt(index)==(str.charAt((str.length()-1)-index))){
						count+=1;
					}}}
				
			if (ident=='A'){
				if (count==((str.length()-1)/2)){
					yesPalindrome=true;
				}}
			else if (ident=='B'){
				if (count==((str.length()/2))){
					yesPalindrome=true;
				}}
			
			return yesPalindrome;
			}}
			
