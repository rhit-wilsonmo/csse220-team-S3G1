package other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Lives {
	private int lives;
	
	public Lives() {
		this.lives = 3;
	}
	
	public void lostLife() {
		this.lives--;
	}
	
	// check whether life is 0 or not
	public boolean isZeroLife(){
		if(this.lives<=0) return true;
		return false;
	}
	
	public void showGameOver(Graphics g) {
		// to display the reset button
		g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 300, 300);
	}
	
}