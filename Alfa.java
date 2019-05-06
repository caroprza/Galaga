import javax.swing.ImageIcon;

public class Alfa extends Alien{
	
	public Alfa() {
		sprite = new ImageIcon("src/alfa.png").getImage();
		kind = 1;
		speed = 3;
		health = 10;
	}
	
	
	public Alfa(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}


	public void move() {
		
		if(this.getxPos()/52 % 2 == 0) {
			this.setyPos(this.getyPos()-5);
		}
		else {
			this.setyPos(this.getyPos()+5);
		}
		this.setxPos(this.getxPos()+this.getSpeed());
	}
	
	public void morir(int health) {
		
	}


}
