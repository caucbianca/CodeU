package Assignment3;

import java.util.TreeSet;

public class WordSearch {

	/**
	 * Find all the words from the matrix that appear in the dictionary too
	 * 
	 * @param rows			number of rows of the matrix
	 * @param columns		number of columns of the matrix
	 * @param matrix		the matrix of letters
	 * @param dictionary		dictionary which contains a set of words
	 * @return			the set of words which appear in the dictionary
	 */
	public static TreeSet<String> findWords(int rows, int columns, char[][] matrix,
			MyDictionary dictionary) {
		
		/* Initialize a set in order to store the words without duplicates */
		TreeSet<String> words = new TreeSet<>();

		/* Apply the DFS algorithm for each letter in the matrix 
		   so we can find all the words which begin with that letter */
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				depthFirstSearch(i, j, matrix, dictionary, new boolean[rows][columns], "", words);
			}
		}

		/* Return the set of words */
		return words;
	}

	
	/**
	 * Apply the depth first search algorithm in order to find words which appear in dictionary
	 * 
	 * @param x			the row of the starting point
	 * @param y			the column of the starting point
	 * @param matrix		matrix of letters
	 * @param dictionary		dictionary with accepted words
	 * @param visited		shows which letters were used
	 * @param previousWord		the word obtained in the previous step
	 * @param words			the set of words found in the matrix which appear in dictionary
	 */
	public static void depthFirstSearch(int x, int y, char[][] matrix, MyDictionary dictionary,
			boolean[][] visited, String previousWord, TreeSet<String> words) {

		/* Invalid row or column */
		if (!checkRow(x, matrix) || !checkColumn(y, matrix)) {
			return;
		}

		/* The current letter has been already used in the word */
		if (visited[x][y] == true) {
			return;
		}

		String currentWord = previousWord + matrix[x][y];

		/* The word is valid => Add it in the set */
		if (dictionary.isWord(currentWord)) {
			words.add(currentWord);
		}

		/* Continue to search words which start with the current one */
		if (dictionary.isPrefix(currentWord)) {
			
			/* Mark the letter as visited (the letter can't be used anymore) */
			visited[x][y] = true;

			/* Apply the DFS algorithm for each neighbour */
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					depthFirstSearch(i, j, matrix, dictionary, visited, currentWord, words);
				}
			}
		}

	}

	public static boolean checkRow(int x, char[][] matrix) {
		return x >= 0 && x < matrix.length;
	}

	public static boolean checkColumn(int y, char[][] matrix) {
		return y >= 0 && y < matrix[0].length;
	}
}
