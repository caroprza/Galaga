import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Nivel extends JPanel implements KeyListener, MouseListener, Runnable{
	
	private Nave nave;
	private ArrayList<Alien> aliens;
	private Image bg;
	private boolean movingLeft, movingRight, pause, gameOver, youWon;
	private Thread hilo;
	private int activeGun, height, width, level, power, threadTime;
	private GraphicButton exitBtn;
	private Ventana v;
	
	public Nivel(Ventana v, int level, ArrayList<Alien> aliens) {
		super();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.requestFocusInWindow();
		this.width = 1000;
		this.height = 800;
		this.setPreferredSize(new Dimension(width, height));
		this.bg = new ImageIcon("space.gif").getImage();
		this.level = level;
		this.power= 300;
		this.aliens = aliens;
		threadTime = 20;
		exitBtn = new GraphicButton(315, 300, 300, 100, "SALIR");
		this.v = v;
		
		
		nave = new Nave();
		this.activeGun = 1;
		
		
		hilo = new Thread(this);
		hilo.start();
		
		this.addMouseListener(this);
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 0, 0, this.getWidth(), this.getHeight(), this);
		
		
		//Nave
		g.drawImage(nave.getSprite(), nave.getxPos(), this.height-200, 70, 70, this);
		
		
		//Aliens
		for(int i=0; i<aliens.size(); i++) {
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
		for(int i=0; i<this.nave.getLives(); i++) {
			g.drawImage(new ImageIcon("heart.png").getImage(), 850-30*i, 10, this);
		}

		//Power
		g.setColor(Color.MAGENTA);
		g.fillRect(300, 15, this.power, 20);
				
		//Gun
		g.setColor(Color.YELLOW);
		g.drawImage(new ImageIcon("bullet-"+ activeGun +".png").getImage(), 60, 15, 20, 20, this);
		g.drawString(Integer.toString(activeGun), 20, 35);
		
		
		//Pause
		if(pause) {
			Graphics2D g2 = (Graphics2D)g;
			
			g.setColor(Color.WHITE);
			g.fillRect(190, 190, 520, 320);
			g.setColor(Color.BLACK);
			g.fillRect(200, 200, 500, 300);
			exitBtn.paint(g2);
			
		}
		
		if(gameOver) {
			Graphics2D g2 = (Graphics2D)g;
			
			g.setColor(Color.WHITE);
			g.fillRect(190, 190, 520, 320);
			g.setColor(Color.BLACK);
			g.fillRect(200, 200, 500, 300);
			new GraphicButton(285, 250, 300, 50, "GAME OVER").paint(g2);
			exitBtn.paint(g2);
		}
		
		if(youWon) {
			Graphics2D g2 = (Graphics2D)g;
			
			g.setColor(Color.WHITE);
			g.fillRect(190, 190, 520, 320);
			g.setColor(Color.BLACK);
			g.fillRect(200, 200, 500, 300);
			new GraphicButton(275, 250, 300, 50, "¡Has ganado!").paint(g2);
			exitBtn.paint(g2);
			
		}
		
		
		
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(this.threadTime);
				
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
						if (this.nave.getLives() <= 0) {
							//GAME OVER
								gameOver = true;
						}
					}
					aliens.get(i).move();
				
				}
				
				
				//You WON
				if(this.aliens.size() == 0) {
					youWon = true;
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
		else if(e.getKeyCode() == KeyEvent.VK_0) {
			//When 0 is pressed, you get a slow mo effect by changing the thread sleep rate, you get charged 150 power units.
			//If it is already slow, you only change the sleep rate again.
			if(this.threadTime == 50) {
				this.threadTime = 20;
			}
			else if(this.power >= 150) {
				this.threadTime = this.threadTime == 50? 20 : 50;
				this.power -= 150;
				this.power += 50;
			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			//When ESC is pressed, pauses the game, if pressed again, then it resumes.
			this.threadTime = this.threadTime == 1000? 20 : 1000; 
			this.pause = this.pause ? false : true; 
		}
		
		//FOR DEBUGGING
		else if(e.getKeyCode() == KeyEvent.VK_COMMA) {
			this.threadTime -=1;
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

		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			if(3 <= this.level) {
				this.activeGun = 3;
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		Rectangle clic = new  Rectangle(e.getX(), e.getY(), 1, 1);
		if(exitBtn.intersects(clic)) {
			v.remove(v.getCurrentScreen());
			HomeScreen a = new HomeScreen(v);
			v.setCurrentScreen(a);
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
			v.revalidate();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
