package ui;

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
import other.EndScreen;
import other.Gem;
import other.Key;
import other.Lives;
import other.Player1;
import other.Score;
import other.Troll;

/**
 * Class: Game Component
 * 
 * @author Ayaka, Adeline, Madison <br>
 *         Purpose: Runs the game; key listener, painting the screen, timers,
 *         resetting the game
 */
public class GameComponent extends JComponent {

	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	private Player1 bubbles = new Player1(0, 0);
//	private Troll troll = new Troll(90,90);
	private ArrayList<Troll> trolls = new ArrayList<>();
	private ArrayList<Gem> gems = new ArrayList<>();
	private Key key = new Key(0, 0);
	private Lives life = new Lives(0, 0);
	private ArrayList<Lives> life_arr = new ArrayList<>();
	private EndScreen ending = new EndScreen();
	private GameModel model;
	private Timer timer, timer1;

	// corresponding the tile number for key
	private static final char EXITNUM = 5;
	private static final char KEYNUM = 6;

	// corresponding the tile number for life
	private static final char KEYNUM_LIFE = 7;

	// for checking whether wall is or not
	private int nextX;
	private int nextY;
	private static final int SIZE = 90;

	// Madison: Score
	private Score score = new Score();

	// Ayaka: Live and button for restart
	private Lives lives = new Lives();
	private JButton resetButton;

	private int currentLevel = 1;
	private String levelName;

	public HashMap<Integer, int[][]> levels = new HashMap<>();

	private boolean life_paint = false;
	private boolean reset = false;

	// Madison: Displays the hearts
//	private Lives hearts = new Heart

