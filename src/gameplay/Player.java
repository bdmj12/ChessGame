package gameplay;

import java.util.ArrayList;
import java.util.Random;

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

	private ArrayList<Piece> pieces = new ArrayList<>();

	// to simplify code later
	private int rowIndex;

	private Random randomGenerator = new Random();
	private int rand;

	private King myKing;

	public King getMyKing() {
		return myKing;
	}

	// constructor for normal chess
	public Player(Board board, Alliance all) {
		this.alliance = all;

		if (alliance == Alliance.WHITE) {
			rowIndex = 7;
		} else {
			rowIndex = 0;
		}

		myKing = new King(alliance, board, rowIndex, 4);

		// adds King and Queen
		pieces.add(myKing);
		pieces.add(new Queen(alliance, board, rowIndex, 3));

		// adds rooks, knights, bishops
		for (int i = 0; i < 2; i++) {
			pieces.add(new Bishop(alliance, board, rowIndex, 2 + 3 * i));
			pieces.add(new Knight(alliance, board, rowIndex, 1 + 5 * i));
			pieces.add(new Rook(alliance, board, rowIndex, 7 * i));
		}

		// adds pawns
		for (int i = 0; i < 8; i++) {
			pieces.add(new Pawn(alliance, board, (int) Math.round((5.0 / 7.0) * rowIndex + 1), i));
		}

	}

	// this is the constructor for crazy chess (both teams random)
	public Player(Board board, Alliance all, int boardSize) {
		this.alliance = all;

		if (alliance == Alliance.WHITE) {
			rowIndex = boardSize - 1;
		} else {
			rowIndex = 0;
		}

		myKing = new King(alliance, board, rowIndex, boardSize / 2 - 1);

		// adds King and Queen
		pieces.add(myKing);
		pieces.add(new Queen(alliance, board, rowIndex, boardSize / 2));

		// adds rooks, knights, bishops
		for (int i = 0; i < boardSize; i++) {
			if (i != boardSize / 2 && i != boardSize / 2 - 1) {
				rand = randomGenerator.nextInt(3);
				if (rand == 0) {
					pieces.add(new Rook(alliance, board, rowIndex, i));
				}
				if (rand == 1) {
					pieces.add(new Bishop(alliance, board, rowIndex, i));
				}
				if (rand == 2) {
					pieces.add(new Knight(alliance, board, rowIndex, i));
				}
			}
		}

		// adds pawns
		for (int i = 0; i < boardSize; i++) {
			pieces.add(new Pawn(alliance, board, (int) ((boardSize - 3) / ((float) boardSize - 1) * rowIndex + 1), i));
		}

	}

	// this is the constructor for crazy chess (both teams same pieces)
	// ONLY TO GENERATE BLACK PIECES!
	public Player(ArrayList<Piece> whitePlayerPieces, Board board) {
		this.alliance = Alliance.BLACK;
		for (Piece piece : whitePlayerPieces) {
			if (piece instanceof Pawn) {
				this.pieces.add(new Pawn(alliance, board, (Board.BOARD_SIZE - 1 - piece.getRow()), piece.getCol()));
			}
			if (piece instanceof Rook) {
				this.pieces.add(new Rook(alliance, board, (Board.BOARD_SIZE - 1 - piece.getRow()), piece.getCol()));
			}
			if (piece instanceof Bishop) {
				this.pieces.add(new Bishop(alliance, board, (Board.BOARD_SIZE - 1 - piece.getRow()), piece.getCol()));
			}
			if (piece instanceof Knight) {
				this.pieces.add(new Knight(alliance, board, (Board.BOARD_SIZE - 1 - piece.getRow()), piece.getCol()));
			}
			if (piece instanceof Queen) {
				this.pieces.add(new Queen(alliance, board, (Board.BOARD_SIZE - 1 - piece.getRow()), piece.getCol()));
			}
			if (piece instanceof King) {
				myKing = new King(alliance, board, (Board.BOARD_SIZE - 1 - piece.getRow()), piece.getCol());
				this.pieces.add(myKing);
			}
		}

	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

}
