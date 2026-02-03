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
import other.Tile;
import other.Troll;

public class GameComponent extends JComponent {
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	private Player1 bubbles = new Player1(90, 810);
	private Troll troll = new Troll(120,120);
	
	private GameModel model;
	private Timer timer;

	// for checking whether wall is or not
	private int nextX;
	private int nextY;
	private static final int SIZE = 90;
//	private Timer timer1;
//	
//	private int[][] maze_level_1 = {
//			{1,1,1,1,1,1,1,1,1,1},
//			{1,0,0,0,0,0,0,0,0,1},
//			{1,0,1,1,1,1,1,1,0,1},
//			{1,0,1,0,0,0,0,1,0,1},
//			{1,0,1,0,1,1,0,1,0,1},
//			{1,0,1,0,1,0,0,1,0,1},
//			{1,0,1,0,1,0,0,1,0,1},
//			{1,0,1,0,1,1,1,1,0,1},
//			{1,0,1,0,0,0,0,0,0,1},
//			{1,0,1,1,1,1,1,1,1,1},
//	};
//
	public boolean isWall(int flag_value) {
		System.out.println(flag_value);
		if(flag_value==0)return true;
		return false;
	}
	
	
	public GameComponent(GameModel model) {
		this.model = model;
//		model.getMaze_level_1();
//		Tile[][] tiles_for_GC = model.getTiles_level_1();
	
		timer= new Timer(20,e -> {

		 
		 
		bubbles.update(WIDTH, HEIGHT);
		troll.update(WIDTH, HEIGHT);
		
//		for (int i=0; i<10; i++) {
//			for (int j=0; j<10; j++) {
//				if (tiles_for_GC[i][j].getCollision() == true &&tiles_for_GC[i][j].getX() == bubbles.getX()) {
//					bubbles.bounceBack();
//				}
//		
//			}
//		}
//		if(isWall(model[bubbles.getX()][bubbles.getY()]))
//		
		repaint();
		});
//	timer1= new Timer(20,e -> {
//		bubbles.update(WIDTH, HEIGHT);
//		troll.update(WIDTH, HEIGHT);
//		
//		repaint();
//	});
		
		timer.start();
		setFocusable(true);
		
		// changed
		addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        int col = bubbles.getX() / SIZE;
		        int row = bubbles.getY() / SIZE;
	
		        if (e.getKeyCode() == KeyEvent.VK_D) {
		            int nextCol = col + 1;
		            if (nextCol < 10 && isWall(model.getMaze_level_1()[row][nextCol])) {
		                bubbles.move_x_right();
		            }
		        }
		        if (e.getKeyCode() == KeyEvent.VK_A) {
		            int nextCol = col - 1;
		            if (nextCol >= 0 && isWall(model.getMaze_level_1()[row][nextCol])) {
		                bubbles.move_x_left();
		            }
		        }
		        if (e.getKeyCode() == KeyEvent.VK_W) {
		            int nextRow = row - 1;
		            if (nextRow >= 0 && isWall(model.getMaze_level_1()[nextRow][col])) {
		                bubbles.move_y_up();
		            }
		        }
		        if (e.getKeyCode() == KeyEvent.VK_S) {
		            int nextRow = row + 1;
		            if (nextRow < 10 && isWall(model.getMaze_level_1()[nextRow][col])) {
		                bubbles.move_y_down();
		            }
		        }
		    }
		});
	}


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
//	for (int i =0; i<10;i++) {
//		for (int j= 0; j<10; j++) {
//			if (maze_level_1[i][j]==1) {
//				g2.fillRect(j*90, i*90, 90, 90);
//			}
//	}
	 	model.drawMap(g2, troll);
		bubbles.draw(g2);
		troll.draw(g2);
	}

	// Minimal placeholder to test  it’s running
//	g2.drawString("Final Project Starter: UI is running ✅", 20, 30);

	// TODO: draw based on model state
//
}
//}


