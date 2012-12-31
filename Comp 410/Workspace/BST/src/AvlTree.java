
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
	
	private static class AvlNode<AnyType>{
		AnyType element;
		AvlNode<AnyType> right;
		AvlNode<AnyType> left;
		int height;
		
		//Constructors
		AvlNode (AnyType elt){
			this (elt,null,null);
		}
		
		AvlNode (AnyType elt, AvlNode<AnyType> lt, AvlNode<AnyType> rt){
			element = elt; right = rt; left = lt; height = 0;
		}
	}
	
	private int height(AvlNode<AnyType> t){
		/**
		 * Return the height of node t, or -1, if null
		 */
		return t==null ? -1 : t.height;
	}
	
	private AvlNode<AnyType> root;
	
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
		if(isEmpty()) throw new UnderflowException("AvlTree findMin");
		return findMin(root).element;
	}
	public AnyType findMax(){
		if(isEmpty()) throw new UnderflowException("AvlTree findMax");
		return findMax(root).element;
	}
	public void insert(AnyType x){
		root = insert(x,root);
	}
	public void printTree(){
		if (isEmpty())
			System.out.print("Empty Tree");
		else printTree(root);
		
	}

	
	private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t){
		/**
		 * Internal method to insert into a subtree
		 * @param x the item to insert
		 * @param t the node that roots the subtree
		 * @return the new root of the subtree
		 */
		if(t == null)
			return new AvlNode<AnyType>(x, null, null);
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult < 0){
			t.left = insert(x, t.left);
			if (height(t.right) - height(t.left) == 2)
				if (x.compareTo(t.left.element) < 0)
					t = rotateWithLeftChild(t);
				else 
					t = doubleWithLeftChild(t);
		}
		else if(compareResult > 0){
			t.right = insert(x, t.right);
			if (height(t.left) - height(t.right) == 2)
				
				if (x.compareTo(t.right.element) > 0)
					t = rotateWithRightChild(t);
				else
					t = doubleWithRightChild(t);
		}
		else
			; //duplicate; do nothing
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
		
	}
	private boolean contains(AnyType x, AvlNode<AnyType> t){
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
	private AvlNode<AnyType> findMin(AvlNode<AnyType> t){
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
	private AvlNode<AnyType> findMax(AvlNode<AnyType> t){
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
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2){
		/**
		 * Rotate Avl Tree node with left child.
		 * Update heights, then return the new root
		 */
		AvlNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height);
		return k1;
	}
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3){
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithRightChild(k3);
	}
	
	private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1){
		/**
		 * Rotate Avl Tree node with left child.
		 * Update heights, then return the new root
		 */
		AvlNode<AnyType> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height);
		return k2;
	}
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3){
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithLeftChild(k3);
	}

	private void printTree(AvlNode<AnyType> t){
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
