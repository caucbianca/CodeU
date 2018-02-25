package Assignment5;

import java.util.ArrayList;

public class FindAlphabet {

	public static String findAlphabet(ArrayList<String> dictionary) {
		/* The dictionary contains only one word */
		if (dictionary.size() == 1) {
			return dictionary.get(0);
		}

		Graph graph = new Graph();

		String previousWord = dictionary.get(0);

		 /* Compare each word from the dictionary with the previous one in order to add
		  * edges in the graph (which will represent alphabet's constraints) */
		for (String currentWord: dictionary) {

			/* Invalid input (if the current word is a prefix of the previous word, then the dictionary is invalid */
			if (previousWord.length() > currentWord.length() && previousWord.startsWith(currentWord) == true)
				return null;

			int index = 0;
			int minLength = Math.min(previousWord.length(), currentWord.length());
			while (index < minLength) {
				/* Add the current letter in the graph */
				graph.addNode(currentWord.charAt(index));
				
				/* If the current two letters from the previous and current word are the same, go further */
				if (previousWord.charAt(index) == currentWord.charAt(index))
					index++;
				
				/* If the current two letters are different, add an edge in the graph then stop */
				else {
					graph.addNode(previousWord.charAt(index));
					graph.addEdge(previousWord.charAt(index), currentWord.charAt(index));
					
					break;
				}
			}

			/* Add the rest of the letters from the previous word in alphabet */
			int copyIndex = index;
			while (copyIndex < previousWord.length()) {
				graph.addNode(previousWord.charAt(copyIndex));
				copyIndex++;
			}

			/* Add the rest of the letters from the current word in alphabet */
			while (index < currentWord.length()) {
				graph.addNode(currentWord.charAt(index));
				index++;
			}

			/* Update the previous word */
			previousWord = currentWord;
		}

		/* Use the topological sort in order to find the alphabet */
		try {
			String alphabet = graph.topologicalSort().toString();
			return alphabet;
		}
		catch (NullPointerException e) {
			return "Impossible to find a valid alphabet! (Cyclic graph)";
		}
	}

}
