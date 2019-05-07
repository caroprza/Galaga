import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Alfa extends Alien{
	
	public Alfa() {
		sprite = new ImageIcon("alfa.png").getImage();
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
		
		
		// Hace movimiento de greca para Alfa
		if(this.getxPos()/52 % 2 == 0) {
			this.setyPos(this.getyPos()-5);
			super.boxCollider.y = this.getyPos();
		}
		else {
			this.setyPos(this.getyPos()+5);
			super.boxCollider.y = this.getyPos();
		}
		
		
		this.setxPos(this.getxPos()+this.getSpeed());
		this.boxCollider.x = this.getxPos();
	}
	


}
