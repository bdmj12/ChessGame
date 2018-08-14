package gui;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import pieces.Alliance;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Tile extends JButton {

	private final int row;
	private final int col;

	private Piece piece = null;

	private boolean containsPiece = false;

	public static final Color DARK_TILE = new Color(151, 127, 71);
	public static final Color LIGHT_TILE = new Color(208, 207, 109);
	public static final Color POSS_MOVES = new Color(201, 201, 229);

	public Tile(Color color, int row, int col) {
		this.setBackground(color);
		this.row = row;
		this.col = col;
	}

	public boolean isPiece() {
		return containsPiece;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public void clearPiece() {
		this.piece = null;
		this.setIcon(null);
		containsPiece = false;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
		piece.setRow(this.getRow());
		piece.setCol(this.getCol());
		containsPiece = true;

		if (piece instanceof Pawn) {
			if (piece.getAlliance() == Alliance.WHITE) {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/wpawn.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} else {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/bpawn.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}

		if (piece instanceof Bishop) {
			if (piece.getAlliance() == Alliance.WHITE) {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/wbishop.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} else {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/bbishop.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}

		if (piece instanceof Rook) {
			if (piece.getAlliance() == Alliance.WHITE) {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/wrook.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} else {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/brook.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}

		if (piece instanceof Knight) {
			if (piece.getAlliance() == Alliance.WHITE) {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/wknight.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} else {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/bknight.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}

		if (piece instanceof King) {
			if (piece.getAlliance() == Alliance.WHITE) {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/wking.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} else {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/bking.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}

		if (piece instanceof Queen) {
			if (piece.getAlliance() == Alliance.WHITE) {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/wqueen.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} else {
				try {
					Image img = ImageIO.read(this.getClass().getResource("ChessPieces/bqueen.png"));
					this.setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
	}
}
