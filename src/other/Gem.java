package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Class: Gem
 * @author Adeline
 * <br>Purpose: The collectible item class; Loads the image, draws on screen, and returns bounding box for collisions
 */
public class Gem {
	private int x;
	private int y;
	private static final int GEM_WIDTH = 45;
	private static final int GEM_HEIGHT =45;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	
	
	/** 
	* Class Constructor
	*/
		public Gem(int x, int y) {
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
				sprite = ImageIO.read(Gem.class.getResource("diamond image.gif"));
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
				g2.drawImage(sprite, x, y, GEM_WIDTH, GEM_HEIGHT , null);
			} else {
				g2.setColor(Color.MAGENTA);
				g2.fillRect(x, y, GEM_WIDTH, GEM_HEIGHT);
			}
		}//draw
		
		/** 
		* Gets the bounding box for the gems
		* @return Rectangle
		*/
		public Rectangle getBounds() {
		    Rectangle r = new Rectangle(
					    x ,
					    y ,
					    GEM_WIDTH,
					    GEM_HEIGHT
		    );
		    return r;
		}//getBounds
		
}
