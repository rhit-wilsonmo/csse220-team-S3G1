package other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lives {
	private int lives;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private int x;
	private int y;
	private int start_y;
	private int start_x;
	private static final int HEART_WIDTH = 50;
	private static final int HEART_HEIGHT=50;
	private int hearts;
	
	public Lives() {
		this.lives = 3;
	}
	
	public void Hearts(int start_x, int start_y ) {
		this.hearts = lives;
		this.start_y = start_y;
		this.start_x = start_x;
		this.x = start_x;
		this.y = start_y;
		loadSpriteOnce();
	}
	
	public void showLives(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Lives: " + this.lives, 600, 50);
		
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
	
	private void loadSpriteOnce() {
		// TODO Auto-generated method stub
		if (triedLoad) return;
		triedLoad =true;
		try {
			sprite = ImageIO.read(Player1.class.getResource("game heart.png"));
		}
		catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (sprite != null) {
			g2.drawImage(sprite, x, y, HEART_WIDTH , HEART_HEIGHT, null);
		}else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, HEART_WIDTH , HEART_HEIGHT);
		}
	}
		
		
	
	
}