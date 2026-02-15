package ui;

import java.awt.Color;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;

import model.GameModel;
import other.Gem;
import other.Key;
import other.Lives;
import other.Player1;
import other.Score;
import other.Tile;
import other.Troll;


/**
 * Class: Game Component
 * @author Ayaka, Adeline, Madison
 * <br>Purpose: Runs the game; key listener, painting the screen, timers, resetting the game
 */
public class GameComponent extends JComponent{
	
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	private Player1 bubbles = new Player1(0,0);
//	private Troll troll = new Troll(90,90);
	private ArrayList<Troll> trolls = new ArrayList<>();
	private ArrayList<Gem> gems = new ArrayList<>();
	private Key key = new Key(0, 0);
	private ArrayList<Lives> life_arr = new ArrayList<>();
	private GameModel model;
	private Timer timer, timer1;
	
	// corresponding the tile number
	private static final char EXITNUM = 5;
	private static final char KEYNUM = 6;
	
	// for checking whether wall is or not
	private int nextX;
	private int nextY;
	private static final int SIZE = 90;
	
	//Madison: Score 
	private Score score = new Score();
	
	//Ayaka: Live and button for restart
	private Lives lives = new Lives();
	private JButton resetButton;
	
	private int currentLevel = 1;
	private String levelName;
	
	public HashMap<Integer, int[][]> levels = new HashMap<>();
	
	private boolean reset = false;
	
	//Madison: Displays the hearts
//	private Lives hearts = new Heart
	
	
	
	
	/** 
	* Constructor of Gamecomponent
	* Handles everything
	*/
	public GameComponent(GameModel model) {
		this.model = model;
		
		levels.put(1, model.maze_level_1);
//		levels.put(3, model.maze_level_1);
		levels.put(2, model.maze_level_2);
		life_arr.add(lives);
		life_arr.add(lives);
		life_arr.add(lives);
		
		System.out.println("component");
		
		// reset button to restart after life is 0
	    resetButton = new JButton("RESTART");
	    resetButton.setBounds(400, 400, 100, 50);
	    resetButton.setVisible(false); // invisible
	    resetButton.addActionListener(e -> resetGame());
	    this.add(resetButton);
	    
	    model.initialize_HardCode(bubbles, trolls, gems, key, model.maze_level_1);
	    
	    // For bubbles
		timer= new Timer(20,e -> {
//			System.out.println("Timer!!!!!");
			bubbles.update(WIDTH, HEIGHT);
			int bub_x = bubbles.getX();
			int bub_y = bubbles.getY();
			
			//collision bw a bubble and a troll
			for (Troll troll: trolls) {
			if(bub_x==troll.getx() && bub_y==troll.gety()) {
				life_arr.remove(0);
				troll.flip();
				troll.update(WIDTH, HEIGHT);
				// check the left lives
//				if(lives.isZeroLife()) {
//					timer.stop();
//					timer1.stop();
				}
			}
			//overwrite(1): commentout
//			System.out.println(bubbles.getX()+ " "+ model.get_exitRow() +""+model.get_exitCol());
//			if((bubbles.getX()/SIZE)==model.get_exitCol() && (bubbles.getY()/SIZE)==model.get_exitRow()) {
//			System.out.println(bub_x/SIZE);
//			System.out.println(bub_y/SIZE);
//			System.out.println(currentLevel);
//			System.out.println(model.get_tile((bub_x/SIZE), (bub_y/SIZE), currentLevel));
			if(levels.get(currentLevel)[bub_y/SIZE][bub_x/SIZE]==EXITNUM && key.isHaveKey()) {
				System.out.println("Next Level!");
//				timer.stop();
//				timer1.stop();
				currentLevel+=1;
				reset = false;
//				levelName = "level_" + currentLevel;
				// model.filename = levelName;
//				model.nextLevel(currentLevel);
//				model.level_1 = true;
//				model.loadlevel(getGraphics(), bubbles, trolls, gems, model.file);
			
			}
			
			
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
			repaint();
		});
		
		// for troll
		timer1 = new Timer(250,e -> {
			System.out.println("Timer1!!!!");
			for (Troll troll : trolls) {
			int col_troll = troll.getx()/SIZE;
			int row_troll = troll.gety()/SIZE;

			// for troll's flip
			if(col_troll>=0 && col_troll<=9) {
//		
				if(col_troll>=0 && troll.getdx() <0 && isWall(levels.get(currentLevel)[row_troll][col_troll-1])) {
					troll.flip();
					troll.update(WIDTH, HEIGHT);
				}else if(col_troll<=8 && troll.getdx() >0 && isWall(levels.get(currentLevel)[row_troll][col_troll+1])) {
//					System.out.println("This is else if!");
					troll.flip();
					troll.update(WIDTH, HEIGHT);
				}else {
					troll.update(WIDTH, HEIGHT);
				}
			} 
			repaint();
			}
		});
		//Madison: Comment out to do the start screen
		timer.start();
		timer1.start();
		setFocusable(true);
		
		addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        int col = bubbles.getX() / SIZE; //SIZE=90
		        int row = bubbles.getY() / SIZE;
	
		        if (e.getKeyCode() == KeyEvent.VK_D) {
		            int nextCol = col + 1;
//		            if (nextCol < 10 && isWall(model.getMaze_level_1()[row][nextCol])) {
//		        	bubbles.move_x_right();
//		            }
		            if (nextCol < 10 && isWall(levels.get(currentLevel)[row][nextCol])==false) {
			        	bubbles.move_x_right();
			            }
		        }
		        if (e.getKeyCode() == KeyEvent.VK_A) {
		            int nextCol = col - 1;
//		            if (nextCol >= 0 && isWall(model.getMaze_level_1()[row][nextCol])) {
		            if (nextCol < 10 && isWall(levels.get(currentLevel)[row][nextCol])==false) {
		            bubbles.move_x_left();
		            }
		        }
		        if (e.getKeyCode() == KeyEvent.VK_W) {
		            int nextRow = row - 1;
//		            if (nextRow >= 0 && isWall(model.getMaze_level_1()[nextRow][col])) 
		            if (nextRow < 10 && isWall(levels.get(currentLevel)[nextRow][col])==false) {
		            	System.out.print("can push a button");
		            	bubbles.move_y_up();
		            }
		        }
		        if (e.getKeyCode() == KeyEvent.VK_S) {
		            int nextRow = row + 1;
//		            if (nextRow < 10 && isWall(model.getMaze_level_1()[nextRow][col])) 
		            	 if (nextRow < 10 && isWall(levels.get(currentLevel)[nextRow][col])==false){
		            bubbles.move_y_down();
		            }
		        }
		        
//		        Code that allows pickup of gems (keep the size>0 or exception/error); may need to rework into hashmap ****
		        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		        	System.out.println("Gems num: "+ gems.size());
//		        		System.out.println("MY PRECIOUS");
//		        	for (Gem gem : gems) {
//		        		if (gems.size()>0 && bubbles.getBounds().intersects(gem.getBounds())) {
//		        			System.out.println("MY PRECIOUS");
//		        			gems.remove(gem);
//		        		//Madison: Score goes up by one every time bubbles picks up a gem
//		        			score.addScore();
//		        	}
//		        	}
		        	System.out.println("key: " + levels.get(currentLevel)[row-1][col]);
		        	if(levels.get(currentLevel)[row][col]==KEYNUM) {
						key.setKey();
					}
		        	
		        	for(int i=0; i<gems.size(); i++) {
		        		if (gems.size()>0 && bubbles.getBounds().intersects(gems.get(i).getBounds())) {
		        			System.out.println("MY PRECIOUS");
		        			gems.remove(gems.get(i));
		        		//Madison: Score goes up by one every time bubbles picks up a gem
		        			score.addScore();
		        		}
//		        	if (e.getKeyCode()== KeyEvent.VK_SPACE) {
//		        		
//		        	}
		        	}
		        }
		    
		    }
		});
	}
	