	/**
	 * Constructor of Gamecomponent Handles everything
	 */
	public GameComponent(GameModel model) {
		this.model = model;

		levels.put(1, model.maze_level_1);
//		levels.put(3, model.maze_level_1);
		levels.put(2, model.maze_level_2);
		life_arr.add(lives);
		life_arr.add(lives);
		life_arr.add(lives);

		// reset button to restart after life is 0
		resetButton = new JButton("RESTART");
		resetButton.setBounds(400, 400, 100, 50);
		resetButton.setVisible(false); // invisible
		resetButton.addActionListener(e -> resetGame());
		this.add(resetButton);

		model.initialize_HardCode(bubbles, trolls, gems, key, life, model.maze_level_1);

		// For bubbles
		timer = new Timer(20, e -> {
			bubbles.update(WIDTH, HEIGHT);
			int bub_x = bubbles.getX();
			int bub_y = bubbles.getY();

			// collision bw a bubble and a troll
			for (Troll troll : trolls) {
				if (bub_x == troll.getx() && bub_y == troll.gety()) {
					life_arr.remove(0);
					troll.flip();
					troll.update(WIDTH, HEIGHT);

				}
			}
			if (levels.size() >= 1 && levels.get(currentLevel)[bub_y / SIZE][bub_x / SIZE] == EXITNUM
					&& key.isHaveKey()) {
				currentLevel += 1;
				reset = false;
			}
			repaint();
		});

		// for troll
		timer1 = new Timer(250, e -> {
			for (Troll troll : trolls) {
				int col_troll = troll.getx() / SIZE;
				int row_troll = troll.gety() / SIZE;

				// for troll's flip
				if (col_troll >= 0 && col_troll <= 9) {
//		
					if (col_troll >= 0 && troll.getdx() < 0
							&& isWall(levels.get(currentLevel)[row_troll][col_troll - 1])) {
						troll.flip();
						troll.update(WIDTH, HEIGHT);
					} else if (col_troll <= 8 && troll.getdx() > 0
							&& isWall(levels.get(currentLevel)[row_troll][col_troll + 1])) {
						troll.flip();
						troll.update(WIDTH, HEIGHT);
					} else {
						troll.update(WIDTH, HEIGHT);
					}
				}
				repaint();
			}
		});
		// Madison: Comment out to do the start screen
		timer.start();
		timer1.start();
		setFocusable(true);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int col = bubbles.getX() / SIZE; // SIZE=90
				int row = bubbles.getY() / SIZE;

				if (e.getKeyCode() == KeyEvent.VK_D) {
					int nextCol = col + 1;
					if (nextCol < 10 && isWall(levels.get(currentLevel)[row][nextCol]) == false) {
						bubbles.move_x_right();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					int nextCol = col - 1;
					if (nextCol < 10 && isWall(levels.get(currentLevel)[row][nextCol]) == false) {
						bubbles.move_x_left();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_W) {
					int nextRow = row - 1;
					if (nextRow < 10 && isWall(levels.get(currentLevel)[nextRow][col]) == false) {
						bubbles.move_y_up();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					int nextRow = row + 1;
					if (nextRow < 10 && isWall(levels.get(currentLevel)[nextRow][col]) == false) {
						bubbles.move_y_down();
					}
				}

//		        Code that allows pickup of gems (keep the size>0 or exception/error); may need to rework into hashmap ****
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					if (levels.get(currentLevel)[row][col] == KEYNUM) {
						key.setKey();
					}

					if (levels.get(currentLevel)[row][col] == KEYNUM_LIFE && life_arr.size() < 3) {
						life_arr.add(lives);
						life_paint = true;

					}

					for (int i = 0; i < gems.size(); i++) {
						if (gems.size() > 0 && bubbles.getBounds().intersects(gems.get(i).getBounds())) {
							gems.remove(gems.get(i));
							// Madison: Score goes up by one every time bubbles picks up a gem
							score.addScore();
						}
					}
				}

			}
		});
	}

	/**
	 * Draws the game
	 * 
	 * @param graphics g java graphics
	 * @return void
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (currentLevel == 1) {
			model.loadLevel_HardCode(g2, bubbles, trolls, gems, key, model.maze_level_1);
			bubbles.loadSpriteOnce();
			bubbles.draw(g2);
		} else if (currentLevel == 2) {
			bubbles.loadSpriteOnce();
			bubbles.draw(g2);
			repaint();
			if (reset == false) {
				model.reset();
				trolls.removeAll(trolls);
				gems.removeAll(gems);
				key.setKey();
				life_paint = false;
				model.initialize_HardCode(bubbles, trolls, gems, key, life, model.maze_level_2);
				timer1.start();
				reset = true;
			}
			model.loadLevel_HardCode(g2, bubbles, trolls, gems, key, model.maze_level_2);
		} else if (currentLevel == 3) {
			ending.showEnd(g2);
			trolls.removeAll(trolls);
			gems.removeAll(gems);
			life_paint = true;

		}

		for (Troll troll : trolls) {
			troll.draw(g2);
		}
		for (Gem gem : gems) {
			gem.draw(g2);
		}
		if (!key.isHaveKey()) {
			key.draw(g2);
		}

		if (life_paint == false) {
			life.drawbutnot(g2);
		}
//		key.draw(g2);
		if (life_arr.size() == 0) {
			lives.showGameOver(g);
			timer.stop();
			timer1.stop();
			resetButton.setVisible(true);
		}

		for (int i = 0; i < life_arr.size(); i++) {
			life_arr.get(i).draw(g2, 600 + i * 50, 0);
		}

		// show score at the top of the left
		score.showScore(g);

		// show game over after life is 0

	}//paintComponent

	/**
	 * Determines if an entity (player or troll) can move through the next block
	 * true: movement / false: no movement
	 * 
	 * @param c a value gotten from gamemodel from the 2D array that gives the
	 *          layout of the maze.
	 * @return boolean
	 */
	public boolean isWall(int c) {
		if (c == 1)
			return true;
		return false;
	}//isWall

	/**
	 * Resets the game to the beginning
	 * 
	 * @return void
	 */
	private void resetGame() {

		model.reset();
		trolls.removeAll(trolls);
		gems.removeAll(gems);
		key.setKey();
		model.initialize_HardCode(bubbles, trolls, gems, key, life, model.maze_level_1);
		model.loadLevel_HardCode(getGraphics(), bubbles, trolls, gems, key, model.maze_level_1);
		currentLevel = 1;

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
