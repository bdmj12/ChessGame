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
	private JPanel dialogSubPanel1;
	private JLabel comboLabel1;
	private JLabel comboLabel2;

	private JCheckBox tickBox;

	private JPanel dialogSubPanel2;

	private JButton okButton;
	private JComboBox<Integer> comboBox;

	public JButton aboutButton;
	private JDialog aboutDialog;
	private JLabel aboutLabel;

	private JPanel dialogPanel;
	private int size;

	private JRadioButton normalChess;
	private JRadioButton crazyChess;
	private ButtonGroup buttonGroup;
	private int[] crazyOptions = { 4, 6, 8, 10, 12, 14, 16 };

	private JDialog dialog;

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
		// creates the window

		this.board = board;
		mainFrame = new JFrame();

		aboutButton = new JButton("About");
		aboutButton.addActionListener(this);

		aboutDialog = new JDialog();
		aboutLabel = new JLabel();
		aboutLabel.setPreferredSize(new Dimension(350, 310));
		aboutDialog.add(aboutLabel);
		aboutDialog.setResizable(false);

		// creates the dialog window
		normalChess = new JRadioButton("Normal");
		crazyChess = new JRadioButton("Crazy!  ");
		buttonGroup = new ButtonGroup();
		normalChess.setSelected(true);

		comboBox = new JComboBox<Integer>();
		for (int i = 0; i < (crazyOptions.length); i++) {
			comboBox.addItem(crazyOptions[i]);
		}
		comboBox.setSelectedIndex(3);
		comboBox.setEnabled(false);

		dialogSubPanel1 = new JPanel();
		dialogSubPanel1.setLayout(new BoxLayout(dialogSubPanel1, BoxLayout.X_AXIS));
		comboLabel1 = new JLabel("Size of board:");

		dialogSubPanel2 = new JPanel();
		dialogSubPanel2.setLayout(new BoxLayout(dialogSubPanel2, BoxLayout.X_AXIS));
		comboLabel2 = new JLabel("Players have same pieces:");

		tickBox = new JCheckBox();
		tickBox.setSelected(false);
		tickBox.setEnabled(false);

		dialogSubPanel2.add(comboLabel2);
		dialogSubPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
		dialogSubPanel2.add(tickBox);

		dialogSubPanel1.add(comboLabel1);
		dialogSubPanel1.add(Box.createRigidArea(new Dimension(30, 0)));
		dialogSubPanel1.add(comboBox);

		buttonGroup.add(normalChess);
		buttonGroup.add(crazyChess);

		normalChess.addActionListener(this);
		crazyChess.addActionListener(this);

		okButton = new JButton("OK");

		okButton.addActionListener(this);

		dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

		dialogPanel.add(normalChess);
		dialogPanel.add(crazyChess);
		dialogPanel.add(dialogSubPanel1);
		dialogPanel.add(dialogSubPanel2);
		dialogPanel.add(okButton);

		dialogPanel.setPreferredSize(new Dimension(180, 120));
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

		normalChess.setAlignmentX(dialogPanel.CENTER_ALIGNMENT);
		crazyChess.setAlignmentX(dialogPanel.CENTER_ALIGNMENT);
		okButton.setAlignmentX(dialogPanel.CENTER_ALIGNMENT);

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

			dialog = new JDialog(mainFrame);
			dialog.add(dialogPanel);
			dialog.setTitle("New Game");
			dialog.setResizable(false);
			dialog.setModal(true);
			dialog.pack();
			dialog.setLocationRelativeTo(mainFrame);

			dialog.setVisible(true);

		} else if (e.getSource() == okButton) {
			board.clearBoard();
			mainFrame.remove(board);
			board = null;

			if (normalChess.isSelected()) {
				board = new Board(8);
				board.setGui(this);
				mainFrame.getContentPane().add(BorderLayout.CENTER, board);

				game = new Game(this, Mode.NORMAL, 8);
				dialog.dispose();
			} else {
				size = (int) comboBox.getSelectedItem();
				board = new Board(size);
				board.setGui(this);
				mainFrame.getContentPane().add(BorderLayout.CENTER, board);

				if (tickBox.isSelected()) {
					game = new Game(this, Mode.CRAZY_SAME, size);
				} else {
					game = new Game(this, Mode.CRAZY, size);
				}
				dialog.dispose();
			}
		} else {
			if (normalChess.isSelected()) {
				comboBox.setEnabled(false);
				tickBox.setEnabled(false);
				;
			} else {
				comboBox.setEnabled(true);
				tickBox.setEnabled(true);

			}
		}
	}
}