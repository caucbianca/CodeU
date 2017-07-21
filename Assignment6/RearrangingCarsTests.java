package Assignment6;

import static org.junit.Assert.*;

import org.junit.Test;

public class RearrangingCarsTests {

	@Test
	public void alreadyCorrectPlacedCarsTest() {
		int[] input = new int[] { 0, 1, 2, 3 };
		int[] output = new int[] { 0, 1, 2, 3 };
		
		assertEquals("No moves!", ParkingArea.getRearrangingMoves(input, output));
	}

	@Test
	public void freeParkingAreaTest() {
		int[] input = new int[] { 0 };
		int[] output = new int[] { 0 };
		
		assertEquals("No moves!", ParkingArea.getRearrangingMoves(input, output));
	}

	@Test
	public void normalCaseTest() {
		int[] input = new int[] { 1, 2, 0, 3 };
		int[] output = new int[] { 3, 1, 2, 0 };
		
		StringBuilder reference = new StringBuilder();
		String move1 = "Move 2 from 1 to 2.\n";
		String move2 = "Move 1 from 0 to 1.\n";
		String move3 = "Move 3 from 3 to 0.\n";
		reference.append(move1).append(move2).append(move3);		
		
		assertEquals(reference.toString(), ParkingArea.getRearrangingMoves(input, output));
	}

	@Test
	public void freeSpaceCorrectlyPlacedTest() {
		int[] input = new int[] { 0, 2, 1, 3, 4, 5 };
		int[] output = new int[] { 0, 3, 1, 2, 5, 4 };

		StringBuilder reference = new StringBuilder();
		String move1 = "Move 2 from 1 to 0.\n";
		String move2 = "Move 3 from 3 to 1.\n";
		String move3 = "Move 2 from 0 to 3.\n";
		String move4 = "Move 4 from 4 to 0.\n";
		String move5 = "Move 5 from 5 to 4.\n";
		String move6 = "Move 4 from 0 to 5.\n";
		reference.append(move1).append(move2).append(move3).append(move4).append(move5)
				.append(move6);

		assertEquals(reference.toString(), ParkingArea.getRearrangingMoves(input, output));
	}
}
