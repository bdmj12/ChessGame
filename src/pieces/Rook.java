package pieces;

import java.util.ArrayList;

import gui.Board;

public class Rook extends Piece {

	private Type type;

	public Rook(Alliance alliance, Board board, int position, King king) {
		super(alliance, board, position, king);
		this.type = Type.ROOK;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Integer> calculateLegalMoves() {
		ArrayList<Integer> moves = new ArrayList<>();
		int pos = this.getPosition();

		// seems like inefficient code: running the same code in each for loop but need
		// different things for each?

		// checks the options to the right
		for (int i = pos + 1; i % 8 != 0 && pos % 8 != 7; i++) {
			if (!this.getBoard().isPieceAt(i)) {
				addMove(i, moves);
			} else if (this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				addMove(i, moves);
				break;
			} else {
				break;
			}

		}

		// checks options to the left
		for (int i = pos - 1; i % 8 != 7 && pos % 8 != 0; i--) {
			if (!this.getBoard().isPieceAt(i)) {
				addMove(i, moves);
			} else if (this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				addMove(i, moves);
				break;
			} else {
				break;
			}
		}

		// checks options above
		for (int i = pos - 8; i > 0; i -= 8) {
			if (!this.getBoard().isPieceAt(i)) {
				addMove(i, moves);
			} else if (this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				addMove(i, moves);
				break;
			} else {
				break;
			}
		}

		// checks options below
		for (int i = pos + 8; i < 64; i += 8) {
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
