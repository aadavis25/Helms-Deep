/**
 * @author AaronDavis
 * "On my honor, I have neither given nor received unauthorized aid on this assignment"
 * last modified: 11/9/11
 */
import static java.lang.System.out;

public class SPTNode<AnyType extends Comparable<? super AnyType>> {

		private AnyType element;
		private SPTNode<AnyType> left = null;
		private SPTNode<AnyType> right = null;
		private SPTNode<AnyType> parent = null;
		private static int size = 1;
		private static int dups = 0;
		private static SPTNode root;
		//constructor
		public SPTNode (AnyType theElement){ 
			element = theElement;
		}
		public SPTNode (AnyType theElement, SPTNode<AnyType> par){
			parent = par;
			element = theElement;
		}
		//Getters and setters
		public int getDups(){
			return dups;
		}
		public SPTNode<AnyType> getRoot(){
			return root;
		}
		public void setRoot(SPTNode<AnyType> r){
			root = r;
		}
		public SPTNode<AnyType> getParent(){
			return parent;
		}
		public AnyType getElement() {
			return element;
		}
		public void setElement(AnyType element) {
			this.element = element;
		}
		public SPTNode<AnyType> getLeft() {
			return left;
		}
		public SPTNode<AnyType> getRight() {
			return right;
		}
		
		/*
		 * External methods
		 */
		public static int getSize(){ 
			return size;
			}
		public  SPTNode<AnyType> insert(AnyType x){
			insert(x, this);
			return find(x);
		}
		public SPTNode<AnyType> find( AnyType x){
			return find(x, this);
		}
		public SPTNode<AnyType> remove(AnyType x){
			return remove(x, this);
		}
		public void print (){
			print(this,"");
		}
		
		/*
		 * Internal methods
		 */
		private SPTNode<AnyType> insert(AnyType x, SPTNode<AnyType> t){
			/**
			 * Internal method to insert into a subtree
			 * @param x the item to insert
			 * @param t the node containing the subtree
			 * @return the new root of the subtree
			 */
			if (t == null){
				size ++;
				return new SPTNode<AnyType>(x);
			}
			
			int compareResult = x.compareTo(t.element);
			
			if(compareResult < 0){
				t.left = insert(x, t.left);
				t.left.parent = t;
			}
			else if(compareResult > 0){
				t.right = insert(x, t.right);
				t.right.parent = t;
			}
			else{
				//System.out.println("DUPLICATE ALERT");
				dups++;
			}//Duplicate; do nothing
			return t;
		}
		
		private SPTNode<AnyType> remove(AnyType x, SPTNode<AnyType> t){
			/**
			 * Internal method to remove from a subtree
			 * @param x the item to remove
			 * @param t the node that roots the subtree
			 * @return the new root of the subtree
			 */
			if (t == null){
				out.println ("Item not found.");
				return t;
			//Item not found; do nothing
			}
			
			int compareResult = x.compareTo(t.element);
			
			if (compareResult < 0)
				t.left = remove(x, t.left);
			else if (compareResult > 0)
				t.right = remove(x, t.right);
			else if (t.left != null && t.right != null){ //Two children
				t.element = findMin(t.right).element;
				t.right = remove(t.element,t.right);
			}
			else{
				t = (t.left != null) ? t.left : t.right;
				size--;
			}
			return t;
		}
		
