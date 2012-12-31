
// NOTE - no test tables in this sample solution.
// You should have them.

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BoundedBagTest {
	
	// Data members
	private BoundedBag empty, singleton, typical, full;
	
	@Before
	public void setup() {
		empty = new BoundedBag();
		
		singleton = new BoundedBag();
		singleton.add(347);

		typical = new BoundedBag();
		typical.add(33);
		typical.add(33);
		typical.add(-33);
		
		full = new BoundedBag();
		full.add(1);
		full.add(2);
		full.add(2);
		full.add(-999999999);
	}
	
	@Test
	public void defaultConstructor() {
		BoundedBag b0 = new BoundedBag();
		out.println( "\n*** b0 after default constructor = " + b0 );
		assertEquals(0, b0.size());
	}
	
	@Test
	public void add() {
		out.println("\n*** add");
		BoundedBag b1 = new BoundedBag();
		b1.add(1);
		out.println( "b1 = " + b1);
		assertEquals(1, b1.size());
		
		b1.add(2);
		out.println( "b1 = " + b1); 
		assertEquals(2, b1.size());
		
		b1.add(2);
		out.println( "b1 = " + b1);
		assertEquals(3, b1.size());
		
		b1.add(-999999999);
		out.println( "b1 = " + b1);
		assertEquals(4, b1.size());
	}
	
	@Ignore @Test
	public void containsSuccessful() {
		assertTrue(typical.contains(33));
		assertTrue(typical.contains(-33));
		assertTrue(full.contains(1));
		assertTrue(full.contains(2));
		assertTrue(full.contains( -999999999));
		assertFalse(empty.contains(4));
	}
	
	 @Test
	public void containsUnsuccessful() {
		assertFalse(empty.contains(1));
		assertFalse(typical.contains(9));
		assertFalse(full.contains(33));
	}

	
	@Test
	public void removeTypicalSuccessful() {
		assertTrue(typical.remove(33));
		assertEquals(2,typical.size());
		assertTrue(typical.remove(-33));
		assertEquals(1,typical.size());
		assertTrue(typical.remove(33));
		assertEquals(0,typical.size());
	}
	
	@Test
	public void removeUnsuccessful() {
		assertFalse(empty.remove(1));
		assertFalse(singleton.remove(1));
		assertFalse(typical.remove(44));
		assertFalse(full.remove(33));
	}
	
	// White box test
	@Test
	public void removeFirstFromFull() {
		assertTrue(full.remove(1));
	}
	
	// White box test
	@Test
	public void removeLastFromFull() {
		assertTrue(full.remove(-999999999));
	}

}
