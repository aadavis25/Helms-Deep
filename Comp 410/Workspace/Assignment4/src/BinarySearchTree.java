
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	
	private static class BinaryNode<AnyType>{
		AnyType element; //data in the node
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		
		BinaryNode(AnyType theElement){
			this(theElement, null, null);
		}
		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt){
			element = theElement; 
			right = rt; 
			left = lt;
		}
	}
	
	private BinaryNode<AnyType> root;
	
	public BinarySearchTree(){
		root = null;
	}
	public void makeEmpty(){
		root=null; 
	}
	public boolean isEmpty(){
		return (root == null);
	}
	
	public boolean contains(AnyType x){
		return contains(x,root);
	}
	public AnyType findMin(){
		if(isEmpty()) throw new UnderflowException("BinarySearchTree findMin");
		return findMin(root).element;
	}
	public AnyType findMax(){
		if(isEmpty()) throw new UnderflowException("BinarySearchTree findMax");
		return findMax(root).element;
	}
	public void insert(AnyType x){
		root = insert(x,root);
	}
	public void remove(AnyType x){
		root = remove(x,root);
	}
	public void printTree(){
		if (isEmpty())
			System.out.print("Empty Tree");
		else printTree(root);
		
	}
	
	private boolean contains(AnyType x, BinaryNode<AnyType> t){
	/**
	 * Internal method to find an item in a subtree
	 * @param x is the item to search for
	 * @param t is the node that roots the subtree
	 * @return the node containing the matched item
	 */
		if (t == null)
			return false;
		
		int compareResult = x.compareTo(t.element);
		
		if (compareResult < 0)
			return contains(x, t.left);
		if (compareResult > 0)
			return contains(x, t.right);
		else 
			return true; //Match
	}
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
		/**
		 * Internal method to find the smallest element in a subtree
		 * @param t the root that contains the subtree
		 * @return the node containing the smallest item
		 */
		if(t == null)
			return null;
		else if(t.left==null)
			return t;
		return findMin(t.left);
	}
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
		/**
		 * Internal method to find the largest element in a subtree
		 * @param t the root that contains the subtree
		 * @return the node containing the largest item
		 */
		if(t == null)
			return null;
		else if(t.right==null)
			return t;
		return findMin(t.right);
	}
	
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t){
		/**
		 * Internal method to insert into a subtree
		 * @param x the item to insert
		 * @param t the node containing the subtree
		 * @return the new root of the subtree
		 */
		if (t == null)
			return new BinaryNode<AnyType>(x, null, null);
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult < 0)
			t.left = insert(x, t.left);
		else if(compareResult > 0)
			t.right = insert(x, t.right);
		else
			; //Duplicate; do nothing
		return t;
	}
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t){
		/**
		 * Internal method to remove from a subtree
		 * @param x the item to remove
		 * @param t the node that roots the subtree
		 * @return the new root of the subtree
		 */
		if (t == null)
			return t; //Item not found; do nothing
		
		int compareResult = x.compareTo(t.element);
		
		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null){ //Two children
			t.element = findMin(t.right).element;
			t.right = remove(t.element,t.right);
		}
		else
			t = (t.left != null) ? t.left : t.right;
		return t;
		
	}
	private void printTree(BinaryNode<AnyType> t){
		/**
		 * internal method to print a subtree in sorted order
		*@param t the node that roots the subtree
		*/
		if (t != null){
			printTree(t.left);
			System.out.print(t.element);
			printTree(t.right);
		}
	}
	
	
}