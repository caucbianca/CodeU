package Assignment2;

public class Node<T> {
	private T key;
	Node<T> left;
	Node<T> right;
	
	public Node() {
		
	}
	
	public Node(T key) {
		this.key = key;
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
}
