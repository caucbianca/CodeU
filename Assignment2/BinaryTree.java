package Assignment2;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {
	Node<T> root;

	public BinaryTree() {

	}

	public BinaryTree(Node<T> root) {
		this.root = root;
	}

	/**
	 * Add a new node in the tree
	 * @param key		the key of the new node
	 * @param parentKey	the key of the new node's parent
	 * @param isLeft	true if the new node will be left son, false otherwise
	 */
	public void addNode(T key, T parentKey, boolean isLeft) {
		/* The tree doesn't have a root => 
		 * Initialize root with this new node */
		if (this.root == null) {
			addRoot(key, true);
			return;
		}

		/* Use BFS in order to find the parent of the node 
		 * (assumed that there are no duplicate keys in the tree) */
		Queue<Node<T>> queue = new LinkedList<Node<T>>();

		/* Add the root in the queue */
		queue.add(root);

		Node<T> currentNode;
		while (!queue.isEmpty()) {
			/* Extract the first node from the queue */
			currentNode = queue.poll();
			
			/* The parent of the new node was found */
			if (currentNode.getKey() == parentKey) {
				/* Add the new node to the left */
				if (isLeft == true) {
					currentNode.left = new Node<T>(key, null, null);
				} 
				/* Add the new node to the right */
				else {
					currentNode.right = new Node<T>(key, null, null);
				}
				return;
			}

			/* Add the sons of the current node (if they exists) */
			if (currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.add(currentNode.right);
			}
		}
		
		/* The parent key was not found in the tree =>
		 * the new node can't be insered in the tree */
		System.out.println("The tree doesn't contain a node with parentKey");
	}

	/**
	 * Add a (new) root to the tree 	
	 * @param key	the key of the (new) root
	 * @param isLeft	true if there is a previous root and we want to insert this
	 * 					as a left son of the new root / false if we want to insert the
	 * 					previous root as a right son of the new root
	 */
	public void addRoot(T key, boolean isLeft) {
		/* The tree doesn't have a root */
		if (this.root == null) {
			this.root = new Node<T>(key, null, null);
		} 
		
		/* The tree has a root */
		else {
			Node<T> newRoot;
			
			/* Add the previous root as a son of the new root */
			if (isLeft == true) {
				newRoot = new Node<T>(key, this.root, null);
			} else {
				newRoot = new Node<T>(key, null, this.root);
			}
			
			/* Set the new root of the tree */
			this.root = newRoot;
		}
	}
}
