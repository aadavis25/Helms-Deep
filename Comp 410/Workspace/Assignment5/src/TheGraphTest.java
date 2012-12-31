/**
 * @author Aaron M Davis
 * last edited: 11/19/11
 */
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;
public class TheGraphTest {

	TheGraph gr = new TheGraph();
	PriQueue pq = new PriQueue();
	TheGraph gr2 = new TheGraph();
	
	@Test
	public void add(){
		assertEquals(gr.size(),0);
		gr.add("Chapel_Hill");
		assertTrue(gr.getListNames().containsKey("Chapel_Hill"));
		assertTrue("Chapel_Hill".equals(gr.getVert("Chapel_Hill").getElt()));
		gr.add("Raleigh");
		gr.add("Durham");
		assertEquals(gr.size(),3);
	}
	@Test
	public void addAdjac(){
		gr.add("Chapel_Hill");
		gr.add("Raleigh");
		gr.add("Durham");
		gr.addAdjac("Raleigh", "Chapel_Hill", 12);
		gr.addAdjac("Chapel_Hill", "Raleigh", 7);
		gr.addAdjac("Chapel_Hill", "Durham", 1);
		gr.addAdjac("Durham", "Chapel_Hill", 100);
		gr.addAdjac("Raleigh", "Durham", 1);
		gr.addAdjac("Durham", "Raleigh", 50);
		assertEquals(gr.size(), 3);
		
		Iterator itr = gr.getListNames().keySet().iterator();
		int num = 0;
		while (itr.hasNext()){
			num += gr.listVert.get((Integer) gr.getListNames().get(itr.next())).getListAdj().size();
		}
		assertEquals(num,6);
		
		ArrayList list = gr.listVert.get((Integer) gr.getListNames().get("Chapel_Hill")).getListAdj();
		itr = list.iterator();
		num = 0;
		while (itr.hasNext()){
			num += ((TheGraph.GVertex.Adjac) itr.next()).getWeight();
		}
		assertEquals(num, 8);
		
		list = gr.listVert.get((Integer) gr.getListNames().get("Durham")).getListAdj();
		itr = list.iterator();
		num = 0;
		while (itr.hasNext()){
			num += ((TheGraph.GVertex.Adjac) itr.next()).getWeight();
		}
		assertEquals(num, 150);
		
		list = gr.listVert.get((Integer) gr.getListNames().get("Raleigh")).getListAdj();
		itr = list.iterator();
		num = 0;
		while (itr.hasNext()){
			num += ((TheGraph.GVertex.Adjac) itr.next()).getWeight();
		}
		assertEquals(num, 13);
	}
	@Test
	public void priorityQueueTest(){
		TheGraph.GVertex a = gr.new GVertex("a");
		TheGraph.GVertex b = gr.new GVertex("b");
		TheGraph.GVertex c = gr.new GVertex("c");
		TheGraph.GVertex d = gr.new GVertex("d");
		TheGraph.GVertex e = gr.new GVertex("e");
		TheGraph.GVertex f = gr.new GVertex("f");
		TheGraph.GVertex g = gr.new GVertex("g");
		TheGraph.GVertex h = gr.new GVertex("h");
		TheGraph.GVertex i = gr.new GVertex("i");
		assertEquals(pq.size(), 0);
		pq.enq(i,9);
		pq.enq(d,4);
		pq.enq(f,6);
		pq.enq(b,2);
		pq.enq(e,5);
		pq.enq(c,3);
		pq.enq(h,8);
		pq.enq(a,1);
		pq.enq(g,7);
		assertEquals(pq.size, 9);
		
		assertEquals(pq.deq().node.getElt(), "a"); 
		assertEquals(pq.deq().node.getElt(), "b");
		assertEquals(pq.deq().node.getElt(), "c");
		assertEquals(pq.deq().node.getElt(), "d");
		assertEquals(pq.deq().node.getElt(), "e");
		assertEquals(pq.deq().node.getElt(), "f");
		assertEquals(pq.deq().node.getElt(), "g");
		assertEquals(pq.deq().node.getElt(), "h");
		assertEquals(pq.deq().node.getElt(), "i");
		assertEquals(pq.size, 0);
	}
	@Test
	public void dijkstras(){
		
		gr2.add("A");
		gr2.add("B");
		gr2.add("C");
		gr2.add("D");
		gr2.add("E");
		gr2.add("F");
		gr2.add("G");
		gr2.add("H");
		gr2.addAdjac("A", "B", 9);
		gr2.addAdjac("A", "F", 14);
		gr2.addAdjac("A", "G", 15);
		gr2.addAdjac("B", "C", 24);
		gr2.addAdjac("F", "C", 18);
		gr2.addAdjac("F", "G", 5);
		gr2.addAdjac("F", "E", 30);
		gr2.addAdjac("C", "E", 2);
		gr2.addAdjac("G", "E", 20);
		gr2.addAdjac("G", "H", 44);
		gr2.addAdjac("E", "D", 6);
		gr2.addAdjac("D", "C", 6);
		gr2.addAdjac("E", "H", 16);
		gr2.addAdjac("C", "H", 19);
		
		DijAlg dijk = new DijAlg();
		ArrayList<String> list = dijk.tracePaths(gr2, "A");
		/* Should be
		A
		A->F
		A->G
		A->B
		A->F->C
		A->F->C->E->H
		A->F->C->E->D
		A->F->C->E
		*/
	}
	
	
}
