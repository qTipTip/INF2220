import java.util.ArrayList;
public class BinarySearchTree{

	private static class BinaryNode{

		BinaryNode(String word){
			this(word, null, null);
		}
		BinaryNode(String word, BinaryNode left, BinaryNode right){
			this.word = word;
			this.left = left;
			this.right = right;
		}

		void findDepth(int i){
			
			this.depth = i;
			if(this.depth > maxDepth){
				maxDepth++;
			}
			if(this.left != null){

				this.left.findDepth(i+1);
			}
			if(this.right != null){
				this.right.findDepth(i+1);
			}
		}
		
		int getMaxDepth(){
			return maxDepth;
		}

		String word;
		BinaryNode left;
		BinaryNode right;
		int depth;
		static int maxDepth;
	}

	private BinaryNode root;
	private int[] depthDistribution;	
	public BinarySearchTree(){
		root = null;
	}

	public void makeEmpty(){
		root = null;
	}

	public boolean isEmpty(){
		return root == null;
	}

	public boolean contains(String word){
		return contains(word, root);
	}

	public String findMin(){
		if (isEmpty()) {
			System.out.println("UnderFlowException in findMin");
			//throw new UnderflowException();
		}
		return findMin(root).word;
	}

	public String findMax(){
		if (isEmpty()) {
			System.out.println("UnderFlowException in findMax");
			//throw new UnderflowException();
		}
		return findMax(root).word;
	}
	public void insert (String word){
		root = insert(word, root);
	}
	public void remove(String word){
		root = remove(word, root);
	}
	public void printTree(){
		// The public printTree method
		if( isEmpty()){
			System.out.println( " The Dictionary is Empty ");
		} else {
			printTree(root);
		}
	}

	private boolean contains(String word, BinaryNode n){
		if(n == null){
			return false;
		}
		int compareResult = word.compareTo(n.word);
		if(compareResult < 0){
			return contains(word, n.left);
		} else if (compareResult > 0){
			return contains(word, n.right);
		} else {
			return true;
		}
	}

	private BinaryNode findMin(BinaryNode n){
		// Finds the minimum entry in the subtree rooted by n
		if(n == null){
			return null;
		} else if ( n.left == null ){
			return n;
		}
		return findMin(n.left);
	}

	private BinaryNode findMax(BinaryNode n){
		//Finds the maximum entry in the subtree rooted by n
		if ( n != null ){
			while( n.right != null){
				n = n.right;
			}
		}
		return n;
	}

	private BinaryNode insert(String word, BinaryNode n){
		if (n == null){
			return new BinaryNode(word, null, null);
		}
		int compareResult = word.compareTo(n.word);

		if (compareResult < 0){
			n.left = insert(word, n.left);
		} else if (compareResult > 0){
			n.right = insert(word, n.right);
		} else {
			//
		}
		return n;

	}

	private BinaryNode remove(String word, BinaryNode n){
		if (n == null){
			return n;
		}

		int compareResult = word.compareTo(n.word);

		if(compareResult < 0){
			n.left = remove(word, n.left);
		} else if (compareResult > 0){
			n.right = remove(word, n.right);
		} else if (n.left != null && n.right != null){
			n.word = findMin(n.right).word;
			n.right = remove(n.word, n.right);
		} else {
			n = (n.left != null) ? n.left : n.right; // Using the ternary operator
		}
		return n;
	}

	private void printTree(BinaryNode n){
		// Recursively prints the contents of the tree in sorted order
		if(n != null){
			printTree(n.left);
			System.out.println(n.word);
			printTree(n.right);
		}
	}

	private void depthDistribution(BinaryNode n){
		if (n != null){
			depthDistribution(n.left);
			depthDistribution[n.depth] = depthDistribution[n.depth] + 1;	
			depthDistribution(n.right);
		}	
	}
 
	public void printStatistics(){
		root.findDepth(0);
		System.out.println("Depth of binary search tree: " + root.getMaxDepth());
		float averageDepth = 0;

		depthDistribution = new int[root.getMaxDepth() + 1];
		depthDistribution(root);
		for (int i = 0; i < depthDistribution.length - 1; i++) {
			averageDepth = depthDistribution[i]*i;
			System.out.println("Level " + (i) + ": " + depthDistribution[i]);
		}
		averageDepth = averageDepth / root.getMaxDepth();
		System.out.println("Average depth of nodes: " + averageDepth);
		
	}


	
}

