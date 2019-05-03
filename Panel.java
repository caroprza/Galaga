import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener, Runnable{
	
	private Nave nave;
	private Alien aliens[] = new Alien[30];
	private Image bg;
	private boolean movingLeft, movingRight;
	private Thread hilo;
	private int width, height;
	
	public Panel() {
		super();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.requestFocusInWindow();
		this.width = 1200;
		this.height = 900;
		this.setPreferredSize(new Dimension(width, height));
		this.bg = new ImageIcon("src/space.gif").getImage();
		
		nave = new Nave();
		Random r = new Random();
		
		hilo = new Thread(this);
		hilo.start();
		
		
		
		//Draws all the aliens with random positions inside the playing area
		for(int i=0; i<10; i++) {
			aliens[i]= new Alfa(r.nextInt(this.width), r.nextInt(this.height-250)+50);
		}
		for(int i=10; i<15; i++) {
			aliens[i]= new Beta(r.nextInt(this.width), r.nextInt(this.height-250)+50);
		}
		for(int i=15; i<20; i++) {
			aliens[i]= new Gamma(r.nextInt(this.width), r.nextInt(this.height-250)+50);
		}
		for(int i=15; i<20; i++) {
			aliens[i]= new Delta(r.nextInt(this.width), r.nextInt(this.height-250)+50);
		}
		for(int i=20; i<30; i++) {
			aliens[i]= new Lambda(r.nextInt(this.width), r.nextInt(this.height-250)+50);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(nave.getSprite(), nave.getxPos(), 750, 100, 100, this);
		for(int i=0; i<30; i++) {
			g.drawImage(aliens[i].getSprite(), aliens[i].getxPos(), aliens[i].getyPos(), 50, 50, this);
		}
		
		
		//GUI
		
		//Drawing bullet data on top of the screen
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), 50);
	}

	public void run() {
		while(nave.getxPos()>= 0 && nave.getxPos()<=this.width) {
			try {
				Thread.sleep(20);
				if(movingLeft) {
					nave.setxPos(nave.getxPos()-nave.getSpeed());
				}
				if(movingRight) {
					nave.setxPos(nave.getxPos()+nave.getSpeed());
				}
				this.repaint();
			}
			catch (InterruptedException ex){
				System.out.println("Exception at Thread");
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movingRight = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			movingLeft = true;
		}

	}
	
	public void keyReleased(KeyEvent e) {
		movingLeft = movingRight = false;
	}
}
