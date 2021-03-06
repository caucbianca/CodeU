package Assignment2;

import java.util.ArrayList;

public class Question1<T> {

	public static void main(String[] args) {
		int key;

		/* TEST 1 */
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>();
		tree1.addRoot(5, true);
		tree1.addNode(2, 5, true);
		tree1.addNode(3, 2, true);
		tree1.addRoot(4, true);
		tree1.addNode(7, 2, false);
		/* EXPECTED OUTPUT: "Path to the node with key 7 : 2 5 4" */
		key = 7;
		getPath(tree1, key);
		/* EXPECTED OUTPUT: "The key 12 doesn't exists in the tree" */
		key = 12;
		getPath(tree1, key);

		/* TEST 2: the tree contains no keys */
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>();
		/* EXPECTED OUTPUT: "The key 3 doesn't exists in the tree" */
		key = 3;
		getPath(tree2, key);

		/* TEST 3: The tree from the assignment */
		BinaryTree<Integer> tree3 = new BinaryTree<>();
		tree3.addRoot(16, true);
		tree3.addNode(9, 16, true);
		tree3.addNode(18, 16, false);
		tree3.addNode(19, 18, false);
		tree3.addNode(14, 9, false);
		tree3.addNode(3, 9, true);
		tree3.addNode(5, 3, false);
		tree3.addNode(1, 3, true);
		/* EXPECTED OUTPUT: "Path to the node with key 5 : 3 9 16" */
		key = 5;
		getPath(tree3, key);
		/* EXPECTED OUTPUT: "Path to the node with key 14 : 9 16" */
		key = 14;
		getPath(tree3, key);
		/* EXPECTED OUTPUT: "Path to the node with key 16 : null" */
		key = 16;
		getPath(tree3, key);
		/* EXPECTED OUTPUT: "Path to the node with key 1 : 3 9 16" */
		key = 1;
		getPath(tree3, key);
	}

	/**
	 * Get the path to the node with the given key
	 * 
	 * @param tree	tree where the key is searched
	 * @param key	searched key
	 * @return 		path to the node with the given key
	 */
	public static <T> void getPath(BinaryTree<T> tree, T key) {
		/* Determine the path to the node with the given key */
		ArrayList<Node> path = new ArrayList<>();
		boolean found = determinePath(tree.root, key, path);

		/* The key was not found */
		if (found == false) {
			System.out.println("The key " + key + " doesn't exists in the tree");
		}

		/* The key was found => display it */
		else {
			displayPath(key, path);
		}
	}

	/**
	 * Determine the path to the node with the given key
	 * 
	 * @param root	the root of the tree where we search for the key
	 * @param key	searched key
	 * @return 		true if the key was found in the tree with the given root, 
	 * 				false otherwise
	 */
	public static <T> boolean determinePath(Node<T> root, T key, ArrayList<Node> path) {
		if (root == null) {
			return false;
		}

		/* Found the given key => the process of searching is stopped
		  (path variable will contain the path to the node) */
		if (root.getKey() == key) {
			return true;
		}

		/* Add the node to the current path */
		path.add(root);

		/* Check if the key appears in the left subtree */
		boolean checkLeftTree = determinePath(root.left, key, path);
		
		/* The key was found in the left subtree =>
		   Don't search the right subtree anymore */
		if (checkLeftTree == true) {
			return true;
		}
		
		/* Check if the key appears in the right subtree */
		boolean checkRightTree = determinePath(root.right, key, path);
		
		/* The key was found in the right subtree */
		if (checkRightTree == true) {
			return true;
		}

		/* The key was not found in the left subtree, neither in the right
		   subtree => delete the current node from the path */
			path.remove(path.size() - 1);

		return false;
	}

	/**
	 * Display the path to the node with the given key
	 * 
	 * @param path	path to the node
	 * @param key	searched key
	 */
	public static <T> void displayPath(T key, ArrayList<Node> path) {
		Node currentNode;
		System.out.print("Path to the node with key " + key + " : ");

		/* If the path is empty, the key is in the root of the tree */
		if (path.isEmpty()) {
			System.out.println("null");
		} 
		
		/* Display each element from the path */
		else {
			while (!path.isEmpty()) {
				currentNode = path.get(path.size() - 1);
				System.out.print(currentNode.getKey() + " ");
				path.remove(path.size() - 1);
			}
			System.out.println();
		}
	}
}
