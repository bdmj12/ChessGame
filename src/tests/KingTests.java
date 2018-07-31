package tests;

import gui.Board;
import pieces.Alliance;
import pieces.Bishop;
import pieces.King;
import pieces.Queen;
import pieces.Rook;

public class KingTests {

	static King wKing;
	static King bKing;

	public static void main(String[] args) {

		int pos = 35;

		Board board = new Board();
		King wking = new King(Alliance.WHITE, board, pos);
		board.getChessboard().get(pos).setPiece(wking);

		King bking = new King(Alliance.BLACK, board, 3);
		board.getChessboard().get(3).setPiece(bking);

		board.setKings(wking, bking);

		Bishop b = new Bishop(Alliance.WHITE, board, 46, wking);
		board.getChessboard().get(46).setPiece(b);

		Rook r = new Rook(Alliance.BLACK, board, 62, bking);
		board.getChessboard().get(62).setPiece(r);

		Queen q = new Queen(Alliance.BLACK, board, 12, bking);
		board.getChessboard().get(12).setPiece(q);

		// board.chessboard.get(36).setPiece(new Rook(Alliance.WHITE,board,36));

	}

}
