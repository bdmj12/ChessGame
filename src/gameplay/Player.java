package gameplay;

import java.util.ArrayList;

import gui.Board;
import pieces.Alliance;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Player {

	private Alliance alliance;

	private ArrayList<Piece> activePieces = new ArrayList<>();
	private ArrayList<Piece> capturedPieces = new ArrayList<>();

	// to simplify code later
	private int rowIndex;

	private Board board;

	private King myKing;

	public King getMyKing() {
		return myKing;
	}

	public Player(Board board, Alliance all) {
		this.board = board;
		this.alliance = all;

		if (alliance == Alliance.WHITE) {
			rowIndex = 7;
		} else {
			rowIndex = 0;
		}

		myKing = new King(alliance, board, rowIndex, 4);

		// adds King and Queen
		activePieces.add(myKing);
		activePieces.add(new Queen(alliance, board, rowIndex, 3));

		// adds rooks, knights, bishops
		for (int i = 0; i < 2; i++) {
			activePieces.add(new Bishop(alliance, board, rowIndex, 2 + 3 * i));
			activePieces.add(new Knight(alliance, board, rowIndex, 1 + 5 * i));
			activePieces.add(new Rook(alliance, board, rowIndex, 7 * i));
		}

		// adds pawns
		for (int i = 0; i < 8; i++) {
			activePieces.add(new Pawn(alliance, board, (int) Math.round((5.0 / 7.0) * rowIndex + 1), i));
		}

	}

	public ArrayList<Piece> getActivePieces() {
		return activePieces;
	}

	public void setActivePieces(ArrayList<Piece> activePieces) {
		this.activePieces = activePieces;
	}

	public ArrayList<Piece> getCapturedPieces() {
		return capturedPieces;
	}

	public void setCapturedPieces(ArrayList<Piece> capturedPieces) {
		this.capturedPieces = capturedPieces;
	}

}
