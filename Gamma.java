import javax.swing.ImageIcon;

public class Gamma extends Alien{
	
	public Gamma() {
		sprite = new ImageIcon("gamma.png").getImage();
		kind = 2;
		speed = 2;
		health = 100;
	}
	
	
	public Gamma(int xPos, int yPos) {
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
	
}
