import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	public Ventana() {
		super("Gala");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Nivel nivel1 = new Nivel();
		this.add(nivel1);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Ventana v = new Ventana();
	}
	
	
	
}
