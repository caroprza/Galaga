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

public class LevelChooser extends JPanel implements MouseListener{
	
	GraphicButton level1 = new GraphicButton(250, 200, 100, 100, "1");
	GraphicButton level2 = new GraphicButton(400, 200, 100, 100, "2");
	GraphicButton level3 = new GraphicButton(550, 200, 100, 100, "3");
	GraphicButton level4 = new GraphicButton(300, 350, 100, 100, "4");
	GraphicButton level5 = new GraphicButton(450, 350, 100, 100, "5");

	Ventana v;
	private Image bg;
	
	
	public LevelChooser(Ventana v) {
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
		level1.paint(g2);
		level2.paint(g2);
		level3.paint(g2);
		level4.paint(g2);
		level5.paint(g2);
		
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Atari", Font.CENTER_BASELINE, 20));
		g.drawString("Created by JC and CP. 2019 - POO - ITESM GDA", 200, 600);
		
		
	}

	public void mouseClicked(MouseEvent e) {
		//Cuando el usuario hace clic, se cambia el panel en ventana.
		Rectangle clic = new  Rectangle(e.getX(), e.getY(), 1, 1);
		if (level1.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			Nivel a = new Nivel(v, 1, randomAliens(10, 0, 0, 0, 0));
			v.setCurrentScreen(a);
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
		}
		else if (level2.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			Nivel a = new Nivel(v, 2, randomAliens(5, 10, 0, 0, 0));
			v.setCurrentScreen(a);
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
		}
		else if (level3.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			Nivel a = new Nivel(v, 3, randomAliens(5, 5, 10, 0, 0));
			v.setCurrentScreen(a);
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
		}
		else if (level4.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			Nivel a = new Nivel(v, 4, randomAliens(3, 3, 4, 6, 0));
			v.setCurrentScreen(a);
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
		}
		else if (level5.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			Nivel a = new Nivel(v, 5, randomAliens(5, 2, 3, 2, 3));
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
	
	
	public static ArrayList<Alien> randomAliens(int nAlfa, int nBeta, int nGamma, int nDelta, int nLambda){
		
		ArrayList<Alien> aliens = new ArrayList<Alien>();
		Random r = new Random();
		
		for(int i=0; i<nAlfa; i++) {
			aliens.add(new Alfa(r.nextInt(900), r.nextInt(800-450)+50));
		}
		for(int i=0; i<nBeta; i++) {
			aliens.add(new Beta(r.nextInt(900), r.nextInt(800-450)+50));
		}
		for(int i=0; i<nGamma; i++) {
			aliens.add(new Gamma(r.nextInt(900), r.nextInt(800-450)+50));
		}
		for(int i=0; i<nDelta; i++) {
			aliens.add(new Delta(r.nextInt(900), r.nextInt(800-450)+50));
		}
		for(int i=0; i<nLambda; i++) {
			aliens.add(new Lambda(r.nextInt(900), r.nextInt(800-450)+50));
		}
		
		return aliens;
	}
	
}
