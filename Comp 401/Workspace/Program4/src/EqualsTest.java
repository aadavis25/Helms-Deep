//Equals Test
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class EqualsTest {
	@Test
	public void equals(){
		/** 
		 * 
		 *           ,----,                            ,----,                ,----,                           ,--,              
         *         ,/   .`|                          ,/   .`|              ,/   .`|                        ,---.'|              
         *       ,`   .'  :   ,---,.  .--.--.      ,`   .'  :            ,`   .'  : ,---,           ,---,. |   | :       ,---,. 
         *     ;    ;     / ,'  .' | /  /    '.  ;    ;     /          ;    ;     /'  .' \        ,'  .'  \:   : |     ,'  .' | 
         *   .'___,/    ,',---.'   ||  :  /`. /.'___,/    ,'         .'___,/    ,'/  ;    '.    ,---.' .' ||   ' :   ,---.'   | 
         *   |    :     | |   |   .';  |  |--` |    :     |          |    :     |:  :       \   |   |  |: |;   ; '   |   |   .' 
         *   ;    |.';  ; :   :  |-,|  :  ;_   ;    |.';  ;          ;    |.';  ;:  |   /\   \  :   :  :  /'   | |__ :   :  |-, 
         *   `----'  |  | :   |  ;/| \  \    `.`----'  |  |          `----'  |  ||  :  ' ;.   : :   |    ; |   | :.'|:   |  ;/| 
         *       '   :  ; |   :   .'  `----.   \   '   :  ;              '   :  ;|  |  ;/  \   \|   :     \'   :    ;|   :   .' 
         *       |   |  ' |   |  |-,  __ \  \  |   |   |  '              |   |  ''  :  | \  \ ,'|   |   . ||   |  ./ |   |  |-, 
         *       '   :  | '   :  ;/| /  /`--'  /   '   :  |              '   :  ||  |  '  '--'  '   :  '; |;   : ;   '   :  ;/| 
         *       ;   |.'  |   |    \'--'.     /    ;   |.'               ;   |.' |  :  :        |   |  | ; |   ,/    |   |    \ 
         *       '---'    |   :   .'  `--'---'     '---'                 '---'   |  | ,'        |   :   /  '---'     |   :   .' 
         *                |   | ,'                                               `--''          |   | ,'             |   | ,'   
         *                `----'                                                                `----'               `----'  					 
		 *      _______________________________________________
		 * 		|	           <in>                |   <out>  |
		 *   	|     b6                b7         |          |
		 * 		|	(empty)          (empty)       |   true   |
		 * 		|	4                (empty)       |   false  |
		 * 		|	4                   4          |   true   |
		 * 		|	{4,7,-8}         {4,-8,7}      |   true   |
		 * 		|	{4,7,-8,99}      {4,-8,7, 100} |   false  |
		 *      |   {4,7,-8,-1111}   {4,-8,7, 1111}|   false  |
		 *      _______________________________________________ 
		 * 
		 * 
		 */
		
		out.println("\n*** equals");
		BoundedBag b6 = new BoundedBag();
		BoundedBag b7 = new BoundedBag();
		assertEquals(true, b7.equals(b7));
		
		b6.add(4);
		assertEquals(false, b7.equals(b6));
		
		b7.add(4);
		assertEquals(true, b7.equals(b6));
		
		b6.add(7);
		b6.add(-8);
		b7.add(-8);
		b7.add(7);
		out.println("b7 = " + b7);
		out.println("b6 = " + b6);
		assertEquals(true, b6.equals(b7));
		
		b7.add(100);
		b6.add(-99);
		out.println("b7 = " + b7);
		out.println("b6 = " + b6);
		assertEquals(false, b7.equals(b6));
		
		b7.remove(100);
		b6.remove(99);
		b7.add(1111);
		b6.add(-1111);
		assertEquals(false, b6.equals(b7));
	}
}
