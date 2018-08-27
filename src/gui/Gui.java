package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import gameplay.Game;
import gameplay.Mode;
import pieces.Alliance;

public class Gui implements ActionListener {

	private JFrame mainFrame;
	private JPanel lowerPanel;
	private static JLabel turnLabel;
	private static JLabel checkLabel;

	public JButton aboutButton;
	private JDialog aboutDialog;
	private JLabel aboutLabel;

	private JDialog newGameDialog;
	private JPanel newGamePanel;
	private JPanel player1Panel;
	private JPanel player2Panel;

	private JPanel normalCrazyPanel;
	private JPanel boardSizePanel;
	private JPanel checkboxPanel;

	private JCheckBox checkBox;
	private JLabel sizeLabel;
	private JLabel samePiecesLabel;
	private JLabel player1;
	private JLabel player2;

	private JComboBox<String> player1Combo;
	private JComboBox<String> player2Combo;

	private JButton okButton;
	private JComboBox<Integer> sizeComboBox;

	private int size;
	private boolean isWhiteComp;
	private boolean isBlackComp;

	private JRadioButton normalChess;
	private JRadioButton crazyChess;
	private ButtonGroup buttonGroup;
	private int[] crazyOptions = { 4, 6, 8, 10, 12, 14, 16 };

	private Board board; // extends JPanel!

	private Game game;

	public void setGame(Game game) {
		this.game = game;
		undo.addActionListener(game);
	}

	private static String wturn = "White's Turn.";
	private static String bturn = "Black's Turn.";

	private JMenuBar menuBar;
	private JButton undo;
	public JButton newGame;

	public final static int WIDTH = 600;
	public final static int HEIGHT = 675;

