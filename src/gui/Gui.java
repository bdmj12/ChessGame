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
import gameplay.Main;
import pieces.Alliance;

public class Gui implements ActionListener {

	private JFrame mainFrame;
	private JPanel lowerPanel;
	private static JLabel turnLabel;
	private static JLabel checkLabel;

	private Board board; // extends JPanel!

	private static String wturn = "White's Turn.";
	private static String bturn = "Black's Turn.";

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem undo;
	private JMenuItem newGame;
	private JMenuItem redo;

	private JOptionPane newGameDialog;

	public final static int WIDTH = 600;
	public final static int HEIGHT = 675;

	public Gui(Board board) {
		// creates the window
		mainFrame = new JFrame();
		this.board = board;

		menu = new JMenu("Game");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		newGame = new JMenuItem("New Game");

		newGame.addActionListener(this);

		menu.add(newGame);
		menu.add(undo);
		menu.add(redo);

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

	public static void updateLabels(boolean bool) {

		// turn label
		if (Game.turn == Alliance.WHITE) {
			turnLabel.setText(wturn);
		} else {
			turnLabel.setText(bturn);
		}

		// test for check & checkmate
		if (bool) {
			if (Game.isCheckMate()) {
				checkLabel.setText("Checkmate!");
			}

			else if (Game.inCheck()) {
				checkLabel.setText("Check!");
			} else {
				checkLabel.setText("");
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// to add later
		// newGameDialog = new JOptionPane();

		mainFrame.setVisible(false);
		mainFrame.dispose();
		Main.main(null);

	}

}
