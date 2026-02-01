package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player1 implements Collidable{

	private int start_y;
	private int start_x;
	private int x;
	private int y;
	private static final int WIDTH = 20;
	private static final int HEIGHT=20;
	private static final int DX = 10;
	private static final int DY = 0;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	
	public Player1(int start_y, int start_x) {
		super();
		this.start_y = start_y;
		this.start_x = start_x;
		this.x = start_x;
		this.y = start_y;
	
	}
	
	// code from ball
	private void loadSpriteOnce() {
		// TODO Auto-generated method stub
		if (triedLoad) return;
		triedLoad =true;
		try {
			sprite = ImageIO.read(Player1.class.getResource("tennis.png"));
		}
		catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}

		
	}
	
	public void draw(Graphics2D g2) {
		
		if (sprite != null) {
			g2.drawImage(sprite, x, y, WIDTH , HEIGHT, null);
		} else {
		g2.setColor(Color.MAGENTA);
		g2.fillRect(x, y, WIDTH, HEIGHT);
		}
	}
	
	public void move_x() {
		this.x+= DX;
	}
	public void move_y() {
		this.y += DY;
	}

	@Override
	public void update(int WorldWidth, int WorldHeight) {
		
		// x
		if (x <= 0) {
			this.x = 0;
		}
		
		// y
		if (y <= 0) {
			this.y=0;
		}
		
		//xl
		if (x + WIDTH >= WorldWidth) {
			this.x = WorldWidth- this.WIDTH;
		}
		
		//yl
		if (x + WIDTH >= WorldHeight) {
			this.y = WorldHeight- this.HEIGHT;
		}
	}
}
