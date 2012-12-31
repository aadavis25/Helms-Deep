
public class AvlTest {

	
	public static void main (String[] args){
		AvlTree<Integer> tree = new AvlTree<Integer>();
		tree.insert(2);
		tree.insert(1);
		tree.insert(4);
		tree.insert(5);
		tree.insert(9);
		tree.insert(3);
		tree.insert(6);
		tree.insert(7);
		tree.printTree();
		
	}
}
