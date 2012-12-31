/**
 * @author Aaron M Davis
 * last edited: 11/19/11
 */
import java.util.ArrayList;


public class PriQueue {
	int size = 0;
	QueItem head = null;
	QueItem tail = null;
	
	/**
	 * @return minimum queue item 
	 * move head pointer and nullify returned QueItem's pointers
	 * Special cases for weird sizes
	 */
	public QueItem deq(){
		//keep track of node to be returned
		QueItem temp = head;
		size--;
		//change pointers based on special cases
		if (size == 0){
			head = null; tail = null;
		}
		else
			head = head.next;
		if (head != null){ 	
			head.prev = null;
		}
		//nullify pointers and return
		nullify(temp);
		return temp;
	}
	
	/**
	 * 
	 * @param v is the vertex to be added to the queue
	 * @param p is the priority of the item
	 * create a new QueItem from the vertex and the priority, put them at the back of the queue 
	 * and sort the queue
	 * Special case for empty queues
	 */
	public void enq(TheGraph.GVertex v, int pri){
		//create a queue item from the inputs
		QueItem q = new QueItem(null, tail, v, pri);
		size++;
		if (size > 1){
			tail.next = q;
			q.prev = tail;
			tail = q;
			//move pointers then sort
			sort(q);
		}
		else{//if queue was empty, just add and make it head and tail
			head = q;
			tail = q;
		}
	}
	
	/**
	 * @param q is the queue item just added to the list
	 * look at the item ahead of q in the list, if it's prority is lower, switch places
	 * Special cases for head and tail items.
	 */
	private void sort(QueItem q){
		while(q.prev.priority > q.priority){
			if (q == tail)
				tail = q.prev;
			if (q.prev == head)
				head = q;
			//keep track of nodes around the two to be switched
			QueItem tempNext = q.next;
			QueItem tempPrev = q.prev.prev;
			//move pointers
			q.next = q.prev;
			q.prev = tempPrev;
			q.next.next = tempNext;
			q.next.prev = q;
			//new next and previous of switched nodes
			if (tempNext != null) tempNext.prev = q.next;
			if (tempPrev != null) tempPrev.next = q;
			//break if reached beginning of queue
			if (head == q) break;
		}
	}
	
	/**
	 * @param q item to be nullified 
	 * Make pointers of the object null
	 */
	public void nullify(QueItem q){
		q.prev = null;
		q.next = null;
	}
	
	/**
	 * @return size of the queue
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Inner class of items that go in to the queue
	 */
	public class QueItem{
		/*Data members
		 * @prev is the item before the QueItem
		 * @next is the item behind the QueItem (both can be null if QueItem is head or tail)
		 * @node is the node contained in the QueItem
		 * @priority is the priority of the QueItem, determining its place in the queue
		 */
		QueItem prev;
		QueItem next;
		TheGraph.GVertex node;
		int priority;
		//getter
		public TheGraph.GVertex getNode() {return node;}
		//constructor
		public QueItem(QueItem nex, QueItem pre,TheGraph.GVertex n, int p){
			next = nex;
			prev = pre;
			node = n;
			priority = p;
		}
		
		/*public QueItem(TheGraph.GVertex n, int p){
			node = n;
			priority = p;
		}*/
	}
}
