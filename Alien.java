import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Alien {
	protected int speed, health, kind;
	protected int xPos, yPos;
	protected Image sprite;
	
	public Alien() {
		sprite = new ImageIcon("src/alfa.png").getImage();
	}
	
	public abstract void move(int distance);
	public abstract void morir(int health);

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

	
	public int getKind() {
		return kind;
	}
	
	
}
