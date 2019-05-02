import java.awt.Image;

import javax.swing.ImageIcon;


public class Nave {
	private int speed, health;
	private Bullet bullets[];
	private int xPos, yPos;
	private Image sprite;
	
	public class Bullet {
		int kind;
		boolean isAvailable;
		int ammo;
		
		public Bullet() {
			
		}
		
		public Bullet(int kind, boolean isAvailable, int ammo) {
			this.kind = kind;
			this.isAvailable = isAvailable;
			this.ammo = ammo;
		}
		
		
	}
	
	public Nave() {
		sprite = new ImageIcon("src/navecita.png").getImage();
		bullets = new Bullet[3];
		speed = 6;
		bullets[0] = new Bullet(1,true,200);
		bullets[1] = new Bullet();
		bullets[2] = new Bullet();
		xPos = 300;
		yPos = 300;
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
