package gameplay;

import gui.Board;
import gui.Gui;

public class Main {

	public static void main(String[] args) {

		Board board = new Board(8);

		Gui gui = new Gui(board);

		Game game = new Game(gui, Mode.NORMAL, 8);

		gui.newGame.doClick();

	}

}
