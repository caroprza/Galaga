import java.awt.Dimension;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	public int width, height;
	
	public Ventana(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		Panel p = new Panel(this);
		this.add(p);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Ventana v = new Ventana(900, 700);
	}
	
	
	
}
