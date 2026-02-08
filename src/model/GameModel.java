package model;

import ui.GameComponent;
import other.Troll;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;

import javax.swing.JComponent;

import other.Tile;
/**
 * Class: GameModel
 * @author Adeline, Ayaka, Madison
 * <br>Purpose: The GameModel class handles the drawing of each level/ maze
 */

/** 
* Class constructor.
*/
public class GameModel extends JComponent{
	
	private Tile[][] tiles_level_1 = new Tile[10][10];
	private int[][] maze_level_1 = {
			{1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,1,1,0,1},
			{1,0,1,0,0,0,0,1,0,1},
			{1,0,1,0,1,1,0,1,0,1},
			{1,0,1,0,1,0,0,1,0,1},
			{1,0,1,0,1,0,0,1,0,1},
			{1,0,1,0,1,1,1,1,0,1},
			{1,0,1,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,1,1,1,1},
			
	};
	/** 
	* Draws the map
	* @param java graphics
	* @return void
	*/
	public void drawMap(Graphics2D g2) {
		for (int i =0; i<10;i++) {
			for (int j= 0; j<10; j++) {
				if (maze_level_1[i][j]==1) {
					// new Tile tile = Tile(true);
					Tile tile1 = new Tile(true, i, j);
					tiles_level_1[i][j]= tile1;
					g2.fillRect(j*90, i*90, 90, 90);
//					troll.flip();
				}
				Tile tile1 = new Tile(true, i, j);
				tiles_level_1[i][j]= tile1;
				
			}
		}
//		int col_troll = troll.getx()/90;
//		int row_troll = troll.gety()/90;
//		System.out.println(troll.getx());
//		if(col_troll<10&&wall(maze_level_1[row_troll][col_troll])) {
//			troll.flip();
		} 
	
	/** 
	* determines if the next black is a wall or not
	* @param int
	* @return boolean
	*/
	public boolean wall(int i) {
		for (int j=0; j<10; j++) {
			if (maze_level_1[i][j]==1) return true;
			else return false;
		}
	return false;
	}
	
	public int[][] getMaze_level_1() {
		return maze_level_1;
	}

	public void setMaze_level_1(int[][] maze_level_1) {
		this.maze_level_1 = maze_level_1;
	}

	public Tile[][] getTiles_level_1() {
		return tiles_level_1;
	}

	public void setTiles_level_1(Tile[][] tiles_level_1) {
		this.tiles_level_1 = tiles_level_1;
	}
	
	
}
