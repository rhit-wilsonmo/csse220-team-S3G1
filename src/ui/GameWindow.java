package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.lang.ModuleLayer.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameModel;
import other.Score;
import other.StartScreen;
import ui.GameComponent;
import model.GameModel;
/**
 * Class: GameWindow
 * @author Madison, Adeline,Ayaka
 * <br>Purpose: Controls the frame
 */
public class GameWindow {
	private GameComponent component;
	private GameModel model;

	/** 
	* Loads the frame and adds the gameComponent
	* @return void
	*/
	public static void show() {
		// Minimal model instance (empty for now, by design)
		GameModel model = new GameModel();
//		JPanel cards = new JPanel(new CardLayout());
//		StartScreen startScreen = new StartScreen();
//		
//		cards.add(startScreen, "START");
		
		
		
		


		JFrame frame = new JFrame("CSSE220 Final Project");
		
//		frame.setContentPane(cards);
//		CardLayout cl = (CardLayout) cards.getLayout();
//		cl.show(cards, "START");
//		startScreen.getStartButton().addActionListener(e -> {
//			
//		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add(new Score());


		frame.add(new GameComponent(model));
		


		frame.setSize(900, 900);
		frame.setLocationRelativeTo(null); // center on screen (nice UX, still minimal)
		frame.setVisible(true);
		
//Need something so i can commit with a new comment
		
		}

}
