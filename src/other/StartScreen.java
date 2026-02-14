package other;
import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {
	
	private JLabel titleLabel;
	JButton startButton;
	
	public StartScreen() {
		
		setLayout(new BorderLayout());
		
		titleLabel = new JLabel("Game Start", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
		
		startButton = new JButton("Start");
		startButton.setFont(new Font("Arial", Font.PLAIN, 20));
		
		add(titleLabel, BorderLayout.CENTER);
		add(startButton, BorderLayout.SOUTH);
		
	}
	
	public JButton getStartButton() {
		return startButton;
	}

}
