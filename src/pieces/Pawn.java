package pieces;

import java.util.ArrayList;

import gui.Board;

public class Pawn extends Piece {

	private Type type;

	public Pawn(Alliance alliance, Board board, int position, King king) {
		super(alliance, board, position, king);
		this.type = Type.PAWN;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Integer> calculateLegalMoves() {
		ArrayList<Integer> moves = new ArrayList<>();
		int pos = this.getPosition();
		if (this.getAlliance() == Alliance.BLACK) {

			if (pos + 8 < 63) {
				if (!this.getBoard().isPieceAt(pos + 8)) {
					addMove(pos + 8, moves);
				}
			}
			if (pos > 7 && pos < 16) {
				if (!this.getBoard().isPieceAt(pos + 16)) {
					addMove(pos + 16, moves);
				}
			}
			// capturing
			for (int i = -1; i < 2; i++) {
				if (i != 0 && this.getBoard().isPieceAt(pos + 8 + i)
						&& this.getBoard().getAllianceAt(pos + 8 + i) != this.getAlliance()) {
					addMove(pos + 8 + i, moves);
				}
			}

		} else {
			if (pos - 8 >= 0) {
				if (!this.getBoard().isPieceAt(pos - 8)) {
					addMove(pos - 8, moves);
				}
			}
			if (pos < 56 && pos > 47) {
				if (!this.getBoard().isPieceAt(pos - 16)) {
					addMove(pos - 16, moves);
				}
			}
			// capturing
			for (int i = -1; i < 2; i++) {
				if (i != 0 && this.getBoard().isPieceAt(pos - 8 + i)
						&& this.getBoard().getAllianceAt(pos - 8 + i) != this.getAlliance()) {
					addMove(pos - 8 + i, moves);
				}
			}
		}

		return moves;
	}
}
