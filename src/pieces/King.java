package pieces;

import java.util.ArrayList;

import gui.Board;

public class King extends Piece {

	ArrayList<Integer> moves;

	private Type type;

	public King(Alliance alliance, Board board, int position) {
		super(alliance, board, position);
		this.type = Type.KING;
		myKing = this;
		// TODO Auto-generated constructor stub
	}

	// public boolean inCheck(int pos) {
	// int loc = this.getPosition();
	// this.setPosition(100);
	//
	// getBoard().getChessboard().get(loc).clearPiece();
	//
	// for (int i = 0; i < 64; i++) {
	// if (this.getBoard().isPieceAt(i) && this.getBoard().getAllianceAt(i) !=
	// this.getAlliance()
	// && this.getBoard().getPieceAt(i) != this) {
	// if (this.getBoard().getPieceAt(i).calculateLegalMoves().contains(pos)) {
	// this.setPosition(loc);
	// getBoard().getChessboard().get(loc).setPiece(this);
	// return true;
	//
	// }
	// }
	// }
	// getBoard().getChessboard().get(loc).setPiece(this);
	// this.setPosition(loc);
	// return false;
	// }

	@Override
	public void addMove(int x, ArrayList<Integer> moves) {
		if (!isKingInCheck(this.getBoard(), x)
				&& (!this.getBoard().isPieceAt(x) || this.getBoard().getAllianceAt(x) != this.getAlliance())) {
			moves.add(x);
		}
	}

	@Override
	public ArrayList<Integer> calculateLegalMoves() {
		moves = new ArrayList<>();
		int pos = this.getPosition();
		int modPos = pos % 8;

		switch (modPos) {
		case 0:
			if (pos >= 0 && pos < 8) {
				addMove(pos + 1, moves);
				addMove(pos + 8, moves);
				addMove(pos + 9, moves);
			} else if (pos > 55 && pos < 64) {
				addMove(pos + 1, moves);
				addMove(pos - 8, moves);
				addMove(pos - 7, moves);

			} else {
				addMove(pos - 8, moves);
				addMove(pos - 7, moves);
				addMove(pos + 1, moves);
				addMove(pos + 9, moves);
				addMove(pos + 8, moves);

			}
			break;
		case 7:
			if (pos >= 0 && pos < 8) {
				addMove(pos - 1, moves);
				addMove(pos + 8, moves);
				addMove(pos + 7, moves);
			} else if (pos > 55 && pos < 64) {
				addMove(pos - 8, moves);
				addMove(pos - 9, moves);
				addMove(pos - 1, moves);

			} else {
				addMove(pos - 8, moves);
				addMove(pos - 9, moves);
				addMove(pos - 1, moves);
				addMove(pos + 7, moves);
				addMove(pos + 8, moves);

			}
			break;

		default:

			if (pos >= 0 && pos < 8) {
				addMove(pos + 8, moves);
				addMove(pos + 9, moves);
				addMove(pos + 7, moves);
				addMove(pos + 1, moves);
				addMove(pos - 1, moves);

			} else if (pos > 55 && pos < 64) {
				addMove(pos - 8, moves);
				addMove(pos - 9, moves);
				addMove(pos - 7, moves);
				addMove(pos + 1, moves);
				addMove(pos - 1, moves);

			} else {

				addMove(pos - 8, moves);
				addMove(pos - 9, moves);
				addMove(pos - 1, moves);
				addMove(pos + 7, moves);
				addMove(pos + 8, moves);
				addMove(pos + 1, moves);
				addMove(pos + 9, moves);
				addMove(pos - 7, moves);
			}
		}
		return moves;

	}
}
