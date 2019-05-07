import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class HomeScreen extends JPanel implements MouseListener{
	
	GraphicButton play = new GraphicButton(350, 200, 200, 100, "PLAY");
	GraphicButton controls = new GraphicButton(300, 350, 300, 100, "CONTROLS");

	Ventana v;
	private Image bg;
	
	
	public HomeScreen(Ventana v) {
		super();
		this.setPreferredSize(new Dimension(1000, 800));
		this.addMouseListener(this);
		this.requestFocus();
		this.v = v;
		this.bg = new ImageIcon("b.gif").getImage();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 0, 0, this.getWidth(), this.getHeight(), this);

		Graphics2D g2 = (Graphics2D)g;
		
		g2.setFont(new Font("Atari", Font.CENTER_BASELINE, 90));
		g2.setColor(Color.WHITE);
		g.drawString("ZERAPPIO", 265, 120);
		g2.setColor(Color.PINK);
		g.drawString("ZERAPPIO", 270, 125);

		g2.setFont(new Font("Atari", Font.CENTER_BASELINE, 30));
		g2.setColor(Color.PINK);
		play.paint(g2);
		controls.paint(g2);
		
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Atari", Font.CENTER_BASELINE, 20));
		g.drawString("Created by JC and CP. 2019 - POO - ITESM GDA", 200, 600);
		
		
	}

	public void mouseClicked(MouseEvent e) {
		//Cuando el usuario hace clic, se cambia el panel en ventana.
		Rectangle clic = new  Rectangle(e.getX(), e.getY(), 1, 1);
		if (play.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			LevelChooser a = new LevelChooser(v);
			v.setCurrentScreen(a);
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
		}
		else if (controls.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			Controls a = new Controls(v);
			v.setCurrentScreen(a);
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
		}
		
		v.revalidate();
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
