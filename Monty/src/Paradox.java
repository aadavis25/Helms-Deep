import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class Paradox {
	static int  doorsMinusOne = 3;
	static long ST;
	static long ET;
	
	public static void main (String[] args) throws IOException{
		ST = System.nanoTime();
		String runs;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("How many trials?");  
	    runs = in.readLine();
	    double runsInt = Double.parseDouble(runs);
	   // System.out.println(runsInt);	
		double stickCount = 0;
		double switchCount = 0;
	    
		for (int i = 0 ; i < runsInt;i++ ){
			if (switchh())
				switchCount++;
			if (stick())
				stickCount++;
		}
		ET = System.nanoTime();
		double perc1 = (switchCount/runsInt)*100;
		double perc2 = (stickCount/runsInt)*100;
		System.out.println("Switch won " + perc1 + "%.");	
		System.out.println("Stick won " + perc2 + "%.");
		System.out.println(ET-ST);
		
		
		
	}
	
	
	public static int getRandom(int end){
		Random random = new Random();
		int rand = random.nextInt(end);
		return rand;
	}
	
	public static boolean stick(){
		Doors doors = new Doors();
		int choice = getRandom(doorsMinusOne);
		//System.out.println("Guess was " + choice + ", door was "+ doors.prize);
		return doors.guess(choice);
	}
	
	public static boolean switchh(){
		Doors doors = new Doors();
		int choice = getRandom(doorsMinusOne);
		int removed = removeDoor(choice, doors.prize);
		int newGuess = switchGuess(choice, removed);
		//System.out.println("Guess was " + newGuess + ", door was "+ doors.prize);
		return doors.guess(newGuess);
	}
	
	public static int removeDoor(int guess, int prize){
		int neww = getRandom(doorsMinusOne);
		while (neww == guess | neww == prize){
			neww = getRandom(doorsMinusOne);
		}
		return neww;
	}
	
	public static int switchGuess(int current, int removed){
		int neww = getRandom(doorsMinusOne);
		while (neww == current | neww == removed){
			neww = getRandom(doorsMinusOne);
		}
		return neww;
	}
	
	public static class Doors{
		
		int  prize;
		
		public Doors(){
			int prizeDoor = getRandom(doorsMinusOne);
			prize = prizeDoor;
		}
		
		public  boolean guess(int guess){
			if (guess == prize)
				return true;
			return false;
		}
	}

}
