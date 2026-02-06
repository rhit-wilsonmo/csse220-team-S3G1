package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Troll implements Collidable{
	private int start_y;
	private int start_x;
	private int x;
	private int y;
	private static final int TROLL_WIDTH =90;
	private static final int TROLL_HEIGHT=90;
	private int dx = 90;
	private int dy = 90;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	
	
	public Troll(int start_y, int start_x) {
		super();
		this.start_y = start_y;
		this.start_x = start_x;
		this.x = start_x;
		this.y = start_y;
		
		loadSpriteOnce();
	}
	
	// code from ball
	private void loadSpriteOnce() {
		// TODO Auto-generated method stub
		if (triedLoad) return;
		triedLoad =true;
		try {
			sprite = ImageIO.read(Player1.class.getResource("troll2.png"));
		}
		catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}
	}
	
	
	public void draw(Graphics2D g2) {
		
		if (sprite != null) {
			g2.drawImage(sprite, x, y, TROLL_WIDTH , TROLL_HEIGHT, null);
		} else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, TROLL_WIDTH, TROLL_HEIGHT);
		}
	}
	
	public void move() {
		x+=dx;
	}
	public void flip() {
		dx=-dx;
	}
//	public void move_x_right() {
//		x+= DX;
//	}
//	public void move_x_left() {
//		x-= DX;
//	}
//	public void move_y_down() {
//		y += DY;
//	}
//	public void move_y_up() {
//		y -= DY;
//	}

	@Override
	public void update(int WorldWidth, int WorldHeight) {
		move();
		// x
//		if (x < 0) {
//			x = 0;
//			dx=-dx;
//		}
//		
//		// y
//		if (y < 0) {
//			y=0;
//			dy=-dy;
//		}
//		
//		//xl
//		if (x + TROLL_WIDTH > WorldWidth) {
//			x = WorldWidth- TROLL_WIDTH;
//			x=-dx;
//		}
//		
//		//yl
//		if (y + TROLL_HEIGHT > WorldHeight) {
//			y = WorldHeight- TROLL_HEIGHT;
//			y=-dy;
//		}
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
