package model;

import java.awt.Color;

public class GameModel {
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

	public int[][] getMaze_level_1() {
		return maze_level_1;
	}

	public void setMaze_level_1(int[][] maze_level_1) {
		this.maze_level_1 = maze_level_1;
	}
	
	public boolean wall(int i) {
			for (int j=0; j<10; j++) {
				if (maze_level_1[i][j]==1) return true;
				else return false;
			}
		return false;

	}
	
}
