package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class: Troll
 * 
 * @author Ayaka, Madison, Adeline <br>
 *         Purpose: The 'enemy' class of the game.
 */
public class Troll implements Collidable {
	private int x;
	private int y;
	private static final int TROLL_WIDTH = 90;
	private static final int TROLL_HEIGHT = 90;
	private int dx = 90;
	private int dy = 90;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;

	/**
	 * Class Constructor
	 */
	public Troll(int start_x, int start_y) {
		super();
		this.x = start_x;
		this.y = start_y;

		loadSpriteOnce();
	}

	/**
	 * Loads the image code from ball
	 * 
	 * @return void
	 */
	private void loadSpriteOnce() {
		if (triedLoad)
			return;
		triedLoad = true;
		try {
			sprite = ImageIO.read(Player1.class.getResource("troll_2.png"));
		} catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}
	}

	/**
	 * Draws the image code from ball
	 * 
	 * @return void
	 */
	public void draw(Graphics2D g2) {

		if (sprite != null) {
			g2.drawImage(sprite, x, y, TROLL_WIDTH, TROLL_HEIGHT, null);
		} else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, TROLL_WIDTH, TROLL_HEIGHT);
		}
	}

	/**
	 * Moves troll
	 * 
	 * @return void
	 */
	public void move() {
		x += dx;
	}

	/**
	 * Flips troll
	 * 
	 * @return void
	 */
	public void flip() {
		dx = -dx;
	}

	@Override
	public void update(int WorldWidth, int WorldHeight) {
		move();
	}

	public int getx() {
		return x;
	}

	public void setx(int x) {
		this.x = x;
	}

	public int gety() {
		return y;
	}

	public void sety(int y) {
		this.y = y;
	}

	public int getdx() {
		return dx;
	}

	public void setdx(int dx) {
		this.dx = dx;
	}
}
