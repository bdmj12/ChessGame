package gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import gui.Board;
import gui.Gui;
import pieces.Alliance;
import pieces.Piece;

public class Game implements ActionListener {

	private Player whitePlayer;
	private Player blackPlayer;
	private ArrayList<Piece> allPieces;
	private Gui gui;

	private int newRow;
	private int newCol;

	private static Mode mode;

	public Mode getMode() {
		return mode;
	}

	public static Alliance turn;

	private static boolean isGameOver = false;

	private Board board;

	public int numOfMoves;

	public Game(Gui gui, Mode mode, int boardSize) {

		this.mode = mode;

		numOfMoves = 0;

		isGameOver = false;

		gui.setGame(this);
		this.gui = gui;

		board = gui.getBoard();

		board.setGame(this);

		if (mode == Mode.NORMAL) {

			whitePlayer = new Player(board, Alliance.WHITE);
			blackPlayer = new Player(board, Alliance.BLACK);
		}

		else {
			whitePlayer = new Player(board, Alliance.WHITE, boardSize);
			blackPlayer = new Player(board, Alliance.BLACK, boardSize);
		}

		turn = Alliance.WHITE;

		// adds the pieces to the board

		for (Piece piece : whitePlayer.getPieces()) {
			piece.setMyPlayer(whitePlayer);
			piece.setEnemyPlayer(blackPlayer);
			board.getChessboard()[piece.getRow()][piece.getCol()].setPiece(piece);

		}
		for (Piece piece : blackPlayer.getPieces()) {
			piece.setMyPlayer(blackPlayer);
			piece.setEnemyPlayer(whitePlayer);
			board.getChessboard()[piece.getRow()][piece.getCol()].setPiece(piece);
		}

		// update labels without checking for check and checkmate
		gui.updateLabels(false);

		allPieces = (ArrayList<Piece>) whitePlayer.getPieces().clone();
		allPieces.addAll(blackPlayer.getPieces());

	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public boolean isCheckMate() {
		// if not in testMode, this checks whether
		// the active player has any valid moves
		// otherwise ---> checkmate!

		if (mode != Mode.TEST) {
			if (turn == Alliance.WHITE) {
				for (Piece piece : whitePlayer.getPieces()) {
					if (!piece.calculateLegalMoves().isEmpty()) {

						return false;
					}
				}
				isGameOver = true;
				return true;
			} else {
				for (Piece piece : blackPlayer.getPieces()) {
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
	public boolean inCheck() {
		if (mode != Mode.TEST) {
			if (turn == Alliance.WHITE) {
				for (Piece piece : blackPlayer.getPieces()) {
					if (piece.getRow() != board.BOARD_SIZE + 1) {
						if (piece.calculateLegalMoves()
								.contains(board.getChessboard()[whitePlayer.getMyKing().getRow()][whitePlayer
										.getMyKing().getCol()])) {
							return true;
						}
					}
				}
				return false;
			} else {
				for (Piece piece : whitePlayer.getPieces()) {
					if (piece.getRow() != board.BOARD_SIZE + 1) {
						if (piece.calculateLegalMoves()
								.contains(board.getChessboard()[blackPlayer.getMyKing().getRow()][blackPlayer
										.getMyKing().getCol()])) {
							return true;
						}
					}
				}
				return false;
			}
		}
		return false;
	}

	public void updatePositions() {
		for (Piece piece : allPieces) {
			piece.updatePosition();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (numOfMoves != 0) {

			board.clearBoard();

			for (Piece piece : allPieces) {

				newRow = piece.getPreviousPositions().get(numOfMoves - 1).getRow();
				newCol = piece.getPreviousPositions().get(numOfMoves - 1).getCol();
				if (newRow != board.BOARD_SIZE + 1) {
					board.getChessboard()[newRow][newCol].setPiece(piece);
					piece.setRow(newRow);
					piece.setCol(newCol);

					piece.getPreviousPositions().remove(numOfMoves - 1);

				}
			}

			numOfMoves--;
			changeTurn();
			gui.updateLabels(true);

		}
	}

}
