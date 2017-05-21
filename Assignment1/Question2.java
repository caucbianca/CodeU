package Assignment1;

public class Question2 {

	public static void main(String[] args) {

		// Testing for Integer elements
		Cell<Integer> firstCell = new Cell<Integer>(0, null);
		Cell<Integer> currentCell, previousCell;
		previousCell = firstCell;
		for (int i = 1; i <= 100; i++) {
			currentCell = new Cell<Integer>(i, null);
			previousCell.setNext(currentCell);
			previousCell = currentCell;
		}

		int k = 4;
		Cell<Integer> result = find(k, firstCell);
		if (result != null) {
			System.out.println(result.getValue());
		} else {
			System.out.println("Invalid value for k");
		}

	}

	/**
	 * Finds the k-th to the last element of a given list: iterate through the
	 * list until it reaches the k-th to the first element and then starts
	 * iterate with another pointer the list - when the previous one reaches the
	 * end of the list, the other will point to the k-th to the last element
	 * 
	 * @param k
	 *            the index of the element
	 * @param firstCell
	 *            list
	 * @return k-th element to the last / null
	 */
	public static <E> Cell<E> find(int k, Cell<E> firstCell) {
		Cell<E> cell = firstCell;
		Cell<E> cellAux = firstCell;
		int index = 1;

		// Iterate through the list until we reach the k-th to the first element
		while (cell != null && index <= k) {
			cell = cell.getNext();
			index++;
		}

		// In the previous iteration, the end of the list was reached, so k is
		// bigger than the length of the list
		if (cell == null) {
			return null;
		}

		// Iterate the list with two pointers: when cell.getNext is null,
		// cellAux will point to the k-th to the last element
		while (cell.getNext() != null) {
			cell = cell.getNext();
			cellAux = cellAux.getNext();
		}

		return cellAux;
	}
}

class Cell<E> {
	private E value;
	private Cell<E> next;

	public Cell() {
		value = null;
		next = null;
	}

	public Cell(E value, Cell<E> next) {
		this.value = value;
		this.next = next;
	}

	public E getValue() {
		return this.value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public Cell<E> getNext() {
		return this.next;
	}

	public void setNext(Cell<E> next) {
		this.next = next;
	}
}