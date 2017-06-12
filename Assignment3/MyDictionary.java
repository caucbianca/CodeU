package Assignment3;

import java.util.ArrayList;

public class MyDictionary {
	ArrayList<String> words;
	ArrayList<String> prefixes;

	public MyDictionary(ArrayList<String> words) {
		this.words = words;
		/* Add the prefixes */
		prefixes = new ArrayList<String>();
		for (String currentWord : words) {
			for (int j = 0; j < currentWord.length(); j++) {
				String currentPrefix = currentWord.substring(0, j + 1);
				if (!prefixes.contains(currentPrefix)) {
					prefixes.add(currentPrefix);
				}
			}
		}
	}

	public boolean isPrefix(String prefix) {
		return prefixes.contains(prefix);
	}

	public boolean isWord(String word) {
		return words.contains(word);
	}
}
