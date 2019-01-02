package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gameplay.Game;
import gameplay.Mode;
import pieces.Alliance;
import pieces.Pawn;
import pieces.Queen;

public class Board extends JPanel implements ActionListener {

	public static int BOARD_SIZE;
	private boolean tilePainter = true;

	private Game game;
	private Gui gui;

	private boolean aiRunning = false;

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	private boolean isTileSelected = false;

	private Tile selectedTile;
	private Tile clickedTile;

	private Tile[][] chessboard;

	public Board(int boardSize) {

		BOARD_SIZE = boardSize;

		chessboard = new Tile[BOARD_SIZE][BOARD_SIZE];

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
						// if we are in test mode OR it is that pieces turn to play
						if (game.getMode() == Mode.TEST || clickedTile.getPiece().getAlliance() == Game.turn) {

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

						if (game.getMode() != Mode.TEST) {
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

						// clears the old tile
						selectedTile.clearPiece();

						// check for promotion
						if (clickedTile.getPiece() instanceof Pawn) {
							if (clickedTile.getPiece().getAlliance() == Alliance.WHITE) {
								if (clickedTile.getPiece().getRow() == 0) {
									clickedTile.setPiece(new Queen(Alliance.WHITE, this, clickedTile.getRow(),
											clickedTile.getCol()));
									game.getWhitePlayer().getPieces().add(clickedTile.getPiece());
									game.getAllPieces().add(clickedTile.getPiece());
								}
							} else {
								if (clickedTile.getPiece().getRow() == 1) {
									clickedTile.setPiece(new Queen(Alliance.BLACK, this, clickedTile.getRow(),
											clickedTile.getCol()));
									game.getBlackPlayer().getPieces().add(clickedTile.getPiece());
									game.getAllPieces().add(clickedTile.getPiece());
								}
							}

						}
						game.updatePositions();

						game.numOfMoves++;

						Game.changeTurn();

					}

					isTileSelected = false;
					e.setSource(null);

					// this includes checking for checkmate (via isCheckMate() in Game)
					gui.updateLabels(true);

				}
			}

			if (!aiRunning) {
				aiRunning = true;
				if (Game.turn == game.getWhitePlayer().getAlliance()) {
					// AI move
					if (game.getWhitePlayer().isComputer()) {
						game.getWhitePlayer().computerMove();

					}
				} else if (game.getBlackPlayer().isComputer()) {
					game.getBlackPlayer().computerMove();

				}

				aiRunning = false;
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