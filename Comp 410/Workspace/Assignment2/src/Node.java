
public class Node<AnyType> {

	AnyType val;
	Node<AnyType> nextNode = null;
	Node<AnyType> prevNode = null;
	
	public Node (){
	}
	
	public Node (AnyType v){ 
		val = v; 
	}
	
	public void setVal(AnyType v){
		val = v;
	}
	
	public AnyType getVal(){
		return val;
	}

	public Node<AnyType> getNext() {
		return nextNode;
	}

	public void setNext(Node<AnyType> next) {
		nextNode = next;
	}

	public Node<AnyType> getPrev() {
		return prevNode;
	}

	public void setPrev(Node<AnyType> prev) {
		prevNode = prev;
	}
	
	
	
}
