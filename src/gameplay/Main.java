package gameplay;

import gui.Gui;

public class Main {

	public static void main(String[] args) {

		Gui gui = new Gui();

		Game game = new Game(gui, Mode.NORMAL, 8);

	}

}
