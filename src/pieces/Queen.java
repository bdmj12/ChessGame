package pieces;

import java.util.ArrayList;

import gui.Board;

public class Queen extends Piece {

	private Type type;

	public Queen(Alliance alliance, Board board, int position, King king) {
		super(alliance, board, position, king);

		this.type = Type.QUEEN;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Integer> calculateLegalMoves() {

		ArrayList<Integer> moves = new ArrayList<>();
		int pos = this.getPosition();

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
