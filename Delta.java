import javax.swing.ImageIcon;

public class Delta extends Alien{
	
	public Delta() {
		sprite = new ImageIcon("delta.png").getImage();
		kind = 4;
		speed = 5;
		health = 10;
	}
	
	
	public Delta(int xPos, int yPos) {
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
