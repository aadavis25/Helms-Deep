import java.io.*;
import java.util.*;

class KeyboardInputDemo {

public static void main(String[] args) throws IOException {
   
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  String line, wrd;
  int low, high;
  int MAX=15;

  //prompt the user
  System.out.print("shortest word length? ");	
  // Read a line from the keyboard
  line = in.readLine();
  // convert the string to a number
  low = Integer.parseInt(line);

  System.out.print("longest word length? ");	
  line = in.readLine();
  high = Integer.parseInt(line);
  
  for (int i=0; i<MAX; i++) {
     wrd = MyRandom.nextString(low, high);
	  System.out.println(wrd);
  }
   
  while (true) {
     // Display a prompt to the user
     System.out.print("Want to quit? ");	
     // Read a line from the user...
     line = in.readLine();
     // if the user types the word "quit", we will break the loop
     if ( line.equalsIgnoreCase("yes") ) {  break; }
  }
    
}

}