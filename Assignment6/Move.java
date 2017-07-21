package Assignment6;

public class Move {
	int car;
	int initialPosition;
	int finalPosition;
	
	public Move(int car, int initialPosition, int finalPosition) {
		this.car = car;
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
	}
	
	public StringBuilder getDisplayText() {
		StringBuilder move = new StringBuilder();
		move.append("Move ");
		move.append(car);
		move.append(" from ");
		move.append(initialPosition);
		move.append(" to ");
		move.append(finalPosition);
		move.append(".\n");

		return move;
	}

}
