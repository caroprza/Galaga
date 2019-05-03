import javax.swing.ImageIcon;

public class Delta extends Alien{

	public Delta() {
		sprite = new ImageIcon("src/delta.png").getImage();
		kind = 2;
	}
	
	
	public Delta(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}

	
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
