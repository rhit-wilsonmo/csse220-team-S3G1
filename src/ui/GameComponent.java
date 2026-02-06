package ui;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

import model.GameModel;
import other.Gem;
import other.Player1;
import other.Score;
import other.Tile;
import other.Troll;

public class GameComponent extends JComponent {
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	private Player1 bubbles = new Player1(90, 810);
	private Troll troll = new Troll(90,90);
	private ArrayList<Gem> gems = new ArrayList<>();
	private GameModel model;
	private Timer timer, timer1;
	

	// for checking whether wall is or not
	private int nextX;
	private int nextY;
	private static final int SIZE = 90;
	
	//Madison: Score 
	private int score = 0;
	
	
	public GameComponent(GameModel model) {
		this.model = model;
		gems.add(new Gem(90,180));
		timer= new Timer(20,e -> {
		bubbles.update(WIDTH, HEIGHT);
		
//		for future reference maybe
//		model.getMaze_level_1();
//		Tile[][] tiles_for_GC = model.getTiles_level_1();
		
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
		
		
	timer1 = new Timer(100,e -> {
		int col_troll = troll.getx()/SIZE;
		int row_troll = troll.gety()/SIZE;
		
		if(col_troll>=0 && col_troll<10) {
			if(col_troll>=0 && troll.getdx() <0 && isWall(model.getMaze_level_1()[row_troll][col_troll-1])==false) {
				troll.flip();
				troll.update(WIDTH, HEIGHT);
			}else if(col_troll>=0 && troll.getdx() >0 && isWall(model.getMaze_level_1()[row_troll][col_troll+1])==false) {
					troll.flip();
					troll.update(WIDTH, HEIGHT);
//			}else if (col_troll==8 && isWall(model.getMaze_level_1()[row_troll][col_troll+1])==false) {
//				troll.flip();
//				troll.update(WIDTH, HEIGHT);
			}else {
				troll.update(WIDTH, HEIGHT);
			}
			
		} 
		repaint();
	});
		
	
		timer.start();
		timer1.start();
		setFocusable(true);
		
		// chanded
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
		        
//		        Code that allows pickup of gems (keep the size>0 or exception/error)
		        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		        	if (gems.size() >0 && bubbles.getBounds().intersects(gems.get(0).getBounds())) {
		            gems.removeFirst();
		            //Madison: Score goes up by one every time bubbles picks up a gem
		            score += 1;
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
	 	model.drawMap(g2);
		bubbles.draw(g2);
		troll.draw(g2);
		 for (Gem gem: gems) {
			  gem.draw(g2);
		  }
		 //To display the score
		 g.setColor(Color.WHITE);
		 g.setFont(new Font("Arial", Font.BOLD, 50));
		 g.drawString("Score: " + score, 10, 50);
		 
		 
	}

	public boolean isWall(int flag_value) {
//		System.out.println(flag_value);
		if(flag_value==0)return true;
		return false;
	}
	
//	public boolean isWall_troll(int flag_value,) {
////	System.out.println(flag_value);
//		if(flag_value==0)return true;
//		return false;
//	}
	
//	In GameComponent after the timer
//	int col_troll = troll.getx()/SIZE;
//	int row_troll = troll.gety()/SIZE;
//	
//	if(isWall(model.getMaze_level_1()[row_troll][col_troll])) {
//		troll.flip();
//	} 
	
}