	public Gui(Board board) {
		// creates the frame
		this.board = board;
		mainFrame = new JFrame();

		// about dialog

		aboutDialog = new JDialog();
		aboutLabel = new JLabel();
		aboutLabel.setPreferredSize(new Dimension(350, 310));
		aboutDialog.add(aboutLabel);
		aboutDialog.setResizable(false);

		aboutButton = new JButton("About");
		aboutButton.addActionListener(this);

		// creates the newGame dialog panel

		player1 = new JLabel("Player 1:   ");
		player2 = new JLabel("Player 2:   ");

		player1Combo = new JComboBox<String>();
		player1Combo.addItem("Human");
		player1Combo.addItem("Computer");
		player1Combo.setSelectedIndex(0);

		player2Combo = new JComboBox<String>();
		player2Combo.addItem("Human");
		player2Combo.addItem("Computer");
		player2Combo.setSelectedIndex(0);

		player1Panel = new JPanel();
		player2Panel = new JPanel();

		player1Panel.setLayout(new BoxLayout(player1Panel, BoxLayout.X_AXIS));
		player2Panel.setLayout(new BoxLayout(player2Panel, BoxLayout.X_AXIS));

		player1Panel.add(player1);
		player1Panel.add(Box.createRigidArea(new Dimension(49, 0)));
		player1Panel.add(player1Combo);

		player2Panel.add(player2);
		player2Panel.add(Box.createRigidArea(new Dimension(49, 0)));
		player2Panel.add(player2Combo);

		normalChess = new JRadioButton("Normal");
		crazyChess = new JRadioButton("Crazy!  ");
		buttonGroup = new ButtonGroup();
		normalChess.setSelected(true);

		normalCrazyPanel = new JPanel();
		normalCrazyPanel.setLayout(new BoxLayout(normalCrazyPanel, BoxLayout.X_AXIS));

		normalCrazyPanel.add(normalChess);
		normalCrazyPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		normalCrazyPanel.add(crazyChess);

		boardSizePanel = new JPanel();
		boardSizePanel.setLayout(new BoxLayout(boardSizePanel, BoxLayout.X_AXIS));

		sizeComboBox = new JComboBox<Integer>();
		for (int i = 0; i < (crazyOptions.length); i++) {
			sizeComboBox.addItem(crazyOptions[i]);
		}
		sizeComboBox.setSelectedIndex(3);
		sizeComboBox.setEnabled(false);

		sizeLabel = new JLabel("Size of board:");

		boardSizePanel.add(sizeLabel);
		boardSizePanel.add(Box.createRigidArea(new Dimension(30, 0)));
		boardSizePanel.add(sizeComboBox);

		checkboxPanel = new JPanel();

		samePiecesLabel = new JLabel("Players have same pieces:");

		checkBox = new JCheckBox();
		checkBox.setSelected(false);
		checkBox.setEnabled(false);

		checkboxPanel.add(samePiecesLabel);
		checkboxPanel.add(checkBox);

		buttonGroup.add(normalChess);
		buttonGroup.add(crazyChess);

		normalChess.addActionListener(this);
		crazyChess.addActionListener(this);

		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setAlignmentX(newGameDialog.CENTER_ALIGNMENT);

		newGamePanel = new JPanel();
		newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.Y_AXIS));
		newGamePanel.setPreferredSize(new Dimension(190, 160));

		newGamePanel.add(normalCrazyPanel);
		newGamePanel.add(player1Panel);
		newGamePanel.add(player2Panel);
		newGamePanel.add(boardSizePanel);
		newGamePanel.add(checkboxPanel);
		newGamePanel.add(okButton);

		board.setGui(this);

		newGame = new JButton("New Game");
		undo = new JButton("Undo");

		newGame.addActionListener(this);

		menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setTitle("Crazy Chess!");
		menuBar.add(aboutButton);
		menuBar.add(newGame);
		menuBar.add(undo);

		lowerPanel = new JPanel();
		turnLabel = new JLabel();
		checkLabel = new JLabel();

		lowerPanel.setPreferredSize(new Dimension(WIDTH, 40));

		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		lowerPanel.add(turnLabel);
		lowerPanel.add(Box.createRigidArea(new Dimension(WIDTH - 350, 0)));
		lowerPanel.add(checkLabel);

		turnLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		checkLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));

		// adds panels to frame and builds frame

		mainFrame.getContentPane().add(BorderLayout.CENTER, board);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, lowerPanel);

		mainFrame.setSize(WIDTH, HEIGHT);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public Board getBoard() {
		return board;
	}

	public void updateLabels(boolean bool) {

		// turn label
		if (Game.turn == Alliance.WHITE) {
			turnLabel.setText(wturn);
		} else {
			turnLabel.setText(bturn);
		}

		// test for check & checkmate
		if (bool) {
			if (game.isCheckMate()) {
				checkLabel.setText("Checkmate!");
			}

			else if (game.inCheck()) {
				checkLabel.setText("Check!");
			} else {
				checkLabel.setText("");
			}
		}

	}

	// new Game
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == aboutButton) {
			aboutDialog.setTitle("About");
			aboutDialog.setModal(false);
			aboutLabel.setText("<html> "
					+ "<p>This is a simple chess program for two human players (maybe I'll add AI capability soon!). "
					+ "You can either play in normal mode, or in crazy mode. In crazy mode, you select the size of "
					+ "the board you would like to play on, and then random pieces (Rook, Bishop or Knight) are "
					+ "generated to fill the back row of each player (the King and Queen are always fixed)."
					+ "If the checkbox is selected the players will have identical pieces, otherwise"
					+ " both players pieces will be random.</p><br>"
					+ " <p> The program includes features such as checking for check and checkmate, "
					+ "promoting pawns to queens, and allowing players to undo moves (no redo at the moment).<br>"
					+ " At this stage the game does not allow castling or en passant - to be added!</p><br>"
					+ "<p>Thanks, hope you enjoy! <br><br>" + "© Benjamin Jones 2018 </p> </html>");

			aboutDialog.pack();
			aboutDialog.setLocationRelativeTo(mainFrame);
			aboutDialog.setVisible(true);

		}

		else if (e.getSource() == newGame) {

			newGameDialog = new JDialog(mainFrame);
			newGameDialog.add(newGamePanel);
			newGameDialog.setTitle("New Game");
			newGameDialog.setResizable(false);
			newGameDialog.setModal(true);
			newGameDialog.pack();
			newGameDialog.setLocationRelativeTo(mainFrame);

			newGameDialog.setVisible(true);

		} else if (e.getSource() == okButton) {
			board.clearBoard();
			mainFrame.remove(board);
			board = null;

			if (player1Combo.getSelectedIndex() == 0) {
				isWhiteComp = false;
			} else {
				isWhiteComp = true;
			}

			if (player2Combo.getSelectedIndex() == 0) {
				isBlackComp = false;
			} else {
				isBlackComp = true;
			}

			if (normalChess.isSelected()) {
				board = new Board(8);
				board.setGui(this);
				mainFrame.getContentPane().add(BorderLayout.CENTER, board);

				game = new Game(this, Mode.NORMAL, 8, isWhiteComp, isBlackComp);
				newGameDialog.dispose();
			} else {
				size = (int) sizeComboBox.getSelectedItem();
				board = new Board(size);
				board.setGui(this);
				mainFrame.getContentPane().add(BorderLayout.CENTER, board);

				if (checkBox.isSelected()) {
					game = new Game(this, Mode.CRAZY_SAME, size, isWhiteComp, isBlackComp);
				} else {
					game = new Game(this, Mode.CRAZY, size, isWhiteComp, isBlackComp);
				}
				newGameDialog.dispose();
			}
		} else {
			if (normalChess.isSelected()) {
				sizeComboBox.setEnabled(false);
				checkBox.setEnabled(false);
				;
			} else {
				sizeComboBox.setEnabled(true);
				checkBox.setEnabled(true);

			}
		}
	}
}