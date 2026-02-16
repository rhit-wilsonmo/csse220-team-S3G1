package ui;

import javax.swing.JFrame;

import model.GameModel;

/**
 * Class: GameWindow
 * 
 * @author Madison, Adeline,Ayaka <br>
 *         Purpose: Controls the frame
 */
public class GameWindow {
	private GameModel model;

	/**
	 * Loads the frame and adds the gameComponent
	 * 
	 * @return void
	 */
	public static void show() {
		GameModel model = new GameModel();

		JFrame frame = new JFrame("CSSE220 Final Project");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new GameComponent(model));

		frame.setSize(900, 900);
		frame.setLocationRelativeTo(null); // center on screen (nice UX, still minimal)
		frame.setVisible(true);

	}

}
