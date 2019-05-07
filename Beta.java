import javax.swing.ImageIcon;

public class Beta extends Alien{
	
	public Beta() {
		sprite = new ImageIcon("beta.png").getImage();
		kind = 2;
		speed = 3;
		health = 25;
	}
	
	
	public Beta(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}


	

}
