package pieces;

import java.util.ArrayList;

import gameplay.Player;
import gui.Board;
import gui.Tile;

public abstract class Piece {

	protected Alliance alliance;
	protected int row;
	protected int col;
	protected Board board;
	protected ArrayList<Tile> moves;
	public static boolean isKingInCheckRunning = false;
	protected Player myPlayer;
	protected Player enemyPlayer;

	public Player getMyPlayer() {
		return myPlayer;
	}

	public void setMyPlayer(Player myPlayer) {

		this.myPlayer = myPlayer;
	}

	public Player getEnemyPlayer() {
		return enemyPlayer;
	}

	public void setEnemyPlayer(Player enemyPlayer) {
		this.enemyPlayer = enemyPlayer;
	}

	public Piece(Alliance alliance, Board board, int row, int col) {
		this.alliance = alliance;
		this.board = board;
		this.row = row;
		this.col = col;
	}

	public abstract ArrayList<Tile> calculateLegalMoves();

	public Board getBoard() {
		return board;
	}

	public Tile getTileAt(int x, int y) {
		return this.getBoard().getChessboard()[x][y];
	}

	public Alliance getAlliance() {
		return alliance;
	}

	public void setRow(int row) {
		this.row = row;

	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isPieceAt(int row, int col) {
		return this.board.getChessboard()[row][col].isPiece();
	}

	public Piece getPieceAt(int row, int col) {
		return this.board.getChessboard()[row][col].getPiece();
	}

	public Alliance getAllianceAt(int row, int col) {
		return this.board.getChessboard()[row][col].getPiece().getAlliance();
	}

	// checks if a coordinate is on the board (used for calculating possible moves)
	protected boolean withinRange(int x, int y) {
		if (x >= 0 && x < board.BOARD_SIZE && y >= 0 && y < board.BOARD_SIZE) {
			return true;
		} else {
			return false;
		}
	}

	protected void rookMoves(int x, int y) {

		for (int k = 1; k < board.BOARD_SIZE; k++) {

			if (withinRange(row + k * x, col + k * y)) {

				if (!isPieceAt(row + k * x, col + k * y)) {
					addMove(row + k * x, col + k * y);
				} else if (getAllianceAt(row + k * x, col + k * y) != alliance) {
					addMove(row + k * x, col + k * y);
					break;
				} else {
					break;
				}

			}
		}

	}

	public boolean isKingInCheck(int proposedRow, int proposedCol) {
		if (myPlayer != null) {

			// isKingInCheckRunning is to avoid an infinite sequence of pieces running this
			// method!
			isKingInCheckRunning = true;

			int currentRow = this.row;
			int currentCol = this.col;

			Piece piece = null;
			if (isPieceAt(proposedRow, proposedCol)) {
				piece = getPieceAt(proposedRow, proposedCol);

				piece.setRow(board.BOARD_SIZE + 1);
				piece.setCol(board.BOARD_SIZE + 1);

			}

			// move piece to proposed position
			this.row = proposedRow;
			this.col = proposedCol;
			board.getChessboard()[proposedRow][proposedCol].setPiece(this);
			board.getChessboard()[currentRow][currentCol].clearPiece();

			// for every enemy piece
			for (Piece enemyPiece : enemyPlayer.getActivePieces()) {
				// if they could take the King
				if (enemyPiece.calculateLegalMoves().contains(
						board.getChessboard()[myPlayer.getMyKing().getRow()][myPlayer.getMyKing().getCol()])) {

					// change the piece back
					this.row = currentRow;
					this.col = currentCol;
					getBoard().getChessboard()[this.row][this.col].setPiece(this);
					getBoard().getChessboard()[proposedRow][proposedCol].clearPiece();
					if (piece != null) {
						getBoard().getChessboard()[proposedRow][proposedCol].setPiece(piece);
						piece.setRow(proposedRow);
						piece.setCol(proposedRow);
					}
					isKingInCheckRunning = false;

					return true;

				}

			}
			// change the piece back
			this.row = currentRow;
			this.col = currentCol;
			getBoard().getChessboard()[this.row][this.col].setPiece(this);
			getBoard().getChessboard()[proposedRow][proposedCol].clearPiece();
			if (piece != null) {
				getBoard().getChessboard()[proposedRow][proposedCol].setPiece(piece);
			}
			isKingInCheckRunning = false;

			return false;
		}
		return false;
	}

	public void addMove(int x, int y) {
		if (!isKingInCheckRunning) {
			if (!isKingInCheck(x, y)) {
				moves.add(getTileAt(x, y));
			}
		} else {
			moves.add(getTileAt(x, y));
		}
	}

}
