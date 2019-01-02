package gameplay;

import gui.Board;
import gui.Gui;

public class Main {

	public static void main(String[] args) {

		// create an intial 8x8 board

		Board board = new Board(8);

		// create the gui and add the board

		Gui gui = new Gui(board);

		// create a new normal 8x8 game

		Game game = new Game(gui, Mode.NORMAL, 8, false, false);

		// open the new game dialog

		gui.newGame.doClick();

	}

}
