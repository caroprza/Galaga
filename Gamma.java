import javax.swing.ImageIcon;

public class Gamma extends Alien{

	public Gamma() {
		sprite = new ImageIcon("gamma.png").getImage();
		kind = 2;
	}
	
	
	public Gamma(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}

	
	public void move() {
		
	}

}
