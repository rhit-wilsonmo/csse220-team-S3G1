package other;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.*;

//import ui.score;
/**
 * Class: Score
 * @author Madison
 * <br>Purpose: The class that keeps track and loads the score onto the screen
 */

public class Score   {
	private int score = 0;
	
	/** 
	* Class constructor
	*/
	public Score() {
	}
	
	/** 
	* adds to the score
	* @return void
	*/
	public void addScore(){
		score += 1;	
	}
	
	/** 
	* Shows the score when called
	* @return void
	*/
	public void showScore(Graphics g) {
		//To display the score
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Score: " + this.score, 10, 50);
	}
	

}
