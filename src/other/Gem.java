package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gem {
	private int x;
	private int y;
	private static final int GEM_WIDTH = 45;
	private static final int GEM_HEIGHT =45;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	
		public Gem(int x, int y) {
			this.x = x;
			this.y = y;
			
			loadSpriteOnce();
		}
		
		public void loadSpriteOnce() {
			if (triedLoad) return;
			triedLoad =true;
			try {
				sprite = ImageIO.read(Gem.class.getResource("diamond image.gif"));
			}
			catch (IOException | IllegalArgumentException ex) {
				sprite = null;
			}
		}
		public void draw(Graphics2D g2) {
			
			if (sprite != null) {
				g2.drawImage(sprite, x, y, GEM_WIDTH, GEM_HEIGHT , null);
			} else {
				g2.setColor(Color.MAGENTA);
				g2.fillRect(x, y, GEM_WIDTH, GEM_HEIGHT);
			}
		}
		
		public Rectangle getBounds() {
		    Rectangle r = new Rectangle(
					    x - GEM_WIDTH,
					    y - GEM_HEIGHT,
					    GEM_WIDTH,
					    GEM_HEIGHT
		    );
		    return r;
		}
		
}
