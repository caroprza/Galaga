import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
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
	
	public Nivel() {
		super();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.requestFocusInWindow();
		this.width = 1200;
		this.height = 900;
		this.setPreferredSize(new Dimension(width, height));
		this.bg = new ImageIcon("src/space.gif").getImage();
		this.level = 1;
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
		for(int i=0; i<aliens.size(); i++) {
			g.drawImage(aliens.get(i).getSprite(), aliens.get(i).getxPos(), aliens.get(i).getyPos(), aliens.get(i).getWidth(), aliens.get(i).getHeight(), this);
		}
		
		
		
		//GUI
		
		//Drawing bullet data on top of the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, 50);
		g.drawImage(new ImageIcon("src/bullet-1.png").getImage(), 100, 25, 10, 20, this);
		g.drawImage(new ImageIcon("src/bullet-2.png").getImage(), 130, 25, 30, 30, this);
		g.drawImage(new ImageIcon("src/bullet-3.png").getImage(), 200, 25, 15, 30, this);
		
		
		g.drawImage(new ImageIcon("src/heart.png").getImage(), 800, 25, this);
		g.drawImage(new ImageIcon("src/heart.png").getImage(), 860, 25, this);
		g.drawImage(new ImageIcon("src/heart.png").getImage(), 830, 25, this);
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Comic Sans", Font.BOLD, 30));
		
		g.drawString(Integer.toString(activeGun), 20, 40);
		
		//Bullets
		for (int i = 0; i < this.nave.bullets.size(); i++) {
			g.drawImage(this.nave.bullets.get(i).sprite, this.nave.bullets.get(i).xPos, this.nave.bullets.get(i).yPos, this.nave.bullets.get(i).width, this.nave.bullets.get(i).height, this);
		}
		
		//Power
		g.setColor(Color.ORANGE);
		g.fillRect(300, 10, this.power, 30);
				
		
		
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
				
				
				for (int i = 10; i < this.nave.bullets.size(); i++) {
					this.nave.bullets.get(i).yPos -= this.nave.bullets.get(i).speed;
					this.nave.bullets.get(i).boxCollider.y = this.nave.bullets.get(i).yPos;
					this.nave.bullets.get(i).boxCollider.x = this.nave.bullets.get(i).xPos;
					
					
					for (int j = 0; j < this.aliens.size(); j++) {

						if (this.nave.bullets.get(i).boxCollider.intersects(this.aliens.get(j).boxCollider)) {
							this.aliens.get(j).setHealth(this.aliens.get(j).getHealth() - this.nave.bullets.get(i).damage);
							if (this.aliens.get(j).getHealth() <= 0) {
								this.aliens.remove(j);
								j--;
							}
							this.nave.bullets.remove(i);
							i--;
						}
					}
				}
				
				//Alien movement, rebota cuando pasa del width o cuando se va menor a 0
				for(int i = 0; i<aliens.size(); i++) {
					if(aliens.get(i).getxPos() >= this.width) {
						aliens.get(i).setSpeed((aliens.get(i).getSpeed() * -1) - 1);
						aliens.get(i).setyPos(aliens.get(i).getyPos() + 30);
					}
					else if (aliens.get(i).getxPos() <= 0) {
						aliens.get(i).setSpeed((aliens.get(i).getSpeed() * -1) + 1);
						aliens.get(i).setyPos(aliens.get(i).getyPos() + 30);
					}
					
					if (aliens.get(i).getyPos() > this.height - 50) {
						this.aliens.get(i).setyPos(100);
						this.nave.setLives(this.nave.getLives() - 1);
						System.out.println(this.nave.getLives());
						if (this.nave.getLives() <= 0) {
							System.out.println("GAME OVER");
						}
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
				power -= 20;
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
