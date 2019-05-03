import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Nivel extends JPanel implements KeyListener, Runnable{
	
	private Nave nave;
	private Alfa aliens[] = new Alfa[30];
	private Ventana v;
	private Image bg;
	private boolean movingLeft, movingRight;
	private Thread hilo;
	private int activeGun;
	
	public Nivel(Ventana v) {
		super();
		this.v = v;
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.requestFocusInWindow();
		this.bg = new ImageIcon("src/space.gif").getImage();
		
		
		
		nave = new Nave();
		activeGun = 1;
		Random r = new Random();
		
		hilo = new Thread(this);
		hilo.start();
		
		//Draws all the aliens with random positions inside the playing area
		for(int i=0; i<10; i++) {
			aliens[i]= new Alfa(r.nextInt(v.width), r.nextInt(v.height-450)+50);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(nave.getSprite(), nave.getxPos(), v.height-100, 70, 70, this);
		
		
		for(int i=0; i<10; i++) {
			g.drawImage(aliens[i].getSprite(), aliens[i].getxPos(), aliens[i].getyPos(), 80, 80, this);
		}
		
		
		//GUI
		
		//Drawing bullet data on top of the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, v.width, 50);
		g.drawImage(new ImageIcon("src/bullet-1.png").getImage(), 100, 25, 10, 20, this);
		g.drawImage(new ImageIcon("src/bullet-2.png").getImage(), 130, 25, 30, 30, this);
		g.drawImage(new ImageIcon("src/bullet-3.png").getImage(), 200, 25, 15, 30, this);
		
		g.drawImage(new ImageIcon("src/heart.png").getImage(), 800, 25, this);
		g.drawImage(new ImageIcon("src/heart.png").getImage(), 860, 25, this);
		g.drawImage(new ImageIcon("src/heart.png").getImage(), 830, 25, this);
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(20);
				if(movingLeft) {
					nave.setxPos(nave.getxPos()-nave.getSpeed());
				}
				if(movingRight) {
					nave.setxPos(nave.getxPos()+nave.getSpeed());
				}
				
				for(int i = 0; i<10; i++) {
					if(aliens[i].getxPos() >= v.getWidth()) {
						aliens[i].setSpeed((aliens[i].getSpeed() * -1) - 1);
						aliens[i].setyPos(aliens[i].getyPos() + 30);
					}
					else if (aliens[i].getxPos() <= 0) {
						aliens[i].setSpeed((aliens[i].getSpeed() * -1) + 1);
						aliens[i].setyPos(aliens[i].getyPos() + 30);
					}
					
					aliens[i].move();
				
				}
				this.repaint();
			}
			catch (InterruptedException ex){
				System.out.println("Exception at Thread");
			}
		}
		
	}

	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1) {
			activeGun = 1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_2) {
			activeGun = 2;
		}
		else if(e.getKeyCode() == KeyEvent.VK_3) {
			activeGun = 3;
		}
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("ARMA: "+ activeGun +" AMMO: "+ nave.bullets[activeGun].ammo);
			nave.shoot(activeGun);
		}
		
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
