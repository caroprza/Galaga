import javax.swing.ImageIcon;

public class Lambda extends Alien{
	
	public Lambda() {
		sprite = new ImageIcon("lambda.png").getImage();
		kind = 2;
		speed = 4;
		health = 50;
	}
	
	
	public Lambda(int xPos, int yPos) {
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