//	public void startTimer() {
//		timer.start();
//		timer1.start();
//		
//	}

	/** 
	* Draws the game
	* @param graphics g		java graphics
	* @return void
	*/
	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
//		model.drawMap(g2);
		if(currentLevel == 1) {
			model.loadLevel_HardCode(g2, bubbles, trolls, gems, key, model.maze_level_1);
		} else if(currentLevel == 2) {
//			System.out.print(bubbles.getX() + " " + bubbles.getY());
			repaint();
			if (reset==false) {
				model.reset();
				trolls.removeAll(trolls);
				gems.removeAll(gems);
				key.setKey();
				model.initialize_HardCode(bubbles, trolls, gems, key, model.maze_level_2);
				timer1.start();
				reset=true;
			}
			model.loadLevel_HardCode(g2, bubbles, trolls, gems, key, model.maze_level_2);
//			timer.start();
//			timer1.start();
		}
//		model.loadLevel_HardCode(g2, bubbles, trolls, gems);
		bubbles.loadSpriteOnce();
		bubbles.draw(g2);
		for (Troll troll : trolls) {
			troll.draw(g2);
		}
		for (Gem gem: gems) {
			gem.draw(g2);
		}
		if(!key.isHaveKey()) {
			key.draw(g2);
		}
//		key.draw(g2);
		if (life_arr.size()==0) {
			lives.showGameOver(g);
			timer.stop();
			timer1.stop();
	        resetButton.setVisible(true);
		}
		        
			
		for(int i = 0; i <life_arr.size();i++) {
			life_arr.get(i).draw(g2, 600+i*50, 0);
		}
		
		

		// show score at the top of the left
		score.showScore(g);
		
		
		// show game over after life is 0
		
	}

	/** 
	* Determines if an entity (player or troll) can move through the next block
	* true: movement / false: no movement
	* @param c		a value gotten from gamemodel from the 2D array that gives the layout of the maze.
	* @return boolean
	*/
	public boolean isWall(int c) {
		if(c == 1)return true;
		return false;
	}	
	
//	public void startGame() {
//		startTimer();
		
//	}
	
	/** 
	* Resets the game to the beginning
	* @return void 
	*/
	private void resetGame() {

		model.reset();
		trolls.removeAll(trolls);
		gems.removeAll(gems);
		key.setKey();
		model.initialize_HardCode(bubbles, trolls, gems, key, model.maze_level_1);
		model.loadLevel_HardCode(getGraphics(), bubbles, trolls, gems, key, model.maze_level_1);
		
		
//		troll = new Troll(90,90);
//		gems = new ArrayList<>();
//		gems.add(new Gem(90,180));
//		lives = new Lives();;;
		life_arr.add(lives);
		life_arr.add(lives);
		life_arr.add(lives);
	    score = new Score();
	    resetButton.setVisible(false);
	    // restart
	    timer.start();
	    timer1.start();
	    repaint();
	}
	
}



