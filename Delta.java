import javax.swing.ImageIcon;

public class Delta extends Alien{
	
	public Delta() {
		sprite = new ImageIcon("delta.png").getImage();
		kind = 4;
		speed = 5;
		health = 30;
	}
	
	
	public Delta(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}


	


}
