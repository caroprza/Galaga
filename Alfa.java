import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Alfa extends Alien{
	
	public Alfa() {
		sprite = new ImageIcon("alfa.png").getImage();
		kind = 1;
		speed = 3;
		health = 15;
	}
	
	
	public Alfa(int xPos, int yPos) {
		this();
		this.xPos = xPos;
		this.yPos = yPos;
	}


	
	


}
