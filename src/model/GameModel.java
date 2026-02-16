package model;

import other.Troll;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import other.Gem;
import other.Key;
import other.Lives;
import other.Player1;

/**
 * Class: GameModel
 * @author Adeline, Ayaka, Madison
 * <br>Purpose: The GameModel class handles the drawing of each level/ maze
 */

/**
 * Class constructor.
 */
public class GameModel extends JComponent {

//	private Tile[][] tiles_level_1 = new Tile[10][10];
	// 0 represents a path
	// 1 represents a wall
	// 2 represents player
	// 3 represents gem
	// 4 represents troll
	// 5 represents exit
	// 6 represents key
	// 7 represents life
	public int[][] maze_level_1 = { { 1, 5, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 6, 1, 0, 1 }, { 1, 0, 1, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 3, 1, 1, 1, 1, 1, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 }, { 1, 4, 0, 0, 0, 0, 0, 1, 0, 1 },
			{ 1, 3, 1, 1, 0, 1, 0, 0, 7, 1 }, { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1 },

	};

	public int[][] maze_level_2 = { { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 4, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 3, 0, 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 4, 0, 0, 0, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 1, 0, 1, 1, 1, 1, 1 }, { 1, 0, 3, 0, 0, 3, 7, 4, 1, 1 }, { 1, 0, 1, 0, 0, 1, 1, 1, 1, 1 },
			{ 1, 6, 1, 0, 5, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },

	};

	private boolean drawnBubbles = false;
	private boolean drawnGems = false;
	private boolean drawnTrolls = false;
	private boolean drawnKey = false;
	private boolean drawnLife = false;

	public boolean level_1 = false;
	public boolean level_2 = false;
	public boolean level_3 = false;
	public String fileName = "level_1";

	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;

	private int exit_row;
	private int exit_col;

	// overwrite: the following method
	public int countGems(int[][] maze_level) {
		int count = 0;
		for (int row = 0; row < maze_level.length; row++) {
			for (int col = 0; col < maze_level[row].length; col++) {
				if (maze_level[row][col] == 3)
					count++;
			}
		}
		return count;
	}

	public int countTroll(int[][] maze_level) {
		int count = 0;
		for (int row = 0; row < maze_level.length; row++) {
			for (int col = 0; col < maze_level[row].length; col++) {
				if (maze_level[row][col] == 4)
					count++;
			}
		}
		return count;
	}

	public void initialize_HardCode(Player1 p, ArrayList<Troll> troll, ArrayList<Gem> gem, Key key, Lives life,
			int[][] maze_level) {
		int countG = countGems(maze_level); // overwrite
		int countT = countTroll(maze_level);
		for (int row = 0; row < maze_level.length; row++) {
			for (int col = 0; col < maze_level[row].length; col++) {
				if (maze_level[row][col] == 2 && drawnBubbles == false) {
					p.setX(col * 90);
					p.setY(row * 90);
					drawnBubbles = true;
				}
				if (maze_level[row][col] == 3 && drawnGems == false) {
					gem.add(new Gem(col * 90, row * 90));
					if (gem.size() == countG) {
						drawnGems = true;
					}
				}
				if (maze_level[row][col] == 4 && drawnTrolls == false) {
					troll.add(new Troll(col * 90, row * 90));
					if (troll.size() == countT) {
						drawnTrolls = true;
					}
				}
				if (maze_level[row][col] == 6 && drawnKey == false) {
					key.add(col * 90, row * 90);
					drawnKey = true;

				}
				if (maze_level[row][col] == 7 && drawnLife == false) {
					life.add(col * 90, row * 90);
					drawnLife = true;

				}

			}
		}
		loadSpriteOnce();
	}

	public void loadLevel_HardCode(Graphics g2, Player1 p, ArrayList<Troll> troll, ArrayList<Gem> gem, Key key,
			int[][] maze_level) {
		int countG = countGems(maze_level); // overwrite
		for (int row = 0; row < maze_level.length; row++) {
			for (int col = 0; col < maze_level[row].length; col++) {
				if (maze_level[row][col] == 0) {
					continue;
				}
				if (maze_level[row][col] == 1) {
					g2.setColor(Color.BLACK);
					g2.fillRect(col * 90, row * 90, 90, 90);
				}

				if (maze_level[row][col] == 5) {
					exit_row = row;
					exit_col = col;
					if (sprite != null) {
						g2.drawImage(sprite, col * 90, row * 90, 90, 90, null);
					} else {
						g2.setColor(Color.MAGENTA);
						g2.fillRect(col * 90, row * 90, 90, 90);
					}
				}

			}
		}
	}// LoadLevel_HardCode

	public void reset() {
		drawnBubbles = false;
		drawnTrolls = false;
		drawnGems = false;
		drawnKey = false;
		drawnLife = false;
	}

	public int get_tile(int getRow, int getCol, int[][] maze) {
		return maze[getCol][getRow];
	}

	public void loadSpriteOnce() {

		if (triedLoad)
			return;
		triedLoad = true;
		try {
			sprite = ImageIO.read(GameModel.class.getResource("THE_DOOR.png"));
		} catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}
	}// loadSpriteOnce

}
