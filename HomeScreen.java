import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HomeScreen extends JPanel{
	
	public HomeScreen() {
		super();
		this.setBackground(Color.BLACK);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(400, 100, 400, 200);
		g.setColor(Color.RED);
		g.drawString("Play", 400, 200);
	}

}
