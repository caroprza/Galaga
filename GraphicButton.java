import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GraphicButton extends Rectangle{
		//GraphicButton es un rectangulo que le puedes poner texto. 
		
		String text;
		public GraphicButton(int x, int y, int width, int height, String text) {
			super(x,y,width, height);
			this.text = text;
		}
		
		public void paint(Graphics2D g){
			g.setColor(Color.BLACK);
			g.fill(this);
			g.setColor(Color.PINK);
			g.drawString(this.text, x+width/3, y+height/2);
		}
	}