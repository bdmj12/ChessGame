package gameplay;

import javax.swing.JPanel;

import gui.Board;
import gui.Gui;
import pieces.Alliance;
import pieces.Piece;

public class Game {

	private static Player whitePlayer;
	private static Player blackPlayer;
	private Gui gui;

	public static Alliance turn;

	private static boolean isGameOver = false;

	private static Board board;

	public Game() {

		isGameOver = false;

		board = new Board();

		board.setGame(this);

		gui = new Gui(board);

		whitePlayer = new Player(board, Alliance.WHITE);
		blackPlayer = new Player(board, Alliance.BLACK);

		turn = Alliance.WHITE;

		// adds the pieces to the board

		for (Piece piece : whitePlayer.getActivePieces()) {
			piece.setMyPlayer(whitePlayer);
			piece.setEnemyPlayer(blackPlayer);
			board.getChessboard()[piece.getRow()][piece.getCol()].setPiece(piece);

		}
		for (Piece piece : blackPlayer.getActivePieces()) {
			piece.setMyPlayer(blackPlayer);
			piece.setEnemyPlayer(whitePlayer);
			board.getChessboard()[piece.getRow()][piece.getCol()].setPiece(piece);
		}

		// update labels without checking for check and checkmate
		Gui.updateLabels(false);

	}

	public static boolean isGameOver() {
		return isGameOver;
	}

	public static boolean isCheckMate() {
		// if not in testMode, this checks whether
		// the active player has any valid moves
		// otherwise ---> checkmate!

		if (Board.testMode == false) {
			if (turn == Alliance.WHITE) {
				for (Piece piece : whitePlayer.getActivePieces()) {
					if (!piece.calculateLegalMoves().isEmpty()) {

						return false;
					}
				}
				isGameOver = true;
				return true;
			} else {
				for (Piece piece : blackPlayer.getActivePieces()) {
					if (!piece.calculateLegalMoves().isEmpty()) {
						return false;
					}
				}

				isGameOver = true;
				return true;

			}
		}
		return false;

	}

	public static void changeTurn() {
		if (turn == Alliance.WHITE) {
			turn = Alliance.BLACK;
		} else {
			turn = Alliance.WHITE;
		}
	}

	public JPanel getBoard() {
		return board;
	}

	// this method is to check whether to update the labels or not
	public static boolean inCheck() {
		if (Board.testMode == false) {
			if (turn == Alliance.WHITE) {
				for (Piece piece : blackPlayer.getActivePieces()) {
					if (piece.calculateLegalMoves()
							.contains(board.getChessboard()[whitePlayer.getMyKing().getRow()][whitePlayer.getMyKing()
									.getCol()])) {
						return true;
					}
				}
				return false;
			} else {
				for (Piece piece : whitePlayer.getActivePieces()) {
					if (piece.calculateLegalMoves()
							.contains(board.getChessboard()[blackPlayer.getMyKing().getRow()][blackPlayer.getMyKing()
									.getCol()])) {
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}

}
