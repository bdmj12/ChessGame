package pieces;

import java.util.ArrayList;

import gui.Board;

public abstract class Piece {

	private Board board;

	protected King myKing;

	static boolean isKingInCheckRunning = false;

	private Alliance alliance;

	private int position;

	private Type type;

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

	public boolean isKingInCheck(Board board, int proposedMove) {
		isKingInCheckRunning = true;

		Piece piece = null;
		int loc = this.getPosition();
		if (this.getBoard().isPieceAt(proposedMove)) {
			piece = getBoard().getPieceAt(proposedMove);
		}

		// move piece to proposed position
		this.setPosition(proposedMove);
		getBoard().getChessboard().get(proposedMove).setPiece(this);
		getBoard().getChessboard().get(loc).clearPiece();

		// for every enemy piece
		for (int i = 0; i < 64; i++) {
			if (this.getBoard().isPieceAt(i) && this.getBoard().getAllianceAt(i) != this.getAlliance()) {
				// if they could take the King
				if (this.getBoard().getPieceAt(i).calculateLegalMoves().contains(myKing.getPosition())) {

					// change the piece back
					this.setPosition(loc);
					getBoard().getChessboard().get(loc).setPiece(this);
					getBoard().getChessboard().get(proposedMove).clearPiece();
					if (piece != null) {
						getBoard().getChessboard().get(proposedMove).setPiece(piece);
					}
					isKingInCheckRunning = false;

					return true;

				}
			}

		}

		// change the piece back
		this.setPosition(loc);

		getBoard().getChessboard().get(loc).setPiece(this);
		getBoard().getChessboard().get(proposedMove).clearPiece();
		if (piece != null) {
			getBoard().getChessboard().get(proposedMove).setPiece(piece);
		}
		isKingInCheckRunning = false;
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

	public King getMyKing() {
		return myKing;
	}

	public void addMove(int x, ArrayList<Integer> moves) {
		if (!isKingInCheckRunning) {
			if (!isKingInCheck(this.getBoard(), x)
					&& (!this.getBoard().isPieceAt(x) || this.getBoard().getAllianceAt(x) != this.getAlliance())) {
				moves.add(x);
			}
		} else if (!this.getBoard().isPieceAt(x) || this.getBoard().getAllianceAt(x) != this.getAlliance()) {
			moves.add(x);
		}
	}
}
