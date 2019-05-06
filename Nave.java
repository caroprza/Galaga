import java.awt.Image;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.ImageIcon;


public class Nave {
	private int speed, health;
	private int xPos, yPos;
	private Image sprite;
	public ArrayList<Bullet> bullets;
	
	public Nave() {
		sprite = new ImageIcon("navecita.png").getImage();
		speed = 10;
		health = 5;
		xPos = 550;
		yPos = 800;
		bullets = new ArrayList<Bullet>();
	}
	
	public void shoot(int activeGun) {
		bullets.add(new Bullet(activeGun, this.xPos + 30, this.yPos + 15));
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
		
	
}
