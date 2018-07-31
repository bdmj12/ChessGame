package pieces;

import java.util.ArrayList;

import gui.Board;

public class Bishop extends Piece {

	private Type type;

	public Bishop(Alliance alliance, Board board, int position, King king) {
		super(alliance, board, position, king);
		this.type = Type.BISHOP;
	}

	@Override
	public ArrayList<Integer> calculateLegalMoves() {
		ArrayList<Integer> moves = new ArrayList<>();
		int pos = this.getPosition();

		// seems like inefficient code: running the same code in each for loop but need
		// different things for each?

		// checks the options to the right upper
		for (int i = pos - 7; !(i < 0) && i % 8 != 0; i -= 7) {
			if (!this.getBoard().isPieceAt(i)) {
				addMove(i, moves);
			} else if (this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				addMove(i, moves);
				break;
			} else {
				break;
			}

		}

		// checks options to the bottom left
		for (int i = pos + 7; !(i > 63) && i % 8 != 7; i += 7) {
			if (!this.getBoard().isPieceAt(i)) {
				addMove(i, moves);
			} else if (this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				addMove(i, moves);
				break;
			} else {
				break;
			}
		}

		// checks options left upper
		for (int i = pos - 9; !(i < 0) && i % 8 != 7; i -= 9) {
			if (!this.getBoard().isPieceAt(i)) {
				addMove(i, moves);
			} else if (this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				addMove(i, moves);
				break;
			} else {
				break;
			}
		}

		// checks options right below
		for (int i = pos + 9; !(i > 63) && i % 8 != 0; i += 9) {
			if (!this.getBoard().isPieceAt(i)) {
				addMove(i, moves);
			} else if (this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				addMove(i, moves);
				break;
			} else {
				break;
			}
		}

		return moves;
	}

}
