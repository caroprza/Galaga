import javax.swing.ImageIcon;

public class Beta extends Alien{
	
	public Beta() {
		sprite = new ImageIcon("beta.png").getImage();
		kind = 2;
		speed = 3;
		health = 10;
	}
	
	
	public Beta(int xPos, int yPos) {
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
