package Assignment1;

import java.util.HashMap;

public class Question1 {

	public static void main(String[] args) {
		String s1 = "";
		String s2 = "";
		System.out.println(s1 + "  " + s2 + " => " + checkPermutation(s1, s2) + "\n");

		String s3 = "listen";
		String s4 = "siLenT";
		System.out.println(s3 + "  " + s4 + " => " + checkPermutation(s3, s4) + "\n");

		String s5 = "Apple";
		String s6 = "Pabble";
		System.out.println(s5 + "  " + s6 + " => " + checkPermutation(s5, s6) + "\n");

		String s7 = null;
		String s8 = null;
		System.out.println(s7 + "  " + s8 + " => " + checkPermutation(s7, s8));
	}

	/**
	 * Checks if the given input is invalid: 
	 * - case 1: different lengths => impossible to be permutations of each other 
	 * - case 2: contains other characters then English alphabet letters
	 * 
	 * @param word1
	 *            first word
	 * @param word2
	 *            second word
	 * @return string describing the error / null
	 */
	public static String checkInput(String word1, String word2) {
		// Check if there is a null string
		if (word1 == null || word2 == null) {
			return "Null String";
		}

		// Check the length
		if (word1.length() != word2.length()) {
			return "Different length";
		}

		// Iterate through each word at the same time
		for (int i = 0; i < word1.length(); i++) {

			// Check if the current characters are valid
			if (!isValidCharacter(word1.charAt(i)) || !isValidCharacter(word2.charAt(i))) {
				return "Invalid input";
			}
		}

		// No error
		return null;
	}

	/**
	 * Check if a word is the permutation of another word
	 * 
	 * @param word1
	 *            the first word
	 * @param word2
	 *            the second word
	 * @return true if word2 is a permutation of word1, false otherwise
	 */
	public static boolean checkPermutation(String word1, String word2) {
		// Check the validity of the input
		String error = checkInput(word1, word2);
		if (error != null) {
			System.out.println("ERROR: " + error);
			return false;
		}

		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();

		// Iterate through the characters of the first word and keep the number
		// of appearances of each letter in a hash map
		for (int i = 0; i < word1.length(); i++) {
			char letter = Character.toLowerCase(word1.charAt(i));

			// The key is in the hash map => update the value
			if (hashMap.containsKey(letter) == true) {
				int oldValue = hashMap.get(letter);
				hashMap.replace(letter, oldValue + 1);
			}

			// The key is not in the hash map => add the key with value = 1
			else {
				hashMap.put(letter, 1);
			}
		}

		// Iterate through the characters of the second word and decrement the
		// value for the current character
		for (int i = 0; i < word2.length(); i++) {
			char letter = Character.toLowerCase(word2.charAt(i));

			// The key is in the hash map and there are
			if (hashMap.containsKey(letter) && hashMap.get(letter) > 0) {
				int oldValue = hashMap.get(letter);
				hashMap.replace(letter, oldValue - 1);
			}

			// The letter is not in the hash map / no more appearances
			else {
				return false;
			}
		}

		return true;
	}

	/**
	 * Check if a character is from the English alphabet
	 * 
	 * @param x
	 *            tested character
	 * @return true if is from the alphabet, false otherwise
	 */
	public static boolean isValidCharacter(char x) {
		if ((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')) {
			return true;
		}

		return false;
	}
}
