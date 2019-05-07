import javax.swing.ImageIcon;

public class Gamma extends Alien{
	
	public Gamma() {
		sprite = new ImageIcon("gamma.png").getImage();
		kind = 3;
		speed = 2;
		health = 50;
	}
	
	
	public Gamma(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}


}