		private SPTNode<AnyType> findMin(SPTNode<AnyType> t){
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
		
		private SPTNode<AnyType> find(AnyType x, SPTNode<AnyType> t){
			/**
			 * Internal method to find an item in a subtree
			 * @param x is the item to search for
			 * @param t is the node that roots the subtree
			 * @return true if the item is found, false if not
			 */
				if (t == null)
					return null;
				
				int compareResult = x.compareTo(t.element);
				
				if (compareResult < 0)
					return find(x, t.left);
				if (compareResult > 0)
					return find(x, t.right);
				else{//Match, time to move the node to root
						splay(t);
;
					return t;
				}
			}
		
		private void splay(SPTNode<AnyType> t){
			AnyType x = t.getElement();
			while (t.parent != null){
			if (t.getParent().getParent() == null){ //if root is parent
				if (x.compareTo(t.getParent().getElement()) > 0){
					singleRight(t);
					return;
				}
				else {
					singleLeft(t);
					return;
				}
			}
			else if (x.compareTo(t.getParent().getElement()) > 0)
				if (t.getParent().getElement().compareTo(t.getParent().getParent().getElement()) > 0){
					zigzigRight(t);
					return;
				}
				else{
					zigzagRight(t);
					return;
				}
			else if ((x.compareTo(t.getParent().getElement()) < 0))
				if (t.getParent().getElement().compareTo(t.getParent().getParent().getElement()) < 0){
					zigzigLeft(t);
					return;
				}
				else {
					zigzagLeft(t);
					return;
				}
			root = t;
			}
			
			
		}

		private SPTNode<AnyType> singleLeft(SPTNode<AnyType> x){
			/**
			 * Internal method to rotate splay the tree
			 * Switches the root node and a left child
			 * @param x left child of the root
			 * @return the new root of the tree
			 */
			//change pointers
			SPTNode<AnyType> par = x.parent;
 			x.parent = null; 
			par.parent = x;
			par.left = x.right;
			x.right = par;
			return x;
		}
		private SPTNode<AnyType> singleRight(SPTNode<AnyType> x){
			/**
			 * Internal method to rotate splay the tree
			 * Switches the root node and a right child
			 * @param x right child of the root
			 * @return the new root of the tree
			 */
			//change pointers
			SPTNode<AnyType> par = x.parent;
			x.parent = null; 
			par.parent = x;
			par.right = x.left;
			x.left = par;
			
			
			return x;
	}
		private SPTNode<AnyType> zigzagRight(SPTNode<AnyType> x){
			/**
			 * Internal method to rotate splay the tree
			 * rotates 3 nodes in a left zig zag pattern
			 * @param x right child of a parent that is the left
			 * child of the grandparent
			 * @return the new original node that is now the parent
			 * of its old parent and grandparent
			 */
			SPTNode<AnyType> par = x.parent;
			SPTNode<AnyType> grand = par.parent;
		
			x.parent = grand.parent;
			par.parent = x;
			grand.parent = x;
			par.right = x.left;
			grand.left = x.right; 
			if (par.right != null)
				par.right.parent = par;
			if (grand.left != null)
				grand.left.parent = grand;
			x.left = par; 
			x.right = grand;
			return x;
	}
		private SPTNode<AnyType> zigzagLeft(SPTNode<AnyType> x){
			/**
			 * Internal method to rotate splay the tree
			 * rotates 3 nodes in a right zig zag pattern
			 * @param x left child of a parent that is the right
			 * child of the grandparent
			 * @return the new original node that is now the parent
			 * of its old parent and grandparent
			 */
			SPTNode<AnyType> par = x.parent;
			SPTNode<AnyType> grand = par.parent;
		
			x.parent = grand.parent; 
			par.parent = x;
			grand.parent = x;
			grand.right = x.left; 
			par.left = x.right; 
			if (par.left != null)
				par.left.parent = par;
			if (grand.right != null)
				grand.right.parent = grand;
			x.right = par;
			x.left = grand;
		
			return x;
	}		
		private SPTNode<AnyType> zigzigLeft(SPTNode<AnyType> x){
		
			/**
			 * Internal method to rotate splay the tree
			 * rotates 3 nodes in a left zig zig pattern
			 * @param x left child of a parent that is the left
			 * child of the grandparent
			 * @return the new original node that is now the parent
			 * of its old parent and the grandparent of its old grandparent
			 */
			SPTNode<AnyType> par = x.parent;
			SPTNode<AnyType> grand = par.parent;
		
			x.parent = grand.parent;
			grand.parent = par;
			par.parent = x;
			grand.left = par.right;
			par.left = x.right;
			if (grand.left != null)
				grand.left.parent = grand;
			if (par.left != null)
				par.left.parent = par;
			par.right = grand;
			x.right = par;
		
			return x;
	}
		private SPTNode<AnyType> zigzigRight(SPTNode<AnyType> x){
			/**
			 * Internal method to rotate splay the tree
			 * rotates 3 nodes in a right zig zig pattern
			 * @param x right child of a parent that is the right
			 * child of the grandparent
			 * @return the new original node that is now the parent
			 * of its old parent and the grandparent of its old grandparent
			 */
			
			SPTNode<AnyType> par = x.parent;
			SPTNode<AnyType> grand = par.parent;
		
			x.parent = grand.parent;
			grand.parent = par;
			par.parent = x;
			grand.right = par.left; 
			par.right = x.left; 
			if (grand.right != null)
				grand.right.parent = grand;
		
			if (par.right != null)
				par.right.parent = par;
		
			par.left = grand;
			x.left = par;
		
			return x;
	}	
		
		//Reverse-in-order traversal
		private void print(SPTNode<AnyType> t, String s){
			if ( t != null ){
			print(t.right, s + "     ");
			out.println(s + t.element);
			print(t.left, s + "     ");
			}
		}
	}