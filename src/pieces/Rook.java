package pieces;

import java.util.ArrayList;

import gui.Board;
import gui.Tile;

public class Rook extends Piece {

	public Rook(Alliance alliance, Board board, int row, int col) {
		super(alliance, board, row, col);
	}

	@Override
	public ArrayList<Tile> calculateLegalMoves() {
		moves = new ArrayList<>();

		for (int i : new int[] { 1, -1 }) {

			// rows
			rookMoves(i, 0);

			// columns

			rookMoves(0, i);

		}

		return moves;
	}

}
