package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameModel;
import other.StartScreen;

public class Controller extends JPanel {
	public boolean has_key_pressed = false;

	public Controller() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					has_key_pressed=true;
				}
			}
		});
	}
}


