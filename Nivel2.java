import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Nivel2 extends JPanel implements KeyListener, Runnable{
	
	private Nave nave;
	private Beta aliens[] = new Beta[30];
	private Ventana v;
	private Image bg;
	private boolean movingLeft, movingRight;
	private Thread hilo;
	
	public Nivel2(Ventana v) {
		super();
		this.v = v;
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.requestFocusInWindow();
		this.bg = new ImageIcon("src/space.gif").getImage();
		
		
		
		nave = new Nave();
		Random r = new Random();
		
		hilo = new Thread(this);
		hilo.start();
		
		//Draws all the aliens with random positions inside the playing area
		for(int i=0; i<10; i++) {
			aliens[i]= new Beta(r.nextInt(v.width), r.nextInt(v.height-450)+50);
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
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, v.width, 50);
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
