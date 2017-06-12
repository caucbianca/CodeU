package Assignment3;

import java.util.ArrayList;
import java.util.TreeSet;

public class TestWordSearch {

	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<String>();
		words.add("car");
		words.add("card");
		words.add("cart");
		words.add("cat");

		/* Initialize dictionary */
		MyDictionary dictionary = new MyDictionary(words);
		char[][] matrix = { { 'a', 'a', 'r' }, { 't', 'c', 'd' } };

		/* Initialize expected output */
		TreeSet<String> expected = new TreeSet<String>();
		expected.add("car");
		expected.add("card");
		expected.add("cat");

		/* Search for words in the matrix */
		TreeSet<String> result = WordSearch.findWords(matrix.length, matrix[0].length, matrix,
				dictionary);
		
		/* Compare the output with the expected output */
		if (result.containsAll(expected) && expected.containsAll(result)) {
			System.out.println("CORRECT ANSWER");
		} else {
			System.out.println("WRONG ANSWER");
		}
	}

}
