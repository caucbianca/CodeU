package Assignment2;

public class Question2<T> {

	public static void main(String[] args) {
		Node node1 = new Node();
		Node node2 = new Node();
		
		/* the tree from assignment for the first question*/
		BinaryTree<Integer> tree = new BinaryTree<>();
		tree.addRoot(16, true);
		tree.addNode(9, 16, true);
		tree.addNode(18, 16, false);
		tree.addNode(19, 18, false);
		tree.addNode(14, 9, false);
		tree.addNode(3, 9, true);
		tree.addNode(5, 3, false);
		tree.addNode(1, 3, true);
		
		/* TEST 1: example question 2 (both in the left subtree of the root)
		 * EXPECTED OUTPUT: "Lowest common ancestor for 5 and 14 : 9" */
		node1.setKey(5);
		node2.setKey(14);
		displayCommonAncestor(tree, node1, node2);
		
		/* TEST 2: One of the nodes is the root of the tree
		 * EXPECTED OUTPUT: "Lowest common ancestor for 3 and 16 : 16" */
		node1.setKey(3);
		node2.setKey(16);
		displayCommonAncestor(tree, node1, node2);
		
		/* TEST 3: One of the nodes in the left subtree and the other in the right
		 * EXPECTED OUTPUT: "Lowest common ancestor for 9 and 18 : 16" */
		node1.setKey(9);
		node2.setKey(18);
		displayCommonAncestor(tree, node1, node2);
		
		/* TEST 4: One node is the parent of the other one 
		 * EXPECTED OUTPUT: "Lowest common ancestor for 18 and 19 : 18" */
		node1.setKey(18);
		node2.setKey(19);
		displayCommonAncestor(tree, node1, node2);
		
		/* TEST 5: One node does not appear in the tree
		 * EXPECTED OUTPUT: "Lowest common ancestor for 7 and 3 : null" */
		node1.setKey(7);
		node2.setKey(3);
		displayCommonAncestor(tree, node1, node2);
//		 
	}
	
	public static <T> void displayCommonAncestor(BinaryTree<T> tree, Node first, Node second) {
		/* Determine the lowest common ancestor for the nodes */
		Node ancestor = commonAncestors(tree.root, first, second);
		
		/* Display the ancestor */
		if (ancestor != null){
			System.out.println("Lowest common ancestor for " + first.getKey() + " and "+ 
					second.getKey() + " : " + ancestor.getKey());
		}
		/* There is no ancestor for the nodes (one of them doesn't appear in the tree) */
		else{
			System.out.println("Lowest common ancestor for " + first.getKey() + " and "+ 
					second.getKey() + " : null");
		}
	}

	/**
	 * Determine the lowest common ancestor of the given nodes
	 * 
	 * @param root		the root of the tree 
	 * @param first		the first node
	 * @param second	the second node
	 * @return			the lowest common ancestor / null
	 */
	public static Node commonAncestors(Node root, Node first, Node second) {
		Node ancestor = null;

		/* Determine how many nodes appear in the left subtree */
		int foundLeft = countNodes(root.left, first, second);

		/* Both nodes appear in the left subtree */
		if (foundLeft == 2) {

			 /* The current root is the direct ancestor of one of the nodes =>
			    the current root is the lowest common ancestor */
			if (root.left.equals(first) || root.left.equals(second)) {
				return root.left;
			}

			/* There is a lower common ancestor than the current one in the left subtree */
			else {
				return commonAncestors(root.left, first, second);
			}
		}

		/* Only one node was found in the left subtree */
		if (foundLeft == 1) {
			/* The other node is the root => the lowest common ancestor is the root */
			if (root.equals(first) || root.equals(second)) {
				return root;
			}
		}
		
		/* Determine how many nodes appear in the right subtree */
		int foundRight = countNodes(root.right, first, second);

		/* Both nodes were found in the right subtree */
		if (foundRight == 2) {

			 /* The current root is the direct ancestor of one of the nodes =>
			    the current root is the lowest common ancestor */
			if (root.right.equals(first) || root.right.equals(second)) {
				return root.right;
			}

			/* There is a lower common ancestor than the current one in the right subtree */
			else {
				return commonAncestors(root.right, first, second);
			}
		}

		/* Only one node was found in the right subtree */
		if (foundRight == 1) {
			/* The other node is the root => the lowest common ancestor is the root */
			if (root.equals(first) || root.equals(second)) {
				return root;
			}
		}

		/* One node is in the left subtree and the other one in the right
		   subtree => The lowest common ancestor is the root */
		if (foundLeft == 1 && foundRight == 1) {
			return root;
		}

		/* One of the nodes does not appear in the tree => No common ancestor */
		return null;
	}

	/**
	 * Count how many of the given two nodes appear in the tree
	 * 
	 * @param root		the root of the tree
	 * @param first		the first node
	 * @param second	the second node
	 * @return 			2 if both appears, 1 if only one appears, 0 otherwise
	 */
	public static int countNodes(Node root, Node first, Node second) {
		int result = 0;

		/* Condition for exiting the recurrence */
		if (root == null) {
			return 0;
		}

		/* The root is one of the two given nodes */
		if (root.equals(first) || root.equals(second)) {
			result++;
		}

		/* Check the left subtree */
		result += countNodes(root.left, first, second);

		/* Both nodes were found => we don't search the right subtree anymore */
		if (result == 2) {
			return result;
		}

		/* Search the right subtree and return the result */
		return result + countNodes(root.right, first, second);
	}
}
