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

	public void addNode(T key, T parentKey, boolean isLeft) {
		if (this.root == null) {
			addRoot(key, true);
			return;
		}

		Queue<Node<T>> queue = new LinkedList<Node<T>>();

		queue.add(root);

		Node<T> currentNode;
		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			if (currentNode.getKey() == parentKey) {
				if (isLeft == true) {
					currentNode.left = new Node<T>(key, null, null);
				} else {
					currentNode.right = new Node<T>(key, null, null);
				}
				return;
			}

			if (currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.add(currentNode.right);
			}
		}
		
		System.out.println("The tree doesn't contain a node with parentKey");
	}

	public void addRoot(T key, boolean isLeft) {
		if (this.root == null) {
			this.root = new Node<T>(key, null, null);
		} else {
			Node<T> newRoot;
			if (isLeft == true) {
				newRoot = new Node<T>(key, this.root, null);
			} else {
				newRoot = new Node<T>(key, null, this.root);
			}
			this.root = newRoot;
		}
	}
}
