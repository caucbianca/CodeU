package Assignment5;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		/* TEST 1
		 * EXPECTED OUTPUT: T, A, R, C 
		 */
		ArrayList<String> dictionary1 = new ArrayList<>();
		dictionary1.add("ART");
		dictionary1.add("RAT");
		dictionary1.add("CAT");
		dictionary1.add("CAR");
		
		String alphabet1 = FindAlphabet.findAlphabet(dictionary1);
		System.out.println("Test 1: " + alphabet1);
		
		
		/* TEST 2
		 * EXPECTED OUTPUT: Impossible to find a valid alphabet! (Cyclic graph)
		 */
		ArrayList<String> dictionary2 = new ArrayList<>();
		dictionary2.add("ABC");
		dictionary2.add("ACB");
		dictionary2.add("BCA");
		dictionary2.add("BBA");
		
		String alphabet2 = FindAlphabet.findAlphabet(dictionary2);
		System.out.println("Test 2: "+ alphabet2);
	}

}
