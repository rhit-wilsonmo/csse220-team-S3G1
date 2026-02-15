package other;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartScreen extends JPanel {
	
	private JLabel titleLabel;
	private JButton startButton;
	public boolean has_key_pressed = false;
	
	public StartScreen() {
		
		setLayout(new BorderLayout());
		
		titleLabel = new JLabel("Press space to start", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
		
//		startButton = new JButton("Start");
//		startButton.setFont(new Font("Arial", Font.PLAIN, 20));
		
		add(titleLabel, BorderLayout.CENTER);
//		add(startButton, BorderLayout.SOUTH);
		
	}
	
//	public JButton getStartButton() {
//		return startButton;
//	}
//	
//	public void showStart() {
//		
//	}
	public void key() {
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

