package Assignment5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

public class Graph {
	int numberEdges;
	HashSet<Character> nodes;
	HashMap<Character, HashSet<Character>> adjacentNodes;
	HashMap<Character, Integer> innerEdges;

	public Graph() {
		nodes = new HashSet<>();
		adjacentNodes = new HashMap<>();
		innerEdges = new HashMap<>();
	}

	public void addNode(Character letter) {
		boolean succes = nodes.add(letter);

		if (succes) {
			adjacentNodes.put(letter, new HashSet<Character>());
			innerEdges.put(letter, 0);
		}
	}

	public void deleteNode(Character letter) {
		nodes.remove(letter);
		adjacentNodes.remove(letter);
		innerEdges.remove(letter);
	}

	public void addEdge(Character source, Character destination) {
		HashSet<Character> neighbours = adjacentNodes.get(source);
		boolean success = neighbours.add(destination);

		 /* Update the number of edges and inner edges if the addition succeded */
		if (success) {
			numberEdges++;
			int oldValue = innerEdges.get(destination);
			innerEdges.replace(destination, oldValue + 1);
		}
	}

	public ArrayList<Character> topologicalSort() {
		ArrayList<Character> stack = new ArrayList<>();
		ArrayList<Character> sorted = new ArrayList<>();

		/* Add the nodes without inner edges in the stack */
		Set<Character> letters = adjacentNodes.keySet();
		for (Character letter : letters) {
			if (innerEdges.get(letter) == 0) {
				stack.add(letter);
			}
		}

		Character currentLetter;

		while (!stack.isEmpty()) {
			/* Extract the first node from the stack */
			currentLetter = stack.get(stack.size() - 1);
			stack.remove(stack.size() - 1);

			/* Add the node in the sorted stack */
			sorted.add(currentLetter);

			/* Check all the edges for the node */
			HashSet<Character> neighbours = this.adjacentNodes.get(currentLetter);			
			Iterator<Character> iterator = neighbours.iterator();
			while (iterator.hasNext()) {
				Character destination = iterator.next();
				
				/* Delete the source -> destination edge */
				neighbours.remove(destination);
				
				/* Update the number of edges and inner edges */
				numberEdges--;
				int oldValue = innerEdges.get(destination);
				innerEdges.replace(destination, oldValue - 1);
				
				/* Add in the stack the node if it has no inner edges */
				if (innerEdges.get(destination) == 0) {
					stack.add(destination);
				}
			}
		}

		 /* If there are remaining edges, it means that the graph had a cycle, so the alphabet is invalid */
		if (this.numberEdges != 0) {
			return null;
		}

		return sorted;
	}
}
