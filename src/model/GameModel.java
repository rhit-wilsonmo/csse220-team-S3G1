package model;

import ui.GameComponent;
import other.Troll;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JComponent;

import other.Tile;

public class GameModel extends JComponent{
	
	public GameModel() {
		File file = new File("level1_finalgame.txt");
	    try {
	    	  Scanner scanner = new Scanner(file);
	    		  int row = 0;
	    		  while (scanner.hasNextLine()) {
	    		    String line = scanner.nextLine();

	    		    for (int col = 0; col < line.length(); col++) {
	    		      char c = line.charAt(col);
	    		      if (c == 'P') {
	    		    	  System.out.println(c);
	    		    	  start_x = col * TILE_SIZE;
	    		    	  start_y = row * TILE_SIZE;
	    		  	      
	    		    }
	    		    }

	    		    row++;
	    		  }

	    		  scanner.close();
	    	} catch (FileNotFoundException e) {
	    	  System.out.println("level1.txt not found");
	    	}
	}
	
}
