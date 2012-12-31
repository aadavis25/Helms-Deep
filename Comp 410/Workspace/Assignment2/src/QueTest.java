import static java.lang.System.out;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class QueTest {

	Que q = new Que();
	Que r = new Que();
	Que s = new Que();
	Que t = new Que();
	
	@Test
	public void empty(){
		assertTrue(q.empty());
		q.enq(6);
		assertFalse(q.empty());
	}
	
	@Test
	public void size(){
		assertEquals(r.size(),0);
		
		s.enq(4); s.enq(12);
		assertEquals(s.size(), r.size()+2);
	}
	
	@Test
	public void deq(){
		assertEquals(r.size(),0);
		assertTrue(t.equals(r)); //t is also supposedly empty
		
		t=r;
		r.enq(4);
		r.deq();
		assertTrue(t.equals(r));
		
		r.enq(3); r.enq(2);
		t=r;
		r.enq(9);
		r.deq();
		assertTrue(t.equals(r));
	}
	
	@Test
	public void head(){
		
		boolean x=false;
		try { t.head(); }
		catch (NullPointerException e){ x = true; }
		assertTrue(x);
		
		t.enq(4);
		assertEquals(t.head(),4);
		assertEquals(t.head(),t.tail());
		
		t.enq(6); 
		t.enq(8);
		assertEquals(t.head(), 4);
		t.deq();
		assertEquals(t.head(),6);
		t.deq();
		assertEquals(t.head(),8);
	}
	
	@Test
	public void tail(){ 
		
		boolean x=false;
		try { t.tail(); }
		catch (NullPointerException e){ x = true; }
		assertTrue(x);
		
		t.enq(70);
		assertEquals(t.head(),t.tail());
		t.enq(7);
		assertEquals(t.tail(),7);
		t.enq(900);
		assertEquals(t.tail(),900);
	}
	
	@Test
	public void equals(){
		assertTrue(q.equals(r));
		q.enq(5);
		assertFalse(q.equals(r));
		r.enq(5);
		assertTrue(q.equals(r));
		r.deq(); q.deq(); r.enq(4); q.enq(4);
		assertTrue(q.equals(r));
		
		
		
		
		
	}
	
}
