package app;

import javax.swing.SwingUtilities;


import ui.GameWindow;
import ui.Controller;

/**
 * Class: MainApp
 * @author Ayaka, Madison, Adeline
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * Entry point for the final project.
 */
public class MainApp {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
		new MainApp().run();
		});
		}//main
	
	public void run() {
		
		GameWindow.show();
		// Hint: MainApp should not contain game logic or drawing code
		//Hello World
		// Hi
		//Hellooo
		//FOR COMMIT
		}//run
}