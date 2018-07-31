package pieces;

import java.util.ArrayList;

import gui.Board;

public class Knight extends Piece {

	private Type type;

	public Knight(Alliance alliance, Board board, int position, King king) {
		super(alliance, board, position, king);
		this.type = Type.KNIGHT;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Integer> calculateLegalMoves() {
		ArrayList<Integer> moves = new ArrayList<>();
		int pos = this.getPosition();

		ArrayList<Integer> row0 = new ArrayList<>();
		ArrayList<Integer> row1 = new ArrayList<>();
		ArrayList<Integer> row6 = new ArrayList<>();
		ArrayList<Integer> row7 = new ArrayList<>();
		ArrayList<Integer> col0 = new ArrayList<>();
		ArrayList<Integer> col1 = new ArrayList<>();
		ArrayList<Integer> col6 = new ArrayList<>();
		ArrayList<Integer> col7 = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			col0.add(8 * i);
			col1.add(8 * i + 1);
			col6.add(8 * i + 6);
			col7.add(8 * i + 7);

			row0.add(i);
			row1.add(i + 8);
			row6.add(i + 48);
			row7.add(i + 56);

		}

		if (!row0.contains(pos) && !row1.contains(pos) && !col0.contains(pos)) {
			addMove(pos - 17, moves);

		}

		if (!row0.contains(pos) && !row1.contains(pos) && !col7.contains(pos)) {
			addMove(pos - 15, moves);

		}

		if (!row0.contains(pos) && !col0.contains(pos) && !col1.contains(pos)) {
			addMove(pos - 10, moves);
		}

		if (!row0.contains(pos) && !col6.contains(pos) && !col7.contains(pos)) {
			addMove(pos - 6, moves);
		}

		if (!row7.contains(pos) && !col0.contains(pos) && !col1.contains(pos)) {
			addMove(pos + 6, moves);
		}

		if (!row6.contains(pos) && !row7.contains(pos) && !col0.contains(pos)) {
			addMove(pos + 15, moves);
		}

		if (!row6.contains(pos) && !row7.contains(pos) && !col7.contains(pos)) {
			addMove(pos + 17, moves);
		}

		if (!row7.contains(pos) && !col6.contains(pos) && !col7.contains(pos)) {
			addMove(pos + 10, moves);
		}

		return moves;

	}
}
