import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Nivel extends JPanel implements KeyListener, Runnable{
	
	private Nave nave;
	private ArrayList<Alfa> aliens;
	private Image bg;
	private boolean movingLeft, movingRight;
	private Thread hilo;
	private int activeGun, height, width, level, power;
	
	public Nivel(Ventana v) {
		super();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.requestFocusInWindow();
		this.width = 1000;
		this.height = 800;
		this.setPreferredSize(new Dimension(width, height));
		this.bg = new ImageIcon("space.gif").getImage();
		this.level = 5;
		this.power= 0;
		aliens = new ArrayList<Alfa>();
		
		nave = new Nave();
		this.activeGun = 1;
		Random r = new Random();
		
		hilo = new Thread(this);
		hilo.start();
		
		
		
		//Creates all the aliens with random positions inside the playing area
		for(int i=0; i<10; i++) {
			aliens.add(new Alfa(r.nextInt(this.width), r.nextInt(this.height-450)+50));
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(nave.getSprite(), nave.getxPos(), this.height-100, 70, 70, this);
		
		//Aliens
		for(int i=0; i<10; i++) {
			g.drawImage(aliens.get(i).getSprite(), aliens.get(i).getxPos(), aliens.get(i).getyPos(), aliens.get(i).getWidth(), aliens.get(i).getHeight(), this);
		}
		
		//Bullets
		for (int i = 0; i < this.nave.bullets.size(); i++) {
			g.drawImage(this.nave.bullets.get(i).sprite, this.nave.bullets.get(i).xPos, this.nave.bullets.get(i).yPos, this.nave.bullets.get(i).width, this.nave.bullets.get(i).height, this);
		}
		
		//GUI
		g.setFont(new Font("Atari", Font.CENTER_BASELINE, 30));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, 50);
		
		
		//Lives
		for(int i=0; i<this.nave.getHealth(); i++) {
			g.drawImage(new ImageIcon("heart.png").getImage(), 850-30*i, 10, this);
		}

		//Power
		g.setColor(Color.MAGENTA);
		g.fillRect(300, 15, this.power, 20);
				
		//Gun
		g.setColor(Color.YELLOW);
		g.drawImage(new ImageIcon("bullet-"+ activeGun +".png").getImage(), 60, 15, 20, 20, this);
		g.drawString(Integer.toString(activeGun), 20, 35);
		
		
		
		
		
		
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(20);
				
				if(power < 300) {
					power++;
				}
				
				if(movingLeft) {
					nave.setxPos(nave.getxPos()-nave.getSpeed());
				}
				if(movingRight) {
					nave.setxPos(nave.getxPos()+nave.getSpeed());
				}
				
				//Bullets movement
				for (int i = 0; i < this.nave.bullets.size(); i++) {
					this.nave.bullets.get(i).yPos -= this.nave.bullets.get(i).speed;
					
				}
				
				//Aliens movement
				for(int i = 0; i<10; i++) {
					if(aliens.get(i).getxPos() >= this.width) {
						aliens.get(i).setSpeed((aliens.get(i).getSpeed() * -1) - 1);
						aliens.get(i).setyPos(aliens.get(i).getyPos() + 30);
					}
					else if (aliens.get(i).getxPos() <= 0) {
						aliens.get(i).setSpeed((aliens.get(i).getSpeed() * -1) + 1);
						aliens.get(i).setyPos(aliens.get(i).getyPos() + 30);
					}
					
					aliens.get(i).move();
				
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
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(power>40) {
				nave.shoot(activeGun);
				if(activeGun == 1) {
					power -= 20;
				}
				if(activeGun == 2) {
					power -= 50;
				}
				if(activeGun == 3) {
					power -= 80;
				}
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_1) {
			this.activeGun = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			if(2 <= this.level) {
				this.activeGun = 2;
			}
			else {
				System.out.println("NO HAY EN LA TIENDA MA");
			}

		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			if(3 <= this.level) {
				this.activeGun = 3;
			}
			else {
				System.out.println("NO HAY EN LA TIENDA MA");
			}
		}
	}
}
