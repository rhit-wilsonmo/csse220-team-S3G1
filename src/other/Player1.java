package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class: Player
 * @author Ayaka, Madison, Adeline
 * Purpose: Player class
 */
public class Player1 implements Collidable{

	private int start_y;
	private int start_x;
	private int x;
	private int y;
	private static final int PLAYER_WIDTH = 90;
	private static final int PLAYER_HEIGHT=90;
	private static final int DX = 90;
	private static final int DY = 90;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	
	//for check whether wall is or not
	private int nextX;
	private int nextY;
	
	public Player1(int start_x, int start_y) {
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
			sprite = ImageIO.read(Player1.class.getResource("unicorn image.png"));
		}
		catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}

		
	}
	
	public void draw(Graphics2D g2) {
		
		if (sprite != null) {
			g2.drawImage(sprite, x, y, PLAYER_WIDTH , PLAYER_HEIGHT, null);
		} else {
		g2.setColor(Color.MAGENTA);
		g2.fillRect(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
	}
	
	public void move_x_right() {
		x+= DX;
	}
	public void move_x_left() {
		x-= DX;
	}
	public void move_y_down() {
		y += DY;
	}
	public void move_y_up() {
		y -= DY;
	}
	
	public void bounceBack(){
		x=x-100;
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void update(int WorldWidth, int WorldHeight) {
		
		// x
		if (x < 0) {
			x = 0;
		}
		
		// y
		if (y < 0) {
			y=0;
		}
		
		//xl
		if (x + PLAYER_WIDTH > WorldWidth) {
			x = WorldWidth- PLAYER_WIDTH;
		}
		
		//yl
		if (y + PLAYER_HEIGHT > WorldHeight) {
			y = WorldHeight- PLAYER_HEIGHT;
		}
	}

	public Rectangle getBounds() {
	    Rectangle r = new Rectangle(
				    x - PLAYER_WIDTH,
				    y - PLAYER_HEIGHT,
				    PLAYER_WIDTH * 2,
				    PLAYER_HEIGHT * 2
	    );
	    return r;
	}
	
	
}
