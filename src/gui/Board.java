package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pieces.Alliance;
import pieces.King;
import pieces.Piece;

public class Board implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel2;
	private JLabel leftLabel;
	private JLabel rightLabel;
	private King wKing;
	private King bKing;

	private boolean tilePainter = true;
	private boolean isTileSelected = false;

	private boolean isGameOver = false;
	private Tile selectedTile;
	private Alliance turn = Alliance.WHITE;

	private String wturn = "White's turn.";
	private String bturn = "Black's turn";

	// int i=0;
	private ArrayList<Tile> chessboard = new ArrayList<Tile>();

	public final static int HEIGHT = 675;
	public final static int WIDTH = 600;

	public Board() {
		frame = new JFrame();
		panel = new JPanel(new GridLayout(8, 8));
		panel2 = new JPanel();
		leftLabel = new JLabel(wturn);
		rightLabel = new JLabel();

		for (int i = 0; i < 64; i++) {

			// adds and colours the tiles
			if (tilePainter) {
				chessboard.add(new Tile(Tile.LIGHT_TILE, i));
			} else {
				chessboard.add(new Tile(Tile.DARK_TILE, i));
			}
			if (i % 8 != 7) {
				tilePainter = !tilePainter;
			}

			// adds actionlistener to each tile
			chessboard.get(i).addActionListener(this);

			// adds each tile to panel
			panel.add(chessboard.get(i));
		}

		panel2.setPreferredSize(new Dimension(WIDTH, 40));
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 75));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(leftLabel);
		panel2.add(Box.createRigidArea(new Dimension(WIDTH - 350, 0)));
		panel2.add(rightLabel);
		// text.setHorizontalAlignment(SwingConstants.CENTER);
		leftLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		rightLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));

		// adds panels to frame and builds frame
		frame.getContentPane().add(BorderLayout.NORTH, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel2);

		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void repaintBoard() {

		tilePainter = true;

		for (int i = 0; i < 64; i++) {

			// colours the tiles
			if (tilePainter) {
				chessboard.get(i).setBackground(Tile.LIGHT_TILE);
			} else {
				chessboard.get(i).setBackground(Tile.DARK_TILE);
			}
			if (i % 8 != 7) {
				tilePainter = !tilePainter;
			}

		}

	}

	public void setKings(King wKing, King bKing) {
		this.wKing = wKing;
		this.bKing = bKing;
	}

	public boolean isCheckMate() {
		// test for checkmate

		for (int i = 0; i < 64; i++) {
			if (isPieceAt(i) && getAllianceAt(i) == turn) {
				if (!getPieceAt(i).calculateLegalMoves().isEmpty()) {
					return false;
				}
			}

		}
		isGameOver = true;
		if (turn == Alliance.BLACK) {
			rightLabel.setText("Checkmate! White wins!");
		} else {
			rightLabel.setText("Checkmate! White wins!");
		}
		return true;

	}

	public ArrayList<Tile> getChessboard() {
		return this.chessboard;
	}

	public boolean isPieceAt(int x) {
		return chessboard.get(x).isPiece();
	}

	public Piece getPieceAt(int x) {
		if (chessboard.get(x).isPiece()) {
			return chessboard.get(x).getPiece();
		} else {
			return null;
		}
	}

	public Alliance getAllianceAt(int x) {
		if (chessboard.get(x).isPiece()) {
			return chessboard.get(x).getPiece().getAlliance();
		} else {
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!isGameOver) {

			if (e.getSource() instanceof Tile) {
				Tile clickedTile = (Tile) e.getSource();

				if (!isTileSelected) {
					if (clickedTile.isPiece() && clickedTile.getPiece().getAlliance() == turn
							&& !clickedTile.getPiece().calculateLegalMoves().isEmpty()) {
						selectedTile = clickedTile;
						isTileSelected = true;
						for (Integer move : selectedTile.getPiece().calculateLegalMoves()) {
							chessboard.get(move).setBackground(Tile.POSS_MOVES);
						}
					}
				} else {
					repaintBoard();
					if (selectedTile.getPiece().calculateLegalMoves().contains(clickedTile.getPosition())) {

						// move the piece
						clickedTile.setPiece(selectedTile.getPiece());
						clickedTile.getPiece().setPosition(clickedTile.getPosition());

						// display check in rightLabel if necessary

						selectedTile.clearPiece();

						if (turn == Alliance.WHITE) {
							turn = Alliance.BLACK;
							leftLabel.setText(bturn);
						} else {
							turn = Alliance.WHITE;
							leftLabel.setText(wturn);
						}

						if (turn == Alliance.WHITE) {
							if (wKing.inCheck()) {
								rightLabel.setText("Check!");
							} else {
								rightLabel.setText("");
							}

						} else if (bKing.inCheck()) {
							rightLabel.setText("Check!");
						} else {
							rightLabel.setText("");
						}
					}

					isTileSelected = false;

				}

				e.setSource(null);

				isCheckMate();
			}
		}

	}
}
