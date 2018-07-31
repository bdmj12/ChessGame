package gameplay;

import gui.Board;
import pieces.Alliance;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Game {

	Board board;

	public void buildGame() {

		board = new Board();

		// adds the pieces to the tiles

		// kings and queens

		King bKing = new King(Alliance.BLACK, board, 4);
		King wKing = new King(Alliance.WHITE, board, 60);

		board.getChessboard().get(4).setPiece(bKing);
		board.getChessboard().get(3).setPiece(new Queen(Alliance.BLACK, board, 3, bKing));

		board.getChessboard().get(60).setPiece(wKing);
		board.getChessboard().get(59).setPiece(new Queen(Alliance.WHITE, board, 59, wKing));

		// pawns
		for (int i = 0; i < 8; i++) {
			board.getChessboard().get(i + 8).setPiece(new Pawn(Alliance.BLACK, board, i + 8, bKing));
			board.getChessboard().get(63 - i - 8).setPiece(new Pawn(Alliance.WHITE, board, i + 8, wKing));
		}

		// rooks,knight,bishops
		for (int j = 0; j < 2; j++) {
			board.getChessboard().get(7 * j).setPiece(new Rook(Alliance.BLACK, board, 7 * j, bKing));
			board.getChessboard().get(5 * j + 1).setPiece(new Knight(Alliance.BLACK, board, 5 * j + 1, bKing));
			board.getChessboard().get(3 * j + 2).setPiece(new Bishop(Alliance.BLACK, board, 3 * j + 2, bKing));

			board.getChessboard().get(63 - 7 * j).setPiece(new Rook(Alliance.WHITE, board, 63 - 7 * j, wKing));
			board.getChessboard().get(63 - 5 * j - 1)
					.setPiece(new Knight(Alliance.WHITE, board, 63 - 5 * j - 1, wKing));
			board.getChessboard().get(63 - 3 * j - 2)
					.setPiece(new Bishop(Alliance.WHITE, board, 63 - 3 * j - 2, wKing));
		}

	}

}