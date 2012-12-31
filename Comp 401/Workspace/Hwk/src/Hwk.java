import static java.lang.System.out;

// p. 786  Write methods for problems 3, 4, 6, 7, and 9

public class Hwk {

	public static int[] x = new int[] { 10, 30, 70, 44, 3 };

	public static void main(String[] args) {

		out.println("number of odd digits in 987 = " + problem3(987));
		out.println("sum of digits in 987 = " + problem4(987));
		out.println("sum of values in x is " + problem6(x, 0));
		out.println("largest value in x is " + problem7(x, 0));
//		out.println("cummulative sums of of x are ");
//		problem9(x,x.length-1);
//		for (int index =0; index< x.length;index++)
//			out.println(x[index]);
		
	}

	public static int problem3(int number) {
		int numodds;
		boolean even = ((number%10)%2==0);
		if(number>0){
			if (even){
				numodds = problem3(number/10);
			}
			else{ 
				numodds = problem3(number/10)+1;
			
			}
		}
		else{ numodds=0;}
		return numodds;
		
	}
	public static int problem4(int number){
		int sum;
		if (number>0){
			sum = (number%10) + problem4(number/10);
		}
		else {sum =0;}
		return sum;
	}
	
	public static int problem6(int[] x, int index){
		int sum;
		if (index<x.length){
			sum= x[index] + problem6(x, ++index);
		}
		else {sum=0;}
		return sum;
	}
	
	public static int problem7(int[] x, int index){
		int largest;
		if (index<x.length){
			largest = problem7(x,index+1);
			if (x[index]>largest)
				largest=x[index];
		}
		else{
			largest=0;
		}
		return largest;
		
	}
	public static int problem9(int[]x, int xlength){
		int sum;
		if (xlength>=0){	//xlength = x.length-1  ==> it works backwards
			sum = x[xlength]+ problem9(x,xlength-1);
			x[xlength] = sum;
			;
			}
		else sum=0;
		return sum;
	}
}
