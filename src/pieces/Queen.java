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

		moves.addAll(new Bishop(this.getAlliance(), this.getBoard(), this.getPosition(), myKing).calculateLegalMoves());
		moves.addAll(new Rook(this.getAlliance(), this.getBoard(), this.getPosition(), myKing).calculateLegalMoves());

		return moves;
	}

}
