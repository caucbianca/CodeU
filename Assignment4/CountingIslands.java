package Assignment4;

import java.util.ArrayList;
import java.util.HashMap;

public class CountingIslands {

	/**
	 * Determine the number of islands in the matrix
	 * 
	 * @param matrix	a matrix with boolean elements (true = land, false = water)
	 * @return 		the number of islands
	 */
	public static int getNumberOfIslands(boolean[][] matrix) {
		int islandID = 0;
		int rows = matrix.length;
		int columns = matrix[0].length;
		
		HashMap<Integer, ArrayList<Coordinates>> islands = new HashMap<>();
		int[][] parentIsland = new int[rows][columns];
		
		int i, j, indexIsland;
		ArrayList<Coordinates> islandLeft, islandAbove;

		 /* For every tile in the matrix which is land, check the tile from above
		 * and to the left to see if they are part of the same island (it is not
		 * necessary to check the right tile and the below tile as they will be
		 * checked in the future) */
		for (i = 0; i < rows; i++) {
			for (j = 0; j < columns; j++) {
				if (matrix[i][j] == true) {
					 /* Check if the tile to the left and from above are both land */
					boolean checkAbove = i > 0 && matrix[i - 1][j] == true;
					boolean checkLeft = j > 0 && matrix[i][j - 1] == true;

					 /* The tile from above is land, so both are part of the same island */
					if (checkAbove == true) {
						 /* Determine the island where the current tile belongs to */
						indexIsland = parentIsland[i - 1][j];
						islandAbove = islands.get(indexIsland);

						/* Add the tile to the island from above */
						islandAbove.add(new Coordinates(i, j));

						/* Update the parent island */
						parentIsland[i][j] = indexIsland;
					}

					 /* The tile to the left is land, so both are part of the same island */
					if (checkLeft == true) {
						 /* Determine the island where the current tile belongs to */
						indexIsland = parentIsland[i][j - 1];
						islandLeft = islands.get(indexIsland);

						 /* If there was a tile above and this belongs to another
						 * island than the tile to the left, we unify the two
						 * islands (the current tile is already in the island above) */
						if (checkAbove == true
								&& parentIsland[i - 1][j] != indexIsland) {
							
							/* Determine the island from above */
							int indexIslandAbove = parentIsland[i - 1][j];
							islandAbove = islands.get(indexIslandAbove);
							
							/* Update the parent island for the tiles in the left island */
							for (Coordinates c: islandLeft) {
								parentIsland[c.x][c.y] = indexIslandAbove;
							}
							
							/* Add all the tiles from the left island to the above island (unification) */
							islandAbove.addAll(islandLeft);
							
							/* Delete the left island */
							islands.remove(indexIsland);
							islandID--;							
						} 
						
						/* There was no island above, so we add the current tile to the left island */
						else if (checkAbove == false) {
							/* Add the current tile to the left island */
							islandLeft.add(new Coordinates(i, j));

							/* Update the parent island */
							parentIsland[i][j] = indexIsland;
						}
					}

					/* There is no island above or to the left, so we create a new island */
					if (checkLeft == false && checkAbove == false) {
						/* Create a new island */
						ArrayList<Coordinates> newIsland = new ArrayList<>();
						newIsland.add(new Coordinates(i,j));
						islandID++;
						
						/* Add the current island in list of islands */
						islands.put(islandID, newIsland);

						/* Update the parent island of the tile */
						parentIsland[i][j] = islandID;
					}
				}
			}
		}

		/* IF WE WANT TO SEE WHICH CELLS BELONGS TO EACH ISLAND, ALL WE HAVE TO DO IS TO
		 * RETURN islands INSTEAD OF islands.size AND CHANGE THE TYPE OF THE METHOD TO 
		 * HashMap<Integer, ArrayList<Coordinates>> AND IN THE MAIN METHOD TO DISPLAY EACH
		 * ARRAYLIST FOR ALL THE ENTRIES IN THE HASHMAP.
		 */
		
		/* Return the number of islands */
		return islands.size();
	}

}
