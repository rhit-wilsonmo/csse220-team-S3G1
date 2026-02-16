package other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class: Lives
 * 
 * @author Madison and Ayaka <br>
 *         Purpose: The Lives class responsible for displaying the hearts on
 *         screen
 */
public class Lives {
	private int num_lives;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private int y;
	private int x;
	private static final int HEART_WIDTH = 50;
	private static final int HEART_HEIGHT = 50;

	/**
	 * Class Constructor
	 */
	public Lives() {
		this.num_lives = 3;
		loadSpriteOnce();
	}

	public Lives(int x, int y) {
		this.x = x;
		this.y = y;
		loadSpriteOnce();
	}

	/**
	 * Shows text lives may be deleted
	 * 
	 * @return void
	 */
	public void showLives(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Lives: " + this.num_lives, 600, 50);

	}

	/**
	 * check whether life is lost may be deleted
	 * 
	 * @return void
	 */
	public void lostLife() {
		this.num_lives--;
	}

	/**
	 * Draws game over screen
	 * 
	 * @param java graphics
	 * @return void
	 */
	public void showGameOver(Graphics g) {
		// to display the reset button
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("GAME OVER", 300, 300);
	}

	/**
	 * Loads the image code from ball
	 * 
	 * @return void
	 */
	private void loadSpriteOnce() {
		// TODO Auto-generated method stub
		if (triedLoad)
			return;
		triedLoad = true;
		try {
			sprite = ImageIO.read(Player1.class.getResource("game_heart.png"));
		} catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}
	}

	/**
	 * Draws the image code from ball
	 * 
	 * @return void
	 */
	public void draw(Graphics2D g2, int heart_x, int heart_y) {
		if (sprite != null) {
			g2.drawImage(sprite, heart_x, heart_y, HEART_WIDTH, HEART_HEIGHT, null);
		} else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(heart_x, heart_y, HEART_WIDTH, HEART_HEIGHT);
		}
	}

	public void drawbutnot(Graphics2D g2) {
		if (sprite != null) {
			g2.drawImage(sprite, x, y, HEART_WIDTH, HEART_HEIGHT, null);
		} else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, HEART_WIDTH, HEART_HEIGHT);
		}
	}

	public void add(int x1, int y1) {
		x = x1;
		y = y1;
		loadSpriteOnce();
	}

}