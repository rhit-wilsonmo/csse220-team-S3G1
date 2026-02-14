package model;

import ui.GameComponent;
import other.Troll;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

import javax.swing.JComponent;

import other.Gem;
import other.Player1;
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
	//0 represents a path
	//1 represents a wall
	//2 represents player
	//3 represents gem
	//4 represents troll
	//5 represents exit
	//6 represents trapdoor (maybeeee)
	public char[][] maze_level_1 = {
			{1,5,1,1,1,1,1,1,1,1},
			{1,0,1,1,0,0,0,0,0,1},
			{1,0,0,0,1,1,0,1,0,1},
			{1,0,1,2,0,1,1,1,0,1},
			{1,0,1,1,0,0,0,0,0,1},
			{1,0,3,1,1,1,1,1,0,1},
			{1,1,1,1,1,1,1,1,0,1},
			{1,4,0,0,0,0,0,1,0,1},
			{1,3,1,1,0,1,0,0,0,1},
			{1,1,1,1,0,1,1,1,1,1},
			
	};
	
	public char[][] maze_level_2= {
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,3,0,0,4,1,1},
			{1,0,1,1,0,1,1,1,1,1},
			{1,0,1,1,5,1,1,1,1,1},
			{1,2,1,1,1,1,1,1,1,1},
			
	};
	private boolean drawnBubbles = false;
	private boolean drawnGems = false;
	private boolean drawnTrolls = false;
	private boolean drawnExit = false;
	
	public boolean level_1 = false;
	public boolean level_2 = false;
	public boolean level_3 = false;
	public String fileName = "level_1";
//	public String file = "level1.txt";
	
	
	private int exit_row;
	private int exit_col;
	
//	private int currentLevel = 0;
	private int maxLevel = 0;
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
				}
				Tile tile1 = new Tile(true, i, j);
				tiles_level_1[i][j]= tile1;
				
			}
		}
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
	} //wall
	
	public Tile[][] getTiles_level_1() {
		return tiles_level_1;
	}//getTiles_level_1

	public void setTiles_level_1(Tile[][] tiles_level_1) {
		this.tiles_level_1 = tiles_level_1;
	}//setTiles_level_1
	
	public char[][] getMaze_level_1() {
		return maze_level_1;
	}

	public void setMaze_level_1(char[][] maze_level_1) {
		this.maze_level_1 = maze_level_1;
	}

	//overwrite: the following method
	public int countGems(char[][] maze_level) {
		int count = 0;
		for(int row=0; row<maze_level.length;row++) {
			for (int col=0; col<maze_level[row].length; col++) {
				if(maze_level[row][col]==3) count++;
			}
		}
		return count;
	}
	public void loadLevel_HardCode(Graphics g2, Player1 p, ArrayList<Troll> troll, ArrayList<Gem> gem,char [][] maze_level) {
		int countG = countGems(maze_level); //overwrite
		for(int row=0; row<maze_level.length;row++) {
			for (int col=0; col<maze_level[row].length; col++) {
				if (maze_level[row][col]==1) {
					g2.setColor(Color.BLACK);
					g2.fillRect(col*90, row*90, 90, 90);
				}
				if (maze_level[row][col]==0) {
					continue;
				}
				if (maze_level[row][col]==2 && drawnBubbles==false) {
					 p.setX(col*90);
					 p.setY(row*90);
//					 p.set_start(col*90, row*90);
					 drawnBubbles = true;
				}
				if (maze_level[row][col]==3 && drawnGems==false) {
//					System.out.println(col + " " + row);
//					System.out.println(drawnGems)
					gem.add(new Gem(col*90, row*90));
//					drawnGems=true;
					if (gem.size()==countG) {
						drawnGems = true;
					}
					
				}
				if (maze_level[row][col]==4&& drawnTrolls==false) {
					troll.add(new Troll(col*90, row*90));
					drawnTrolls=true;
				}
				if (maze_level[row][col]==5&& drawnExit==false) {
					exit_row = row;
					exit_col = col;
					g2.setColor(Color.YELLOW);
					g2.fillRect(col*90, row*90, 90, 90);
//					drawnExit = true;
				}
				
			}
		}
	}//LoadLevel_HardCode
	
