package pieces;

import java.util.ArrayList;

import gui.Board;

public abstract class Piece {

	private Board board;

	protected King myKing;

	private Alliance alliance;

	private int position;

	private Type type;

	private boolean bool = true;

	public Piece(Alliance alliance, Board board, int position, King king) {
		this.alliance = alliance;
		this.position = position;
		this.board = board;
		this.myKing = king;
	}

	public Piece(Alliance alliance, Board board, int position) {
		this.alliance = alliance;
		this.position = position;
		this.board = board;
	}

	public void setPosition(int newPos) {
		this.position = newPos;
	}

	public boolean isKingInCheck(Board board, int x) {
		// if (bool == true) {
		// if (getBoard().isPieceAt(x)) {
		// // System.out.println(this.getBoard().getPieceAt(x).getPosition());
		// }
		// Piece piece = null;
		// int loc = this.getPosition();
		// if (this.getBoard().isPieceAt(x)) {
		// piece = getBoard().getPieceAt(x);
		// }
		//
		// // move piece to proposed position
		// this.setPosition(x);
		// getBoard().getChessboard().get(x).setPiece(this);
		// getBoard().getChessboard().get(loc).clearPiece();
		//
		// // for every enemy piece
		// for (int i = 0; i < 64; i++) {
		// if (this.getBoard().isPieceAt(i) && this.getBoard().getAllianceAt(i) !=
		// this.getAlliance()) {
		// // if they could take the King
		// if
		// (this.getBoard().getPieceAt(i).calculateLegalMoves().contains(myKing.getPosition()))
		// {
		//
		// // change the piece back
		// this.setPosition(loc);
		// getBoard().getChessboard().get(loc).setPiece(this);
		// getBoard().getChessboard().get(x).clearPiece();
		// if (piece != null) {
		// getBoard().getChessboard().get(x).setPiece(piece);
		// }
		// bool = !bool;
		// return true;
		//
		// }
		// }
		//
		// }
		// // change the piece back
		// this.setPosition(loc);
		//
		// getBoard().getChessboard().get(loc).setPiece(this);
		// getBoard().getChessboard().get(x).clearPiece();
		// if (piece != null) {
		// getBoard().getChessboard().get(x).setPiece(piece);
		// }
		// bool = !bool;
		// return false;
		// }
		// bool = !bool;
		return false;

	}

	public int getPosition() {
		return this.position;
	}

	public Alliance getAlliance() {
		return this.alliance;
	}

	public abstract ArrayList<Integer> calculateLegalMoves();

	public Board getBoard() {
		return board;
	}

	public Type getType() {
		return type;
	}

	public void addMove(int x, ArrayList<Integer> moves) {
		if (!isKingInCheck(this.getBoard(), x)
				&& (!this.getBoard().isPieceAt(x) || this.getBoard().getAllianceAt(x) != this.getAlliance())) {
			moves.add(x);
		}
	}

}
