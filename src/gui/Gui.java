package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameplay.Game;
import pieces.Alliance;

public class Gui implements ActionListener {

	private JFrame mainFrame;
	private JPanel lowerPanel;
	private static JLabel turnLabel;
	private static JLabel checkLabel;

	private Board board; // extends JPanel!

	private Game game;

	public void setGame(Game game) {
		this.game = game;
		undo.addActionListener(game);
	}

	private static String wturn = "White's Turn.";
	private static String bturn = "Black's Turn.";

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem undo;
	private JMenuItem newGame;

	public final static int WIDTH = 600;
	public final static int HEIGHT = 675;

	public Gui() {
		// creates the window
		mainFrame = new JFrame();

		board = new Board();
		board.setGui(this);

		menu = new JMenu("Game");
		undo = new JMenuItem("Undo");
		newGame = new JMenuItem("New Game");

		newGame.addActionListener(this);

		menu.add(newGame);
		menu.add(undo);

		menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		menuBar.add(menu);

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

		int reply = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to start a new game?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (reply == JOptionPane.YES_OPTION) {
			// clear board
			board.clearBoard();

			// clear checklabel
			checkLabel.setText("");

			// create a new Game
			game = new Game(this);

		}
	}

}
