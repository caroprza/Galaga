import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{
	
	public int width, height;
	public JPanel currentScreen;
	
	public Ventana(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		
		//Debería poderse cambiar solo el número de nivel y no el objeto, usar herencia de nivel u otra cosa.
		currentScreen = new HomeScreen();
		this.add(currentScreen);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Ventana v = new Ventana(900, 700);
	}
	
	
	
}