//	public int countGems(String filename) {
//		File file = new File(filename);
//		int row =0;
//		int count =0;
//		
//		try {
//			Scanner scanner = new Scanner(file);
//			
//			while(scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				
//		for(int col =0; col < line.length(); col++) {
//			char c = line.charAt(col);
//				if (c=='G') {
//					count +=1;
//				}
//				}
//				row++;
//			}
//			scanner.close();
//		}
//		catch(FileNotFoundException e) {
//			System.out.println(filename + " not found");
//		}
//		return count;
//	}//countGems
//	
//	public int countTrolls(String filename) {
//		File file = new File(filename);
//		int row =0;
//		int count =0;
//		
//		try {
//			Scanner scanner = new Scanner(file);
//			
//			while(scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				
//		for(int col =0; col < line.length(); col++) {
//			char c = line.charAt(col);
//				if (c=='T') {
//					count +=1;
//				}
//				}
//				row++;
//			}
//			scanner.close();
//		}
//		catch(FileNotFoundException e) {
//			System.out.println(filename + " not found");
//		}
//		return count;
//	}//countTrolls
//	
//
//	public void loadLevel(Graphics g2, Player1 p, ArrayList<Troll> troll, ArrayList<Gem> gem, String filename ) {
//		File file = new File(filename);
//		int row = 0;
//		int countG = countGems(filename);
//		int countT = countTrolls(filename);
//		
//		try {
//			Scanner scanner = new Scanner(file);
//			
//			while(scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				
//				for(int col =0; col < line.length(); col++) {
//					char c = line.charAt(col);
//					
////					System.out.println("Yippee!");
//					if(c == 'P' && drawnBubbles == false) {
////						 p.setStart_x();
////						 p.setStart_y(row*90);
//						 p.setX(col*90);
//						 p.setY(row*90);
//						 drawnBubbles = true;
//						
//					} else if (c == 'T' && drawnTrolls == false) {
//						System.out.print("Troll");
//						troll.add(new Troll(col*90, row*90));
//						
//						drawnTrolls = true;
//						
//					} else if (c== 'G' && drawnGems ==false) {
////						System.out.println(col + " " + row);
//						gem.add(new Gem(col*90, row*90));
//
////						System.out.println(gem.size());
//						if (gem.size()==countG) {
//							drawnGems = true;
//						}
//					} 
//					else if(c== ',' ) {
//						exit_row = row;
//						exit_col = col;
////						Tile tile1 = new Tile(false, col, row);
////						tiles_level_1[row][col] = tile1;
//						g2.setColor(Color.RED);
//						g2.fillRect(col*90, row*90, 90, 90);
//						continue;
//					}
//						else if(c== '1') {
//						Tile tile1 = new Tile(true, col, row);
//						tiles_level_1[row][col] = tile1;
//						g2.setColor(Color.BLACK);
//						g2.fillRect(col*90, row*90, 90, 90);
//						
//					}
//						else if(c== '0') {
//							
//							continue;
//							
//						}
//				}
//				
//				row++;
//			}
//			scanner.close();
//		} catch(FileNotFoundException e) {
//			System.out.println(filename +" not found");
//		}
//				
//	}//loadlevel
	
	public void reset() {
		drawnBubbles = false;
		drawnTrolls = false;
		drawnGems= false;
	}
	
	public int get_exitRow() {
		return exit_row;
	}
	
	public int get_exitCol() {
		return exit_col;
	}
	
	//Loading new levels
//	public void nextLevel(int currentLevel) {
//		while (currentLevel <= maxLevel) {
//			if (currentLevel == 1) {
//				file="level"+currentLevel+".txt";
////				loadlevel(fileName);
//			} else if (currentLevel == 2);{
//				file= "level" + currentLevel + ".txt";
//			}
			
		
//		}
//	}
	
	
}
