package other;

import java.awt.image.BufferedImage;
import java.awt.*;

public class Tile {

	private boolean Collision = false;
	private BufferedImage tile = null;
	private Rectangle rect;
	private int x;
	private int y;
	
	
	public Tile(boolean wall, int i, int j) {
		if (wall == true) {
			Rectangle rect = new Rectangle(j*90, i*90, 90,90);
			this.Collision = true;
			this.x = j*90;
			this.y = i*90;
		}
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


	public boolean isCollision() {
		return Collision;
	}


	public boolean getCollision() {
		return Collision;
	}
	
	
//	public boolean isWall() {
//		return Collision;
//		
//	}
}
