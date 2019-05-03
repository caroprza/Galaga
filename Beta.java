import javax.swing.ImageIcon;

public class Beta extends Alien{
	
	public Beta() {
		sprite = new ImageIcon("src/beta.png").getImage();
		kind = 2;
	}
	
	
	public Beta(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}


	public void move(int distance) {
		this.setxPos(this.getxPos()+distance);
	}


	@Override
	public void morir(int health) {
		// TODO Auto-generated method stub
		
	}

}
