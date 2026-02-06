package other;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.*;

//import ui.score;

public class Score   {
	private int score = 0;
	
	public Score() {
	}
	
	public void addScore(){
		score += 1;	
	}
	
	public void showScore(Graphics g) {
		//To display the score
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Score: " + this.score, 10, 50);
	}
	

}
