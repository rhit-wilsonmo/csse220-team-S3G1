package other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class EndScreen {
	public void showEnd(Graphics2D g2) {

		g2.setColor(Color.GRAY);
		g2.fillRect(225, 225, 450, 450);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Arial", Font.BOLD, 50));
		g2.drawString("YOU WIN!!!!",320, 450);

	}

}
