import javax.swing.ImageIcon;

public class Alfa extends Alien{
	
	public Alfa() {
		sprite = new ImageIcon("src/alfa.png").getImage();
		kind = 1;
	}
	
	
	public Alfa(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}


	public void move(int distance) {
		this.setxPos(this.getxPos()+distance);
	}

}
