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

	private ActionListener newGameAL;

	private JButton okButton;
	private JButton cancelButton;

	private JPanel dialogPanel;

	private JRadioButton normalChess;
	private JRadioButton crazyChess;
	private ButtonGroup buttonGroup;

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
	private JButton newGame;

	public final static int WIDTH = 600;
	public final static int HEIGHT = 675;

	public Gui() {
		// creates the window
		mainFrame = new JFrame();

		// creates the dialog window
		normalChess = new JRadioButton("Normal.");
		crazyChess = new JRadioButton("Crazy!");
		buttonGroup = new ButtonGroup();

		buttonGroup.add(normalChess);
		buttonGroup.add(crazyChess);

		okButton = new JButton("OK");

		okButton.addActionListener(this);

		dialogPanel = new JPanel();

		dialogPanel.add(normalChess);
		dialogPanel.add(crazyChess);
		dialogPanel.add(okButton);

		dialogPanel.setLocation(WIDTH / 2, HEIGHT / 2);

		board = new Board(8);
		board.setGui(this);

		newGame = new JButton("New Game");
		undo = new JButton("Undo");

		newGame.addActionListener(this);

		menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
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

		if (e.getSource() == newGame) {

			dialog = new JDialog(mainFrame);
			dialog.add(dialogPanel);
			dialog.setTitle("New Game");

			dialog.setModal(true);
			dialog.pack();
			dialog.setVisible(true);

		} else {
			mainFrame.remove(board);

			if (normalChess.isSelected()) {
				board = new Board(8);
				board.setGui(this);
				mainFrame.getContentPane().add(BorderLayout.CENTER, board);

				game = new Game(this, Mode.NORMAL, 8);
				dialog.dispose();
			} else {
				board = new Board(12);
				board.setGui(this);
				mainFrame.getContentPane().add(BorderLayout.CENTER, board);

				game = new Game(this, Mode.CRAZY, 12);
				dialog.dispose();
			}
		}
	}
}
