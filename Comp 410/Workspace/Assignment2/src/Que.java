
public class Que {

	
	private int size = 0;
	private Node head = null;
	private Node tail = null;
	
	public Que(){
		
	}
	
	public <T> void enq(T v){
		Node<T> n = new Node<T>(v);
		if (head == null) 
			head = n; 
		if (tail != null){
			tail.setNext(n);
			n.setPrev(tail);
		}
		tail = n;
		size++;
	}
	
	public void deq(){
		if (size <= 1){
			head = null; tail = null;
			size=0;
		}
		else{
		head = head.getNext();
		head.setPrev(null);
		size--;
		}
		
	}
	
	public Object head(){
		return head.getVal();
	}
	
	public  Object tail(){
		return tail.getVal();
	}
	
	public boolean empty(){
		return (size == 0);
	}
	
	public int size(){
		return size;
	}
	
	public boolean equals(Que que2){
		Que q1 = this; Que q2 = que2;
		if (q1.size() ==0 && q2.size() ==0)
			return true;
		if (this.size() != que2.size())
			return false;
		for(int i=0;i<this.size();i++){
			if (q1.head() != q2.head()){return false;}
			
			else{ q1.deq(); q2.deq();}
		}
				
			
		return true;
	}
	
	
	
}
