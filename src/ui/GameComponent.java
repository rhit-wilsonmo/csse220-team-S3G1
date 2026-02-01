package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.Timer;

import model.GameModel;
import other.Player1;
import other.Troll;

public class GameComponent extends JComponent {
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	private Player1 bubbles = new Player1(90, 810);
	private Troll troll = new Troll(100,100);
	
	private GameModel model;
	private Timer timer;
	
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

	public GameComponent(GameModel model) {
	this.model = model;
	
	timer= new Timer(20,e -> {
		bubbles.update(WIDTH, HEIGHT);
		troll.update(WIDTH, HEIGHT);
		
		repaint();
	});
	
	timer.start();
	setFocusable(true);
	
	addKeyListener(new KeyAdapter() {
	@Override
	public void keyPressed(KeyEvent e) {
	  if (e.getKeyCode() == KeyEvent.VK_D) {
	    bubbles.move_x_right();
	  }
	  if (e.getKeyCode() == KeyEvent.VK_A) {
		    bubbles.move_x_left();
		  }
	  if (e.getKeyCode() == KeyEvent.VK_W) {
		    bubbles.move_y_up();
		  }
	  if (e.getKeyCode() == KeyEvent.VK_S) {
		    bubbles.move_y_down();
		  }
	}
	});
	}


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	for (int i =0; i<10;i++) {
		for (int j= 0; j<10; j++) {
			if (maze_level_1[i][j]==1) {
				g2.fillRect(j*90, i*90, 90, 90);
			}
	}
		bubbles.draw(g2);
		troll.draw(g2);
	}

	// Minimal placeholder to test  it’s running
//	g2.drawString("Final Project Starter: UI is running ✅", 20, 30);

	// TODO: draw based on model state

	}
}


