import java.util.Scanner;

public class CounterDemo {
	
	
	public static void main(String[] args){
		Counter[] grades = new Counter [4];
		Scanner keyboard = new Scanner (System.in);
		int score = keyboard.nextInt();
	
		for(int index=0;index<grades.length;index++){
			while (score>0)
				if (score<100 && score>90)
					grades[0].increaseByOne();
				else if (score<89 && score>80)
					grades[2].increaseByOne();
				else if (score<79 && score>70)
					grades[3].increaseByOne();
				else if (score<70)
					grades[4].increaseByOne();
			
		}
	}
}