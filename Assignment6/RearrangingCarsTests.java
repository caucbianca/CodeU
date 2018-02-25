package Assignment6;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RearrangingCarsTests {
	public static final int NO_CAR = 0;

	public int[] applyMoves(int[] input, int[] output) {
		ArrayList<Move> moves = ParkingArea.rearrangeCars(input, output);

		for (Move move : moves) {
			int initialPosition = move.initialPosition;
			int finalPosition = move.finalPosition;

			input[finalPosition] = input[initialPosition];
			input[initialPosition] = NO_CAR;
		}

		return input;
	}

	@Test
	public void alreadyCorrectPlacedCarsTest() {
		int[] input = new int[] { 0, 1, 2, 3 };
		int[] output = new int[] { 0, 1, 2, 3 };

		System.out.println("Already correct placed cars test: ");
		System.out.println(ParkingArea.getRearrangingMoves(input, output));
		System.out.println();

		assertArrayEquals(output, applyMoves(input, output));
	}

	@Test
	public void freeParkingAreaTest() {
		int[] input = new int[] { 0 };
		int[] output = new int[] { 0 };

		System.out.println("Free parking area test: ");
		System.out.println(ParkingArea.getRearrangingMoves(input, output));
		System.out.println();

		assertArrayEquals(output, applyMoves(input, output));
	}

	@Test
	public void normalCaseTest() {
		int[] input = new int[] { 1, 2, 0, 3 };
		int[] output = new int[] { 3, 1, 2, 0 };

		System.out.println("Normal case test: ");
		System.out.println(ParkingArea.getRearrangingMoves(input, output));
		System.out.println();
		
		assertArrayEquals(output, applyMoves(input, output));
	}

	@Test
	public void freeSpaceCorrectlyPlacedTest() {
		int[] input = new int[] { 0, 2, 1, 3, 4, 5 };
		int[] output = new int[] { 0, 3, 1, 2, 5, 4 };

		System.out.println("Free space correctly placed test: ");
		System.out.println(ParkingArea.getRearrangingMoves(input, output));
		System.out.println();
		
		assertArrayEquals(output, applyMoves(input, output));
	}
}
