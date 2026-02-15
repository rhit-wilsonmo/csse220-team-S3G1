package other;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Key {
	private int x;
	private int y;
	private static final int KEY_WIDTH = 45;
	private static final int KEY_HEIGHT =45;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private boolean key = false;
	
	
	/** 
	* Class Constructor
	*/
		public Key(int x, int y) {
			this.x = x;
			this.y = y;
			
			loadSpriteOnce();
		}
		
		/** 
		* Loads the image
		* code from ball
		* @return void
		*/
		public void loadSpriteOnce() {
			if (triedLoad) return;
			triedLoad =true;
			try {
				sprite = ImageIO.read(Gem.class.getResource("game_heart.png"));
			}
			catch (IOException | IllegalArgumentException ex) {
				sprite = null;
			}
		}//loadSpriteOnce
		
		/** 
		* Draws the image
		* code from ball
		* @param java graphics
		* @return void
		*/
		public void draw(Graphics2D g2) {
			
			if (sprite != null) {
				g2.drawImage(sprite, x, y, KEY_WIDTH, KEY_HEIGHT , null);
			} else {
				g2.setColor(Color.MAGENTA);
				g2.fillRect(x, y, KEY_WIDTH, KEY_HEIGHT);
			}
		}//draw
		
		/** 
		* Gets the bounding box for the gems
		* @return Rectangle
		*/
		public Rectangle getBounds() {
		    Rectangle r = new Rectangle(
					    x-KEY_WIDTH ,
					    y-KEY_HEIGHT,
					    KEY_WIDTH,
					    KEY_HEIGHT
		    );
		    return r;
		}//getBounds
		
		public void setKey() {
			this.key = true;
		}
		
		public boolean isHaveKey() {
			return this.key;
		}
		
		public void add(int x1, int y1) {
			x = x1;
			y = y1;
			loadSpriteOnce();
			System.out.print("Hi ");
		}
		
}
