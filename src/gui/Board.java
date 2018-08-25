package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gameplay.Game;

public class Board extends JPanel implements ActionListener {

	public static final int BOARD_SIZE = 8;
	private boolean tilePainter = true;

	private Game game;
	private Gui gui;

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	private boolean isTileSelected = false;

	// eg. we can add individual pieces to the board in testMode
	public static boolean testMode = false;

	private Tile selectedTile;
	private Tile clickedTile;

	private Tile[][] chessboard = new Tile[BOARD_SIZE][BOARD_SIZE];

	public Board() {

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT - 75));

		this.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {

				// adds tiles to chessboard array
				if (tilePainter) {
					chessboard[i][j] = new Tile(Tile.LIGHT_TILE, i, j);
				} else {
					chessboard[i][j] = new Tile(Tile.DARK_TILE, i, j);
				}
				if (j != BOARD_SIZE - 1) {
					tilePainter = !tilePainter;
				}

				// adds actionListener to each tile
				chessboard[i][j].addActionListener(this);

				// adds each tile to the panel (this)
				this.add(chessboard[i][j]);
			}
		}
	}

	private void repaintBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {

				// repaints tiles (eg. after highlighting possible moves)
				if (tilePainter) {
					chessboard[i][j].setBackground(Tile.LIGHT_TILE);
				} else {
					chessboard[i][j].setBackground(Tile.DARK_TILE);
				}
				if (j != BOARD_SIZE - 1) {
					tilePainter = !tilePainter;
				}

			}
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Tile[][] getChessboard() {
		return chessboard;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!game.isGameOver()) {
			if (e.getSource() instanceof Tile) {
				clickedTile = (Tile) e.getSource();

				if (!isTileSelected) {
					// if theres a tile there with non-empty possible moves
					if (clickedTile.isPiece() && !clickedTile.getPiece().calculateLegalMoves().isEmpty()) {
						// if we are in text mode OR it is that pieces turn to play
						if (testMode == true || clickedTile.getPiece().getAlliance() == Game.turn) {

							// selects the tile and displays possible moves
							selectedTile = clickedTile;
							isTileSelected = true;
							for (Tile move : selectedTile.getPiece().calculateLegalMoves()) {
								move.setBackground(Tile.POSS_MOVES);
							}
						}
					}
					// otherwise nothing happens
				} else {

					// i.e. repaint the possibleMoves tiles
					repaintBoard();

					if (selectedTile.getPiece().calculateLegalMoves().contains(clickedTile)) {

						if (Board.testMode == false) {
							if (clickedTile.isPiece()) {

								// set capturedPiece position to off the board
								clickedTile.getPiece().setRow(BOARD_SIZE + 1);
								clickedTile.getPiece().setCol(BOARD_SIZE + 1);

							}
						}

						// move the piece

						clickedTile.setPiece(selectedTile.getPiece());
						clickedTile.getPiece().setRow(clickedTile.getRow());
						clickedTile.getPiece().setCol(clickedTile.getCol());
						game.updatePositions();

						// clears the old tile
						selectedTile.clearPiece();

						game.numOfMoves++;

						Game.changeTurn();

					}

					isTileSelected = false;

					e.setSource(null);

					// this includes checking for checkmate (via isCheckMate() in Game)
					gui.updateLabels(true);

				}
			}

		}
	}

	public void clearBoard() {
		for (int i = 0; i < Board.BOARD_SIZE; i++) {
			for (int j = 0; j < Board.BOARD_SIZE; j++) {
				chessboard[i][j].clearPiece();
			}
		}

	}

}