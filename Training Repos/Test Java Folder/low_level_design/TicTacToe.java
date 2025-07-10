package low_level_design;

import java.util.List;

/**
 * <pre>
 *   <b>Low level Design for TIC-TAC-TOE:</b>
 *         Design requirements :
 *           - Game should support 2 players
 *           - Game should end on two states - One low_level_design.Winner/Draw
 *           - Option selection for size of the board (Support for Normal(3x3), Medium(4x4) and Larger(5x5))
 *           - Reset Option to restart the game
 *           - Undo option to go back to the previous move
 * </pre>
 *
 */

enum BoardTypes {
	Normal(3), Medium(4), Larger(5);

	private int code;

	BoardTypes(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}

enum Cell {
	X, O
}

enum Winner {
	First_Player, Second_Player, Draw, Undecided
}

class Move {
	private int row;
	private int col;

	// getter and setters
}

/*
 * For creating low_level_design.Board object you can use two kinds of design
 * patterns as per article <link>
 * https://medium.com/@pelensky/java-tic-tac-toe-command-vs-factory-pattern-
 * 3fb141046145 </link>
 *
 * Command pattern: (refer your book) but sizes can be dynamically changed. So,
 * no use of it
 *
 * Factory Pattern: You can use this to dynamically create objects of board with
 * different sizes.
 */
class TicTacToe {

	private int size;
	private Cell[][] board;
	private List<Move> listOfMoves;
	private boolean isFirstPlayer = true;

	public TicTacToe(int size) {
		this.size = size;
		this.board = new Cell[size][size];
	}

	public Winner addMove(Move move) {
		// change the board according to the player
		// add the move to the listOfMoves
		// reverse the isFirstPlayer flag
		// call checkWin function to return low_level_design.Winner/Undecided
		// ...
		return null;
	}

	public void undo() {
		// remove the last element from listOfMoves
		// reverse the isFirstPlayer flag
		// ...
	}

	public void reset() {
		// empty the board
		// set the isFirstPlayer to true
		// ...
	}

	private boolean checkWin(Move move) {
		// check if the same players mark present diagonally or vertically or
		// horizontally
		// ...
		return false;
	}

}
