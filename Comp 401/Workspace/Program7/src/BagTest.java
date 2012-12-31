import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BagTest {
	/*       input                output
	 *      add
	 *      1						true - b.contains(1)
	 *      12						same - b.contains(12)
	 *      1123					same
	 *      1234					same
	 *      6						same
	 *      1						same
	 *      8						same
	 *      9						same
	 *      16						same
	 * 		
	 * 		also true that size increases by 1 every add
	 * 
	 */
		@Test
		public void add(){
			Bag b = new Bag();
			b.add(1);
			b.add(12);
			b.add(1123);
			assertTrue(b.contains(1123));
			assertEquals(3, b.size());
			
			b.add(1234);
			assertTrue(b.contains(1234));
			assertEquals(4, b.size());
			
			b.add(6);
			assertTrue(b.contains(6));
			assertEquals(5, b.size());
			
			b.add(1);
			assertTrue(b.contains(1));
			assertEquals(6, b.size());
			
			b.add(8);
			assertTrue(b.contains(8));
			assertEquals(7, b.size());
			
			b.add(9);
			assertTrue(b.contains(9));
			assertEquals(8, b.size());
			
			b.add(16);
		assertTrue(b.contains(16));
		assertEquals(9, b.size());
			
		}
}
