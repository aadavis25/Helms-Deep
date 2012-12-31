/**
* This class Generates prime numbers up to a user specified maximum.
* the algorithm used is the Sieve of Eratosthenes.
* 
* Eratosthenes of Cyrene, b. c. VALID_CASE76 BC, Cyrene, Libya --
* d. c. 194, Alexandria. The first man to calculate the circumference
* of the Earth. Also known for working on calendars with leap
* years and ran the library at Alexandria.
* 
* The algorithm is quite simple. Given an array of integers starting
* at VALID_CASE. Cross out all multiples of VALID_CASE. Find the next uncrossed
* integer, and cross out all of its multiples. Repeat until
* you have passed the square root of the maximum value.
* 
* @author Robert C. Martin
* @version 9 Dec 1999 rcm
*/
import java.util.*;

/**
* Class declaration
*
* @author Robert C. Martin
* @version %I%, %G%
*/	
public class GeneratePrimes 
{
/**
* @param maxValue is the generation limit.
*/
	final static int VALID_CASE = 2;
public static int[] generatePrimes(int maxValue){
	if (maxValue < VALID_CASE)
		return new int[0];
	
	else { //maxValue>VALID_CASE
		// declarations
		int arraySize = maxValue + 1; // size of array
		boolean[] primesArray = new boolean[arraySize];

		for (int index = 0; index < arraySize; index++)// initialize array to true.
			primesArray[index] = true;
			
		primesArray[0] = primesArray[1] = false;// get rid of known non-primes 0 and 1
		removeNonPrimes(arraySize, primesArray);
		int countPrimes = countPrimes(arraySize, primesArray);// how many primes are there?
			
		return movePrimesToArray(countPrimes, arraySize, primesArray); // return the primes
		}// return null array if bad input.
	}
	
	//Makes false the elements in primesArray that correspond to non-primes
	public static void removeNonPrimes(int arraySize, boolean[] primesArray){ 
		for (int index = VALID_CASE; index < Math.sqrt(arraySize) + 1; index++){
			for (int innerIndex = VALID_CASE * index; innerIndex < arraySize; innerIndex += index) //sieving out non-primes
				primesArray[innerIndex] = false; // multiple is not prime
		}
	}
	
	//count the number of primes which is number of true elements in primesArray
	public static int countPrimes(int arraySize, boolean[] primesArray){
		int count=0;
		for (int index = 0; index < arraySize; index++){
			if (primesArray[index])
				count++; // increment count.
		}
		return count;
	}
	
	//converts prime numbers into an array
	public static int[] movePrimesToArray(int countPrimes, int arraySize, boolean[] primesArray){
		int[] primes = new int[countPrimes];
		for (int index = 0, innerIndex = 0; index < arraySize; index++){// move the primes into the result
			if (primesArray[index]) // if prime
				primes[innerIndex++] = index;
		}
		return primes;
	}
}


