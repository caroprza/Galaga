import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Alien {
	protected int speed, health, kind;
	protected int xPos, yPos, width, height;
	protected Image sprite;
	protected Rectangle boxCollider;
	
	public Alien() {
		this.sprite = new ImageIcon("alfa.png").getImage();
		this.width = 80;
		this.height = 80;
		boxCollider = new Rectangle(this.xPos, this.yPos, this.width, this.height);
	}
	
public void move() {
		
		
		// Hace movimiento de greca para Alfa
		if(this.getxPos()/52 % 2 == 0) {
			this.setyPos(this.getyPos()-5);
			this.boxCollider.y = this.getyPos();
		}
		else {
			this.setyPos(this.getyPos()+5);
			this.boxCollider.y = this.getyPos();
		}
		
		
		this.setxPos(this.getxPos()+this.getSpeed());
		this.boxCollider.x = this.getxPos();
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

	
	public int getKind() {
		return kind;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
