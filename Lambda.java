import javax.swing.ImageIcon;

public class Lambda extends Alien{
	
	public Lambda() {
		sprite = new ImageIcon("src/lambda.png").getImage();
		kind = 5;
	}
	
	
	public Lambda(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}



	public void move() {
		// TODO Auto-generated method stub
		
	}

}
