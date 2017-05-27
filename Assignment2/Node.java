package Assignment2;

public class Node<T> {
	private T key;
	Node<T> left;
	Node<T> right;

	public Node(T key, Node<T> left, Node<T> right){
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	public T getKey(){
		return this.key;
	}

	public void setKey(T newKey) {
		this.key = newKey;
	}
	
}
