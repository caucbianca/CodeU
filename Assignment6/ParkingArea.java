package Assignment6;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingArea {
	public static final int NO_CAR = 0;

	public static ArrayList<Move> rearrangeCars(int[] input, int[] output) {
		 /* The hash map keys are represented by the wrong placed cars' identifiers and the values
		 * are represented by the index of the parking spaces where these are located. */
		HashMap<Integer, Integer> wrongCarsPositions = new HashMap<>();

		int freeSpace = -1;

		 /* Add the wrong placed cars in the hash map and store the index of the free parking space. */
		for (int parkingSpaceIndex = 0; parkingSpaceIndex < input.length; parkingSpaceIndex++) {
			if (input[parkingSpaceIndex] == NO_CAR) {
				freeSpace = parkingSpaceIndex;
			} else if (output[parkingSpaceIndex] != input[parkingSpaceIndex]) {
				wrongCarsPositions.put(input[parkingSpaceIndex], parkingSpaceIndex);
			}
		}

		ArrayList<Move> moves = new ArrayList<>();

		while (!wrongCarsPositions.isEmpty()) {
			 /* Determine which car is going to be moved in the free parking space */
			int movingCar = output[freeSpace];

			/* If the free parking space is correctly placed and there are still wrong placed cars,
			 * move the first wrong placed car in the free space */
			if (movingCar == NO_CAR) {
				/* Determine the first wrongly placed car and its parking place */
				movingCar = wrongCarsPositions.keySet().iterator().next();
				int currentPlace = wrongCarsPositions.get(movingCar);

				/* Move the car in the free parking space and store the move*/
				wrongCarsPositions.replace(movingCar, currentPlace, freeSpace);
				moves.add(new Move(movingCar, currentPlace, freeSpace));

				/* Update the index of the free space */
				freeSpace = currentPlace;
			} else {
				 /* Determine the parking place of the car which will be moved in the free space */
				int currentPlace = wrongCarsPositions.get(movingCar);

				/* Store the move */
				moves.add(new Move(movingCar, currentPlace, freeSpace));

				/* Update the index of the free space */
				freeSpace = currentPlace;

				/* Remove the car from the hash map */
				wrongCarsPositions.remove(movingCar);
			}
		}

		return moves;
	}

	public static String getRearrangingMoves(int[] input, int[] output) {
		ArrayList<Move> rearrangingMoves = rearrangeCars(input, output);
		if (rearrangingMoves == null || rearrangingMoves.isEmpty())
			return "No moves!";

		StringBuilder moves = new StringBuilder();
		for (Move currentMove : rearrangingMoves) {
			moves.append(currentMove.getDisplayText());
		}

		return moves.toString();
	}

}
