import javax.swing.ImageIcon;

public class Lambda extends Alien{
	
	public Lambda() {
		sprite = new ImageIcon("lambda.png").getImage();
		kind = 5;
		speed = 4;
		health = 100;
	}
	
	
	public Lambda(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}




}
